/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : sharde

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2017-09-14 22:24:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'duomu', '26', 'beijing');
INSERT INTO `user` VALUES ('2', 'user1', null, null);
INSERT INTO `user` VALUES ('3', 'user2', null, null);
INSERT INTO `user` VALUES ('4', 'user3', null, null);
INSERT INTO `user` VALUES ('5', 'user4', null, null);
INSERT INTO `user` VALUES ('6', 'user5', null, null);
INSERT INTO `user` VALUES ('7', 'user1', null, null);
INSERT INTO `user` VALUES ('8', 'user2', null, null);
INSERT INTO `user` VALUES ('9', 'user3', null, null);
INSERT INTO `user` VALUES ('10', 'user4', null, null);
INSERT INTO `user` VALUES ('11', 'user5', null, null);
INSERT INTO `user` VALUES ('12', 'user0', null, null);
INSERT INTO `user` VALUES ('13', 'user1', null, null);
INSERT INTO `user` VALUES ('14', 'user2', null, null);
INSERT INTO `user` VALUES ('15', 'user3', null, null);
INSERT INTO `user` VALUES ('16', 'user4', null, null);
INSERT INTO `user` VALUES ('17', 'user5', null, null);

-- ----------------------------
-- Table structure for user_0
-- ----------------------------
DROP TABLE IF EXISTS `user_0`;
CREATE TABLE `user_0` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=115033898924113921 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user_0
-- ----------------------------
INSERT INTO `user_0` VALUES ('112944529560043520', 'user1', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944530461818880', 'user3', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944530776391680', 'user5', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944531678167040', 'user10', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944531992739840', 'user12', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944532475084800', 'user15', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944533251031040', 'user20', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944534760980480', 'user33', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944536501616640', 'user42', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944536711331840', 'user44', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944537067847680', 'user47', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944537277562880', 'user49', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944594215239680', 'user1', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944595096043520', 'user4', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944595473530880', 'user7', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944595683246080', 'user9', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944595892961280', 'user11', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944596668907520', 'user18', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944597319024640', 'user23', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944597528739840', 'user25', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944598094970880', 'user28', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944598304686080', 'user30', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944598514401280', 'user32', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944599101603840', 'user37', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944599311319040', 'user39', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944599521034240', 'user41', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944599730749440', 'user43', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944599940464640', 'user45', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944600150179840', 'user47', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944600359895040', 'user49', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944784594698240', 'user3', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944784804413440', 'user5', '26', 'beijing');
INSERT INTO `user_0` VALUES ('112944785014128640', 'user7', '26', 'beijing');
INSERT INTO `user_0` VALUES ('115033898924113920', 'user6', '26', 'beijing');

-- ----------------------------
-- Table structure for user_1
-- ----------------------------
DROP TABLE IF EXISTS `user_1`;
CREATE TABLE `user_1` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=112944790651273217 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user_1
-- ----------------------------
INSERT INTO `user_1` VALUES ('112944531338428416', 'user8', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944532345061376', 'user14', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944533456551936', 'user22', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944533666267136', 'user24', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944533875982336', 'user26', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944534085697536', 'user28', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944534442213376', 'user31', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944536287707136', 'user40', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944536853938176', 'user45', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944595574194176', 'user8', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944595783909376', 'user10', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944596098482176', 'user13', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944596308197376', 'user15', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944596413054976', 'user16', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944596559855616', 'user17', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944597084143616', 'user21', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944598761865216', 'user34', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944783982329856', 'user1', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944785303535616', 'user9', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944786079481856', 'user14', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944786708627456', 'user20', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944787505545216', 'user24', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944787924975616', 'user26', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944788134690816', 'user28', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944788344406016', 'user30', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944788554121216', 'user32', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944788763836416', 'user34', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944788973551616', 'user36', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944789812412416', 'user42', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944790022127616', 'user44', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944790231842816', 'user46', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944790441558016', 'user48', '26', 'beijing');
INSERT INTO `user_1` VALUES ('112944790651273216', 'user50', '26', 'beijing');

-- ----------------------------
-- Table structure for user_2
-- ----------------------------
DROP TABLE IF EXISTS `user_2`;
CREATE TABLE `user_2` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=112944789221015553 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user_2
-- ----------------------------
INSERT INTO `user_2` VALUES ('112944530558287872', 'user4', '26', 'beijing');
INSERT INTO `user_2` VALUES ('112944531019661312', 'user6', '26', 'beijing');
INSERT INTO `user_2` VALUES ('112944532592525312', 'user16', '26', 'beijing');
INSERT INTO `user_2` VALUES ('112944534542876672', 'user32', '26', 'beijing');
INSERT INTO `user_2` VALUES ('112944535318822912', 'user36', '26', 'beijing');
INSERT INTO `user_2` VALUES ('112944535528538112', 'user38', '26', 'beijing');
INSERT INTO `user_2` VALUES ('112944536052826112', 'user39', '26', 'beijing');
INSERT INTO `user_2` VALUES ('112944536954601472', 'user46', '26', 'beijing');
INSERT INTO `user_2` VALUES ('112944537164316672', 'user48', '26', 'beijing');
INSERT INTO `user_2` VALUES ('112944594856968192', 'user2', '26', 'beijing');
INSERT INTO `user_2` VALUES ('112944594982797312', 'user3', '26', 'beijing');
INSERT INTO `user_2` VALUES ('112944595339313152', 'user6', '26', 'beijing');
INSERT INTO `user_2` VALUES ('112944596199145472', 'user14', '26', 'beijing');
INSERT INTO `user_2` VALUES ('112944596975091712', 'user20', '26', 'beijing');
INSERT INTO `user_2` VALUES ('112944597751037952', 'user26', '26', 'beijing');
INSERT INTO `user_2` VALUES ('112944597960753152', 'user27', '26', 'beijing');
INSERT INTO `user_2` VALUES ('112944598191439872', 'user29', '26', 'beijing');
INSERT INTO `user_2` VALUES ('112944598401155072', 'user31', '26', 'beijing');
INSERT INTO `user_2` VALUES ('112944785550999552', 'user11', '26', 'beijing');
INSERT INTO `user_2` VALUES ('112944785970429952', 'user13', '26', 'beijing');
INSERT INTO `user_2` VALUES ('112944786180145152', 'user15', '26', 'beijing');
INSERT INTO `user_2` VALUES ('112944786389860352', 'user17', '26', 'beijing');
INSERT INTO `user_2` VALUES ('112944787753009152', 'user25', '26', 'beijing');
INSERT INTO `user_2` VALUES ('112944788654784512', 'user33', '26', 'beijing');
INSERT INTO `user_2` VALUES ('112944788864499712', 'user35', '26', 'beijing');
INSERT INTO `user_2` VALUES ('112944789221015552', 'user38', '26', 'beijing');

-- ----------------------------
-- Table structure for user_3
-- ----------------------------
DROP TABLE IF EXISTS `user_3`;
CREATE TABLE `user_3` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=112944790538027009 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user_3
-- ----------------------------
INSERT INTO `user_3` VALUES ('112944530344378368', 'user2', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944531120324608', 'user7', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944532231815168', 'user13', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944532693188608', 'user17', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944533364277248', 'user21', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944533783707648', 'user25', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944534328967168', 'user30', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944535000055808', 'user34', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944595230261248', 'user5', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944597222555648', 'user22', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944597432270848', 'user24', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944598627647488', 'user33', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944598858334208', 'user35', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944599005134848', 'user36', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944599214850048', 'user38', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944599424565248', 'user40', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944599634280448', 'user42', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944599843995648', 'user44', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944600263426048', 'user48', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944600473141248', 'user50', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944784707944448', 'user4', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944784917659648', 'user6', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944785190289408', 'user8', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944785756520448', 'user12', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944786280808448', 'user16', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944786595381248', 'user19', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944788021444608', 'user27', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944788231159808', 'user29', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944789699166208', 'user41', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944789908881408', 'user43', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944790118596608', 'user45', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944790328311808', 'user47', '26', 'beijing');
INSERT INTO `user_3` VALUES ('112944790538027008', 'user49', '26', 'beijing');

-- ----------------------------
-- Table structure for user_4
-- ----------------------------
DROP TABLE IF EXISTS `user_4`;
CREATE TABLE `user_4` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=112944789527199745 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user_4
-- ----------------------------
INSERT INTO `user_4` VALUES ('112944531577503744', 'user9', '26', 'beijing');
INSERT INTO `user_4` VALUES ('112944531787218944', 'user11', '26', 'beijing');
INSERT INTO `user_4` VALUES ('112944532835794944', 'user18', '26', 'beijing');
INSERT INTO `user_4` VALUES ('112944533045510144', 'user19', '26', 'beijing');
INSERT INTO `user_4` VALUES ('112944533569798144', 'user23', '26', 'beijing');
INSERT INTO `user_4` VALUES ('112944533989228544', 'user27', '26', 'beijing');
INSERT INTO `user_4` VALUES ('112944534198943744', 'user29', '26', 'beijing');
INSERT INTO `user_4` VALUES ('112944535205576704', 'user35', '26', 'beijing');
INSERT INTO `user_4` VALUES ('112944535415291904', 'user37', '26', 'beijing');
INSERT INTO `user_4` VALUES ('112944536400953344', 'user41', '26', 'beijing');
INSERT INTO `user_4` VALUES ('112944536610668544', 'user43', '26', 'beijing');
INSERT INTO `user_4` VALUES ('112944537407586304', 'user50', '26', 'beijing');
INSERT INTO `user_4` VALUES ('112944596002013184', 'user12', '26', 'beijing');
INSERT INTO `user_4` VALUES ('112944596798930944', 'user19', '26', 'beijing');
INSERT INTO `user_4` VALUES ('112944600049516544', 'user46', '26', 'beijing');
INSERT INTO `user_4` VALUES ('112944784515006464', 'user2', '26', 'beijing');
INSERT INTO `user_4` VALUES ('112944785416781824', 'user10', '26', 'beijing');
INSERT INTO `user_4` VALUES ('112944786486329344', 'user18', '26', 'beijing');
INSERT INTO `user_4` VALUES ('112944786842845184', 'user21', '26', 'beijing');
INSERT INTO `user_4` VALUES ('112944787052560384', 'user22', '26', 'beijing');
INSERT INTO `user_4` VALUES ('112944787262275584', 'user23', '26', 'beijing');
INSERT INTO `user_4` VALUES ('112944788457652224', 'user31', '26', 'beijing');
INSERT INTO `user_4` VALUES ('112944789086797824', 'user37', '26', 'beijing');
INSERT INTO `user_4` VALUES ('112944789317484544', 'user39', '26', 'beijing');
INSERT INTO `user_4` VALUES ('112944789527199744', 'user40', '26', 'beijing');

-- ----------------------------
-- Table structure for user_5
-- ----------------------------
DROP TABLE IF EXISTS `user_5`;
CREATE TABLE `user_5` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user_5
-- ----------------------------

-- ----------------------------
-- Table structure for user_6
-- ----------------------------
DROP TABLE IF EXISTS `user_6`;
CREATE TABLE `user_6` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user_6
-- ----------------------------

-- ----------------------------
-- Table structure for user_7
-- ----------------------------
DROP TABLE IF EXISTS `user_7`;
CREATE TABLE `user_7` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user_7
-- ----------------------------

-- ----------------------------
-- Table structure for user_8
-- ----------------------------
DROP TABLE IF EXISTS `user_8`;
CREATE TABLE `user_8` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user_8
-- ----------------------------

-- ----------------------------
-- Table structure for user_9
-- ----------------------------
DROP TABLE IF EXISTS `user_9`;
CREATE TABLE `user_9` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user_9
-- ----------------------------

-- ----------------------------
-- Table structure for user_job
-- ----------------------------
DROP TABLE IF EXISTS `user_job`;
CREATE TABLE `user_job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `company` varchar(200) DEFAULT NULL,
  `position` varchar(200) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `years` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户工作履历表';

-- ----------------------------
-- Records of user_job
-- ----------------------------

-- ----------------------------
-- Table structure for user_job_0
-- ----------------------------
DROP TABLE IF EXISTS `user_job_0`;
CREATE TABLE `user_job_0` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `company` varchar(200) DEFAULT NULL,
  `position` varchar(200) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `years` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户工作履历表';

-- ----------------------------
-- Records of user_job_0
-- ----------------------------

-- ----------------------------
-- Table structure for user_job_1
-- ----------------------------
DROP TABLE IF EXISTS `user_job_1`;
CREATE TABLE `user_job_1` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `company` varchar(200) DEFAULT NULL,
  `position` varchar(200) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `years` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户工作履历表';

-- ----------------------------
-- Records of user_job_1
-- ----------------------------

-- ----------------------------
-- Table structure for user_job_2
-- ----------------------------
DROP TABLE IF EXISTS `user_job_2`;
CREATE TABLE `user_job_2` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `company` varchar(200) DEFAULT NULL,
  `position` varchar(200) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `years` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户工作履历表';

-- ----------------------------
-- Records of user_job_2
-- ----------------------------

-- ----------------------------
-- Table structure for user_job_3
-- ----------------------------
DROP TABLE IF EXISTS `user_job_3`;
CREATE TABLE `user_job_3` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `company` varchar(200) DEFAULT NULL,
  `position` varchar(200) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `years` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户工作履历表';

-- ----------------------------
-- Records of user_job_3
-- ----------------------------

-- ----------------------------
-- Table structure for user_job_4
-- ----------------------------
DROP TABLE IF EXISTS `user_job_4`;
CREATE TABLE `user_job_4` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `company` varchar(200) DEFAULT NULL,
  `position` varchar(200) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `years` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户工作履历表';

-- ----------------------------
-- Records of user_job_4
-- ----------------------------

-- ----------------------------
-- Table structure for user_job_5
-- ----------------------------
DROP TABLE IF EXISTS `user_job_5`;
CREATE TABLE `user_job_5` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `company` varchar(200) DEFAULT NULL,
  `position` varchar(200) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `years` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户工作履历表';

-- ----------------------------
-- Records of user_job_5
-- ----------------------------

-- ----------------------------
-- Table structure for user_job_6
-- ----------------------------
DROP TABLE IF EXISTS `user_job_6`;
CREATE TABLE `user_job_6` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `company` varchar(200) DEFAULT NULL,
  `position` varchar(200) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `years` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户工作履历表';

-- ----------------------------
-- Records of user_job_6
-- ----------------------------

-- ----------------------------
-- Table structure for user_job_7
-- ----------------------------
DROP TABLE IF EXISTS `user_job_7`;
CREATE TABLE `user_job_7` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `company` varchar(200) DEFAULT NULL,
  `position` varchar(200) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `years` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户工作履历表';

-- ----------------------------
-- Records of user_job_7
-- ----------------------------

-- ----------------------------
-- Table structure for user_job_8
-- ----------------------------
DROP TABLE IF EXISTS `user_job_8`;
CREATE TABLE `user_job_8` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `company` varchar(200) DEFAULT NULL,
  `position` varchar(200) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `years` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户工作履历表';

-- ----------------------------
-- Records of user_job_8
-- ----------------------------

-- ----------------------------
-- Table structure for user_job_9
-- ----------------------------
DROP TABLE IF EXISTS `user_job_9`;
CREATE TABLE `user_job_9` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `company` varchar(200) DEFAULT NULL,
  `position` varchar(200) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `years` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户工作履历表';

-- ----------------------------
-- Records of user_job_9
-- ----------------------------
