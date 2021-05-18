package com.example.minishop_spring.controller;

import com.example.minishop_spring.model.Member;
import com.example.minishop_spring.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Controller
@AllArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/signin")
    public String showSignIn() {
        return "member/signin";
    }

    @GetMapping("/signup")
    public String showSignUp() {
        return "member/signup";
    }

    @GetMapping("/auth/mypage")
    public ModelAndView showMyPage(HttpSession session) throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView();
        Member member = (Member) session.getAttribute("member");

        modelAndView.setViewName("member/mypage");
        modelAndView.addObject("member", new ObjectMapper().writeValueAsString(member));

        return modelAndView;
    }

    @PostMapping("/signin")
    public String signIn(@RequestParam String id,
                         @RequestParam String password,
                         HttpSession session) {

        Optional<Member> member = memberService.signIn(id, password);
        if (member.isPresent()) {
            session.setAttribute("member", member.get());
            return "redirect: /";
        } else {
            return "redirect: /signin";
        }
    }

    @PostMapping("/signup")
    @ResponseBody
    public String signUp(@RequestBody Member member, HttpSession session) {

        log.info("{}", member);

        return memberService.signUp(member, session) ? "success" : "failure";
    }

    @GetMapping("/isDuplicate")
    @ResponseBody
    public String isDuplicate(@RequestParam(required = false) String email, @RequestParam(required = false) String phoneNumber) {

        log.info("email: {} phoneNumber: {}", email, phoneNumber);

        boolean isDuplicate = email != null ?
                memberService.isDuplicateEmail(email) :
                memberService.isDuplicatePhoneNumber(phoneNumber);

        return isDuplicate ? "duplicate" : "unique";
    }
}
