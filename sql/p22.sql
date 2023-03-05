-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-03-2023 a las 17:16:54
-- Versión del servidor: 10.1.38-MariaDB
-- Versión de PHP: 5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `p22`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `apellido` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `apellido2` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin NOT NULL,
  `telefono` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `pass` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `intentos` int(11) DEFAULT NULL,
  `rolAdmin` bit(1) DEFAULT NULL,
  `idioma` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `provincia` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `poblacion` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `apellido`, `apellido2`, `email`, `telefono`, `pass`, `intentos`, `rolAdmin`, `idioma`, `provincia`, `poblacion`) VALUES
(1, 'bddAngel', 'Blasco', 'Cano', 'angel@blasco.es', '111111111', 'd8MeP4zGXPM4qJGUPLXAzawYDnp8tkyYiBBDxim3egFo0X0gXfVN3aRECNFtOPst', 5, b'0', 'es', 'Asturias', 'Aller'),
(2, 'bddBeatriz', 'Cano', 'Domingo', 'beatriz@cano.es', '222222222', '0QeuCMNAnCXA3YmUpOWfjs62ERUze8/HbC9W7IZLNZSMbPXEdw9jIKS/M24UBAdE', 5, b'1', 'es', 'Burgos', 'Carazo'),
(3, 'bddCarlos', 'Domingo', 'Egido', 'carlos@domingo.es', '333333333', 'hlf7dB1h/9ATZJU9sfefkCiA5uwMORe1mvc55lw5r6wdT4gbUPdRpS0wVVoH2Joz', 5, b'0', 'en', 'Cuenca', 'Fuentes'),
(4, 'bddDiego', 'Egido', 'Floren', 'diego@egido.es', '444444444', 'FFtzNLNPWwvmp6PhY6pST0Z9+tRPdBr/x3aDwX3wGRxnrHuN7yOnHQ9ABkCZeb6s', 5, b'0', 'es', 'Toledo', 'Bargas'),
(5, 'bddEric', 'Floren', 'Guilabert', 'eric@floren.es', '555555555', '3FJ3w8drRJbs71crYBBP3LMVqOrgs0krmgObgMBVqFhsH51ushVT8VfwRFlThb7A', 5, b'0', 'en', 'Navarra', 'Allo'),
(6, 'bddFrancisco', 'Guilabert', 'Huerta', 'fran@guilabert.es', '666666666', 'PyyMPRRBv7lIuZD9zsctKnnIqR4fsQ5bKt796AFAZcLIdaFKoUDaabd5zKJrUC8I', 5, b'1', 'es', 'Lugo', 'Cervo');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
