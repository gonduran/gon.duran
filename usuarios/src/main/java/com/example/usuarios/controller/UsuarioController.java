package com.example.usuarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.usuarios.model.Usuario;
import com.example.usuarios.service.UsuarioService;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.constraints.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;

    //devuelve la informacion de todos los usuarios
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        log.info("GET /usuarios");
        log.info("Devuelve la informacion de todos los usuarios");
        return usuarioService.getAllUsuarios();
    }

    //devuelve la informacion de un usuario especifico
    @GetMapping("/{idUsuario}")
    public ResponseEntity<Object> getUsuarioById(@PathVariable("idUsuario") Long idUsuario) {
        log.info("GET /{idUsuario}");
        Optional<Usuario> usuario = usuarioService.getUsuarioById(idUsuario);
        if (usuario.isEmpty()) {
            log.error("No se encontró el usuario con ID {}", idUsuario);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró el usuario con ID " + idUsuario));
        }
        log.info("Se encontró el usuario con ID {}", idUsuario);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Object> createUsuario(@Validated @RequestBody Usuario usuario) {
        Usuario createdUsuario = usuarioService.createUsuario(usuario);
        if (createdUsuario == null) {
            log.error("Error al crear el usuario {}", usuario);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Error al crear el usuario"));
        }
        return ResponseEntity.ok(createdUsuario);
    }
    
    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable Long idUsuario, @RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(idUsuario, usuario);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long idUsuario){
        usuarioService.deleteUsuario(idUsuario);
    }

    static class ErrorResponse {
        private final String message;
    
        public ErrorResponse(String message) {
            this.message = message;
        }
    
        public String getMessage() {
            return message;
        }
    }

}
