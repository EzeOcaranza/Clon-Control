package Ejercicio1.repository;

import Ejercicio1.Jugador;
import java.util.List;

/**
 * Interfaz para el repositorio de jugadores.
 * Define operaciones CRUD.
 */
public interface JugadorRepository {
	/**
	 * Guarda un jugador.
	 * @param jugador a guardar
	 */
	void guardar(Jugador jugador);

	/**
	 * Encuentra un jugador por nickname.
	 * @param nickname a buscar
	 * @return jugador encontrado o null
	 */
	Jugador encontrarPorNickname(String nickname);

	/**
	 * Obtiene todos los jugadores.
	 * @return lista de todos los jugadores
	 */
	List<Jugador> obtenerTodos();

	/**
	 * Elimina un jugador.
	 * @param jugador a eliminar
	 */
	void eliminar(Jugador jugador);

	/**
	 * Actualiza un jugador.
	 * @param jugador a actualizar
	 */
	void actualizar(Jugador jugador);

	/**
	 * Obtiene la cantidad de jugadores.
	 * @return cantidad
	 */
	int contar();
}
