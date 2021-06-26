package com.example.minishop_spring.service;

import com.example.minishop_spring.config.RootConfig;
import com.example.minishop_spring.config.WebConfig;
import com.example.minishop_spring.model.Authority;
import com.example.minishop_spring.model.Member;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { RootConfig.class, WebConfig.class })
class MemberServiceTest {
    @Setter(onMethod_ = @Autowired)
    private MemberService memberService;

    @DisplayName("회원가입")
    @Test
    public void signUp() {
        log.info("{}", memberService.signUp(Member.builder()
                            .name("hg")
                            .phoneNumber("0000")
                            .email("hg@hg.hg")
                            .password("123")
                            .authority(Authority.MEMBER.getValue())
                            .build()));
    }
}