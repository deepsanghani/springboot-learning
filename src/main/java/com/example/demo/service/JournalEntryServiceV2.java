package com.example.demo.service;


import com.example.demo.entity.JournalEntryV2;
import com.example.demo.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryServiceV2 {
    @Autowired
    private JournalEntryRepo journalEntryRepo;

    public void saveEntry(JournalEntryV2 journalEntry){
        journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntryV2> getAllEntry(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntryV2> findById(ObjectId id){
        return journalEntryRepo.findById(id);
    }

    public void deleteById(ObjectId id){
        journalEntryRepo.deleteById(id);
    }
}
