CREATE DATABASE  IF NOT EXISTS `UBA_agencia_artistas` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `UBA_agencia_artistas`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: UBA_agencia_artistas
-- ------------------------------------------------------
-- Server version	9.0.1

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
-- Table structure for table `EXHIBICION`
--

DROP TABLE IF EXISTS `EXHIBICION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `EXHIBICION` (
  `EXP_ID` int NOT NULL,
  `PINT_ID` int NOT NULL,
  PRIMARY KEY (`EXP_ID`,`PINT_ID`),
  KEY `PINT_ID` (`PINT_ID`),
  CONSTRAINT `EXHIBICION_ibfk_1` FOREIGN KEY (`EXP_ID`) REFERENCES `EXPOSICION` (`EXP_ID`) ON DELETE CASCADE,
  CONSTRAINT `EXHIBICION_ibfk_2` FOREIGN KEY (`PINT_ID`) REFERENCES `PINTURA` (`PINT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EXHIBICION`
--

LOCK TABLES `EXHIBICION` WRITE;
/*!40000 ALTER TABLE `EXHIBICION` DISABLE KEYS */;
/*!40000 ALTER TABLE `EXHIBICION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EXPOSICION`
--

DROP TABLE IF EXISTS `EXPOSICION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `EXPOSICION` (
  `EXP_ID` int NOT NULL AUTO_INCREMENT,
  `EXP_NAME` varchar(100) DEFAULT NULL,
  `EXP_START_DATE` date DEFAULT NULL,
  `EXP_END_DATE` date DEFAULT NULL,
  `GAL_ID` int DEFAULT NULL,
  PRIMARY KEY (`EXP_ID`),
  KEY `GAL_ID` (`GAL_ID`),
  CONSTRAINT `EXPOSICION_ibfk_1` FOREIGN KEY (`GAL_ID`) REFERENCES `GALERIA` (`GAL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EXPOSICION`
--

LOCK TABLES `EXPOSICION` WRITE;
/*!40000 ALTER TABLE `EXPOSICION` DISABLE KEYS */;
INSERT INTO `EXPOSICION` VALUES (1,'Pista de baile','2024-11-25','2024-11-27',1),(2,'El arte del ahora: Nuevas perspectivas','2024-11-27','2024-11-30',3),(3,'Renacimiento en la pintura europea','2024-11-30','2024-12-01',4),(4,'Explorando el cubismo: Picasso y más allá','2024-11-30','2024-12-10',1),(5,'Mundos virtuales: Arte en la era digital','2024-11-22','2024-11-28',5),(6,'Graffiti y murales: El arte urbano en evolución','2024-11-28','2024-12-25',6);
/*!40000 ALTER TABLE `EXPOSICION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GALERIA`
--

DROP TABLE IF EXISTS `GALERIA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `GALERIA` (
  `GAL_ID` int NOT NULL AUTO_INCREMENT,
  `GAL_NAME` varchar(100) DEFAULT NULL,
  `GAL_ADDRESS` varchar(255) DEFAULT NULL,
  `GAL_PHONE` varchar(15) DEFAULT NULL,
  `GAL_EMAIL` varchar(100) DEFAULT NULL,
  `GAL_WEBSITE` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`GAL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GALERIA`
--

LOCK TABLES `GALERIA` WRITE;
/*!40000 ALTER TABLE `GALERIA` DISABLE KEYS */;
INSERT INTO `GALERIA` VALUES (1,'Galería de Arte Moderno','123 Calle Falsa, Ciudad','987654321','contacto@galeria.com','www.galeria.com'),(2,'Galería de Arte Barroco','156 Calle Federico, Ciudad','9989898','contacto99@galeria.com','www.galeria.com'),(3,'Galería de Arte Contemporáneo','156 Calle Federico, Ciudad','9989898','contacto@galeria.com','www.galerias.com'),(4,'Galería de Arte Clásico','156 Calle Federico, Ciudad','9989898','contacto@galeria.com','www.galerias.com'),(5,'Galería de Arte Digital','156 Calle Federico, Ciudad','9989898','contacto@galeria.com','www.galerias.com'),(6,'Galería de Arte en la calle','156 Calle Federico, Ciudad','9989898','contacto@galeria.com','www.galerias.com');
/*!40000 ALTER TABLE `GALERIA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PINTOR`
--

DROP TABLE IF EXISTS `PINTOR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PINTOR` (
  `PIN_ID` int NOT NULL AUTO_INCREMENT,
  `PIN_NAME` varchar(100) DEFAULT NULL,
  `PIN_CITY` varchar(100) DEFAULT NULL,
  `PIN_BORN_DATE` date DEFAULT NULL,
  `PIN_PHONE` varchar(15) DEFAULT NULL,
  `PIN_EMAIL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PIN_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PINTOR`
--

LOCK TABLES `PINTOR` WRITE;
/*!40000 ALTER TABLE `PINTOR` DISABLE KEYS */;
INSERT INTO `PINTOR` VALUES (1,'Pablo Picasso','Málaga','1881-10-25','123456789','picasso@art.com');
/*!40000 ALTER TABLE `PINTOR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PINTURA`
--

DROP TABLE IF EXISTS `PINTURA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PINTURA` (
  `PINT_ID` int NOT NULL AUTO_INCREMENT,
  `PINT_NAME` varchar(100) DEFAULT NULL,
  `PINT_DATE` date DEFAULT NULL,
  `PINT_TECH` int DEFAULT NULL,
  `PINT_VALUE` decimal(10,2) DEFAULT NULL,
  `PIN_ID` int DEFAULT NULL,
  PRIMARY KEY (`PINT_ID`),
  KEY `PIN_ID` (`PIN_ID`),
  KEY `PINT_TECH` (`PINT_TECH`),
  CONSTRAINT `PINTURA_ibfk_1` FOREIGN KEY (`PIN_ID`) REFERENCES `PINTOR` (`PIN_ID`),
  CONSTRAINT `PINTURA_ibfk_2` FOREIGN KEY (`PINT_TECH`) REFERENCES `TECNICA` (`TECN_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PINTURA`
--

LOCK TABLES `PINTURA` WRITE;
/*!40000 ALTER TABLE `PINTURA` DISABLE KEYS */;
INSERT INTO `PINTURA` VALUES (1,'Les Demoiselles d\'Avignon','1907-01-01',1,1000000.00,NULL),(2,'Les Demoiselles d\'Avignon','1907-01-01',1,1000000.00,NULL),(3,'Les Demoiselles d\'Avignon','1907-01-01',1,1000000.00,1);
/*!40000 ALTER TABLE `PINTURA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TECNICA`
--

DROP TABLE IF EXISTS `TECNICA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TECNICA` (
  `TECN_ID` int NOT NULL AUTO_INCREMENT,
  `TECN_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`TECN_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TECNICA`
--

LOCK TABLES `TECNICA` WRITE;
/*!40000 ALTER TABLE `TECNICA` DISABLE KEYS */;
INSERT INTO `TECNICA` VALUES (1,'OLEO');
/*!40000 ALTER TABLE `TECNICA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USUARIO`
--

DROP TABLE IF EXISTS `USUARIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `USUARIO` (
  `USER_NAME` varchar(20) NOT NULL,
  `USER_PASSWORD` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`USER_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USUARIO`
--

LOCK TABLES `USUARIO` WRITE;
/*!40000 ALTER TABLE `USUARIO` DISABLE KEYS */;
INSERT INTO `USUARIO` VALUES ('admin','49eb5c4b494de38845d29aa95dda103b');
/*!40000 ALTER TABLE `USUARIO` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-25  1:27:53
