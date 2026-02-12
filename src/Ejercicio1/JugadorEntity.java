package Ejercicio1;

import jakarta.persistence.*;

/**
 * Entidad JPA para Jugador.
 * Mapea la tabla 'jugadores' en la base de datos.
 */
@Entity
@Table(name = "jugadores")
public class JugadorEntity {
	
	@Id
	@Column(name = "nickname", length = 50)
	private String nickname;

	@Column(name = "rango", length = 50, nullable = false)
	private String rango;

	public JugadorEntity() {
	}

	public JugadorEntity(String nickname, String rango) {
		this.nickname = nickname;
		this.rango = rango;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRango() {
		return rango;
	}

	public void setRango(String rango) {
		this.rango = rango;
	}

	@Override
	public String toString() {
		return "JugadorEntity{" +
				"nickname='" + nickname + '\'' +
				", rango='" + rango + '\'' +
				'}';
	}
}
