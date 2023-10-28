package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Persona;
import com.example.demo.repository.PersonaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PersonaServiceTest {

    @Autowired
    private PersonaService personaService;

    @MockBean
    private PersonaRepository personaRepository;

    @Test
    void testSave() {
        Persona persona = new Persona("John", "Doe");

        when(personaRepository.save(persona)).thenReturn(persona);

        Persona expected = personaService.save(persona);

        assertEquals(expected, persona);
    }

    @Test
    void testFindAll() {
        List<Persona> list = Arrays.asList(
                new Persona("John", "Doe"),
                new Persona("Jane", "Doe"));

        when(personaRepository.findAll()).thenReturn(list);

        List<Persona> expected = personaService.findAll();

        assertEquals(expected, list);
    }

    @Test
    void testFindById_existingId() {
        Long personaId = 1L;
        Persona persona = new Persona("John", "Doe");

        when(personaRepository.findById(personaId)).thenReturn(Optional.of(persona));

        Persona expected = personaService.findById(personaId);

        assertEquals(expected, persona);
    }

    @Test
    void testFindById_nonExistingId() {
        Long id = -1L;
        when(personaRepository.findById(id)).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(
                ResourceNotFoundException.class,
                () -> personaService.findById(id),
                "Expected findById to throw, but it didn't");

        assertEquals("Persona no encontrada con id: " + id, thrown.getMessage());
    }

    @Test
    void testDeleteById() {
        Long personaId = 1L;

        doNothing().when(personaRepository).deleteById(personaId);

        personaRepository.deleteById(personaId);

        verify(personaRepository, times(1)).deleteById(personaId);
    }

    @Test
    void testDeleteById_nonExistingId() {
        Long id = -1L;
        when(personaRepository.findById(id)).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(
                ResourceNotFoundException.class,
                () -> personaService.deleteById(id),
                "Expected deleteById to throw, but it didn't");

        assertEquals("Persona no encontrada con id: " + id, thrown.getMessage());
    }
}
