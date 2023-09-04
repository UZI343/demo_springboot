package com.example.springbootblogapplication.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springbootblogapplication.models.Account;
import com.example.springbootblogapplication.models.Post;
import com.example.springbootblogapplication.services.AccountService;
import com.example.springbootblogapplication.services.PostService;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id, Model model){
        Optional<Post> optionalPost=postService.getById(id);
        if(optionalPost.isPresent()){
            Post post=optionalPost.get();
            model.addAttribute("post", post);
            return "post";
        }
        else{
            return "404";
        }

    }
    @GetMapping("/posts/new")
    public String createNewPost(Model model){
        Optional<Account>optionalAccount=accountService.findByEmail("ying@yang");
        if(optionalAccount.isPresent()){
            Post post=new Post();
            post.setAccount(optionalAccount.get());
            model.addAttribute("post", post);
            return "post_new";
        }
        else{
            return "404";
        }
        
    }
    @PostMapping("/posts/new")
    public String saveNewPost(@ModelAttribute Post post){
        postService.save(post);
        return "redirect:/posts/"+post.getId();
    }
}