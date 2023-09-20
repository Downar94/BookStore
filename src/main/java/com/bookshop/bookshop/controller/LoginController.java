package com.bookshop.bookshop.controller;

import com.bookshop.bookshop.payload.AuthenticationResponseDto;
import com.bookshop.bookshop.payload.LoginDto;
import com.bookshop.bookshop.service.LoginServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class LoginController {

    private LoginServiceInterface loginService;

    public LoginController(LoginServiceInterface loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody LoginDto loginDto){
        String jwtToken = loginService.login(loginDto);

        AuthenticationResponseDto authenticationResponse = new AuthenticationResponseDto();
        authenticationResponse.setTokenBody(jwtToken);

        return ResponseEntity.ok(authenticationResponse);
    }

}
