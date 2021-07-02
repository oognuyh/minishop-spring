package com.example.minishop_spring.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

public class ValidatorUtil {

    public static String getErrorsAsString(BindingResult bindingResult) throws JsonProcessingException {
        Map<String, String> errors = new HashMap<>();

        bindingResult.getFieldErrors().forEach(fieldError ->
                errors.put(fieldError.getField(), fieldError.getDefaultMessage()));

        return ObjectUtil.asString(errors);
    }

    public static Map<String, String> getErrorsAsObject(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();

        bindingResult.getFieldErrors().forEach(fieldError ->
                errors.put(fieldError.getField(), fieldError.getDefaultMessage()));

        return errors;
    }
}
