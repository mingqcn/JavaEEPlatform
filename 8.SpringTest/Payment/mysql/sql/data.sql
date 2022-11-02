-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: mysql    Database: oomall
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `payment_business`
--

DROP TABLE IF EXISTS `payment_business`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_business` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `creator_id` bigint DEFAULT NULL,
  `creator_name` varchar(128) DEFAULT NULL,
  `modifier_id` bigint DEFAULT NULL,
  `modifier_name` varchar(128) DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=502 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='业务';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_business`
--

LOCK TABLES `payment_business` WRITE;
/*!40000 ALTER TABLE `payment_business` DISABLE KEYS */;
INSERT INTO `payment_business` VALUES (501,'电子商城',1,'admin111',NULL,NULL,'2022-11-02 10:48:06',NULL);
/*!40000 ALTER TABLE `payment_business` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_channel`
--

DROP TABLE IF EXISTS `payment_channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_channel` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `appid` varchar(128) NOT NULL,
  `name` varchar(128) NOT NULL,
  `sp_mchid` varchar(128) NOT NULL,
  `begin_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `status` tinyint NOT NULL DEFAULT '1',
  `bean_name` varchar(256) DEFAULT NULL,
  `creator_id` bigint DEFAULT NULL,
  `creator_name` varchar(128) DEFAULT NULL,
  `modifier_id` bigint DEFAULT NULL,
  `modifier_name` varchar(128) DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=502 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='支付渠道';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_channel`
--

LOCK TABLES `payment_channel` WRITE;
/*!40000 ALTER TABLE `payment_channel` DISABLE KEYS */;
INSERT INTO `payment_channel` VALUES (501,'100001','微信支付','1900007XXX','2022-05-02 18:49:48','2099-11-02 18:49:56',0,'wePayChannel',1,'admin111',NULL,NULL,'2022-11-02 10:51:31',NULL);
/*!40000 ALTER TABLE `payment_channel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_div_pay_trans`
--

DROP TABLE IF EXISTS `payment_div_pay_trans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_div_pay_trans` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `out_no` varchar(128) NOT NULL,
  `trans_no` varchar(128) NOT NULL,
  `amount` bigint NOT NULL DEFAULT '0',
  `status` tinyint NOT NULL DEFAULT '0',
  `success_time` datetime DEFAULT NULL,
  `adjust_id` bigint DEFAULT NULL,
  `adjust_name` varchar(128) DEFAULT NULL,
  `adjust_time` datetime DEFAULT NULL,
  `pay_trans_id` bigint NOT NULL,
  `creator_id` bigint DEFAULT NULL,
  `creator_name` varchar(128) DEFAULT NULL,
  `modifier_id` bigint DEFAULT NULL,
  `modifier_name` varchar(128) DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=501 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='支付分账交易';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_div_pay_trans`
--

LOCK TABLES `payment_div_pay_trans` WRITE;
/*!40000 ALTER TABLE `payment_div_pay_trans` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_div_pay_trans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_div_refund_trans`
--

DROP TABLE IF EXISTS `payment_div_refund_trans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_div_refund_trans` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `out_no` varchar(128) NOT NULL,
  `trans_no` varchar(128) NOT NULL,
  `amount` bigint NOT NULL DEFAULT '0',
  `status` tinyint NOT NULL DEFAULT '0',
  `success_time` datetime DEFAULT NULL,
  `adjust_id` bigint DEFAULT NULL,
  `adjust_name` varchar(128) DEFAULT NULL,
  `adjust_time` datetime DEFAULT NULL,
  `refund_trans_id` bigint NOT NULL,
  `div_pay_trans_id` bigint NOT NULL,
  `creator_id` bigint DEFAULT NULL,
  `creator_name` varchar(128) DEFAULT NULL,
  `modifier_id` bigint DEFAULT NULL,
  `modifier_name` varchar(128) DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=501 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='退款分账交易';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_div_refund_trans`
--

LOCK TABLES `payment_div_refund_trans` WRITE;
/*!40000 ALTER TABLE `payment_div_refund_trans` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_div_refund_trans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_ledger`
--

DROP TABLE IF EXISTS `payment_ledger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_ledger` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `out_no` varchar(128) NOT NULL,
  `trans_no` varchar(128) NOT NULL,
  `amount` bigint NOT NULL DEFAULT '0',
  `status` tinyint NOT NULL DEFAULT '0',
  `success_time` datetime DEFAULT NULL,
  `type` tinyint NOT NULL DEFAULT '0',
  `adjust_id` bigint DEFAULT NULL,
  `adjust_name` varchar(128) DEFAULT NULL,
  `adjust_time` datetime DEFAULT NULL,
  `shop_id` bigint NOT NULL,
  `trans_id` bigint DEFAULT NULL,
  `check_time` datetime DEFAULT NULL,
  `creator_id` bigint DEFAULT NULL,
  `creator_name` varchar(128) DEFAULT NULL,
  `modifier_id` bigint DEFAULT NULL,
  `modifier_name` varchar(128) DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=501 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='对账台账';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_ledger`
--

LOCK TABLES `payment_ledger` WRITE;
/*!40000 ALTER TABLE `payment_ledger` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_ledger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_pay_trans`
--

DROP TABLE IF EXISTS `payment_pay_trans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_pay_trans` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `out_no` varchar(128) DEFAULT NULL,
  `trans_no` varchar(128) DEFAULT NULL,
  `amount` bigint NOT NULL DEFAULT '0',
  `status` tinyint NOT NULL DEFAULT '0',
  `success_time` datetime DEFAULT NULL,
  `adjust_id` bigint DEFAULT NULL,
  `adjust_name` varchar(128) DEFAULT NULL,
  `adjust_time` datetime DEFAULT NULL,
  `sp_openid` varchar(128) DEFAULT NULL,
  `time_expire` datetime DEFAULT NULL,
  `time_begin` datetime DEFAULT NULL,
  `shop_channel_id` bigint NOT NULL,
  `business_id` bigint NOT NULL,
  `creator_id` bigint DEFAULT NULL,
  `creator_name` varchar(128) DEFAULT NULL,
  `modifier_id` bigint DEFAULT NULL,
  `modifier_name` varchar(128) DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT NULL,
  `prepay_id` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=527 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='支付交易';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_pay_trans`
--

LOCK TABLES `payment_pay_trans` WRITE;
/*!40000 ALTER TABLE `payment_pay_trans` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_pay_trans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_refund_trans`
--

DROP TABLE IF EXISTS `payment_refund_trans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_refund_trans` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `out_no` varchar(128) NOT NULL,
  `trans_no` varchar(128) NOT NULL,
  `amount` bigint NOT NULL DEFAULT '0',
  `status` tinyint NOT NULL DEFAULT '0',
  `success_time` datetime DEFAULT NULL,
  `adjust_id` bigint DEFAULT NULL,
  `adjust_name` varchar(128) DEFAULT NULL,
  `adjust_time` datetime DEFAULT NULL,
  `user_received_account` varchar(128) DEFAULT NULL,
  `pay_trans_id` bigint NOT NULL,
  `shop_id` bigint NOT NULL,
  `business_id` bigint NOT NULL,
  `creator_id` bigint DEFAULT NULL,
  `creator_name` varchar(128) DEFAULT NULL,
  `modifier_id` bigint DEFAULT NULL,
  `modifier_name` varchar(128) DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=501 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='退款交易';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_refund_trans`
--

LOCK TABLES `payment_refund_trans` WRITE;
/*!40000 ALTER TABLE `payment_refund_trans` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_refund_trans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_shop_channel`
--

DROP TABLE IF EXISTS `payment_shop_channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_shop_channel` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `shop_id` bigint NOT NULL,
  `sub_mchid` varchar(128) NOT NULL,
  `channel_id` bigint NOT NULL,
  `creator_id` bigint DEFAULT NULL,
  `creator_name` varchar(128) DEFAULT NULL,
  `modifier_id` bigint DEFAULT NULL,
  `modifier_name` varchar(128) DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT NULL,
  `status` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=502 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商铺支付渠道账户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_shop_channel`
--

LOCK TABLES `payment_shop_channel` WRITE;
/*!40000 ALTER TABLE `payment_shop_channel` DISABLE KEYS */;
INSERT INTO `payment_shop_channel` VALUES (501,1,'1900008XXX',501,1,'admin111',NULL,NULL,'2022-11-02 10:53:41',NULL,0);
/*!40000 ALTER TABLE `payment_shop_channel` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-03  0:03:23
