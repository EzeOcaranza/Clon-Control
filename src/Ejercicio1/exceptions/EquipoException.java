package Ejercicio1.exceptions;

/**
 * Excepción personalizada para errores relacionados con equipos.
 */
public class EquipoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EquipoException(String mensaje) {
		super(mensaje);
	}

	public EquipoException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
}
