package com.docker.test3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    
    
    @GetMapping("/a")
    public String name() {
        return "aaaa";
    }

}
