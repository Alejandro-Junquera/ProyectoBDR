CREATE TABLE Alumno(
dni VARCHAR(9) NOT NULL,
nombre VARCHAR(20),
apellidos VARCHAR(50),
fecha_nacimiento DATE,
telefono int(9),
clave VARCHAR(20),
PRIMARY KEY (dni)
);

CREATE TABLE Profesor(
dni VARCHAR(9) NOT NULL,
nombre VARCHAR(20),
apellidos VARCHAR(50),
email VARCHAR(50),
clave VARCHAR(20),
PRIMARY KEY(dni)
);

CREATE TABLE Asignatura(
    id int NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50),
    horasSemanales int(2),
    dni_pro VARCHAR(9) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (dni_pro) REFERENCES Profesor(dni)
);

CREATE TABLE RA(
    id int NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50),
    descripcion VARCHAR(100),
    ponderacion FLOAT(2),
    id_asi int NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (id_asi) REFERENCES Asignatura(id)
);

CREATE TABLE Matricula(
    dni_alu VARCHAR(9) NOT NULL,
    id_asi int NOT NULL,
    PRIMARY KEY(dni_alu,id_asi),
    FOREIGN KEY (dni_alu) REFERENCES Alumno(dni),
    FOREIGN KEY (id_asi) REFERENCES Asignatura(id)
);

CREATE TABLE Califica(
    dni_alu VARCHAR(9) NOT NULL,
    id_ra int NOT NULL,
    nota float(3,2),
    PRIMARY KEY(dni_alu,id_ra),
    FOREIGN KEY(dni_alu) REFERENCES Alumno(dni),
    FOREIGN KEY (id_ra) REFERENCES RA(id)
);