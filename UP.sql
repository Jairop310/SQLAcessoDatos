CREATE TABLE videojuegos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    year INT,
    genero VARCHAR(50)
);
CREATE TABLE personajes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    tipo VARCHAR(50),
    videojuego_id INT,
    FOREIGN KEY (videojuego_id) REFERENCES videojuegos(id)
);

INSERT INTO videojuegos (name, year, genero) VALUES 
('Fornite', 2017, 'Accion'),
('League of Legends', 2009, 'MOBA'),
('Valorant', 2020, 'FPS'),
('Among Us', 2018, 'Social Deduction');

INSERT INTO personajes (name, tipo, videojuego_id) VALUES 
('John Wick', 'Arma', 1),         -- Fornite
('Zed', 'Ninja', 2),              -- League of Legends
('Fizz', 'Pez', 2),               -- League of Legends
('Pepe', 'Tanque', 3),            -- Valorant
('Impostor', 'Infiltrado', 4);    -- Among Us