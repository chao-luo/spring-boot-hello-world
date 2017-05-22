package com.chaoluo.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@EnableAutoConfiguration
//@Import({ com.WebSecurityConfig.class })
@EnableWebSecurity
@ComponentScan("com")
public class Application {

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/foo")
    String foo() {
        return "Foo, Hello World!";
    }

    @RequestMapping("/user")
    Principal getPrincipal(Principal user) {
        return user;
    }

    @RequestMapping("/authentication")
    Authentication getAuthentication(Authentication authentication) {
        return authentication;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}