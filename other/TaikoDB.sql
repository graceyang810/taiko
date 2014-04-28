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

 Date: 04/20/2014 22:17:13 PM
*/

SET NAMES latin1;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `ResultTable`
-- ----------------------------
DROP TABLE IF EXISTS `ResultTable`;
CREATE TABLE `ResultTable` (
  `ID` int(11) NOT NULL,
  `Score` int(11) NOT NULL DEFAULT '0',
  `Perfect` int(11) NOT NULL DEFAULT '0',
  `Cool` int(11) NOT NULL DEFAULT '0',
  `Miss` int(11) NOT NULL DEFAULT '0',
  `Combo` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `ShakeApplyTable`
-- ----------------------------
DROP TABLE IF EXISTS `ShakeApplyTable`;
CREATE TABLE `ShakeApplyTable` (
  `ID` int(11) NOT NULL,
  `shakeTime` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `ShakeRoomTable`
-- ----------------------------
DROP TABLE IF EXISTS `ShakeRoomTable`;
CREATE TABLE `ShakeRoomTable` (
  `host` int(11) NOT NULL,
  `guest` int(11) NOT NULL DEFAULT '0',
  `musicID` int(11) NOT NULL DEFAULT '0',
  `feedback` int(11) NOT NULL DEFAULT '-1' COMMENT '-1表示未回应，0表示拒绝，1表示同意'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `musictable`
-- ----------------------------
DROP TABLE IF EXISTS `musictable`;
CREATE TABLE `musictable` (
  `ID` int(16) NOT NULL AUTO_INCREMENT,
  `RhythmURL` varchar(255) NOT NULL,
  `MusicName` varchar(255) NOT NULL,
  `Difficulty` int(255) NOT NULL,
  `SoundURL` varchar(255) NOT NULL,
  `Length` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `musictable`
-- ----------------------------
BEGIN;
INSERT INTO `musictable` VALUES ('1', 'RhythmInfo/rhythm_1.json', '伤心的人别听慢歌', '2', '../songs/1.mp3', '141'), ('2', 'RhythmInfo/rhythm_2.json', '伤心的人别听慢歌2', '4', '../songs/1.mp3', '141');
COMMIT;

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

-- ----------------------------
--  Records of `usertable`
-- ----------------------------
BEGIN;
INSERT INTO `usertable` VALUES ('9', 'TableOpTest', 'hahaha', '0', '1', null, null), ('10', 'servletDBTest', 'xixixixi', '0', '1', null, null);
COMMIT;

-- ----------------------------
--  Table structure for `waitingRoomTable`
-- ----------------------------
DROP TABLE IF EXISTS `waitingRoomTable`;
CREATE TABLE `waitingRoomTable` (
  `host` int(11) NOT NULL,
  `guest` int(11) NOT NULL DEFAULT '0',
  `musicID` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`host`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

SET FOREIGN_KEY_CHECKS = 1;
