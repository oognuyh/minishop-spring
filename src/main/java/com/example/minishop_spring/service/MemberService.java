package com.example.minishop_spring.service;

import com.example.minishop_spring.model.Member;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public interface MemberService {

    Optional<Member> signIn(String id, String password);
    boolean signUp(Member member, HttpSession session);
    Member update(Member member);
    Optional<Member> fetchById(Integer id);
    boolean isDuplicateEmail(String email);
    boolean isDuplicatePhoneNumber(String phoneNumber);
}
