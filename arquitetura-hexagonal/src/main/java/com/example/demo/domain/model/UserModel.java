package com.example.demo.domain.model;

import lombok.Data;

import java.util.UUID;

@Data
public class UserModel {
    public UUID id;
    public String name;
    public String email;
    public String password;
}
