package com.recepie.sourav.recepieproject.controller;

import com.recepie.sourav.recepieproject.repositories.CategoryRepository;
import com.recepie.sourav.recepieproject.repositories.RecepieRepository;
import com.recepie.sourav.recepieproject.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecepieRepository recepieRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecepieRepository recepieRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recepieRepository = recepieRepository;
    }


    @GetMapping({"/","index","index.html"})
    public String index(Model model){
       model.addAttribute("recipes",recepieRepository.findAll());
       return "index";
    }
}
