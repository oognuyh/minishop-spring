package com.example.minishop_spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Authority {
    MEMBER("MEMBER"),
    ADMIN("ADMIN");

    private final String value;
}
