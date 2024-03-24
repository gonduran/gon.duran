package com.example.usuarios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioController() {
        // Inicializar la lista con datos

        usuarios.add(new Usuario(1, "usuario1", "password1", "usuario1@correo.cl",true,false, 
                                 ArrayList.asList(new DatoPersona(1, "Pedro", "Gutierrez", "Perez", 
                                                                  "1-9", "911111111", "pedro@correo.cl", 
                                                                  ArrayList.asList(new Direccion(1, "calle1", "1111", 
                                                                                                 "comuna", "ciudad", "region", 
                                                                                                 "particular", true, true)))),
                                 ArrayList.asList(new Rol(1, "Administrador", 
                                                          ArrayList.asList(new Privilegio(1, "Configuracion"))))));
 /*                                   Arrays.asList(new Consulta(1,"motivo","diagnostico","tratamiento","23/03/2022"), 
                                                    new Consulta(2,"motivo","diagnostico","tratamiento", "18/08/2023")),
                                    Arrays.asList(new HistorialMedico(1,"alergias","enfermedadesPrevias",
                                                                    "procedimientos","tipoSangre", "18/08/2007"), 
                                                    new HistorialMedico(2,"alergias","enfermedadesPrevias",
                                                                    "procedimientos","tipoSangre", "23/08/2023"))));*/
    }
    
    @GetMapping("/usuarios")
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    
    @GetMapping("/usuarios/{id}")
    public Usuario getUsuarioById(@PathVariable int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getIdUsuario() == id) {
                return usuario;
            }
        }
        return null;
    }

    @GetMapping(path = "/usuarios/{idUsuario}/roles")
	public List<Rol> listarRoles(@PathVariable("idUsuario") int idUsuario) {

		for (Usuario usuario : usuarios) {

			if (usuario.getIdUsuario() == idUsuario) {

				List<Rol> roles = usuario.getRoles();
                return roles;
			}
		}
		return null;
	}

    @GetMapping(path = "/usuarios/{idUsuario}/direcciones")
	public List<Direccion> listarDirecciones(@PathVariable("idUsuario") int idUsuario) {

        
		for (Usuario usuario : usuarios) {

            if (usuario.getIdUsuario() == idUsuario) {
                List<DatoPersona> datospersonales = usuario.getDatospersonales();

                List<Direccion> direcciones = datospersonales.getDirecciones();
                return direcciones;
            }
		}
		return null;
	}

}
