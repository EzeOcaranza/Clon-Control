-- ========================================
-- Inicialización de Base de Datos MySQL
-- ========================================

-- Tabla de Jugadores
CREATE TABLE IF NOT EXISTS jugadores (
    nickname VARCHAR(100) PRIMARY KEY,
    rango VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_rango (rango)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla de Equipos
CREATE TABLE IF NOT EXISTS equipos (
    nombre VARCHAR(100) PRIMARY KEY,
    founded_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla de relación Equipos-Jugadores
CREATE TABLE IF NOT EXISTS equipo_jugadores (
    equipo_nombre VARCHAR(100) NOT NULL,
    jugador_nickname VARCHAR(100) NOT NULL,
    PRIMARY KEY (equipo_nombre, jugador_nickname),
    FOREIGN KEY (equipo_nombre) REFERENCES equipos(nombre) ON DELETE CASCADE,
    FOREIGN KEY (jugador_nickname) REFERENCES jugadores(nickname) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla de Usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    nickname VARCHAR(100) PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla de Mensajes
CREATE TABLE IF NOT EXISTS mensajes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_nickname VARCHAR(100) NOT NULL,
    contenido TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_nickname) REFERENCES usuarios(nickname) ON DELETE CASCADE,
    INDEX idx_usuario (usuario_nickname),
    INDEX idx_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla de relación Mensajes-Etiquetas
CREATE TABLE IF NOT EXISTS mensaje_etiquetas (
    mensaje_id BIGINT NOT NULL,
    etiqueta VARCHAR(100) NOT NULL,
    PRIMARY KEY (mensaje_id, etiqueta),
    FOREIGN KEY (mensaje_id) REFERENCES mensajes(id) ON DELETE CASCADE,
    INDEX idx_etiqueta (etiqueta)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ========================================
-- Datos de Prueba Iniciales
-- ========================================

-- Insertar jugadores de prueba
INSERT INTO jugadores (nickname, rango) VALUES
    ('Cristiano', 'Elite'),
    ('Messi', 'Elite'),
    ('Neymar', 'Profesional'),
    ('Benzema', 'Profesional'),
    ('Haaland', 'Elite')
ON DUPLICATE KEY UPDATE updated_at = CURRENT_TIMESTAMP;

-- Insertar equipos de prueba
INSERT INTO equipos (nombre) VALUES
    ('Real Madrid'),
    ('Barcelona'),
    ('Manchester City'),
    ('Paris SG'),
    ('Liverpool')
ON DUPLICATE KEY UPDATE updated_at = CURRENT_TIMESTAMP;

-- Insertar relaciones equipo-jugadores
INSERT INTO equipo_jugadores (equipo_nombre, jugador_nickname) VALUES
    ('Real Madrid', 'Cristiano'),
    ('Real Madrid', 'Benzema'),
    ('Barcelona', 'Messi'),
    ('Barcelona', 'Neymar'),
    ('Manchester City', 'Haaland')
ON DUPLICATE KEY UPDATE equipo_nombre = equipo_nombre;

-- Insertar usuarios de prueba
INSERT INTO usuarios (nickname, email) VALUES
    ('admin', 'admin@clon-control.com'),
    ('user1', 'user1@clon-control.com'),
    ('user2', 'user2@clon-control.com')
ON DUPLICATE KEY UPDATE updated_at = CURRENT_TIMESTAMP;

-- Insertar mensajes de prueba
INSERT INTO mensajes (usuario_nickname, contenido) VALUES
    ('admin', 'Bienvenido a Clon Control'),
    ('user1', 'Primer mensaje del usuario'),
    ('user2', 'Sistema funcionando correctamente')
ON DUPLICATE KEY UPDATE contenido = VALUES(contenido);

-- ========================================
-- Permisos y Límites
-- ========================================

-- Crear usuario específico si lo requiere
-- CREATE USER IF NOT EXISTS 'clon_user'@'%' IDENTIFIED BY 'clon_pass';
-- GRANT ALL PRIVILEGES ON clon_control.* TO 'clon_user'@'%';
-- FLUSH PRIVILEGES;
