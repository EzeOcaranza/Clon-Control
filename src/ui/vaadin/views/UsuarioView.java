package ui.vaadin.views;

import Ejercicio2.Usuario;
import Ejercicio2.Mensaje;
import Ejercicio2.factory.UsuarioMensajeFactory;
import Ejercicio2.repository.UsuarioRepository;
import Ejercicio2.observer.UsuarioNotificador;
import Ejercicio2.observer.LoggingObserver;
import config.AppConfig;
import java.util.List;

/**
 * Vista de Usuarios para Vaadin.
 * Permite crear, editar usuarios y gestionar sus mensajes.
 * 
 * Componentes Vaadin necesarios:
 * - Grid para mostrar usuarios
 * - Dialog para crear/editar usuarios
 * - Button para agregar usuario
 * - TextField para datos del usuario
 * - TextArea para escribir mensajes
 */
public class UsuarioView {
	
	private UsuarioRepository usuarioRepository;
	private UsuarioNotificador notificador;

	public UsuarioView() {
		this.usuarioRepository = AppConfig.getInstance().getUsuarioRepository();
		this.notificador = new UsuarioNotificador();
		
		// Registrar observadores
		notificador.registrarObservador(new LoggingObserver());
		
		inicializar();
	}

	private void inicializar() {
		// Crear usuarios de ejemplo
		Usuario u1 = UsuarioMensajeFactory.crearUsuario("Juan", "Pérez García", "1990-05-15", "juanperez");
		Usuario u2 = UsuarioMensajeFactory.crearUsuario("María", "García López", "1992-03-20", "mariagarcia");

		usuarioRepository.guardar(u1);
		usuarioRepository.guardar(u2);

		// Crear mensajes de ejemplo
		Mensaje m1 = UsuarioMensajeFactory.crearMensajeConEtiquetas(
			"¡Hola a todos!", "2026-02-12", "juanperez", "saludo"
		);
		u1.publicarMensaje(m1);
		notificador.notificarPublicacion(m1);

		System.out.println("UsuarioView inicializada con datos de prueba");
	}

	/**
	 * Retorna todos los usuarios.
	 * @return lista de usuarios
	 */
	public List<Usuario> obtenerUsuarios() {
		return usuarioRepository.obtenerTodos();
	}

	/**
	 * Crea un nuevo usuario.
	 * @param nombre del usuario
	 * @param apellidos del usuario
	 * @param nickName del usuario
	 * @return usuario creado
	 */
	public Usuario crearUsuario(String nombre, String apellidos, String nickName) {
		Usuario usuario = UsuarioMensajeFactory.crearUsuario(nombre, apellidos, nickName);
		usuarioRepository.guardar(usuario);
		return usuario;
	}

	/**
	 * Publica un mensaje del usuario.
	 * @param usuario que publica
	 * @param texto del mensaje
	 * @param fecha de publicación
	 */
	public void publicarMensaje(Usuario usuario, String texto, String fecha) {
		Mensaje mensaje = UsuarioMensajeFactory.crearMensajeSimple(texto, fecha, usuario.getNickName());
		usuario.publicarMensaje(mensaje);
		usuarioRepository.actualizar(usuario);
		notificador.notificarPublicacion(mensaje);
	}

	public UsuarioNotificador getNotificador() {
		return notificador;
	}
}
