package com.example.pacientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pacientes.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{
    
}
