package com.example.springbootblogapplication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
@Entity
@Getter
@Setter
@Table(name="Accounts")
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;
    
    private String email;

    private String password;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "account")
    private List<Post>posts;
}
