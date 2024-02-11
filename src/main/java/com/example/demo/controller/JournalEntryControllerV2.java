package com.example.demo.controller;

import com.example.demo.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
    @GetMapping("/abc")
    public void getAll(){

    }

    @PostMapping
    public void createEntry(@RequestBody JournalEntry entry){

    }

    @GetMapping
    public void getEntryById(@RequestParam long id){

    }

    @GetMapping("/id/{id}")
    public void getEntryByPathVarId(@PathVariable long id){

    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable long id){

    }

    @PutMapping("{id}")
    public void updateJournalEntry(@PathVariable long id, @RequestBody JournalEntry journalEntry){

    }
}
