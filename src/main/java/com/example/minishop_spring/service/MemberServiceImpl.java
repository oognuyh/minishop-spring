package com.example.minishop_spring.service;

import com.example.minishop_spring.exception.MemberDoesNotExistException;
import com.example.minishop_spring.exception.PasswordDoesNotMatchException;
import com.example.minishop_spring.model.Authority;
import com.example.minishop_spring.model.Member;
import com.example.minishop_spring.repository.MemberRepository;
import com.example.minishop_spring.util.CurrentMemberUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Service("memberService")
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void signUp(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setAuthority(Authority.MEMBER.getValue());

        memberRepository.save(member);
    }

    @Transactional
    @Override
    public void update(Member member) {
        Member existingMember = memberRepository.findById(member.getId()).orElseThrow(MemberDoesNotExistException::new);

        existingMember.setName(member.getName());
        existingMember.setPhoneNumber(member.getPhoneNumber());
        existingMember.setZip(member.getZip());
        existingMember.setAddress1(member.getAddress1());
        existingMember.setAddress2(member.getAddress2());
        existingMember.setEmail(member.getEmail());

        CurrentMemberUtil.update(memberRepository.save(existingMember));
    }

    @Transactional
    @Override
    public void changePassword(String existingPassword, String newPassword, Integer memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(MemberDoesNotExistException::new);

        if (passwordEncoder.matches(existingPassword, member.getPassword())) {
            member.setPassword(passwordEncoder.encode(newPassword));

            CurrentMemberUtil.update(memberRepository.save(member));
        } else {
            throw new PasswordDoesNotMatchException();
        }
    }

    @Override
    public boolean isDuplicateEmail(Member member, String email) {
        if (member.getEmail().equals(email)) return false;

        return memberRepository.existsByEmail(email);
    }

    @Override
    public boolean isDuplicatePhoneNumber(Member member, String phoneNumber) {
        if (member.getPhoneNumber().equals(phoneNumber)) return false;

        return memberRepository.existsByPhoneNumber(phoneNumber);
    }
}
