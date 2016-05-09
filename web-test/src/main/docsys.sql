/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50537
Source Host           : localhost:3306
Source Database       : docsys

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2016-01-29 10:26:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for apidescriptor
-- ----------------------------
DROP TABLE IF EXISTS `apidescriptor`;
CREATE TABLE `apidescriptor` (
  `uuid` int(6) NOT NULL AUTO_INCREMENT,
  `functionno` varchar(6) NOT NULL,
  `functiondesc` varchar(255) NOT NULL,
  `classname` varchar(255) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `version` varchar(10) DEFAULT NULL,
  `createuser` varchar(20) DEFAULT NULL,
  `createdate` varchar(14) DEFAULT NULL,
  `lastupdatedate` varchar(14) DEFAULT NULL,
  `lastupdateuser` varchar(20) DEFAULT NULL,
  `updatelog` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for apifield
-- ----------------------------
DROP TABLE IF EXISTS `apifield`;
CREATE TABLE `apifield` (
  `uuid` int(10) NOT NULL AUTO_INCREMENT,
  `functionno` varchar(6) NOT NULL,
  `fieldname` varchar(20) NOT NULL,
  `fieldtype` varchar(10) NOT NULL,
  `fielddesc` varchar(255) NOT NULL,
  `fielddirection` varchar(1) NOT NULL,
  `ispublic` varchar(1) NOT NULL,
  `required` varchar(1) NOT NULL,
  `islistfield` varchar(1) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
