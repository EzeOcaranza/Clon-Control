package Ejercicio2.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Ejercicio2.Usuario;
import Ejercicio2.factory.UsuarioMensajeFactory;

/**
 * Tests para verificar el Repository Pattern de Usuario.
 */
@DisplayName("Tests Repository Pattern - Usuario")
public class UsuarioRepositoryTest {

	private UsuarioRepository repository;
	private Usuario usuario1;
	private Usuario usuario2;

	@BeforeEach
	public void setUp() {
		repository = new UsuarioRepositoryMemoria();
		usuario1 = UsuarioMensajeFactory.crearUsuario("Juan", "Pérez", "juan123");
		usuario2 = UsuarioMensajeFactory.crearUsuario("María", "García", "maria456");
	}

	@Test
	@DisplayName("Guardar y recuperar usuario")
	public void testGuardarUsuario() {
		repository.guardar(usuario1);
		Usuario recuperado = repository.encontrarPorNickName("juan123");

		assertNotNull(recuperado);
		assertEquals("Juan", recuperado.getNombre());
		assertEquals("juan123", recuperado.getNickName());
	}

	@Test
	@DisplayName("Obtener todos los usuarios")
	public void testObtenerTodos() {
		repository.guardar(usuario1);
		repository.guardar(usuario2);

		assertEquals(2, repository.obtenerTodos().size());
	}

	@Test
	@DisplayName("Actualizar usuario")
	public void testActualizarUsuario() {
		repository.guardar(usuario1);
		usuario1.setNombre("Carlos");
		repository.actualizar(usuario1);

		Usuario actualizado = repository.encontrarPorNickName("juan123");
		assertEquals("Carlos", actualizado.getNombre());
	}

	@Test
	@DisplayName("Eliminar usuario")
	public void testEliminarUsuario() {
		repository.guardar(usuario1);
		repository.guardar(usuario2);

		repository.eliminar(usuario1);

		assertNull(repository.encontrarPorNickName("juan123"));
		assertEquals(1, repository.contar());
	}

	@Test
	@DisplayName("Contar usuarios")
	public void testContar() {
		assertEquals(0, repository.contar());
		repository.guardar(usuario1);
		assertEquals(1, repository.contar());
		repository.guardar(usuario2);
		assertEquals(2, repository.contar());
	}
}
