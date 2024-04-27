package com.example.usuarios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.usuarios.model.Rol;
import com.example.usuarios.repository.RolRepository;

@ExtendWith(MockitoExtension.class)
public class RolServiceTest {
    @InjectMocks
    private RolServiceImpl rolService;

    @Mock
    private RolRepository rolRepositoryMock;

    @Test
    public void getAllRolUsuariosTest() {
        Rol rol1 = new Rol();
        rol1.setDescripcion("Administrador");
        rol1.setId(1L);

        Rol rol2 = new Rol();
        rol2.setDescripcion("Usuario");
        rol2.setId(2L);

        List<Rol> roles = Arrays.asList(rol1, rol2);

        when(rolRepositoryMock.findAll()).thenReturn(roles);

        List<Rol> resultado = rolService.getAllRolUsuarios();

        assertEquals(2, resultado.size());
        assertEquals("Administrador", resultado.get(0).getDescripcion());
        assertEquals("Usuario", resultado.get(1).getDescripcion());
    }

    @Test
    public void getRolUsuarioByIdTest() {
        Rol rol = new Rol();
        rol.setDescripcion("Administrador");
        rol.setId(1L);

        when(rolRepositoryMock.findById(1L)).thenReturn(Optional.of(rol));

        Optional<Rol> resultado = rolService.getRolUsuarioById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Administrador", resultado.get().getDescripcion());
    }

    @Test
    public void createRolUsuarioTest() {

        Rol rol = new Rol();
        rol.setDescripcion("Administrador");
        rol.setId(1L);

        when(rolRepositoryMock.save(any())).thenReturn(rol);

        Rol resultado = rolService.createRolUsuario(rol);

        assertEquals("Administrador", resultado.getDescripcion());
    }

    @Test
    public void updateRolUsuarioTest_Exists() {
        Rol rol = new Rol();
        rol.setDescripcion("Administrador");
        rol.setId(1L);

        when(rolRepositoryMock.existsById(1L)).thenReturn(true);
        when(rolRepositoryMock.save(rol)).thenReturn(rol);

        Rol resultado = rolService.updateRolUsuario(1L, rol);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Administrador", resultado.getDescripcion());
    }

    @Test
    public void updateRolUsuarioTest_NotExists() {
        Rol rol = new Rol();
        rol.setDescripcion("Administrador");
        rol.setId(2L);

        when(rolRepositoryMock.existsById(2L)).thenReturn(false);

        Rol resultado = rolService.updateRolUsuario(2L, rol);

        assertNull(resultado);
    }
}
