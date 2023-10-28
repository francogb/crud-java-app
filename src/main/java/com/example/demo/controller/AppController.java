package com.example.demo.controller;

import com.example.demo.model.Persona;
import com.example.demo.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listPersonas", personaService.findAll());
        return "index";
    }

    @GetMapping("/showNewPersonaForm")
    public String showNewPersonaForm(Model model) {
        Persona persona = new Persona();
        model.addAttribute("persona", persona);
        return "new_persona";
    }

    @PostMapping("/savePersona")
    public String savePersona(@ModelAttribute("persona") Persona persona) {
        personaService.save(persona);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Persona persona = personaService.findById(id);
        model.addAttribute("persona", persona);
        return "update_persona";
    }

    @GetMapping("/deletePersona/{id}")
    public String deletePersona(@PathVariable(value = "id") long id) {
        personaService.deleteById(id);
        return "redirect:/";
    }
}
