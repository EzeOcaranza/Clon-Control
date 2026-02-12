package Ejercicio1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import Ejercicio1.exceptions.EquipoException;
import java.util.List;

/**
 * Tests unitarios para la clase Equipo.
 * Cubre constructores, gestión de jugadores y excepciones personalizadas.
 */
@DisplayName("Pruebas de Equipo")
public class EquipoTest {

	private Equipo equipo;
	private Jugador jugador1;
	private Jugador jugador2;

	@BeforeEach
	public void setUp() {
		equipo = new Equipo("Real Madrid");
		jugador1 = new Jugador("Cristiano7", "Elite");
		jugador2 = new Jugador("Modric10", "Elite");
	}

	@Test
	@DisplayName("Crear equipo con nombre válido")
	public void testCrearEquipoValido() {
		assertNotNull(equipo);
		assertEquals("Real Madrid", equipo.getNombre());
		assertEquals(0, equipo.getCantidadJugadores());
	}

	@Test
	@DisplayName("Lanzar excepción cuando nombre es null")
	public void testCrearEquipoNombreNull() {
		assertThrows(EquipoException.class, () -> {
			new Equipo(null);
		});
	}

	@Test
	@DisplayName("Lanzar excepción cuando nombre es vacío")
	public void testCrearEquipoNombreVacio() {
		assertThrows(EquipoException.class, () -> {
			new Equipo("   ");
		});
	}

	@Test
	@DisplayName("Obtener nombre correctamente")
	public void testGetNombre() {
		assertEquals("Real Madrid", equipo.getNombre());
	}

	@Test
	@DisplayName("Establecer nombre válido")
	public void testSetNombreValido() {
		equipo.setNombre("Barcelona");
		assertEquals("Barcelona", equipo.getNombre());
	}

	@Test
	@DisplayName("Lanzar excepción al establecer nombre null")
	public void testSetNombreNull() {
		assertThrows(EquipoException.class, () -> {
			equipo.setNombre(null);
		});
	}

	@Test
	@DisplayName("Agregar jugador al equipo")
	public void testAgregarJugadorValido() {
		equipo.agregarJugador(jugador1);
		assertEquals(1, equipo.getCantidadJugadores());
		assertTrue(equipo.getJugadores().contains(jugador1));
	}

	@Test
	@DisplayName("Agregar múltiples jugadores")
	public void testAgregarMultiplesJugadores() {
		equipo.agregarJugador(jugador1);
		equipo.agregarJugador(jugador2);
		assertEquals(2, equipo.getCantidadJugadores());
	}

	@Test
	@DisplayName("Lanzar excepción al agregar jugador null")
	public void testAgregarJugadorNull() {
		assertThrows(EquipoException.class, () -> {
			equipo.agregarJugador(null);
		});
	}

	@Test
	@DisplayName("Eliminar jugador del equipo")
	public void testEliminarJugadorValido() {
		equipo.agregarJugador(jugador1);
		boolean eliminado = equipo.eliminarJugador(jugador1);
		assertTrue(eliminado);
		assertEquals(0, equipo.getCantidadJugadores());
	}

	@Test
	@DisplayName("Intentar eliminar jugador que no existe")
	public void testEliminarJugadorNoExistente() {
		boolean eliminado = equipo.eliminarJugador(jugador1);
		assertFalse(eliminado);
	}

	@Test
	@DisplayName("Obtener lista de jugadores inmutable")
	public void testGetJugadoresInmutable() {
		equipo.agregarJugador(jugador1);
		List<Jugador> jugadores = equipo.getJugadores();
		assertThrows(UnsupportedOperationException.class, () -> {
			jugadores.add(new Jugador("Test", "Basico"));
		});
	}

	@Test
	@DisplayName("Obtener cantidad de jugadores correcta")
	public void testGetCantidadJugadores() {
		assertEquals(0, equipo.getCantidadJugadores());
		equipo.agregarJugador(jugador1);
		assertEquals(1, equipo.getCantidadJugadores());
		equipo.agregarJugador(jugador2);
		assertEquals(2, equipo.getCantidadJugadores());
	}

	@Test
	@DisplayName("toString retorna formato correcto")
	public void testToString() {
		equipo.agregarJugador(jugador1);
		String resultado = equipo.toString();
		assertTrue(resultado.contains("Real Madrid"));
		assertTrue(resultado.contains("Miembros"));
	}

	@Test
	@DisplayName("Implementa interfaz IEquipoLector")
	public void testImplementaIEquipoLector() {
		assertTrue(equipo instanceof IEquipoLector);
	}

	@Test
	@DisplayName("Implementa interfaz IEquipoEscritor")
	public void testImplementaIEquipoEscritor() {
		assertTrue(equipo instanceof IEquipoEscritor);
	}
}
