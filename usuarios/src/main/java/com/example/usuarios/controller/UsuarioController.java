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
import com.example.usuarios.model.Direccion;
import com.example.usuarios.model.RolUsuario;
import com.example.usuarios.service.UsuarioService;
import com.example.usuarios.service.DireccionService;
import com.example.usuarios.service.RolUsuarioService;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private DireccionService direccionService;
    @Autowired
    private RolUsuarioService rolUsuarioService;

    //devuelve la informacion de todos los usuarios
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        log.info("GET /usuarios");
        log.info("Devuelve la informacion de todos los usuarios");
        return usuarioService.getAllUsuarios();
    }

    //devuelve la informacion de todos las direcciones
    @GetMapping("/direccion")
    public List<Direccion> getAllDirecciones() {
        log.info("GET /usuarios/direcciones");
        log.info("Devuelve la informacion de todos las direcciones");
        return direccionService.getAllDirecciones();
    }

    //devuelve la informacion de todos los rol usuarios
    @GetMapping("/rolusuario")
    public List<RolUsuario> getAllRolUsuarios() {
        log.info("GET /usuarios/rolusuarios");
        log.info("Devuelve la informacion de todos los rol usuarios");
        return rolUsuarioService.getAllRolUsuarios();
    }

    //devuelve la informacion de un usuario especifico
    @GetMapping("/{idUsuario}")
    public ResponseEntity<Object> getUsuarioById(@PathVariable("idUsuario") Long idUsuario) {
        log.info("GET /usuarios/{idUsuario}");
        Optional<Usuario> usuario = usuarioService.getUsuarioById(idUsuario);
        if (usuario.isEmpty()) {
            log.error("No se encontró el usuario con ID {}", idUsuario);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró el usuario con ID " + idUsuario));
        }
        log.info("Se encontró el usuario con ID {}", idUsuario);
        return ResponseEntity.ok(usuario);
    }

    //devuelve la informacion de una direccion especifico
    @GetMapping("/direccion/{idDireccion}")
    public ResponseEntity<Object> getDireccionById(@PathVariable("idDireccion") Long idDireccion) {
        log.info("GET /usuarios/direccion/{idDireccion}");
        Optional<Direccion> direccion = direccionService.getDireccionById(idDireccion);
        if (direccion.isEmpty()) {
            log.error("No se encontró la direccion con ID {}", idDireccion);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró la direccion con ID " + idDireccion));
        }
        log.info("Se encontró el usuario con ID {}", idDireccion);
        return ResponseEntity.ok(direccion);
    }

    //devuelve la informacion de un rol usuario especifico
    @GetMapping("/rolusuario/{idRolUsuario}")
    public ResponseEntity<Object> getRolUsuarioById(@PathVariable("idRolUsuario") Long idRolUsuario) {
        log.info("GET /usuarios/rolusuario/{idRolUsuario}");
        Optional<RolUsuario> rolUsuario = rolUsuarioService.getRolUsuarioById(idRolUsuario);
        if (rolUsuario.isEmpty()) {
            log.error("No se encontró el rol usuario con ID {}", idRolUsuario);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró el rol usuario con ID " + idRolUsuario));
        }
        log.info("Se encontró el usuario con ID {}", idRolUsuario);
        return ResponseEntity.ok(rolUsuario);
    }

    @PostMapping
    public ResponseEntity<Object> createUsuario(@Validated @RequestBody Usuario usuario) {
        log.info("POST /usuarios");
        Usuario createUsuario = usuarioService.createUsuario(usuario);
        if (createUsuario == null) {
            log.error("Error al crear el usuario {}", usuario);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Error al crear el usuario"));
        }
        return ResponseEntity.ok(createUsuario);
    }
    
    @PostMapping("/direccion")
    public ResponseEntity<Object> createDireccion(@Validated @RequestBody Direccion direccion) {
        log.info("POST /usuarios/direccion");
        Direccion createDireccion = direccionService.createDireccion(direccion);
        if (createDireccion == null) {
            log.error("Error al crear la direccion {}", direccion);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Error al crear la direccion"));
        }
        return ResponseEntity.ok(createDireccion);
    }
    
    @PostMapping("/rolusuario")
    public ResponseEntity<Object> createRolUsuario(@Validated @RequestBody RolUsuario rolUsuario) {
        log.info("POST /usuarios/rolusuario");
        RolUsuario createRolUsuario = rolUsuarioService.createRolUsuario(rolUsuario);
        if (createRolUsuario == null) {
            log.error("Error al crear el rol usuario {}", rolUsuario);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Error al crear el rol usuario"));
        }
        return ResponseEntity.ok(createRolUsuario);
    }
    
    @PutMapping("/{idUsuario}")
    public ResponseEntity<Object> updateUsuario(@PathVariable("idUsuario") Long idUsuario, @RequestBody Usuario usuario) {
        log.info("PUT /usuarios/{idUsuario}");
        Optional<Usuario> usuarioFind = usuarioService.getUsuarioById(idUsuario);
        if (usuarioFind.isEmpty()) {
            log.error("No se encontró el usuario con ID {}", idUsuario);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró el dato persona con ID " + idUsuario));
        }
        log.info("Se encontró el usuario con ID {}", idUsuario);
        return ResponseEntity.ok(usuarioService.updateUsuario(idUsuario, usuario));
    }

    @PutMapping("/direccion/{idDireccion}")
    public ResponseEntity<Object> updateDireccion(@PathVariable("idDireccion") Long idDireccion, @RequestBody Direccion direccion) {
        log.info("PUT /usuarios/direccion/{idDireccion}");
        Optional<Direccion> direccionFind = direccionService.getDireccionById(idDireccion);
        if (direccionFind.isEmpty()) {
            log.error("No se encontró la dirección con ID {}", idDireccion);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró la dirección con ID " + idDireccion));
        }
        log.info("Se encontró la dirección con ID {}", idDireccion);
        return ResponseEntity.ok(direccionService.updateDireccion(idDireccion, direccion));
    }

    @PutMapping("/rolusuario/{idRolUsuario}")
    public ResponseEntity<Object> updateRolUsuario(@PathVariable("idRolUsuario") Long idRolUsuario, @RequestBody RolUsuario rolUsuario) {
        log.info("PUT /usuarios/rolusuario/{idRolUsuario}");
        Optional<RolUsuario> rolUsuarioFind = rolUsuarioService.getRolUsuarioById(idRolUsuario);
        if (rolUsuarioFind.isEmpty()) {
            log.error("No se encontró el rol usuario con ID {}", idRolUsuario);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró el rol usuario con ID " + idRolUsuario));
        }
        log.info("Se encontró el rol usuario con ID {}", idRolUsuario);
        return ResponseEntity.ok(rolUsuarioService.updateRolUsuario(idRolUsuario, rolUsuario));
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable("idUsuario") Long idUsuario){
        log.info("DELETE /usuarios/{idUsuario}");
        Optional<Usuario> usuarioFind = usuarioService.getUsuarioById(idUsuario);
        if (usuarioFind.isEmpty()) {
            log.error("No se encontró el usuario con ID {}", idUsuario);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró el dato persona con ID " + idUsuario));
        }
        log.info("Se encontró y elimino el usuario con ID {}", idUsuario);
        usuarioService.deleteUsuario(idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse("Se encontró y elimino el usuario con ID " + idUsuario));
    }

    @DeleteMapping("/direccion/{idDireccion}")
    public ResponseEntity<Object> deleteDireccion(@PathVariable("idDireccion") Long idDireccion){
        log.info("DELETE /usuarios/direccion/{idDireccion}");
        Optional<Direccion> direccionFind = direccionService.getDireccionById(idDireccion);
        if (direccionFind.isEmpty()) {
            log.error("No se encontró la dirección con ID {}", idDireccion);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró la dirección con ID " + idDireccion));
        }
        log.info("Se encontró y elimino la dirección con ID {}", idDireccion);
        direccionService.deleteDireccion(idDireccion);
        return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse("Se encontró y elimino la dirección con ID " + idDireccion));
    }

    @DeleteMapping("/rolusuario/{idRolUsuario}")
    public ResponseEntity<Object> deleteRolUsuario(@PathVariable("idRolUsuario") Long idRolUsuario){
        log.info("DELETE /usuarios/rolusuario/{idRolUsuario}");
        Optional<RolUsuario> rolUsuarioFind = rolUsuarioService.getRolUsuarioById(idRolUsuario);
        if (rolUsuarioFind.isEmpty()) {
            log.error("No se encontró el rol usuario con ID {}", idRolUsuario);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró el rol usuario con ID " + idRolUsuario));
        }
        log.info("Se encontró y elimino el rol usuario con ID {}", idRolUsuario);
        rolUsuarioService.deleteRolUsuario(idRolUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse("Se encontró y elimino el rol usuario con ID " + idRolUsuario));
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
