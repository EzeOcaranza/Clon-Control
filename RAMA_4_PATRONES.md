# Rama 4: Patrones Avanzados, DAO/Repository y Vaadin

## Patrones Implementados

### 1. Factory Pattern
**Ubicación:** `Ejercicio1/factory/`, `Ejercicio2/factory/`

**Descripción:** Patrón para crear instancias de forma centralizada y flexible.

**Clases:**
- `JugadorEquipoFactory` - Crea Jugadores y Equipos
  - `crearJugador(nickname, rango)`
  - `crearJugadorElite(nickname)` - Crea jugador Elite (predefinido)
  - `crearJugadorProfesional(nickname)` - Crea jugador Profesional
  - `crearJugadorBasico(nickname)` - Crea jugador Básico
  - `crearEquipo(nombre)`
  - `crearEquipoConJugadores(nombre, jugadores...)`

- `UsuarioMensajeFactory` - Crea Usuarios y Mensajes
  - `crearUsuario(nombre, apellidos, nickName)`
  - `crearMensaje(texto, fecha, autor)`
  - `crearMensajeSimple(texto, fecha, autor)`
  - `crearMensajeConEtiquetas(texto, fecha, autor, etiquetas)`

**Ventajas:**
- Separación de lógica de creación
- Fácil de mantener y extender
- Permite agregar validaciones centralizadas

### 2. Strategy Pattern
**Ubicación:** `Ejercicio1/strategy/`

**Descripción:** Patrón para encapsular algoritmos intercambiables.

**Clases:**
- `EquipoStrategy` - Interfaz para diferentes estrategias
- `EquipoSummaryStrategy` - Formato resumido
  ```
  "Equipo 'Real Madrid' con 3 jugadores"
  ```

- `EquipoDetailedStrategy` - Formato detallado
  ```
  === EQUIPO: Real Madrid ===
  Total de jugadores: 3
  Jugadores:
  - Cristiano7 (Elite)
  - Modric10 (Elite)
  ```

- `EquipoJsonStrategy` - Formato JSON
  ```json
  {
    "nombre": "Real Madrid",
    "cantidad_jugadores": 3,
    "jugadores": [...]
  }
  ```

**Uso:**
```java
EquipoStrategy strategy = new EquipoDetailedStrategy();
String resultado = strategy.procesar(equipo);
```

### 3. Observer Pattern
**Ubicación:** `Ejercicio2/observer/`

**Descripción:** Patrón para notificar cambios a múltiples observadores.

**Clases:**
- `UsuarioObserver` - Interfaz para observadores
  - `onMensajePublicado(Mensaje)`
  - `onMensajeEliminado(Mensaje)`

- `UsuarioNotificador` - Gestor de observadores
  - `registrarObservador(UsuarioObserver)`
  - `desregistrarObservador(UsuarioObserver)`
  - `notificarPublicacion(Mensaje)`
  - `notificarEliminacion(Mensaje)`

- `LoggingObserver` - Observador que registra en log

**Uso:**
```java
UsuarioNotificador notificador = new UsuarioNotificador();
notificador.registrarObservador(new LoggingObserver());
notificador.notificarPublicacion(mensaje);
```

### 4. DAO/Repository Pattern
**Ubicación:** `Ejercicio1/repository/`, `Ejercicio2/repository/`

**Descripción:** Patrón para abstraer acceso a datos.

**Repositorios:**

**Jugador:**
- `JugadorRepository` - Interfaz CRUD
- `JugadorRepositoryMemoria` - Implementación en memoria

**Equipo:**
- `EquipoRepository` - Interfaz CRUD
- `EquipoRepositoryMemoria` - Implementación en memoria

**Usuario:**
- `UsuarioRepository` - Interfaz CRUD
- `UsuarioRepositoryMemoria` - Implementación en memoria

**Métodos comunes:**
- `guardar(Entidad)` - Crear
- `encontrarPor*(criterio)` - Leer
- `obtenerTodos()` - Leer todos
- `actualizar(Entidad)` - Actualizar
- `eliminar(Entidad)` - Eliminar
- `contar()` - Contar registros

**Ventajas:**
- Desacoplamiento de lógica de persistencia
- Fácil de cambiar de BD en memoria a BD real
- Facilita testing con mocks

### 5. Singleton Pattern
**Ubicación:** `config/AppConfig.java`

**Descripción:** Garantiza una única instancia de la configuración.

```java
AppConfig config = AppConfig.getInstance();
JugadorRepository repo = config.getJugadorRepository();
```

### 6. Logging Configuration
**Ubicación:** `config/LoggingConfig.java`

**Descripción:** Configuración centralizada de logs.

**Métodos:**
- `configurar()` - Inicializa logging
- `info(String)` - Log nivel INFO
- `error(String, Exception)` - Log nivel ERROR
- `warning(String)` - Log nivel WARNING

**Salidas:**
- Consola: Logs en tiempo real
- Archivo: `logs/clon-control.log` - Persistencia de logs

## Vaadin UI

### Vistas Disponibles

**EquipoView** - Gestión de equipos
- Listar equipos
- Crear nuevos equipos
- Agregar jugadores a equipos
- Eliminar equipos

**UsuarioView** - Gestión de usuarios
- Listar usuarios
- Crear nuevos usuarios
- Publicar mensajes
- Gestionar mensajes

**DashboardView** - Panel de control
- Estadísticas generales
- Contadores de entidades
- Gráficos y métricas

### Configuración de Vaadin

Para usar Vaadin, agregue a `pom.xml`:
```xml
<dependency>
    <groupId>com.vaadin</groupId>
    <artifactId>vaadin-core</artifactId>
    <version>24.3.5</version>
</dependency>

<dependency>
    <groupId>com.vaadin</groupId>
    <artifactId>vaadin-spring-boot-starter</artifactId>
    <version>24.3.5</version>
</dependency>
```

### Ejecución

```bash
# Compilar
mvn clean package

# Ejecutar
mvn spring-boot:run

# Acceder
http://localhost:8080
```

## Aplicación de Demostración

**Archivo:** `DemoApp.java`

Demuestra todos los patrones en acción:
```bash
java DemoApp
```

## Resumen de Patrones SOLID

| Patrón | Principio SOLID | Beneficio |
|--------|-----------------|-----------|
| Factory | SRP, OCP | Separación de creación |
| Strategy | OCP, SRP | Algoritmos intercambiables |
| Observer | SRP, OCP | Notificaciones desacopladas |
| Repository | DIP, SRP | Abstracción de persistencia |
| Singleton | SRP | Instancia única centralizada |
| Logging | SRP | Registros centralizados |

## Jerarquía de Clases

```
AppConfig (Singleton)
├── JugadorRepository
├── EquipoRepository
└── UsuarioRepository

JugadorEquipoFactory
├── JugadorBuilder
└── EquipoBuilder

UsuarioMensajeFactory
├── UsuarioBuilder
└── MensajeBuilder

EquipoStrategy (Interface)
├── EquipoSummaryStrategy
├── EquipoDetailedStrategy
└── EquipoJsonStrategy

UsuarioObserver (Interface)
└── LoggingObserver

UsuarioNotificador
└── [Lista de observadores]
```

## Próximos Pasos

1. Implementar persistencia real (Base de datos)
2. Agregar autenticación y autorización
3. Crear API REST
4. Mejorar UI con componentes avanzados
5. Agregar validaciones más robustas
