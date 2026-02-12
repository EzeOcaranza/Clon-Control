package Ejercicio2;

import jakarta.persistence.*;

/**
 * Entidad JPA para Mensaje.
 */
@Entity
@Table(name = "mensajes")
public class MensajeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "texto", columnDefinition = "TEXT", nullable = false)
	private String texto;

	@Column(name = "fecha_publicacion", length = 50, nullable = false)
	private String fechaPublicacion;

	@Column(name = "autor", length = 50, nullable = false)
	private String autor;

	@Column(name = "etiquetas", length = 255)
	private String etiquetas;

	@Column(name = "imagen", length = 255)
	private String imagen;

	public MensajeEntity() {
	}

	public MensajeEntity(String texto, String fechaPublicacion, String autor) {
		this.texto = texto;
		this.fechaPublicacion = fechaPublicacion;
		this.autor = autor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(String fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEtiquetas() {
		return etiquetas;
	}

	public void setEtiquetas(String etiquetas) {
		this.etiquetas = etiquetas;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
}
