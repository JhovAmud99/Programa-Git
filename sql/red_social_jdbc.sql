CREATE DATABASE IF NOT EXISTS red_social;
USE red_social;

CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS publicacion (
    id_publicacion INT AUTO_INCREMENT PRIMARY KEY,
    contenido TEXT NOT NULL,
    fecha_publicacion DATETIME NOT NULL,
    id_usuario INT NOT NULL,
    CONSTRAINT fk_publicacion_usuario
        FOREIGN KEY (id_usuario)
        REFERENCES usuario(id_usuario)
);

-- Usuario de prueba para poder ejecutar el CRUD.
INSERT INTO usuario (nombres, apellidos, correo, contrasena)
SELECT 'Jhovan', 'Cordoba', 'jhovan.demo@correo.com', '12345'
WHERE NOT EXISTS (
    SELECT 1 FROM usuario WHERE correo = 'jhovan.demo@correo.com'
);
