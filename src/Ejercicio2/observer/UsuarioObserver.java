package Ejercicio2.observer;

import Ejercicio2.Mensaje;

/**
 * Interfaz Observer para ser notificado de cambios en usuarios.
 */
public interface UsuarioObserver {
	/**
	 * Se llama cuando se publica un nuevo mensaje.
	 * @param mensaje publicado
	 */
	void onMensajePublicado(Mensaje mensaje);

	/**
	 * Se llama cuando se elimina un mensaje.
	 * @param mensaje eliminado
	 */
	void onMensajeEliminado(Mensaje mensaje);
}
