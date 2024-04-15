package com.example.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.usuarios.model.Rol;

public interface RolUsuarioRepository extends JpaRepository<Rol, Long>{
    
}
