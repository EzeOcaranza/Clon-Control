package Ejercicio2;

import java.util.ArrayList;

public class Usuario {
	private String nombre;
	private String apellidos;
	private String fechaNacimiento;
	private String nickName;
	private ArrayList<Mensaje> mensajes;

	public Usuario(String nombre, String apellidos, String fechaNacimiento, String nickName) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.nickName = nickName;
		this.mensajes = new ArrayList();
	}

	public String getNickName() {
		return nickName;
	}

	public void publicarMensaje(Mensaje m) {
		mensajes.add(m);
	}

	public void eliminarMensaje(Mensaje m) {
		mensajes.remove(m);
	}

	public void MostrarUsuario() {
		System.out.println("El usuario es " + this.nickName + " y sus mensajes son " + this.mensajes);
	}
}
