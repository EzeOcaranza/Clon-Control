package Ejercicio2;

import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import Ejercicio2.exceptions.MensajeException;

/**
 * Clase que representa un mensaje en el sistema.
 * Contiene la información del mensaje y sus metadatos.
 */
public class Mensaje {
	private String texto;
	private String fechaPublicacion;
	private String autor;
	private Set<String> etiquetas;
	private String imagen;

	/**
	 * Constructor de Mensaje con validación.
	 * @param texto contenido del mensaje
	 * @param fechaPublicacion fecha de publicación
	 * @param autor autor del mensaje
	 * @param etiquetas etiquetas asociadas
	 * @param imagen ruta de la imagen (puede ser null)
	 * @throws MensajeException si parámetros obligatorios son null o vacíos
	 */
	public Mensaje(String texto, String fechaPublicacion, String autor, String etiquetas, String imagen) {
		if (texto == null || texto.trim().isEmpty()) {
			throw new MensajeException("El texto del mensaje no puede estar vacío");
		}
		if (fechaPublicacion == null || fechaPublicacion.trim().isEmpty()) {
			throw new MensajeException("La fecha de publicación no puede estar vacía");
		}
		if (autor == null || autor.trim().isEmpty()) {
			throw new MensajeException("El autor no puede estar vacío");
		}
		
		this.texto = texto;
		this.fechaPublicacion = fechaPublicacion;
		this.autor = autor;
		this.etiquetas = new HashSet<>();
		this.imagen = imagen;
		
		// Agregar etiquetas iniciales si se proporcionan
		if (etiquetas != null && !etiquetas.trim().isEmpty()) {
			agregarEtiqueta(etiquetas);
		}
	}

	/**
	 * Obtiene el texto del mensaje.
	 * @return el texto
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * Establece el texto del mensaje.
	 * @param texto el nuevo texto
	 * @throws MensajeException si texto es null o vacío
	 */
	public void setTexto(String texto) {
		if (texto == null || texto.trim().isEmpty()) {
			throw new MensajeException("El texto del mensaje no puede estar vacío");
		}
		this.texto = texto;
	}

	/**
	 * Obtiene la fecha de publicación.
	 * @return la fecha
	 */
	public String getFechaPublicacion() {
		return fechaPublicacion;
	}

	/**
	 * Establece la fecha de publicación.
	 * @param fechaPublicacion la nueva fecha
	 */
	public void setFechaPublicacion(String fechaPublicacion) {
		if (fechaPublicacion == null || fechaPublicacion.trim().isEmpty()) {
			throw new MensajeException("La fecha no puede estar vacía");
		}
		this.fechaPublicacion = fechaPublicacion;
	}

	/**
	 * Obtiene el autor del mensaje.
	 * @return el autor
	 */
	public String getAutor() {
		return autor;
	}

	/**
	 * Establece el autor del mensaje.
	 * @param autor el nuevo autor
	 */
	public void setAutor(String autor) {
		if (autor == null || autor.trim().isEmpty()) {
			throw new MensajeException("El autor no puede estar vacío");
		}
		this.autor = autor;
	}

	/**
	 * Obtiene las etiquetas del mensaje.
	 * @return conjunto inmutable de etiquetas
	 */
	public Set<String> getEtiquetas() {
		return Collections.unmodifiableSet(etiquetas);
	}

	/**
	 * Agrega una etiqueta al mensaje.
	 * @param etiqueta la etiqueta a agregar
	 * @throws MensajeException si etiqueta es null o vacía
	 */
	public void agregarEtiqueta(String etiqueta) {
		if (etiqueta == null || etiqueta.trim().isEmpty()) {
			throw new MensajeException("La etiqueta no puede estar vacía");
		}
		etiquetas.add(etiqueta.trim());
	}

	/**
	 * Elimina una etiqueta del mensaje.
	 * @param etiqueta la etiqueta a eliminar
	 * @return true si se eliminó, false en caso contrario
	 */
	public boolean eliminarEtiqueta(String etiqueta) {
		return etiquetas.remove(etiqueta);
	}

	/**
	 * Obtiene la imagen del mensaje.
	 * @return la ruta de la imagen o null
	 */
	public String getImagen() {
		return imagen;
	}

	/**
	 * Establece la imagen del mensaje.
	 * @param imagen la ruta de la imagen
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	/**
	 * Muestra la información del mensaje por consola.
	 */
	public void mostrarMensaje() {
		System.out.println(this.toString());
	}

	/**
	 * Restablecer todas las etiquetas del mensaje.
	 */
	public void limpiarEtiquetas() {
		etiquetas.clear();
	}

	/**
	 * Obtiene la cantidad de etiquetas.
	 * @return cantidad de etiquetas
	 */
	public int getCantidadEtiquetas() {
		return etiquetas.size();
	}

	/**
	 * Representación en string del mensaje.
	 * @return información formateada del mensaje
	 */
	@Override
	public String toString() {
		return "Mensaje {" +
				"texto='" + texto + '\'' +
				", fechaPublicacion='" + fechaPublicacion + '\'' +
				", autor='" + autor + '\'' +
				", etiquetas=" + etiquetas +
				", imagen='" + imagen + '\'' +
				'}';
	}
}
