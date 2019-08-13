package com.demo.springbootrabbitmq.service.reflect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReflectServiceImpl implements ReflectService {

    @Autowired
    private SiteService siteService;

    @Override
    public String test() {
        return siteService.getName("world");
    }
}
