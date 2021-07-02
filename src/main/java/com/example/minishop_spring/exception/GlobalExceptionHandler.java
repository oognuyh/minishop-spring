package com.example.minishop_spring.exception;

import com.example.minishop_spring.model.Response;
import com.example.minishop_spring.util.ObjectUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNoHandlerFoundException() {
        return "error/404";
    }

    @ExceptionHandler(ProductDoesNotExistException.class)
    public String handleProductDoesNotExistException() {
        return "error/404";
    }

    @ExceptionHandler(PasswordDoesNotMatchException.class)
    public String handlePasswordDoesNotMatchException(RedirectAttributes redirectAttributes) throws JsonProcessingException {
        Map<String, String> errors = new HashMap<>();

        errors.put("password", "비밀번호가 올바르지 않습니다.");
        redirectAttributes.addFlashAttribute("errors", ObjectUtil.asString(errors));

        return "redirect:/auth/mypage";
    }

    @ExceptionHandler(MemberDoesNotExistException.class)
    public String handleMemberDoesNotExistException() {
        return "redirect:/signin";
    }

    @ExceptionHandler(CanNotCheckOutWithoutProductsException.class)
    @ResponseBody
    public Response<?> handleCanNotCheckOutWithoutProductsException() throws JsonProcessingException {
        Map<String, String> errors = new HashMap<>();

        errors.put("message", "상품 없이 주문할 수 없습니다.");
        return Response.ERROR(ObjectUtil.asString(errors));
    }

    @ExceptionHandler(Exception.class)
    public String handleInternalServerError() {
        return "error/500";
    }
}
