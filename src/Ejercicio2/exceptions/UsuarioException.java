package Ejercicio2.exceptions;

/**
 * Excepción personalizada para errores relacionados con usuarios.
 */
public class UsuarioException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UsuarioException(String mensaje) {
		super(mensaje);
	}

	public UsuarioException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
}
