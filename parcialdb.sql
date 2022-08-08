-- CREATE DATABASE parcialdb;
USE parcialdb;

CREATE TABLE IF NOT EXISTS usuario (
                           `Nombre` varchar(50) NOT NULL,
                           `Apellido` varchar(50) NOT NULL,
                           `Mail` varchar(50) NOT NULL,
                           `Contraseña` varchar(50) NOT NULL,
                           `PaisOrigen` varchar(50) NOT NULL,
                           `Id` int(11) NOT NULL,
                           `Categoria` varchar(50) NOT NULL,
                           `Pago` bool NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `usuario`
--
ALTER TABLE usuario
    ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE usuario
    MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;
