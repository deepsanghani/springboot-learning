package com.example.demo.controller;

import com.example.demo.entity.JournalEntry;
import com.example.demo.entity.JournalEntryV2;
import com.example.demo.service.JournalEntryServiceV2;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
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

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<JournalEntryV2> allEntry = journalEntryServiceV2.getAllEntry();
        if(allEntry!=null && !allEntry.isEmpty()){
            return new ResponseEntity<>(allEntry, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<JournalEntryV2> createEntry(@RequestBody JournalEntryV2 entry){
        try {
            entry.setDate(LocalDateTime.now());
            journalEntryServiceV2.saveEntry(entry);
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

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId id){
        journalEntryServiceV2.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry entry){
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
