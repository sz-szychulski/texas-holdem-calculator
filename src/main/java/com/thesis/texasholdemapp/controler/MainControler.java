package com.thesis.texasholdemapp.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainControler {

    @GetMapping("/")
    public String welcome(Model model) {
        return "index";
    }
}
