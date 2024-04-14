package com.example.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.usuarios.model.DatoPersona;

public interface DatoPersonaRepository extends JpaRepository<DatoPersona, Long>{
    
}
