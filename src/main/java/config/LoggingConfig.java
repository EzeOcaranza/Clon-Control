package config;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;

/**
 * Configuración centralizada de logging.
 * Configura logs a consola y archivo.
 */
public class LoggingConfig {
	private static final Logger logger = Logger.getLogger("Clon-Control");

	/**
	 * Configura el sistema de logging.
	 */
	public static void configurar() {
		try {
			// Crear manejador de archivo
			FileHandler fileHandler = new FileHandler("logs/clon-control.log", true);
			fileHandler.setFormatter(new SimpleFormatter());
			fileHandler.setLevel(Level.INFO);

			// Configurar logger
			logger.addHandler(fileHandler);
			logger.setLevel(Level.INFO);

			logger.info("Sistema de logging configurado correctamente");
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error configurando logging", e);
		}
	}

	/**
	 * Obtiene el logger global.
	 * @return logger configurado
	 */
	public static Logger getLogger() {
		return logger;
	}

	/**
	 * Log de información.
	 * @param mensaje a registrar
	 */
	public static void info(String mensaje) {
		logger.info(mensaje);
	}

	/**
	 * Log de error.
	 * @param mensaje a registrar
	 * @param excepcion que ocurrió
	 */
	public static void error(String mensaje, Exception excepcion) {
		logger.log(Level.SEVERE, mensaje, excepcion);
	}

	/**
	 * Log de advertencia.
	 * @param mensaje a registrar
	 */
	public static void warning(String mensaje) {
		logger.warning(mensaje);
	}
}
