package com.rented.demouasfadillah.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BerandaController {
    @GetMapping("/beranda-awal")
    public String beranda(Model model){
        return "beranda";

    }
    @GetMapping("/contact")
    public String contact(Model model){
        return "contact-beranda";
    }
}
