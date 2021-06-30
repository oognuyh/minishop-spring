package com.example.minishop_spring.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@SuppressWarnings("unchecked")
public class Response<T> {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime transactionTime;
    private boolean isOk;
    private T data;

    public static <T> Response<T> OK() {
        return (Response<T>) Response.builder()
                .transactionTime(LocalDateTime.now())
                .isOk(true)
                .build();
    }

    public static <T> Response<T> OK(T data) {
        return (Response<T>) Response.builder()
                .transactionTime(LocalDateTime.now())
                .isOk(true)
                .data(data)
                .build();
    }

    public static <T> Response<T> ERROR() {
        return (Response<T>) Response.builder()
                .transactionTime(LocalDateTime.now())
                .isOk(false)
                .build();
    }

    public static <T> Response<T> ERROR(T data) {
        return (Response<T>) Response.builder()
                .transactionTime(LocalDateTime.now())
                .isOk(false)
                .data(data)
                .build();
    }
}
