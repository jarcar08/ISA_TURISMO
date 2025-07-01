CREATE DATABASE  IF NOT EXISTS `bd_turismo` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bd_turismo`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bd_turismo
-- ------------------------------------------------------
-- Server version	9.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clima`
--

DROP TABLE IF EXISTS `clima`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clima` (
  `cliId` int NOT NULL AUTO_INCREMENT,
  `cliEstado` varchar(100) DEFAULT NULL,
  `cliTempMax` decimal(10,2) DEFAULT NULL,
  `cliTempMin` decimal(10,2) DEFAULT NULL,
  `cliRecomendacion` text,
  `cliFecha` datetime DEFAULT NULL,
  `cliestId` int NOT NULL,
  PRIMARY KEY (`cliId`),
  KEY `clima_estacion_FK` (`cliestId`),
  CONSTRAINT `clima_estacion_FK` FOREIGN KEY (`cliestId`) REFERENCES `estacion` (`estId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clima`
--

LOCK TABLES `clima` WRITE;
/*!40000 ALTER TABLE `clima` DISABLE KEYS */;
INSERT INTO `clima` VALUES (1,'Soleado',29.00,19.50,'Llevar bloqueador solar.','2025-06-25 13:52:47',1),(2,'Nublado',22.00,15.00,'Usar ropa ligera pero llevar abrigo.','2025-06-25 13:52:47',2),(3,'Lluvia ligera',24.00,18.00,'Llevar paraguas o impermeable.','2025-06-25 13:52:47',3),(4,'Calor extremo',35.50,25.00,'Evitar salir al mediodía.','2025-06-25 13:52:47',4),(5,'Frío seco',19.00,4.50,'Vestir ropa térmica.','2025-06-25 13:52:47',5);
/*!40000 ALTER TABLE `clima` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estacion`
--

