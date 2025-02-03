package com.matchmaker.user_mgt.service;

import com.matchmaker.user_mgt.model.Role;
import com.matchmaker.user_mgt.model.User;
import com.matchmaker.user_mgt.model.dto.UserRequest;
import com.matchmaker.user_mgt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User convertToEntity(UserRequest userRequest) {
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setGender(userRequest.getGender());
        user.setFullName(userRequest.getFullName());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(Role.USER);
        return user;
    }

    @Override
    public User createUser(UserRequest userRequest) {
        User user = convertToEntity(userRequest);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public ResponseEntity<Void> deleteUser(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            userRepository.deleteById(userOptional.get().getUserId());
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}
