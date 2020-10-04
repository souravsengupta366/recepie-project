package com.recepie.sourav.recepieproject.Services;

import com.recepie.sourav.recepieproject.commands.RecepieCommand;
import com.recepie.sourav.recepieproject.domain.Recepie;

import java.util.Set;



public interface RecepieService {
    Set<Recepie> findAll();
    Recepie findById(Long id);
    Recepie save(Recepie recepie);
    void deleteById(Long id);
    RecepieCommand saveRecepieCommand(RecepieCommand recepieCommand);
    RecepieCommand findCommandById(Long id);
}
