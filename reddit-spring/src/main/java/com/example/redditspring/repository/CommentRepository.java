package com.example.redditspring.repository;


import com.example.redditspring.model.Comment;
import com.example.redditspring.model.Post;
import com.example.redditspring.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}