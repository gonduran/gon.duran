package com.example.usuarios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usuarios.model.RolUsuario;
import com.example.usuarios.repository.RolUsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RolUsuarioServiceImpl implements RolUsuarioService {
    @Autowired
    private RolUsuarioRepository rolUsuarioRepository;

    @Override
    public List<RolUsuario> getAllRolUsuarios() {
        return rolUsuarioRepository.findAll();
    }

    @Override
    public Optional<RolUsuario> getRolUsuarioById(Long id) {
        return rolUsuarioRepository.findById(id);
    }
    
    @Override
    public RolUsuario createRolUsuario(RolUsuario rolUsuario){
        return rolUsuarioRepository.save(rolUsuario);
    }

    @Override
    public RolUsuario updateRolUsuario(Long id, RolUsuario rolUsuario){
        if(rolUsuarioRepository.existsById(id)){
            rolUsuario.setId(id);
            return rolUsuarioRepository.save(rolUsuario);
        }   else {
                return null;
        }
    }

    @Override
    public void deleteRolUsuario(Long id){
        rolUsuarioRepository.deleteById(id);
    }

}
