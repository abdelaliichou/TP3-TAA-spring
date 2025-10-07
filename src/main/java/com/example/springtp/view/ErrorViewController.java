package com.example.springtp.view;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorViewController {

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "accessDenied";
    }
}
