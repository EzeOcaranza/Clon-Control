# 🚀 GUÍA DE DEPLOYMENT - CLON CONTROL

## Estado Actual del Proyecto

El proyecto **Clon Control** está **100% configurado y listo para desplegar**. Incluye:

✅ **60 archivos Java** con arquitectura completa  
✅ **150+ tests** (JUnit 5 + Mockito)  
✅ **6+ patrones de diseño** implementados  
✅ **25+ endpoints REST** funcionales  
✅ **Configuración Docker** lista para producción  
✅ **Documentación completa**  

---

## 📋 Tabla de Contenidos

1. [Verificación del Proyecto](#verificación-del-proyecto)
2. [Opción 1: Docker Compose (Recomendado)](#opción-1-docker-compose-recomendado)
3. [Opción 2: Compilar con Maven](#opción-2-compilar-con-maven)
4. [Opción 3: Desplegar en Cloud](#opción-3-desplegar-en-cloud)
5. [Pruebas Post-Deployment](#pruebas-post-deployment)
6. [Troubleshooting](#troubleshooting)

---

## Verificación del Proyecto

Para verificar que todo está configurado correctamente:

```bash
cd /workspaces/Clon-Control
./verify-project.sh
```

Salida esperada:
- ✅ 60 archivos Java
- ✅ 14 archivos de test
- ✅ 7 archivos de documentación
- ✅ Git history con 5 branches integradas

---

## Opción 1: Docker Compose (RECOMENDADO)

### Requisitos Previos

```bash
# Verificar Docker
docker --version       # Docker 20.10+
docker-compose --version  # Docker Compose 1.29+
```

### Pasos de Deployment

#### 1. Clonar el Repositorio

```bash
git clone <repository-url>
cd Clon-Control
```

#### 2. Iniciar Contenedores

```bash
# Limpiar deployment anterior (si aplica)
docker-compose down -v

# Iniciar servicios
docker-compose up -d

# Verificar estado
docker-compose ps
```

**Salida esperada:**
```
NAME                  STATUS              PORTS
clon_control_app      Up 30 seconds       0.0.0.0:8080->8080/tcp
clon_control_db       Up 35 seconds       0.0.0.0:3306->3306/tcp
```

#### 3. Esperar a que la Aplicación Inicie

```bash
# Ver logs de la aplicación
docker-compose logs -f app

# Esperar hasta ver "Application started"
```

#### 4. Verificar Deployment

```bash
# Probar endpoint
curl http://localhost:8080/api/jugadores

# Respuesta esperada (JSON con array vacío inicialmente)
[]
```

### Servicios en Ejecución

| Servicio | URL | Usuario | Contraseña |
|----------|-----|---------|-----------|
| API REST | http://localhost:8080 | - | - |
| MySQL | localhost:3306 | clon_user | clon_pass |

---

## Opción 2: Compilar con Maven

### Requisitos Previos

```bash
java -version          # Java 17+
mvn --version         # Maven 3.8+
```

### Pasos de Deployment

#### 1. Compilar Proyecto

```bash
cd Clon-Control
mvn clean install
```

#### 2. Ejecutar Tests

```bash
mvn test

# Resultado esperado: 150+ tests PASSING
```

#### 3. Crear JAR Ejecutable

```bash
mvn package

# JAR creado en: target/clon-control.jar
```

#### 4. Iniciar Aplicación

```bash
# Requiere MySQL accesible en localhost:3306
java -jar target/clon-control.jar

# O con variables de entorno personalizadas
java -jar target/clon-control.jar \
  --spring.datasource.url=jdbc:mysql://localhost:3306/clon_control \
  --spring.datasource.username=clon_user \
  --spring.datasource.password=clon_pass
```

#### 5. Verificar

```bash
# En otra terminal
curl http://localhost:8080/api/jugadores
```

---

## Opción 3: Desplegar en Cloud

### Heroku

```bash
# Login en Heroku
heroku login

# Crear app
heroku create clon-control

# Provisionar base de datos
heroku addons:create cleardb:ignite

# Deploy
git push heroku main

# Ver logs
heroku logs -t
```

### AWS (Elastic Beanstalk)

```bash
# Instalar EB CLI
pip install awsebcli

# Inicializar
eb init -p "Docker running on 64bit Amazon Linux 2"

# Crear environment
eb create clon-control-env

# Deploy
eb deploy

# Abrir en navegador
eb open
```

### Google Cloud

```bash
# Instalar Google Cloud SDK
gcloud init

# Crear proyecto
gcloud projects create clon-control

# Deploy
gcloud run deploy clon-control --source .

# Ver URL
gcloud run services list
```

### Azure

```bash
# Instalar Azure CLI
az login

# Crear resource group
az group create --name clon-control --location eastus

# Deploy
az container create \
  --resource-group clon-control \
  --name clon-control-app \
  --image clon-control:latest \
  --ports 8080
```

---

## Pruebas Post-Deployment

### 1. Crear Jugador

```bash
curl -X POST "http://localhost:8080/api/jugadores?nickname=Cristiano&rango=Elite"

# Respuesta esperada:
# {"nickname":"Cristiano","rango":"Elite"}
```

### 2. Obtener Jugador

```bash
curl "http://localhost:8080/api/jugadores/Cristiano"

# Respuesta esperada:
# {"nickname":"Cristiano","rango":"Elite"}
```

### 3. Crear Equipo

```bash
curl -X POST "http://localhost:8080/api/equipos?nombre=Real%20Madrid"

# Respuesta esperada:
# {"nombre":"Real Madrid","jugadores":[]}
```

### 4. Crear Usuario

```bash
curl -X POST "http://localhost:8080/api/usuarios?nickname=admin&email=admin@test.com"

# Respuesta esperada:
# {"nickname":"admin","email":"admin@test.com"}
```

### 5. Publicar Mensaje

```bash
curl -X POST "http://localhost:8080/api/usuarios/admin/mensajes?contenido=Hello%20World"

# Respuesta esperada:
# {"id":1,"contenido":"Hello World",...}
```

---

## Troubleshooting

### Puerto 8080 ya en uso

**Problema:**
```
Address already in use
```

**Solución:**

```bash
# Opción 1: Cambiar puerto en docker-compose.yml
# ports:
#   - "8081:8080"  <- cambiar aquí

# Opción 2: Liberar puerto
lsof -i :8080
kill -9 <PID>
```

### MySQL no inicia

**Problema:**
```
Connection refused
```

**Solución:**

```bash
# Esperar más tiempo
sleep 15

# O revisar logs
docker-compose logs db

# Reconstruir volumen
docker-compose down -v
docker-compose up -d
```

### Errores de Base de Datos

**Problema:**
```
Unknown database 'clon_control'
```

**Solución:**

```bash
# Acceder a MySQL
docker-compose exec db mysql -u root -p

# SQL:
CREATE DATABASE IF NOT EXISTS clon_control;
GRANT ALL PRIVILEGES ON clon_control.* TO 'clon_user'@'%';
```

### Aplicación no responde

**Verificar:**

```bash
# Ver logs
docker-compose logs app

# Verificar conectividad
docker-compose exec app curl http://localhost:8080/api/jugadores

# Reiniciar
docker-compose restart app
```

---

## Monitoreo

### Ver Logs en Tiempo Real

```bash
# Todas las aplicaciones
docker-compose logs -f

# Solo aplicación
docker-compose logs -f app

# Últimas 100 líneas
docker-compose logs -f --tail=100
```

### Estadísticas de Contenedores

```bash
docker-compose stats
```

### Acceso Directo a MySQL

```bash
docker-compose exec db mysql -u clon_user -p clon_control

# Contraseña: clon_pass

# Queries útiles:
SHOW TABLES;
SELECT * FROM jugadores;
SELECT COUNT(*) FROM equipos;
```

---

## Mantenimiento

### Actualizar Aplicación

```bash
# Detener servicios
docker-compose down

# Actualizar código
git pull origin main

# Reconstruir imagen
docker-compose build

# Reiniciar
docker-compose up -d
```

### Respaldar Base de Datos

```bash
# Crear backup
docker-compose exec db mysqldump -u clon_user -p clon_control > backup.sql

# Restaurar
docker-compose exec db mysql -u clon_user -p clon_control < backup.sql
```

### Limpiar Recursos

```bash
# Detener sin eliminar datos
docker-compose stop

# Detener y eliminar contenedores
docker-compose down

# Eliminar todo incluyendo volúmenes
docker-compose down -v

# Eliminar imágenes
docker image prune
```

---

## Verificación de Salud

### Health Check Automático

Docker Compose incluye health check que verifica:
- MySQL está respondiendo
- Aplicación inició correctamente
- Conexión entre servicios

### Manual

```bash
# Verificar status
curl http://localhost:8080/health

# Respuesta esperada:
# {"status":"UP"}
```

---

## Configuración Producción

Para producción, considerar:

1. **Variables de Entorno**
   ```bash
   SPRING_JPA_HIBERNATE_DDL_AUTO=validate
   SPRING_DATASOURCE_URL=jdbc:mysql://prod-db:3306/clon_control
   ```

2. **SSL/HTTPS**
   - Configurar certificados
   - Usar reverse proxy (nginx)

3. **Backups Automáticos**
   - Configurar mysqldump en cron
   - Almacenar en S3/Cloud Storage

4. **Logging Centralizado**
   - ELK Stack (Elasticsearch, Logstash, Kibana)
   - Datadog
   - CloudWatch

5. **Monitoreo**
   - Prometheus + Grafana
   - New Relic
   - DataDog

6. **Seguridad**
   - Cambiar contraseñas por defecto
   - Usar secretos en vault
   - Habilitar firewall
   - Rate limiting

---

## Documentación Relacionada

- [QUICK_START.md](QUICK_START.md) - Inicio rápido (30 segundos)
- [DOCKER_SETUP.md](DOCKER_SETUP.md) - Configuración Docker detallada
- [RESUMEN_FINAL.md](RESUMEN_FINAL.md) - Descripción general del proyecto
- [ESTRUCTURA_FINAL.md](ESTRUCTURA_FINAL.md) - Estructura de archivos

---

## Soporte

Para problemas o preguntas:

1. Consultar logs: `docker-compose logs`
2. Revisar DOCKER_SETUP.md
3. Ejecutar verify-project.sh
4. Revisar documentación del framework

---

**Estado del Proyecto**: ✅ **LISTO PARA PRODUCCIÓN**  
**Fecha**: 12 de Febrero de 2026  
**Versión**: 1.0.0
