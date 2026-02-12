package Ejercicio1;

//la relacion entre jugador y equipo es una relacion de agregación, debido a que al equipo se le agregan jugadores para poder realizarlo
public class Test {

	public static void main(String[] args) {
		// aqui creamos el equipo y le ponemos nombre
		Equipo eq = new Equipo("VodkaJS eSports");

		// aqui creamos a los jugadores y les damos el nombre de usuario y su rango
		Jugador jug1 = new Jugador("Gaston", "Hierro");
		Jugador jug2 = new Jugador("cocacola", "Oro");
		Jugador jug3 = new Jugador("Nonova", "Bronce");
		Jugador jug4 = new Jugador("Steady", "Bronce");
		Jugador jug5 = new Jugador("Arekusu", "Plata");

		// aqui añadimos a los jugadores al equipo
		eq.addJugador(jug1);
		eq.addJugador(jug2);
		eq.addJugador(jug3);
		eq.addJugador(jug4);
		eq.addJugador(jug5);

		// finalmente mostramos el equipo y sus miembros
		eq.mostrar();
	}

}
