package com.matchmaker.user_mgt;

import com.matchmaker.user_mgt.model.Gender;
import com.matchmaker.user_mgt.model.Role;
import com.matchmaker.user_mgt.model.User;
import com.matchmaker.user_mgt.model.dto.UserRequest;
import com.matchmaker.user_mgt.repository.UserRepository;
import com.matchmaker.user_mgt.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private UserRequest userRequest;
    private User user;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        userRequest = new UserRequest();
        userRequest.setEmail("test@example.com");
        userRequest.setFullName("John Doe");
        userRequest.setGender(Gender.MALE);
        userRequest.setPassword("password123");

        user = new User();
        user.setUserId(1L);
        user.setEmail("test@example.com");
        user.setFullName("John Doe");
        user.setGender(Gender.MALE);
        user.setPassword("encodedPassword");
        user.setRole(Role.USER);
    }

    @Test
    public void testCreateUser() {
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);
        User createdUser = userService.createUser(userRequest);
        assertNotNull(createdUser);
        assertEquals("test@example.com", createdUser.getEmail());
        assertEquals("John Doe", createdUser.getFullName());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
        var users = userService.getAllUsers();
        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetUserById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        Optional<User> foundUser = userService.getUserById(1L);
        assertTrue(foundUser.isPresent());
        assertEquals("test@example.com", foundUser.get().getEmail());
        verify(userRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testDeleteUser_UserExists() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        String email = "nonexistent@example.com";
        ResponseEntity<Void> response = userService.deleteUser(email);
        assertEquals(204, response.getStatusCode().value()); // No Content
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteUser_UserNotFound() {

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        String email = "nonexistent@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        ResponseEntity<Void> response = userService.deleteUser(email);
        assertEquals(404, response.getStatusCode().value());
        verify(userRepository, never()).deleteById(anyLong());
    }

}