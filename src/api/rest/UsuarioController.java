package api.rest;

import Ejercicio2.Mensaje;
import Ejercicio2.Usuario;
import Ejercicio2.factory.UsuarioMensajeFactory;
import config.AppConfig;
import Ejercicio2.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller para Usuarios.
 * 
 * Endpoints:
 * GET    /api/usuarios           - Obtener todos los usuarios
 * GET    /api/usuarios/{id}      - Obtener usuario específico
 * POST   /api/usuarios           - Crear nuevo usuario
 * PUT    /api/usuarios/{id}      - Actualizar usuario
 * DELETE /api/usuarios/{id}      - Eliminar usuario
 */
@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

	private UsuarioRepository usuarioRepository;

	public UsuarioController() {
		this.usuarioRepository = AppConfig.getInstance().getUsuarioRepository();
	}

	/**
	 * GET /api/usuarios - Obtener todos los usuarios
	 */
	@GetMapping
	public List<Usuario> obtenerTodos() {
		return usuarioRepository.obtenerTodos();
	}

	/**
	 * GET /api/usuarios/{nickname} - Obtener usuario específico
	 */
	@GetMapping("/{nickname}")
	public Usuario obtenerPorNickname(@PathVariable String nickname) {
		return usuarioRepository.encontrarPorNickname(nickname);
	}

	/**
	 * POST /api/usuarios - Crear nuevo usuario
	 */
	@PostMapping
	public Usuario crear(@RequestParam String nickname, @RequestParam String email) {
		Usuario usuario = new Usuario(nickname, email);
		usuarioRepository.guardar(usuario);
		return usuario;
	}

	/**
	 * PUT /api/usuarios/{nickname} - Actualizar usuario
	 */
	@PutMapping("/{nickname}")
	public Usuario actualizar(@PathVariable String nickname, @RequestParam String email) {
		Usuario usuario = usuarioRepository.encontrarPorNickname(nickname);
		if (usuario != null) {
			usuario.setEmail(email);
			usuarioRepository.actualizar(usuario);
		}
		return usuario;
	}

	/**
	 * DELETE /api/usuarios/{nickname} - Eliminar usuario
	 */
	@DeleteMapping("/{nickname}")
	public void eliminar(@PathVariable String nickname) {
		Usuario usuario = usuarioRepository.encontrarPorNickname(nickname);
		if (usuario != null) {
			usuarioRepository.eliminar(usuario);
		}
	}

	/**
	 * POST /api/usuarios/{nickname}/mensajes - Publicar mensaje
	 */
	@PostMapping("/{nickname}/mensajes")
	public Usuario publicarMensaje(@PathVariable String nickname, @RequestParam String contenido) {
		Usuario usuario = usuarioRepository.encontrarPorNickname(nickname);
		if (usuario != null) {
			Mensaje mensaje = UsuarioMensajeFactory.crearMensajeBasico(contenido);
			usuario.publicarMensaje(mensaje);
			usuarioRepository.actualizar(usuario);
		}
		return usuario;
	}

	/**
	 * GET /api/usuarios/{nickname}/mensajes - Obtener mensajes del usuario
	 */
	@GetMapping("/{nickname}/mensajes")
	public List<Mensaje> obtenerMensajes(@PathVariable String nickname) {
		Usuario usuario = usuarioRepository.encontrarPorNickname(nickname);
		if (usuario != null) {
			return usuario.obtenerMensajes();
		}
		return List.of();
	}

	/**
	 * GET /api/usuarios/count - Obtener cantidad de usuarios
	 */
	@GetMapping("/count")
	public int contar() {
		return usuarioRepository.contar();
	}
}
