package com.VictorDaodu.SocialAPI.controller;

import com.VictorDaodu.SocialAPI.exception.ResourceNotFoundException;
import com.VictorDaodu.SocialAPI.model.User;
import com.VictorDaodu.SocialAPI.repository.UserRepository;
import com.VictorDaodu.SocialAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUser() {
        return userService.getUserRepository().findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        User user = userService.getUserRepository().findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not Found for this id ::" + userId));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/add/user")
    public User createUser(@RequestBody User user) {
        return userService.getUserRepository().save(user);
    }

    @PutMapping("/edit/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId, @RequestBody User userDetails)
            throws ResourceNotFoundException {
        User user = userService.getUserRepository().findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id:: " + userId));

        user.setEmail(userDetails.getEmail());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setLocation(userDetails.getLocation());
        final User updatedUser = userService.getUserRepository().save(user);

        return ResponseEntity.ok(updatedUser);
    }
    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        User user = userService.getUserRepository().findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        userService.getUserRepository().delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}