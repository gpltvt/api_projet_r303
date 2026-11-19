package com.basespringboot.controller;

import com.basespringboot.dto.LoginDto;
import com.basespringboot.dto.RegisterDto;
import com.basespringboot.dto.TokenResponse;
import com.basespringboot.dto.UserResponse;
import com.basespringboot.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@Valid @RequestBody RegisterDto dto) {
        return authService.register(dto);
    }

    @PostMapping("/login")
    public TokenResponse login(@Valid @RequestBody LoginDto dto) {
        return authService.login(dto);
    }
}
