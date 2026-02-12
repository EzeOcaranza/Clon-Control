package Ejercicio2;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import Ejercicio2.exceptions.UsuarioException;

/**
 * Clase que representa un usuario del sistema.
 * Gestiona la información del usuario y sus mensajes publicados.
 * Implementa segregación de interfaz para lectores y escritores.
 */
public class Usuario implements IUsuarioLector, IUsuarioEscritor {
	private String nombre;
	private String apellidos;
	private String fechaNacimiento;
	private String nickName;
	private List<Mensaje> mensajes;

	/**
	 * Constructor de Usuario con validación.
	 * @param nombre nombre del usuario
	 * @param apellidos apellidos del usuario
	 * @param fechaNacimiento fecha de nacimiento
	 * @param nickName nombre único del usuario
	 * @throws UsuarioException si algún parámetro es null o vacío
	 */
	public Usuario(String nombre, String apellidos, String fechaNacimiento, String nickName) {
		if (nombre == null || nombre.trim().isEmpty()) {
			throw new UsuarioException("El nombre no puede estar vacío");
		}
		if (apellidos == null || apellidos.trim().isEmpty()) {
			throw new UsuarioException("Los apellidos no pueden estar vacíos");
		}
		if (nickName == null || nickName.trim().isEmpty()) {
			throw new UsuarioException("El nickName no puede estar vacío");
		}
		
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.nickName = nickName;
		this.mensajes = new ArrayList<>();
	}

	/**
	 * Obtiene el nombre del usuario.
	 * @return el nombre
	 */
	@Override
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del usuario.
	 * @param nombre el nuevo nombre
	 */
	@Override
	public void setNombre(String nombre) {
		if (nombre == null || nombre.trim().isEmpty()) {
			throw new UsuarioException("El nombre no puede estar vacío");
		}
		this.nombre = nombre;
	}

	/**
	 * Obtiene los apellidos del usuario.
	 * @return los apellidos
	 */
	@Override
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Establece los apellidos del usuario.
	 * @param apellidos los nuevos apellidos
	 */
	@Override
	public void setApellidos(String apellidos) {
		if (apellidos == null || apellidos.trim().isEmpty()) {
			throw new UsuarioException("Los apellidos no pueden estar vacíos");
		}
		this.apellidos = apellidos;
	}

	/**
	 * Obtiene la fecha de nacimiento del usuario.
	 * @return la fecha de nacimiento
	 */
	@Override
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Establece la fecha de nacimiento del usuario.
	 * @param fechaNacimiento la nueva fecha
	 */
	@Override
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * Obtiene el nickName del usuario.
	 * @return el nickName
	 */
	@Override
	public String getNickName() {
		return nickName;
	}

	/**
	 * Establece el nickName del usuario.
	 * @param nickName el nuevo nickName
	 */
	@Override
	public void setNickName(String nickName) {
		if (nickName == null || nickName.trim().isEmpty()) {
			throw new UsuarioException("El nickName no puede estar vacío");
		}
		this.nickName = nickName;
	}

	/**
	 * Obtiene la lista inmutable de mensajes del usuario.
	 * @return lista de mensajes
	 */
	@Override
	public List<Mensaje> getMensajes() {
		return Collections.unmodifiableList(mensajes);
	}

	/**
	 * Publica un nuevo mensaje.
	 * @param mensaje el mensaje a publicar
	 * @throws UsuarioException si mensaje es null
	 */
	@Override
	public void publicarMensaje(Mensaje mensaje) {
		if (mensaje == null) {
			throw new UsuarioException("El mensaje no puede ser null");
		}
		mensajes.add(mensaje);
	}

	/**
	 * Elimina un mensaje publicado.
	 * @param mensaje el mensaje a eliminar
	 * @return true si se eliminó, false en caso contrario
	 */
	@Override
	public boolean eliminarMensaje(Mensaje mensaje) {
		return mensajes.remove(mensaje);
	}

	/**
	 * Obtiene la cantidad de mensajes publicados.
	 * @return cantidad de mensajes
	 */
	@Override
	public int getCantidadMensajes() {
		return mensajes.size();
	}

	/**
	 * Muestra la información del usuario por consola.
	 */
	public void mostrarUsuario() {
		System.out.println(this.toString());
	}

	/**
	 * Representación en string del usuario.
	 * @return información formateada del usuario
	 */
	@Override
	public String toString() {
		return "Usuario: " + nombre + " " + apellidos + 
		       "\nNickname: " + nickName + 
		       "\nFecha de nacimiento: " + fechaNacimiento +
		       "\nMensajes: " + mensajes;
	}
}
