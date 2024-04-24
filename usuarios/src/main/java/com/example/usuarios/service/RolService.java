package com.example.usuarios.service;

import com.example.usuarios.model.Rol;
import java.util.List;
import java.util.Optional;

public interface RolService {
    List<Rol> getAllRolUsuarios();
    Optional<Rol> getRolUsuarioById(Long id);
    Rol createRolUsuario(Rol rolUsuario);
    Rol updateRolUsuario(Long id,Rol rolUsuario);
    void deleteRolUsuario(Long id);
}
