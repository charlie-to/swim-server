package com.example.swimServer.interfaces.api.record;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/record")
@Validated
public class RecordController {

    @GetMapping(produces = "application/json")
    public String record() {
        return "{\"message\":\"Hello, World!\"}";
    }
}
