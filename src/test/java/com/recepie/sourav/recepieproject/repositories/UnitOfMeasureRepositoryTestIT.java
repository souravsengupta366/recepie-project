package com.recepie.sourav.recepieproject.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest // this ensures the db (embedded db) we are using, is being used here. Else getDescription method will return null
class UnitOfMeasureRepositoryTestIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;
    @Test
    void findByDescription() {
       assertEquals("Cup",unitOfMeasureRepository.findByDescription("Cup").get().getDescription());
    }
}