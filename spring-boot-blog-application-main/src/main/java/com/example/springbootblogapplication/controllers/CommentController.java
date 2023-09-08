package com.example.springbootblogapplication.controllers;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springbootblogapplication.models.Account;
import com.example.springbootblogapplication.models.Comment;
import com.example.springbootblogapplication.models.Post;
import com.example.springbootblogapplication.services.AccountService;
import com.example.springbootblogapplication.services.CommentService;
import com.example.springbootblogapplication.services.PostService;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;
    private final AccountService accountService;
    @GetMapping("/comments/{id}") // add a new comment to post id
    @PreAuthorize("isAuthenticated()")
    public String getComment(@PathVariable Long id,Model model,Principal principal){
        Optional<Post> optionalPost = this.postService.getById(id);
        String authUsername = "anonymousUser";
        if (principal != null){
            authUsername = principal.getName();
        }
        
        Optional<Account> optionalAccount = accountService.findOneByEmail(authUsername);
        // if post exists put it in model
        if (optionalPost.isPresent() && optionalAccount.isPresent()) {
            Post post = optionalPost.get();
            Comment comment=new Comment();
            comment.setPost(post);
            comment.setAccount(optionalAccount.get());
            model.addAttribute("comment", comment);
            model.addAttribute("post", optionalPost.get());
            return "comment";
        } else {
            return "404";
        }
    }
    @PostMapping("/comments/{id}") // post a new comment to post id
    @PreAuthorize("isAuthenticated()")
    public String addcomment(@PathVariable Long id,Principal principal, @ModelAttribute Comment comment,@ModelAttribute Post post) {
        // return "redirect:/";
        Random rand=new Random();
        comment.setPost(post);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setId((long) rand.nextInt(1<<30));
        String authUsername = "anonymousUser";
        if (principal != null){
            authUsername = principal.getName();
        }
        Optional<Account> optionalAccount = accountService.findOneByEmail(authUsername);
        if(optionalAccount.isPresent()){
            comment.setAccount(optionalAccount.get());
        }
        else{
            return "404";
        }
        commentService.save(comment);
        
        
        return "redirect:/";
    }
    
}
