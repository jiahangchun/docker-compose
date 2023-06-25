package com.docker.controller;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : JIA
 */
@RestController
public class TestController {

    @GetMapping("/a")
    public String a(){
        return "aaaaa";
    }
}
