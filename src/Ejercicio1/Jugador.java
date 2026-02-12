package Ejercicio1;

public class Jugador {
	private String nickname;
	private String rango;

	public Jugador(String nickname, String rango) {

		this.nickname = nickname;
		this.rango = rango;
	}

	public String getNickname() {
		return nickname;
	}

	public String getRango() {
		return rango;
	}

	@Override
	public String toString() {
		return "\nNickname= " + nickname + ", rango= " + rango;
	}

}
