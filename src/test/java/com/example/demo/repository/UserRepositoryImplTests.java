package com.example.demo.repository;

import com.example.demo.entity.User;
import com.example.demo.service.UserArgumentsProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserRepositoryImplTests {
    @Mock
    private UserRepoImpl userRepo;

    @Disabled
    @Test
    public void testSaveNewUser(){
        Assertions.assertNotNull(userRepo.getUserForSA());
    }
}
