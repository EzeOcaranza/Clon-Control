package Ejercicio2.repository;

import Ejercicio2.Usuario;
import java.util.*;
import java.util.logging.Logger;

/**
 * Implementación en memoria del repositorio de usuarios.
 */
public class UsuarioRepositoryMemoria implements UsuarioRepository {
	private static final Logger logger = Logger.getLogger(UsuarioRepositoryMemoria.class.getName());
	private Map<String, Usuario> almacen = new HashMap<>();

	@Override
	public void guardar(Usuario usuario) {
		if (usuario != null) {
			almacen.put(usuario.getNickName(), usuario);
			logger.info("Usuario guardado: " + usuario.getNickName());
		}
	}

	@Override
	public Usuario encontrarPorNickName(String nickName) {
		Usuario usuario = almacen.get(nickName);
		if (usuario != null) {
			logger.info("Usuario encontrado: " + nickName);
		} else {
			logger.warning("Usuario no encontrado: " + nickName);
		}
		return usuario;
	}

	@Override
	public List<Usuario> obtenerTodos() {
		logger.info("Obteniendo todos los usuarios: " + almacen.size());
		return new ArrayList<>(almacen.values());
	}

	@Override
	public void eliminar(Usuario usuario) {
		if (usuario != null) {
			almacen.remove(usuario.getNickName());
			logger.info("Usuario eliminado: " + usuario.getNickName());
		}
	}

	@Override
	public void actualizar(Usuario usuario) {
		if (usuario != null && almacen.containsKey(usuario.getNickName())) {
			almacen.put(usuario.getNickName(), usuario);
			logger.info("Usuario actualizado: " + usuario.getNickName());
		}
	}

	@Override
	public int contar() {
		return almacen.size();
	}
}
