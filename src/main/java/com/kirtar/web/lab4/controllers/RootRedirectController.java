package com.kirtar.web.lab4.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/main", "/auth", "/auth/logout"})
public class RootRedirectController {

    @GetMapping()
    public String toMain() {
        return "forward:/";
    }

}