package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.model.Persona;
import com.example.demo.repository.PersonaRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class PersonaServiceTest {

    @Autowired
    private PersonaService personaService;

    @MockBean
    private PersonaRepository personaRepository;

    @Test
    void testFindAll() {
        List<Persona> list = new ArrayList<>();
        Persona p1 = new Persona("John", "Doe");
        Persona p2 = new Persona("Jane", "Doe");
        list.add(p1);
        list.add(p2);

        when(personaRepository.findAll()).thenReturn(list);

        List<Persona> expected = personaService.findAll();
    
        assertEquals(expected, list);
    }

    // ... otras pruebas ...
}
