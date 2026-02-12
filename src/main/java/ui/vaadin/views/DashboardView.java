package ui.vaadin.views;

import Ejercicio1.repository.EquipoRepository;
import Ejercicio1.repository.JugadorRepository;
import Ejercicio2.repository.UsuarioRepository;
import config.AppConfig;

/**
 * Vista de Dashboard para Vaadin.
 * Muestra estadísticas generales de la aplicación.
 * 
 * Componentes Vaadin necesarios:
 * - Statistic cards para mostrar totales
 * - Charts para gráficos
 * - Tabs para diferentes secciones
 */
public class DashboardView {
	
	private EquipoRepository equipoRepository;
	private JugadorRepository jugadorRepository;
	private UsuarioRepository usuarioRepository;

	public DashboardView() {
		this.equipoRepository = AppConfig.getInstance().getEquipoRepository();
		this.jugadorRepository = AppConfig.getInstance().getJugadorRepository();
		this.usuarioRepository = AppConfig.getInstance().getUsuarioRepository();
	}

	/**
	 * Obtiene estadísticas generales.
	 * @return string con las estadísticas
	 */
	public String obtenerEstadisticas() {
		StringBuilder sb = new StringBuilder();
		sb.append("=== ESTADÍSTICAS GENERALES ===\n");
		sb.append("Equipos: ").append(equipoRepository.contar()).append("\n");
		sb.append("Jugadores: ").append(jugadorRepository.contar()).append("\n");
		sb.append("Usuarios: ").append(usuarioRepository.contar()).append("\n");
		return sb.toString();
	}

	public int getEquiposCount() {
		return equipoRepository.contar();
	}

	public int getJugadoresCount() {
		return jugadorRepository.contar();
	}

	public int getUsuariosCount() {
		return usuarioRepository.contar();
	}
}
