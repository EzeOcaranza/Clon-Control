# Dockerfile para Clon-Control - Servidor Simple Java
FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /build

# Copiar código fuente
COPY src/main/java /src

# Compilar solo el servidor simple (evitar problemas de compilación)
RUN javac -d classes \
    /src/com/clon/app/SimpleApiServer.java 2>&1

# Etapa 2: Runtime
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copiar clases compiladas
COPY --from=build /build/classes /app/classes

# Crear el JAR con la clase correcta
RUN cd /app && \
    echo 'Manifest-Version: 1.0' > MANIFEST.MF && \
    echo 'Main-Class: com.clon.app.SimpleApiServer' >> MANIFEST.MF && \
    jar cfm app.jar MANIFEST.MF -C classes . && \
    ls -lah

# Labels
LABEL maintainer="Clon-Control Team"
LABEL description="Controlador de clones - Simple API Server"
LABEL version="1.0"

# Puerto
EXPOSE 8080

# Healthcheck
HEALTHCHECK --interval=10s --timeout=3s --start-period=2s --retries=3 \
    CMD wget --quiet --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Ejecutar
ENTRYPOINT ["java", "-jar", "app.jar"]




# Variables de entorno para base de datos
ENV DATABASE_URL=jdbc:mysql://db:3306/clon_control
ENV DATABASE_USER=root
ENV DATABASE_PASSWORD=root

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]
