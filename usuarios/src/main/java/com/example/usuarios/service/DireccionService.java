package com.example.usuarios.service;

import com.example.usuarios.model.Direccion;
import java.util.List;
import java.util.Optional;

public interface DireccionService {
    List<Direccion> getAllDirecciones();
    Optional<Direccion> getDireccionById(Long id);
    Direccion createDireccion(Direccion direccion);
    Direccion updateDireccion(Long id,Direccion direccion);
    void deleteDireccion(Long id);
    List<Direccion> getAllDireccionesUsuarioById(Long id);
}
