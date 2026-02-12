package Ejercicio1;

import java.util.ArrayList;

public class Equipo {
	private String nombre;
	private ArrayList<Jugador> jug;

	// en el constructor le asignamos a jug una nueva lista para poder almacenar a
	// los jugadores
	public Equipo(String nombre) {
		this.nombre = nombre;
		this.jug = new ArrayList();
	}

	public ArrayList<Jugador> getJug() {
		return jug;
	}

	public void addJugador(Jugador j) {
		jug.add(j);
	}

	public void mostrar() {
		System.out.println("El equipo es " + this.nombre + " y sus miembros son:" + this.jug);
	}
}
