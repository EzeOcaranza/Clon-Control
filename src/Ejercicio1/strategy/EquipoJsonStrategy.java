package Ejercicio1.strategy;

import Ejercicio1.Equipo;

/**
 * Estrategia para mostrar información en formato JSON del equipo.
 */
public class EquipoJsonStrategy implements EquipoStrategy {

	@Override
	public String procesar(Equipo equipo) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");
		sb.append("  \"nombre\": \"").append(equipo.getNombre()).append("\",\n");
		sb.append("  \"cantidad_jugadores\": ").append(equipo.getCantidadJugadores()).append(",\n");
		sb.append("  \"jugadores\": [\n");
		
		var jugadores = equipo.getJugadores();
		for (int i = 0; i < jugadores.size(); i++) {
			var j = jugadores.get(i);
			sb.append("    {\n");
			sb.append("      \"nickname\": \"").append(j.getNickname()).append("\",\n");
			sb.append("      \"rango\": \"").append(j.getRango()).append("\"\n");
			sb.append("    }");
			if (i < jugadores.size() - 1) sb.append(",");
			sb.append("\n");
		}
		
		sb.append("  ]\n");
		sb.append("}");
		return sb.toString();
	}
}
