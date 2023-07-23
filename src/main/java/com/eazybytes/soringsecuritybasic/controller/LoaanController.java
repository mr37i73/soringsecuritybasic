package com.eazybytes.soringsecuritybasic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoaanController {
    @GetMapping("/loan")
    public String getLoan() {
        return "Here is your loan details";
    }

}
