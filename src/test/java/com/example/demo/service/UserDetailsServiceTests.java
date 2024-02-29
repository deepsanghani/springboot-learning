package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
import org.bson.assertions.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.*;

import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

public class UserDetailsServiceTests {
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;


    @Mock
    private UserRepo userRepo;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Disabled
    @Test
    void loadUserByUsernameTest(){
        when(userRepo.findByUsername(ArgumentMatchers.anyString())).thenReturn(User.builder().username("Ram").password("abcd").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("ram");
        assertNotNull(user);
    }
}
