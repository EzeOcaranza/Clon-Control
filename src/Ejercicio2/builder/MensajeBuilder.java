package Ejercicio2.builder;

import Ejercicio2.Mensaje;
import Ejercicio2.exceptions.MensajeException;

/**
 * Builder para la clase Mensaje implementando el patrón Builder.
 * Permite crear instancias de Mensaje de forma fluida y flexible.
 */
public class MensajeBuilder {
	private String texto;
	private String fechaPublicacion;
	private String autor;
	private String etiquetas;
	private String imagen;

	/**
	 * Establece el texto del mensaje.
	 * @param texto el texto a asignar
	 * @return la instancia del builder para encadenamiento
	 */
	public MensajeBuilder conTexto(String texto) {
		if (texto == null || texto.trim().isEmpty()) {
			throw new MensajeException("El texto del mensaje no puede estar vacío");
		}
		this.texto = texto;
		return this;
	}

	/**
	 * Establece la fecha de publicación del mensaje.
	 * @param fechaPublicacion la fecha a asignar
	 * @return la instancia del builder para encadenamiento
	 */
	public MensajeBuilder conFechaPublicacion(String fechaPublicacion) {
		if (fechaPublicacion == null || fechaPublicacion.trim().isEmpty()) {
			throw new MensajeException("La fecha de publicación no puede estar vacía");
		}
		this.fechaPublicacion = fechaPublicacion;
		return this;
	}

	/**
	 * Establece el autor del mensaje.
	 * @param autor el autor a asignar
	 * @return la instancia del builder para encadenamiento
	 */
	public MensajeBuilder conAutor(String autor) {
		if (autor == null || autor.trim().isEmpty()) {
			throw new MensajeException("El autor no puede estar vacío");
		}
		this.autor = autor;
		return this;
	}

	/**
	 * Establece las etiquetas del mensaje.
	 * @param etiquetas las etiquetas a asignar
	 * @return la instancia del builder para encadenamiento
	 */
	public MensajeBuilder conEtiquetas(String etiquetas) {
		this.etiquetas = etiquetas;
		return this;
	}

	/**
	 * Establece la imagen del mensaje.
	 * @param imagen la imagen a asignar
	 * @return la instancia del builder para encadenamiento
	 */
	public MensajeBuilder conImagen(String imagen) {
		this.imagen = imagen;
		return this;
	}

	/**
	 * Construye la instancia de Mensaje.
	 * @return nueva instancia de Mensaje
	 * @throws MensajeException si faltan parámetros obligatorios
	 */
	public Mensaje build() {
		if (texto == null || fechaPublicacion == null || autor == null) {
			throw new MensajeException("Texto, fecha de publicación y autor son obligatorios");
		}
		return new Mensaje(texto, fechaPublicacion, autor, etiquetas, imagen);
	}
}
