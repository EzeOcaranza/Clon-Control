package Ejercicio2;

public class Mensaje {
	private String texto;
	private String fechaPublicacion;
	private String autor;
	private String etiquetas;
	private String imagen;

	public Mensaje(String texto, String fechaPublicacion, String autor, String etiquetas, String imagen) {
		this.texto = texto;
		this.fechaPublicacion = fechaPublicacion;
		this.autor = autor;
		this.etiquetas = etiquetas;
		this.imagen = imagen;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void agregarEtiqueta() {
		
	}


	public void eliminarEtiqueta() {

	}

	public void MostrarMensaje() {

	}

	@Override
	public String toString() {
		return "Mensaje [texto=" + texto + ", fechaPublicacion=" + fechaPublicacion + ", autor=" + autor
				+ ", etiquetas=" + etiquetas + ", imagen=" + imagen + "]";
	}
}
