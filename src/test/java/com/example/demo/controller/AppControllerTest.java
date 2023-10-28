package com.example.demo.controller;

import com.example.demo.model.Persona;
import com.example.demo.service.PersonaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AppControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonaService personaService;

    @Test
    void testViewHomePage() throws Exception {
        List<Persona> list = Arrays.asList(
                new Persona("John", "Doe"),
                new Persona("Jane", "Doe")
        );

        when(personaService.findAll()).thenReturn(list);

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("listPersonas", list));
    }

    @Test
    void testShowNewPersonaForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/showNewPersonaForm"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("new_persona"))
                .andExpect(model().attributeExists("persona"));
    }

    @Test
    void testSavePersona() throws Exception {
        Persona persona = new Persona("John", "Doe");

        mockMvc.perform(MockMvcRequestBuilders.post("/savePersona")
                .flashAttr("persona", persona))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void testShowFormForUpdate() throws Exception {
        Long id = 1L;
        Persona persona = new Persona("John", "Doe");

        when(personaService.findById(id)).thenReturn(persona);

        mockMvc.perform(MockMvcRequestBuilders.get("/showFormForUpdate/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("update_persona"))
                .andExpect(model().attribute("persona", persona));
    }

    @Test
    void testDeletePersona() throws Exception {
        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/deletePersona/{id}", id))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}
