package Ejercicio2.observer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Ejercicio2.Mensaje;
import Ejercicio2.factory.UsuarioMensajeFactory;

/**
 * Tests para verificar el Observer Pattern.
 */
@DisplayName("Tests Observer Pattern - Notificaciones")
public class UsuarioObserverTest {

	private UsuarioNotificador notificador;
	
	@Mock
	private UsuarioObserver observador1;
	
	@Mock
	private UsuarioObserver observador2;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		notificador = new UsuarioNotificador();
	}

	@Test
	@DisplayName("Notificador registra observadores")
	public void testRegistrarObservador() {
		notificador.registrarObservador(observador1);
		notificador.registrarObservador(observador2);

		Mensaje mensaje = UsuarioMensajeFactory.crearMensajeSimple(
			"Hola", "2026-02-12", "usuario1"
		);

		notificador.notificarPublicacion(mensaje);

		verify(observador1).onMensajePublicado(mensaje);
		verify(observador2).onMensajePublicado(mensaje);
	}

	@Test
	@DisplayName("Notificador notifica a todos los observadores al publicar")
	public void testNotificarPublicacion() {
		notificador.registrarObservador(observador1);
		notificador.registrarObservador(observador2);

		Mensaje mensaje = UsuarioMensajeFactory.crearMensajeSimple(
			"Mensaje test", "2026-02-12", "autor1"
		);

		notificador.notificarPublicacion(mensaje);

		verify(observador1, times(1)).onMensajePublicado(mensaje);
		verify(observador2, times(1)).onMensajePublicado(mensaje);
	}

	@Test
	@DisplayName("Notificador notifica a todos al eliminar mensaje")
	public void testNotificarEliminacion() {
		notificador.registrarObservador(observador1);
		notificador.registrarObservador(observador2);

		Mensaje mensaje = UsuarioMensajeFactory.crearMensajeSimple(
			"Mensaje a eliminar", "2026-02-12", "autor1"
		);

		notificador.notificarEliminacion(mensaje);

		verify(observador1).onMensajeEliminado(mensaje);
		verify(observador2).onMensajeEliminado(mensaje);
	}

	@Test
	@DisplayName("Desregistrar observador lo deja de notificar")
	public void testDesregistrarObservador() {
		notificador.registrarObservador(observador1);
		notificador.registrarObservador(observador2);
		notificador.desregistrarObservador(observador1);

		Mensaje mensaje = UsuarioMensajeFactory.crearMensajeSimple(
			"Test", "2026-02-12", "usuario"
		);

		notificador.notificarPublicacion(mensaje);

		verify(observador1, never()).onMensajePublicado(mensaje);
		verify(observador2).onMensajePublicado(mensaje);
	}

	@Test
	@DisplayName("Sin observadores no lanza error")
	public void testSinObservadores() {
		Mensaje mensaje = UsuarioMensajeFactory.crearMensajeSimple(
			"Test", "2026-02-12", "usuario"
		);

		assertDoesNotThrow(() -> {
			notificador.notificarPublicacion(mensaje);
			notificador.notificarEliminacion(mensaje);
		});
	}

	@Test
	@DisplayName("LoggingObserver registra publicaciones")
	public void testLoggingObserver() {
		UsuarioObserver loggingObserver = new LoggingObserver();
		
		Mensaje mensaje = UsuarioMensajeFactory.crearMensajeConEtiquetas(
			"Mensaje importante", "2026-02-12", "usuario1", "importante"
		);

		assertDoesNotThrow(() -> {
			loggingObserver.onMensajePublicado(mensaje);
			loggingObserver.onMensajeEliminado(mensaje);
		});
	}

	@Test
	@DisplayName("Observador se puede registrar y desregistrar múltiples veces")
	public void testRegistroMultiple() {
		notificador.registrarObservador(observador1);
		notificador.desregistrarObservador(observador1);
		notificador.registrarObservador(observador1);

		Mensaje mensaje = UsuarioMensajeFactory.crearMensajeSimple(
			"Test", "2026-02-12", "usuario"
		);

		notificador.notificarPublicacion(mensaje);

		verify(observador1, times(1)).onMensajePublicado(mensaje);
	}
}
