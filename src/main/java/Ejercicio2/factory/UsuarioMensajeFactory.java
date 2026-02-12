package Ejercicio2.factory;

import Ejercicio2.Usuario;
import Ejercicio2.Mensaje;
import Ejercicio2.builder.UsuarioBuilder;
import Ejercicio2.builder.MensajeBuilder;
import Ejercicio2.exceptions.UsuarioException;
import Ejercicio2.exceptions.MensajeException;

/**
 * Factory para crear instancias de Usuario y Mensaje.
 * Implementa Abstract Factory Pattern para creación de objetos.
 */
public class UsuarioMensajeFactory {

	/**
	 * Crea un usuario con validación básica.
	 * @param nombre del usuario
	 * @param apellidos del usuario
	 * @param nickName del usuario
	 * @return nueva instancia de Usuario
	 */
	public static Usuario crearUsuario(String nombre, String apellidos, String nickName) {
		return crearUsuario(nombre, apellidos, null, nickName);
	}

	/**
	 * Crea un usuario con todos los parámetros.
	 * @param nombre del usuario
	 * @param apellidos del usuario
	 * @param fechaNacimiento del usuario
	 * @param nickName del usuario
	 * @return nueva instancia de Usuario
	 */
	public static Usuario crearUsuario(String nombre, String apellidos, String fechaNacimiento, String nickName) {
		try {
			return new UsuarioBuilder()
				.conNombre(nombre)
				.conApellidos(apellidos)
				.conFechaNacimiento(fechaNacimiento)
				.conNickName(nickName)
				.build();
		} catch (UsuarioException e) {
			throw new UsuarioException("Error al crear usuario: " + e.getMessage(), e);
		}
	}

	/**
	 * Crea un mensaje con validación básica.
	 * @param texto del mensaje
	 * @param fecha de publicación
	 * @param autor del mensaje
	 * @return nueva instancia de Mensaje
	 */
	public static Mensaje crearMensaje(String texto, String fecha, String autor) {
		return crearMensaje(texto, fecha, autor, null, null);
	}

	/**
	 * Crea un mensaje con todos los parámetros.
	 * @param texto del mensaje
	 * @param fecha de publicación
	 * @param autor del mensaje
	 * @param etiquetas del mensaje
	 * @param imagen del mensaje
	 * @return nueva instancia de Mensaje
	 */
	public static Mensaje crearMensaje(String texto, String fecha, String autor, String etiquetas, String imagen) {
		try {
			return new MensajeBuilder()
				.conTexto(texto)
				.conFechaPublicacion(fecha)
				.conAutor(autor)
				.conEtiquetas(etiquetas)
				.conImagen(imagen)
				.build();
		} catch (MensajeException e) {
			throw new MensajeException("Error al crear mensaje: " + e.getMessage(), e);
		}
	}

	/**
	 * Crea un mensaje simple sin etiquetas ni imagen.
	 * @param texto del mensaje
	 * @param fecha de publicación
	 * @param autor del mensaje
	 * @return nueva instancia de Mensaje
	 */
	public static Mensaje crearMensajeSimple(String texto, String fecha, String autor) {
		return crearMensaje(texto, fecha, autor);
	}

	/**
	 * Crea un mensaje con etiquetas.
	 * @param texto del mensaje
	 * @param fecha de publicación
	 * @param autor del mensaje
	 * @param etiquetas del mensaje
	 * @return nueva instancia de Mensaje
	 */
	public static Mensaje crearMensajeConEtiquetas(String texto, String fecha, String autor, String etiquetas) {
		return crearMensaje(texto, fecha, autor, etiquetas, null);
	}
}
