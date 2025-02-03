package com.matchmaker.user_mgt.service;

import com.matchmaker.user_mgt.model.User;
import com.matchmaker.user_mgt.model.dto.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

public interface UserService {

    public User convertToEntity(UserRequest userRequest);
    public User createUser(UserRequest userRequest);
    public List<User> getAllUsers();
    public Optional<User> getUserById(Long id);
    public ResponseEntity<Void> deleteUser(String email);
}
