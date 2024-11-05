DELIMITER //
CREATE PROCEDURE actualizar_valoracion_total(videojuego_nombre VARCHAR(100))
BEGIN
    DECLARE precio_videojuego INT DEFAULT 0;
    DECLARE precio_personajes INT DEFAULT 0;

    SELECT precio_unitario INTO precio_videojuego
    FROM videojuegos
    WHERE videojuegos.name = videojuego_nombre;

    SELECT SUM(precio_unitario) INTO precio_personajes
    FROM personajes
    WHERE personajes.videojuego_name = videojuego_nombre;

    UPDATE videojuegos
    SET precio_total = precio_videojuego + precio_personajes
    WHERE videojuegos.name = videojuego_nombre;
END //
DELIMITER ;
