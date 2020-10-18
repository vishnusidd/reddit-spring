package com.example.redditspring.repository;

import com.example.redditspring.model.Post;
import com.example.redditspring.model.User;
import com.example.redditspring.model.Vote;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}