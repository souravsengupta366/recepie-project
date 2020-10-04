package com.recepie.sourav.recepieproject.controller;

import com.recepie.sourav.recepieproject.Services.IngredientsService;
import com.recepie.sourav.recepieproject.Services.RecepieService;
import com.recepie.sourav.recepieproject.Services.UnitOfMeasureService;
import com.recepie.sourav.recepieproject.commands.IngredientsCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IngredientsController {
    private final RecepieService recepieService;
    private final IngredientsService ingredientsService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientsController(RecepieService recepieService, IngredientsService ingredientsService, UnitOfMeasureService unitOfMeasureService) {
        this.recepieService = recepieService;
        this.ingredientsService = ingredientsService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @RequestMapping("/recipe/{id}/ingredients")
    public String viewIngrediens(@PathVariable String id, Model model){
        model.addAttribute("recipe",recepieService.findById(new Long(id)));
        System.out.println(recepieService.findById(new Long(id)).getIngredients().size());
        return "recepie/ingredients/list";
    }

    @PutMapping("/recipe/{recipeId}/ingredients")
    public String addUpdate(@ModelAttribute IngredientsCommand ingredientsCommand){
        if(ingredientsCommand == null)
           System.out.println("inside addUpdate and ingridientsCommand is null");
        System.out.println("Ingredient command's recepie id:"+ingredientsCommand.getRecepieId());
        ingredientsService.saveIngredientsCommand(ingredientsCommand);
        return "redirect:/recipe/{id}/ingredients";
    }

    @RequestMapping("/recipe/{recipeId}/ingredients/{id}/show")
    public String showIngredients(@PathVariable String recipeId,
                                  @PathVariable String id,
                                  Model model){

        IngredientsCommand ingredientsCommand = recepieService
                .findCommandById(new Long(recipeId))
                .getIngredients()
                .stream().filter(ingCommand -> ingCommand
                        .getId() == new Long(id)).findFirst().get();
        model.addAttribute("ingredient",ingredientsCommand);
        return "recepie/ingredients/show";
    }

    @RequestMapping("/recipe/{recipeId}/ingredients/{id}/delete")
    public String deleteIngredients(@PathVariable String recipeId,
                                  @PathVariable String id,
                                  Model model){
        ingredientsService.deleteById(new Long(recipeId),new Long(id));
//        model.addAttribute("recipe",recepie);
        return "redirect:/recipe/{recipeId}/ingredients";
    }
    @RequestMapping("/recipe/{recipeId}/ingredients/{id}/update")
    public String update(@PathVariable String recipeId,
                            @PathVariable String id,
                            Model model){
        IngredientsCommand ingredientsCommand = ingredientsService.getIngredientsCommandById(new Long(recipeId), new Long(id));
        model.addAttribute("ingredient",ingredientsCommand);
        model.addAttribute("uomList",unitOfMeasureService.findAll());
        return "recepie/ingredients/add_update";
    }


}
