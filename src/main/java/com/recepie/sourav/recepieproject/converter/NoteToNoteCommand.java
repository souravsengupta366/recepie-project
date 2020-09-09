package com.recepie.sourav.recepieproject.converter;

import com.recepie.sourav.recepieproject.commands.NoteCommand;
import com.recepie.sourav.recepieproject.domain.Note;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NoteToNoteCommand implements Converter<Note, NoteCommand> {
    @Synchronized
    @Nullable
    @Override
    public NoteCommand convert(Note source) {
        if(source==null)
            return null;
        final NoteCommand noteCommand = new NoteCommand();
        noteCommand.setId(source.getId());
        noteCommand.setNotes(source.getNotes());
        return noteCommand;
    }
}
