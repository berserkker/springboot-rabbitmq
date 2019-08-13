package com.demo.springbootrabbitmq.service;

import com.demo.springbootrabbitmq.entity.User;

import java.util.List;

public interface UserService {
    boolean create(User user);

    boolean batchInsert(List<User> user);
}
