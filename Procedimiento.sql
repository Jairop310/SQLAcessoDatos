DELIMITER //
CREATE PROCEDURE actualizar_valoracion_total(videojuego_nombre VARCHAR(100))
BEGIN
    DECLARE valoracion_videojuego INT DEFAULT 0;
    DECLARE valoracion_personajes INT DEFAULT 0;

    SELECT valoracion INTO valoracion_videojuego
    FROM videojuegos
    WHERE name = videojuego_nombre;

    SELECT SUM(valoracion) INTO valoracion_personajes
    FROM personajes
    WHERE videojuego_name = videojuego_nombre;

    UPDATE videojuegos
    SET valoracion_total = valoracion_videojuego + valoracion_personajes
    WHERE name = videojuego_nombre;
END //
DELIMITER ;
