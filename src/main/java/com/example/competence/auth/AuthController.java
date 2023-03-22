package com.example.competence.auth;

import com.example.competence.Entity.Personne;
import com.example.competence.auth.dto.RegisterRequestDto;
import com.example.competence.auth.dto.RequestLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("register")
    public Personne register(@RequestBody RegisterRequestDto dto){
        return service.register(dto);
    }

    @PostMapping("login")
    public String login(@RequestBody RequestLoginDto loginDto){
        try{
            manager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsername(),
                            loginDto.getPassword()
                    )
            );
        } catch (BadCredentialsException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return jwtTokenUtil.generateToken(loginDto.getUsername(), Map.of());
    }
}