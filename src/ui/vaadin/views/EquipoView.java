package ui.vaadin.views;

import Ejercicio1.Equipo;
import Ejercicio1.Jugador;
import Ejercicio1.factory.JugadorEquipoFactory;
import Ejercicio1.repository.EquipoRepository;
import Ejercicio1.repository.JugadorRepository;
import config.AppConfig;
import java.util.List;

/**
 * Vista de Equipos para Vaadin.
 * Permite crear, editar y eliminar equipos, así como gestionar jugadores.
 * 
 * Componentes Vaadin necesarios:
 * - Grid para mostrar equipos
 * - Dialog para crear/editar equipos
 * - Button para agregar equipo
 * - TextField para nombre del equipo
 * - ComboBox para seleccionar jugadores
 */
public class EquipoView {
	
	private EquipoRepository equipoRepository;
	private JugadorRepository jugadorRepository;

	public EquipoView() {
		this.equipoRepository = AppConfig.getInstance().getEquipoRepository();
		this.jugadorRepository = AppConfig.getInstance().getJugadorRepository();
		inicializar();
	}

	private void inicializar() {
		// Cargar datos de ejemplo
		Jugador j1 = JugadorEquipoFactory.crearJugadorElite("Cristiano7");
		Jugador j2 = JugadorEquipoFactory.crearJugadorElite("Modric10");
		Jugador j3 = JugadorEquipoFactory.crearJugadorProfesional("Benzema");

		jugadorRepository.guardar(j1);
		jugadorRepository.guardar(j2);
		jugadorRepository.guardar(j3);

		Equipo real = JugadorEquipoFactory.crearEquipoConJugadores("Real Madrid", j1, j2, j3);
		equipoRepository.guardar(real);

		System.out.println("EquipoView inicializada con datos de prueba");
	}

	/**
	 * Retorna todos los equipos.
	 * @return lista de equipos
	 */
	public List<Equipo> obtenerEquipos() {
		return equipoRepository.obtenerTodos();
	}

	/**
	 * Crea un nuevo equipo.
	 * @param nombre del equipo
	 * @return equipo creado
	 */
	public Equipo crearEquipo(String nombre) {
		Equipo equipo = JugadorEquipoFactory.crearEquipo(nombre);
		equipoRepository.guardar(equipo);
		return equipo;
	}

	/**
	 * Agrega un jugador a un equipo.
	 * @param equipo destino
	 * @param jugador a agregar
	 */
	public void agregarJugador(Equipo equipo, Jugador jugador) {
		equipo.agregarJugador(jugador);
		equipoRepository.actualizar(equipo);
	}
}
