package com.demo.springbootrabbitmq.service;

import com.demo.springbootrabbitmq.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean create(User user) {
        return jdbcTemplate.update("insert into user(name,email,age)values(?,?,?)",
                user.getName(), user.getEmail(), user.getAge()) > 0;
    }
}
