package com.example.minishop_spring.service;

import com.example.minishop_spring.model.Member;

import java.util.Optional;

public interface MemberService {

    Optional<Member> signIn(String email, String password);
    Member signUp(Member member);
    Member update(Member member);
    boolean isDuplicateEmail(String email);
    boolean isDuplicatePhoneNumber(String phoneNumber);
}
