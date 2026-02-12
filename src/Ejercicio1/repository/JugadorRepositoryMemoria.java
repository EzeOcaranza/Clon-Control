package Ejercicio1.repository;

import Ejercicio1.Jugador;
import java.util.*;
import java.util.logging.Logger;

/**
 * Implementación en memoria del repositorio de jugadores.
 * Simula una base de datos usando HashMap.
 */
public class JugadorRepositoryMemoria implements JugadorRepository {
	private static final Logger logger = Logger.getLogger(JugadorRepositoryMemoria.class.getName());
	private Map<String, Jugador> almacen = new HashMap<>();

	@Override
	public void guardar(Jugador jugador) {
		if (jugador != null) {
			almacen.put(jugador.getNickname(), jugador);
			logger.info("Jugador guardado: " + jugador.getNickname());
		}
	}

	@Override
	public Jugador encontrarPorNickname(String nickname) {
		Jugador jugador = almacen.get(nickname);
		if (jugador != null) {
			logger.info("Jugador encontrado: " + nickname);
		} else {
			logger.warning("Jugador no encontrado: " + nickname);
		}
		return jugador;
	}

	@Override
	public List<Jugador> obtenerTodos() {
		logger.info("Obteniendo todos los jugadores: " + almacen.size());
		return new ArrayList<>(almacen.values());
	}

	@Override
	public void eliminar(Jugador jugador) {
		if (jugador != null) {
			almacen.remove(jugador.getNickname());
			logger.info("Jugador eliminado: " + jugador.getNickname());
		}
	}

	@Override
	public void actualizar(Jugador jugador) {
		if (jugador != null && almacen.containsKey(jugador.getNickname())) {
			almacen.put(jugador.getNickname(), jugador);
			logger.info("Jugador actualizado: " + jugador.getNickname());
		}
	}

	@Override
	public int contar() {
		return almacen.size();
	}
}
