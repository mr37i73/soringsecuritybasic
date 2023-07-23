package com.eazybytes.soringsecuritybasic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/account")
    public String accountDetais() {
        return "Here are the accuont details";
    }
}
