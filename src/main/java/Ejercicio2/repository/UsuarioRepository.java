package Ejercicio2.repository;

import Ejercicio2.Usuario;
import java.util.List;

/**
 * Interfaz para el repositorio de usuarios.
 */
public interface UsuarioRepository {
	void guardar(Usuario usuario);
	Usuario encontrarPorNickName(String nickName);
	List<Usuario> obtenerTodos();
	void eliminar(Usuario usuario);
	void actualizar(Usuario usuario);
	int contar();
}
