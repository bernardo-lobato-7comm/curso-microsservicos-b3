package com.example.demo.domain.services;

import com.example.demo.domain.model.UserModel;
import com.example.demo.domain.ports.inbound.UserInbound;
import com.example.demo.domain.ports.outbound.UserOutbound;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService implements UserInbound {

    UserOutbound repository;
    public UserModel save(UserModel u) {
                return this.repository.save(u);
    }
    public boolean changePassword(UserModel u, String OldPassword, String newPassword) {
        return false;
    }
}
