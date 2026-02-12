package Ejercicio1.exceptions;

/**
 * Excepción personalizada para errores relacionados con jugadores.
 */
public class JugadorException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public JugadorException(String mensaje) {
		super(mensaje);
	}

	public JugadorException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
}
