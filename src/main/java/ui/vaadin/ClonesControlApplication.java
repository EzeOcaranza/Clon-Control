package ui.vaadin;

/**
 * Aplicación principal Vaadin para Clon-Control.
 * 
 * Para usar esta aplicación, agregue las siguientes dependencias a pom.xml:
 * 
 * <dependency>
 *     <groupId>com.vaadin</groupId>
 *     <artifactId>vaadin-core</artifactId>
 *     <version>24.3.5</version>
 * </dependency>
 * 
 * <dependency>
 *     <groupId>com.vaadin</groupId>
 *     <artifactId>vaadin-spring-boot-starter</artifactId>
 *     <version>24.3.5</version>
 * </dependency>
 * 
 * Arquitectura:
 * - Vistas para Equipos, Usuarios, Mensajes
 * - Formularios para crear/editar entidades
 * - Grillas para mostrar datos
 * - Buscadores y filtros
 */
public class ClonesControlApplication {
	
	/**
	 * Punto de entrada de la aplicación Vaadin.
	 * 
	 * COMPONENTES:
	 * 1. EquipoView - Gestión de equipos y jugadores
	 * 2. UsuarioView - Gestión de usuarios y mensajes
	 * 3. DashboardView - Panel de control general
	 * 
	 * RUTAS:
	 * - /equipos → Listado de equipos
	 * - /usuarios → Listado de usuarios
	 * - /dashboard → Dashboard principal
	 * 
	 * USO:
	 * npx npm init
	 * npm install @vaadin/[email protected]
	 * npm run build
	 * 
	 * Luego ejecutar con Spring Boot:
	 * mvn spring-boot:run
	 */
	public static void main(String[] args) {
		// La aplicación se ejecutará en http://localhost:8080
		System.out.println("Clon-Control UI con Vaadin");
		System.out.println("Para usar Vaadin, configure el proyecto como Spring Boot + Vaadin");
		System.out.println("Vea la documentación en: https://vaadin.com/docs/latest");
	}
}
