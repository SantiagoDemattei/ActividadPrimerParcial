-- CREATE DATABASE parcialdb;
USE parcialdb;

CREATE TABLE IF NOT EXISTS usuario (
                           `Nombre` varchar(255) NOT NULL,
                           `Apellido` varchar(255) NOT NULL,
                           `Mail` varchar(255) NOT NULL,
                           `Contraseña` varchar(255) NOT NULL,
                           `PaisOrigen` varchar(255) NOT NULL,
                           `Id` INT NOT NULL,
                           `Categoria` INT NOT NULL,
                           `PagaMembresia` bool NOT NULL,
                           foreign key (Categoria) references categoria(Categoria_id)
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
