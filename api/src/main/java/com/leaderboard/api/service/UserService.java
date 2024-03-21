package com.leaderboard.api.service;

import com.leaderboard.api.Badge;
import com.leaderboard.api.model.User;
import com.leaderboard.api.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) throws BadRequestException {
        try {
            validateUser(user);
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
        user.setBadges(new HashSet<>());
        return userRepository.save(user);
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: "+ userId));
    }

    public List<User> getAllUsers() {
        return userRepository.findAllByOrderByScoreDesc();
    }

    public User updateUserScore(String userId, int score) {
        User user = getUserById(userId);
        user.setScore(score);
        awardBadges(user);
        return userRepository.save(user);
    }

    private void awardBadges(User user) {
        user.setBadges(new HashSet<>()); // Reset badges before awarding new ones
        if (user.getScore() >= 1 && user.getScore() <= 30) {
            user.getBadges().add(Badge.CODE_NINJA);
        }
        if (user.getScore() >= 30 && user.getScore() <= 60) {
            user.getBadges().add(Badge.CODE_CHAMP);
        }
        if (user.getScore() >= 60) {
            user.getBadges().add(Badge.CODE_MASTER);
        }
    }
    public void validateUser(User user) throws BadRequestException {
        if(user.getUserId() == null || user.getUsername() == null) {
            throw new BadRequestException("userId and userName is required.");
        }
        if(user.getScore() < 0 || user.getScore() > 100) {
            throw new BadRequestException("Score must be between 0 and 100.");
        }
        if(user.getBadges() != null && user.getBadges().size() > 3) {
            throw new BadRequestException("User can have maximum 3 badges.");
        }

    }

}
