package Ejercicio1;

/**
 * Interfaz que define operaciones de escritura para el equipo.
 * Segregación de interfaz para Single Responsibility.
 */
public interface IEquipoEscritor {
	void setNombre(String nombre);
	void agregarJugador(Jugador jugador);
	boolean eliminarJugador(Jugador jugador);
}
