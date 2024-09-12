package com.example.swimServer.interfaces.api.greeting;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Validated
public class HelloController {

    @RequestMapping(path="/hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello, World!";
    }

    @SecurityRequirement(name = "bearerAuth")
    @RequestMapping(path="/secureHello", method = RequestMethod.GET)
    public String secureHello(@AuthenticationPrincipal Jwt jwt) {
        return "Hello, Secure World!";
    }

}
