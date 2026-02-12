classDiagram
    %% Dominio Ejercicio 1: Equipos y Jugadores
    class Jugador {
        -String nickname
        -String rango
        +Jugador(String, String)
        +getNickname() String
        +setNickname(String)
        +getRango() String
        +setRango(String)
    }

    class Equipo {
        -String nombre
        -List~Jugador~ jugadores
        +Equipo(String)
        +getNombre() String
        +setNombre(String)
        +getJugadores() List
        +agregarJugador(Jugador)
        +eliminarJugador(Jugador)
        +getCantidadJugadores() int
    }

    class IEquipoLector {
        +getNombre() String
        +getJugadores() List
        +getCantidadJugadores() int
    }

    class IEquipoEscritor {
        +setNombre(String)
        +agregarJugador(Jugador)
        +eliminarJugador(Jugador)
    }

    class JugadorException {
        +JugadorException(String)
        +JugadorException(String, Throwable)
    }

    class EquipoException {
        +EquipoException(String)
        +EquipoException(String, Throwable)
    }

    class JugadorBuilder {
        -String nickname
        -String rango
        +conNickname(String) JugadorBuilder
        +conRango(String) JugadorBuilder
        +build() Jugador
    }

    class EquipoBuilder {
        -String nombre
        -List~Jugador~ jugadores
        +conNombre(String) EquipoBuilder
        +agregarJugador(Jugador) EquipoBuilder
        +agregarJugadores(List) EquipoBuilder
        +build() Equipo
    }

    %% Dominio Ejercicio 2: Usuarios y Mensajes
    class Usuario {
        -String nombre
        -String apellidos
        -String fechaNacimiento
        -String nickName
        -List~Mensaje~ mensajes
        +Usuario(String, String, String, String)
        +getNombre() String
        +setNombre(String)
        +getApellidos() String
        +setApellidos(String)
        +getFechaNacimiento() String
        +setFechaNacimiento(String)
        +getNickName() String
        +setNickName(String)
        +getMensajes() List
        +publicarMensaje(Mensaje)
        +eliminarMensaje(Mensaje)
        +getCantidadMensajes() int
    }

    class Mensaje {
        -String texto
        -String fechaPublicacion
        -String autor
        -Set~String~ etiquetas
        -String imagen
        +Mensaje(String, String, String, String, String)
        +getTexto() String
        +setTexto(String)
        +getFechaPublicacion() String
        +setFechaPublicacion(String)
        +getAutor() String
        +setAutor(String)
        +getEtiquetas() Set
        +agregarEtiqueta(String)
        +eliminarEtiqueta(String)
        +getCantidadEtiquetas() int
    }

    class IUsuarioLector {
        +getNombre() String
        +getApellidos() String
        +getFechaNacimiento() String
        +getNickName() String
        +getMensajes() List
        +getCantidadMensajes() int
    }

    class IUsuarioEscritor {
        +setNombre(String)
        +setApellidos(String)
        +setFechaNacimiento(String)
        +setNickName(String)
        +publicarMensaje(Mensaje)
        +eliminarMensaje(Mensaje)
    }

    class UsuarioException {
        +UsuarioException(String)
        +UsuarioException(String, Throwable)
    }

    class MensajeException {
        +MensajeException(String)
        +MensajeException(String, Throwable)
    }

    class UsuarioBuilder {
        -String nombre
        -String apellidos
        -String fechaNacimiento
        -String nickName
        +conNombre(String) UsuarioBuilder
        +conApellidos(String) UsuarioBuilder
        +conFechaNacimiento(String) UsuarioBuilder
        +conNickName(String) UsuarioBuilder
        +build() Usuario
    }

    class MensajeBuilder {
        -String texto
        -String fechaPublicacion
        -String autor
        -String etiquetas
        -String imagen
        +conTexto(String) MensajeBuilder
        +conFechaPublicacion(String) MensajeBuilder
        +conAutor(String) MensajeBuilder
        +conEtiquetas(String) MensajeBuilder
        +conImagen(String) MensajeBuilder
        +build() Mensaje
    }

    %% Excepciones base
    class RuntimeException {
    }

    %% Relaciones - Herencia
    JugadorException --|> RuntimeException
    EquipoException --|> RuntimeException
    UsuarioException --|> RuntimeException
    MensajeException --|> RuntimeException

    %% Interfaces
    Equipo --|> IEquipoLector
    Equipo --|> IEquipoEscritor
    Usuario --|> IUsuarioLector
    Usuario --|> IUsuarioEscritor

    %% Relaciones de Composición
    Equipo "1" --o "*" Jugador : contiene
    Usuario "1" --o "*" Mensaje : publica

    %% Builders
    JugadorBuilder ..> Jugador : creates
    EquipoBuilder ..> Equipo : creates
    UsuarioBuilder ..> Usuario : creates
    MensajeBuilder ..> Mensaje : creates

    %% Excepciones en Clases
    Jugador --> JugadorException : throws
    Equipo --> EquipoException : throws
    Usuario --> UsuarioException : throws
    Mensaje --> MensajeException : throws

    %% Builders uses excepciones
    JugadorBuilder --> JugadorException : throws
    EquipoBuilder --> EquipoException : throws
    UsuarioBuilder --> UsuarioException : throws
    MensajeBuilder --> MensajeException : throws
