package com.example.demo.controller;

import com.example.demo.api.response.WeatherApiResponse;
import com.example.demo.entity.JournalEntry;
import com.example.demo.entity.JournalEntryV2;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.JournalEntryServiceV2;
import com.example.demo.service.UserService;
import com.example.demo.service.WeatherService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private WeatherService weatherService;
//
//    @GetMapping
//    public List<User> getAllUser(){
//        return userService.getAll();
//    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDb = userService.findByUserName(username);
        userInDb.setPassword(user.getPassword());
        userService.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserByName(String username){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepo.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherApiResponse weatherApiResponse = weatherService.getWeather("Jamnagar");
        String greeting = "";
        if(weatherApiResponse != null){
            greeting = " Weather feels like" + weatherApiResponse.toString();
        }
        return new ResponseEntity<>("Hi " + authentication.getName() + greeting, HttpStatus.OK);
    }
}
