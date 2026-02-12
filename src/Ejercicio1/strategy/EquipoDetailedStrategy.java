package Ejercicio1.strategy;

import Ejercicio1.Equipo;

/**
 * Estrategia para mostrar información detallada del equipo.
 */
public class EquipoDetailedStrategy implements EquipoStrategy {

	@Override
	public String procesar(Equipo equipo) {
		StringBuilder sb = new StringBuilder();
		sb.append("=== EQUIPO: ").append(equipo.getNombre()).append(" ===\n");
		sb.append("Total de jugadores: ").append(equipo.getCantidadJugadores()).append("\n");
		sb.append("Jugadores:\n");
		equipo.getJugadores().forEach(j -> 
			sb.append("- ").append(j.getNickname()).append(" (").append(j.getRango()).append(")\n")
		);
		return sb.toString();
	}
}
