package com.example.model;

import jakarta.transaction.Transactional;
import jdk.jfr.Percentage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {
    @Autowired
    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }
    @Transactional
    public Persona saveUser(Persona user) {
        System.out.println("provo ad salvare");
        return personaRepository.save(user);
    }

    public List<Persona> findAll(){
        return (List<Persona>) personaRepository.findAll();
    }


    // altri metodi di servizio
}
