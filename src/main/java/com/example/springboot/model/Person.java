package com.example.springboot.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {

    private String name;
    private Integer age;
}
