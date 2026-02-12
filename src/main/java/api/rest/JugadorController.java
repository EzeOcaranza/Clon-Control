package api.rest;

import Ejercicio1.Jugador;
import Ejercicio1.factory.JugadorEquipoFactory;
import config.AppConfig;
import Ejercicio1.repository.JugadorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller para Jugadores.
 * 
 * Endpoints:
 * GET    /api/jugadores           - Obtener todos los jugadores
 * GET    /api/jugadores/{id}      - Obtener jugador por nickname
 * POST   /api/jugadores           - Crear nuevo jugador
 * PUT    /api/jugadores/{id}      - Actualizar jugador
 * DELETE /api/jugadores/{id}      - Eliminar jugador
 */
@RestController
@RequestMapping("/api/jugadores")
@CrossOrigin(origins = "*")
public class JugadorController {

	private JugadorRepository repository;

	public JugadorController() {
		this.repository = AppConfig.getInstance().getJugadorRepository();
	}

	/**
	 * GET /api/jugadores - Obtener todos los jugadores
	 */
	@GetMapping
	public List<Jugador> obtenerTodos() {
		return repository.obtenerTodos();
	}

	/**
	 * GET /api/jugadores/{nickname} - Obtener jugador específico
	 */
	@GetMapping("/{nickname}")
	public Jugador obtenerPorNickname(@PathVariable String nickname) {
		return repository.encontrarPorNickname(nickname);
	}

	/**
	 * POST /api/jugadores - Crear nuevo jugador
	 */
	@PostMapping
	public Jugador crear(@RequestParam String nickname, @RequestParam String rango) {
		Jugador jugador = JugadorEquipoFactory.crearJugador(nickname, rango);
		repository.guardar(jugador);
		return jugador;
	}

	/**
	 * PUT /api/jugadores/{nickname} - Actualizar jugador
	 */
	@PutMapping("/{nickname}")
	public Jugador actualizar(@PathVariable String nickname, @RequestParam String rango) {
		Jugador jugador = repository.encontrarPorNickname(nickname);
		if (jugador != null) {
			jugador.setRango(rango);
			repository.actualizar(jugador);
		}
		return jugador;
	}

	/**
	 * DELETE /api/jugadores/{nickname} - Eliminar jugador
	 */
	@DeleteMapping("/{nickname}")
	public void eliminar(@PathVariable String nickname) {
		Jugador jugador = repository.encontrarPorNickname(nickname);
		if (jugador != null) {
			repository.eliminar(jugador);
		}
	}

	/**
	 * GET /api/jugadores/count - Obtener cantidad de jugadores
	 */
	@GetMapping("/count")
	public int contar() {
		return repository.contar();
	}
}
