package api.rest;

import Ejercicio2.Mensaje;
import Ejercicio2.factory.UsuarioMensajeFactory;
import config.AppConfig;
import Ejercicio2.repository.MensajeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller para Mensajes.
 * 
 * Endpoints:
 * GET    /api/mensajes           - Obtener todos los mensajes
 * GET    /api/mensajes/{id}      - Obtener mensaje específico
 * POST   /api/mensajes           - Crear nuevo mensaje
 * PUT    /api/mensajes/{id}      - Actualizar mensaje
 * DELETE /api/mensajes/{id}      - Eliminar mensaje
 */
@RestController
@RequestMapping("/api/mensajes")
@CrossOrigin(origins = "*")
public class MensajeController {

	private MensajeRepository mensajeRepository;

	public MensajeController() {
		this.mensajeRepository = AppConfig.getInstance().getMensajeRepository();
	}

	/**
	 * GET /api/mensajes - Obtener todos los mensajes
	 */
	@GetMapping
	public List<Mensaje> obtenerTodos() {
		return mensajeRepository.obtenerTodos();
	}

	/**
	 * GET /api/mensajes/{id} - Obtener mensaje específico
	 */
	@GetMapping("/{id}")
	public Mensaje obtenerPorId(@PathVariable String id) {
		return mensajeRepository.encontrarPorId(id);
	}

	/**
	 * POST /api/mensajes - Crear nuevo mensaje
	 */
	@PostMapping
	public Mensaje crear(@RequestParam String contenido) {
		Mensaje mensaje = UsuarioMensajeFactory.crearMensajeBasico(contenido);
		mensajeRepository.guardar(mensaje);
		return mensaje;
	}

	/**
	 * POST /api/mensajes/{id}/etiquetas - Agregar etiqueta a mensaje
	 */
	@PostMapping("/{id}/etiquetas")
	public Mensaje agregarEtiqueta(@PathVariable String id, @RequestParam String etiqueta) {
		Mensaje mensaje = mensajeRepository.encontrarPorId(id);
		if (mensaje != null) {
			mensaje.agregarEtiqueta(etiqueta);
			mensajeRepository.actualizar(mensaje);
		}
		return mensaje;
	}

	/**
	 * DELETE /api/mensajes/{id}/etiquetas - Eliminar etiqueta del mensaje
	 */
	@DeleteMapping("/{id}/etiquetas")
	public Mensaje eliminarEtiqueta(@PathVariable String id, @RequestParam String etiqueta) {
		Mensaje mensaje = mensajeRepository.encontrarPorId(id);
		if (mensaje != null) {
			mensaje.eliminarEtiqueta(etiqueta);
			mensajeRepository.actualizar(mensaje);
		}
		return mensaje;
	}

	/**
	 * DELETE /api/mensajes/{id} - Eliminar mensaje
	 */
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable String id) {
		Mensaje mensaje = mensajeRepository.encontrarPorId(id);
		if (mensaje != null) {
			mensajeRepository.eliminar(mensaje);
		}
	}

	/**
	 * GET /api/mensajes/count - Obtener cantidad de mensajes
	 */
	@GetMapping("/count")
	public int contar() {
		return mensajeRepository.contar();
	}
}
