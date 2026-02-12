package config;

import Ejercicio1.repository.*;
import Ejercicio2.repository.*;

/**
 * Configuración centralizada de la aplicación.
 * Implementa patrón Singleton.
 */
public class AppConfig {
	private static AppConfig instancia;

	// Repositories
	private JugadorRepository jugadorRepository;
	private EquipoRepository equipoRepository;
	private UsuarioRepository usuarioRepository;

	private AppConfig() {
		// Inicializar repositorios en memoria
		this.jugadorRepository = new JugadorRepositoryMemoria();
		this.equipoRepository = new EquipoRepositoryMemoria();
		this.usuarioRepository = new UsuarioRepositoryMemoria();
	}

	/**
	 * Obtiene la instancia única de AppConfig.
	 * @return instancia de AppConfig
	 */
	public static AppConfig getInstance() {
		if (instancia == null) {
			synchronized (AppConfig.class) {
				if (instancia == null) {
					instancia = new AppConfig();
				}
			}
		}
		return instancia;
	}

	public JugadorRepository getJugadorRepository() {
		return jugadorRepository;
	}

	public EquipoRepository getEquipoRepository() {
		return equipoRepository;
	}

	public UsuarioRepository getUsuarioRepository() {
		return usuarioRepository;
	}
}
