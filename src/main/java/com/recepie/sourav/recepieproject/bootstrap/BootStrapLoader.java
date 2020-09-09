package com.recepie.sourav.recepieproject.bootstrap;

import com.recepie.sourav.recepieproject.domain.*;
import com.recepie.sourav.recepieproject.repositories.CategoryRepository;
import com.recepie.sourav.recepieproject.repositories.RecepieRepository;
import com.recepie.sourav.recepieproject.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class BootStrapLoader implements CommandLineRunner {

    private final UnitOfMeasureRepository uomRepo ;
    private final CategoryRepository categoryRepository;
    private final RecepieRepository recepieRepository;

    public BootStrapLoader(UnitOfMeasureRepository uomRepo, CategoryRepository categoryRepository,
                           RecepieRepository recepieRepository) {
        this.uomRepo = uomRepo;
        this.categoryRepository = categoryRepository;
        this.recepieRepository = recepieRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        Optional<UnitOfMeasure> opTeaspoon = uomRepo.findByDescription("Teaspoon");
        Optional<UnitOfMeasure> opTablespoon = uomRepo.findByDescription("Tablespoon");
        Optional<UnitOfMeasure> opCup = uomRepo.findByDescription("Cup");
        Optional<UnitOfMeasure> opPinch = uomRepo.findByDescription("Pinch");
        Optional<UnitOfMeasure> opEach = uomRepo.findByDescription("Each");
        Optional<UnitOfMeasure> opOunce = uomRepo.findByDescription("Ounce");

        Optional<Category> opAmerican = categoryRepository.findByDescription("American");
        Optional<Category> opItalian = categoryRepository.findByDescription("Italian");
        Optional<Category> opMexican = categoryRepository.findByDescription("Mexican");
        Optional<Category> opFastFood = categoryRepository.findByDescription("Fast Food");
        Optional<Category> opIndian = categoryRepository.findByDescription("Indian");



        Recepie recepie1 = new Recepie();
        recepie1.setDescription("How to Make Perfect Guacamole Recipe");
        recepie1.setCookTime(10);
        recepie1.setPrepTime(10);
        recepie1.setDirections("1. Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon\n" +
                "2. Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n"+
                "3. Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown. " +
                        "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness. " +
                        "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste. " +
                        "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving. " +
                        "\n" +
                        "4. Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. " +
                        "(The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve."
        );
        recepie1.setServings(3);
        recepie1.setSource("SimplyRecipes");
        recepie1.setUrl("www.simplyrecipes.com/recipes/perfect_guacamole/");
        recepie1.setDifficulty(Difficulty.EASY);
        recepie1.getCategories().add(opFastFood.get());
        recepie1.getCategories().add(opAmerican.get());


        Note note1 = new Note();
        note1.setNotes("Be careful handling chiles if using. Wash your hands thoroughly after handling" +
                " and do not touch your eyes or the area near your eyes with your hands for several hours.");
        note1.setRecepie(recepie1);
        recepie1.setNote(note1);


        Ingredients ingredients1 = new Ingredients("salt",BigDecimal.valueOf(1/4),
                opTeaspoon.get(),recepie1);
        Ingredients ingredients2 = new Ingredients("ripe avocados",BigDecimal.valueOf(2),
                opEach.get(),recepie1);
        Ingredients ingredients3 = new Ingredients("lemon juice",BigDecimal.valueOf(1),
                opTablespoon.get(),recepie1);
        Ingredients ingredients4 = new Ingredients("green onion",BigDecimal.valueOf(1/4),opCup.get(),recepie1);

        recepie1.getIngredients().add(ingredients1);
        recepie1.getIngredients().add(ingredients2);
        recepie1.getIngredients().add(ingredients3);
        recepie1.getIngredients().add(ingredients4);


        recepieRepository.save(recepie1);
        System.out.println("Recepie added");

        Recepie recepie2 = new Recepie();
        recepie2.setDifficulty(Difficulty.MODERATE);
        recepie2.getIngredients().add(new Ingredients("salt",BigDecimal.valueOf(1/4),opTeaspoon.get(),
                recepie2));
        recepie2.getIngredients().add(new Ingredients("lemon juice",BigDecimal.valueOf(1),opTablespoon.get(),
                recepie2));
        recepie2.getIngredients().add(new Ingredients("meat",BigDecimal.valueOf(10),opEach.get(),recepie2));
        recepie2.setNote(new Note("This is just an extra additional note"));
        recepie2.setDescription("How to make perfect lemony meat");
        recepie2.setDirections("The directions are as follows:\n" +
                "1. Add the meat.\n" +
                "2. Add the lemon.\n" +
                "3. Add some salt. \n" +
                "and voila, the food is ready!!");
        recepie2.setSource("SimplyRecipes");
        recepie2.setUrl("www.simplyrecipes.com/recipes/perfect_guacamole/");
        recepie2.getCategories().add(opFastFood.get());
        recepie2.getCategories().add(opIndian.get());
        recepieRepository.save(recepie2);
        System.out.println("Recepie2 added");
        System.out.println(recepie2.getDirections());
    }
}
