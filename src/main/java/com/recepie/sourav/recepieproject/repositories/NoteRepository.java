package com.recepie.sourav.recepieproject.repositories;

import com.recepie.sourav.recepieproject.domain.Note;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<Note,Long> {
}
