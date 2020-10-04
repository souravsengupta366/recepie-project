package com.recepie.sourav.recepieproject.controller;

import com.recepie.sourav.recepieproject.Services.RecepieService;
import com.recepie.sourav.recepieproject.commands.RecepieCommand;
import com.recepie.sourav.recepieproject.domain.Recepie;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class RecepieController {

    private RecepieService recepieService;

    @RequestMapping("/recipe/{id}/show/")
//    @ResponseBody
    String showRecepie(@PathVariable String id, Model model){
        model.addAttribute("recepie",recepieService.findById(new Long(id)));
        return "recepie/show";
    }

    @RequestMapping("/recipe/add")
    public String recepieAdd(Model model){
        model.addAttribute("recipe",new RecepieCommand());
        return "recepie/recepie-form";
    }

    @RequestMapping("/recipe/{id}/update")
    public String recepieUpdate(@PathVariable String id, Model model){
        RecepieCommand recepieCommand = recepieService.findCommandById(new Long(id));
        Recepie recepie = recepieService.findById(new Long(id));
        model.addAttribute("recipe",recepieCommand);
        System.out.println("recepie command cook time"+recepieCommand.getCookTime());
        System.out.println("recepie cook time"+recepie.getCookTime());
        return "recepie/recepie-form";
    }

    @PostMapping("recepie")
    public String addUpdatePost(@ModelAttribute RecepieCommand recepieCommand){
        RecepieCommand savedRecepieCommand = recepieService.saveRecepieCommand(recepieCommand);
        System.out.println("inside post mapping");
        return "redirect:/recipe/"+savedRecepieCommand.getId()+"/show/";
    }

    @RequestMapping("/recipe/{id}/delete")
    public String recepieDelete(@PathVariable String id){
        recepieService.deleteById(new Long(id));
        return "redirect:/index";
    }

}
