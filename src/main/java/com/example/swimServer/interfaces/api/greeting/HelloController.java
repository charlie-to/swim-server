package com.example.swimServer.interfaces.api.greeting;

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

}
