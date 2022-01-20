package com.sbrf.reboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @GetMapping("/user")
    public String getMessage(@RequestParam(value = "name", required = false, defaultValue = "user") String name, Model model) {
        model.addAttribute("name", name);
        return "user";
    }
}
