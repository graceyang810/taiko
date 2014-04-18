/*
 Navicat MySQL Data Transfer

 Source Server         : taiko
 Source Server Type    : MySQL
 Source Server Version : 50616
 Source Host           : localhost
 Source Database       : TaikoDB

 Target Server Type    : MySQL
 Target Server Version : 50616
 File Encoding         : gb2312

 Date: 04/18/2014 21:21:18 PM
*/

SET NAMES latin1;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `usertable`
-- ----------------------------
DROP TABLE IF EXISTS `usertable`;
CREATE TABLE `usertable` (
  `ID` smallint(16) NOT NULL AUTO_INCREMENT,
  `UserName` char(16) NOT NULL,
  `Password` char(16) NOT NULL,
  `ExperiencePoint` int(32) NOT NULL DEFAULT '0',
  `Level` tinyint(8) NOT NULL DEFAULT '1',
  `PhotoURL` char(50) DEFAULT NULL,
  `Gender` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`ID`,`UserName`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
