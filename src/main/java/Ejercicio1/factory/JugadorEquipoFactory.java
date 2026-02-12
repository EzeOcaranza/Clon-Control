package Ejercicio1.factory;

import Ejercicio1.Jugador;
import Ejercicio1.Equipo;
import Ejercicio1.builder.JugadorBuilder;
import Ejercicio1.builder.EquipoBuilder;
import Ejercicio1.exceptions.JugadorException;
import Ejercicio1.exceptions.EquipoException;

/**
 * Factory para crear instancias de Jugador y Equipo.
 * Implementa Abstract Factory Pattern para creación de objetos.
 */
public class JugadorEquipoFactory {

	/**
	 * Crea un jugador con validación básica.
	 * @param nickname del jugador
	 * @param rango del jugador
	 * @return nueva instancia de Jugador
	 */
	public static Jugador crearJugador(String nickname, String rango) {
		try {
			return new JugadorBuilder()
				.conNickname(nickname)
				.conRango(rango)
				.build();
		} catch (JugadorException e) {
			throw new JugadorException("Error al crear jugador: " + e.getMessage(), e);
		}
	}

	/**
	 * Crea un jugador de tipo elite.
	 * @param nickname del jugador
	 * @return nueva instancia de Jugador con rango Elite
	 */
	public static Jugador crearJugadorElite(String nickname) {
		return crearJugador(nickname, "Elite");
	}

	/**
	 * Crea un jugador de tipo profesional.
	 * @param nickname del jugador
	 * @return nueva instancia de Jugador con rango Profesional
	 */
	public static Jugador crearJugadorProfesional(String nickname) {
		return crearJugador(nickname, "Profesional");
	}

	/**
	 * Crea un jugador de tipo básico.
	 * @param nickname del jugador
	 * @return nueva instancia de Jugador con rango Básico
	 */
	public static Jugador crearJugadorBasico(String nickname) {
		return crearJugador(nickname, "Básico");
	}

	/**
	 * Crea un equipo con nombre.
	 * @param nombre del equipo
	 * @return nueva instancia de Equipo
	 */
	public static Equipo crearEquipo(String nombre) {
		try {
			return new EquipoBuilder()
				.conNombre(nombre)
				.build();
		} catch (EquipoException e) {
			throw new EquipoException("Error al crear equipo: " + e.getMessage(), e);
		}
	}

	/**
	 * Crea un equipo con jugadores predefinidos.
	 * @param nombre del equipo
	 * @param jugadores variable list de jugadores
	 * @return nueva instancia de Equipo
	 */
	public static Equipo crearEquipoConJugadores(String nombre, Jugador... jugadores) {
		Equipo equipo = crearEquipo(nombre);
		for (Jugador j : jugadores) {
			equipo.agregarJugador(j);
		}
		return equipo;
	}
}
