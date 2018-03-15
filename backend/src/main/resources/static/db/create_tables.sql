-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: daily_advisor
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `adv_coach_types`
--

DROP TABLE IF EXISTS `adv_coach_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adv_coach_types` (
  `coach_type_id` bigint(20) DEFAULT NULL,
  `adv_id` bigint(20) NOT NULL,
  PRIMARY KEY (`adv_id`),
  KEY `FKdrwgbtqrkrsyvyyhqeqlqh80i` (`coach_type_id`),
  CONSTRAINT `FKamagjur1s81mwkx7upcrlnk52` FOREIGN KEY (`adv_id`) REFERENCES `advertisement` (`adv_id`),
  CONSTRAINT `FKdrwgbtqrkrsyvyyhqeqlqh80i` FOREIGN KEY (`coach_type_id`) REFERENCES `coach_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adv_coach_types`
--

LOCK TABLES `adv_coach_types` WRITE;
/*!40000 ALTER TABLE `adv_coach_types` DISABLE KEYS */;
/*!40000 ALTER TABLE `adv_coach_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `advertisement`
--

DROP TABLE IF EXISTS `advertisement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertisement` (
  `adv_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adv_text` varchar(255) NOT NULL,
  `answers` int(11) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `edit_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(255) NOT NULL,
  `visits` int(11) NOT NULL,
  `user` bigint(20) NOT NULL,
  PRIMARY KEY (`adv_id`),
  KEY `FK6ekmum015wpqk46gaw6as29km` (`user`),
  CONSTRAINT `FK6ekmum015wpqk46gaw6as29km` FOREIGN KEY (`user`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertisement`
--

LOCK TABLES `advertisement` WRITE;
/*!40000 ALTER TABLE `advertisement` DISABLE KEYS */;
/*!40000 ALTER TABLE `advertisement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coach_type`
--

DROP TABLE IF EXISTS `coach_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coach_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coach_type`
--

LOCK TABLES `coach_type` WRITE;
/*!40000 ALTER TABLE `coach_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `coach_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coaching`
--

DROP TABLE IF EXISTS `coaching`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coaching` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  `client_user_id` bigint(20) DEFAULT NULL,
  `coach_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2s28hmxenmsdm2j68fntqhi` (`client_user_id`),
  KEY `FKk6r3wrbq4uarv8l3j0uuiqjmj` (`coach_user_id`),
  CONSTRAINT `FK2s28hmxenmsdm2j68fntqhi` FOREIGN KEY (`client_user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKk6r3wrbq4uarv8l3j0uuiqjmj` FOREIGN KEY (`coach_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coaching`
--

LOCK TABLES `coaching` WRITE;
/*!40000 ALTER TABLE `coaching` DISABLE KEYS */;
/*!40000 ALTER TABLE `coaching` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diet`
--

DROP TABLE IF EXISTS `diet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(255) DEFAULT NULL,
  `created_by_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm6xq4qjbgekv93kpudleb5wd4` (`created_by_user_id`),
  CONSTRAINT `FKm6xq4qjbgekv93kpudleb5wd4` FOREIGN KEY (`created_by_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diet`
--

LOCK TABLES `diet` WRITE;
/*!40000 ALTER TABLE `diet` DISABLE KEYS */;
/*!40000 ALTER TABLE `diet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diet_meal`
--

DROP TABLE IF EXISTS `diet_meal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diet_meal` (
  `id` bigint(20) NOT NULL,
  `meals_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`meals_id`),
  UNIQUE KEY `UK_o6s4s80mx2a64rki2wkfhev2g` (`meals_id`),
  CONSTRAINT `FK4m9hdweso6s7rbcx2o53dt7p8` FOREIGN KEY (`meals_id`) REFERENCES `meal` (`id`),
  CONSTRAINT `FKe7yjjpl57jh53ep3lcp9g248e` FOREIGN KEY (`id`) REFERENCES `diet` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diet_meal`
--

LOCK TABLES `diet_meal` WRITE;
/*!40000 ALTER TABLE `diet_meal` DISABLE KEYS */;
/*!40000 ALTER TABLE `diet_meal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end_date` datetime DEFAULT NULL,
  `end_time` time NOT NULL,
  `is_full_day_event` bit(1) NOT NULL,
  `is_recurring` bit(1) NOT NULL,
  `start_date` datetime NOT NULL,
  `start_time` time NOT NULL,
  `recurring_pattern` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjkhj96nngj53hw0skqw197syj` (`recurring_pattern`),
  CONSTRAINT `FKjkhj96nngj53hw0skqw197syj` FOREIGN KEY (`recurring_pattern`) REFERENCES `recurring_pattern` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_instance_exception`
--

DROP TABLE IF EXISTS `event_instance_exception`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_instance_exception` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(255) NOT NULL,
  `end_date` datetime DEFAULT NULL,
  `end_time` datetime NOT NULL,
  `is_cancelled` bit(1) NOT NULL,
  `is_full_day_event` bit(1) NOT NULL,
  `is_rescheduled` bit(1) NOT NULL,
  `start_date` datetime NOT NULL,
  `start_time` datetime NOT NULL,
  `event_exceptions_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtem0x667ng1uuhy5125uwrmbh` (`event_exceptions_id`),
  CONSTRAINT `FKtem0x667ng1uuhy5125uwrmbh` FOREIGN KEY (`event_exceptions_id`) REFERENCES `event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_instance_exception`
--

LOCK TABLES `event_instance_exception` WRITE;
/*!40000 ALTER TABLE `event_instance_exception` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_instance_exception` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `location_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meal`
--

DROP TABLE IF EXISTS `meal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cal` int(11) DEFAULT NULL,
  `carbohydrates` float DEFAULT NULL,
  `fat` int(11) DEFAULT NULL,
  `meal_text` varchar(255) NOT NULL,
  `event` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrieyqit0xl0xorp2ecx7kb32h` (`event`),
  CONSTRAINT `FKrieyqit0xl0xorp2ecx7kb32h` FOREIGN KEY (`event`) REFERENCES `event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meal`
--

LOCK TABLES `meal` WRITE;
/*!40000 ALTER TABLE `meal` DISABLE KEYS */;
/*!40000 ALTER TABLE `meal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meeting`
--

DROP TABLE IF EXISTS `meeting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meeting` (
  `meeting_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `meeting_text` varchar(255) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `id` bigint(20) NOT NULL,
  `location_id` bigint(20) NOT NULL,
  `user_id_user_id` bigint(20) DEFAULT NULL,
  `user_id2_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`meeting_id`),
  KEY `FKt1clq7l3gopf7jd2dhy7w6owu` (`id`),
  KEY `FK8bqft0294qmh1tj203qp4hmrt` (`location_id`),
  KEY `FKebw0835fc7xdi64ytc6c6tikc` (`user_id_user_id`),
  KEY `FKeb1bs27kdfgy15t0oi3ps35ms` (`user_id2_user_id`),
  CONSTRAINT `FK8bqft0294qmh1tj203qp4hmrt` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`),
  CONSTRAINT `FKeb1bs27kdfgy15t0oi3ps35ms` FOREIGN KEY (`user_id2_user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKebw0835fc7xdi64ytc6c6tikc` FOREIGN KEY (`user_id_user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKt1clq7l3gopf7jd2dhy7w6owu` FOREIGN KEY (`id`) REFERENCES `event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meeting`
--

LOCK TABLES `meeting` WRITE;
/*!40000 ALTER TABLE `meeting` DISABLE KEYS */;
/*!40000 ALTER TABLE `meeting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) DEFAULT NULL,
  `msg_time` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `receiver_user_id` bigint(20) DEFAULT NULL,
  `sender_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh4xy93vijopygnpjqbe0c9iew` (`receiver_user_id`),
  KEY `FK80flimpheqbm2ex5r6ng1iodk` (`sender_user_id`),
  CONSTRAINT `FK80flimpheqbm2ex5r6ng1iodk` FOREIGN KEY (`sender_user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKh4xy93vijopygnpjqbe0c9iew` FOREIGN KEY (`receiver_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recurring_pattern`
--

DROP TABLE IF EXISTS `recurring_pattern`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recurring_pattern` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `day_of_month` int(11) DEFAULT NULL,
  `day_of_week` int(11) DEFAULT NULL,
  `max_num_of_occurrences` int(11) NOT NULL,
  `month_of_year` int(11) DEFAULT NULL,
  `separation_count` int(11) NOT NULL,
  `week_of_month` int(11) DEFAULT NULL,
  `recurring_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK81auos7012hw5npe7g44aaisf` (`recurring_type_id`),
  CONSTRAINT `FK81auos7012hw5npe7g44aaisf` FOREIGN KEY (`recurring_type_id`) REFERENCES `recurring_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recurring_pattern`
--

LOCK TABLES `recurring_pattern` WRITE;
/*!40000 ALTER TABLE `recurring_pattern` DISABLE KEYS */;
/*!40000 ALTER TABLE `recurring_pattern` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recurring_type`
--

DROP TABLE IF EXISTS `recurring_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recurring_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `recurring_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8uyc6fve0x5jqrsb8k1s88n4f` (`recurring_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recurring_type`
--

LOCK TABLES `recurring_type` WRITE;
/*!40000 ALTER TABLE `recurring_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `recurring_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `UK_bjxn5ii7v7ygwx39et0wawu0q` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `train`
--

DROP TABLE IF EXISTS `train`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `train` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(255) DEFAULT NULL,
  `train_name` varchar(255) NOT NULL,
  `train_text` varchar(255) NOT NULL,
  `created_by_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcrjsdast3vvs0wemyow97ufup` (`created_by_user_id`),
  CONSTRAINT `FKcrjsdast3vvs0wemyow97ufup` FOREIGN KEY (`created_by_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `train`
--

LOCK TABLES `train` WRITE;
/*!40000 ALTER TABLE `train` DISABLE KEYS */;
/*!40000 ALTER TABLE `train` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `training`
--

DROP TABLE IF EXISTS `training`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `training` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `training_text` varchar(255) NOT NULL,
  `event` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2xuyrbif3wqhbvo2ibo8u4ym8` (`event`),
  CONSTRAINT `FK2xuyrbif3wqhbvo2ibo8u4ym8` FOREIGN KEY (`event`) REFERENCES `event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `training`
--

LOCK TABLES `training` WRITE;
/*!40000 ALTER TABLE `training` DISABLE KEYS */;
/*!40000 ALTER TABLE `training` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trainings`
--

DROP TABLE IF EXISTS `trainings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trainings` (
  `id` bigint(20) NOT NULL,
  `trainings_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`,`trainings_id`),
  UNIQUE KEY `UK_1qk5ub5hjk751ncn9fxhaf7ga` (`trainings_id`),
  CONSTRAINT `FKdikhvjoebvwticusvxfje5lk7` FOREIGN KEY (`id`) REFERENCES `train` (`id`),
  CONSTRAINT `FKnr4gjx54krsmihtxwyfcy2xn1` FOREIGN KEY (`trainings_id`) REFERENCES `training` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainings`
--

LOCK TABLES `trainings` WRITE;
/*!40000 ALTER TABLE `trainings` DISABLE KEYS */;
/*!40000 ALTER TABLE `trainings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_diet`
--

DROP TABLE IF EXISTS `user_diet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_diet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  `diet_id` bigint(20) DEFAULT NULL,
  `user_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrdxp6wbdbxbuk1np67iq2fjlo` (`diet_id`),
  KEY `FKdjho8i7tai2xkavub47pwbh6p` (`user_user_id`),
  CONSTRAINT `FKdjho8i7tai2xkavub47pwbh6p` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKrdxp6wbdbxbuk1np67iq2fjlo` FOREIGN KEY (`diet_id`) REFERENCES `diet` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_diet`
--

LOCK TABLES `user_diet` WRITE;
/*!40000 ALTER TABLE `user_diet` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_diet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_profile`
--

DROP TABLE IF EXISTS `user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_profile` (
  `user_profile_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `about` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_profile_id`),
  KEY `FK6kwj5lk78pnhwor4pgosvb51r` (`user_id`),
  CONSTRAINT `FK6kwj5lk78pnhwor4pgosvb51r` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profile`
--

LOCK TABLES `user_profile` WRITE;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_train`
--

DROP TABLE IF EXISTS `user_train`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_train` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  `train_id` bigint(20) DEFAULT NULL,
  `user_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7fygmvqedkd30hekpe1mg4qog` (`train_id`),
  KEY `FKi40v3fo77wksy6nc3qivc1eda` (`user_user_id`),
  CONSTRAINT `FK7fygmvqedkd30hekpe1mg4qog` FOREIGN KEY (`train_id`) REFERENCES `train` (`id`),
  CONSTRAINT `FKi40v3fo77wksy6nc3qivc1eda` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_train`
--

LOCK TABLES `user_train` WRITE;
/*!40000 ALTER TABLE `user_train` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_train` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-14 17:47:12
