package com.example.demo.domain.ports.inbound;

import com.example.demo.domain.model.UserModel;

public interface UserInbound {
    UserModel save(UserModel u);

    boolean changePassword(UserModel u, String OldPassword, String newPassword);
}
