package Ejercicio1.builder;

import Ejercicio1.Equipo;
import Ejercicio1.Jugador;
import Ejercicio1.exceptions.EquipoException;
import java.util.ArrayList;
import java.util.List;

/**
 * Builder para la clase Equipo implementando el patrón Builder.
 * Permite crear instancias de Equipo de forma fluida y flexible.
 */
public class EquipoBuilder {
	private String nombre;
	private List<Jugador> jugadores;

	/**
	 * Establece el nombre del equipo.
	 * @param nombre el nombre a asignar
	 * @return la instancia del builder para encadenamiento
	 */
	public EquipoBuilder conNombre(String nombre) {
		if (nombre == null || nombre.trim().isEmpty()) {
			throw new EquipoException("El nombre del equipo no puede estar vacío");
		}
		this.nombre = nombre;
		return this;
	}

	/**
	 * Agrega un jugador al equipo durante la construcción.
	 * @param jugador el jugador a agregar
	 * @return la instancia del builder para encadenamiento
	 */
	public EquipoBuilder agregarJugador(Jugador jugador) {
		if (jugador == null) {
			throw new EquipoException("El jugador no puede ser null");
		}
		if (jugadores == null) {
			jugadores = new ArrayList<>();
		}
		jugadores.add(jugador);
		return this;
	}

	/**
	 * Agrega múltiples jugadores al equipo durante la construcción.
	 * @param listaJugadores la lista de jugadores a agregar
	 * @return la instancia del builder para encadenamiento
	 */
	public EquipoBuilder agregarJugadores(List<Jugador> listaJugadores) {
		if (listaJugadores == null || listaJugadores.isEmpty()) {
			throw new EquipoException("La lista de jugadores no puede ser null o vacía");
		}
		if (jugadores == null) {
			jugadores = new ArrayList<>();
		}
		jugadores.addAll(listaJugadores);
		return this;
	}

	/**
	 * Construye la instancia de Equipo.
	 * @return nueva instancia de Equipo
	 * @throws EquipoException si el nombre es obligatorio
	 */
	public Equipo build() {
		if (nombre == null) {
			throw new EquipoException("El nombre del equipo es obligatorio");
		}
		Equipo equipo = new Equipo(nombre);
		if (jugadores != null) {
			for (Jugador j : jugadores) {
				equipo.agregarJugador(j);
			}
		}
		return equipo;
	}
}
