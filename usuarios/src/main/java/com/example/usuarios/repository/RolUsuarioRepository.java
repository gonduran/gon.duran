package com.example.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.usuarios.model.RolUsuario;

public interface RolUsuarioRepository extends JpaRepository<RolUsuario, Long>{
    
}
