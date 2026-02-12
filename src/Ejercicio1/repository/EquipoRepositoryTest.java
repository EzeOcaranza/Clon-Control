package Ejercicio1.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Ejercicio1.Equipo;
import Ejercicio1.factory.JugadorEquipoFactory;

/**
 * Tests para verificar el Repository Pattern de Equipo.
 */
@DisplayName("Tests Repository Pattern - Equipo")
public class EquipoRepositoryTest {

	private EquipoRepository repository;
	private Equipo equipo1;
	private Equipo equipo2;

	@BeforeEach
	public void setUp() {
		repository = new EquipoRepositoryMemoria();
		equipo1 = JugadorEquipoFactory.crearEquipoConJugadores(
			"Real Madrid",
			JugadorEquipoFactory.crearJugadorElite("Cristiano7")
		);
		equipo2 = JugadorEquipoFactory.crearEquipoConJugadores(
			"Barcelona",
			JugadorEquipoFactory.crearJugadorElite("Pedri")
		);
	}

	@Test
	@DisplayName("Guardar y recuperar equipo")
	public void testGuardarEquipo() {
		repository.guardar(equipo1);
		Equipo recuperado = repository.encontrarPorNombre("Real Madrid");

		assertNotNull(recuperado);
		assertEquals("Real Madrid", recuperado.getNombre());
		assertEquals(1, recuperado.getCantidadJugadores());
	}

	@Test
	@DisplayName("Obtener todos los equipos")
	public void testObtenerTodos() {
		repository.guardar(equipo1);
		repository.guardar(equipo2);

		assertEquals(2, repository.obtenerTodos().size());
	}

	@Test
	@DisplayName("Buscar equipo no existente retorna null")
	public void testBuscarNoExistente() {
		Equipo resultado = repository.encontrarPorNombre("Equipo Fantasma");
		assertNull(resultado);
	}

	@Test
	@DisplayName("Eliminar equipo")
	public void testEliminarEquipo() {
		repository.guardar(equipo1);
		repository.guardar(equipo2);

		repository.eliminar(equipo1);

		assertNull(repository.encontrarPorNombre("Real Madrid"));
		assertNotNull(repository.encontrarPorNombre("Barcelona"));
	}

	@Test
	@DisplayName("Contar equipos")
	public void testContar() {
		assertEquals(0, repository.contar());
		repository.guardar(equipo1);
		assertEquals(1, repository.contar());
		repository.guardar(equipo2);
		assertEquals(2, repository.contar());
	}
}
