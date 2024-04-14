package com.example.usuarios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usuarios.model.DatoPersona;
import com.example.usuarios.repository.DatoPersonaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DatoPersonaServiceImpl implements DatoPersonaService {
    @Autowired
    private DatoPersonaRepository datoPersonaRepository;

    @Override
    public List<DatoPersona> getAllDatoPersonas() {
        return datoPersonaRepository.findAll();
    }

    @Override
    public Optional<DatoPersona> getDatoPersonaById(Long id) {
        return datoPersonaRepository.findById(id);
    }
    
    @Override
    public DatoPersona createDatoPersona(DatoPersona datoPersona){
        return datoPersonaRepository.save(datoPersona);
    }

    @Override
    public DatoPersona updateDatoPersona(Long id, DatoPersona datoPersona){
        if(datoPersonaRepository.existsById(id)){
            datoPersona.setId(id);
            return datoPersonaRepository.save(datoPersona);
        }   else {
                return null;
        }
    }

    @Override
    public void deleteDatoPersona(Long id){
        datoPersonaRepository.deleteById(id);
    }

}
