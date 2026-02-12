package Ejercicio2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import Ejercicio2.exceptions.MensajeException;
import java.util.Set;

/**
 * Tests unitarios para la clase Mensaje.
 * Cubre constructores, gestión de etiquetas y excepciones personalizadas.
 */
@DisplayName("Pruebas de Mensaje")
public class MensajeTest {

	private Mensaje mensaje;

	@BeforeEach
	public void setUp() {
		mensaje = new Mensaje("Contenido del mensaje", "2026-02-12", "juanperez", "etiqueta1", null);
	}

	@Test
	@DisplayName("Crear mensaje con valores válidos")
	public void testCrearMensajeValido() {
		assertNotNull(mensaje);
		assertEquals("Contenido del mensaje", mensaje.getTexto());
		assertEquals("2026-02-12", mensaje.getFechaPublicacion());
		assertEquals("juanperez", mensaje.getAutor());
		assertNull(mensaje.getImagen());
	}

	@Test
	@DisplayName("Lanzar excepción cuando texto es null")
	public void testCrearMensajeTextoNull() {
		assertThrows(MensajeException.class, () -> {
			new Mensaje(null, "2026-02-12", "juanperez", null, null);
		});
	}

	@Test
	@DisplayName("Lanzar excepción cuando fecha es vacía")
	public void testCrearMensajeFechaVacia() {
		assertThrows(MensajeException.class, () -> {
			new Mensaje("Texto", "", "juanperez", null, null);
		});
	}

	@Test
	@DisplayName("Lanzar excepción cuando autor es null")
	public void testCrearMensajeAutorNull() {
		assertThrows(MensajeException.class, () -> {
			new Mensaje("Texto", "2026-02-12", null, null, null);
		});
	}

	@Test
	@DisplayName("Obtener texto correctamente")
	public void testGetTexto() {
		assertEquals("Contenido del mensaje", mensaje.getTexto());
	}

	@Test
	@DisplayName("Establecer texto válido")
	public void testSetTextoValido() {
		mensaje.setTexto("Nuevo contenido");
		assertEquals("Nuevo contenido", mensaje.getTexto());
	}

	@Test
	@DisplayName("Lanzar excepción al establecer texto vacío")
	public void testSetTextoVacio() {
		assertThrows(MensajeException.class, () -> {
			mensaje.setTexto("   ");
		});
	}

	@Test
	@DisplayName("Obtener fecha de publicación")
	public void testGetFechaPublicacion() {
		assertEquals("2026-02-12", mensaje.getFechaPublicacion());
	}

	@Test
	@DisplayName("Establecer fecha de publicación válida")
	public void testSetFechaPublicacionValida() {
		mensaje.setFechaPublicacion("2026-02-13");
		assertEquals("2026-02-13", mensaje.getFechaPublicacion());
	}

	@Test
	@DisplayName("Obtener autor correctamente")
	public void testGetAutor() {
		assertEquals("juanperez", mensaje.getAutor());
	}

	@Test
	@DisplayName("Establecer autor válido")
	public void testSetAutorValido() {
		mensaje.setAutor("carloslopez");
		assertEquals("carloslopez", mensaje.getAutor());
	}

	@Test
	@DisplayName("Agregar una etiqueta")
	public void testAgregarEtiqueta() {
		mensaje.agregarEtiqueta("etiqueta2");
		Set<String> etiquetas = mensaje.getEtiquetas();
		assertTrue(etiquetas.contains("etiqueta2"));
	}

	@Test
	@DisplayName("Agregar múltiples etiquetas")
	public void testAgregarMultiplesEtiquetas() {
		mensaje.agregarEtiqueta("etiqueta2");
		mensaje.agregarEtiqueta("etiqueta3");
		assertEquals(3, mensaje.getCantidadEtiquetas());
	}

	@Test
	@DisplayName("Lanzar excepción al agregar etiqueta vacía")
	public void testAgregarEtiquetaVacia() {
		assertThrows(MensajeException.class, () -> {
			mensaje.agregarEtiqueta("");
		});
	}

	@Test
	@DisplayName("Lanzar excepción al agregar etiqueta null")
	public void testAgregarEtiquetaNull() {
		assertThrows(MensajeException.class, () -> {
			mensaje.agregarEtiqueta(null);
		});
	}

	@Test
	@DisplayName("Eliminar una etiqueta")
	public void testEliminarEtiqueta() {
		mensaje.agregarEtiqueta("etiqueta2");
		boolean eliminada = mensaje.eliminarEtiqueta("etiqueta2");
		assertTrue(eliminada);
		assertFalse(mensaje.getEtiquetas().contains("etiqueta2"));
	}

	@Test
	@DisplayName("Intentar eliminar etiqueta que no existe")
	public void testEliminarEtiquetaNoExistente() {
		boolean eliminada = mensaje.eliminarEtiqueta("etiqueta999");
		assertFalse(eliminada);
	}

	@Test
	@DisplayName("Obtener etiquetas inmutable")
	public void testGetEtiquetasInmutable() {
		assertThrows(UnsupportedOperationException.class, () -> {
			mensaje.getEtiquetas().add("etiqueta999");
		});
	}

	@Test
	@DisplayName("Limpiar todas las etiquetas")
	public void testLimpiarEtiquetas() {
		mensaje.agregarEtiqueta("etiqueta2");
		mensaje.agregarEtiqueta("etiqueta3");
		mensaje.limpiarEtiquetas();
		assertEquals(0, mensaje.getCantidadEtiquetas());
	}

	@Test
	@DisplayName("Obtener cantidad de etiquetas")
	public void testGetCantidadEtiquetas() {
		assertEquals(1, mensaje.getCantidadEtiquetas());
		mensaje.agregarEtiqueta("etiqueta2");
		assertEquals(2, mensaje.getCantidadEtiquetas());
	}

	@Test
	@DisplayName("Obtener imagen")
	public void testGetImagen() {
		assertNull(mensaje.getImagen());
	}

	@Test
	@DisplayName("Establecer imagen")
	public void testSetImagen() {
		mensaje.setImagen("/ruta/imagen.jpg");
		assertEquals("/ruta/imagen.jpg", mensaje.getImagen());
	}

	@Test
	@DisplayName("toString retorna formato correcto")
	public void testToString() {
		String resultado = mensaje.toString();
		assertTrue(resultado.contains("Contenido del mensaje"));
		assertTrue(resultado.contains("juanperez"));
	}
}
