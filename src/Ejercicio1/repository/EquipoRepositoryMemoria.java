package Ejercicio1.repository;

import Ejercicio1.Equipo;
import java.util.*;
import java.util.logging.Logger;

/**
 * Implementación en memoria del repositorio de equipos.
 * Simula una base de datos usando HashMap.
 */
public class EquipoRepositoryMemoria implements EquipoRepository {
	private static final Logger logger = Logger.getLogger(EquipoRepositoryMemoria.class.getName());
	private Map<String, Equipo> almacen = new HashMap<>();

	@Override
	public void guardar(Equipo equipo) {
		if (equipo != null) {
			almacen.put(equipo.getNombre(), equipo);
			logger.info("Equipo guardado: " + equipo.getNombre());
		}
	}

	@Override
	public Equipo encontrarPorNombre(String nombre) {
		Equipo equipo = almacen.get(nombre);
		if (equipo != null) {
			logger.info("Equipo encontrado: " + nombre);
		} else {
			logger.warning("Equipo no encontrado: " + nombre);
		}
		return equipo;
	}

	@Override
	public List<Equipo> obtenerTodos() {
		logger.info("Obteniendo todos los equipos: " + almacen.size());
		return new ArrayList<>(almacen.values());
	}

	@Override
	public void eliminar(Equipo equipo) {
		if (equipo != null) {
			almacen.remove(equipo.getNombre());
			logger.info("Equipo eliminado: " + equipo.getNombre());
		}
	}

	@Override
	public void actualizar(Equipo equipo) {
		if (equipo != null && almacen.containsKey(equipo.getNombre())) {
			almacen.put(equipo.getNombre(), equipo);
			logger.info("Equipo actualizado: " + equipo.getNombre());
		}
	}

	@Override
	public int contar() {
		return almacen.size();
	}
}
