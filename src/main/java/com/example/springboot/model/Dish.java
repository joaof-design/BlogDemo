package com.example.springboot.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Dish {

    private String name;
    private Integer calories;
    private String type;
}
