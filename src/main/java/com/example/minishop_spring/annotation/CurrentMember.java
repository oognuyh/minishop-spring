package com.example.minishop_spring.annotation;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

@Documented
@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member")
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER })
public @interface CurrentMember {
}
