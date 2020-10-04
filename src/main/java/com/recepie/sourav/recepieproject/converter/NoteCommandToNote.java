package com.recepie.sourav.recepieproject.converter;

import com.recepie.sourav.recepieproject.commands.NoteCommand;
import com.recepie.sourav.recepieproject.domain.Note;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NoteCommandToNote implements Converter<NoteCommand, Note> {

    @Synchronized
    @Nullable
    @Override
    public Note convert(NoteCommand source) {
        if(source == null)
            return null;
        final Note note = new Note();
        note.setId(source.getId());
        note.setNotes(source.getNotes());
        return note;
    }
}
