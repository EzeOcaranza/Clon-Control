package Ejercicio2.observer;

import Ejercicio2.Mensaje;
import java.util.ArrayList;
import java.util.List;

/**
 * Notificador de eventos de usuario.
 * Implementa el patrón Observer.
 */
public class UsuarioNotificador {
	private List<UsuarioObserver> observadores = new ArrayList<>();

	/**
	 * Registra un observador.
	 * @param observador a registrar
	 */
	public void registrarObservador(UsuarioObserver observador) {
		observadores.add(observador);
	}

	/**
	 * Desregistra un observador.
	 * @param observador a desregistrar
	 */
	public void desregistrarObservador(UsuarioObserver observador) {
		observadores.remove(observador);
	}

	/**
	 * Notifica a todos los observadores que se publicó un mensaje.
	 * @param mensaje publicado
	 */
	public void notificarPublicacion(Mensaje mensaje) {
		observadores.forEach(o -> o.onMensajePublicado(mensaje));
	}

	/**
	 * Notifica a todos los observadores que se eliminó un mensaje.
	 * @param mensaje eliminado
	 */
	public void notificarEliminacion(Mensaje mensaje) {
		observadores.forEach(o -> o.onMensajeEliminado(mensaje));
	}
}
