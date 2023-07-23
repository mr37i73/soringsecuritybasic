package com.eazybytes.soringsecuritybasic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {
    @GetMapping("/card")
    public String getCard(){
        return "Here are your card details";
    }
}
