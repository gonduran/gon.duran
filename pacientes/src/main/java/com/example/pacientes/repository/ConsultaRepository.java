package com.example.pacientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pacientes.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long>{
    
}
