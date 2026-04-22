CREATE DATABASE colegio_db;
USE colegio_db;

CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(20) UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    rol VARCHAR(50) NOT NULL
);

CREATE TABLE estudiante (
    id_estudiante INT AUTO_INCREMENT PRIMARY KEY,
    dni CHAR(8) NOT NULL UNIQUE,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE,
    sexo ENUM('M', 'F') NOT NULL,
    CHECK (dni REGEXP '^[0-9]{8}$')
);

CREATE TABLE apoderado (
    id_apoderado INT AUTO_INCREMENT PRIMARY KEY,
    dni CHAR(8) NOT NULL UNIQUE,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    direccion VARCHAR(150),
    correo VARCHAR(100),
    telefono VARCHAR(9),
    sexo ENUM('M', 'F') NOT NULL,
    CHECK (dni REGEXP '^[0-9]{8}$'),
    CHECK (telefono REGEXP '^9[0-9]{8}$')
);

CREATE TABLE estudiante_apoderado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_estudiante INT NOT NULL,
    id_apoderado INT NOT NULL,
    relacion VARCHAR(50),
    UNIQUE (id_estudiante, id_apoderado),
    FOREIGN KEY (id_estudiante) REFERENCES estudiante(id_estudiante) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_apoderado) REFERENCES apoderado(id_apoderado) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE nivel (
    id_nivel INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE matricula (
    id_matricula INT AUTO_INCREMENT PRIMARY KEY,
    id_estudiante INT NOT NULL,
    id_apoderado INT NOT NULL,
    id_nivel INT NOT NULL,
    anio INT NOT NULL CHECK (anio >= 2000),
    estado ENUM('ACTIVO', 'INACTIVO', 'CANCELADO') NOT NULL,
    observacion TEXT,
    fecha DATE DEFAULT (CURRENT_DATE),
    UNIQUE (id_estudiante, anio),
    FOREIGN KEY (id_estudiante) REFERENCES estudiante(id_estudiante) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_apoderado) REFERENCES apoderado(id_apoderado) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_nivel) REFERENCES nivel(id_nivel) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE documento (
    id_documento INT AUTO_INCREMENT PRIMARY KEY,
    id_matricula INT NOT NULL,
    nombre_archivo VARCHAR(100),
    tipo VARCHAR(50),
    fecha DATE,
    FOREIGN KEY (id_matricula) REFERENCES matricula(id_matricula) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE actividad (
    id_actividad INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    accion VARCHAR(100),
    detalle TEXT,
    fecha DATE,
    hora TIME,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO usuario (codigo, nombre, correo, contrasena, rol) VALUES
('DA03250121', 'Jesus Rivera', 'dixmarket720@gmail.com', '123456', 'Encargado'),
('DA03250122', 'Maria Lopez', 'maria@gmail.com', '123456', 'Admin'),
('DA03250123', 'Carlos Perez', 'carlos@gmail.com', '123456', 'Asistente');

INSERT INTO nivel (nombre) VALUES
('Inicial 1'),
('Inicial 2'),
('Inicial 3');

INSERT INTO estudiante (dni, nombres, apellidos, fecha_nacimiento, sexo) VALUES
('61017198', 'Jesus Angel', 'Rivera Villanueva', '2018-03-26', 'M'),
('62018299', 'Dafne', 'Romero Villanueva', '2018-07-15', 'F'),
('63019300', 'Luis', 'Garcia Torres', '2017-05-10', 'M'),
('64020411', 'Camila', 'Fernandez Rojas', '2018-11-20', 'F'),
('65021522', 'Mateo', 'Sanchez Diaz', '2017-09-02', 'M');

INSERT INTO apoderado (dni, nombres, apellidos, direccion, correo, telefono, sexo) VALUES
('72110499', 'Cristian', 'Rivera Eleuterio', 'Jr. Celestino Avila 21', 'cristian21@gmail.com', '941201921', 'M'),
('72110482', 'Hermelinda', 'Villanueva Jimenez', 'Jr. Celestino Avila', 'hermelinda1231@gmail.com', '941247056', 'F'),
('73211593', 'Jose', 'Garcia Lopez', 'Av. Peru 123', 'jose@gmail.com', '945678123', 'M'),
('74312604', 'Ana', 'Rojas Diaz', 'Av. Lima 456', 'ana@gmail.com', '987654321', 'F'),
('75413715', 'Pedro', 'Sanchez Ruiz', 'Jr. Arequipa 789', 'pedro@gmail.com', '912345678', 'M');

INSERT INTO estudiante_apoderado (id_estudiante, id_apoderado, relacion) VALUES
(1, 1, 'Padre'),
(1, 2, 'Madre'),
(2, 2, 'Madre'),
(3, 3, 'Padre'),
(4, 4, 'Madre'),
(5, 5, 'Padre');

INSERT INTO matricula (id_estudiante, id_apoderado, id_nivel, anio, estado, observacion) VALUES
(1, 1, 1, 2026, 'ACTIVO', 'Matricula inicial'),
(2, 2, 1, 2026, 'ACTIVO', 'Matricula inicial'),
(3, 3, 2, 2026, 'ACTIVO', 'Matricula nueva'),
(4, 4, 2, 2026, 'INACTIVO', 'Retiro temporal'),
(5, 5, 3, 2026, 'CANCELADO', 'Cambio de colegio');

INSERT INTO documento (id_matricula, nombre_archivo, tipo, fecha) VALUES
(1, 'Matricula1.pdf', 'PDF', '2026-04-07'),
(2, 'Matricula2.pdf', 'PDF', '2026-04-07'),
(3, 'Matricula3.pdf', 'PDF', '2026-04-08'),
(4, 'Matricula4.pdf', 'PDF', '2026-04-09'),
(5, 'Matricula5.pdf', 'PDF', '2026-04-10');

INSERT INTO actividad (id_usuario, accion, detalle, fecha, hora) VALUES
(1, 'Crear Matricula', 'Registro de matricula del estudiante DNI 61017198', '2026-04-14', '16:50:00'),
(1, 'Crear Matricula', 'Registro de matricula del estudiante DNI 62018299', '2026-04-14', '16:55:00'),
(2, 'Actualizar Matricula', 'Cambio de estado a INACTIVO del estudiante DNI 64020411', '2026-04-15', '10:20:00'),
(3, 'Cancelar Matricula', 'Cancelacion del estudiante DNI 65021522', '2026-04-15', '11:00:00'),
(2, 'Registrar Apoderado', 'Nuevo apoderado registrado DNI 73211593', '2026-04-15', '12:30:00');