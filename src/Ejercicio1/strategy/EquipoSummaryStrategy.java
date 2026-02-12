package Ejercicio1.strategy;

import Ejercicio1.Equipo;

/**
 * Estrategia para mostrar información resumida del equipo.
 */
public class EquipoSummaryStrategy implements EquipoStrategy {

	@Override
	public String procesar(Equipo equipo) {
		return String.format("Equipo '%s' con %d jugadores", 
			equipo.getNombre(), 
			equipo.getCantidadJugadores());
	}
}
