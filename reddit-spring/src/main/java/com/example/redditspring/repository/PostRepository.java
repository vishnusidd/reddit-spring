package com.example.redditspring.repository;

import com.example.redditspring.model.Post;
import com.example.redditspring.model.Subreddit;
import com.example.redditspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);
}