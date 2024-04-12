package com.example.usuarios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usuarios.model.Usuario;
import com.example.usuarios.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> getUsuarioById(Long idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }
    
    @Override
    public Usuario createUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario updateUsuario(Long idUsuario, Usuario usuario){
        if(usuarioRepository.existsById(idUsuario)){
            usuario.setIdUsuario(idUsuario);
            return usuarioRepository.save(usuario);
        }   else {
                return null;
        }
    }

    @Override
    public void deleteUsuario(Long id){
        usuarioRepository.deleteById(id);
    }
}
