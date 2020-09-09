package com.recepie.sourav.recepieproject.converter;

import com.recepie.sourav.recepieproject.commands.RecepieCommand;
import com.recepie.sourav.recepieproject.domain.Recepie;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecepieToRecepieCommand implements Converter<Recepie, RecepieCommand> {
    final CategoryToCategoryCommand categoryToCategoryCommand;
    final NoteToNoteCommand noteToNoteCommand;
    final IngredientsToIngredientsCommand ingredientsToIngredientsCommand;
    RecepieToRecepieCommand(CategoryToCategoryCommand categoryToCategoryCommand, NoteToNoteCommand noteToNoteCommand, IngredientsToIngredientsCommand ingredientsToIngredientsCommand){
        this.categoryToCategoryCommand = categoryToCategoryCommand;
        this.noteToNoteCommand = noteToNoteCommand;
        this.ingredientsToIngredientsCommand = ingredientsToIngredientsCommand;
    }
    @Synchronized
    @Nullable
    @Override
    public RecepieCommand convert(Recepie source) {
        if(source == null)
            return null;
        final RecepieCommand recepieCommand = new RecepieCommand();
        recepieCommand.setId(source.getId());
        recepieCommand.setDescription(source.getDescription());
        recepieCommand.setPrepTime(source.getPrepTime());
        recepieCommand.setCookTime(source.getCookTime());
        recepieCommand.setServings(source.getServings());
        recepieCommand.setSource(source.getSource());
        recepieCommand.setUrl(source.getUrl());
        recepieCommand.setDirections(source.getDirections());
        recepieCommand.setNote(noteToNoteCommand.convert(source.getNote()));
        if(source.getIngredients().size()>0){
            source.getIngredients()
                    .forEach(ingredients ->
                            recepieCommand.getIngredients().add(ingredientsToIngredientsCommand.convert(ingredients)));
        }
        recepieCommand.setDifficulty(source.getDifficulty());
        if(source.getCategories().size() > 0){
            source.getCategories()
                    .forEach(category ->
                            recepieCommand.getCategories().add(categoryToCategoryCommand.convert(category)));
        }
        return recepieCommand;
    }
}
