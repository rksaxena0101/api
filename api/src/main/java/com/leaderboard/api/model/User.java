package com.leaderboard.api.model;

import com.leaderboard.api.Badge;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "user")
public class User {

    @Id
    private String userId;
    private String username;
    private int score;
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<Badge> badges;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Set<Badge> getBadges() {
        return badges;
    }

    public void setBadges(Set<Badge> badges) {
        this.badges = badges;
    }

    public User(String userId, String username) {
        this.userId = userId;
        this.username = username;
        this.score = 0;
        this.badges = new HashSet<>();
    }


}
