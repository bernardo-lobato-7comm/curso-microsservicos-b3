package com.example.demo.domain.ports.outbound;

import com.example.demo.domain.model.UserModel;

public interface UserOutbound {
    UserModel save(UserModel u);
}
