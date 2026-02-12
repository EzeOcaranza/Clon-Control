package Ejercicio2.observer;

import Ejercicio2.Mensaje;
import java.util.logging.Logger;

/**
 * Observador que registra en log los eventos de mensajes.
 */
public class LoggingObserver implements UsuarioObserver {
	private static final Logger logger = Logger.getLogger(LoggingObserver.class.getName());

	@Override
	public void onMensajePublicado(Mensaje mensaje) {
		logger.info("PUBLICADO: Mensaje por " + mensaje.getAutor() + 
			" en " + mensaje.getFechaPublicacion() + 
			" - Etiquetas: " + mensaje.getEtiquetas());
	}

	@Override
	public void onMensajeEliminado(Mensaje mensaje) {
		logger.info("ELIMINADO: Mensaje por " + mensaje.getAutor() + 
			" - Texto: " + mensaje.getTexto());
	}
}
