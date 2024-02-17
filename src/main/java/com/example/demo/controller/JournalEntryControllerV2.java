package com.example.demo.controller;

import com.example.demo.entity.JournalEntry;
import com.example.demo.entity.JournalEntryV2;
import com.example.demo.entity.User;
import com.example.demo.service.JournalEntryServiceV2;
import com.example.demo.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryServiceV2 journalEntryServiceV2;

    @Autowired
    private UserService userService;

    @GetMapping("{username}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String username){
        User user = userService.findByUserName(username);
        List<JournalEntryV2> allEntry = user.getJournalEntries();
        if(allEntry!=null && !allEntry.isEmpty()){
            return new ResponseEntity<>(allEntry, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("{username}")
    public ResponseEntity<JournalEntryV2> createEntry(@RequestBody JournalEntryV2 entry, @PathVariable String username){
        try {
            User user = userService.findByUserName(username);
            entry.setDate(LocalDateTime.now());
            journalEntryServiceV2.saveEntry(entry, username);
            return new ResponseEntity<>(entry, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntryV2> getEntryByPathVarId(@PathVariable ObjectId id){
        Optional<JournalEntryV2> journalEntryServiceV2ById = journalEntryServiceV2.findById(id);
        if(journalEntryServiceV2ById.isPresent()){
            return new ResponseEntity<>(journalEntryServiceV2ById.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{username}/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId id, @PathVariable String username){
        journalEntryServiceV2.deleteById(id, username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{username}/{id}")
    public ResponseEntity<?> updateJournalEntry(@PathVariable ObjectId id,
            @PathVariable String username, @RequestBody JournalEntry entry){
        JournalEntryV2 old = journalEntryServiceV2.findById(id).orElse(null);
        if(old!=null) {
            old.setTitle(entry.getTitle() != null && !entry.getTitle().equals("") ? entry.getTitle() : old.getTitle());
            old.setContent(entry.getContent() != null && !entry.getContent().equals("") ? entry.getContent() : old.getContent());
            journalEntryServiceV2.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
