package com.example.minishop_spring.service;

import com.example.minishop_spring.model.Member;

public interface MemberService {

    Member signUp(Member member);
    void update(Member member);
    void changePassword(String existingPassword, String newPassword, Integer memberId);
    Member findById(Integer id);
    boolean isDuplicateEmail(String email);
    boolean isDuplicatePhoneNumber(String phoneNumber);
}
