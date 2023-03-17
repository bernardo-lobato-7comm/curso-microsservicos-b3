package com.example.demo.infrasctructure.configuration;

import com.example.demo.domain.services.UserService;
import com.example.demo.domain.ports.inbound.UserInbound;
import com.example.demo.infrasctructure.adapters.repositories.UserRepositoryImpl2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    UserInbound userService(final UserRepositoryImpl2 userRepository) {
        return new UserService(userRepository);
    }

}
