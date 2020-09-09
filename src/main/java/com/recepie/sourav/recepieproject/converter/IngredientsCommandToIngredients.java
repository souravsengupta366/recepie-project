package com.recepie.sourav.recepieproject.converter;

import com.recepie.sourav.recepieproject.commands.IngredientsCommand;
import com.recepie.sourav.recepieproject.domain.Ingredients;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientsCommandToIngredients implements Converter<IngredientsCommand, Ingredients> {
    final UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

    public IngredientsCommandToIngredients(UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure) {
        this.unitOfMeasureCommandToUnitOfMeasure = unitOfMeasureCommandToUnitOfMeasure;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredients convert(IngredientsCommand source) {
        if(source == null)
            return null;
        final Ingredients ingredients = new Ingredients();
        ingredients.setId(source.getId());
        ingredients.setDescription(source.getDescription());
        ingredients.setAmount(source.getAmount());
        ingredients.setUnitOfMeasure(unitOfMeasureCommandToUnitOfMeasure.convert(source.getUnitOfMeasure()));
        return ingredients;
    }
}
