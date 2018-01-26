/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : spider

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-01-26 15:32:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for detail
-- ----------------------------
DROP TABLE IF EXISTS `detail`;
CREATE TABLE `detail` (
  `ID` int(11) NOT NULL,
  `AREA` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ORIENTATION` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ROOMNUM` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `WOMANNUM` int(10) DEFAULT NULL,
  `MENNUM` int(10) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `WEBURL` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SUBLINE` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STATION` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DISTANCE` int(11) DEFAULT NULL,
  `TITLE` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PRICE` int(30) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2988 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
