package com.example.demo.controller;

import com.example.demo.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class JournalEntryController {
    private Map<Long, JournalEntry> entries = new HashMap<>();
    @GetMapping("/abc")
    public List<JournalEntry> getAll(){
        return new ArrayList<>(entries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry entry){
        entries.put(entry.getId(), entry);
        return true;
    }

    @GetMapping
    public JournalEntry getEntryById(@RequestParam long id){
        return entries.get(id);
    }

    @GetMapping("/id/{id}")
    public JournalEntry getEntryByPathVarId(@PathVariable long id){
        return entries.get(id);
    }

    @DeleteMapping("{id}")
    public JournalEntry deleteById(@PathVariable long id){
        return entries.remove(id);
    }

    @PutMapping("{id}")
    public String updateJournalEntry(@PathVariable long id, @RequestBody JournalEntry journalEntry){
        JournalEntry oldEntry = entries.get(id);
        if(oldEntry==null){
            return "Not found";
        }
        else{
            entries.put(id, journalEntry);
            return "Updated Successfully!";
        }
    }
}
