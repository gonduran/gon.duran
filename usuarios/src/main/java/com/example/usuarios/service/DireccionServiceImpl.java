package com.example.usuarios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usuarios.model.Direccion;
import com.example.usuarios.repository.DireccionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DireccionServiceImpl implements DireccionService {
    @Autowired
    private DireccionRepository direccionRepository;

    @Override
    public List<Direccion> getAllDirecciones() {
        return direccionRepository.findAll();
    }

    @Override
    public Optional<Direccion> getDireccionById(Long id) {
        return direccionRepository.findById(id);
    }
    
    @Override
    public Direccion createDireccion(Direccion direccion){
        return direccionRepository.save(direccion);
    }

    @Override
    public Direccion updateDireccion(Long id, Direccion direccion){
        if(direccionRepository.existsById(id)){
            direccion.setId(id);
            return direccionRepository.save(direccion);
        }   else {
                return null;
        }
    }

    @Override
    public void deleteDireccion(Long id){
        direccionRepository.deleteById(id);
    }


    @Override
    public List<Direccion> getAllDireccionesUsuarioById(Long id){
        return direccionRepository.findByUsuarioId(id);
    }

}
