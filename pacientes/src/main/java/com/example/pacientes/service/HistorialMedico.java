package com.example.pacientes.service;

import com.example.pacientes.model.HistorialMedico;
import java.util.List;
import java.util.Optional;

public interface HistorialMedico {
    List<HistorialMedico> getAllHistorialMedicos();
    Optional<HistorialMedico> getHistorialMedicoById(Long id);
    HistorialMedico createHistorialMedico(HistorialMedico historialMedico);
    HistorialMedico updateHistorialMedico(Long id,HistorialMedico historialMedico);
    void deleteHistorialMedico(Long id); 
}
