package com.ahmet.tutorial.service;

import com.ahmet.tutorial.dto.UserDto;
import com.ahmet.tutorial.entity.User;
import com.ahmet.tutorial.exception.ResourceNotFoundException;
import com.ahmet.tutorial.repository.UserRepository;
import com.ahmet.tutorial.service.impl.UserServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository repository;

    @BeforeAll
    public static void setup() {
        System.out.println("Unit tests starting");
    }

    @AfterAll
    public static void finish() {
        System.out.println("Unit tests finished");
    }

    @BeforeEach
    public void beforeEachTest() {
        System.out.println("Unit test being called");
    }

    @AfterEach
    public void afterEachTest() {
        System.out.println("Unit test finished running");
    }

    @Test
    void getUserByIdTest() {
        when(repository.findById(1L)).thenReturn(createUser());
        UserDto user = userService.getUserById(1L);
        assertEquals("Ahmet", user.getFirstName());
    }

    private Optional<User> createUser() {
        return Optional.of(User.builder()
                               .id(1L)
                               .firstName("Ahmet")
                               .build());
    }

//    @Disabled
//    public void disabledTest() {
//        System.out.println("Ignore this test");
//    }

    @Test
    void exceptionTest() {
        when(repository.findById(1L)).thenReturn(nullUser());
        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(1L));
    }

    private Optional<User> nullUser() {
        return Optional.empty();
    }

}
