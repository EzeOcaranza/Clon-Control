package Ejercicio1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import Ejercicio1.exceptions.JugadorException;

/**
 * Tests unitarios para la clase Jugador.
 * Cubre constructores, getters, setters y excepciones personalizadas.
 */
@DisplayName("Pruebas de Jugador")
public class JugadorTest {

	private Jugador jugador;

	@BeforeEach
	public void setUp() {
		jugador = new Jugador("Cristiano7", "Elite");
	}

	@Test
	@DisplayName("Crear jugador con valores válidos")
	public void testCrearJugadorValido() {
		assertNotNull(jugador);
		assertEquals("Cristiano7", jugador.getNickname());
		assertEquals("Elite", jugador.getRango());
	}

	@Test
	@DisplayName("Lanzar excepción cuando nickname es null")
	public void testCrearJugadorNickNameNull() {
		assertThrows(JugadorException.class, () -> {
			new Jugador(null, "Elite");
		});
	}

	@Test
	@DisplayName("Lanzar excepción cuando nickname es vacío")
	public void testCrearJugadorNickNameVacio() {
		assertThrows(JugadorException.class, () -> {
			new Jugador("", "Elite");
		});
	}

	@Test
	@DisplayName("Lanzar excepción cuando rango es null")
	public void testCrearJugadorRangoNull() {
		assertThrows(JugadorException.class, () -> {
			new Jugador("Cristiano7", null);
		});
	}

	@Test
	@DisplayName("Lanzar excepción cuando rango es vacío")
	public void testCrearJugadorRangoVacio() {
		assertThrows(JugadorException.class, () -> {
			new Jugador("Cristiano7", "");
		});
	}

	@Test
	@DisplayName("Obtener nickname correctamente")
	public void testGetNickname() {
		assertEquals("Cristiano7", jugador.getNickname());
	}

	@Test
	@DisplayName("Obtener rango correctamente")
	public void testGetRango() {
		assertEquals("Elite", jugador.getRango());
	}

	@Test
	@DisplayName("Establecer nickname válido")
	public void testSetNicknameValido() {
		jugador.setNickname("Messi10");
		assertEquals("Messi10", jugador.getNickname());
	}

	@Test
	@DisplayName("Lanzar excepción al establecer nickname null")
	public void testSetNicknameNull() {
		assertThrows(JugadorException.class, () -> {
			jugador.setNickname(null);
		});
	}

	@Test
	@DisplayName("Lanzar excepción al establecer nickname vacío")
	public void testSetNicknameVacio() {
		assertThrows(JugadorException.class, () -> {
			jugador.setNickname("   ");
		});
	}

	@Test
	@DisplayName("Establecer rango válido")
	public void testSetRangoValido() {
		jugador.setRango("Profesional");
		assertEquals("Profesional", jugador.getRango());
	}

	@Test
	@DisplayName("Lanzar excepción al establecer rango null")
	public void testSetRangoNull() {
		assertThrows(JugadorException.class, () -> {
			jugador.setRango(null);
		});
	}

	@Test
	@DisplayName("Lanzar excepción al establecer rango vacío")
	public void testSetRangoVacio() {
		assertThrows(JugadorException.class, () -> {
			jugador.setRango("");
		});
	}

	@Test
	@DisplayName("toString retorna formato correcto")
	public void testToString() {
		String resultado = jugador.toString();
		assertTrue(resultado.contains("Cristiano7"));
		assertTrue(resultado.contains("Elite"));
	}
}
