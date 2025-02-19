package com.eazybytes.eazyschool.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homeController {
    @RequestMapping(value = {"", "/", "/home"})
    public String displayHomePage(Model model) {
        return "home.html";
    }

}
