-- MySQL dump 10.13  Distrib 5.6.19, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: rh_db
-- ------------------------------------------------------
-- Server version	5.6.19-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Location`
--

DROP TABLE IF EXISTS `Location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Location` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `ancestry` char(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `prefix` char(1) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `tenant_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hlpmn5gnkoaxb9yn1arp9svw6` (`tenant_id`),
  CONSTRAINT `FK_hlpmn5gnkoaxb9yn1arp9svw6` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Location`
--

LOCK TABLES `Location` WRITE;
/*!40000 ALTER TABLE `Location` DISABLE KEYS */;
/*!40000 ALTER TABLE `Location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Log`
--

DROP TABLE IF EXISTS `Log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `message` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Log`
--

LOCK TABLES `Log` WRITE;
/*!40000 ALTER TABLE `Log` DISABLE KEYS */;
/*!40000 ALTER TABLE `Log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activities`
--

DROP TABLE IF EXISTS `activities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activities` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cetgegf2d5qvgmxj7xrjhu1cq` (`project_id`),
  CONSTRAINT `FK_cetgegf2d5qvgmxj7xrjhu1cq` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activities`
--

LOCK TABLES `activities` WRITE;
/*!40000 ALTER TABLE `activities` DISABLE KEYS */;
INSERT INTO `activities` VALUES (9,'2014-10-29 20:19:28','2014-10-29 20:19:28','ZSAWDEFRGTHYJ',6),(10,'2014-10-29 20:27:12','2014-10-29 20:27:12','dfsgh',3),(11,'2014-10-29 20:30:09','2014-10-29 20:30:09','abc',4),(12,'2014-10-29 20:31:12','2014-10-29 20:31:12','ewqwqeqw',3),(13,'2014-10-29 20:32:55','2014-10-29 20:32:55','acasdds',4),(15,'2014-10-30 07:19:59','2014-10-30 07:19:59','xpt5',5),(31,'2014-10-30 10:45:58','2014-10-30 10:45:58','defrgbterfre',2),(32,'2014-10-30 19:10:37','2014-10-30 19:10:37','Recrutar Candidatos',1),(33,'2014-10-30 21:35:26','2014-10-30 21:35:26','Entrevistas',1),(34,'2014-11-05 07:52:16','2014-11-05 07:52:16','Recrutar sindicatos',1),(36,'2014-11-06 12:10:57','2014-11-06 12:10:57','sdwfgnh m',7),(37,'2014-11-06 12:10:59','2014-11-06 12:10:59','weqedfrg',7);
/*!40000 ALTER TABLE `activities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_projects`
--

DROP TABLE IF EXISTS `admin_projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_projects` (
  `project_id` bigint(20) NOT NULL,
  `admin_id` bigint(20) NOT NULL,
  KEY `FK_ermep7oskf1gu52qsr841hnxf` (`admin_id`),
  KEY `FK_b2pg0e0j8n77j2if635b7hqmo` (`project_id`),
  CONSTRAINT `FK_b2pg0e0j8n77j2if635b7hqmo` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`),
  CONSTRAINT `FK_ermep7oskf1gu52qsr841hnxf` FOREIGN KEY (`admin_id`) REFERENCES `individuals` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_projects`
--

LOCK TABLES `admin_projects` WRITE;
/*!40000 ALTER TABLE `admin_projects` DISABLE KEYS */;
INSERT INTO `admin_projects` VALUES (1,1),(2,1),(3,1),(3,2),(4,3),(5,2),(6,2),(7,1);
/*!40000 ALTER TABLE `admin_projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_points`
--

DROP TABLE IF EXISTS `contact_points`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_points` (
  `DTYPE` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `value` int(11) DEFAULT NULL,
  `prefix` varchar(255) DEFAULT NULL,
  `employee_id` bigint(20) DEFAULT NULL,
  `candidate_id` bigint(20) DEFAULT NULL,
  `FROM_CLASS` varchar(31) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `holder_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cpqkjnppemp8bc9ja7ulqkjni` (`employee_id`),
  KEY `FK_chv7oq0ekhg6l8snyarhh2a74` (`candidate_id`),
  KEY `FK_r38raff7yixsx8u5ypcqr27l2` (`holder_id`),
  CONSTRAINT `FK_chv7oq0ekhg6l8snyarhh2a74` FOREIGN KEY (`candidate_id`) REFERENCES `individuals` (`id`),
  CONSTRAINT `FK_cpqkjnppemp8bc9ja7ulqkjni` FOREIGN KEY (`employee_id`) REFERENCES `individuals` (`id`),
  CONSTRAINT `FK_r38raff7yixsx8u5ypcqr27l2` FOREIGN KEY (`holder_id`) REFERENCES `individuals` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_points`
--

LOCK TABLES `contact_points` WRITE;
/*!40000 ALTER TABLE `contact_points` DISABLE KEYS */;
/*!40000 ALTER TABLE `contact_points` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers_projects`
--

DROP TABLE IF EXISTS `customers_projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers_projects` (
  `project_id` bigint(20) NOT NULL,
  `customer_id` bigint(20) NOT NULL,
  KEY `FK_psb5qcfmth1enga4pn2eb7oeu` (`customer_id`),
  KEY `FK_asq8k6x1ky2cga5c9ihdbfrle` (`project_id`),
  CONSTRAINT `FK_asq8k6x1ky2cga5c9ihdbfrle` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`),
  CONSTRAINT `FK_psb5qcfmth1enga4pn2eb7oeu` FOREIGN KEY (`customer_id`) REFERENCES `individuals` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers_projects`
--

LOCK TABLES `customers_projects` WRITE;
/*!40000 ALTER TABLE `customers_projects` DISABLE KEYS */;
INSERT INTO `customers_projects` VALUES (1,1),(2,2),(2,3),(3,3),(4,1),(5,1),(5,3),(6,3),(7,3),(7,1),(7,2);
/*!40000 ALTER TABLE `customers_projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departaments`
--

DROP TABLE IF EXISTS `departaments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departaments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departaments`
--

LOCK TABLES `departaments` WRITE;
/*!40000 ALTER TABLE `departaments` DISABLE KEYS */;
INSERT INTO `departaments` VALUES (1,'2014-10-13 19:59:32','2014-10-13 19:59:32','IT Departament','Movercado'),(2,'2014-10-13 21:19:32','2014-10-13 21:19:32','Imagem','Marketing'),(3,'2014-10-13 21:20:24','2014-10-13 21:20:24','Comunicação inter pessoal','CIP'),(4,'2014-10-13 21:20:46','2014-10-13 21:20:46','masfjnlfafasfsafafasfafrgth feklgrjgkerlnfklsenf afpkmsfçkmasçkfnmçasf afknçasnfkçnsa lfnas','Teste'),(5,'2014-10-13 22:38:10','2014-10-13 22:38:10','Not Yet','Contabilidade'),(6,'2014-10-13 22:46:26','2014-10-13 22:46:26','Teste com log','Test Log'),(7,'2014-10-13 22:47:35','2014-10-13 22:47:35','Log','Log'),(8,'2014-10-13 23:16:21','2014-10-13 23:16:21','New dep','Tested Log'),(9,'2014-10-13 23:23:02','2014-10-13 23:23:02','Novo Log','Recursos Humanos'),(10,'2014-10-14 09:51:52','2014-10-14 09:51:52','On Demand','Mobiz'),(11,'2014-10-14 09:52:18','2014-10-14 09:52:18','Desc','Pilula'),(12,'2014-10-28 23:27:34','2014-10-28 23:27:34','Departamento de Matematica e Informatica','DMI'),(13,'2014-10-28 23:31:34','2014-10-28 23:31:34','// *** Initialize ***//','DSI');
/*!40000 ALTER TABLE `departaments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document_types`
--

DROP TABLE IF EXISTS `document_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document_types` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document_types`
--

LOCK TABLES `document_types` WRITE;
/*!40000 ALTER TABLE `document_types` DISABLE KEYS */;
/*!40000 ALTER TABLE `document_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documents`
--

DROP TABLE IF EXISTS `documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documents` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `employee` tinyblob,
  `name` varchar(255) DEFAULT NULL,
  `published_to` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `documentType_id` bigint(20) DEFAULT NULL,
  `candidate_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ma5fqoi8fgm9q7de71s0ri91q` (`documentType_id`),
  KEY `FK_7yx9149xbrmhm10hqrdarikoi` (`candidate_id`),
  CONSTRAINT `FK_7yx9149xbrmhm10hqrdarikoi` FOREIGN KEY (`candidate_id`) REFERENCES `individuals` (`id`),
  CONSTRAINT `FK_ma5fqoi8fgm9q7de71s0ri91q` FOREIGN KEY (`documentType_id`) REFERENCES `document_types` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documents`
--

LOCK TABLES `documents` WRITE;
/*!40000 ALTER TABLE `documents` DISABLE KEYS */;
/*!40000 ALTER TABLE `documents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `education_levels`
--

DROP TABLE IF EXISTS `education_levels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `education_levels` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `education_levels`
--

LOCK TABLES `education_levels` WRITE;
/*!40000 ALTER TABLE `education_levels` DISABLE KEYS */;
/*!40000 ALTER TABLE `education_levels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_types`
--

DROP TABLE IF EXISTS `event_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_types` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_types`
--

LOCK TABLES `event_types` WRITE;
/*!40000 ALTER TABLE `event_types` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `events` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `eventType_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_77naxf4x8o6xsbx1ejoix7mfm` (`eventType_id`),
  CONSTRAINT `FK_77naxf4x8o6xsbx1ejoix7mfm` FOREIGN KEY (`eventType_id`) REFERENCES `event_types` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `files`
--

DROP TABLE IF EXISTS `files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `files` (
  `FROM_CLASS` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `content` longblob,
  `description` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `holder_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_awbq0tog6rpmwgk0ssw2vhysi` (`holder_id`),
  CONSTRAINT `FK_awbq0tog6rpmwgk0ssw2vhysi` FOREIGN KEY (`holder_id`) REFERENCES `individuals` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `files`
--

LOCK TABLES `files` WRITE;
/*!40000 ALTER TABLE `files` DISABLE KEYS */;
/*!40000 ALTER TABLE `files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `identity_document_types`
--

DROP TABLE IF EXISTS `identity_document_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `identity_document_types` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `descriptions` varchar(255) DEFAULT NULL,
  `names` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `identity_document_types`
--

LOCK TABLES `identity_document_types` WRITE;
/*!40000 ALTER TABLE `identity_document_types` DISABLE KEYS */;
INSERT INTO `identity_document_types` VALUES (1,'2014-10-28 10:38:49','2014-10-28 10:38:49','Bilhete de Identificação','BI');
/*!40000 ALTER TABLE `identity_document_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `imageData` tinyblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `individual_types`
--

DROP TABLE IF EXISTS `individual_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `individual_types` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `individual_types`
--

LOCK TABLES `individual_types` WRITE;
/*!40000 ALTER TABLE `individual_types` DISABLE KEYS */;
/*!40000 ALTER TABLE `individual_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `individuals`
--

DROP TABLE IF EXISTS `individuals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `individuals` (
  `DTYPE` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `academic_level` varchar(255) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `id_number` varchar(255) DEFAULT NULL,
  `identity_document_type` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `marital_status` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nationality` varchar(255) DEFAULT NULL,
  `number_children` int(11) DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  `jobPosition_id` bigint(20) DEFAULT NULL,
  `dateOfApplication` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `individual_type_id` bigint(20) DEFAULT NULL,
  `Individual_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ca4t2574r7hfjkbbf75siyokj` (`type_id`),
  KEY `FK_pwprym26gcg1hrpdhf2qp4ntq` (`department_id`),
  KEY `FK_gpwh956k3d669yy3d7is7xcw5` (`jobPosition_id`),
  KEY `FK_gkywvgfb98ve4vvyja58dl6ps` (`user_id`),
  KEY `FK_6wde1wsur1cgif9ibaspt3jbm` (`individual_type_id`),
  KEY `FK_lkb6yuwppd18rys1mplp1teb4` (`Individual_id`),
  CONSTRAINT `FK_6wde1wsur1cgif9ibaspt3jbm` FOREIGN KEY (`individual_type_id`) REFERENCES `individual_types` (`id`),
  CONSTRAINT `FK_ca4t2574r7hfjkbbf75siyokj` FOREIGN KEY (`type_id`) REFERENCES `individual_types` (`id`),
  CONSTRAINT `FK_gkywvgfb98ve4vvyja58dl6ps` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_gpwh956k3d669yy3d7is7xcw5` FOREIGN KEY (`jobPosition_id`) REFERENCES `job_positions` (`id`),
  CONSTRAINT `FK_lkb6yuwppd18rys1mplp1teb4` FOREIGN KEY (`Individual_id`) REFERENCES `identity_document_types` (`id`),
  CONSTRAINT `FK_pwprym26gcg1hrpdhf2qp4ntq` FOREIGN KEY (`department_id`) REFERENCES `departaments` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `individuals`
--

LOCK TABLES `individuals` WRITE;
/*!40000 ALTER TABLE `individuals` DISABLE KEYS */;
INSERT INTO `individuals` VALUES ('Employee',1,'2014-10-28 10:46:17','2014-11-06 13:57:47','NivelSuperior','1991-12-18 00:00:00',NULL,NULL,NULL,'Mulungo','Casado','José','Jorge','AE',0,NULL,NULL,1,NULL,NULL,9,NULL,NULL),('Employee',2,'2014-10-28 13:46:40','2014-11-06 21:49:13','NivelSuperior','2000-10-26 00:00:00',NULL,NULL,NULL,'Cossa','Solteiro','Benilde','Admilson','BV',0,NULL,NULL,7,NULL,NULL,10,NULL,NULL),('Employee',3,'2014-10-28 13:48:05','2014-11-06 21:49:39','NivelSuperior','2014-10-14 00:00:00',NULL,NULL,NULL,'Maposse','Solteiro','José','Eusébio','IL',0,NULL,NULL,3,NULL,NULL,11,NULL,NULL);
/*!40000 ALTER TABLE `individuals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_application`
--

DROP TABLE IF EXISTS `job_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job_application` (
  `vacancy_id` bigint(20) NOT NULL,
  `hiring_manager_id` bigint(20) NOT NULL,
  `candidate_id` bigint(20) NOT NULL,
  PRIMARY KEY (`vacancy_id`,`candidate_id`),
  KEY `FK_a9r79kacrfbgeiyvjyrm6ah4g` (`hiring_manager_id`),
  KEY `FK_j787iyc4ws6rtnx1xk8ujra08` (`candidate_id`),
  CONSTRAINT `FK_12921tmby838gygv2l7qtnd1v` FOREIGN KEY (`vacancy_id`) REFERENCES `vacancies` (`id`),
  CONSTRAINT `FK_a9r79kacrfbgeiyvjyrm6ah4g` FOREIGN KEY (`hiring_manager_id`) REFERENCES `individuals` (`id`),
  CONSTRAINT `FK_j787iyc4ws6rtnx1xk8ujra08` FOREIGN KEY (`candidate_id`) REFERENCES `individuals` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_application`
--

LOCK TABLES `job_application` WRITE;
/*!40000 ALTER TABLE `job_application` DISABLE KEYS */;
/*!40000 ALTER TABLE `job_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_positions`
--

DROP TABLE IF EXISTS `job_positions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job_positions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_positions`
--

LOCK TABLES `job_positions` WRITE;
/*!40000 ALTER TABLE `job_positions` DISABLE KEYS */;
INSERT INTO `job_positions` VALUES (1,'2014-10-13 19:51:51','2014-10-13 19:51:51','Jorge White','Gestor de Clientes'),(2,'2014-10-13 21:34:01','2014-10-13 21:34:01','Qualquer coisa como descrição.!','Assistente de Clientes'),(3,'2014-10-13 23:25:08','2014-10-13 23:25:08','Moverc','Analista de Dados'),(4,'2014-10-23 14:51:17','2014-10-23 14:51:17','Java Sénior.','Programador'),(5,'2014-10-23 14:53:16','2014-10-23 14:53:16','Júnior','Analista de Sistemas'),(6,'2014-10-23 14:55:47','2014-10-23 14:55:47','Data Base Manager','Gestor de BD'),(7,'2014-10-29 07:37:19','2014-10-29 07:37:19','Responsável por desenhar a estrutura do software.','Arquiteto de Software'),(8,'2014-10-29 07:43:04','2014-10-29 07:43:04','Xpto','XPTO');
/*!40000 ALTER TABLE `job_positions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locales`
--

DROP TABLE IF EXISTS `locales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `locales` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `tenant_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5b2fvjv31fsqflimvymuwnbml` (`tenant_id`),
  CONSTRAINT `FK_5b2fvjv31fsqflimvymuwnbml` FOREIGN KEY (`tenant_id`) REFERENCES `tenants` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locales`
--

LOCK TABLES `locales` WRITE;
/*!40000 ALTER TABLE `locales` DISABLE KEYS */;
/*!40000 ALTER TABLE `locales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logs`
--

DROP TABLE IF EXISTS `logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `date` datetime DEFAULT NULL,
  `message` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logs`
--

LOCK TABLES `logs` WRITE;
/*!40000 ALTER TABLE `logs` DISABLE KEYS */;
INSERT INTO `logs` VALUES (1,'2014-10-13 23:16:21','2014-10-13 23:16:21',NULL,'Registou novo departamento: Tested Log'),(2,'2014-10-13 23:23:02','2014-10-13 23:23:02',NULL,'Registou novo departamento: Recursos Humanos'),(3,'2014-10-13 23:25:08','2014-10-13 23:25:08',NULL,'Registou novo cargo: Analista de Dados'),(4,'2014-10-14 09:51:53','2014-10-14 09:51:53',NULL,'Registou novo departamento: Mobiz'),(5,'2014-10-14 09:52:18','2014-10-14 09:52:18',NULL,'Registou novo departamento: Pilula'),(6,'2014-10-16 08:58:30','2014-10-16 08:58:30',NULL,'Registou nova permissão: CTRL_VENDOR_LIST_GET'),(7,'2014-10-16 09:03:39','2014-10-16 09:03:39',NULL,'Registou nova permissão: CTRL_INOLAB'),(8,'2014-10-16 09:06:48','2014-10-16 09:06:48',NULL,'Registou nova permissão: can_recruit'),(9,'2014-10-16 09:15:51','2014-10-16 09:15:51',NULL,'Registou nova permissão: test'),(10,'2014-10-16 09:20:18','2014-10-16 09:20:18',NULL,'Registou nova permissão: new_test'),(11,'2014-10-16 17:53:23','2014-10-16 17:53:23',NULL,'Registou novo usuário: jmulungo'),(12,'2014-10-17 10:25:46','2014-10-17 10:25:46',NULL,'Registou novo papel: aERTREEWRTH'),(13,'2014-10-17 10:30:16','2014-10-17 10:30:16',NULL,'Registou novo usuário: jmulungo'),(14,'2014-10-23 14:51:17','2014-10-23 14:51:17',NULL,'Registou novo cargo: Programador'),(15,'2014-10-23 14:53:17','2014-10-23 14:53:17',NULL,'Registou novo cargo: Analista de Sistemas'),(16,'2014-10-23 14:55:47','2014-10-23 14:55:47',NULL,'Registou novo cargo: Gestor de BD'),(17,'2014-10-28 10:38:49','2014-10-28 10:38:49',NULL,''),(18,'2014-10-28 13:02:04','2014-10-28 13:02:04',NULL,'Registou um novo projecto: Recrutamento - Phase 1'),(19,'2014-10-28 13:48:48','2014-10-28 13:48:48',NULL,'Registou um novo projecto: Recrutamento - Phase 2'),(20,'2014-10-28 15:03:59','2014-10-28 15:03:59',NULL,'Registou um novo projecto: Recrutamento - Phase 3'),(21,'2014-10-28 16:31:19','2014-10-28 16:31:19',NULL,'Registou um novo projecto: Timesheet - Fase 1');
/*!40000 ALTER TABLE `logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `permissionname` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
INSERT INTO `permissions` VALUES (1,'2014-10-13 12:59:29','2014-10-13 12:59:29','CTRL_STRATEGY_LIST_GET'),(2,'2014-10-13 12:59:29','2014-10-13 12:59:29','CTRL_STRATEGY_ADD_POST'),(3,'2014-10-13 12:59:30','2014-10-13 12:59:30','CTRL_STRATEGY_EDIT_GET'),(4,'2014-10-13 12:59:30','2014-10-13 12:59:30','CTRL_STRATEGY_EDIT_POST'),(5,'2014-10-13 12:59:30','2014-10-13 12:59:30','CTRL_STRATEGY_DELETE_GET'),(6,'2014-10-16 08:58:30','2014-10-16 08:58:30','CTRL_VENDOR_LIST_GET'),(7,'2014-10-16 09:03:39','2014-10-16 09:03:39','CTRL_INOLAB'),(8,'2014-10-16 09:06:48','2014-10-16 09:06:48','can_recruit'),(9,'2014-10-16 09:15:51','2014-10-16 09:15:51','test'),(10,'2014-10-16 09:20:18','2014-10-16 09:20:18','new_test'),(11,'2014-10-30 14:33:12','2014-10-30 14:33:12','testador');
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persistent_logins` (
  `series` varchar(64) NOT NULL,
  `last_used` datetime NOT NULL,
  `token` varchar(64) NOT NULL,
  `username` varchar(64) NOT NULL,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_logins`
--

LOCK TABLES `persistent_logins` WRITE;
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profiles`
--

DROP TABLE IF EXISTS `profiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profiles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profiles`
--

LOCK TABLES `profiles` WRITE;
/*!40000 ALTER TABLE `profiles` DISABLE KEYS */;
/*!40000 ALTER TABLE `profiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projects` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` VALUES (1,'2014-10-28 13:02:04','2014-10-28 13:02:04','Recriar a fase de recrutaamento dos candidatos na aplicação.!','Recrutamento - Phase 1'),(2,'2014-10-28 13:48:47','2014-10-28 13:48:47','Segunda fase do recrutamento..!','Recrutamento - Phase 2'),(3,'2014-10-28 15:03:59','2014-10-28 15:03:59','Fase 3 do recrutamento.!','Recrutamento - Phase 3'),(4,'2014-10-28 16:31:19','2014-10-28 16:31:19','Primeira fase da implementação dos timesheets','Timesheet - Fase 1'),(5,'2014-10-29 10:32:27','2014-10-29 10:32:27','Primeira fase do recrutamento.!','Recrutmento - Fase 1'),(6,'2014-10-29 11:18:31','2014-10-29 11:18:31','Reconstruir o projecto','Engenharia Reversa'),(7,'2014-11-06 12:10:49','2014-11-06 12:10:49','Bdasfgv','Prro 1');
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permissions`
--

DROP TABLE IF EXISTS `role_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permissions` (
  `permission_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `UK_d4atqq8ege1sij0316vh2mxfu` (`role_id`),
  KEY `UK_qfkbccnh2c5o4tc7akq5x11wv` (`permission_id`),
  CONSTRAINT `FK_d4atqq8ege1sij0316vh2mxfu` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FK_qfkbccnh2c5o4tc7akq5x11wv` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permissions`
--

LOCK TABLES `role_permissions` WRITE;
/*!40000 ALTER TABLE `role_permissions` DISABLE KEYS */;
INSERT INTO `role_permissions` VALUES (1,1),(2,5),(1,25),(2,25),(4,25),(2,28),(4,28),(7,28);
/*!40000 ALTER TABLE `role_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `rolename` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'2014-10-13 12:59:47','2014-10-13 12:59:47','ROLE_ADMIN'),(2,'2014-10-13 13:00:18','2014-10-13 13:00:18','ROLE_TRADER'),(3,'2014-10-13 13:00:18','2014-10-13 13:00:18','ROLE_USER'),(5,'2014-10-15 14:36:56','2014-10-15 14:36:56','SEGRDHFJ'),(11,'2014-10-15 15:30:12','2014-10-15 15:30:12','adsfbgnvhmj,khgfdsaxscdvfbgh'),(25,'2014-10-17 10:25:46','2014-10-17 10:25:46','aERTREEWRTH'),(26,'2014-10-29 08:00:19','2014-10-29 08:00:19','Demostração'),(27,'2014-10-29 08:03:14','2014-10-29 08:03:14','Demo 2'),(28,'2014-10-29 08:07:27','2014-10-29 08:07:27','rehtjyurht');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sub_unities`
--

DROP TABLE IF EXISTS `sub_unities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sub_unities` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_unities`
--

LOCK TABLES `sub_unities` WRITE;
/*!40000 ALTER TABLE `sub_unities` DISABLE KEYS */;
/*!40000 ALTER TABLE `sub_unities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenants`
--

DROP TABLE IF EXISTS `tenants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tenants` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `time_zone` char(1) DEFAULT NULL,
  `url_identifier` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenants`
--

LOCK TABLES `tenants` WRITE;
/*!40000 ALTER TABLE `tenants` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timesheets`
--

DROP TABLE IF EXISTS `timesheets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `timesheets` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `employee_id` bigint(20) DEFAULT NULL,
  `projectName` varchar(255) DEFAULT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_qqltgj6er48wbp4ofieiwftoe` (`employee_id`),
  CONSTRAINT `FK_qqltgj6er48wbp4ofieiwftoe` FOREIGN KEY (`employee_id`) REFERENCES `individuals` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timesheets`
--

LOCK TABLES `timesheets` WRITE;
/*!40000 ALTER TABLE `timesheets` DISABLE KEYS */;
/*!40000 ALTER TABLE `timesheets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `UK_5q4rc4fh1on6567qk69uesvyf` (`role_id`),
  KEY `UK_g1uebn6mqk9qiaw45vnacmyo2` (`user_id`),
  CONSTRAINT `FK_5q4rc4fh1on6567qk69uesvyf` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FK_g1uebn6mqk9qiaw45vnacmyo2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,1),(9,1),(10,1),(11,1),(2,2),(7,2),(3,3),(7,3),(8,27);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `password` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `profile_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `FK_7s5nlreekaxdbfml4ofky7utw` (`profile_id`),
  CONSTRAINT `FK_7s5nlreekaxdbfml4ofky7utw` FOREIGN KEY (`profile_id`) REFERENCES `individuals` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2014-10-13 13:00:18','2014-10-13 13:00:18','\0','password','admin',NULL),(2,'2014-10-13 13:00:18','2014-10-13 13:00:18','\0','password','trader',NULL),(3,'2014-10-13 13:00:19','2014-10-13 13:00:19','\0','password','user',NULL),(7,'2014-10-17 10:30:16','2014-10-20 14:54:38','\0','jmulungo','jmulungo',NULL),(8,'2014-11-06 12:48:10','2014-11-06 12:48:10','\0','abel','abel',1),(9,'2014-11-06 13:57:47','2014-11-06 13:57:47','','jessy','jessy',1),(10,'2014-11-06 21:49:12','2014-11-06 21:49:12','','acossa','acossa',2),(11,'2014-11-06 21:49:39','2014-11-06 21:49:39','','emaposse','emaposse',3);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vacancies`
--

DROP TABLE IF EXISTS `vacancies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vacancies` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `job_title_id` bigint(20) DEFAULT NULL,
  `sub_unit_id` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_32j0xpomwbqxpsn7pr8lcxmns` (`job_title_id`),
  KEY `FK_bx16kr0wqat9rnnbsyw7h6un0` (`sub_unit_id`),
  CONSTRAINT `FK_32j0xpomwbqxpsn7pr8lcxmns` FOREIGN KEY (`job_title_id`) REFERENCES `job_positions` (`id`),
  CONSTRAINT `FK_bx16kr0wqat9rnnbsyw7h6un0` FOREIGN KEY (`sub_unit_id`) REFERENCES `sub_unities` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vacancies`
--

LOCK TABLES `vacancies` WRITE;
/*!40000 ALTER TABLE `vacancies` DISABLE KEYS */;
/*!40000 ALTER TABLE `vacancies` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-07  8:32:25
