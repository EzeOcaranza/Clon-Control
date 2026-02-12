package Ejercicio2.exceptions;

/**
 * Excepción personalizada para errores relacionados con mensajes.
 */
public class MensajeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MensajeException(String mensaje) {
		super(mensaje);
	}

	public MensajeException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
}
