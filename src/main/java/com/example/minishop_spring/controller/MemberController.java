package com.example.minishop_spring.controller;

import com.example.minishop_spring.annotation.CurrentMember;
import com.example.minishop_spring.model.Member;
import com.example.minishop_spring.model.Response;
import com.example.minishop_spring.service.MemberService;
import com.example.minishop_spring.util.ObjectUtil;
import com.example.minishop_spring.util.ValidatorUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Controller
@AllArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @RequestMapping("/signin")
    public String showSignIn() {
        return "member/signin";
    }

    @GetMapping("/signup")
    public String showSignUp() {
        return "member/signup";
    }

    @RequestMapping("/auth/mypage")
    public ModelAndView showMyPage(@CurrentMember Member member) throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView("member/mypage");

        modelAndView.addObject("member", ObjectUtil.asString(member));

        return modelAndView;
    }

    @PostMapping("/auth/change-member-detail")
    public String update(@Validated(Member.DetailsValidator.class) Member member,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) throws JsonProcessingException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", ValidatorUtil.getErrorsAsString(bindingResult));
            return "redirect:/auth/mypage";
        }
        memberService.update(member);

        return "redirect:/auth/mypage";
    }

    @PostMapping("/auth/change-password")
    public String changePassword(@CurrentMember Member member,
                                 @RequestParam String existingPassword,
                                 @RequestParam String newPassword) {

        memberService.changePassword(existingPassword, newPassword, member.getId());

        return "redirect:/auth/mypage";
    }

    @PostMapping("/signup")
    public String signUp(@Valid Member member,
                         BindingResult bindingResult,
                         HttpServletRequest request) throws JsonProcessingException {
        log.info("{}", member);
        if (bindingResult.hasErrors()) {
            request.setAttribute("errors", ValidatorUtil.getErrorsAsString(bindingResult));
            return "member/signup";
        }

        memberService.signUp(member);

        return "redirect:/signin";
    }

    @GetMapping("/is-duplicate")
    @ResponseBody
    public Response<?> isDuplicate(@CurrentMember Member member,
                                   @RequestParam(required = false) String email,
                                   @RequestParam(required = false) String phoneNumber) {
        log.info("email: {} phoneNumber: {}", email, phoneNumber);

        boolean isDuplicate = email != null ?
                memberService.isDuplicateEmail(member, email) :
                memberService.isDuplicatePhoneNumber(member, phoneNumber);

        return isDuplicate ? Response.ERROR() : Response.OK();
    }
}
