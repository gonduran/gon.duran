package com.example.usuarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.usuarios.model.Usuario;
import com.example.usuarios.exception.UsuarioBadRequestException;
import com.example.usuarios.exception.UsuarioNotFoundException;
import com.example.usuarios.model.Direccion;
import com.example.usuarios.model.Rol;
import com.example.usuarios.service.UsuarioService;
import com.example.usuarios.service.DireccionService;
import com.example.usuarios.service.RolService;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private RolService rolUsuarioService;

    //devuelve la informacion de todos los usuarios
    @GetMapping
    public CollectionModel<EntityModel<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        log.info("GET /usuarios");
        log.info("Devuelve la informacion de todos los usuarios");
        List<EntityModel<Usuario>> usuariosResources = usuarios.stream()
            .map( usuario -> EntityModel.of(usuario,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioById(usuario.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).deleteUsuario(usuario.getId())).withRel("delete")
            ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios());
        CollectionModel<EntityModel<Usuario>> resources = CollectionModel.of(usuariosResources, linkTo.withRel("usuarios"));

        return resources;
    }

    //devuelve la informacion de todos las direcciones
    @GetMapping("/direccion")
    public CollectionModel<EntityModel<Direccion>> getAllDirecciones() {
        List<Direccion> direcciones = direccionService.getAllDirecciones();
        log.info("GET /usuarios/direcciones");
        log.info("Devuelve la informacion de todas las direcciones");
        List<EntityModel<Direccion>> direccionesResources = direcciones.stream()
            .map( direccion -> EntityModel.of(direccion,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getDireccionById(direccion.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).deleteDireccion(direccion.getId())).withRel("delete")
            ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllDirecciones());
        CollectionModel<EntityModel<Direccion>> resources = CollectionModel.of(direccionesResources, linkTo.withRel("direcciones"));

        return resources;
    }

    //devuelve la informacion de todos los rol usuarios
    @GetMapping("/rolusuario")
    public CollectionModel<EntityModel<Rol>> getAllRolUsuarios() {
        List<Rol> rolUsuarios = rolUsuarioService.getAllRolUsuarios();
        log.info("GET /usuarios/rolusuarios");
        log.info("Devuelve la informacion de todos los rol usuarios");
        List<EntityModel<Rol>> rolUsuariosResources = rolUsuarios.stream()
            .map( rolUsuario -> EntityModel.of(rolUsuario,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getRolUsuarioById(rolUsuario.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).deleteRolUsuario(rolUsuario.getId())).withRel("delete")
            ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllRolUsuarios());
        CollectionModel<EntityModel<Rol>> resources = CollectionModel.of(rolUsuariosResources, linkTo.withRel("rolusuarios"));

        return resources;
    }

    //devuelve la informacion de un usuario especifico
    @GetMapping("/{idUsuario}")
    public EntityModel<Usuario> getUsuarioById(@PathVariable("idUsuario") Long idUsuario) {
        Optional<Usuario> usuario = usuarioService.getUsuarioById(idUsuario);
        log.info("GET /usuarios/{idUsuario}");
        log.info("Se ejecuta getUsuarioById: {}", idUsuario);
        if (usuario.isPresent()) {
            log.info("Se encontró el usuario con ID {}", idUsuario);
            return EntityModel.of(usuario.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioById(idUsuario)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).deleteUsuario(idUsuario)).withRel("delete"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios()).withRel("all-usuarios"));
        } else {
            log.error("No se encontró el usuario con ID {}", idUsuario);
            throw new UsuarioBadRequestException("No se encontró el usuario con ID: " + idUsuario);
        }
    }

    //devuelve la informacion de una direccion especifico
    @GetMapping("/direccion/{idDireccion}")
    public EntityModel<Direccion> getDireccionById(@PathVariable("idDireccion") Long idDireccion) {
        Optional<Direccion> direccion = direccionService.getDireccionById(idDireccion);
        log.info("GET /usuarios/direccion/{idDireccion}");
        log.info("Se ejecuta getDireccionById: {}", idDireccion);
        if (direccion.isPresent()) {
            log.info("Se encontró la direccion con ID {}", idDireccion);
            return EntityModel.of(direccion.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getDireccionById(idDireccion)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).deleteDireccion(idDireccion)).withRel("delete"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllDirecciones()).withRel("all-direcciones"));
        } else {
            log.error("No se encontró la direccion con ID {}", idDireccion);
            throw new UsuarioNotFoundException("No se encontró la direccion con ID: " + idDireccion);
        }
    }

    //devuelve la informacion de un rol usuario especifico
    @GetMapping("/rolusuario/{idRolUsuario}")
    public EntityModel<Rol> getRolUsuarioById(@PathVariable("idRolUsuario") Long idRolUsuario) {
        Optional<Rol> historialMedico = rolUsuarioService.getRolUsuarioById(idRolUsuario);
        log.info("GET /usuarios/rolusuario/{idRolUsuario}");
        log.info("Se ejecuta getRolUsuarioById: {}", idRolUsuario);
        if (historialMedico.isPresent()) {
            log.info("Se encontró el rol usuario con ID {}", idRolUsuario);
            return EntityModel.of(historialMedico.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getRolUsuarioById(idRolUsuario)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).deleteRolUsuario(idRolUsuario)).withRel("delete"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllRolUsuarios()).withRel("all-rolusuarios"));
        } else {
            log.error("No se encontró el rol usuario con ID {}", idRolUsuario);
            throw new UsuarioNotFoundException("No se encontró el rol usuario con ID: " + idRolUsuario);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error en el servidor: " + e.getMessage());
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<String> handlePacienteNotFoundException(UsuarioNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(e.getMessage());
    }

    @ExceptionHandler(UsuarioBadRequestException.class)
    public ResponseEntity<String> handlePacienteBadRequestException(UsuarioBadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(e.getMessage());
    }

    @PostMapping
    public EntityModel<Usuario> createUsuario(@Validated @RequestBody Usuario usuario) {
        log.info("POST /usuarios");
        log.info("Se ejecuta createUsuario");
        Usuario createUsuario = usuarioService.createUsuario(usuario);
        if (createUsuario == null) {
            log.error("Error al crear el usuario {}", usuario);
            throw new UsuarioBadRequestException("Error al crear el usuario");
        }
        return EntityModel.of(createUsuario,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioById(createUsuario.getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios()).withRel("all-usuarios"));
    }
    
    @PostMapping("/direccion")
    public EntityModel<Direccion> createDireccion(@Validated @RequestBody Direccion direccion) {
        log.info("POST /usuarios/direccion");
        log.info("Se ejecuta createConsulta");
        Direccion createDireccion = direccionService.createDireccion(direccion);
        if (createDireccion == null) {
            log.error("Error al crear la direccion {}", direccion);
            throw new UsuarioBadRequestException("Error al crear la direccion");
        }
        return EntityModel.of(createDireccion,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getDireccionById(createDireccion.getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllDirecciones()).withRel("all-direcciones"));
    }
    
    @PostMapping("/rolusuario")
    public EntityModel<Rol> createRolUsuario(@Validated @RequestBody Rol rolUsuario) {
        log.info("POST /usuarios/rolusuario");
        log.info("Se ejecuta createRolUsuario");
        Rol createRolUsuario = rolUsuarioService.createRolUsuario(rolUsuario);
        if (createRolUsuario == null) {
            log.error("Error al crear el rol usuario {}", rolUsuario);
            throw new UsuarioBadRequestException("Error al crear el rol usuario");
        }
        return EntityModel.of(createRolUsuario,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getRolUsuarioById(createRolUsuario.getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllRolUsuarios()).withRel("all-rolusuarios"));
    }
    
    @PutMapping("/{idUsuario}")
    public EntityModel<Usuario> updateUsuario(@PathVariable("idUsuario") Long idUsuario, @RequestBody Usuario usuario) {
        log.info("PUT /usuarios/{idUsuario}");
        log.info("Se ejecuta updateUsuario: {}", idUsuario);
        Optional<Usuario> usuarioFind = usuarioService.getUsuarioById(idUsuario);
        if (usuarioFind.isEmpty()) {
            log.error("No se encontró el usuario con ID {}", idUsuario);
            throw new UsuarioNotFoundException("No se encontró el usuario con ID: " + idUsuario);
        }
        log.info("Se encontró y actualizo el usuario con ID {}", idUsuario);
        Usuario updateUsuario = usuarioService.updateUsuario(idUsuario, usuario);
        return EntityModel.of(updateUsuario,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioById(idUsuario)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios()).withRel("all-usuarios"));

    }

    @PutMapping("/direccion/{idDireccion}")
    public EntityModel<Direccion> updateDireccion(@PathVariable("idDireccion") Long idDireccion, @RequestBody Direccion direccion) {
        log.info("PUT /usuarios/direccion/{idDireccion}");
        log.info("Se ejecuta updateDireccion: {}", idDireccion);
        Optional<Direccion> direccionFind = direccionService.getDireccionById(idDireccion);
        if (direccionFind.isEmpty()) {
            log.error("No se encontró la dirección con ID {}", idDireccion);
            throw new UsuarioNotFoundException("No se encontró la dirección con ID: " + idDireccion);
        }
        log.info("Se encontró y actualizo la dirección con ID {}", idDireccion);
        Direccion updateDireccion = direccionService.updateDireccion(idDireccion, direccion);
        return EntityModel.of(updateDireccion,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getDireccionById(idDireccion)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllDirecciones()).withRel("all-consultas"));

    }

    @PutMapping("/rolusuario/{idRolUsuario}")
    public EntityModel<Rol> updateRolUsuario(@PathVariable("idRolUsuario") Long idRolUsuario, @RequestBody Rol rolUsuario) {
        log.info("PUT /usuarios/rolusuario/{idRolUsuario}");
        log.info("Se ejecuta updateRolUsuario: {}", idRolUsuario);
        Optional<Rol> historialMedicoFind = rolUsuarioService.getRolUsuarioById(idRolUsuario);
        if (historialMedicoFind.isEmpty()) {
            log.error("No se encontró el rol usuario con ID {}", idRolUsuario);
            throw new UsuarioNotFoundException("No se encontró el rol usuario con ID: " + idRolUsuario);
        }
        log.info("Se encontró y actualizo el rol usuario con ID {}", idRolUsuario);
        Rol updateRolUsuario = rolUsuarioService.updateRolUsuario(idRolUsuario, rolUsuario);
        return EntityModel.of(updateRolUsuario,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getRolUsuarioById(idRolUsuario)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllRolUsuarios()).withRel("all-rolusuarios"));

    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable("idUsuario") Long idUsuario){
        log.info("DELETE /usuarios/{idUsuario}");
        Optional<Usuario> usuarioFind = usuarioService.getUsuarioById(idUsuario);
        if (usuarioFind.isEmpty()) {
            log.error("No se encontró el usuario con ID {}", idUsuario);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró el usuario con ID " + idUsuario));
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
        Optional<Rol> rolUsuarioFind = rolUsuarioService.getRolUsuarioById(idRolUsuario);
        if (rolUsuarioFind.isEmpty()) {
            log.error("No se encontró el rol usuario con ID {}", idRolUsuario);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró el rol usuario con ID " + idRolUsuario));
        }
        log.info("Se encontró y elimino el rol usuario con ID {}", idRolUsuario);
        rolUsuarioService.deleteRolUsuario(idRolUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse("Se encontró y elimino el rol usuario con ID " + idRolUsuario));
    }

    @PostMapping("/validar")
    public ResponseEntity<Object> validarUsuario(@RequestParam String usuario, @RequestParam String password) {
        log.info("POST /usuarios/validar");
        log.info("Usuario {}", usuario);
        log.info("Password {}", password);
        boolean esValido = usuarioService.validarUsuario(usuario, password);
        if (esValido){
            return ResponseEntity.ok().body("Usuario y contraseña validos");
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Usuario o contraseña incorrectos."));
        }
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

    //devuelve la informacion de las direcciones de un usuario especifico
    @GetMapping(path = "/{idUsuario}/direcciones")
    public CollectionModel<EntityModel<Direccion>> listarDireccionesUsuario(@Validated @PathVariable("idUsuario") Long idUsuario) {
        log.info("GET /usuarios/{idUsuario}/direcciones");
        log.info("Se ejecuta listarDireccionesUsuario {}", idUsuario);
        
        List<Direccion> direcciones = direccionService.getAllDireccionesUsuarioById(idUsuario);
        if (direcciones.isEmpty()) {
            log.error("No se encontró direcciones para ID Usuario {} ", idUsuario);
            throw new UsuarioNotFoundException("No se encontró direcciones con ID Usuario: " + idUsuario);

        } else {
            log.info("Se encontró direcciones con ID Usuario {}", idUsuario);
            List<EntityModel<Direccion>> direccionesResources = direcciones.stream()
            .map( direccion -> EntityModel.of(direccion,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getDireccionById(direccion.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).deleteDireccion(direccion.getId())).withRel("delete")
            ))
            .collect(Collectors.toList());

            WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).listarDireccionesUsuario(idUsuario));
            CollectionModel<EntityModel<Direccion>> resources = CollectionModel.of(direccionesResources, linkTo.withRel("all-direcciones-usuario"));

            return resources;
        }

    }

}
