package com.basespringboot.service;

import com.basespringboot.dto.LoginDto;
import com.basespringboot.dto.RegisterDto;
import com.basespringboot.dto.TokenResponse;
import com.basespringboot.dto.UserResponse;
import com.basespringboot.entity.AppUser;
import com.basespringboot.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserResponse register(RegisterDto dto) {
        if (appUserRepository.existsByUsername(dto.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nom d'utilisateur déjà pris");
        }
        AppUser user = new AppUser();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        AppUser saved = appUserRepository.save(user);
        return new UserResponse(saved.getId(), saved.getUsername());
    }

    public TokenResponse login(LoginDto dto) {
        AppUser user = appUserRepository.findByUsername(dto.getUsername())
                .filter(u -> passwordEncoder.matches(dto.getPassword(), u.getPassword()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiants invalides"));
        return new TokenResponse(jwtService.generateToken(user.getUsername()));
    }
}