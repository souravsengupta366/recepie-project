package com.recepie.sourav.recepieproject.Services;

import com.recepie.sourav.recepieproject.commands.RecepieCommand;
import com.recepie.sourav.recepieproject.converter.RecepieCommandToRecepie;
import com.recepie.sourav.recepieproject.converter.RecepieToRecepieCommand;
import com.recepie.sourav.recepieproject.domain.Recepie;
import com.recepie.sourav.recepieproject.repositories.RecepieRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Setter
@Getter
//@NoArgsConstructor
//@AllArgsConstructor
@Service
public class RecepieServiceImpl implements RecepieService{


    private final RecepieRepository recepieRepository;
    private final RecepieToRecepieCommand recepieToRecepieCommand;
    private final RecepieCommandToRecepie recepieCommandToRecepie;

    public RecepieServiceImpl(RecepieRepository recepieRepository, RecepieToRecepieCommand recepieToRecepieCommand, RecepieCommandToRecepie recepieCommandToRecepie) {
        this.recepieRepository = recepieRepository;
        this.recepieToRecepieCommand = recepieToRecepieCommand;
        this.recepieCommandToRecepie = recepieCommandToRecepie;
    }

    @Override
    public Set<Recepie> findAll() {
        Set<Recepie> allRecepies = new HashSet<>();
        recepieRepository.findAll().forEach(recepie -> allRecepies.add(recepie));
        return allRecepies;
    }

    @Override
    public Recepie findById(Long id) {
        Optional<Recepie> recepie = recepieRepository.findById(id);
        if(!recepie.isPresent())
            throw new RuntimeException("There is no recepie with the given id");
        return recepie.get();
    }

    @Override
    public void deleteById(Long id) {
        recepieRepository.deleteById(id);
    }

    @Override
    public RecepieCommand saveRecepieCommand(RecepieCommand recepieCommand) {

        Recepie savedRecepie = recepieRepository.save(recepieCommandToRecepie.convert(recepieCommand));
        return recepieToRecepieCommand.convert(savedRecepie);
    }

    @Override
    public RecepieCommand findCommandById(Long id) {
        return recepieToRecepieCommand.convert(recepieRepository.findById(id).get());
    }

    @Override
    public Recepie save(Recepie recepie) {
        Recepie savedRecepie = recepieRepository.save(recepie);
        return savedRecepie;
    }
}
