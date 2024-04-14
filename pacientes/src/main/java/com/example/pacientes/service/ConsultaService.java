package com.example.pacientes.service;

import com.example.pacientes.model.Consulta;
import java.util.List;
import java.util.Optional;

public interface ConsultaService {
    List<Consulta> getAllConsultas();
    Optional<Consulta> getConsultaById(Long id);
    Consulta createConsulta(Consulta consulta);
    Consulta updateConsulta(Long id,Consulta consulta);
    void deleteConsulta(Long id);
}
