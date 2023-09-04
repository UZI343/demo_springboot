package com.example.springbootblogapplication.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootblogapplication.models.Post;
import com.example.springbootblogapplication.repositories.Postrepository;

@Service
public class PostService {
    @Autowired
    private Postrepository postrepository;
    
    public Optional<Post>getById(Long id){
        return postrepository.findById(id);
    }
    public List<Post>getAll(){
        return postrepository.findAll();
    }
    public Post save(Post post){
        if(post.getId()==null){
            post.setCreatedAt(LocalDateTime.now());
        }
        return postrepository.save(post);
    }
}
