-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: resident_room_db
-- ------------------------------------------------------
-- Server version	5.7.30-log

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
-- Table structure for table `tbl_facility`
--

DROP TABLE IF EXISTS `tbl_facility`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_facility` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `facility_admin_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6410jfforx38e5p498x1pv8al` (`facility_admin_id`),
  CONSTRAINT `FK6410jfforx38e5p498x1pv8al` FOREIGN KEY (`facility_admin_id`) REFERENCES `tbl_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_facility`
--

LOCK TABLES `tbl_facility` WRITE;
/*!40000 ALTER TABLE `tbl_facility` DISABLE KEYS */;
INSERT INTO `tbl_facility` VALUES (1,'2022-07-21 21:32:59.102000','2022-07-21 21:32:59.102000','3f5c0eb6-cb90-482b-8bf4-8265ac5cce01','facility1','facility1',1000,1),(2,'2022-07-21 21:33:21.213000','2022-07-21 21:33:21.213000','c3c110ec-7730-46ea-9a35-50e5a1869744','facility2','facility2',1000,2),(3,'2022-07-21 21:33:34.115000','2022-07-21 21:33:34.115000','2fe94a7d-fd7a-44a5-b517-39ee10139317','facility3','facility3',1000,1);
/*!40000 ALTER TABLE `tbl_facility` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_facility_room`
--

DROP TABLE IF EXISTS `tbl_facility_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_facility_room` (
  `facility_id` bigint(20) NOT NULL,
  `room_id` bigint(20) NOT NULL,
  KEY `FK3oarugnhvync968hren231swt` (`room_id`),
  KEY `FKi6r4g8y76xqurped4qpvln99y` (`facility_id`),
  CONSTRAINT `FK3oarugnhvync968hren231swt` FOREIGN KEY (`room_id`) REFERENCES `tbl_room` (`id`),
  CONSTRAINT `FKi6r4g8y76xqurped4qpvln99y` FOREIGN KEY (`facility_id`) REFERENCES `tbl_facility` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_facility_room`
--

LOCK TABLES `tbl_facility_room` WRITE;
/*!40000 ALTER TABLE `tbl_facility_room` DISABLE KEYS */;
INSERT INTO `tbl_facility_room` VALUES (1,1),(1,2),(2,3);
/*!40000 ALTER TABLE `tbl_facility_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_resident`
--

DROP TABLE IF EXISTS `tbl_resident`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_resident` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `room_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsuokspyvdn2wy4xs04aj9hghb` (`room_id`),
  KEY `FKgtvgcdfbj4naqfbrjgb2l5yoh` (`user_id`),
  CONSTRAINT `FKgtvgcdfbj4naqfbrjgb2l5yoh` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`id`),
  CONSTRAINT `FKsuokspyvdn2wy4xs04aj9hghb` FOREIGN KEY (`room_id`) REFERENCES `tbl_room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_resident`
--

LOCK TABLES `tbl_resident` WRITE;
/*!40000 ALTER TABLE `tbl_resident` DISABLE KEYS */;
INSERT INTO `tbl_resident` VALUES (1,'2022-07-21 21:28:59.586000','2022-07-21 21:35:29.835000',1,3),(2,'2022-07-21 21:29:26.834000','2022-07-21 21:29:26.834000',NULL,4);
/*!40000 ALTER TABLE `tbl_resident` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_room`
--

DROP TABLE IF EXISTS `tbl_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_room` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `room_code` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `room_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsq26n7x09891tujjx47klp3jk` (`room_type_id`),
  CONSTRAINT `FKsq26n7x09891tujjx47klp3jk` FOREIGN KEY (`room_type_id`) REFERENCES `tbl_room_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_room`
--

LOCK TABLES `tbl_room` WRITE;
/*!40000 ALTER TABLE `tbl_room` DISABLE KEYS */;
INSERT INTO `tbl_room` VALUES (1,'2022-07-21 21:31:18.915000','2022-07-21 21:31:18.915000','room1','47d1e249-4f14-4f0b-bfbf-26c0db432db0',0,1),(2,'2022-07-21 21:31:30.002000','2022-07-21 21:31:30.002000','room2','3794fe31-915f-4562-a2c4-f04dbb567116',0,1),(3,'2022-07-21 21:31:37.960000','2022-07-21 21:31:37.960000','room3','bf6824a0-cf3a-45b0-850e-93ebb98b16d0',0,2);
/*!40000 ALTER TABLE `tbl_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_room_type`
--

DROP TABLE IF EXISTS `tbl_room_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_room_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `room_type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_room_type`
--

LOCK TABLES `tbl_room_type` WRITE;
/*!40000 ALTER TABLE `tbl_room_type` DISABLE KEYS */;
INSERT INTO `tbl_room_type` VALUES (1,'2022-07-21 21:30:33.292000','2022-07-21 21:30:33.292000','roomType 1',1000,'roomType1'),(2,'2022-07-21 21:30:45.138000','2022-07-21 21:30:45.138000','roomType 2',1000,'roomType2');
/*!40000 ALTER TABLE `tbl_room_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_user`
--

DROP TABLE IF EXISTS `tbl_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `active` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `passport_no` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `permissions` varchar(255) DEFAULT NULL,
  `phone_no` varchar(255) DEFAULT NULL,
  `roles` varchar(255) DEFAULT NULL,
  `user_group_id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_user`
--

LOCK TABLES `tbl_user` WRITE;
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` VALUES (1,'2022-07-21 21:27:08.458000','2022-07-21 21:27:08.458000',1,'john@gmail.com','John','male','Smith','45784589564','John123','','0778889995','',1,'John'),(2,'2022-07-21 21:28:16.946000','2022-07-21 21:28:16.946000',1,'rayan@gmail.com','Rayan','male','Smith','45784589564','John123','','0778889995','',1,'Rayan'),(3,'2022-07-21 21:28:59.451000','2022-07-21 21:28:59.451000',1,'kamal@gmail.com','kamal','male','Smith','45784589564','kamal123','','0778889995','',2,'Kamal'),(4,'2022-07-21 21:29:25.957000','2022-07-21 21:29:25.957000',1,'sumith@gmail.com','Sumith','male','Sumith','45784589564','kamal123','','0778889995','',2,'Sumith');
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-21 21:45:09
