package com.example.usuarios.service;

import com.example.usuarios.model.DatoPersona;
import java.util.List;
import java.util.Optional;

public interface DatoPersonaService {
    List<DatoPersona> getAllDatoPersonas();
    Optional<DatoPersona> getDatoPersonaById(Long id);
    DatoPersona createDatoPersona(DatoPersona datoPersona);
    DatoPersona updateDatoPersona(Long id,DatoPersona datoPersona);
    void deleteDatoPersona(Long id);
}
