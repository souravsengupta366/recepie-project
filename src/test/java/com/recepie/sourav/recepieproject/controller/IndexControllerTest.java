package com.recepie.sourav.recepieproject.controller;

import com.recepie.sourav.recepieproject.Services.RecepieService;
import com.recepie.sourav.recepieproject.domain.Recepie;
import com.recepie.sourav.recepieproject.repositories.RecepieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class IndexControllerTest {
    @Mock
    RecepieRepository recepieRepository;
    @Mock
    RecepieService recepieService;
    @Mock
    Model model;
    IndexController indexController;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);//what is this?
        indexController = new IndexController(recepieService);
//        indexController = Mockito.mock(IndexController.class);
    }


    @Test
    void indexControllerUnitTest() throws Exception {
        //mock servlet context or mock dispatcher servlet. No need  to bring spring web context
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        //performing unit testing of request mappings
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void index( ) {
        Set<Recepie> recepies = new HashSet<>();
        recepies.add(new Recepie());
        Recepie recepie = new Recepie();
        recepie.setId(1L);
        recepies.add(recepie);

        when(recepieService.findAll()).thenReturn(recepies);

        ArgumentCaptor<Set<Recepie>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String returnVal = indexController.index(model);

        //then
        assertEquals("index",returnVal);
        verify(model,times(1)).addAttribute(eq("recipes"),argumentCaptor.capture());
        verify(recepieService,times(1)).findAll();
        assertEquals(2,argumentCaptor.getValue().size());
//        System.out.println(indexController.index(model));
       /****NOt WORKING***/
//        try {
//            Mockito.when("index".equals(indexController.index(model))).thenThrow(new RuntimeException(" Index returned"));
//        }catch (Exception e){
//            System.out.println("caught");
//        }

    }
}