package com.example.medicalreservationsystem.users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService();
        setPrivateField(userService, "userRepository", userRepository);
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User();
        user1.setUsername("user1");

        User user2 = new User();
        user2.setUsername("user2");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.getAllUsers();
        assertEquals(2, users.size());
        assertEquals("user1", users.get(0).getUsername());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetByIdReturnsUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("test");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getById(1L);
        assertNotNull(result);
        assertEquals("test", result.getUsername());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testGetByIdReturnsNullWhenNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        User result = userService.getById(99L);
        assertNull(result);
        verify(userRepository, times(1)).findById(99L);
    }

    private void setPrivateField(Object target, String fieldName, Object value) {
        try {
            var field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
