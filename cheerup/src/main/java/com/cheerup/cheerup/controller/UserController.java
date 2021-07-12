package com.cheerup.cheerup.controller;

import com.cheerup.cheerup.dto.SignupRequestDto;
import com.cheerup.cheerup.security.UserDetailsImpl;
import com.cheerup.cheerup.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public Map<String, String> registerUser(@Valid @RequestBody SignupRequestDto requestDto, Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();
        if (errors.hasErrors()) {
            return userService.validateHandling(errors);
        }
        String error = userService.registerUser(requestDto);
        if (error.equals("")) {
            validatorResult.put("msg", "회원가입 완료");
            return validatorResult;
        } else {
            validatorResult.put("errors", error);
            return validatorResult;
        }
    }

    @GetMapping("/user/session")
    public Map<String, String> signup(HttpServletRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Map<String, String> session = new HashMap<>();
        String username = "null";
        String role = "null";
        Optional<UserDetailsImpl> userDetailsOptional = Optional.ofNullable(userDetails);
        if (userDetailsOptional.isPresent()) {
            username = userDetails.getUsername();
            role = String.valueOf(userDetails.getRole());
        }
        request.getSession().setAttribute("username", username);
        request.getSession().setAttribute("role", role);
        request.getSession().setMaxInactiveInterval(360*60);
        session.put("username", String.valueOf(request.getSession().getAttribute("username")));
        session.put("role", String.valueOf(request.getSession().getAttribute("role")));
        return session;
    }

    @GetMapping("/user/kakao/callback")
    public void kakaoLogin(String code) {
        // authorizedCode: 카카오 서버로부터 받은 인가 코드
        userService.kakaoLogin(code);
    }
}