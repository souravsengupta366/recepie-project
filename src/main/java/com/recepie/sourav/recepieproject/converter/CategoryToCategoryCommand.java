package com.recepie.sourav.recepieproject.converter;

import com.recepie.sourav.recepieproject.commands.CategoryCommand;
import com.recepie.sourav.recepieproject.domain.Category;
import org.springframework.lang.Nullable;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements  Converter<Category,CategoryCommand>{

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category source) {
        if(source == null)
            return null;
        final CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(source.getId());
        categoryCommand.setDescription(source.getDescription());
        return categoryCommand;
    }
}
