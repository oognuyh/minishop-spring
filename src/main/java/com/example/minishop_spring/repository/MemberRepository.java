package com.example.minishop_spring.repository;

import com.example.minishop_spring.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByEmailAndPassword(String email, String password);
    Optional<Member> findByPhoneNumberAndPassword(String phoneNumber, String password);
}
