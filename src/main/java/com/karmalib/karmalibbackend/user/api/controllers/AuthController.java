package com.karmalib.karmalibbackend.user.api.controllers;

import com.karmalib.karmalibbackend.common.application.CommandResult;
import com.karmalib.karmalibbackend.common.presentation.JwtUtil;
import com.karmalib.karmalibbackend.common.presentation.RestService;
import com.karmalib.karmalibbackend.user.application.commands.CreateUserCommand;
import com.karmalib.karmalibbackend.user.application.services.UserCommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserCommandService userCommandService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserCommandService userCommandService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userCommandService = userCommandService;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.get("username"), request.get("password")
                )
        );

        String token = jwtUtil.generateToken(authentication.getName(), new HashMap<>());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody CreateUserCommand command) {
        CommandResult result = userCommandService.register(command);
        return RestService.buildResponse(result);
    }
}