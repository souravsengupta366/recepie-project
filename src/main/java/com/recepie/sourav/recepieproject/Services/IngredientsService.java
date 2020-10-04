package com.recepie.sourav.recepieproject.Services;

import com.recepie.sourav.recepieproject.commands.IngredientsCommand;
import com.recepie.sourav.recepieproject.domain.Ingredients;

public interface IngredientsService {
    public void deleteById(Long recepieId, Long id);
    public Ingredients getIngredientsById(Long recepieId,Long id);
    public IngredientsCommand getIngredientsCommandById(Long recepieId, Long id);
    public Ingredients saveIngredientsCommand(IngredientsCommand ingredientsCommand);
    public Ingredients save(Ingredients ingredients);
}
