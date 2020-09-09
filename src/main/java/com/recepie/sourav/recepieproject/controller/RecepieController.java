package com.recepie.sourav.recepieproject.controller;

import com.recepie.sourav.recepieproject.Services.RecepieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class RecepieController {

    private RecepieService recepieService;

    @RequestMapping("/recipe/show/{id}")
//    @ResponseBody
    String showRecepie(@PathVariable String id, Model model){
        model.addAttribute("recepie",recepieService.findById(new Long(id)));
        return "recepie/show";
    }


}
