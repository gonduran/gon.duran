package com.example.usuarios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usuarios.model.Rol;
import com.example.usuarios.repository.RolRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RolServiceImpl implements RolService {
    @Autowired
    private RolRepository rolUsuarioRepository;

    @Override
    public List<Rol> getAllRolUsuarios() {
        return rolUsuarioRepository.findAll();
    }

    @Override
    public Optional<Rol> getRolUsuarioById(Long id) {
        return rolUsuarioRepository.findById(id);
    }
    
    @Override
    public Rol createRolUsuario(Rol rolUsuario){
        return rolUsuarioRepository.save(rolUsuario);
    }

    @Override
    public Rol updateRolUsuario(Long id, Rol rolUsuario){
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
