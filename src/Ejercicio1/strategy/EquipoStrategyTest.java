package Ejercicio1.strategy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Ejercicio1.Equipo;
import Ejercicio1.Jugador;
import Ejercicio1.factory.JugadorEquipoFactory;

/**
 * Tests para verificar el Strategy Pattern.
 */
@DisplayName("Tests Strategy Pattern - Presentación de Equipo")
public class EquipoStrategyTest {

	private Equipo equipo;
	private EquipoStrategy summaryStrategy;
	private EquipoStrategy detailedStrategy;
	private EquipoStrategy jsonStrategy;

	@BeforeEach
	public void setUp() {
		equipo = JugadorEquipoFactory.crearEquipoConJugadores(
			"Barcelona",
			JugadorEquipoFactory.crearJugadorElite("Pedri"),
			JugadorEquipoFactory.crearJugadorElite("Gavi")
		);

		summaryStrategy = new EquipoSummaryStrategy();
		detailedStrategy = new EquipoDetailedStrategy();
		jsonStrategy = new EquipoJsonStrategy();
	}

	@Test
	@DisplayName("Strategy Summary genera formato resumido")
	public void testSummaryStrategy() {
		String resultado = summaryStrategy.procesar(equipo);
		
		assertTrue(resultado.contains("Barcelona"));
		assertTrue(resultado.contains("2"));
		assertTrue(resultado.contains("jugadores"));
	}

	@Test
	@DisplayName("Strategy Detailed genera formato detallado")
	public void testDetailedStrategy() {
		String resultado = detailedStrategy.procesar(equipo);
		
		assertTrue(resultado.contains("Barcelona"));
		assertTrue(resultado.contains("Total de jugadores"));
		assertTrue(resultado.contains("Pedri"));
		assertTrue(resultado.contains("Gavi"));
		assertTrue(resultado.contains("Elite"));
	}

	@Test
	@DisplayName("Strategy JSON genera formato JSON válido")
	public void testJsonStrategy() {
		String resultado = jsonStrategy.procesar(equipo);
		
		assertTrue(resultado.contains("\"nombre\""));
		assertTrue(resultado.contains("Barcelona"));
		assertTrue(resultado.contains("\"cantidad_jugadores\""));
		assertTrue(resultado.contains("\"jugadores\""));
		assertTrue(resultado.contains("{"));
		assertTrue(resultado.contains("}"));
	}

	@Test
	@DisplayName("Diferentes estrategias producen diferentes salidas")
	public void testDiferentesEstrategias() {
		String summary = summaryStrategy.procesar(equipo);
		String detailed = detailedStrategy.procesar(equipo);
		String json = jsonStrategy.procesar(equipo);

		assertNotEquals(summary, detailed);
		assertNotEquals(summary, json);
		assertNotEquals(detailed, json);
	}

	@Test
	@DisplayName("Strategy Summary es más corta que Detailed")
	public void testSummaryMasCorta() {
		String summary = summaryStrategy.procesar(equipo);
		String detailed = detailedStrategy.procesar(equipo);

		assertTrue(summary.length() < detailed.length());
	}

	@Test
	@DisplayName("Estrategias intercambiables con misma interfaz")
	public void testIntercambiabilidad() {
		EquipoStrategy[] estrategias = {
			summaryStrategy,
			detailedStrategy,
			jsonStrategy
		};

		for (EquipoStrategy estrategia : estrategias) {
			String resultado = estrategia.procesar(equipo);
			assertNotNull(resultado);
			assertFalse(resultado.isEmpty());
		}
	}

	@Test
	@DisplayName("Equipo vacío se procesa correctamente")
	public void testEquipoVacio() {
		Equipo equipoVacio = JugadorEquipoFactory.crearEquipo("Equipo Vacío");

		String summary = summaryStrategy.procesar(equipoVacio);
		String detailed = detailedStrategy.procesar(equipoVacio);
		String json = jsonStrategy.procesar(equipoVacio);

		assertTrue(summary.contains("0"));
		assertTrue(detailed.contains("0"));
		assertTrue(json.contains("0"));
	}
}
