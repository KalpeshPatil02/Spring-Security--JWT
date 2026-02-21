package com.product.Product.controller;

import com.product.Product.entity.Role;
import com.product.Product.entity.User;
import com.product.Product.repository.UsersRepository;
import com.product.Product.utility.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
        return "User Registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        return jwtUtil.generateToken(user.getUsername());
    }

    @PostMapping("/register-admin")
    public String registerAdmin(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_ADMIN);
        userRepository.save(user);
        return "Admin Registered";
    }

}
