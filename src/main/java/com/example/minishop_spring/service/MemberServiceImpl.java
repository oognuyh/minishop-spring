package com.example.minishop_spring.service;

import com.example.minishop_spring.model.Member;
import com.example.minishop_spring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Optional<Member> signIn(String id, String password) {
        boolean isPhoneNumber = id.chars().allMatch(Character::isDigit);

        return isPhoneNumber ?
                memberRepository.findByPhoneNumberAndPassword(id, password) :
                memberRepository.findByEmailAndPassword(id, password);
    }

    @Transactional
    @Override
    public Member signUp(Member member) {
        return memberRepository.save(member);
    }

    @Transactional
    @Override
    public Member update(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public boolean isDuplicateEmail(String email) {
        return memberRepository.countByEmail(email) > 0;
    }

    @Override
    public boolean isDuplicatePhoneNumber(String phoneNumber) {
        return memberRepository.countByPhoneNumber(phoneNumber) > 0;
    }
}
