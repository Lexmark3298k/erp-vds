package com.vdsolutions.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("titulo", "VD Solutions ERP");
        model.addAttribute("mensaje", "Sistema de Gestión Integral");
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }
}