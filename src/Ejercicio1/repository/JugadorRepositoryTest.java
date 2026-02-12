package Ejercicio1.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Ejercicio1.Jugador;
import Ejercicio1.Equipo;
import Ejercicio1.factory.JugadorEquipoFactory;

/**
 * Tests para verificar el Repository Pattern de Jugador y Equipo.
 */
@DisplayName("Tests Repository Pattern - Jugador")
public class JugadorRepositoryTest {

	private JugadorRepository repository;
	private Jugador jugador1;
	private Jugador jugador2;

	@BeforeEach
	public void setUp() {
		repository = new JugadorRepositoryMemoria();
		jugador1 = JugadorEquipoFactory.crearJugadorElite("Cristiano7");
		jugador2 = JugadorEquipoFactory.crearJugadorProfesional("Neymar");
	}

	@Test
	@DisplayName("Guardar y recuperar jugador")
	public void testGuardarJugador() {
		repository.guardar(jugador1);
		Jugador recuperado = repository.encontrarPorNickname("Cristiano7");

		assertNotNull(recuperado);
		assertEquals("Cristiano7", recuperado.getNickname());
		assertEquals("Elite", recuperado.getRango());
	}

	@Test
	@DisplayName("Obtener todos los jugadores")
	public void testObtenerTodos() {
		repository.guardar(jugador1);
		repository.guardar(jugador2);

		assertEquals(2, repository.obtenerTodos().size());
		assertTrue(repository.obtenerTodos().contains(jugador1));
		assertTrue(repository.obtenerTodos().contains(jugador2));
	}

	@Test
	@DisplayName("Buscar jugador no existente retorna null")
	public void testBuscarNoExistente() {
		Jugador resultado = repository.encontrarPorNickname("NoExiste");
		assertNull(resultado);
	}

	@Test
	@DisplayName("Actualizar jugador")
	public void testActualizarJugador() {
		repository.guardar(jugador1);
		jugador1.setRango("Profesional");
		repository.actualizar(jugador1);

		Jugador actualizado = repository.encontrarPorNickname("Cristiano7");
		assertEquals("Profesional", actualizado.getRango());
	}

	@Test
	@DisplayName("Eliminar jugador")
	public void testEliminarJugador() {
		repository.guardar(jugador1);
		repository.guardar(jugador2);

		repository.eliminar(jugador1);

		assertNull(repository.encontrarPorNickname("Cristiano7"));
		assertNotNull(repository.encontrarPorNickname("Neymar"));
		assertEquals(1, repository.contar());
	}

	@Test
	@DisplayName("Contar jugadores")
	public void testContar() {
		assertEquals(0, repository.contar());

		repository.guardar(jugador1);
		assertEquals(1, repository.contar());

		repository.guardar(jugador2);
		assertEquals(2, repository.contar());
	}

	@Test
	@DisplayName("Reemplazar jugador con mismo nickname")
	public void testReemplazarJugador() {
		repository.guardar(jugador1);
		Jugador jugador1Modificado = JugadorEquipoFactory.crearJugadorBasico("Cristiano7");
		
		repository.guardar(jugador1Modificado);

		assertEquals(1, repository.contar());
		Jugador resultado = repository.encontrarPorNickname("Cristiano7");
		assertEquals("Básico", resultado.getRango());
	}
}
