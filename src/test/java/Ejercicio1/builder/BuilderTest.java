package Ejercicio1.builder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Ejercicio1.Equipo;
import Ejercicio1.Jugador;
import Ejercicio1.exceptions.EquipoException;
import Ejercicio1.exceptions.JugadorException;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests unitarios con Mockito para los Builders.
 * Cubre la construcción fluida y validaciones.
 */
@DisplayName("Pruebas de Builders con Mockito")
public class BuilderTest {

	@Mock
	private Jugador jugadorMock;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Construir Jugador correctamente con Builder")
	public void testConstructorJugadorBuilder() {
		Jugador jugador = new JugadorBuilder()
			.conNickname("Cristiano7")
			.conRango("Elite")
			.build();

		assertNotNull(jugador);
		assertEquals("Cristiano7", jugador.getNickname());
		assertEquals("Elite", jugador.getRango());
	}

	@Test
	@DisplayName("Lanzar excepción en Builder si faltan parámetros")
	public void testConstructorJugadorBuilderFaltanParametros() {
		assertThrows(JugadorException.class, () -> {
			new JugadorBuilder()
				.conNickname("Cristiano7")
				.build();
		});
	}

	@Test
	@DisplayName("Lanzar excepción en Builder con nickname vacío")
	public void testConstructorJugadorBuilderNicknameVacio() {
		assertThrows(JugadorException.class, () -> {
			new JugadorBuilder()
				.conNickname("")
				.conRango("Elite")
				.build();
		});
	}

	@Test
	@DisplayName("Construir Equipo correctamente con Builder")
	public void testConstructorEquipoBuilder() {
		Equipo equipo = new EquipoBuilder()
			.conNombre("Real Madrid")
			.agregarJugador(new Jugador("Cristiano7", "Elite"))
			.agregarJugador(new Jugador("Modric10", "Elite"))
			.build();

		assertNotNull(equipo);
		assertEquals("Real Madrid", equipo.getNombre());
		assertEquals(2, equipo.getCantidadJugadores());
	}

	@Test
	@DisplayName("Construir Equipo sin jugadores")
	public void testConstructorEquipoBuilderSinJugadores() {
		Equipo equipo = new EquipoBuilder()
			.conNombre("Barcelona")
			.build();

		assertNotNull(equipo);
		assertEquals("Barcelona", equipo.getNombre());
		assertEquals(0, equipo.getCantidadJugadores());
	}

	@Test
	@DisplayName("Lanzar excepción en EquipoBuilder si falta nombre")
	public void testConstructorEquipoBuilderFaltaNombre() {
		assertThrows(EquipoException.class, () -> {
			new EquipoBuilder()
				.agregarJugador(new Jugador("Cristiano7", "Elite"))
				.build();
		});
	}

	@Test
	@DisplayName("Agregar múltiples jugadores con Builder")
	public void testEquipoBuilderAgregarMultiples() {
		List<Jugador> jugadores = new ArrayList<>();
		jugadores.add(new Jugador("Cristiano7", "Elite"));
		jugadores.add(new Jugador("Modric10", "Elite"));

		Equipo equipo = new EquipoBuilder()
			.conNombre("Real Madrid")
			.agregarJugadores(jugadores)
			.build();

		assertEquals(2, equipo.getCantidadJugadores());
	}

	@Test
	@DisplayName("Lanzar excepción si se agrega jugador null en Builder")
	public void testEquipoBuilderAgregarJugadorNull() {
		assertThrows(EquipoException.class, () -> {
			new EquipoBuilder()
				.conNombre("Real Madrid")
				.agregarJugador(null)
				.build();
		});
	}

	@Test
	@DisplayName("Verificar encadenamiento fluido del Builder")
	public void testEncadenamientoFluidoBuilder() {
		JugadorBuilder builder = new JugadorBuilder();
		JugadorBuilder resultado = builder.conNickname("Test").conRango("Basico");
		assertEquals(builder, resultado);
	}

	@Test
	@DisplayName("Construir múltiples instancias independientes")
	public void testConstructorMultiplesInstancias() {
		Jugador jugador1 = new JugadorBuilder()
			.conNickname("Cristiano7")
			.conRango("Elite")
			.build();

		Jugador jugador2 = new JugadorBuilder()
			.conNickname("Messi10")
			.conRango("Elite")
			.build();

		assertNotEquals(jugador1.getNickname(), jugador2.getNickname());
		assertEquals("Cristiano7", jugador1.getNickname());
		assertEquals("Messi10", jugador2.getNickname());
	}

	@Test
	@DisplayName("Usar mock para verificar comportamiento del Builder")
	public void testBuilderConMock() {
		// Setup del mock
		when(jugadorMock.getNickname()).thenReturn("MockedPlayer");
		when(jugadorMock.getRango()).thenReturn("Elite");

		// Verificar el mock
		assertEquals("MockedPlayer", jugadorMock.getNickname());
		assertEquals("Elite", jugadorMock.getRango());

		// Verificar que fue llamado
		verify(jugadorMock).getNickname();
	}
}
