package com.example.usuarios.service;

import com.example.usuarios.model.RolUsuario;
import java.util.List;
import java.util.Optional;

public interface RolUsuarioService {
    List<RolUsuario> getAllRolUsuarios();
    Optional<RolUsuario> getRolUsuarioById(Long id);
    RolUsuario createRolUsuario(RolUsuario rolUsuario);
    RolUsuario updateRolUsuario(Long id,RolUsuario rolUsuario);
    void deleteRolUsuario(Long id);
}
