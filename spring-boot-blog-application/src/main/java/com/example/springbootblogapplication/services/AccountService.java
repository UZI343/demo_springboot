package com.example.springbootblogapplication.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor.OptimalPropertyAccessor;
import org.springframework.stereotype.Service;

import com.example.springbootblogapplication.models.Account;
import com.example.springbootblogapplication.repositories.AccountRepository;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;

    public Account save(Account account){
        return accountRepository.save(account);
    }
    public Optional<Account>findByEmail(String Email){
        return accountRepository.findOneByEmail(Email);
    }
}
