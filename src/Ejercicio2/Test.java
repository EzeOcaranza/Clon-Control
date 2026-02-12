package Ejercicio2;

//la relación es de asociación, un mensaje puede estar asociado a un usuario aunque no sea obligatorio
public class Test {

	public static void main(String[] args) {
		// añadimos dos usuarios y les ponemos sus características
		Usuario us1 = new Usuario("Ezequiel", "Ocaranza Dell'Arte", "24/10/2005", "eOcaranza");
		Usuario us2 = new Usuario("Javier", "Fernández Ortiz", "15/03/2000", "jFortiz");

		// creamos dos mensajes con sus caracteristicas
		Mensaje m1 = new Mensaje("Hola", "10/01/26", "eOcaranza", "#saludo", "https://imagen.png/GDASbdjs51");
		Mensaje m3 = new Mensaje("Ola", "9/01/26", "eOcaranza", "#saluo", "https://imagen.png/GDASbd51yt");
		Mensaje m2 = new Mensaje("Adios", "12/01/26", "jFortiz", "#despedidad", "https://imagen.png/GDASbd7jh");

		// le asignamos a cada usuario su mensaje respectivamente
		us1.publicarMensaje(m1);
		us1.publicarMensaje(m3);
		us2.publicarMensaje(m2);
		// enseñamos por pantalla los usuarios y sus mensajes
		us1.MostrarUsuario();
		us2.MostrarUsuario();

		// aqui probamos que eliminar el mensaje sea funcional
		us1.eliminarMensaje(m3);
		us1.MostrarUsuario();
	}

}
