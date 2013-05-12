CREATE DATABASE  IF NOT EXISTS `hqcims` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `hqcims`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: hqcims
-- ------------------------------------------------------
-- Server version	5.5.24

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
-- Table structure for table `cms_order_detail`
--

DROP TABLE IF EXISTS `cms_order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_order_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_id` bigint(20) NOT NULL,
  `sale` float(10,2) NOT NULL COMMENT '销售价格',
  `num` int(11) NOT NULL COMMENT '数量',
  `order_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='购物车明细';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_order_detail`
--

LOCK TABLES `cms_order_detail` WRITE;
/*!40000 ALTER TABLE `cms_order_detail` DISABLE KEYS */;
INSERT INTO `cms_order_detail` (`id`, `goods_id`, `sale`, `num`, `order_id`) VALUES (9,1,30000.00,1,17);
INSERT INTO `cms_order_detail` (`id`, `goods_id`, `sale`, `num`, `order_id`) VALUES (10,1,30000.00,1,18);
INSERT INTO `cms_order_detail` (`id`, `goods_id`, `sale`, `num`, `order_id`) VALUES (11,1,30000.00,3,19);
INSERT INTO `cms_order_detail` (`id`, `goods_id`, `sale`, `num`, `order_id`) VALUES (12,3,2998.50,1,20);
INSERT INTO `cms_order_detail` (`id`, `goods_id`, `sale`, `num`, `order_id`) VALUES (13,2,40.00,1,20);
INSERT INTO `cms_order_detail` (`id`, `goods_id`, `sale`, `num`, `order_id`) VALUES (14,1,30000.00,1,20);
INSERT INTO `cms_order_detail` (`id`, `goods_id`, `sale`, `num`, `order_id`) VALUES (18,2,40.00,1,22);
INSERT INTO `cms_order_detail` (`id`, `goods_id`, `sale`, `num`, `order_id`) VALUES (19,1,30000.00,1,22);
INSERT INTO `cms_order_detail` (`id`, `goods_id`, `sale`, `num`, `order_id`) VALUES (20,3,1999.00,2,23);
INSERT INTO `cms_order_detail` (`id`, `goods_id`, `sale`, `num`, `order_id`) VALUES (21,1,30000.00,1,24);
/*!40000 ALTER TABLE `cms_order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_menu`
--

DROP TABLE IF EXISTS `tb_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `href` varchar(100) DEFAULT NULL,
  `target` varchar(45) DEFAULT NULL COMMENT '目标（mainFrame、 _blank、_self、_parent、_top）',
  `icon` varchar(45) DEFAULT NULL,
  `is_top` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_menu`
--

LOCK TABLES `tb_menu` WRITE;
/*!40000 ALTER TABLE `tb_menu` DISABLE KEYS */;
INSERT INTO `tb_menu` (`id`, `parent_id`, `name`, `href`, `target`, `icon`, `is_top`) VALUES (1,0,'基础数据管理','',NULL,NULL,0);
INSERT INTO `tb_menu` (`id`, `parent_id`, `name`, `href`, `target`, `icon`, `is_top`) VALUES (2,1,'客户管理','/cms/consumer','mainFrame',NULL,1);
INSERT INTO `tb_menu` (`id`, `parent_id`, `name`, `href`, `target`, `icon`, `is_top`) VALUES (3,1,'商品管理','/cms/goods','mainFrame',NULL,1);
INSERT INTO `tb_menu` (`id`, `parent_id`, `name`, `href`, `target`, `icon`, `is_top`) VALUES (4,0,'进销存管理','/sys/user/info','',NULL,0);
INSERT INTO `tb_menu` (`id`, `parent_id`, `name`, `href`, `target`, `icon`, `is_top`) VALUES (5,4,'进货管理','/cms/test','mainFrame',NULL,1);
INSERT INTO `tb_menu` (`id`, `parent_id`, `name`, `href`, `target`, `icon`, `is_top`) VALUES (6,4,'销售管理','/cms/orderDetail','mainFrame',NULL,1);
INSERT INTO `tb_menu` (`id`, `parent_id`, `name`, `href`, `target`, `icon`, `is_top`) VALUES (7,4,'购物车管理','/cms/cart','mainFrame',NULL,1);
INSERT INTO `tb_menu` (`id`, `parent_id`, `name`, `href`, `target`, `icon`, `is_top`) VALUES (8,4,'订单管理','/cms/order','mainFrame',NULL,1);
INSERT INTO `tb_menu` (`id`, `parent_id`, `name`, `href`, `target`, `icon`, `is_top`) VALUES (9,4,'换货管理','/sys/user/info','mainFrame',NULL,1);
INSERT INTO `tb_menu` (`id`, `parent_id`, `name`, `href`, `target`, `icon`, `is_top`) VALUES (10,0,'财务管理','',NULL,NULL,0);
INSERT INTO `tb_menu` (`id`, `parent_id`, `name`, `href`, `target`, `icon`, `is_top`) VALUES (11,10,'应收管理','/sys/user/info','mainFrame',NULL,1);
INSERT INTO `tb_menu` (`id`, `parent_id`, `name`, `href`, `target`, `icon`, `is_top`) VALUES (12,10,'实收管理','/sys/user/info','mainFrame',NULL,1);
INSERT INTO `tb_menu` (`id`, `parent_id`, `name`, `href`, `target`, `icon`, `is_top`) VALUES (13,10,'欠款管理','/sys/user/info','mainFrame',NULL,1);
INSERT INTO `tb_menu` (`id`, `parent_id`, `name`, `href`, `target`, `icon`, `is_top`) VALUES (14,0,'销售及盘点','',NULL,NULL,0);
INSERT INTO `tb_menu` (`id`, `parent_id`, `name`, `href`, `target`, `icon`, `is_top`) VALUES (15,14,'销售统计','/sys/user/info','mainFrame',NULL,1);
/*!40000 ALTER TABLE `tb_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_receivable`
--

DROP TABLE IF EXISTS `cms_receivable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_receivable` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `order_id` bigint(20) NOT NULL COMMENT '本次应收对应的订单',
  `amount` varchar(45) NOT NULL COMMENT '本次应收金额',
  `del_flag` char(1) NOT NULL COMMENT '0 有效 1无效',
  `consumer_id` bigint(20) NOT NULL COMMENT '客户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='应收表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_receivable`
--

LOCK TABLES `cms_receivable` WRITE;
/*!40000 ALTER TABLE `cms_receivable` DISABLE KEYS */;
INSERT INTO `cms_receivable` (`id`, `create_date`, `order_id`, `amount`, `del_flag`, `consumer_id`) VALUES (1,'2013-05-12 09:43:48',22,'30040.0','0',2);
INSERT INTO `cms_receivable` (`id`, `create_date`, `order_id`, `amount`, `del_flag`, `consumer_id`) VALUES (2,'2013-05-12 10:25:45',23,'3998.0','0',1);
/*!40000 ALTER TABLE `cms_receivable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_order`
--

DROP TABLE IF EXISTS `cms_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `consumer_id` bigint(20) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` char(1) NOT NULL COMMENT '0 有效 1无效',
  `user_id` bigint(20) NOT NULL DEFAULT '0',
  `remarks` varchar(255) DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0 有效 1无效',
  `total` float(10,2) DEFAULT '0.00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='销售表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_order`
--

LOCK TABLES `cms_order` WRITE;
/*!40000 ALTER TABLE `cms_order` DISABLE KEYS */;
INSERT INTO `cms_order` (`id`, `consumer_id`, `create_date`, `del_flag`, `user_id`, `remarks`, `status`, `total`) VALUES (17,5,'2013-05-11 12:48:01','1',2,NULL,1,0.00);
INSERT INTO `cms_order` (`id`, `consumer_id`, `create_date`, `del_flag`, `user_id`, `remarks`, `status`, `total`) VALUES (18,5,'2013-05-11 12:54:37','1',2,NULL,1,0.00);
INSERT INTO `cms_order` (`id`, `consumer_id`, `create_date`, `del_flag`, `user_id`, `remarks`, `status`, `total`) VALUES (19,5,'2013-05-11 13:08:51','1',2,NULL,1,0.00);
INSERT INTO `cms_order` (`id`, `consumer_id`, `create_date`, `del_flag`, `user_id`, `remarks`, `status`, `total`) VALUES (20,1,'2013-05-11 13:10:35','1',2,NULL,1,0.00);
INSERT INTO `cms_order` (`id`, `consumer_id`, `create_date`, `del_flag`, `user_id`, `remarks`, `status`, `total`) VALUES (21,4,'2013-05-12 08:43:59','1',2,NULL,0,4000.00);
INSERT INTO `cms_order` (`id`, `consumer_id`, `create_date`, `del_flag`, `user_id`, `remarks`, `status`, `total`) VALUES (22,2,'2013-05-12 09:10:36','0',2,NULL,2,30040.00);
INSERT INTO `cms_order` (`id`, `consumer_id`, `create_date`, `del_flag`, `user_id`, `remarks`, `status`, `total`) VALUES (23,1,'2013-05-12 09:52:53','0',2,NULL,2,3998.00);
INSERT INTO `cms_order` (`id`, `consumer_id`, `create_date`, `del_flag`, `user_id`, `remarks`, `status`, `total`) VALUES (24,1,'2013-05-12 11:11:49','0',2,NULL,0,30000.00);
/*!40000 ALTER TABLE `cms_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_cart`
--

DROP TABLE IF EXISTS `cms_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `consumer_id` bigint(20) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` varchar(45) NOT NULL COMMENT '0 有效 1无效',
  `user_id` bigint(20) NOT NULL DEFAULT '0',
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='销售表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_cart`
--

LOCK TABLES `cms_cart` WRITE;
/*!40000 ALTER TABLE `cms_cart` DISABLE KEYS */;
INSERT INTO `cms_cart` (`id`, `consumer_id`, `create_date`, `del_flag`, `user_id`, `remarks`) VALUES (6,1,'2013-05-12 12:18:42','0',2,NULL);
/*!40000 ALTER TABLE `cms_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_goods`
--

DROP TABLE IF EXISTS `cms_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL COMMENT '店铺名称',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `code` varchar(45) NOT NULL COMMENT '记住码',
  `purchase` float(10,2) NOT NULL COMMENT '进货价格',
  `sale` float(10,2) NOT NULL COMMENT '销售价格',
  `rate` float(5,2) NOT NULL COMMENT '销售点数',
  `unit` varchar(45) NOT NULL COMMENT '单位（箱/个等）',
  `origin` varchar(200) DEFAULT '0' COMMENT '产地',
  `brand` varchar(200) DEFAULT '0' COMMENT '规格',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT ' 删除标识  0正常 1删除',
  `update_date` datetime NOT NULL,
  `num` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_goods`
--

LOCK TABLES `cms_goods` WRITE;
/*!40000 ALTER TABLE `cms_goods` DISABLE KEYS */;
INSERT INTO `cms_goods` (`id`, `name`, `remarks`, `code`, `purchase`, `sale`, `rate`, `unit`, `origin`, `brand`, `create_date`, `del_flag`, `update_date`, `num`) VALUES (1,'thinkpad','111','thinkpad',10000.00,30000.00,2.00,'1','美国','联想','2013-05-01 16:36:36','0','2013-05-12 19:11:49',98);
INSERT INTO `cms_goods` (`id`, `name`, `remarks`, `code`, `purchase`, `sale`, `rate`, `unit`, `origin`, `brand`, `create_date`, `del_flag`, `update_date`, `num`) VALUES (2,'青岛啤酒','','QDPJ',20.00,40.00,1.00,'1','青岛','青岛啤酒','2013-05-02 03:02:06','0','2013-05-12 19:10:03',99);
INSERT INTO `cms_goods` (`id`, `name`, `remarks`, `code`, `purchase`, `sale`, `rate`, `unit`, `origin`, `brand`, `create_date`, `del_flag`, `update_date`, `num`) VALUES (3,'小米手机','','XMSJ',1999.00,2998.50,0.50,'1','北京','小米','2013-05-02 09:39:10','0','2013-05-12 19:10:47',96);
INSERT INTO `cms_goods` (`id`, `name`, `remarks`, `code`, `purchase`, `sale`, `rate`, `unit`, `origin`, `brand`, `create_date`, `del_flag`, `update_date`, `num`) VALUES (4,'云烟','','YY',9.00,27.00,2.00,'0','','','2013-05-02 13:57:12','0','2013-05-05 03:35:10',100);
INSERT INTO `cms_goods` (`id`, `name`, `remarks`, `code`, `purchase`, `sale`, `rate`, `unit`, `origin`, `brand`, `create_date`, `del_flag`, `update_date`, `num`) VALUES (5,'康师傅矿泉水','','KSFKQS',1.00,2.00,1.00,'0','','','2013-05-04 10:47:04','0','2013-05-05 01:10:03',100);
INSERT INTO `cms_goods` (`id`, `name`, `remarks`, `code`, `purchase`, `sale`, `rate`, `unit`, `origin`, `brand`, `create_date`, `del_flag`, `update_date`, `num`) VALUES (6,'康师傅面','','KSFM',3.00,6.00,1.00,'0','','','2013-05-04 10:47:40','0','2013-05-05 01:10:03',100);
INSERT INTO `cms_goods` (`id`, `name`, `remarks`, `code`, `purchase`, `sale`, `rate`, `unit`, `origin`, `brand`, `create_date`, `del_flag`, `update_date`, `num`) VALUES (7,'天堂伞','','TTS',10.00,20.00,1.00,'0','','','2013-05-04 10:47:56','0','2013-05-05 01:10:03',100);
INSERT INTO `cms_goods` (`id`, `name`, `remarks`, `code`, `purchase`, `sale`, `rate`, `unit`, `origin`, `brand`, `create_date`, `del_flag`, `update_date`, `num`) VALUES (8,'安尔乐','','AEL',10.00,20.00,1.00,'0','','','2013-05-04 10:48:19','0','2013-05-04 18:48:19',100);
INSERT INTO `cms_goods` (`id`, `name`, `remarks`, `code`, `purchase`, `sale`, `rate`, `unit`, `origin`, `brand`, `create_date`, `del_flag`, `update_date`, `num`) VALUES (9,'TCL电视','','TCLDS',1000.00,2000.00,1.00,'0','','','2013-05-04 10:48:40','0','2013-05-11 21:07:18',100);
INSERT INTO `cms_goods` (`id`, `name`, `remarks`, `code`, `purchase`, `sale`, `rate`, `unit`, `origin`, `brand`, `create_date`, `del_flag`, `update_date`, `num`) VALUES (10,'海尔洗衣机','','HEXYJ',2000.00,3000.00,0.50,'0','','','2013-05-04 10:48:54','0','2013-05-11 21:06:15',100);
INSERT INTO `cms_goods` (`id`, `name`, `remarks`, `code`, `purchase`, `sale`, `rate`, `unit`, `origin`, `brand`, `create_date`, `del_flag`, `update_date`, `num`) VALUES (11,'海尔热水器','131313','HERSQ',2001.11,2201.22,0.10,'个','','','2013-05-04 10:49:12','0','2013-05-12 19:40:21',100);
/*!40000 ALTER TABLE `cms_goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict`
--

DROP TABLE IF EXISTS `sys_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `label` varchar(100) NOT NULL COMMENT '标签',
  `value` varchar(100) NOT NULL COMMENT '键值',
  `type` varchar(100) NOT NULL COMMENT '类型',
  `desciption` varchar(100) NOT NULL COMMENT '描述',
  `sort` int(11) NOT NULL COMMENT '排序（升序）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  PRIMARY KEY (`id`),
  KEY `value` (`value`),
  KEY `label` (`label`),
  KEY `del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='系统字典表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict`
--

LOCK TABLES `sys_dict` WRITE;
/*!40000 ALTER TABLE `sys_dict` DISABLE KEYS */;
INSERT INTO `sys_dict` (`id`, `label`, `value`, `type`, `desciption`, `sort`, `del_flag`) VALUES (1,'正常','0','del_flag','删除标记',10,'0');
INSERT INTO `sys_dict` (`id`, `label`, `value`, `type`, `desciption`, `sort`, `del_flag`) VALUES (2,'删除','1','del_flag','删除标记',20,'0');
INSERT INTO `sys_dict` (`id`, `label`, `value`, `type`, `desciption`, `sort`, `del_flag`) VALUES (3,'显示','1','show_hide','显示/隐藏',10,'0');
INSERT INTO `sys_dict` (`id`, `label`, `value`, `type`, `desciption`, `sort`, `del_flag`) VALUES (4,'隐藏','0','show_hide','显示/隐藏',20,'0');
INSERT INTO `sys_dict` (`id`, `label`, `value`, `type`, `desciption`, `sort`, `del_flag`) VALUES (5,'是','1','yes_no','是/否',10,'0');
INSERT INTO `sys_dict` (`id`, `label`, `value`, `type`, `desciption`, `sort`, `del_flag`) VALUES (6,'否','0','yes_no','是/否',20,'0');
INSERT INTO `sys_dict` (`id`, `label`, `value`, `type`, `desciption`, `sort`, `del_flag`) VALUES (7,'已确认','0','order_status','订单状态',10,'0');
INSERT INTO `sys_dict` (`id`, `label`, `value`, `type`, `desciption`, `sort`, `del_flag`) VALUES (8,'无效','1','order_status','订单状态',20,'0');
INSERT INTO `sys_dict` (`id`, `label`, `value`, `type`, `desciption`, `sort`, `del_flag`) VALUES (9,'已应收','2','order_status','订单状态',30,'0');
/*!40000 ALTER TABLE `sys_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_cart_detail`
--

DROP TABLE IF EXISTS `cms_cart_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_cart_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_id` bigint(20) NOT NULL,
  `sale` float(10,2) NOT NULL COMMENT '销售价格',
  `num` int(11) NOT NULL COMMENT '数量',
  `cart_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='购物车明细';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_cart_detail`
--

LOCK TABLES `cms_cart_detail` WRITE;
/*!40000 ALTER TABLE `cms_cart_detail` DISABLE KEYS */;
INSERT INTO `cms_cart_detail` (`id`, `goods_id`, `sale`, `num`, `cart_id`) VALUES (10,1,30000.00,1,6);
/*!40000 ALTER TABLE `cms_cart_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_consumer`
--

DROP TABLE IF EXISTS `cms_consumer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_consumer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL COMMENT '店铺名称',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `code` varchar(45) NOT NULL COMMENT '记住码',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `shop_name` varchar(200) DEFAULT NULL COMMENT '店铺名称',
  `shop_phone` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL COMMENT '客户联系方式',
  `is_consumer` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否代收客户  0是 1否',
  `is_provider` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否供应商客户',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT ' 删除标识  0正常 1删除',
  `is_special` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0 表示正常客户 1表示散户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_consumer`
--

LOCK TABLES `cms_consumer` WRITE;
/*!40000 ALTER TABLE `cms_consumer` DISABLE KEYS */;
INSERT INTO `cms_consumer` (`id`, `name`, `remarks`, `code`, `address`, `shop_name`, `shop_phone`, `phone`, `is_consumer`, `is_provider`, `create_date`, `del_flag`, `is_special`) VALUES (1,'散户','11111111','cskh1','111','绝味鸭脖','88447905','13423232323',1,0,'2013-04-30 14:10:24','0',1);
INSERT INTO `cms_consumer` (`id`, `name`, `remarks`, `code`, `address`, `shop_name`, `shop_phone`, `phone`, `is_consumer`, `is_provider`, `create_date`, `del_flag`, `is_special`) VALUES (2,'测试客户1','11','CSKH1','1111','杨翔牛肚','87669881','13456567878',0,1,'2013-04-30 14:20:41','0',0);
INSERT INTO `cms_consumer` (`id`, `name`, `remarks`, `code`, `address`, `shop_name`, `shop_phone`, `phone`, `is_consumer`, `is_provider`, `create_date`, `del_flag`, `is_special`) VALUES (3,'测试客户3','11111','cskh2','嗷嗷待食','杨翔牛肚','87669881','13456567878',0,1,'2013-04-30 14:35:04','1',0);
INSERT INTO `cms_consumer` (`id`, `name`, `remarks`, `code`, `address`, `shop_name`, `shop_phone`, `phone`, `is_consumer`, `is_provider`, `create_date`, `del_flag`, `is_special`) VALUES (4,'测试客户2','111111111111111','cskh2','陕西省西安市高新区科技二路','杨翔牛肚','88447905','13456567878',0,1,'2013-04-30 15:44:04','0',0);
INSERT INTO `cms_consumer` (`id`, `name`, `remarks`, `code`, `address`, `shop_name`, `shop_phone`, `phone`, `is_consumer`, `is_provider`, `create_date`, `del_flag`, `is_special`) VALUES (5,'测试客户3','111','CSKH3','111','杨翔牛肚1','87669881','13456567878',0,1,'2013-05-01 12:31:34','0',0);
/*!40000 ALTER TABLE `cms_consumer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `login_name` varchar(100) NOT NULL COMMENT '登录名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(200) DEFAULT NULL COMMENT '电话',
  `mobile` varchar(200) DEFAULT NULL COMMENT '手机',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  `login_ip` varchar(100) DEFAULT NULL COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `roles` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `login_name` (`login_name`),
  KEY `del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='系统用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`id`, `login_name`, `password`, `name`, `email`, `phone`, `mobile`, `remarks`, `create_date`, `del_flag`, `login_ip`, `login_date`, `roles`) VALUES (1,'thinkgem','02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032','Thinkgem','thinkgem@163.com','8675','8675','','2013-04-30 06:12:12','0',NULL,NULL,'admin');
INSERT INTO `sys_user` (`id`, `login_name`, `password`, `name`, `email`, `phone`, `mobile`, `remarks`, `create_date`, `del_flag`, `login_ip`, `login_date`, `roles`) VALUES (2,'admin','e42eea6440c503c9c9a06842b2c59d36fb341fcd8bf9efc4f5ddf353','管理员','thinkgem@163.com','8675','8675','11iii0001111','2013-04-30 06:12:12','0','0:0:0:0:0:0:0:1','2013-04-30 21:03:00','admin');
INSERT INTO `sys_user` (`id`, `login_name`, `password`, `name`, `email`, `phone`, `mobile`, `remarks`, `create_date`, `del_flag`, `login_ip`, `login_date`, `roles`) VALUES (3,'user','c483879feb6704d4780ad0d259cca93ffc173b02c48084f8b186a53c','用户','thinkgem@163.com','8675','8675','','2013-04-30 06:12:12','0','127.0.0.1','2013-04-30 14:15:48','admin');
INSERT INTO `sys_user` (`id`, `login_name`, `password`, `name`, `email`, `phone`, `mobile`, `remarks`, `create_date`, `del_flag`, `login_ip`, `login_date`, `roles`) VALUES (4,'user2','c483879feb6704d4780ad0d259cca93ffc173b02c48084f8b186a53c','用户2','thinkgem@163.com','8675','8675','','2013-04-30 06:12:12','0',NULL,NULL,'admin');
INSERT INTO `sys_user` (`id`, `login_name`, `password`, `name`, `email`, `phone`, `mobile`, `remarks`, `create_date`, `del_flag`, `login_ip`, `login_date`, `roles`) VALUES (5,'user3','c483879feb6704d4780ad0d259cca93ffc173b02c48084f8b186a53c','用户3','thinkgem@163.com','8675','8675','','2013-04-30 06:12:12','0',NULL,NULL,'admin');
INSERT INTO `sys_user` (`id`, `login_name`, `password`, `name`, `email`, `phone`, `mobile`, `remarks`, `create_date`, `del_flag`, `login_ip`, `login_date`, `roles`) VALUES (6,'user4','c483879feb6704d4780ad0d259cca93ffc173b02c48084f8b186a53c','用户4','thinkgem@163.com','8675','8675','','2013-04-30 06:12:12','0',NULL,NULL,'admin');
INSERT INTO `sys_user` (`id`, `login_name`, `password`, `name`, `email`, `phone`, `mobile`, `remarks`, `create_date`, `del_flag`, `login_ip`, `login_date`, `roles`) VALUES (7,'user5','c483879feb6704d4780ad0d259cca93ffc173b02c48084f8b186a53c','用户5','thinkgem@163.com','8675','8675','','2013-04-30 06:12:12','0',NULL,NULL,'admin');
INSERT INTO `sys_user` (`id`, `login_name`, `password`, `name`, `email`, `phone`, `mobile`, `remarks`, `create_date`, `del_flag`, `login_ip`, `login_date`, `roles`) VALUES (8,'user6','c483879feb6704d4780ad0d259cca93ffc173b02c48084f8b186a53c','用户6','thinkgem@163.com','8675','8675','','2013-04-30 06:12:12','0',NULL,NULL,'admin');
INSERT INTO `sys_user` (`id`, `login_name`, `password`, `name`, `email`, `phone`, `mobile`, `remarks`, `create_date`, `del_flag`, `login_ip`, `login_date`, `roles`) VALUES (9,'user7','c483879feb6704d4780ad0d259cca93ffc173b02c48084f8b186a53c','用户7','thinkgem@163.com','8675','8675','','2013-04-30 06:12:12','0',NULL,NULL,'admin');
INSERT INTO `sys_user` (`id`, `login_name`, `password`, `name`, `email`, `phone`, `mobile`, `remarks`, `create_date`, `del_flag`, `login_ip`, `login_date`, `roles`) VALUES (10,'user8','c483879feb6704d4780ad0d259cca93ffc173b02c48084f8b186a53c','用户8','thinkgem@163.com','8675','8675','','2013-04-30 06:12:12','0',NULL,NULL,'admin');
INSERT INTO `sys_user` (`id`, `login_name`, `password`, `name`, `email`, `phone`, `mobile`, `remarks`, `create_date`, `del_flag`, `login_ip`, `login_date`, `roles`) VALUES (11,'user9','c483879feb6704d4780ad0d259cca93ffc173b02c48084f8b186a53c','用户9','thinkgem@163.com','8675','8675','','2013-04-30 06:12:12','0',NULL,NULL,'admin');
INSERT INTO `sys_user` (`id`, `login_name`, `password`, `name`, `email`, `phone`, `mobile`, `remarks`, `create_date`, `del_flag`, `login_ip`, `login_date`, `roles`) VALUES (12,'user10','c483879feb6704d4780ad0d259cca93ffc173b02c48084f8b186a53c','用户10','thinkgem@163.com','8675','8675','','2013-04-30 06:12:12','0',NULL,NULL,'admin');
INSERT INTO `sys_user` (`id`, `login_name`, `password`, `name`, `email`, `phone`, `mobile`, `remarks`, `create_date`, `del_flag`, `login_ip`, `login_date`, `roles`) VALUES (13,'user11','c483879feb6704d4780ad0d259cca93ffc173b02c48084f8b186a53c','用户11','thinkgem@163.com','8675','8675','','2013-04-30 06:12:12','0',NULL,NULL,'admin');
INSERT INTO `sys_user` (`id`, `login_name`, `password`, `name`, `email`, `phone`, `mobile`, `remarks`, `create_date`, `del_flag`, `login_ip`, `login_date`, `roles`) VALUES (14,'user12','c483879feb6704d4780ad0d259cca93ffc173b02c48084f8b186a53c','用户12','thinkgem@163.com','8675','8675','','2013-04-30 06:12:12','0',NULL,NULL,'admin');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-05-12 20:20:44
