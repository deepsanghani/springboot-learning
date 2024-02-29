package com.example.demo.service;


import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserSeviceTests {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Disabled
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveUser(User user){
        assertTrue(userService.saveNewUser(user));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,2,3",
            "4,5,9",
            "3,3,5"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected,a+b);
    }
}
