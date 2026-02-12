package Ejercicio1.strategy;

import Ejercicio1.Equipo;

/**
 * Interfaz Strategy para diferentes formas de presentar información del equipo.
 */
public interface EquipoStrategy {
	/**
	 * Procesa y retorna la información del equipo según la estrategia.
	 * @param equipo a procesar
	 * @return string con la información procesada
	 */
	String procesar(Equipo equipo);
}
