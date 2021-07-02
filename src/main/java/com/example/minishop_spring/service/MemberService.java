package com.example.minishop_spring.service;

import com.example.minishop_spring.model.Member;

public interface MemberService {

    void signUp(Member member);
    void update(Member member);
    void changePassword(String existingPassword, String newPassword, Integer memberId);
    boolean isDuplicateEmail(Member member, String email);
    boolean isDuplicatePhoneNumber(Member member, String phoneNumber);
}
