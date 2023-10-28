package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Persona;
import com.example.demo.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public List<Persona> findAll() {
        List<Persona> personas = personaRepository.findAll();
        if (personas.isEmpty()) {
            return Collections.emptyList();
        }
        return personas;
    }

    public Persona findById(Long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada con id: " + id));
    }

    public Persona save(Persona persona) {
        if (persona == null) {
            throw new IllegalArgumentException("La persona no puede ser null");
        }
        return personaRepository.save(persona);
    }

    public void deleteById(Long id) {
        if (!personaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Persona no encontrada con id: " + id);
        }
        personaRepository.deleteById(id);
    }
}
