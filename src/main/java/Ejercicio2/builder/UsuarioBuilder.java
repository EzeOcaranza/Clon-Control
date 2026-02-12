package Ejercicio2.builder;

import Ejercicio2.Usuario;
import Ejercicio2.exceptions.UsuarioException;

/**
 * Builder para la clase Usuario implementando el patrón Builder.
 * Permite crear instancias de Usuario de forma fluida y flexible.
 */
public class UsuarioBuilder {
	private String nombre;
	private String apellidos;
	private String fechaNacimiento;
	private String nickName;

	/**
	 * Establece el nombre del usuario.
	 * @param nombre el nombre a asignar
	 * @return la instancia del builder para encadenamiento
	 */
	public UsuarioBuilder conNombre(String nombre) {
		if (nombre == null || nombre.trim().isEmpty()) {
			throw new UsuarioException("El nombre no puede estar vacío");
		}
		this.nombre = nombre;
		return this;
	}

	/**
	 * Establece los apellidos del usuario.
	 * @param apellidos los apellidos a asignar
	 * @return la instancia del builder para encadenamiento
	 */
	public UsuarioBuilder conApellidos(String apellidos) {
		if (apellidos == null || apellidos.trim().isEmpty()) {
			throw new UsuarioException("Los apellidos no pueden estar vacíos");
		}
		this.apellidos = apellidos;
		return this;
	}

	/**
	 * Establece la fecha de nacimiento del usuario.
	 * @param fechaNacimiento la fecha a asignar
	 * @return la instancia del builder para encadenamiento
	 */
	public UsuarioBuilder conFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
		return this;
	}

	/**
	 * Establece el nickname del usuario.
	 * @param nickName el nickname a asignar
	 * @return la instancia del builder para encadenamiento
	 */
	public UsuarioBuilder conNickName(String nickName) {
		if (nickName == null || nickName.trim().isEmpty()) {
			throw new UsuarioException("El nickName no puede estar vacío");
		}
		this.nickName = nickName;
		return this;
	}

	/**
	 * Construye la instancia de Usuario.
	 * @return nueva instancia de Usuario
	 * @throws UsuarioException si faltan parámetros obligatorios
	 */
	public Usuario build() {
		if (nombre == null || apellidos == null || nickName == null) {
			throw new UsuarioException("Nombre, apellidos y nickName son obligatorios");
		}
		return new Usuario(nombre, apellidos, fechaNacimiento, nickName);
	}
}
