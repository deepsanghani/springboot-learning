package com.example.demo.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Disabled
    @Test
    void testEmail(){
        emailService.sendEmail("deepsanghani3@gmail.com","test", "kaise ho app");
    }
}
