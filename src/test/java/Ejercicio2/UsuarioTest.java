package Ejercicio2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import Ejercicio2.exceptions.UsuarioException;
import java.util.List;

/**
 * Tests unitarios para la clase Usuario.
 * Cubre constructores, gestión de mensajes y excepciones personalizadas.
 */
@DisplayName("Pruebas de Usuario")
public class UsuarioTest {

	private Usuario usuario;
	private Mensaje mensaje1;
	private Mensaje mensaje2;

	@BeforeEach
	public void setUp() {
		usuario = new Usuario("Juan", "Pérez García", "1990-05-15", "juanperez");
		mensaje1 = new Mensaje("Hola a todos", "2026-02-12", "juanperez", "saludo", null);
		mensaje2 = new Mensaje("¿Cómo están?", "2026-02-12", "juanperez", "pregunta", null);
	}

	@Test
	@DisplayName("Crear usuario con valores válidos")
	public void testCrearUsuarioValido() {
		assertNotNull(usuario);
		assertEquals("Juan", usuario.getNombre());
		assertEquals("Pérez García", usuario.getApellidos());
		assertEquals("1990-05-15", usuario.getFechaNacimiento());
		assertEquals("juanperez", usuario.getNickName());
		assertEquals(0, usuario.getCantidadMensajes());
	}

	@Test
	@DisplayName("Lanzar excepción cuando nombre es null")
	public void testCrearUsuarioNombreNull() {
		assertThrows(UsuarioException.class, () -> {
			new Usuario(null, "Pérez", "1990-05-15", "usuario");
		});
	}

	@Test
	@DisplayName("Lanzar excepción cuando apellidos es vacío")
	public void testCrearUsuarioApellidosVacio() {
		assertThrows(UsuarioException.class, () -> {
			new Usuario("Juan", "   ", "1990-05-15", "usuario");
		});
	}

	@Test
	@DisplayName("Lanzar excepción cuando nickName es null")
	public void testCrearUsuarioNickNameNull() {
		assertThrows(UsuarioException.class, () -> {
			new Usuario("Juan", "Pérez", "1990-05-15", null);
		});
	}

	@Test
	@DisplayName("Obtener nombre correctamente")
	public void testGetNombre() {
		assertEquals("Juan", usuario.getNombre());
	}

	@Test
	@DisplayName("Establecer nombre válido")
	public void testSetNombreValido() {
		usuario.setNombre("Carlos");
		assertEquals("Carlos", usuario.getNombre());
	}

	@Test
	@DisplayName("Lanzar excepción al establecer nombre vacío")
	public void testSetNombreVacio() {
		assertThrows(UsuarioException.class, () -> {
			usuario.setNombre("");
		});
	}

	@Test
	@DisplayName("Obtener apellidos correctamente")
	public void testGetApellidos() {
		assertEquals("Pérez García", usuario.getApellidos());
	}

	@Test
	@DisplayName("Establecer apellidos válidos")
	public void testSetApellidosValidos() {
		usuario.setApellidos("Martínez López");
		assertEquals("Martínez López", usuario.getApellidos());
	}

	@Test
	@DisplayName("Obtener fecha de nacimiento")
	public void testGetFechaNacimiento() {
		assertEquals("1990-05-15", usuario.getFechaNacimiento());
	}

	@Test
	@DisplayName("Establecer fecha de nacimiento")
	public void testSetFechaNacimiento() {
		usuario.setFechaNacimiento("1995-12-20");
		assertEquals("1995-12-20", usuario.getFechaNacimiento());
	}

	@Test
	@DisplayName("Obtener nickName correctamente")
	public void testGetNickName() {
		assertEquals("juanperez", usuario.getNickName());
	}

	@Test
	@DisplayName("Establecer nickName válido")
	public void testSetNickNameValido() {
		usuario.setNickName("jperezmartinez");
		assertEquals("jperezmartinez", usuario.getNickName());
	}

	@Test
	@DisplayName("Publicar un mensaje")
	public void testPublicarMensaje() {
		usuario.publicarMensaje(mensaje1);
		assertEquals(1, usuario.getCantidadMensajes());
		assertTrue(usuario.getMensajes().contains(mensaje1));
	}

	@Test
	@DisplayName("Publicar múltiples mensajes")
	public void testPublicarMultiplesMensajes() {
		usuario.publicarMensaje(mensaje1);
		usuario.publicarMensaje(mensaje2);
		assertEquals(2, usuario.getCantidadMensajes());
	}

	@Test
	@DisplayName("Lanzar excepción al publicar mensaje null")
	public void testPublicarMensajeNull() {
		assertThrows(UsuarioException.class, () -> {
			usuario.publicarMensaje(null);
		});
	}

	@Test
	@DisplayName("Eliminar un mensaje publicado")
	public void testEliminarMensaje() {
		usuario.publicarMensaje(mensaje1);
		boolean eliminado = usuario.eliminarMensaje(mensaje1);
		assertTrue(eliminado);
		assertEquals(0, usuario.getCantidadMensajes());
	}

	@Test
	@DisplayName("Intentar eliminar mensaje que no existe")
	public void testEliminarMensajeNoExistente() {
		boolean eliminado = usuario.eliminarMensaje(mensaje1);
		assertFalse(eliminado);
	}

	@Test
	@DisplayName("Obtener lista de mensajes inmutable")
	public void testGetMensajesInmutable() {
		usuario.publicarMensaje(mensaje1);
		List<Mensaje> mensajes = usuario.getMensajes();
		assertThrows(UnsupportedOperationException.class, () -> {
			mensajes.add(mensaje2);
		});
	}

	@Test
	@DisplayName("Obtener cantidad de mensajes correcta")
	public void testGetCantidadMensajes() {
		assertEquals(0, usuario.getCantidadMensajes());
		usuario.publicarMensaje(mensaje1);
		assertEquals(1, usuario.getCantidadMensajes());
		usuario.publicarMensaje(mensaje2);
		assertEquals(2, usuario.getCantidadMensajes());
	}

	@Test
	@DisplayName("toString retorna formato correcto")
	public void testToString() {
		usuario.publicarMensaje(mensaje1);
		String resultado = usuario.toString();
		assertTrue(resultado.contains("Juan"));
		assertTrue(resultado.contains("juanperez"));
	}

	@Test
	@DisplayName("Implementa interfaz IUsuarioLector")
	public void testImplementaIUsuarioLector() {
		assertTrue(usuario instanceof IUsuarioLector);
	}

	@Test
	@DisplayName("Implementa interfaz IUsuarioEscritor")
	public void testImplementaIUsuarioEscritor() {
		assertTrue(usuario instanceof IUsuarioEscritor);
	}
}
