CREATE TABLE videojuegos (    
    name VARCHAR(100) Primary Key,
    year INT,
    genero VARCHAR(50)
);
CREATE TABLE personajes (
    name VARCHAR(100) Primary Key,
    tipo VARCHAR(50),
    videojuego_name varchar(100),
    FOREIGN KEY (videojuego_name) REFERENCES videojuegos(name)
);

INSERT INTO videojuegos (name, year, genero) VALUES 
('Fornite', 2017, 'Accion'),
('League of Legends', 2009, 'MOBA'),
('Valorant', 2020, 'FPS'),
('Among Us', 2018, 'Social Deduction');

INSERT INTO personajes (name, tipo, videojuego_name) VALUES 
('John Wick', 'Arma', 'Fornite'),         
('Zed', 'Ninja', 'League of Legends'),             
('Fizz', 'Pez', 'League of Legends'),               
('Pepe', 'Tanque', 'Valorant'),           
('Impostor', 'Infiltrado', 'Among Us');    