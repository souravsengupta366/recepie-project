package com.recepie.sourav.recepieproject.converter;

import com.recepie.sourav.recepieproject.commands.IngredientsCommand;
import com.recepie.sourav.recepieproject.domain.Ingredients;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientsToIngredientsCommand implements Converter<Ingredients, IngredientsCommand> {
    final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
    IngredientsToIngredientsCommand(UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand){
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }
    @Synchronized
    @Nullable
    @Override
    public IngredientsCommand convert(Ingredients source) {
        if(source == null)
            return null;
        final IngredientsCommand ingredientsCommand = new IngredientsCommand();
        ingredientsCommand.setId(source.getId());
        ingredientsCommand.setAmount(source.getAmount());
        ingredientsCommand.setDescription(source.getDescription());
        ingredientsCommand.setUnitOfMeasure(unitOfMeasureToUnitOfMeasureCommand.convert(source.getUnitOfMeasure()));
        return ingredientsCommand;
    }
}
