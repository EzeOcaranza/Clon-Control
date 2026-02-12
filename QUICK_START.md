# 🚀 QUICK START GUIDE - Clon Control

## ⚡ Inicio Rápido (30 segundos)

### Opción 1: Docker Compose (RECOMENDADO)

```bash
# Navegar al directorio del proyecto
cd Clon-Control

# Iniciar aplicación y base de datos
docker-compose up -d

# Verificar que está corriendo
docker-compose ps

# Ver logs
docker-compose logs -f
```

**Resultado**: 
- 🟢 API disponible en `http://localhost:8080`
- 🟢 MySQL en puerto 3306 (usuario: `clon_user`, contraseña: `clon_pass`)

### Opción 2: Compilar con Maven

```bash
# Compilar el proyecto
mvn clean install

# Ejecutar tests
mvn test

# Crear JAR
mvn package

# Ejecutar (requiere MySQL accesible)
java -jar target/clon-control.jar
```

---

## 🧪 Probar la API (ejemplos con curl)

### 1. Crear un jugador

```bash
curl -X POST "http://localhost:8080/api/jugadores?nickname=Cristiano&rango=Elite"
```

**Respuesta**:
```json
{
  "nickname": "Cristiano",
  "rango": "Elite"
}
```

### 2. Obtener todos los jugadores

```bash
curl "http://localhost:8080/api/jugadores"
```

### 3. Crear un equipo

```bash
curl -X POST "http://localhost:8080/api/equipos?nombre=Real%20Madrid"
```

### 4. Agregar jugador al equipo

```bash
curl -X POST "http://localhost:8080/api/equipos/Real%20Madrid/jugadores?nicknamejugador=Cristiano"
```

### 5. Crear usuario

```bash
curl -X POST "http://localhost:8080/api/usuarios?nickname=admin&email=admin@test.com"
```

### 6. Publicar mensaje

```bash
curl -X POST "http://localhost:8080/api/usuarios/admin/mensajes?contenido=Hola%20mundo"
```

### 7. Obtener mensajes del usuario

```bash
curl "http://localhost:8080/api/usuarios/admin/mensajes"
```

---

## 🗄️ Acceder a la Base de Datos

### Desde Docker

```bash
# Acceder a MySQL
docker-compose exec db mysql -u clon_user -p clon_control
# Contraseña: clon_pass

# Ver tablas
SHOW TABLES;
SELECT * FROM jugadores;
```

### Localmente (si tienes MySQL 8.0)

```bash
mysql -h localhost -u clon_user -p clon_control
# Contraseña: clon_pass
```

---

## 📊 Ejecutar Tests

### Todos los tests

```bash
mvn test
```

**Resultado esperado**: 150+ tests PASSED ✅

### Tests específicos

```bash
# Solo tests unitarios
mvn test -Dtest=Jugador*

# Solo tests del builder
mvn test -Dtest=*BuilderTest

# Solo tests de repository
mvn test -Dtest=*RepositoryTest

# Solo tests de patrón
mvn test -Dtest=*FactoryTest,*StrategyTest,*ObserverTest
```

---

## 🛑 Detener la Aplicación

### Con Docker Compose

```bash
# Detener servicios
docker-compose down

# Detener y eliminar volúmenes (perderá BD)
docker-compose down -v

# Detener un servicio específico
docker-compose stop app / db
```

---

## 🔧 Solución de Problemas

### Puerto 8080 ya en uso

```bash
# Opción 1: Verificar qué proceso usa el puerto
lsof -i :8080

# Opción 2: Cambiar puerto en docker-compose.yml
# Línea: ports: ["8081:8080"]
docker-compose down
docker-compose up -d
```

### MySQL no inicializa

```bash
# Eliminar volumen de datos y reiniciar
docker-compose down -v
docker-compose up -d
```

### Ver logs detallados

```bash
# Todos los servicios
docker-compose logs -f

# Solo aplicación
docker-compose logs -f app

# Solo base de datos
docker-compose logs -f db

# Últimas 100 líneas
docker-compose logs -f --tail=100
```

### Error de conexión a BD

```bash
# Verificar que MySQL está saludable
docker-compose ps

# Ver logs de base de datos
docker-compose logs db

# Esperar 10 segundos e intentar de nuevo
sleep 10 && curl "http://localhost:8080/api/jugadores"
```

---

## 📝 Endpoints Principales

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/jugadores` | Listar todos los jugadores |
| POST | `/api/jugadores` | Crear nuevo jugador |
| GET | `/api/equipos` | Listar todos los equipos |
| POST | `/api/equipos` | Crear nuevo equipo |
| GET | `/api/usuarios` | Listar todos los usuarios |
| POST | `/api/usuarios` | Crear nuevo usuario |
| GET | `/api/mensajes` | Listar todos los mensajes |
| POST | `/api/mensajes` | Crear nuevo mensaje |

**Ver documentación completa**: `DOCKER_SETUP.md`

---

## 📂 Archivos Importantes

```
Clon-Control/
├── docker-compose.yml          # Orquestación Docker
├── Dockerfile                  # Build de aplicación
├── init.sql                    # Datos iniciales
├── src/main/resources/application.yml  # Configuración
│
└── DOCUMENTACIÓN
    ├── README.md               # Descripción general
    ├── RESUMEN_FINAL.md        # Resumen de 5 ramas
    ├── ESTRUCTURA_FINAL.md     # Estructura del código
    ├── DOCKER_SETUP.md         # Guía Docker (ESTA AQUÍ)
    └── QUICK_START.md          # Este archivo
```

---

## ✅ Checklist de Setup

- [ ] Clonar repositorio
- [ ] Tener Docker instalado (`docker --version`)
- [ ] Tener Docker Compose (`docker-compose --version`)
- [ ] Ejecutar `docker-compose up -d`
- [ ] Verificar `docker-compose ps`
- [ ] Probar `curl http://localhost:8080/api/jugadores`
- [ ] ¡Listo! 🎉

---

## 📞 Necesitas Ayuda?

1. **Verificar Docker**: `docker-compose ps`
2. **Ver logs**: `docker-compose logs -f`
3. **Consultar guía**: Ver `DOCKER_SETUP.md`
4. **Revisar código**: Ver `RESUMEN_FINAL.md`
5. **Estructura**: Ver `ESTRUCTURA_FINAL.md`

---

## 🎓 Aprender más

### Sobre los Patrones
- Builder Pattern: `src/Ejercicio1/builder/`
- Factory Pattern: `src/Ejercicio1/factory/`
- Strategy Pattern: `src/Ejercicio1/strategy/`
- Observer Pattern: `src/Ejercicio2/observer/`
- Repository Pattern: `src/Ejercicio1/repository/`

### Sobre Tests
- Tests unitarios: `*Test.java` (14 archivos)
- Ejecución: `mvn test`
- Cobertura: 150+ tests

### Sobre API REST
- Controladores: `src/api/rest/`
- 4 controladores, 25+ endpoints
- Documentación: `DOCKER_SETUP.md`

---

**Versión**: 1.0.0  
**Actualizado**: 12 de Febrero de 2026  
**Status**: ✅ Listo para usar
