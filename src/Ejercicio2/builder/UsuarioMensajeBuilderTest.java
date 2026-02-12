package Ejercicio2.builder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Ejercicio2.Usuario;
import Ejercicio2.Mensaje;
import Ejercicio2.exceptions.UsuarioException;
import Ejercicio2.exceptions.MensajeException;

/**
 * Tests unitarios con Mockito para los Builders de Usuario y Mensaje.
 * Cubre la construcción fluida y validaciones.
 */
@DisplayName("Pruebas de Builders Usuario y Mensaje con Mockito")
public class UsuarioMensajeBuilderTest {

	@Mock
	private Usuario usuarioMock;

	@Mock
	private Mensaje mensajeMock;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Construir Usuario correctamente con Builder")
	public void testConstructorUsuarioBuilder() {
		Usuario usuario = new UsuarioBuilder()
			.conNombre("Juan")
			.conApellidos("Pérez García")
			.conNickName("juanperez")
			.conFechaNacimiento("1990-05-15")
			.build();

		assertNotNull(usuario);
		assertEquals("Juan", usuario.getNombre());
		assertEquals("Pérez García", usuario.getApellidos());
		assertEquals("juanperez", usuario.getNickName());
		assertEquals("1990-05-15", usuario.getFechaNacimiento());
	}

	@Test
	@DisplayName("Construir Usuario con fecha opcional")
	public void testConstructorUsuarioBuilderSinFecha() {
		Usuario usuario = new UsuarioBuilder()
			.conNombre("Carlos")
			.conApellidos("López")
			.conNickName("carlos123")
			.build();

		assertNotNull(usuario);
		assertEquals("Carlos", usuario.getNombre());
		assertNull(usuario.getFechaNacimiento());
	}

	@Test
	@DisplayName("Lanzar excepción si falta nombre en UsuarioBuilder")
	public void testConstructorUsuarioBuilderFaltaNombre() {
		assertThrows(UsuarioException.class, () -> {
			new UsuarioBuilder()
				.conApellidos("Pérez")
				.conNickName("user123")
				.build();
		});
	}

	@Test
	@DisplayName("Lanzar excepción si falta nickName en UsuarioBuilder")
	public void testConstructorUsuarioBuilderFaltaNickName() {
		assertThrows(UsuarioException.class, () -> {
			new UsuarioBuilder()
				.conNombre("Juan")
				.conApellidos("Pérez")
				.build();
		});
	}

	@Test
	@DisplayName("Lanzar excepción con apellidos vacíos en UsuarioBuilder")
	public void testConstructorUsuarioBuilderApellidosVacio() {
		assertThrows(UsuarioException.class, () -> {
			new UsuarioBuilder()
				.conNombre("Juan")
				.conApellidos("   ")
				.conNickName("juanperez")
				.build();
		});
	}

	@Test
	@DisplayName("Construir Mensaje correctamente con Builder")
	public void testConstructorMensajeBuilder() {
		Mensaje mensaje = new MensajeBuilder()
			.conTexto("Contenido del mensaje")
			.conFechaPublicacion("2026-02-12")
			.conAutor("juanperez")
			.conEtiquetas("etiqueta1")
			.conImagen("/ruta/imagen.jpg")
			.build();

		assertNotNull(mensaje);
		assertEquals("Contenido del mensaje", mensaje.getTexto());
		assertEquals("2026-02-12", mensaje.getFechaPublicacion());
		assertEquals("juanperez", mensaje.getAutor());
		assertEquals("/ruta/imagen.jpg", mensaje.getImagen());
	}

	@Test
	@DisplayName("Construir Mensaje sin imagen con Builder")
	public void testConstructorMensajeBuilderSinImagen() {
		Mensaje mensaje = new MensajeBuilder()
			.conTexto("Primer mensaje")
			.conFechaPublicacion("2026-02-12")
			.conAutor("usuario1")
			.build();

		assertNotNull(mensaje);
		assertEquals("Primer mensaje", mensaje.getTexto());
		assertNull(mensaje.getImagen());
	}

	@Test
	@DisplayName("Lanzar excepción si falta texto en MensajeBuilder")
	public void testConstructorMensajeBuilderFaltaTexto() {
		assertThrows(MensajeException.class, () -> {
			new MensajeBuilder()
				.conFechaPublicacion("2026-02-12")
				.conAutor("usuario1")
				.build();
		});
	}

	@Test
	@DisplayName("Lanzar excepción si falta fecha en MensajeBuilder")
	public void testConstructorMensajeBuilderFaltaFecha() {
		assertThrows(MensajeException.class, () -> {
			new MensajeBuilder()
				.conTexto("Mensaje")
				.conAutor("usuario1")
				.build();
		});
	}

	@Test
	@DisplayName("Lanzar excepción si falta autor en MensajeBuilder")
	public void testConstructorMensajeBuilderFaltaAutor() {
		assertThrows(MensajeException.class, () -> {
			new MensajeBuilder()
				.conTexto("Mensaje")
				.conFechaPublicacion("2026-02-12")
				.build();
		});
	}

	@Test
	@DisplayName("Verificar encadenamiento fluido de UsuarioBuilder")
	public void testEncadenamientoFluidoUsuarioBuilder() {
		UsuarioBuilder builder = new UsuarioBuilder();
		UsuarioBuilder resultado = builder
			.conNombre("Juan")
			.conApellidos("Pérez")
			.conNickName("juan123");
		assertEquals(builder, resultado);
	}

	@Test
	@DisplayName("Verificar encadenamiento fluido de MensajeBuilder")
	public void testEncadenamientoFluidoMensajeBuilder() {
		MensajeBuilder builder = new MensajeBuilder();
		MensajeBuilder resultado = builder
			.conTexto("Hola")
			.conFechaPublicacion("2026-02-12")
			.conAutor("usuario1");
		assertEquals(builder, resultado);
	}

	@Test
	@DisplayName("Construir múltiples usuarios independientes")
	public void testConstructorMultiplesUsuarios() {
		Usuario usuario1 = new UsuarioBuilder()
			.conNombre("Juan")
			.conApellidos("Pérez")
			.conNickName("juanp")
			.build();

		Usuario usuario2 = new UsuarioBuilder()
			.conNombre("Carlos")
			.conApellidos("López")
			.conNickName("carlosl")
			.build();

		assertNotEquals(usuario1.getNickName(), usuario2.getNickName());
		assertEquals("juanp", usuario1.getNickName());
		assertEquals("carlosl", usuario2.getNickName());
	}

	@Test
	@DisplayName("Usar mock para verificar comportamiento de Usuario")
	public void testUsuarioConMock() {
		when(usuarioMock.getNickName()).thenReturn("mockeduser");
		when(usuarioMock.getNombre()).thenReturn("MockedName");

		assertEquals("mockeduser", usuarioMock.getNickName());
		assertEquals("MockedName", usuarioMock.getNombre());

		verify(usuarioMock).getNickName();
		verify(usuarioMock).getNombre();
	}

	@Test
	@DisplayName("Usar mock para verificar comportamiento de Mensaje")
	public void testMensajeConMock() {
		when(mensajeMock.getTexto()).thenReturn("Texto mockeado");
		when(mensajeMock.getAutor()).thenReturn("mockedauthor");

		assertEquals("Texto mockeado", mensajeMock.getTexto());
		assertEquals("mockedauthor", mensajeMock.getAutor());

		verify(mensajeMock).getTexto();
		verify(mensajeMock).getAutor();
	}
}
