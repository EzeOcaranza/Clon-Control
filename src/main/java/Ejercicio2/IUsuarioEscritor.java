package Ejercicio2;

/**
 * Interfaz que define operaciones de escritura para el usuario.
 * Segregación de interfaz para Single Responsibility.
 */
public interface IUsuarioEscritor {
	void setNombre(String nombre);
	void setApellidos(String apellidos);
	void setFechaNacimiento(String fechaNacimiento);
	void setNickName(String nickName);
	void publicarMensaje(Mensaje mensaje);
	boolean eliminarMensaje(Mensaje mensaje);
}
