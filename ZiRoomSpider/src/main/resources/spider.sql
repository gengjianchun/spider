/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : spider

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-02-02 18:36:34
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
  `ROOMNUM` int(20) DEFAULT NULL,
  `WOMANNUM` int(10) DEFAULT NULL,
  `MENNUM` int(10) DEFAULT NULL,
  `UPDATETIME` datetime DEFAULT NULL,
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
  `UPDATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4265 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
DROP TRIGGER IF EXISTS `del_room_trigger`;
DELIMITER ;;
CREATE TRIGGER `del_room_trigger` AFTER DELETE ON `detail` FOR EACH ROW delete from room where id = old.id
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `del_detail_trigger`;
DELIMITER ;;
CREATE TRIGGER `del_detail_trigger` AFTER DELETE ON `room` FOR EACH ROW begin
    delete from detail where id = old.id;
    end
;;
DELIMITER ;
