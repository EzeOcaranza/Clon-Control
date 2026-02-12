package api.rest;

import Ejercicio1.Equipo;
import Ejercicio1.Jugador;
import Ejercicio1.factory.JugadorEquipoFactory;
import config.AppConfig;
import Ejercicio1.repository.EquipoRepository;
import Ejercicio1.repository.JugadorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller para Equipos.
 * 
 * Endpoints:
 * GET    /api/equipos           - Obtener todos los equipos
 * GET    /api/equipos/{id}      - Obtener equipo por nombre
 * POST   /api/equipos           - Crear nuevo equipo
 * PUT    /api/equipos/{id}      - Actualizar equipo
 * DELETE /api/equipos/{id}      - Eliminar equipo
 */
@RestController
@RequestMapping("/api/equipos")
@CrossOrigin(origins = "*")
public class EquipoController {

	private EquipoRepository equipoRepository;
	private JugadorRepository jugadorRepository;

	public EquipoController() {
		this.equipoRepository = AppConfig.getInstance().getEquipoRepository();
		this.jugadorRepository = AppConfig.getInstance().getJugadorRepository();
	}

	/**
	 * GET /api/equipos - Obtener todos los equipos
	 */
	@GetMapping
	public List<Equipo> obtenerTodos() {
		return equipoRepository.obtenerTodos();
	}

	/**
	 * GET /api/equipos/{nombre} - Obtener equipo específico
	 */
	@GetMapping("/{nombre}")
	public Equipo obtenerPorNombre(@PathVariable String nombre) {
		return equipoRepository.encontrarPorNombre(nombre);
	}

	/**
	 * POST /api/equipos - Crear nuevo equipo
	 */
	@PostMapping
	public Equipo crear(@RequestParam String nombre) {
		Equipo equipo = JugadorEquipoFactory.crearEquipo(nombre);
		equipoRepository.guardar(equipo);
		return equipo;
	}

	/**
	 * POST /api/equipos/{nombre}/jugadores - Agregar jugador al equipo
	 */
	@PostMapping("/{nombre}/jugadores")
	public Equipo agregarJugador(@PathVariable String nombre, @RequestParam String nicknamejugador) {
		Equipo equipo = equipoRepository.encontrarPorNombre(nombre);
		Jugador jugador = jugadorRepository.encontrarPorNickname(nicknamejugador);

		if (equipo != null && jugador != null) {
			equipo.agregarJugador(jugador);
			equipoRepository.actualizar(equipo);
		}
		return equipo;
	}

	/**
	 * DELETE /api/equipos/{nombre} - Eliminar equipo
	 */
	@DeleteMapping("/{nombre}")
	public void eliminar(@PathVariable String nombre) {
		Equipo equipo = equipoRepository.encontrarPorNombre(nombre);
		if (equipo != null) {
			equipoRepository.eliminar(equipo);
		}
	}

	/**
	 * GET /api/equipos/count - Obtener cantidad de equipos
	 */
	@GetMapping("/count")
	public int contar() {
		return equipoRepository.contar();
	}
}
