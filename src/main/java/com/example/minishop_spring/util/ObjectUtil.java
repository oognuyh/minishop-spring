package com.example.minishop_spring.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String asString(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}