package com.example.demo.infrasctructure.adapters.entities;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users", schema = "curso")
@Builder
@Getter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;
    public String name;
    public String email;
    public String password;

}
