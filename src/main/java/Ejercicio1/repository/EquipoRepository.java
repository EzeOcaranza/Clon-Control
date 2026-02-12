package Ejercicio1.repository;

import Ejercicio1.Equipo;
import java.util.List;

/**
 * Interfaz para el repositorio de equipos.
 * Define operaciones CRUD.
 */
public interface EquipoRepository {
	/**
	 * Guarda un equipo.
	 * @param equipo a guardar
	 */
	void guardar(Equipo equipo);

	/**
	 * Encuentra un equipo por nombre.
	 * @param nombre a buscar
	 * @return equipo encontrado o null
	 */
	Equipo encontrarPorNombre(String nombre);

	/**
	 * Obtiene todos los equipos.
	 * @return lista de todos los equipos
	 */
	List<Equipo> obtenerTodos();

	/**
	 * Elimina un equipo.
	 * @param equipo a eliminar
	 */
	void eliminar(Equipo equipo);

	/**
	 * Actualiza un equipo.
	 * @param equipo a actualizar
	 */
	void actualizar(Equipo equipo);

	/**
	 * Obtiene la cantidad de equipos.
	 * @return cantidad
	 */
	int contar();
}
