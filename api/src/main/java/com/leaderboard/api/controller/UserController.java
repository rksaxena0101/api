package com.leaderboard.api.controller;

import com.leaderboard.api.model.User;
import com.leaderboard.api.service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) throws BadRequestException {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping("/{userId}/score")
    public ResponseEntity<User> updateUserScore(@PathVariable String userId, @RequestParam int score) {
        return ResponseEntity.ok(userService.updateUserScore(userId, score));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }


}
