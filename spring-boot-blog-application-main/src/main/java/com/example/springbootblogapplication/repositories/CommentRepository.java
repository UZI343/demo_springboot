package com.example.springbootblogapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbootblogapplication.models.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    
}
