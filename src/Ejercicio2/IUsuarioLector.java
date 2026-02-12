package Ejercicio2;

import java.util.List;

/**
 * Interfaz que define operaciones de lectura para el usuario.
 * Segregación de interfaz para Single Responsibility.
 */
public interface IUsuarioLector {
	String getNombre();
	String getApellidos();
	String getFechaNacimiento();
	String getNickName();
	List<Mensaje> getMensajes();
	int getCantidadMensajes();
}
