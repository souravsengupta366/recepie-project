package com.recepie.sourav.recepieproject.controller;

import com.recepie.sourav.recepieproject.Services.RecepieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final RecepieService recepieService;

    public IndexController( RecepieService recepieService) {
        this.recepieService = recepieService;
    }


    @GetMapping({"/","index","index.html"})
    public String index(Model model){
       model.addAttribute("recipes", recepieService.findAll());
       return "index";
    }
}
