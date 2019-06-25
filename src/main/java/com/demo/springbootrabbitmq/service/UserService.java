package com.demo.springbootrabbitmq.service;

import com.demo.springbootrabbitmq.entity.User;

public interface UserService {
    boolean create(User user);
}
