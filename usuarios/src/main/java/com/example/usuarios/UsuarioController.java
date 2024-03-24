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

        usuarios.add(new Usuario(1, "usuario1", "password1", "usuario1@correo.cl",
                      true, false, 
                    Arrays.asList(new DatoPersona(1, "Pedro", "Gutierrez", "Perez", 
                                                    "1-9", "911111111", "pedro@correo.cl")), 
                    Arrays.asList(new Direccion(1, "calle1", "1111", 
                                                    "comuna", "ciudad", "region", 
                                                    "particular", true, true), 
                                new Direccion(2, "calle2", "2222", 
                                                    "comuna", "ciudad", "region", 
                                                    "comercial", false, true)),
                    Arrays.asList(new Rol(1, "Administrador")), 
                    Arrays.asList(new Privilegio(1, "Configuracion"))));

        usuarios.add(new Usuario(2, "usuario2", "password2", "usuario2@correo.cl",
                      true, false, 
                    Arrays.asList(new DatoPersona(2, "Salome", "Gonzalez", "Muñoz", 
                                                        "2-7", "922222222", "salome@correo.cl")), 
                    Arrays.asList(new Direccion(1, "calle3", "3333", 
                                                "comuna", "ciudad", "region", 
                                                "particular", true, true), 
                                new Direccion(2, "calle4", "4444", 
                                                "comuna", "ciudad", "region", 
                                                "comercial", false, true)),
                    Arrays.asList(new Rol(2, "Cliente")), 
                    Arrays.asList(new Privilegio(2, "Comprador Normal"))));

        usuarios.add(new Usuario(3, "usuario3", "password3", "usuario3@correo.cl",
                           false, false, 
                    Arrays.asList(new DatoPersona(3, "Maria", "Gonzalez", "Tello", 
                                                        "3-5", "933333333", "maria@correo.cl")), 
                    Arrays.asList(new Direccion(1, "calle3", "3333", 
                                                "comuna", "ciudad", "region", 
                                                "particular", true, true), 
                                new Direccion(2, "calle4", "4444", 
                                                "comuna", "ciudad", "region", 
                                                "comercial", true, false)),
                    Arrays.asList(new Rol(2, "Cliente")), 
                    Arrays.asList(new Privilegio(3, "Comprador Premium"))));
                                    
        usuarios.add(new Usuario(4, "usuario4", "password4", "usuario4@correo.cl",
                    true, false, 
                    Arrays.asList(new DatoPersona(4, "Sergio", "Pizarro", "Abarca", 
                                                        "4-3", "944444444", "sergio@correo.cl")), 
                    Arrays.asList(new Direccion(1, "calle4", "4444", 
                                                "comuna", "ciudad", "region", 
                                                "particular", true, true), 
                                new Direccion(2, "calle5", "5555", 
                                                "comuna", "ciudad", "region", 
                                                "comercial", false, false)),
                    Arrays.asList(new Rol(2, "Cliente")), 
                    Arrays.asList(new Privilegio(2, "Comprador Normal"))));

        usuarios.add(new Usuario(5, "usuario5", "password5", "usuario5@correo.cl",
                            true, false, 
                    Arrays.asList(new DatoPersona(5, "Jose", "Ramirez", "Duarte", 
                                                        "5-1", "955555555", "jose@correo.cl")), 
                    Arrays.asList(new Direccion(1, "calle5", "5555", 
                                                "comuna", "ciudad", "region", 
                                                "particular", true, true), 
                                new Direccion(2, "calle6", "6666", 
                                "comuna", "ciudad", "region", 
                                "comercial", false, false)),
                    Arrays.asList(new Rol(3, "Proveedor")), 
                    Arrays.asList(new Privilegio(4, "Vendedor"),
                                new Privilegio(2, "Comprador Normal"))));
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
	public List<Rol> listarRolesUsuario(@PathVariable("idUsuario") int idUsuario) {

		for (Usuario usuario : usuarios) {

			if (usuario.getIdUsuario() == idUsuario) {

				List<Rol> roles = usuario.getRoles();
                return roles;
			}
		}
		return null;
	}

    @GetMapping(path = "/usuarios/{idUsuario}/datospersonales")
	public List<DatoPersona> listarDatosPersonaUsuario(@PathVariable("idUsuario") int idUsuario) {
        
		for (Usuario usuario : usuarios) {

            if (usuario.getIdUsuario() == idUsuario) {
                List<DatoPersona> datospersona = usuario.getDatospersonales();

                return datospersona;
            }
		}
		return null;
	}

    @GetMapping(path = "/usuarios/{idUsuario}/direcciones")
	public List<Direccion> listarDireccionesUsuario(@PathVariable("idUsuario") int idUsuario) {
        
		for (Usuario usuario : usuarios) {

            if (usuario.getIdUsuario() == idUsuario) {

                List<Direccion> direcciones = usuario.getDirecciones();
                return direcciones;
            }
		}
		return null;
	}

    @GetMapping(path = "/usuarios/{idUsuario}/privilegios")
	public List<Privilegio> listarPrivilegiosUsuario(@PathVariable("idUsuario") int idUsuario) {
        
		for (Usuario usuario : usuarios) {

            if (usuario.getIdUsuario() == idUsuario) {

                List<Privilegio> privilegios = usuario.getPrivilegios();
                return privilegios;
            }
		}
		return null;
	}

}
