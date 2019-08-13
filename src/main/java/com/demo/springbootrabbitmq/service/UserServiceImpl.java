package com.demo.springbootrabbitmq.service;

import com.demo.springbootrabbitmq.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean create(User user) {
        return jdbcTemplate.update("insert into user(name,email,age)values(?,?,?)",
                user.getName(), user.getEmail(), user.getAge()) > 0;
    }

    @Override
    public boolean batchInsert(List<User> user) {
//        for (int i = 1; i < 11; i++) {
            StringBuilder sb = new StringBuilder("insert into user (id,name,age,email) values");
            for (int j = 1; j < 1000; j++) {
                sb.append(" (" + ( j) + ",\"test" + ( j) + "\",11,\"test@qq.com\"),");
            }
            System.out.println(sb.toString().substring(0, sb.toString().length() - 1));
            jdbcTemplate.execute(sb.toString().substring(0, sb.toString().length() - 1));
//        }
        return false;
    }
}
