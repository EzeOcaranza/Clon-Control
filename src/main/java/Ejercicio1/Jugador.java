package Ejercicio1;

import Ejercicio1.exceptions.JugadorException;

/**
 * Clase que representa un jugador en el sistema.
 * Contiene información sobre el nickname y rango del jugador.
 */
public class Jugador {
	private String nickname;
	private String rango;

	/**
	 * Constructor de Jugador con validación.
	 * @param nickname nombre único del jugador
	 * @param rango nivel o categoría del jugador
	 * @throws JugadorException si nickname o rango son null o vacíos
	 */
	public Jugador(String nickname, String rango) {
		if (nickname == null || nickname.trim().isEmpty()) {
			throw new JugadorException("El nickname no puede estar vacío");
		}
		if (rango == null || rango.trim().isEmpty()) {
			throw new JugadorException("El rango no puede estar vacío");
		}
		this.nickname = nickname;
		this.rango = rango;
	}

	/**
	 * Obtiene el nickname del jugador.
	 * @return el nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * Establece el nickname del jugador.
	 * @param nickname el nuevo nickname
	 * @throws JugadorException si nickname es null o vacío
	 */
	public void setNickname(String nickname) {
		if (nickname == null || nickname.trim().isEmpty()) {
			throw new JugadorException("El nickname no puede estar vacío");
		}
		this.nickname = nickname;
	}

	/**
	 * Obtiene el rango del jugador.
	 * @return el rango
	 */
	public String getRango() {
		return rango;
	}

	/**
	 * Establece el rango del jugador.
	 * @param rango el nuevo rango
	 * @throws JugadorException si rango es null o vacío
	 */
	public void setRango(String rango) {
		if (rango == null || rango.trim().isEmpty()) {
			throw new JugadorException("El rango no puede estar vacío");
		}
		this.rango = rango;
	}

	/**
	 * Representación en string del jugador.
	 * @return información formateada del jugador
	 */
	@Override
	public String toString() {
		return "\nNickname= " + nickname + ", rango= " + rango;
	}

}
