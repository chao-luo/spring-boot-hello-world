package com.chaoluo.helloworld;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
//@Import({ com.WebSecurityConfig.class })
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

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}