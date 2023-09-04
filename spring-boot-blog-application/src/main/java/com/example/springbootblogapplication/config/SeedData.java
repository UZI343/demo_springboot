package com.example.springbootblogapplication.config;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.springbootblogapplication.models.Account;
import com.example.springbootblogapplication.models.Post;
import com.example.springbootblogapplication.services.AccountService;
import com.example.springbootblogapplication.services.PostService;

@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    private PostService postService;

    @Autowired
    AccountService accountService;

    @Override
    public void run(String... args) throws Exception{
        List<Post>posts=postService.getAll();
        if(posts.size()==0){
            Account account1=new Account();
            account1.setFirstName("Michael");
            account1.setLastName("Jackson");
            account1.setEmail("michael@jackson.com");
            account1.setPassword("hehe");
            accountService.save(account1);
            Post post1=new Post();
            post1.setTitle("sample title ");
            post1.setBody("sample body");
            post1.setAccount(account1);
            postService.save(post1);
            
        }
    }
}
