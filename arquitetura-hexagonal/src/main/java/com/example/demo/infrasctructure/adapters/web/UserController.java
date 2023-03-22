package com.example.demo.infrasctructure.adapters.web;


import com.example.demo.domain.model.UserModel;
import com.example.demo.domain.ports.inbound.UserInbound;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserInbound userInbound;

    public UserController(UserInbound userInbound) {
        this.userInbound = userInbound;
    }

    @PostMapping
    void novo(@RequestBody UserModel um) {
        userInbound.save(um);
    }
}
