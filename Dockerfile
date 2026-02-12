# Multi-stage Dockerfile para Clon-Control
# Etapa 1: Build
FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /build
COPY . .
RUN sh -c 'apk add --no-cache maven && mvn clean package -DskipTests'

# Etapa 2: Runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /build/target/*.jar app.jar

# Información de la imagen
LABEL maintainer="Clon-Control Team"
LABEL description="Controlador de clones - API REST con persistencia JPA"
LABEL version="1.0"

# Exponer puerto de aplicación
EXPOSE 8080

# Variables de entorno para base de datos
ENV DATABASE_URL=jdbc:mysql://db:3306/clon_control
ENV DATABASE_USER=root
ENV DATABASE_PASSWORD=root

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]
