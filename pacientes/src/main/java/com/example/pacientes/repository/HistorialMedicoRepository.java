package com.example.pacientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pacientes.model.HistorialMedico;

public interface HistorialMedicoRepository extends JpaRepository<HistorialMedico, Long>{
    
}
