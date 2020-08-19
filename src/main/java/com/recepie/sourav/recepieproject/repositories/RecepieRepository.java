package com.recepie.sourav.recepieproject.repositories;

import com.recepie.sourav.recepieproject.domain.Recepie;
import org.springframework.data.repository.CrudRepository;

public interface RecepieRepository extends CrudRepository<Recepie,Long> {
}
