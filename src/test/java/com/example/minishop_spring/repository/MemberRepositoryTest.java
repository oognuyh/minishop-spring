package com.example.minishop_spring.repository;

import com.example.minishop_spring.config.RootConfig;
import com.example.minishop_spring.config.WebConfig;
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
class MemberRepositoryTest {
    @Setter(onMethod_ = @Autowired)
    private MemberRepository memberRepository;

    @DisplayName("멤버 전체 목록")
    @Test
    public void testFindAll() {
        log.info("{}", memberRepository.findAll());
    }

    @DisplayName("멤버 등록")
    @Test
    public void save() {
        /*
        * .Member{zip=123, address2=123, address1=123, createdAt=2021-06-26T01:26:47.402080700, password=$2a$10$cJfz/0kOW7KClIetr1UseeT9xwv2xE6bopLUJdZVTlacrXXxh9yfC, carts=null, phoneNumber=00, authority=MEMBER, name=hg, orders=null, id=52, email=hg, updatedAt=2021-06-26T01:26:47.402080700}
01:26:47.440 [http-nio-9090-exec-10] DEBUG org.hibernate.SQL - insert into member (address1, address2, authority, created_at, email, name, password, phone_number, updated_at, zip, id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
Hibernate: insert into member (address1, address2, authority, created_at, email, name, password, phone_number, updated_at, zip, id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        * */

        Member member = memberRepository.save(Member.builder()
                .name("hg")
                .phoneNumber("00")
                .password("$2a$10$cJfz/0kOW7KClIetr1UseeT9xwv2xE6bopLUJdZVTlacrXXxh9yfC")
                .address1("123")
                .address2("123")
                .authority("MEMBER")
                .zip("123")
                .build());

        log.info("{}", member);
    }
}