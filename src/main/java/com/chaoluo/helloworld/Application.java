package com.chaoluo.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class Application {

    @RequestMapping(value = "/resources", method = RequestMethod.GET)
    @ResponseBody
    String getResources() {
        return "Get Resources";
    }

    @RequestMapping(value = "/resources", method = RequestMethod.POST, consumes = {"application/json"})
    @ResponseBody
    String postResources() {
        return "Post Resources!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}