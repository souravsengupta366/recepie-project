package com.recepie.sourav.recepieproject.Services;

import com.recepie.sourav.recepieproject.domain.Recepie;
import com.recepie.sourav.recepieproject.repositories.RecepieRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

//@ExtendWith(SpringExtension.class)
//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class RecepieServiceImplTest {

    @Mock
    RecepieRepository recepieRepository;
    RecepieService recepieService;

    @Before
    void before(){
        MockitoAnnotations.initMocks(this);
        recepieService = new RecepieServiceImpl(recepieRepository);
    }
    @Test
    void findById() {

        Recepie recepie = new Recepie();
        recepie.setId(1l);
        Optional<Recepie> optionalRecepie = Optional.of(recepie);
        when(recepieRepository.findById(anyLong())).thenReturn(optionalRecepie);
        Optional<Recepie> recipeReturned = recepieRepository.findById(1l);
        assertNotNull("Null recipe returned", recipeReturned.get());
        verify(recepieRepository, times(1)).findById(anyLong());
        verify(recepieRepository, never()).findAll();
    }
}