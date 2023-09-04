package com.example.springbootblogapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootblogapplication.models.Post;
@Repository
public interface Postrepository extends JpaRepository<Post,Long>{
    
}
