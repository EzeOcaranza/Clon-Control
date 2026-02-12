package Ejercicio1;

import java.util.List;

/**
 * Interfaz que define operaciones de lectura para el equipo.
 * Segregación de interfaz para Single Responsibility.
 */
public interface IEquipoLector {
	String getNombre();
	List<Jugador> getJugadores();
	int getCantidadJugadores();
}
