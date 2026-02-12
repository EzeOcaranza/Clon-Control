# Clon Control - Docker Setup Guide

## Overview
Clon Control es una aplicación REST API construida con Java 17, Spring Boot, JPA/Hibernate, y MySQL. Esta guía explica cómo ejecutar la aplicación usando Docker y Docker Compose.

## Requisitos Previos
- Docker Engine 20.10+
- Docker Compose 1.29+
- (Opcional) Maven 3.8+ si quieres compilar localmente

## Inicio Rápido con Docker Compose

### 1. Clonar el repositorio
```bash
git clone <repository-url>
cd Clon-Control
```

### 2. Iniciar la aplicación
```bash
docker-compose up -d
```

Este comando:
- Compila la aplicación Java automáticamente
- Inicia el servicio MySQL
- Inicia la aplicación en puerto 8080

### 3. Verificar estado
```bash
docker-compose ps
```

Deberías ver dos servicios corriendo:
- `clon_control_db` (MySQL)
- `clon_control_app` (Aplicación)

## Endpoints API Disponibles

### Jugadores
```bash
GET    /api/jugadores              # Obtener todos
GET    /api/jugadores/{nickname}   # Obtener específico
POST   /api/jugadores              # Crear
PUT    /api/jugadores/{nickname}   # Actualizar
DELETE /api/jugadores/{nickname}   # Eliminar
GET    /api/jugadores/count        # Contar
```

### Equipos
```bash
GET    /api/equipos                # Obtener todos
GET    /api/equipos/{nombre}       # Obtener específico
POST   /api/equipos                # Crear
PUT    /api/equipos/{nombre}       # Actualizar
DELETE /api/equipos/{nombre}       # Eliminar
POST   /api/equipos/{nombre}/jugadores      # Agregar jugador
```

### Usuarios
```bash
GET    /api/usuarios               # Obtener todos
GET    /api/usuarios/{nickname}    # Obtener específico
POST   /api/usuarios               # Crear
PUT    /api/usuarios/{nickname}    # Actualizar
DELETE /api/usuarios/{nickname}    # Eliminar
POST   /api/usuarios/{nickname}/mensajes    # Publicar mensaje
GET    /api/usuarios/{nickname}/mensajes    # Obtener mensajes
```

### Mensajes
```bash
GET    /api/mensajes               # Obtener todos
GET    /api/mensajes/{id}          # Obtener específico
POST   /api/mensajes               # Crear
POST   /api/mensajes/{id}/etiquetas           # Agregar etiqueta
DELETE /api/mensajes/{id}/etiquetas          # Eliminar etiqueta
DELETE /api/mensajes/{id}          # Eliminar
```

## Ejemplo de Uso con curl

### Crear un jugador
```bash
curl -X POST "http://localhost:8080/api/jugadores?nickname=Cristiano&rango=Elite"
```

### Obtener todos los jugadores
```bash
curl "http://localhost:8080/api/jugadores"
```

### Crear un equipo
```bash
curl -X POST "http://localhost:8080/api/equipos?nombre=Real%20Madrid"
```

## Gestión de Contenedores

### Detener la aplicación
```bash
docker-compose down
```

### Eliminar todo incluyendo volúmenes
```bash
docker-compose down -v
```

### Ver logs
```bash
docker-compose logs -f app
docker-compose logs -f db
```

### Acceder a la base de datos
```bash
docker-compose exec db mysql -u clon_user -p clon_control
```
Contraseña: `clon_pass`

## Configuración de Variables de Entorno

Puedes personalizar la configuración creando un archivo `.env`:

```env
DATABASE_URL=jdbc:mysql://db:3306/clon_control
DATABASE_USER=clon_user
DATABASE_PASSWORD=clon_pass
SPRING_JPA_HIBERNATE_DDL_AUTO=update
```

## Build Manual sin Docker Compose

### Compilar la imagen
```bash
docker build -t clon-control:latest .
```

### Ejecutar el contenedor
```bash
docker run -p 8080:8080 \
  -e DATABASE_URL=jdbc:mysql://host.docker.internal:3306/clon_control \
  -e DATABASE_USER=clon_user \
  -e DATABASE_PASSWORD=clon_pass \
  clon-control:latest
```

## Volúmenes Persistentes

- `db_data`: Almacena los datos de MySQL persistentemente
  
Los datos se conservan aunque los contenedores se detengan, a menos que ejecutes `docker-compose down -v`

## Red Docker

Los servicios se comunican a través de la red `clon_control_network`:
- La aplicación se conecta a MySQL usando el host `db`
- La aplicación expone su API en `http://localhost:8080`

## Solución de Problemas

### Puerto 8080 ya está en uso
```bash
# Cambiar el puerto en docker-compose.yml
ports:
  - "8081:8080"  # Nueva configuración
```

### Base de datos no inicializa
```bash
# Reconstruir y eliminar volúmenes
docker-compose down -v
docker-compose up -d
```

### Ver logs detallados
```bash
docker-compose logs --tail=100 -f
```

## Estructura de Directorios de Docker

```
Clon-Control/
├── Dockerfile              # Definición de imagen Java
├── docker-compose.yml      # Orquestación de servicios
├── .dockerignore          # Archivos excluidos del build
├── init.sql               # Script SQL de inicialización
├── pom.xml               # Dependencias Maven
└── src/
    └── main/
        ├── java/          # Código fuente
        └── resources/
            └── application.yml  # Configuración Spring Boot
```

## Información Adicional

- **Java Version**: 17 LTS (Alpine)
- **MySQL Version**: 8.0 (Alpine)
- **Framework**: Spring Boot 3.x
- **ORM**: Hibernate (JPA)
- **Build**: Maven 3.8+

## Contacto y Soporte

Para problemas consulta el archivo README.md principal del proyecto.
