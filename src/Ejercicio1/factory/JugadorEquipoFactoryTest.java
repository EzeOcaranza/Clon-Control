package Ejercicio1.factory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Ejercicio1.Jugador;
import Ejercicio1.Equipo;
import Ejercicio1.exceptions.JugadorException;
import Ejercicio1.exceptions.EquipoException;

/**
 * Tests para verificar el Factory Pattern de Jugador y Equipo.
 */
@DisplayName("Tests Factory Pattern - Jugador y Equipo")
public class JugadorEquipoFactoryTest {

	@Test
	@DisplayName("Factory crea Jugador válido")
	public void testCrearJugador() {
		Jugador jugador = JugadorEquipoFactory.crearJugador("Cristiano7", "Elite");
		assertNotNull(jugador);
		assertEquals("Cristiano7", jugador.getNickname());
		assertEquals("Elite", jugador.getRango());
	}

	@Test
	@DisplayName("Factory crea Jugador Elite con rango predefinido")
	public void testCrearJugadorElite() {
		Jugador jugador = JugadorEquipoFactory.crearJugadorElite("Messi10");
		assertEquals("Elite", jugador.getRango());
		assertEquals("Messi10", jugador.getNickname());
	}

	@Test
	@DisplayName("Factory crea Jugador Profesional con rango predefinido")
	public void testCrearJugadorProfesional() {
		Jugador jugador = JugadorEquipoFactory.crearJugadorProfesional("Neymar");
		assertEquals("Profesional", jugador.getRango());
	}

	@Test
	@DisplayName("Factory crea Jugador Básico con rango predefinido")
	public void testCrearJugadorBasico() {
		Jugador jugador = JugadorEquipoFactory.crearJugadorBasico("Vinicius");
		assertEquals("Básico", jugador.getRango());
	}

	@Test
	@DisplayName("Factory lanza excepción con nickname vacío")
	public void testCrearJugadorNicknameVacio() {
		assertThrows(JugadorException.class, () -> {
			JugadorEquipoFactory.crearJugador("", "Elite");
		});
	}

	@Test
	@DisplayName("Factory lanza excepción con rango null")
	public void testCrearJugadorRangoNull() {
		assertThrows(JugadorException.class, () -> {
			JugadorEquipoFactory.crearJugador("Jugador", null);
		});
	}

	@Test
	@DisplayName("Factory crea Equipo válido")
	public void testCrearEquipo() {
		Equipo equipo = JugadorEquipoFactory.crearEquipo("Real Madrid");
		assertNotNull(equipo);
		assertEquals("Real Madrid", equipo.getNombre());
		assertEquals(0, equipo.getCantidadJugadores());
	}

	@Test
	@DisplayName("Factory crea Equipo con jugadores")
	public void testCrearEquipoConJugadores() {
		Jugador j1 = JugadorEquipoFactory.crearJugadorElite("Cristiano7");
		Jugador j2 = JugadorEquipoFactory.crearJugadorElite("Modric10");

		Equipo equipo = JugadorEquipoFactory.crearEquipoConJugadores("Real Madrid", j1, j2);

		assertEquals("Real Madrid", equipo.getNombre());
		assertEquals(2, equipo.getCantidadJugadores());
		assertTrue(equipo.getJugadores().contains(j1));
		assertTrue(equipo.getJugadores().contains(j2));
	}

	@Test
	@DisplayName("Factory lanza excepción con nombre de equipo vacío")
	public void testCrearEquipoNombreVacio() {
		assertThrows(EquipoException.class, () -> {
			JugadorEquipoFactory.crearEquipo("   ");
		});
	}

	@Test
	@DisplayName("Factory crea múltiples instancias independientes")
	public void testMultiplesInstanciasIndependientes() {
		Jugador j1 = JugadorEquipoFactory.crearJugadorElite("Cristiano7");
		Jugador j2 = JugadorEquipoFactory.crearJugadorElite("Messi10");

		assertNotSame(j1, j2);
		assertNotEquals(j1.getNickname(), j2.getNickname());
	}
}
