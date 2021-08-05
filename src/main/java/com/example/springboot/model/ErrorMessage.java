package com.example.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class ErrorMessage {

    private final String code;
    private final Date timestamp;
    private final String message;
}
