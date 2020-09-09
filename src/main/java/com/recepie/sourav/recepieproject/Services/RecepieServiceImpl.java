package com.recepie.sourav.recepieproject.Services;

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

    public RecepieServiceImpl(RecepieRepository recepieRepository) {
        this.recepieRepository = recepieRepository;
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
}
