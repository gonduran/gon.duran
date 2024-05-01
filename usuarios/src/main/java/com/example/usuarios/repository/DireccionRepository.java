package com.example.usuarios.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.usuarios.model.Direccion;

public interface DireccionRepository extends JpaRepository<Direccion, Long>{
    List<Direccion> findByUsuarioId(Long usuarioId);
}
