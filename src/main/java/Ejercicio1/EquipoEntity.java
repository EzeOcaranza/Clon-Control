package Ejercicio1;

import jakarta.persistence.*;
import java.util.List;

/**
 * Entidad JPA para Equipo.
 * Mapea la tabla 'equipos' en la base de datos.
 */
@Entity
@Table(name = "equipos")
public class EquipoEntity {
	
	@Id
	@Column(name = "nombre", length = 100)
	private String nombre;

	@OneToMany
	@JoinColumn(name = "equipo_nombre")
	private List<JugadorEntity> jugadores;

	public EquipoEntity() {
	}

	public EquipoEntity(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<JugadorEntity> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<JugadorEntity> jugadores) {
		this.jugadores = jugadores;
	}

	@Override
	public String toString() {
		return "EquipoEntity{" +
				"nombre='" + nombre + '\'' +
				", jugadores=" + jugadores +
				'}';
	}
}
