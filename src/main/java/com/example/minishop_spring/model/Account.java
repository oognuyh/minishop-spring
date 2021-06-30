package com.example.minishop_spring.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Setter
@Getter
public class Account extends User {
    private Member member;

    public Account(Member member) {
        super(member.getEmail(), member.getPassword(), List.of(new SimpleGrantedAuthority(member.getAuthority())));
        this.member = member;
    }
}
