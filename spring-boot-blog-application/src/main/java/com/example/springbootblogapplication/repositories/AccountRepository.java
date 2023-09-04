package com.example.springbootblogapplication.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.springbootblogapplication.models.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account,Long>{
    Optional<Account>findOneByEmail(String Email);
}
