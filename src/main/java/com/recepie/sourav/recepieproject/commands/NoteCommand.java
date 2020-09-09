package com.recepie.sourav.recepieproject.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;

@Getter
@Setter
@NoArgsConstructor
public class NoteCommand {

    private Long id;
    @Lob
    private String notes;
}
