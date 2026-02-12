package Ejercicio2;

import jakarta.persistence.*;
import java.util.List;

/**
 * Entidad JPA para Usuario.
 */
@Entity
@Table(name = "usuarios")
public class UsuarioEntity {
	
	@Id
	@Column(name = "nick_name", length = 50)
	private String nickName;

	@Column(name = "nombre", length = 100, nullable = false)
	private String nombre;

	@Column(name = "apellidos", length = 100, nullable = false)
	private String apellidos;

	@Column(name = "fecha_nacimiento")
	private String fechaNacimiento;

	@OneToMany
	@JoinColumn(name = "usuario_nickname")
	private List<MensajeEntity> mensajes;

	public UsuarioEntity() {
	}

	public UsuarioEntity(String nombre, String apellidos, String nickName) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nickName = nickName;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public List<MensajeEntity> getMensajes() {
		return mensajes;
	}

	public void setMensajes(List<MensajeEntity> mensajes) {
		this.mensajes = mensajes;
	}
}
