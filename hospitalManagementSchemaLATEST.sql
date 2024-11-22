-- MySQL dump 10.13  Distrib 8.0.38, for macos14 (arm64)
--
-- Host: localhost    Database: hospital_management_database
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
-- Table structure for table `Admission`
--

DROP TABLE IF EXISTS `Admission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Admission` (
  `admissionId` int NOT NULL AUTO_INCREMENT,
  `patientId` int DEFAULT NULL,
  `roomId` int DEFAULT NULL,
  `doctorId` int DEFAULT NULL,
  `admissionDate` datetime DEFAULT NULL,
  `admissionType` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`admissionId`),
  KEY `patientId` (`patientId`),
  KEY `roomId` (`roomId`),
  KEY `doctorId` (`doctorId`),
  CONSTRAINT `admission_ibfk_1` FOREIGN KEY (`patientId`) REFERENCES `Patient` (`patientId`),
  CONSTRAINT `admission_ibfk_2` FOREIGN KEY (`roomId`) REFERENCES `Room` (`roomId`),
  CONSTRAINT `admission_ibfk_3` FOREIGN KEY (`doctorId`) REFERENCES `Doctor` (`doctorId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Admission`
--

LOCK TABLES `Admission` WRITE;
/*!40000 ALTER TABLE `Admission` DISABLE KEYS */;
INSERT INTO `Admission` VALUES (1,1,1,1,'2024-11-17 00:00:00','ER','Admitted'),(2,2,2,2,'2024-11-16 14:30:00','ER','Discharged'),(3,3,3,3,'2024-11-15 08:15:00','Out-patient','Completed'),(4,4,4,4,'2024-11-18 21:45:00','In-patient','Admitted'),(7,1,1,1,'2024-11-20 00:00:00','In-patient','Admitted'),(8,1,1,1,'2024-11-20 00:00:00','In-patient','Admitted'),(9,2,2,2,'2024-11-21 00:00:00','ER','Discharged'),(10,1,3,3,'2024-11-22 00:00:00','Out-patient','Completed');
/*!40000 ALTER TABLE `Admission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Appointment`
--

DROP TABLE IF EXISTS `Appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Appointment` (
  `appointmentId` int NOT NULL AUTO_INCREMENT,
  `patientId` int DEFAULT NULL,
  `doctorId` int DEFAULT NULL,
  `roomId` int DEFAULT NULL,
  `appointmentTime` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `appointmentType` varchar(255) DEFAULT NULL,
  `priority` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`appointmentId`),
  KEY `patientId` (`patientId`),
  KEY `doctorId` (`doctorId`),
  KEY `roomId` (`roomId`),
  CONSTRAINT `appointment_ibfk_1` FOREIGN KEY (`patientId`) REFERENCES `Patient` (`patientId`),
  CONSTRAINT `appointment_ibfk_2` FOREIGN KEY (`doctorId`) REFERENCES `Doctor` (`doctorId`),
  CONSTRAINT `appointment_ibfk_3` FOREIGN KEY (`roomId`) REFERENCES `Room` (`roomId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Appointment`
--

LOCK TABLES `Appointment` WRITE;
/*!40000 ALTER TABLE `Appointment` DISABLE KEYS */;
INSERT INTO `Appointment` VALUES (1,1,1,1,'2024-11-25 10:00:00','Scheduled','Follow-up','High'),(2,2,2,2,'2024-11-22 14:30:00','Cancelled','Check-up','Normal'),(3,3,3,3,'2024-11-20 08:15:00','Completed','Consultation','Normal'),(4,4,4,4,'2024-11-21 21:45:00','Scheduled','Therapy','High');
/*!40000 ALTER TABLE `Appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Bill`
--

DROP TABLE IF EXISTS `Bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Bill` (
  `billId` int NOT NULL AUTO_INCREMENT,
  `admissionId` int DEFAULT NULL,
  `patientId` int DEFAULT NULL,
  `billDate` datetime DEFAULT NULL,
  `totalAmount` decimal(10,2) DEFAULT NULL,
  `paymentStatus` varchar(255) DEFAULT NULL,
  `paymentMethod` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`billId`),
  KEY `admissionId` (`admissionId`),
  KEY `patientId` (`patientId`),
  CONSTRAINT `bill_ibfk_1` FOREIGN KEY (`admissionId`) REFERENCES `Admission` (`admissionId`),
  CONSTRAINT `bill_ibfk_2` FOREIGN KEY (`patientId`) REFERENCES `Patient` (`patientId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bill`
--

LOCK TABLES `Bill` WRITE;
/*!40000 ALTER TABLE `Bill` DISABLE KEYS */;
INSERT INTO `Bill` VALUES (1,1,1,'2024-11-19 00:00:00',1200.50,'Pending','Credit Card'),(2,2,2,'2024-11-18 00:00:00',350.75,'Paid','Cash'),(3,3,3,'2024-11-17 00:00:00',80.00,'Paid','Insurance'),(4,4,4,'2024-11-19 00:00:00',5500.20,'Pending','Credit Card');
/*!40000 ALTER TABLE `Bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Diagnosis`
--

DROP TABLE IF EXISTS `Diagnosis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Diagnosis` (
  `diagnosisId` int NOT NULL AUTO_INCREMENT,
  `diseaseId` int DEFAULT NULL,
  `admissionId` int DEFAULT NULL,
  `admissionType` varchar(255) DEFAULT NULL,
  `diagnosisDate` datetime DEFAULT NULL,
  `severity` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`diagnosisId`),
  KEY `diseaseId` (`diseaseId`),
  CONSTRAINT `diagnosis_ibfk_1` FOREIGN KEY (`diseaseId`) REFERENCES `Disease` (`diseaseId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Diagnosis`
--

LOCK TABLES `Diagnosis` WRITE;
/*!40000 ALTER TABLE `Diagnosis` DISABLE KEYS */;
INSERT INTO `Diagnosis` VALUES (1,1,1,'In-patient','2024-11-17 00:00:00','Moderate','Active'),(2,2,2,'ER','2024-11-16 00:00:00','Mild','Resolved'),(3,3,3,'Out-patient','2024-11-15 00:00:00','Severe','Active'),(4,4,4,'In-patient','2024-11-18 00:00:00','Moderate','Active');
/*!40000 ALTER TABLE `Diagnosis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Disease`
--

DROP TABLE IF EXISTS `Disease`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Disease` (
  `diseaseId` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `icdCode` varchar(255) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`diseaseId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Disease`
--

LOCK TABLES `Disease` WRITE;
/*!40000 ALTER TABLE `Disease` DISABLE KEYS */;
INSERT INTO `Disease` VALUES (1,'Hypertension','Cardiovascular','I10','High blood pressure'),(2,'Asthma','Respiratory','J45','Chronic airway inflammation'),(3,'Diabetes','Endocrine','E10-E14','High blood sugar'),(4,'Migraine','Neurological','G43','Severe headache');
/*!40000 ALTER TABLE `Disease` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Doctor`
--

DROP TABLE IF EXISTS `Doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Doctor` (
  `doctorId` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `specialization` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`doctorId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Doctor`
--

LOCK TABLES `Doctor` WRITE;
/*!40000 ALTER TABLE `Doctor` DISABLE KEYS */;
INSERT INTO `Doctor` VALUES (1,'David','Lee','Cardiology','Cardiology'),(2,'Sarah','Brown','Pediatrics','Pediatrics'),(3,'Michael','Davis','Oncology','Oncology'),(4,'Emily','Wilson','Neurology','Neurology');
/*!40000 ALTER TABLE `Doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LabActivity`
--

DROP TABLE IF EXISTS `LabActivity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `LabActivity` (
  `activityId` int NOT NULL AUTO_INCREMENT,
  `labRequestId` int DEFAULT NULL,
  `labStaffId` int DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `activityType` varchar(255) DEFAULT NULL,
  `complexity` int DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`activityId`),
  KEY `labRequestId` (`labRequestId`),
  KEY `labStaffId` (`labStaffId`),
  CONSTRAINT `labactivity_ibfk_1` FOREIGN KEY (`labRequestId`) REFERENCES `LabRequest` (`requestId`),
  CONSTRAINT `labactivity_ibfk_2` FOREIGN KEY (`labStaffId`) REFERENCES `LabStaff` (`staffId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LabActivity`
--

LOCK TABLES `LabActivity` WRITE;
/*!40000 ALTER TABLE `LabActivity` DISABLE KEYS */;
INSERT INTO `LabActivity` VALUES (1,1,1,'2024-11-17 09:00:00','2024-11-17 10:00:00','Blood Test',2,'Completed'),(2,2,3,'2024-11-16 15:00:00','2024-11-16 16:30:00','Urine Test',1,'Completed'),(3,3,2,'2024-11-15 10:00:00','2024-11-15 12:00:00','Biopsy',4,'Completed'),(4,4,4,'2024-11-18 14:00:00','2024-11-18 15:30:00','X-ray',3,'Completed');
/*!40000 ALTER TABLE `LabActivity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Laboratory`
--

DROP TABLE IF EXISTS `Laboratory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Laboratory` (
  `laboratoryId` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `contactNumber` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`laboratoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Laboratory`
--

LOCK TABLES `Laboratory` WRITE;
/*!40000 ALTER TABLE `Laboratory` DISABLE KEYS */;
INSERT INTO `Laboratory` VALUES (1,'Lab A','555-1234'),(2,'Lab B','555-5678');
/*!40000 ALTER TABLE `Laboratory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LabRequest`
--

DROP TABLE IF EXISTS `LabRequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `LabRequest` (
  `requestId` int NOT NULL AUTO_INCREMENT,
  `patientId` int DEFAULT NULL,
  `doctorId` int DEFAULT NULL,
  `laboratoryId` int DEFAULT NULL,
  `requestDate` datetime DEFAULT NULL,
  `cost` decimal(10,2) NOT NULL,
  PRIMARY KEY (`requestId`),
  KEY `patientId` (`patientId`),
  KEY `doctorId` (`doctorId`),
  KEY `laboratoryId` (`laboratoryId`),
  CONSTRAINT `labrequest_ibfk_1` FOREIGN KEY (`patientId`) REFERENCES `Patient` (`patientId`),
  CONSTRAINT `labrequest_ibfk_2` FOREIGN KEY (`doctorId`) REFERENCES `Doctor` (`doctorId`),
  CONSTRAINT `labrequest_ibfk_3` FOREIGN KEY (`laboratoryId`) REFERENCES `Laboratory` (`laboratoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LabRequest`
--

LOCK TABLES `LabRequest` WRITE;
/*!40000 ALTER TABLE `LabRequest` DISABLE KEYS */;
INSERT INTO `LabRequest` VALUES (1,1,1,1,'2024-11-17 00:00:00',500.00),(2,2,2,2,'2024-11-16 00:00:00',300.00),(3,3,3,1,'2024-11-15 00:00:00',200.00),(4,4,4,2,'2024-11-18 00:00:00',500.00);
/*!40000 ALTER TABLE `LabRequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LabStaff`
--

DROP TABLE IF EXISTS `LabStaff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `LabStaff` (
  `staffId` int NOT NULL AUTO_INCREMENT,
  `laboratoryId` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `shift` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`staffId`),
  KEY `laboratoryId` (`laboratoryId`),
  CONSTRAINT `labstaff_ibfk_1` FOREIGN KEY (`laboratoryId`) REFERENCES `Laboratory` (`laboratoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LabStaff`
--

LOCK TABLES `LabStaff` WRITE;
/*!40000 ALTER TABLE `LabStaff` DISABLE KEYS */;
INSERT INTO `LabStaff` VALUES (1,1,'Technician A','Lab Technician','Day'),(2,1,'Scientist B','Lab Scientist','Night'),(3,2,'Technician C','Lab Technician','Day'),(4,2,'Scientist D','Lab Scientist','Evening');
/*!40000 ALTER TABLE `LabStaff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Maintenance`
--

DROP TABLE IF EXISTS `Maintenance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Maintenance` (
  `maintenanceId` int NOT NULL AUTO_INCREMENT,
  `roomId` int DEFAULT NULL,
  `scheduleDate` datetime DEFAULT NULL,
  `completionDate` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `notes` text,
  PRIMARY KEY (`maintenanceId`),
  KEY `roomId` (`roomId`),
  CONSTRAINT `maintenance_ibfk_1` FOREIGN KEY (`roomId`) REFERENCES `Room` (`roomId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Maintenance`
--

LOCK TABLES `Maintenance` WRITE;
/*!40000 ALTER TABLE `Maintenance` DISABLE KEYS */;
INSERT INTO `Maintenance` VALUES (1,1,'2024-11-20 00:00:00','2024-11-20 00:00:00','Completed','General cleaning'),(2,2,'2024-11-22 00:00:00',NULL,'Scheduled','Repair faulty equipment'),(3,3,'2024-11-25 00:00:00',NULL,'Scheduled','Deep cleaning'),(4,4,'2024-11-21 00:00:00','2024-11-21 00:00:00','Completed','Sterilization');
/*!40000 ALTER TABLE `Maintenance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Patient`
--

DROP TABLE IF EXISTS `Patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Patient` (
  `patientId` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `birthDate` date DEFAULT NULL,
  `HMO` varchar(255) DEFAULT NULL,
  `medicalHistory` text,
  PRIMARY KEY (`patientId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Patient`
--

LOCK TABLES `Patient` WRITE;
/*!40000 ALTER TABLE `Patient` DISABLE KEYS */;
INSERT INTO `Patient` VALUES (1,'John','Doe','1980-05-15','HMO A','No known allergies'),(2,'Jane','Smith','1975-11-20','HMO B','History of asthma'),(3,'Peter','Jones','1992-03-10','HMO C','None'),(4,'Alice','Williams','1968-08-01','HMO A','Diabetic');
/*!40000 ALTER TABLE `Patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Room`
--

DROP TABLE IF EXISTS `Room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Room` (
  `roomId` int NOT NULL AUTO_INCREMENT,
  `roomType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `isAvailable` tinyint(1) DEFAULT NULL,
  `lastMaintenance` date DEFAULT NULL,
  `cost` decimal(10,2) NOT NULL,
  PRIMARY KEY (`roomId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Room`
--

LOCK TABLES `Room` WRITE;
/*!40000 ALTER TABLE `Room` DISABLE KEYS */;
INSERT INTO `Room` VALUES (1,'Private',1,'2024-11-18',10000.00),(2,'Semi-Private',0,'2024-11-15',5000.00),(3,'Ward',1,'2024-11-10',2500.00),(4,'ICU',0,'2024-11-19',15000.00);
/*!40000 ALTER TABLE `Room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Treatment`
--

DROP TABLE IF EXISTS `Treatment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Treatment` (
  `treatmentId` int NOT NULL AUTO_INCREMENT,
  `patientId` int DEFAULT NULL,
  `doctorId` int DEFAULT NULL,
  `roomId` int DEFAULT NULL,
  `admissionDate` datetime DEFAULT NULL,
  `treatmentType` varchar(255) DEFAULT NULL,
  `description` text,
  `cost` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`treatmentId`),
  KEY `patientId` (`patientId`),
  KEY `doctorId` (`doctorId`),
  KEY `roomId` (`roomId`),
  CONSTRAINT `treatment_ibfk_1` FOREIGN KEY (`patientId`) REFERENCES `Patient` (`patientId`),
  CONSTRAINT `treatment_ibfk_2` FOREIGN KEY (`doctorId`) REFERENCES `Doctor` (`doctorId`),
  CONSTRAINT `treatment_ibfk_3` FOREIGN KEY (`roomId`) REFERENCES `Room` (`roomId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Treatment`
--

LOCK TABLES `Treatment` WRITE;
/*!40000 ALTER TABLE `Treatment` DISABLE KEYS */;
INSERT INTO `Treatment` VALUES (1,1,1,1,'2024-11-17 00:00:00','Surgery','Cardiac surgery',8000.00),(2,2,2,2,'2024-11-16 00:00:00','Medication','Antibiotics',50.50),(3,3,3,3,'2024-11-15 00:00:00','Chemotherapy','Cycle 1',1500.00),(4,4,4,4,'2024-11-18 00:00:00','Physical Therapy','Session 1',150.00);
/*!40000 ALTER TABLE `Treatment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-22 15:30:07
