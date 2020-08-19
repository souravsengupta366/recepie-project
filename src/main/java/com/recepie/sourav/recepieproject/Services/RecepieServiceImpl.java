package com.recepie.sourav.recepieproject.Services;

import com.recepie.sourav.recepieproject.domain.Recepie;
import com.recepie.sourav.recepieproject.repositories.RecepieRepository;

import java.util.Set;

public class RecepieServiceImpl implements RecepieService{

    private final RecepieRepository recepieRepository;

    public RecepieServiceImpl(RecepieRepository recepieRepository) {
        this.recepieRepository = recepieRepository;
    }

    @Override
    public Set<Recepie> findAll() {
        return (Set<Recepie>) recepieRepository.findAll();

    }
}
