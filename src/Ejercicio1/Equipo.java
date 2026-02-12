package Ejercicio1;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * Clase que representa un equipo de jugadores.
 * Gestiona la adición y visualización de jugadores.
 */
public class Equipo {
	private String nombre;
	private List<Jugador> jugadores;

	/**
	 * Constructor de Equipo.
	 * @param nombre nombre del equipo
	 * @throws IllegalArgumentException si nombre es null o vacío
	 */
	public Equipo(String nombre) {
		if (nombre == null || nombre.trim().isEmpty()) {
			throw new IllegalArgumentException("El nombre del equipo no puede estar vacío");
		}
		this.nombre = nombre;
		this.jugadores = new ArrayList<>();
	}

	/**
	 * Obtiene el nombre del equipo.
	 * @return el nombre del equipo
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del equipo.
	 * @param nombre el nuevo nombre
	 * @throws IllegalArgumentException si nombre es null o vacío
	 */
	public void setNombre(String nombre) {
		if (nombre == null || nombre.trim().isEmpty()) {
			throw new IllegalArgumentException("El nombre del equipo no puede estar vacío");
		}
		this.nombre = nombre;
	}

	/**
	 * Obtiene la lista inmutable de jugadores.
	 * @return lista de jugadores
	 */
	public List<Jugador> getJugadores() {
		return Collections.unmodifiableList(jugadores);
	}

	/**
	 * Agrega un jugador al equipo.
	 * @param jugador el jugador a agregar
	 * @throws IllegalArgumentException si jugador es null
	 */
	public void agregarJugador(Jugador jugador) {
		if (jugador == null) {
			throw new IllegalArgumentException("El jugador no puede ser null");
		}
		jugadores.add(jugador);
	}

	/**
	 * Elimina un jugador del equipo.
	 * @param jugador el jugador a eliminar
	 * @return true si se eliminó, false en caso contrario
	 */
	public boolean eliminarJugador(Jugador jugador) {
		return jugadores.remove(jugador);
	}

	/**
	 * Obtiene la cantidad de jugadores en el equipo.
	 * @return cantidad de jugadores
	 */
	public int getCantidadJugadores() {
		return jugadores.size();
	}

	/**
	 * Muestra la información del equipo por consola.
	 */
	public void mostrar() {
		System.out.println(this.toString());
	}

	/**
	 * Representación en string del equipo.
	 * @return información formateada del equipo
	 */
	@Override
	public String toString() {
		return "Equipo: " + nombre + "\nMiembros: " + jugadores;
	}
}
