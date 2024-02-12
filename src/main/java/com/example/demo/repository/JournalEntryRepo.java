package com.example.demo.repository;

import com.example.demo.entity.JournalEntry;
import com.example.demo.entity.JournalEntryV2;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepo extends MongoRepository<JournalEntryV2, ObjectId> {

}