DROP TABLE IF EXISTS `estacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estacion` (
  `estId` int NOT NULL AUTO_INCREMENT,
  `estNumero` int DEFAULT NULL,
  `estNombre` varchar(100) DEFAULT NULL,
  `estUbicacion` varchar(100) DEFAULT NULL,
  `estEstado` tinyint DEFAULT '1',
  `estLat` decimal(9,6) DEFAULT NULL,
  `estLong` decimal(9,6) DEFAULT NULL,
  PRIMARY KEY (`estId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estacion`
--

LOCK TABLES `estacion` WRITE;
/*!40000 ALTER TABLE `estacion` DISABLE KEYS */;
INSERT INTO `estacion` VALUES (1,1,'Estación Central','Lima - Cercado',1,-12.046400,-77.042800),(2,2,'Estación Sur','Arequipa - Paucarpata',1,-16.409000,-71.537500),(3,3,'Estación Norte','Piura - Castilla',1,-5.194500,-80.632800),(4,4,'Estación Selva','Pucallpa - Manantay',1,-8.379100,-74.553900),(5,5,'Estación Sierra','Cusco - Wanchaq',1,-13.522600,-71.967300);
/*!40000 ALTER TABLE `estacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estacion_usuario`
--

DROP TABLE IF EXISTS `estacion_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estacion_usuario` (
  `euId` int NOT NULL AUTO_INCREMENT,
  `euestId` int NOT NULL,
  `euusuId` int NOT NULL,
  `euFechaSeleccion` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`euId`),
  KEY `estacion_usuario_estacion_FK` (`euestId`),
  KEY `estacion_usuario_usuario_FK` (`euusuId`),
  CONSTRAINT `estacion_usuario_estacion_FK` FOREIGN KEY (`euestId`) REFERENCES `estacion` (`estId`),
  CONSTRAINT `estacion_usuario_usuario_FK` FOREIGN KEY (`euusuId`) REFERENCES `usuario` (`usuId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estacion_usuario`
--

LOCK TABLES `estacion_usuario` WRITE;
/*!40000 ALTER TABLE `estacion_usuario` DISABLE KEYS */;
INSERT INTO `estacion_usuario` VALUES (1,1,1,'2025-06-25 23:59:15'),(2,2,1,'2025-06-25 23:59:15'),(3,5,1,'2025-06-25 23:59:15');
/*!40000 ALTER TABLE `estacion_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horario`
--

DROP TABLE IF EXISTS `horario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `horario` (
  `horId` int NOT NULL AUTO_INCREMENT,
  `horestId` int NOT NULL,
  `horLlegada` time DEFAULT NULL,
  `horSalida` time DEFAULT NULL,
  PRIMARY KEY (`horId`),
  KEY `horario_estacion_FK` (`horestId`),
  CONSTRAINT `horario_estacion_FK` FOREIGN KEY (`horestId`) REFERENCES `estacion` (`estId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horario`
--

LOCK TABLES `horario` WRITE;
/*!40000 ALTER TABLE `horario` DISABLE KEYS */;
INSERT INTO `horario` VALUES (1,1,'06:00:00','06:10:00'),(2,2,'08:00:00','08:15:00'),(3,3,'10:30:00','10:45:00'),(4,4,'13:00:00','13:10:00'),(5,5,'15:30:00','15:45:00'),(6,1,'06:00:00','06:10:00'),(7,2,'08:00:00','08:15:00'),(8,3,'10:30:00','10:45:00'),(9,4,'13:00:00','13:10:00'),(10,5,'15:30:00','15:45:00');
/*!40000 ALTER TABLE `horario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `perId` int NOT NULL AUTO_INCREMENT,
  `perDni` varchar(8) NOT NULL,
  `perApellidoPaterno` varchar(100) DEFAULT NULL,
  `perApellidoMaterno` varchar(100) DEFAULT NULL,
  `perNombres` varchar(100) DEFAULT NULL,
  `perCorreo` varchar(100) NOT NULL,
  `pertpId` int DEFAULT NULL,
  PRIMARY KEY (`perId`),
  UNIQUE KEY `persona_unique` (`perDni`),
  UNIQUE KEY `persona_unique_1` (`perCorreo`),
  KEY `persona_tipo_pasajero_FK` (`pertpId`),
  CONSTRAINT `persona_tipo_pasajero_FK` FOREIGN KEY (`pertpId`) REFERENCES `tipo_pasajero` (`tpId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (1,'12345678','Quispe','Castro','Sergio Junior','sergio@ejemplo.com',NULL),(2,'72276974','Jara','Cardenas','Jhon Erick','jaracardenasj74@gmail.com',NULL);
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posicion`
--

DROP TABLE IF EXISTS `posicion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posicion` (
  `posId` int NOT NULL AUTO_INCREMENT,
  `postrenId` int NOT NULL,
  `posestId` int NOT NULL,
  `posLat` decimal(9,6) DEFAULT NULL,
  `posLong` decimal(9,6) DEFAULT NULL,
  `posFechaHora` datetime DEFAULT NULL,
  PRIMARY KEY (`posId`),
  KEY `posicion_tren_FK` (`postrenId`),
  KEY `posicion_estacion_FK` (`posestId`),
  CONSTRAINT `posicion_estacion_FK` FOREIGN KEY (`posestId`) REFERENCES `estacion` (`estId`),
  CONSTRAINT `posicion_tren_FK` FOREIGN KEY (`postrenId`) REFERENCES `tren` (`trenId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posicion`
--

LOCK TABLES `posicion` WRITE;
/*!40000 ALTER TABLE `posicion` DISABLE KEYS */;
INSERT INTO `posicion` VALUES (1,1,1,-12.046000,-77.043000,'2025-06-25 13:54:44'),(2,2,3,-5.195000,-80.632000,'2025-06-25 13:54:44');
/*!40000 ALTER TABLE `posicion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posicion_usuario`
--

DROP TABLE IF EXISTS `posicion_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posicion_usuario` (
  `puId` int NOT NULL AUTO_INCREMENT,
  `puusuId` int NOT NULL,
  `puFechaHora` datetime DEFAULT NULL,
  `puLat` decimal(9,6) DEFAULT NULL,
  `puLong` decimal(9,6) DEFAULT NULL,
  PRIMARY KEY (`puId`),
  KEY `posicion_usuario_usuario_FK` (`puusuId`),
  CONSTRAINT `posicion_usuario_usuario_FK` FOREIGN KEY (`puusuId`) REFERENCES `usuario` (`usuId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posicion_usuario`
--

LOCK TABLES `posicion_usuario` WRITE;
/*!40000 ALTER TABLE `posicion_usuario` DISABLE KEYS */;
INSERT INTO `posicion_usuario` VALUES (1,1,'2025-06-25 13:54:56',-12.046300,-77.042500);
/*!40000 ALTER TABLE `posicion_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preferencia`
--

DROP TABLE IF EXISTS `preferencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preferencia` (
  `preId` int NOT NULL AUTO_INCREMENT,
  `preusuId` int NOT NULL,
  `preestId` int NOT NULL,
  `preIdtipozona` int NOT NULL,
  `preDificultad` enum('Baja','Media','Alta') DEFAULT NULL,
  `preDistanciaMax` decimal(10,2) DEFAULT NULL,
  `preTiempoMax` int DEFAULT NULL,
  `preFecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`preId`),
  KEY `fk_usuario_prefe_idx` (`preusuId`),
  KEY `fk_estacion_prefe_idx` (`preestId`),
  KEY `fk_tipozona_prefe_idx` (`preIdtipozona`),
  CONSTRAINT `fk_estacion_prefe` FOREIGN KEY (`preestId`) REFERENCES `estacion` (`estId`),
  CONSTRAINT `fk_tipozona_prefe` FOREIGN KEY (`preIdtipozona`) REFERENCES `tipo_zona` (`tzId`),
  CONSTRAINT `fk_usuario_prefe` FOREIGN KEY (`preusuId`) REFERENCES `usuario` (`usuId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preferencia`
--

LOCK TABLES `preferencia` WRITE;
/*!40000 ALTER TABLE `preferencia` DISABLE KEYS */;
INSERT INTO `preferencia` VALUES (1,1,1,1,'Media',6.50,1,'2025-06-30 01:30:04');
/*!40000 ALTER TABLE `preferencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `rolId` int NOT NULL AUTO_INCREMENT,
  `rolNombre` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`rolId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'ADMIN'),(2,'GUEST');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarifa`
--

DROP TABLE IF EXISTS `tarifa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarifa` (
  `taId` int NOT NULL AUTO_INCREMENT,
  `taPrecioPasaje` int DEFAULT NULL,
  `tatpId` int NOT NULL,
  PRIMARY KEY (`taId`),
  KEY `tarifa_tipo_pasajero_FK` (`tatpId`),
  CONSTRAINT `tarifa_tipo_pasajero_FK` FOREIGN KEY (`tatpId`) REFERENCES `tipo_pasajero` (`tpId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarifa`
--

LOCK TABLES `tarifa` WRITE;
/*!40000 ALTER TABLE `tarifa` DISABLE KEYS */;
INSERT INTO `tarifa` VALUES (1,5,1),(2,3,2);
/*!40000 ALTER TABLE `tarifa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_pasajero`
--

DROP TABLE IF EXISTS `tipo_pasajero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_pasajero` (
  `tpId` int NOT NULL AUTO_INCREMENT,
  `tpNombre` varchar(100) DEFAULT NULL,
  `tpDescripcion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`tpId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_pasajero`
--

LOCK TABLES `tipo_pasajero` WRITE;
/*!40000 ALTER TABLE `tipo_pasajero` DISABLE KEYS */;
INSERT INTO `tipo_pasajero` VALUES (1,'ADULTO','Pasajeros en General'),(2,'MEDIO','Universitarios, escolares y alumnos de institutos');
/*!40000 ALTER TABLE `tipo_pasajero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_zona`
--

DROP TABLE IF EXISTS `tipo_zona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_zona` (
  `tzId` int NOT NULL AUTO_INCREMENT,
  `tzNombre` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`tzId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_zona`
--

LOCK TABLES `tipo_zona` WRITE;
/*!40000 ALTER TABLE `tipo_zona` DISABLE KEYS */;
INSERT INTO `tipo_zona` VALUES (1,'Natural'),(2,'Histórica'),(3,'Aventura');
/*!40000 ALTER TABLE `tipo_zona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tren`
--

DROP TABLE IF EXISTS `tren`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tren` (
  `trenId` int NOT NULL AUTO_INCREMENT,
  `trenNombre` varchar(100) DEFAULT NULL,
  `trenEstado` enum('Activo','Mantenimiento','Fuera de Servicio') DEFAULT 'Activo',
  PRIMARY KEY (`trenId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tren`
--

LOCK TABLES `tren` WRITE;
/*!40000 ALTER TABLE `tren` DISABLE KEYS */;
INSERT INTO `tren` VALUES (1,'Tren Andino','Activo'),(2,'Tren del Norte','Mantenimiento');
/*!40000 ALTER TABLE `tren` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `usuId` int NOT NULL AUTO_INCREMENT,
  `usuNombre` varchar(100) DEFAULT NULL,
  `usuContrasenia` varchar(255) DEFAULT NULL,
  `usuPerId` int NOT NULL,
  `usuRolId` int NOT NULL,
  `usuEstado` tinyint DEFAULT '1',
  `usuFechaRegistro` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`usuId`),
  KEY `usuario_persona_FK` (`usuPerId`),
  KEY `usuario_rol_FK` (`usuRolId`),
  CONSTRAINT `usuario_persona_FK` FOREIGN KEY (`usuPerId`) REFERENCES `persona` (`perId`),
  CONSTRAINT `usuario_rol_FK` FOREIGN KEY (`usuRolId`) REFERENCES `rol` (`rolId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'sergio@ejemplo.com','$2a$10$Y6zcoBEwsj8R/1SnuvTcgeZhxOZ6F.sBiJtFjmGzWNvU67rVOrDeC',1,1,1,'2025-06-25 23:54:56'),(2,'jaracardenasj74@gmail.com','$2a$10$6X/yQqYNz7wCow4.C70y6.Q3HENQ/3FdxfLPoC0PeJYSwOlUWfcv6',2,1,1,'2025-06-30 00:51:56');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zona_turistica`
--

DROP TABLE IF EXISTS `zona_turistica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zona_turistica` (
  `zotId` int NOT NULL AUTO_INCREMENT,
  `zotNombre` varchar(100) DEFAULT NULL,
  `zotDescripcion` text,
  `zotDireccion` varchar(100) DEFAULT NULL,
  `zotDistanciaMetros` decimal(10,2) DEFAULT NULL,
  `zotTiempoCaminata` int DEFAULT NULL,
  `zotDificultad` enum('Baja','Media','Alta') DEFAULT NULL,
  `zotestId` int NOT NULL,
  `zottzId` int NOT NULL,
  PRIMARY KEY (`zotId`),
  KEY `zona_turistica_estacion_FK` (`zotestId`),
  KEY `zona_turistica_tipo_zona_FK` (`zottzId`),
  CONSTRAINT `zona_turistica_estacion_FK` FOREIGN KEY (`zotestId`) REFERENCES `estacion` (`estId`),
  CONSTRAINT `zona_turistica_tipo_zona_FK` FOREIGN KEY (`zottzId`) REFERENCES `tipo_zona` (`tzId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zona_turistica`
--

LOCK TABLES `zona_turistica` WRITE;
/*!40000 ALTER TABLE `zona_turistica` DISABLE KEYS */;
INSERT INTO `zona_turistica` VALUES (1,'Parque de la Muralla','Zona verde con restos coloniales.','Av. Abancay s/n',500.00,8,'Baja',1,2),(2,'Cerro San Cristóbal','Mirador popular de Lima.','Rímac',2000.00,30,'Media',1,1),(3,'Mirador Yanahuara','Vista panorámica de la ciudad.','Yanahuara',600.00,10,'Baja',2,2),(4,'Volcán Misti','Zona de trekking de alta dificultad.','Cercado',6000.00,180,'Alta',2,3),(5,'Catedral de Piura','Iglesia colonial del siglo XVI.','Plaza de Armas',400.00,5,'Baja',3,2),(6,'Reserva de Manglares','Área natural protegida.','Vice',10000.00,120,'Media',3,1),(7,'Laguna Yarinacocha','Zona de paseo en bote.','Manantay',3000.00,25,'Media',4,1),(8,'Centro Cultural Shipibo','Zona de exposición artesanal.','Manantay',500.00,10,'Baja',4,2),(9,'Sacsayhuamán','Ruinas incas impresionantes.','Cusco',1500.00,20,'Media',5,2),(10,'Cristo Blanco','Mirador de Cusco.','Cusco',1200.00,18,'Baja',5,1);
/*!40000 ALTER TABLE `zona_turistica` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-29 20:30:55
