package com.example.minishop_spring.util;

import com.example.minishop_spring.model.Account;
import com.example.minishop_spring.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class CurrentMemberUtil {

    public static void update(Member member) {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         Account account = (Account) authentication.getPrincipal();

         account.setMember(member);
    }
}
