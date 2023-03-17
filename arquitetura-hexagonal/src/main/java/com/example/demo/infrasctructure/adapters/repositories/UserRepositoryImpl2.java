package com.example.demo.infrasctructure.adapters.repositories;

import com.example.demo.domain.model.UserModel;
import com.example.demo.domain.ports.outbound.UserOutbound;
import com.example.demo.infrasctructure.adapters.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl2 implements UserOutbound {
    @Autowired
    UserRepository repository;
    @Override
    public UserModel save(UserModel u) {
        UserEntity entity = UserEntity.builder()
                .id(u.getId())
                .email(u.getEmail())
                .name(u.getName())
                .password(u.getPassword())
                .build();

        entity = this.repository.save(entity);
        UserModel ret = new UserModel();
        ret.setId(entity.getId());
        ret.setName(entity.getName());
        ret.setEmail(entity.getEmail());
        ret.setPassword(entity.getPassword());

        return ret;
    }
}
