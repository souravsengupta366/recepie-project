package com.recepie.sourav.recepieproject.converter;

import com.recepie.sourav.recepieproject.commands.IngredientsCommand;
import com.recepie.sourav.recepieproject.domain.Ingredients;
import com.recepie.sourav.recepieproject.domain.Recepie;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientsCommandToIngredients implements Converter<IngredientsCommand, Ingredients> {
    private final UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;
//    private final RecepieService recepieService;

    public IngredientsCommandToIngredients(UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure) {
        this.unitOfMeasureCommandToUnitOfMeasure = unitOfMeasureCommandToUnitOfMeasure;
//        this.recepieService = recepieService;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredients convert(IngredientsCommand source) {
        final Ingredients ingredients = new Ingredients();

        if(source == null)
            return null;
        if(source.getRecepieId() != 0l){
            System.out.println("source has a recepie id");
            Recepie recepie = new Recepie();
            recepie.setId(source.getRecepieId());
            ingredients.setRecipe(recepie);
        }
        System.out.println("recepie id is :"+ source.getRecepieId());
        ingredients.setId(source.getId());
        ingredients.setDescription(source.getDescription());
        ingredients.setAmount(source.getAmount());
        ingredients.setUnitOfMeasure(unitOfMeasureCommandToUnitOfMeasure.convert(source.getUnitOfMeasure()));
        return ingredients;
    }
}
