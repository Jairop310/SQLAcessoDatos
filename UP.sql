CREATE TABLE videojuegos (    
    name VARCHAR(100) Primary Key,
    year INT,
    genero VARCHAR(50),
    precio_unitario INT,
    precio_total INT
);
CREATE TABLE personajes (
    name VARCHAR(100) Primary Key,
    tipo VARCHAR(50),
    videojuego_name varchar(100),
    precio_unitario INT,
    FOREIGN KEY (videojuego_name) REFERENCES videojuegos(name)
);

INSERT INTO videojuegos (name, year, genero, precio_unitario) VALUES 
('Fornite', 2017, 'Accion', 80),
('League of Legends', 2009, 'MOBA', 70),
('Valorant', 2020, 'FPS', 70),
('Among Us', 2018, 'Social Deduction', 70);

INSERT INTO personajes (name, tipo, videojuego_name, precio_unitario) VALUES 
('John Wick', 'Arma', 'Fornite', 60),         
('Zed', 'Ninja', 'League of Legends', 40),             
('Fizz', 'Pez', 'League of Legends', 50),               
('Pepe', 'Tanque', 'Valorant', 70),           
('Impostor', 'Infiltrado', 'Among Us', 20);    