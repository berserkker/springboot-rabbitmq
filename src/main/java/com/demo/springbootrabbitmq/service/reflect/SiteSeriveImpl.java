package com.demo.springbootrabbitmq.service.reflect;

import org.springframework.stereotype.Service;

@Service
public class SiteSeriveImpl implements SiteService {
    @Override
    public String getName(String name) {
        return "hello " + name;
    }
}
