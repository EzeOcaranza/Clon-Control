package Ejercicio1.builder;

import Ejercicio1.Jugador;
import Ejercicio1.exceptions.JugadorException;

/**
 * Builder para la clase Jugador implementando el patrón Builder.
 * Permite crear instancias de Jugador de forma fluida y flexible.
 */
public class JugadorBuilder {
	private String nickname;
	private String rango;

	/**
	 * Establece el nickname del jugador.
	 * @param nickname el nickname a asignar
	 * @return la instancia del builder para encadenamiento
	 */
	public JugadorBuilder conNickname(String nickname) {
		if (nickname == null || nickname.trim().isEmpty()) {
			throw new JugadorException("El nickname no puede estar vacío");
		}
		this.nickname = nickname;
		return this;
	}

	/**
	 * Establece el rango del jugador.
	 * @param rango el rango a asignar
	 * @return la instancia del builder para encadenamiento
	 */
	public JugadorBuilder conRango(String rango) {
		if (rango == null || rango.trim().isEmpty()) {
			throw new JugadorException("El rango no puede estar vacío");
		}
		this.rango = rango;
		return this;
	}

	/**
	 * Construye la instancia de Jugador.
	 * @return nueva instancia de Jugador
	 * @throws JugadorException si faltan parámetros obligatorios
	 */
	public Jugador build() {
		if (nickname == null || rango == null) {
			throw new JugadorException("Nickname y rango son obligatorios");
		}
		return new Jugador(nickname, rango);
	}
}
