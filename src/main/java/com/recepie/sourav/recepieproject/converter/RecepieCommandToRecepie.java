package com.recepie.sourav.recepieproject.converter;

import com.recepie.sourav.recepieproject.commands.RecepieCommand;
import com.recepie.sourav.recepieproject.domain.Recepie;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecepieCommandToRecepie implements Converter<RecepieCommand, Recepie> {
    private final NoteCommandToNote noteCommandToNote;
    private final IngredientsCommandToIngredients ingredientsCommandToIngredients;
    private final CategoryCommandToCategory categoryCommandToCategory;
    public RecepieCommandToRecepie(NoteCommandToNote noteCommandToNote, IngredientsCommandToIngredients ingredientsCommandToIngredients, CategoryCommandToCategory categoryCommandToCategory) {
        this.noteCommandToNote = noteCommandToNote;
        this.ingredientsCommandToIngredients = ingredientsCommandToIngredients;
        this.categoryCommandToCategory = categoryCommandToCategory;
    }


    @Synchronized
    @Nullable
    @Override
    public Recepie convert(RecepieCommand source) {
        if(source == null)
            return null;
        final Recepie recepie = new Recepie();
        recepie.setId(source.getId());
        recepie.setDescription(source.getDescription());
        recepie.setPrepTime(source.getPrepTime());
        recepie.setCookTime(source.getCookTime());
        recepie.setServings(source.getServings());
        recepie.setSource(source.getSource());
        recepie.setUrl(source.getUrl());
        recepie.setDirections(source.getDirections());
        recepie.setNote(noteCommandToNote.convert(source.getNote()));
        if(source.getIngredients().size()>0){
            source.getIngredients()
                    .forEach(command ->{
                            recepie.getIngredients().add(ingredientsCommandToIngredients.convert(command));});
        }
        recepie.setDifficulty(source.getDifficulty());
        if(source.getCategories().size()>0){
            source.getCategories()
                    .forEach(categoryCommand ->
                            recepie.getCategories().add(categoryCommandToCategory.convert(categoryCommand)));
        }
        return  recepie;
    }
}
