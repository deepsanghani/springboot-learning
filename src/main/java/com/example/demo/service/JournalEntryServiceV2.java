package com.example.demo.service;


import com.example.demo.entity.JournalEntryV2;
import com.example.demo.entity.User;
import com.example.demo.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryServiceV2 {
    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntryV2 journalEntry, String username){
        User user = userService.findByUserName(username);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntryV2 saved = journalEntryRepo.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);
    }

    public void saveEntry(JournalEntryV2 journalEntry){
        journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntryV2> getAllEntry(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntryV2> findById(ObjectId id){
        return journalEntryRepo.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String username){
        boolean removed = false;
        try {
            User user = userService.findByUserName(username);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveEntry(user);
                journalEntryRepo.deleteById(id);
            }
        } catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occured", e);
        }
        return removed;
    }

    public  List<JournalEntryV2> findByUsername(String username){
        return null;
    }
}
