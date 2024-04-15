package com.example.usuarios.service;

import com.example.usuarios.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> getAllUsuarios();
    Optional<Usuario> getUsuarioById(Long id);
    Usuario createUsuario(Usuario usuario);
    Usuario updateUsuario(Long id,Usuario usuario);
    void deleteUsuario(Long id);
    boolean validarUsuario(String usuario, String password);
}
