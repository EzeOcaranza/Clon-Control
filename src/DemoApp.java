import Ejercicio1.Equipo;
import Ejercicio1.Jugador;
import Ejercicio1.strategy.*;
import Ejercicio1.factory.JugadorEquipoFactory;
import Ejercicio1.repository.EquipoRepository;
import Ejercicio1.repository.JugadorRepository;

import Ejercicio2.Usuario;
import Ejercicio2.Mensaje;
import Ejercicio2.factory.UsuarioMensajeFactory;
import Ejercicio2.observer.UsuarioNotificador;
import Ejercicio2.observer.LoggingObserver;
import Ejercicio2.repository.UsuarioRepository;

import config.AppConfig;
import config.LoggingConfig;
import ui.vaadin.views.*;

/**
 * Aplicación de demostración de todos los patrones implementados en Rama 4.
 */
public class DemoApp {

	public static void main(String[] args) {
		// Configurar logging
		LoggingConfig.configurar();

		System.out.println("\n=== CLON-CONTROL - DEMOSTRACIÓN DE PATRONES ===\n");

		// 1. FACTORY PATTERN
		demoFactoryPattern();

		// 2. STRATEGY PATTERN
		demoStrategyPattern();

		// 3. OBSERVER PATTERN
		demoObserverPattern();

		// 4. DAO/REPOSITORY PATTERN
		demoRepositoryPattern();

		// 5. VAADIN VIEWS
		demoVaadinViews();
	}

	private static void demoFactoryPattern() {
		System.out.println("\n--- 1. FACTORY PATTERN ---");
		
		// Crear jugadores usando Factory
		Jugador j1 = JugadorEquipoFactory.crearJugadorElite("Cristiano7");
		Jugador j2 = JugadorEquipoFactory.crearJugadorProfesional("Neymar");
		Jugador j3 = JugadorEquipoFactory.crearJugadorBasico("Vinicius");

		System.out.println("Jugador Elite creado: " + j1.getNickname() + " (" + j1.getRango() + ")");
		System.out.println("Jugador Profesional creado: " + j2.getNickname() + " (" + j2.getRango() + ")");
		System.out.println("Jugador Básico creado: " + j3.getNickname() + " (" + j3.getRango() + ")");

		// Crear equipo con jugadores
		Equipo equipo = JugadorEquipoFactory.crearEquipoConJugadores("Real Madrid", j1, j2, j3);
		System.out.println("Equipo creado: " + equipo.getNombre() + " con " + equipo.getCantidadJugadores() + " jugadores");
	}

	private static void demoStrategyPattern() {
		System.out.println("\n--- 2. STRATEGY PATTERN ---");
		
		Equipo equipo = JugadorEquipoFactory.crearEquipoConJugadores(
			"Barcelona",
			JugadorEquipoFactory.crearJugadorElite("Pedri"),
			JugadorEquipoFactory.crearJugadorElite("Gavi")
		);

		// Diferentes estrategias de presentación
		EquipoStrategy[] estrategias = {
			new EquipoSummaryStrategy(),
			new EquipoDetailedStrategy(),
			new EquipoJsonStrategy()
		};

		for (EquipoStrategy estrategia : estrategias) {
			System.out.println(estrategia.procesar(equipo));
		}
	}

	private static void demoObserverPattern() {
		System.out.println("\n--- 3. OBSERVER PATTERN ---");
		
		Usuario usuario = UsuarioMensajeFactory.crearUsuario("Juan", "Pérez", "juan123");
		
		UsuarioNotificador notificador = new UsuarioNotificador();
		notificador.registrarObservador(new LoggingObserver());

		// Publicar mensaje (notifica a observadores)
		Mensaje mensaje = UsuarioMensajeFactory.crearMensajeConEtiquetas(
			"¡Primer mensaje!", "2026-02-12", usuario.getNickName(), "saludo"
		);
		
		usuario.publicarMensaje(mensaje);
		notificador.notificarPublicacion(mensaje);

		System.out.println("Usuario " + usuario.getNickName() + " publicó mensaje");
		System.out.println("Observadores notificados");
	}

	private static void demoRepositoryPattern() {
		System.out.println("\n--- 4. DAO/REPOSITORY PATTERN ---");
		
		AppConfig config = AppConfig.getInstance();
		JugadorRepository jugadorRepo = config.getJugadorRepository();
		EquipoRepository equipoRepo = config.getEquipoRepository();
		UsuarioRepository usuarioRepo = config.getUsuarioRepository();

		// Guardar datos
		Jugador jugador = JugadorEquipoFactory.crearJugadorElite("Modric10");
		jugadorRepo.guardar(jugador);

		Equipo equipo = JugadorEquipoFactory.crearEquipo("Real Madrid");
		equipo.agregarJugador(jugador);
		equipoRepo.guardar(equipo);

		Usuario usuario = UsuarioMensajeFactory.crearUsuario("Carlos", "López", "carlos123");
		usuarioRepo.guardar(usuario);

		// Recuperar datos
		System.out.println("Jugadores guardados: " + jugadorRepo.contar());
		System.out.println("Equipos guardados: " + equipoRepo.contar());
		System.out.println("Usuarios guardados: " + usuarioRepo.contar());

		// Buscar
		Jugador encontrado = jugadorRepo.encontrarPorNickname("Modric10");
		System.out.println("Jugador encontrado: " + encontrado.getNickname());

		Equipo equipoEncontrado = equipoRepo.encontrarPorNombre("Real Madrid");
		System.out.println("Equipo encontrado: " + equipoEncontrado.getNombre());
	}

	private static void demoVaadinViews() {
		System.out.println("\n--- 5. VAADIN VIEWS ---");
		
		EquipoView equipoView = new EquipoView();
		UsuarioView usuarioView = new UsuarioView();
		DashboardView dashboard = new DashboardView();

		System.out.println("EquipoView: " + equipoView.obtenerEquipos().size() + " equipos cargados");
		System.out.println("UsuarioView: " + usuarioView.obtenerUsuarios().size() + " usuarios cargados");
		System.out.println("Dashboard:\n" + dashboard.obtenerEstadisticas());

		System.out.println("\nPara usar la UI completa con Vaadin:");
		System.out.println("1. Configure un proyecto Spring Boot + Vaadin");
		System.out.println("2. Ejecute: mvn spring-boot:run");
		System.out.println("3. Acceda a: http://localhost:8080");
	}
}
