package com.recepie.sourav.recepieproject.Services;

import com.recepie.sourav.recepieproject.commands.IngredientsCommand;
import com.recepie.sourav.recepieproject.converter.IngredientsCommandToIngredients;
import com.recepie.sourav.recepieproject.converter.IngredientsToIngredientsCommand;
import com.recepie.sourav.recepieproject.converter.RecepieToRecepieCommand;
import com.recepie.sourav.recepieproject.domain.Ingredients;
import com.recepie.sourav.recepieproject.domain.Recepie;
import org.springframework.stereotype.Service;

@Service
public class IngredientsServiceImpl implements  IngredientsService{
    private final RecepieService recepieService;
    private final RecepieToRecepieCommand recepieToRecepieCommand;
    private final IngredientsToIngredientsCommand ingredientsToIngredientsCommand;
    private final IngredientsCommandToIngredients ingredientsCommandToIngredients;

    public IngredientsServiceImpl(RecepieService recepieService, RecepieToRecepieCommand recepieToRecepieCommand, IngredientsToIngredientsCommand ingredientsToIngredientsCommand, IngredientsCommandToIngredients ingredientsCommandToIngredients) {
        this.recepieService = recepieService;
        this.recepieToRecepieCommand = recepieToRecepieCommand;
        this.ingredientsToIngredientsCommand = ingredientsToIngredientsCommand;
        this.ingredientsCommandToIngredients = ingredientsCommandToIngredients;
    }


    @Override
    public Ingredients getIngredientsById(Long recepieId,Long id) {
        Recepie recepie = recepieService.findById(recepieId);
        Ingredients ingredient = recepie.getIngredients()
                .stream()
                .filter(ingredients -> ingredients.getId() == id)
                .findFirst()
                .get();
        return ingredient;
    }

    @Override
    public IngredientsCommand getIngredientsCommandById(Long recepieId, Long id) {

        Ingredients ingredient = getIngredientsById(new Long(recepieId), new Long(id));
        IngredientsCommand ingredientsCommand = ingredientsToIngredientsCommand.convert(ingredient);
        return ingredientsCommand;
    }

    @Override
    public void deleteById(Long recepieId, Long id) {
        Recepie recepie = recepieService.findById(recepieId);
        Ingredients ingredient = getIngredientsById(recepieId,id);
        ingredient.setRecipe(null);
        System.out.println("# ingredients before deletion -->"+recepie.getIngredients().size());
        recepie.getIngredients().removeIf(ingredients -> ingredients.getId() == id);
        System.out.println("# ingredients after deletion -->"+recepie.getIngredients().size());
        recepieService.save(recepie);
    }

    @Override
    public Ingredients saveIngredientsCommand(IngredientsCommand ingredientsCommand) {
        Ingredients ingredients = ingredientsCommandToIngredients.convert(ingredientsCommand);
        if(ingredients == null)
            System.out.println("ingridients is null in saveIngredientsCommand");
        return save(ingredients);
    }

    @Override
    public Ingredients save(Ingredients ingredients) {
        Recepie recepie = ingredients.getRecipe();
        if(recepie.getIngredients() == null)
            System.out.println("getIngridients sending null value");
        if(ingredients == null)
            System.out.println("Ingridients is null");
        recepie.getIngredients().add(ingredients);
        return recepie.getIngredients().stream().filter(ingd -> ingd.getId() == ingredients.getId()).findFirst().get();
    }
}
