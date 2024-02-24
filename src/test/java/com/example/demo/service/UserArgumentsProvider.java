package com.example.demo.service;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import com.example.demo.entity.User;

import java.util.ArrayList;
import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(User.builder().username("deep1").password("123").roles(new ArrayList<String>()).build()),
                Arguments.of(User.builder().username("suraj").password("123").roles(new ArrayList<String>()).build())
                );
    }
}
