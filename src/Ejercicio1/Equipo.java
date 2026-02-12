package Ejercicio1;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import Ejercicio1.exceptions.EquipoException;

/**
 * Clase que representa un equipo de jugadores.
 * Gestiona la adición y visualización de jugadores.
 * Implementa segregación de interfaz para lectores y escritores.
 */
public class Equipo implements IEquipoLector, IEquipoEscritor {
	private String nombre;
	private List<Jugador> jugadores;

	/**
	 * Constructor de Equipo.
	 * @param nombre nombre del equipo
	 * @throws EquipoException si nombre es null o vacío
	 */
	public Equipo(String nombre) {
		if (nombre == null || nombre.trim().isEmpty()) {
			throw new EquipoException("El nombre del equipo no puede estar vacío");
		}
		this.nombre = nombre;
		this.jugadores = new ArrayList<>();
	}

	/**
	 * Obtiene el nombre del equipo.
	 * @return el nombre del equipo
	 */
	@Override
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del equipo.
	 * @param nombre el nuevo nombre
	 * @throws EquipoException si nombre es null o vacío
	 */
	@Override
	public void setNombre(String nombre) {
		if (nombre == null || nombre.trim().isEmpty()) {
			throw new EquipoException("El nombre del equipo no puede estar vacío");
		}
		this.nombre = nombre;
	}

	/**
	 * Obtiene la lista inmutable de jugadores.
	 * @return lista de jugadores
	 */
	@Override
	public List<Jugador> getJugadores() {
		return Collections.unmodifiableList(jugadores);
	}

	/**
	 * Agrega un jugador al equipo.
	 * @param jugador el jugador a agregar
	 * @throws EquipoException si jugador es null
	 */
	@Override
	public void agregarJugador(Jugador jugador) {
		if (jugador == null) {
			throw new EquipoException("El jugador no puede ser null");
		}
		jugadores.add(jugador);
	}

	/**
	 * Elimina un jugador del equipo.
	 * @param jugador el jugador a eliminar
	 * @return true si se eliminó, false en caso contrario
	 */
	@Override
	public boolean eliminarJugador(Jugador jugador) {
		return jugadores.remove(jugador);
	}

	/**
	 * Obtiene la cantidad de jugadores en el equipo.
	 * @return cantidad de jugadores
	 */
	@Override
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
