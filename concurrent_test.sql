/*
Navicat MySQL Data Transfer

Source Server         : cdb-bt6j5xwk.cd.tencentcdb.com
Source Server Version : 50718
Source Host           : cdb-bt6j5xwk.cd.tencentcdb.com:10069
Source Database       : concurrent_test

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2020-02-19 17:41:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `amount` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', 'iphone99', '1000', '0');
INSERT INTO `goods` VALUES ('2', 'mi99', '1', '0');
