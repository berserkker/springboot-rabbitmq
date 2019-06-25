package com.demo.springbootrabbitmq.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class User implements Serializable {
    private static final long serialVersionUID=1L;

    private int id;
    private String name;
    private String email;
    private Integer age;
}
