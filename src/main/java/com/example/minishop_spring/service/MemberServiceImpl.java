package com.example.minishop_spring.service;

import com.example.minishop_spring.model.Member;
import com.example.minishop_spring.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
@Transactional
@AllArgsConstructor
@Service("memberService")
public
class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public Optional<Member> signIn(String id, String password) {
        boolean isPhoneNumber = id.chars().allMatch(Character::isDigit);

        return isPhoneNumber ?
                memberRepository.findByPhoneNumberAndPassword(id, password) :
                memberRepository.findByEmailAndPassword(id, password);
    }

    @Transactional
    @Override
    public boolean signUp(Member member, HttpSession session) {
        try {
            session.setAttribute("memberId", memberRepository.save(member).getId());

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    @Override
    public Member update(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Optional<Member> fetchById(Integer id) {
        return memberRepository.findById(id);
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
