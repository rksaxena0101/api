package com.leaderboard.api.repository;


import com.leaderboard.api.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByOrderByScoreDesc();

}
