package com.example.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.usuarios.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long>{
    
}
