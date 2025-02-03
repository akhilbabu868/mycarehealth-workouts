package com.matchmaker.user_mgt.controller;

import com.matchmaker.user_mgt.exception.ResourceNotFoundException;
import com.matchmaker.user_mgt.model.User;
import com.matchmaker.user_mgt.service.JwtService;
import com.matchmaker.user_mgt.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found"));
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/email")
    public ResponseEntity<Void> deleteUser(@RequestParam String email) {
        userService.deleteUser(email);
        return ResponseEntity.noContent().build();
    }
}
