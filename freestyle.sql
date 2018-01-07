/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50520
Source Host           : 127.0.0.1:3306
Source Database       : freestyle

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2016-12-31 15:14:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_core_account
-- ----------------------------
DROP TABLE IF EXISTS `t_core_account`;
CREATE TABLE `t_core_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `created_time` datetime DEFAULT NULL COMMENT '记录创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '记录更新时间',
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `realname` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `tel` varchar(100) DEFAULT NULL,
  `head_img_id` bigint(20) DEFAULT NULL,
  `head_img_thumb_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `AK_unique_username` (`username`),
  KEY `FK_Reference_14` (`head_img_id`),
  KEY `FK_Reference_15` (`head_img_thumb_id`),
  CONSTRAINT `FK_Reference_14` FOREIGN KEY (`head_img_id`) REFERENCES `t_sys_file` (`id`),
  CONSTRAINT `FK_Reference_15` FOREIGN KEY (`head_img_thumb_id`) REFERENCES `t_sys_file` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_core_account
-- ----------------------------
INSERT INTO `t_core_account` VALUES ('18', '2016-12-19 20:47:48', '2016-12-29 10:49:29', 'admin', 'd49064f3f879c57db22f033208c31f08', 'admin', 'admin', 'admin', '180', '180');
INSERT INTO `t_core_account` VALUES ('19', '2016-12-19 20:47:56', '2016-12-29 10:21:49', 'admin1', 'f5d30a377a75a55b80aee9c3c7c9fc4d', 'admin1', 'admin', 'admin', null, null);
INSERT INTO `t_core_account` VALUES ('20', '2016-12-19 22:31:11', '2016-12-29 10:21:48', 'admin3', 'f5d30a377a75a55b80aee9c3c7c9fc4d', 'admin3', null, null, null, null);
INSERT INTO `t_core_account` VALUES ('21', '2016-12-20 08:38:49', '2016-12-29 10:49:48', 'hzb', '10de23e2dd2e7c6051924b768b1905d5', 'hzb', null, null, null, null);
INSERT INTO `t_core_account` VALUES ('22', '2016-12-27 18:43:01', '2016-12-29 12:36:53', 'cjm', '10de23e2dd2e7c6051924b768b1905d5', '姓名', '98988@qq.com', '18900305232', '196', '196');
INSERT INTO `t_core_account` VALUES ('23', '2016-12-29 10:17:29', '2016-12-29 15:26:03', 'ljy', '10de23e2dd2e7c6051924b768b1905d5', 'ljy', null, null, '231', '231');
INSERT INTO `t_core_account` VALUES ('24', '2016-12-29 10:50:33', '2016-12-29 10:50:33', 'ndr', '10de23e2dd2e7c6051924b768b1905d5', 'ndr', null, null, null, null);
INSERT INTO `t_core_account` VALUES ('25', '2016-12-29 10:51:04', '2016-12-29 11:28:21', 'ruanyin', '1c709e301190367492442c6527491e38', 'ruanyin', null, null, null, null);
INSERT INTO `t_core_account` VALUES ('26', '2016-12-29 11:35:21', '2016-12-29 11:35:21', '爱的啊', '178a167390484da6e0a160e2498f7ac3', '爱的啊', null, null, null, null);
INSERT INTO `t_core_account` VALUES ('27', '2016-12-29 11:37:03', '2016-12-29 11:37:03', 'qwep77', '178a167390484da6e0a160e2498f7ac3', 'qwep77', null, null, null, null);

-- ----------------------------
-- Table structure for t_core_album
-- ----------------------------
DROP TABLE IF EXISTS `t_core_album`;
CREATE TABLE `t_core_album` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `created_time` datetime DEFAULT NULL COMMENT '记录创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '记录更新时间',
  `account_id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `cover_photo_id` bigint(20) DEFAULT NULL,
  `photo_count` int(11) NOT NULL,
  `visits_count` int(11) NOT NULL,
  `comment_count` int(11) NOT NULL,
  `agree_count` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `AK_unique_account_albumName` (`account_id`,`name`),
  KEY `FK_Reference_8` (`cover_photo_id`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`account_id`) REFERENCES `t_core_account` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`cover_photo_id`) REFERENCES `t_core_photo` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_core_album
-- ----------------------------
INSERT INTO `t_core_album` VALUES ('54', '2016-12-19 21:03:27', '2016-12-27 18:06:28', '18', '相册1', '', '362', '12', '0', '0', '0');
INSERT INTO `t_core_album` VALUES ('55', '2016-12-19 22:18:21', '2016-12-20 10:27:20', '19', '第一个相册', '描述haha', null, '13', '0', '0', '0');
INSERT INTO `t_core_album` VALUES ('56', '2016-12-21 15:09:49', '2016-12-21 15:10:16', '18', '我是ry', '你好', null, '1', '0', '0', '0');
INSERT INTO `t_core_album` VALUES ('57', '2016-12-27 19:02:49', '2016-12-27 19:05:12', '22', '相册22', '描述2', '386', '33', '0', '0', '0');
INSERT INTO `t_core_album` VALUES ('58', '2016-12-29 12:44:56', '2016-12-29 12:44:56', '22', 'uuuuuu', '', null, '0', '0', '0', '0');
INSERT INTO `t_core_album` VALUES ('60', '2016-12-29 15:30:06', '2016-12-29 15:37:27', '23', '11', '得到', '461', '9', '0', '0', '0');
INSERT INTO `t_core_album` VALUES ('61', '2016-12-29 15:32:58', '2016-12-29 15:33:26', '23', '22', '', null, '2', '0', '0', '0');

-- ----------------------------
-- Table structure for t_core_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_core_comment`;
CREATE TABLE `t_core_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `created_time` datetime DEFAULT NULL COMMENT '记录创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '记录更新时间',
  `mood_id` bigint(20) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `account_id` bigint(20) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_12` (`account_id`),
  KEY `FK_Reference_13` (`parent_id`),
  KEY `FK_Reference_4` (`mood_id`),
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`account_id`) REFERENCES `t_core_account` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`parent_id`) REFERENCES `t_core_comment` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`mood_id`) REFERENCES `t_core_mood` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=148 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_core_comment
-- ----------------------------
INSERT INTO `t_core_comment` VALUES ('128', '2016-12-19 21:34:52', '2016-12-19 21:34:52', '48', 'h', '18', null);
INSERT INTO `t_core_comment` VALUES ('129', '2016-12-19 22:07:54', '2016-12-19 22:07:54', '49', 'y', '19', null);
INSERT INTO `t_core_comment` VALUES ('130', '2016-12-19 23:57:40', '2016-12-19 23:57:40', '52', '评论', '18', null);
INSERT INTO `t_core_comment` VALUES ('131', '2016-12-20 09:06:04', '2016-12-20 09:06:04', '52', '真棒', '21', null);
INSERT INTO `t_core_comment` VALUES ('132', '2016-12-20 09:06:21', '2016-12-20 09:06:21', '52', '太棒了', '21', null);
INSERT INTO `t_core_comment` VALUES ('133', '2016-12-20 09:08:47', '2016-12-20 09:08:47', '52', '@admin    评论...哈', '21', '130');
INSERT INTO `t_core_comment` VALUES ('134', '2016-12-20 09:08:56', '2016-12-20 09:08:56', '52', '@hzb 评论...嘿', '21', '132');
INSERT INTO `t_core_comment` VALUES ('135', '2016-12-21 15:00:01', '2016-12-21 15:00:01', '55', '组长真棒！！！', '18', null);
INSERT INTO `t_core_comment` VALUES ('136', '2016-12-21 15:00:31', '2016-12-21 15:00:31', '54', '照片不错', '18', null);
INSERT INTO `t_core_comment` VALUES ('137', '2016-12-21 15:02:31', '2016-12-21 15:02:31', '54', '@admin 真的不错', '18', '136');
INSERT INTO `t_core_comment` VALUES ('138', '2016-12-26 16:43:03', '2016-12-26 16:43:03', '55', 'asdfasdf', '18', null);
INSERT INTO `t_core_comment` VALUES ('140', '2016-12-27 18:59:01', '2016-12-27 18:59:01', '55', '@admin 评论...', '22', '138');
INSERT INTO `t_core_comment` VALUES ('146', '2016-12-29 15:38:42', '2016-12-29 15:38:42', '68', '哈哈', '23', null);
INSERT INTO `t_core_comment` VALUES ('147', '2016-12-29 15:39:11', '2016-12-29 15:39:11', '55', '@admin 评论...你好', '23', '138');

-- ----------------------------
-- Table structure for t_core_friend
-- ----------------------------
DROP TABLE IF EXISTS `t_core_friend`;
CREATE TABLE `t_core_friend` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `created_time` datetime DEFAULT NULL COMMENT '记录创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '记录更新时间',
  `group_id` bigint(20) NOT NULL,
  `friend_id` bigint(20) NOT NULL,
  `account_id` bigint(20) NOT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_16` (`account_id`),
  KEY `FK_Reference_6` (`group_id`),
  KEY `FK_Reference_7` (`friend_id`),
  CONSTRAINT `FK_Reference_16` FOREIGN KEY (`account_id`) REFERENCES `t_core_account` (`id`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`group_id`) REFERENCES `t_core_friend_group` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`friend_id`) REFERENCES `t_core_account` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_core_friend
-- ----------------------------
INSERT INTO `t_core_friend` VALUES ('1', '2016-12-29 15:48:45', '2016-12-29 15:48:45', '33', '18', '24', '连嘉阳');

-- ----------------------------
-- Table structure for t_core_friend_group
-- ----------------------------
DROP TABLE IF EXISTS `t_core_friend_group`;
CREATE TABLE `t_core_friend_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `created_time` datetime DEFAULT NULL COMMENT '记录创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '记录更新时间',
  `name` varchar(100) NOT NULL,
  `account_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `AK_unique_account_groupName` (`name`,`account_id`),
  KEY `FK_Reference_5` (`account_id`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`account_id`) REFERENCES `t_core_account` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_core_friend_group
-- ----------------------------
INSERT INTO `t_core_friend_group` VALUES ('26', '2016-12-19 21:39:42', '2016-12-19 21:39:42', '23', '19');
INSERT INTO `t_core_friend_group` VALUES ('27', '2016-12-20 00:02:46', '2016-12-20 00:02:46', '好友分组一', '18');
INSERT INTO `t_core_friend_group` VALUES ('28', '2016-12-20 08:26:22', '2016-12-20 08:26:22', '777', '18');
INSERT INTO `t_core_friend_group` VALUES ('29', '2016-12-20 08:36:06', '2016-12-20 08:36:06', '我的大学', '19');
INSERT INTO `t_core_friend_group` VALUES ('30', '2016-12-20 08:42:07', '2016-12-20 09:36:40', '我的大学', '21');
INSERT INTO `t_core_friend_group` VALUES ('32', '2016-12-27 19:13:54', '2016-12-27 19:13:54', '我的好友', '22');
INSERT INTO `t_core_friend_group` VALUES ('33', '2016-12-29 15:47:42', '2016-12-29 15:47:42', '我的大学', '24');

-- ----------------------------
-- Table structure for t_core_mood
-- ----------------------------
DROP TABLE IF EXISTS `t_core_mood`;
CREATE TABLE `t_core_mood` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `created_time` datetime DEFAULT NULL COMMENT '记录创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '记录更新时间',
  `account_id` bigint(20) NOT NULL,
  `content` varchar(2000) NOT NULL,
  `type` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_3` (`account_id`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`account_id`) REFERENCES `t_core_account` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_core_mood
-- ----------------------------
INSERT INTO `t_core_mood` VALUES ('47', '2016-12-19 21:03:18', '2016-12-29 10:47:46', '18', '大家好，今天天气不错', 'Text');
INSERT INTO `t_core_mood` VALUES ('48', '2016-12-19 21:04:05', '2016-12-29 10:47:54', '18', '呜呜呜~今天要答辩~', 'photo');
INSERT INTO `t_core_mood` VALUES ('49', '2016-12-19 21:04:19', '2016-12-29 10:48:02', '18', '哈哈', 'Photo');
INSERT INTO `t_core_mood` VALUES ('50', '2016-12-19 22:37:40', '2016-12-19 22:37:40', '18', '我是admin', 'Text');
INSERT INTO `t_core_mood` VALUES ('51', '2016-12-19 23:55:48', '2016-12-19 23:55:48', '18', '今天天气不错~', 'Photo');
INSERT INTO `t_core_mood` VALUES ('52', '2016-12-19 23:57:08', '2016-12-19 23:57:08', '18', '挺好看啊', 'Video');
INSERT INTO `t_core_mood` VALUES ('53', '2016-12-20 09:09:59', '2016-12-20 09:09:59', '21', '今天心情很糟', 'Text');
INSERT INTO `t_core_mood` VALUES ('54', '2016-12-20 09:12:50', '2016-12-29 10:48:22', '21', '啊啊啊~~~~我要答辩~~~~', 'Photo');
INSERT INTO `t_core_mood` VALUES ('55', '2016-12-21 14:59:41', '2016-12-21 14:59:41', '18', '你好，组长！！！！', 'Text');
INSERT INTO `t_core_mood` VALUES ('56', '2016-12-27 18:05:47', '2016-12-29 10:48:12', '18', '浪里个浪！', 'Photo');
INSERT INTO `t_core_mood` VALUES ('65', '2016-12-29 10:48:45', '2016-12-29 10:48:45', '23', '(*^__^*) 嘻嘻……', 'Text');
INSERT INTO `t_core_mood` VALUES ('68', '2016-12-29 15:38:02', '2016-12-29 15:38:02', '23', '你好老师', 'Text');
INSERT INTO `t_core_mood` VALUES ('69', '2016-12-29 15:41:01', '2016-12-29 15:41:01', '23', '你好', 'Photo');

-- ----------------------------
-- Table structure for t_core_mood_file
-- ----------------------------
DROP TABLE IF EXISTS `t_core_mood_file`;
CREATE TABLE `t_core_mood_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `created_time` datetime DEFAULT NULL COMMENT '记录创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '记录更新时间',
  `mood_id` bigint(20) NOT NULL,
  `file_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `AK_unique_mood_file` (`mood_id`,`file_id`),
  KEY `FK_Reference_11` (`file_id`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`mood_id`) REFERENCES `t_core_mood` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`file_id`) REFERENCES `t_sys_file` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_core_mood_file
-- ----------------------------
INSERT INTO `t_core_mood_file` VALUES ('80', '2016-12-19 21:04:19', '2016-12-19 21:04:19', '49', '180');
INSERT INTO `t_core_mood_file` VALUES ('81', '2016-12-19 23:55:48', '2016-12-19 23:55:48', '51', '183');
INSERT INTO `t_core_mood_file` VALUES ('82', '2016-12-19 23:55:48', '2016-12-19 23:55:48', '51', '175');
INSERT INTO `t_core_mood_file` VALUES ('83', '2016-12-19 23:55:48', '2016-12-19 23:55:48', '51', '176');
INSERT INTO `t_core_mood_file` VALUES ('84', '2016-12-19 23:55:48', '2016-12-19 23:55:48', '51', '177');
INSERT INTO `t_core_mood_file` VALUES ('85', '2016-12-19 23:55:48', '2016-12-19 23:55:48', '51', '178');
INSERT INTO `t_core_mood_file` VALUES ('86', '2016-12-19 23:55:48', '2016-12-19 23:55:48', '51', '179');
INSERT INTO `t_core_mood_file` VALUES ('87', '2016-12-19 23:55:48', '2016-12-19 23:55:48', '51', '180');
INSERT INTO `t_core_mood_file` VALUES ('88', '2016-12-19 23:55:48', '2016-12-19 23:55:48', '51', '181');
INSERT INTO `t_core_mood_file` VALUES ('89', '2016-12-19 23:55:48', '2016-12-19 23:55:48', '51', '182');
INSERT INTO `t_core_mood_file` VALUES ('90', '2016-12-19 23:57:08', '2016-12-19 23:57:08', '52', '169');
INSERT INTO `t_core_mood_file` VALUES ('91', '2016-12-20 09:12:50', '2016-12-20 09:12:50', '54', '190');
INSERT INTO `t_core_mood_file` VALUES ('92', '2016-12-20 09:12:50', '2016-12-20 09:12:50', '54', '191');
INSERT INTO `t_core_mood_file` VALUES ('93', '2016-12-20 09:12:50', '2016-12-20 09:12:50', '54', '192');
INSERT INTO `t_core_mood_file` VALUES ('94', '2016-12-20 09:12:50', '2016-12-20 09:12:50', '54', '193');
INSERT INTO `t_core_mood_file` VALUES ('95', '2016-12-20 09:12:50', '2016-12-20 09:12:50', '54', '194');
INSERT INTO `t_core_mood_file` VALUES ('96', '2016-12-20 09:12:50', '2016-12-20 09:12:50', '54', '189');
INSERT INTO `t_core_mood_file` VALUES ('97', '2016-12-27 18:05:47', '2016-12-27 18:05:47', '56', '195');
INSERT INTO `t_core_mood_file` VALUES ('108', '2016-12-29 15:41:01', '2016-12-29 15:41:01', '69', '200');

-- ----------------------------
-- Table structure for t_core_photo
-- ----------------------------
DROP TABLE IF EXISTS `t_core_photo`;
CREATE TABLE `t_core_photo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `created_time` datetime DEFAULT NULL COMMENT '记录创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '记录更新时间',
  `album_id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `file_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_2` (`album_id`),
  KEY `FK_Reference_9` (`file_id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`album_id`) REFERENCES `t_core_album` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`file_id`) REFERENCES `t_sys_file` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=472 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_core_photo
-- ----------------------------
INSERT INTO `t_core_photo` VALUES ('360', '2016-12-19 21:03:47', '2016-12-21 15:06:13', '54', '12 (1).jpg', null, '175');
INSERT INTO `t_core_photo` VALUES ('361', '2016-12-19 21:03:47', '2016-12-21 15:08:24', '54', '21.jpg', null, '176');
INSERT INTO `t_core_photo` VALUES ('362', '2016-12-19 21:03:47', '2016-12-21 15:08:00', '54', '1.jpg', null, '177');
INSERT INTO `t_core_photo` VALUES ('363', '2016-12-19 21:03:48', '2016-12-19 21:03:48', '54', '1 (4).jpg', null, '178');
INSERT INTO `t_core_photo` VALUES ('364', '2016-12-19 21:03:48', '2016-12-19 21:03:48', '54', '1 (5).jpg', null, '179');
INSERT INTO `t_core_photo` VALUES ('365', '2016-12-19 21:03:48', '2016-12-19 21:03:48', '54', '1 (6).jpg', null, '180');
INSERT INTO `t_core_photo` VALUES ('366', '2016-12-19 21:03:49', '2016-12-19 21:03:49', '54', '1 (7).jpg', null, '181');
INSERT INTO `t_core_photo` VALUES ('367', '2016-12-19 21:03:49', '2016-12-19 21:03:49', '54', '1 (8).jpg', null, '182');
INSERT INTO `t_core_photo` VALUES ('368', '2016-12-19 21:03:49', '2016-12-19 21:03:49', '54', '3ce2d74.jpg', null, '184');
INSERT INTO `t_core_photo` VALUES ('369', '2016-12-19 21:03:49', '2016-12-19 21:03:49', '54', '34_950.png', null, '185');
INSERT INTO `t_core_photo` VALUES ('370', '2016-12-19 21:03:50', '2016-12-19 21:03:50', '54', 'HFnx.jpeg', null, '186');
INSERT INTO `t_core_photo` VALUES ('371', '2016-12-19 21:03:50', '2016-12-19 21:03:50', '54', 'Z0543764.jpg', null, '187');
INSERT INTO `t_core_photo` VALUES ('372', '2016-12-19 22:18:38', '2016-12-19 22:18:38', '55', '0_0.jpeg', null, '183');
INSERT INTO `t_core_photo` VALUES ('373', '2016-12-19 22:18:38', '2016-12-19 22:18:38', '55', '1 (1).jpg', null, '175');
INSERT INTO `t_core_photo` VALUES ('374', '2016-12-19 22:18:38', '2016-12-19 22:18:38', '55', '1 (2).jpg', null, '176');
INSERT INTO `t_core_photo` VALUES ('375', '2016-12-19 22:18:39', '2016-12-19 22:18:39', '55', '1 (3).jpg', null, '177');
INSERT INTO `t_core_photo` VALUES ('376', '2016-12-19 22:18:39', '2016-12-19 22:18:39', '55', '1 (4).jpg', null, '178');
INSERT INTO `t_core_photo` VALUES ('377', '2016-12-19 22:18:39', '2016-12-19 22:18:39', '55', '1 (5).jpg', null, '179');
INSERT INTO `t_core_photo` VALUES ('378', '2016-12-19 22:18:39', '2016-12-19 22:18:39', '55', '1 (6).jpg', null, '180');
INSERT INTO `t_core_photo` VALUES ('379', '2016-12-19 22:18:39', '2016-12-19 22:18:39', '55', '1 (7).jpg', null, '181');
INSERT INTO `t_core_photo` VALUES ('380', '2016-12-19 22:18:39', '2016-12-19 22:18:39', '55', '1 (8).jpg', null, '182');
INSERT INTO `t_core_photo` VALUES ('381', '2016-12-19 22:18:40', '2016-12-19 22:18:40', '55', '3ce2d74.jpg', null, '184');
INSERT INTO `t_core_photo` VALUES ('382', '2016-12-19 22:18:40', '2016-12-19 22:18:40', '55', '34_950.png', null, '185');
INSERT INTO `t_core_photo` VALUES ('383', '2016-12-19 22:18:40', '2016-12-19 22:18:40', '55', 'HFnx.jpeg', null, '186');
INSERT INTO `t_core_photo` VALUES ('384', '2016-12-19 22:18:40', '2016-12-19 22:18:40', '55', 'Z0543764.jpg', null, '187');
INSERT INTO `t_core_photo` VALUES ('385', '2016-12-21 15:10:16', '2016-12-21 15:10:16', '56', '编辑好友.jpg', null, '192');
INSERT INTO `t_core_photo` VALUES ('386', '2016-12-27 19:04:19', '2016-12-27 19:04:19', '57', '查看他人主页.jpg', null, '194');
INSERT INTO `t_core_photo` VALUES ('388', '2016-12-27 19:04:20', '2016-12-27 19:04:20', '57', '登录.JPG', null, '198');
INSERT INTO `t_core_photo` VALUES ('389', '2016-12-27 19:04:20', '2016-12-27 19:04:20', '57', '发布图文心情.jpg', null, '199');
INSERT INTO `t_core_photo` VALUES ('390', '2016-12-27 19:04:20', '2016-12-27 19:04:20', '57', '发布文本心情.jpg', null, '200');
INSERT INTO `t_core_photo` VALUES ('391', '2016-12-27 19:04:20', '2016-12-27 19:04:20', '57', '个人主页.jpg', null, '201');
INSERT INTO `t_core_photo` VALUES ('392', '2016-12-27 19:04:20', '2016-12-27 19:04:20', '57', '个人主页时间轴.jpg', null, '202');
INSERT INTO `t_core_photo` VALUES ('393', '2016-12-27 19:04:20', '2016-12-27 19:04:20', '57', '好友操作.jpg', null, '203');
INSERT INTO `t_core_photo` VALUES ('394', '2016-12-27 19:04:20', '2016-12-27 19:04:20', '57', '好友分组操作.jpg', null, '204');
INSERT INTO `t_core_photo` VALUES ('395', '2016-12-27 19:04:21', '2016-12-27 19:04:44', '57', '删除好友', null, '205');
INSERT INTO `t_core_photo` VALUES ('396', '2016-12-27 19:04:21', '2016-12-27 19:04:21', '57', '删除好友分组.jpg', null, '206');
INSERT INTO `t_core_photo` VALUES ('397', '2016-12-27 19:04:21', '2016-12-27 19:04:21', '57', '删除照片.jpg', null, '207');
INSERT INTO `t_core_photo` VALUES ('398', '2016-12-27 19:04:21', '2016-12-27 19:04:21', '57', '上传照片.jpg', null, '208');
INSERT INTO `t_core_photo` VALUES ('399', '2016-12-27 19:04:21', '2016-12-27 19:04:21', '57', '设置封面.jpg', null, '209');
INSERT INTO `t_core_photo` VALUES ('400', '2016-12-27 19:04:21', '2016-12-27 19:04:21', '57', '视频心情.jpg', null, '210');
INSERT INTO `t_core_photo` VALUES ('401', '2016-12-27 19:04:21', '2016-12-27 19:04:21', '57', '视频心情展示.jpg', null, '211');
INSERT INTO `t_core_photo` VALUES ('402', '2016-12-27 19:04:22', '2016-12-27 19:04:22', '57', '首页.jpg', null, '212');
INSERT INTO `t_core_photo` VALUES ('403', '2016-12-27 19:04:22', '2016-12-27 19:04:22', '57', '搜索用户.jpg', null, '213');
INSERT INTO `t_core_photo` VALUES ('404', '2016-12-27 19:04:22', '2016-12-27 19:04:22', '57', '搜索用户区分.jpg', null, '214');
INSERT INTO `t_core_photo` VALUES ('405', '2016-12-27 19:04:22', '2016-12-27 19:04:22', '57', '添加好友.jpg', null, '215');
INSERT INTO `t_core_photo` VALUES ('406', '2016-12-27 19:04:22', '2016-12-27 19:04:22', '57', '图文心情展示.jpg', null, '216');
INSERT INTO `t_core_photo` VALUES ('407', '2016-12-27 19:04:22', '2016-12-27 19:04:22', '57', '退出.jpg', null, '217');
INSERT INTO `t_core_photo` VALUES ('408', '2016-12-27 19:04:22', '2016-12-27 19:04:22', '57', '文本心情展示.jpg', null, '218');
INSERT INTO `t_core_photo` VALUES ('409', '2016-12-27 19:04:23', '2016-12-27 19:04:23', '57', '我的好友.jpg', null, '219');
INSERT INTO `t_core_photo` VALUES ('410', '2016-12-27 19:04:23', '2016-12-27 19:04:23', '57', '相册列表.jpg', null, '220');
INSERT INTO `t_core_photo` VALUES ('411', '2016-12-27 19:04:23', '2016-12-27 19:04:23', '57', '新建好友分组.jpg', null, '221');
INSERT INTO `t_core_photo` VALUES ('412', '2016-12-27 19:04:23', '2016-12-27 19:04:23', '57', '新建相册.jpg', null, '222');
INSERT INTO `t_core_photo` VALUES ('413', '2016-12-27 19:04:23', '2016-12-27 19:04:23', '57', '修改个人信息.jpg', null, '223');
INSERT INTO `t_core_photo` VALUES ('414', '2016-12-27 19:04:23', '2016-12-27 19:04:23', '57', '修改密码.jpg', null, '224');
INSERT INTO `t_core_photo` VALUES ('415', '2016-12-27 19:04:23', '2016-12-27 19:04:23', '57', '修改相册.jpg', null, '225');
INSERT INTO `t_core_photo` VALUES ('416', '2016-12-27 19:04:24', '2016-12-27 19:04:24', '57', '与我相关.jpg', null, '226');
INSERT INTO `t_core_photo` VALUES ('417', '2016-12-27 19:04:24', '2016-12-27 19:04:24', '57', '照片列表.jpg', null, '227');
INSERT INTO `t_core_photo` VALUES ('418', '2016-12-27 19:04:24', '2016-12-27 19:04:24', '57', '注册.JPG', null, '228');
INSERT INTO `t_core_photo` VALUES ('419', '2016-12-27 19:04:24', '2016-12-27 19:04:24', '57', '最近照片.jpg', null, '229');
INSERT INTO `t_core_photo` VALUES ('459', '2016-12-29 15:32:32', '2016-12-29 15:33:12', '61', '@他人和可删除自己的评论111.jpg', null, '190');
INSERT INTO `t_core_photo` VALUES ('460', '2016-12-29 15:32:32', '2016-12-29 15:33:26', '61', 'person-background.jpg', null, '191');
INSERT INTO `t_core_photo` VALUES ('461', '2016-12-29 15:32:32', '2016-12-29 15:32:32', '60', '编辑好友.jpg', null, '192');
INSERT INTO `t_core_photo` VALUES ('464', '2016-12-29 15:32:33', '2016-12-29 15:32:33', '60', '查看他人主页.jpg', null, '194');
INSERT INTO `t_core_photo` VALUES ('465', '2016-12-29 15:32:33', '2016-12-29 15:32:33', '60', '查看照片.jpg', null, '189');
INSERT INTO `t_core_photo` VALUES ('466', '2016-12-29 15:32:33', '2016-12-29 15:32:33', '60', '登录.JPG', null, '198');
INSERT INTO `t_core_photo` VALUES ('467', '2016-12-29 15:32:33', '2016-12-29 15:32:33', '60', '发布图文心情.jpg', null, '199');
INSERT INTO `t_core_photo` VALUES ('468', '2016-12-29 15:32:33', '2016-12-29 15:32:33', '60', '发布文本心情.jpg', null, '200');
INSERT INTO `t_core_photo` VALUES ('469', '2016-12-29 15:32:33', '2016-12-29 15:32:33', '60', '个人主页.jpg', null, '201');
INSERT INTO `t_core_photo` VALUES ('470', '2016-12-29 15:37:15', '2016-12-29 15:37:15', '60', '个人主页.jpg', null, '201');
INSERT INTO `t_core_photo` VALUES ('471', '2016-12-29 15:37:27', '2016-12-29 15:37:27', '60', '视频心情.jpg', null, '210');

-- ----------------------------
-- Table structure for t_sys_file
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_file`;
CREATE TABLE `t_sys_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `created_time` datetime DEFAULT NULL COMMENT '记录创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '记录更新时间',
  `filename` varchar(100) NOT NULL,
  `mime` varchar(100) NOT NULL,
  `uri` varchar(2000) NOT NULL,
  `md5` varchar(100) NOT NULL,
  `ref_count` int(11) NOT NULL,
  `size` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `AK_unique_md5` (`md5`)
) ENGINE=InnoDB AUTO_INCREMENT=232 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_file
-- ----------------------------
INSERT INTO `t_sys_file` VALUES ('153', '2016-12-18 21:40:25', '2016-12-19 08:38:03', 'a.gif', 'image/gif', 'TargetFileFolder/325472601571f31e1bf00674c368d335', '325472601571f31e1bf00674c368d335', '2', '43');
INSERT INTO `t_sys_file` VALUES ('154', '2016-12-18 21:40:25', '2016-12-19 08:38:03', 'album.png', 'image/png', 'TargetFileFolder/9c900e5edad5e9c0d2fbcf964235b8fe', '9c900e5edad5e9c0d2fbcf964235b8fe', '3', '3051');
INSERT INTO `t_sys_file` VALUES ('155', '2016-12-18 21:40:25', '2016-12-19 08:38:03', 'album_bg_single.png', 'image/png', 'TargetFileFolder/9d3dbdd4da716d661b7669cb36f45ef5', '9d3dbdd4da716d661b7669cb36f45ef5', '2', '728');
INSERT INTO `t_sys_file` VALUES ('156', '2016-12-18 21:40:26', '2016-12-19 08:38:03', 'album_bg_single_hover.png', 'image/png', 'TargetFileFolder/9f7f9daaa374ba50fe894a812c92f479', '9f7f9daaa374ba50fe894a812c92f479', '2', '890');
INSERT INTO `t_sys_file` VALUES ('157', '2016-12-18 21:40:26', '2016-12-19 08:38:03', 'album_list_sprites.png', 'image/png', 'TargetFileFolder/659990368e327a30c8951aa3dd50db63', '659990368e327a30c8951aa3dd50db63', '2', '2124');
INSERT INTO `t_sys_file` VALUES ('158', '2016-12-18 21:40:26', '2016-12-19 08:38:03', 'app-icons.png', 'image/png', 'TargetFileFolder/09018da7754153d4270d7cdab1591ed4', '09018da7754153d4270d7cdab1591ed4', '2', '9769');
INSERT INTO `t_sys_file` VALUES ('159', '2016-12-18 21:40:26', '2016-12-19 08:38:03', 'bg.png', 'image/png', 'TargetFileFolder/8ddad23ce6dcc70bf111b23ae022f10c', '8ddad23ce6dcc70bf111b23ae022f10c', '2', '2851');
INSERT INTO `t_sys_file` VALUES ('160', '2016-12-18 21:40:26', '2016-12-19 08:38:03', 'checkbox_checked.png', 'image/png', 'TargetFileFolder/7e95f2e48392799231a759e2289ae274', '7e95f2e48392799231a759e2289ae274', '2', '458');
INSERT INTO `t_sys_file` VALUES ('161', '2016-12-18 21:40:26', '2016-12-19 08:38:03', 'checkbox_uncheck.png', 'image/png', 'TargetFileFolder/06f96efb9f1c568b0a128ab590156bca', '06f96efb9f1c568b0a128ab590156bca', '2', '230');
INSERT INTO `t_sys_file` VALUES ('165', '2016-12-18 22:37:07', '2016-12-19 08:38:03', 'logo.png', 'image/png', 'TargetFileFolder/148d2058120ad801fe652ea9e363dd95', '148d2058120ad801fe652ea9e363dd95', '1', '11298');
INSERT INTO `t_sys_file` VALUES ('169', '2016-12-18 22:50:11', '2016-12-27 18:56:20', 'pointbreak.mp4', 'video/mp4', 'TargetFileFolder/2d275df65e969f3e23621a83bc23751a', '2d275df65e969f3e23621a83bc23751a', '3', '48570006');
INSERT INTO `t_sys_file` VALUES ('170', '2016-12-18 23:15:46', '2016-12-18 23:15:46', 'tip.png', 'image/png', 'TargetFileFolder/c4f3fa81e2bf5d7b4353ac26b351515a', 'c4f3fa81e2bf5d7b4353ac26b351515a', '1', '743');
INSERT INTO `t_sys_file` VALUES ('171', '2016-12-19 00:25:14', '2016-12-19 00:25:14', 'edit_remove.png', 'image/png', 'TargetFileFolder/f55bca7a5816edd468785ecb693abb1f', 'f55bca7a5816edd468785ecb693abb1f', '1', '625');
INSERT INTO `t_sys_file` VALUES ('172', '2016-12-19 00:32:39', '2016-12-19 00:32:39', 'filesave.png', 'image/png', 'TargetFileFolder/dbff1b14d085a13acad4a3d1869ff0c6', 'dbff1b14d085a13acad4a3d1869ff0c6', '1', '898');
INSERT INTO `t_sys_file` VALUES ('173', '2016-12-19 08:33:00', '2016-12-19 08:33:12', 'sy_80219211654.jpg', 'image/jpeg', 'TargetFileFolder/311955598e0e008f375fd202351d87a3', '311955598e0e008f375fd202351d87a3', '2', '97353');
INSERT INTO `t_sys_file` VALUES ('174', '2016-12-19 08:33:59', '2016-12-19 08:33:59', '捕获.JPG', 'image/jpeg', 'TargetFileFolder/9e22ac43b7582648e220a3029a9290ba', '9e22ac43b7582648e220a3029a9290ba', '1', '34427');
INSERT INTO `t_sys_file` VALUES ('175', '2016-12-19 08:36:13', '2016-12-19 23:55:38', '4ec2d5628535e5dd55d2464876c6a7efcf1b62af.jpg', 'image/jpeg', 'TargetFileFolder/80522e672d91137901a1acd311ab0bf1', '80522e672d91137901a1acd311ab0bf1', '9', '151923');
INSERT INTO `t_sys_file` VALUES ('176', '2016-12-19 08:36:13', '2016-12-19 23:55:39', '4ec2d5628535e5dd5576c6a7efcf1b62af.jpg', 'image/jpeg', 'TargetFileFolder/f9943a1b464ff61759c5385b8092aead', 'f9943a1b464ff61759c5385b8092aead', '9', '191020');
INSERT INTO `t_sys_file` VALUES ('177', '2016-12-19 08:36:13', '2016-12-19 23:55:39', '83246025_16.jpg', 'image/jpeg', 'TargetFileFolder/94b8bec0385f6111fea8624ff1624f62', '94b8bec0385f6111fea8624ff1624f62', '9', '236438');
INSERT INTO `t_sys_file` VALUES ('178', '2016-12-19 08:36:14', '2016-12-19 23:55:39', 'Cg-4y1ULoXCII6fEAAeQFx3v166.jpg', 'image/jpeg', 'TargetFileFolder/19e7fe57b5d58209bb1216b20f91c691', '19e7fe57b5d58209bb1216b20f91c691', '9', '96997');
INSERT INTO `t_sys_file` VALUES ('179', '2016-12-19 08:36:14', '2016-12-19 23:55:39', 'ChMkJ1bKx4qIGTZLAAmjEACaMo983.jpg', 'image/jpeg', 'TargetFileFolder/0a4d848d7539e0ba59604e1ac26c1cf2', '0a4d848d7539e0ba59604e1ac26c1cf2', '9', '168691');
INSERT INTO `t_sys_file` VALUES ('180', '2016-12-19 08:36:14', '2016-12-19 23:55:39', 'ChMkJ1bKxpWIIp3vAAimACKZJ164.jpg', 'image/jpeg', 'TargetFileFolder/0576f4b2cfaea3a7d609b1469f5f8685', '0576f4b2cfaea3a7d609b1469f5f8685', '11', '193709');
INSERT INTO `t_sys_file` VALUES ('181', '2016-12-19 08:36:14', '2016-12-19 23:55:39', 'ChMkJlbKwaOIN8zJAAs5Dad2Po5ngACzkl365.jpg', 'image/jpeg', 'TargetFileFolder/0aaecc5e0cc5218ea22d416d1e180970', '0aaecc5e0cc5218ea22d416d1e180970', '10', '159171');
INSERT INTO `t_sys_file` VALUES ('182', '2016-12-19 08:36:14', '2016-12-19 23:55:40', 'ChMkJlbKxmyIJBaWAAeJtnO760.jpg', 'image/jpeg', 'TargetFileFolder/91cc38e3705b3c06b8e0f6f0afd3a846', '91cc38e3705b3c06b8e0f6f0afd3a846', '10', '194450');
INSERT INTO `t_sys_file` VALUES ('183', '2016-12-19 08:44:52', '2016-12-19 23:55:38', '0_0.jpeg', 'image/jpeg', 'TargetFileFolder/aa11af6bbaede6aaa67e76201b719c53', 'aa11af6bbaede6aaa67e76201b719c53', '7', '147679');
INSERT INTO `t_sys_file` VALUES ('184', '2016-12-19 08:44:53', '2016-12-19 22:18:40', '3ce2d74.jpg', 'image/jpeg', 'TargetFileFolder/d286e5a1e77f688c0c5e66f59eff2b2f', 'd286e5a1e77f688c0c5e66f59eff2b2f', '4', '63714');
INSERT INTO `t_sys_file` VALUES ('185', '2016-12-19 08:44:53', '2016-12-19 22:18:40', '34_950.png', 'image/png', 'TargetFileFolder/a42f1207700277f6e5c95c7e03471fc6', 'a42f1207700277f6e5c95c7e03471fc6', '4', '573507');
INSERT INTO `t_sys_file` VALUES ('186', '2016-12-19 08:44:53', '2016-12-19 22:18:40', 'HFnx.jpeg', 'image/jpeg', 'TargetFileFolder/d693d2fb0733b5c5560215cabffee4d4', 'd693d2fb0733b5c5560215cabffee4d4', '4', '189413');
INSERT INTO `t_sys_file` VALUES ('187', '2016-12-19 08:44:53', '2016-12-19 22:18:40', 'Z0543764.jpg', 'image/jpeg', 'TargetFileFolder/5c153dea9a5778335fc7b768ef36ec1d', '5c153dea9a5778335fc7b768ef36ec1d', '4', '168002');
INSERT INTO `t_sys_file` VALUES ('188', '2016-12-19 10:09:59', '2016-12-19 10:26:40', 'company_logo.png', 'image/png', 'TargetFileFolder/f00abd088fd2a59c9b3bc73c8fa03547', 'f00abd088fd2a59c9b3bc73c8fa03547', '3', '48496');
INSERT INTO `t_sys_file` VALUES ('189', '2016-12-20 09:10:59', '2016-12-29 15:32:33', '查看照片.jpg', 'image/jpeg', 'TargetFileFolder/ff2050a63d62d83e3d65302de97217bb', 'ff2050a63d62d83e3d65302de97217bb', '4', '102192');
INSERT INTO `t_sys_file` VALUES ('190', '2016-12-20 09:12:49', '2016-12-29 15:32:32', '@他人和可删除自己的评论.jpg', 'image/jpeg', 'TargetFileFolder/bcaf4d414cb585d7cb7642b0ebfe9f4e', 'bcaf4d414cb585d7cb7642b0ebfe9f4e', '4', '12499');
INSERT INTO `t_sys_file` VALUES ('191', '2016-12-20 09:12:49', '2016-12-29 15:32:32', 'person-background.jpg', 'image/jpeg', 'TargetFileFolder/715b2f963fd862522282fa2481db43f5', '715b2f963fd862522282fa2481db43f5', '4', '44148');
INSERT INTO `t_sys_file` VALUES ('192', '2016-12-20 09:12:49', '2016-12-29 15:32:32', '编辑好友.jpg', 'image/jpeg', 'TargetFileFolder/1ccd82d472cbe966b1b8e269984bbec2', '1ccd82d472cbe966b1b8e269984bbec2', '4', '19435');
INSERT INTO `t_sys_file` VALUES ('193', '2016-12-20 09:12:49', '2016-12-29 15:34:58', '编辑照片.jpg', 'image/jpeg', 'TargetFileFolder/4d45d43f75d8b1832dbdc86310f0be12', '4d45d43f75d8b1832dbdc86310f0be12', '2', '24379');
INSERT INTO `t_sys_file` VALUES ('194', '2016-12-20 09:12:49', '2016-12-29 15:32:33', '查看他人主页.jpg', 'image/jpeg', 'TargetFileFolder/119acb2fdd3fd307ec6137dd9db32d40', '119acb2fdd3fd307ec6137dd9db32d40', '4', '56928');
INSERT INTO `t_sys_file` VALUES ('195', '2016-12-27 18:05:44', '2016-12-27 18:05:44', '运行监控.JPG', 'image/jpeg', 'TargetFileFolder/6f9d6780391f63b27c09befdc5004925', '6f9d6780391f63b27c09befdc5004925', '1', '53493');
INSERT INTO `t_sys_file` VALUES ('196', '2016-12-27 18:48:04', '2016-12-27 18:48:04', '重置密码.JPG', 'image/jpeg', 'TargetFileFolder/464d698ea2d6dbe423a3ee3ad8770e93', '464d698ea2d6dbe423a3ee3ad8770e93', '1', '30303');
INSERT INTO `t_sys_file` VALUES ('197', '2016-12-27 18:50:33', '2016-12-29 15:34:39', '编辑好友分组.jpg', 'image/jpeg', 'TargetFileFolder/0b440d45e4fe5c6e488c154b8de07025', '0b440d45e4fe5c6e488c154b8de07025', '2', '15424');
INSERT INTO `t_sys_file` VALUES ('198', '2016-12-27 18:50:34', '2016-12-29 15:32:33', '登录.JPG', 'image/jpeg', 'TargetFileFolder/474cca846d58584152a21faa3e87d623', '474cca846d58584152a21faa3e87d623', '4', '176519');
INSERT INTO `t_sys_file` VALUES ('199', '2016-12-27 18:50:34', '2016-12-29 15:32:33', '发布图文心情.jpg', 'image/jpeg', 'TargetFileFolder/24db110a6394a29b6d1e054d138e994b', '24db110a6394a29b6d1e054d138e994b', '4', '50980');
INSERT INTO `t_sys_file` VALUES ('200', '2016-12-27 19:04:20', '2016-12-29 15:40:55', '发布文本心情.jpg', 'image/jpeg', 'TargetFileFolder/75eb7e12541e4163d4e27ff0599d45aa', '75eb7e12541e4163d4e27ff0599d45aa', '4', '12075');
INSERT INTO `t_sys_file` VALUES ('201', '2016-12-27 19:04:20', '2016-12-29 15:37:15', '个人主页.jpg', 'image/jpeg', 'TargetFileFolder/bb489dcf4a61cc160d9d3bcfc1a0de2d', 'bb489dcf4a61cc160d9d3bcfc1a0de2d', '4', '77016');
INSERT INTO `t_sys_file` VALUES ('202', '2016-12-27 19:04:20', '2016-12-29 15:31:34', '个人主页时间轴.jpg', 'image/jpeg', 'TargetFileFolder/6ba63e75a952be4dd1bae27666ca4b6b', '6ba63e75a952be4dd1bae27666ca4b6b', '2', '99606');
INSERT INTO `t_sys_file` VALUES ('203', '2016-12-27 19:04:20', '2016-12-29 15:31:34', '好友操作.jpg', 'image/jpeg', 'TargetFileFolder/bddb3db99a12da95e847b2e219574775', 'bddb3db99a12da95e847b2e219574775', '1', '9651');
INSERT INTO `t_sys_file` VALUES ('204', '2016-12-27 19:04:20', '2016-12-29 15:31:34', '好友分组操作.jpg', 'image/jpeg', 'TargetFileFolder/2ac0b998824ae830e5a8be780edbdda7', '2ac0b998824ae830e5a8be780edbdda7', '1', '6472');
INSERT INTO `t_sys_file` VALUES ('205', '2016-12-27 19:04:21', '2016-12-29 15:31:34', '删除好友.jpg', 'image/jpeg', 'TargetFileFolder/ce051f80a355a634a95a993de5c4da79', 'ce051f80a355a634a95a993de5c4da79', '1', '15810');
INSERT INTO `t_sys_file` VALUES ('206', '2016-12-27 19:04:21', '2016-12-29 15:31:34', '删除好友分组.jpg', 'image/jpeg', 'TargetFileFolder/18da863d707a54662ac7a778a04c7a8e', '18da863d707a54662ac7a778a04c7a8e', '1', '15841');
INSERT INTO `t_sys_file` VALUES ('207', '2016-12-27 19:04:21', '2016-12-29 15:31:34', '删除照片.jpg', 'image/jpeg', 'TargetFileFolder/c371de4c1eccff6237a52d15481f9623', 'c371de4c1eccff6237a52d15481f9623', '1', '26027');
INSERT INTO `t_sys_file` VALUES ('208', '2016-12-27 19:04:21', '2016-12-29 15:31:34', '上传照片.jpg', 'image/jpeg', 'TargetFileFolder/897001fbf56be9cee6fb918999dfb088', '897001fbf56be9cee6fb918999dfb088', '1', '35325');
INSERT INTO `t_sys_file` VALUES ('209', '2016-12-27 19:04:21', '2016-12-29 15:31:34', '设置封面.jpg', 'image/jpeg', 'TargetFileFolder/bd04811c963927acbe6f982977a17cd1', 'bd04811c963927acbe6f982977a17cd1', '1', '29503');
INSERT INTO `t_sys_file` VALUES ('210', '2016-12-27 19:04:21', '2016-12-29 15:37:27', '视频心情.jpg', 'image/jpeg', 'TargetFileFolder/34698f20cbc5c4c3cfa83fb9722ffba6', '34698f20cbc5c4c3cfa83fb9722ffba6', '2', '22790');
INSERT INTO `t_sys_file` VALUES ('211', '2016-12-27 19:04:21', '2016-12-29 15:31:34', '视频心情展示.jpg', 'image/jpeg', 'TargetFileFolder/7c211b5865c48de311cddeaaed98ead0', '7c211b5865c48de311cddeaaed98ead0', '1', '29291');
INSERT INTO `t_sys_file` VALUES ('212', '2016-12-27 19:04:22', '2016-12-29 15:31:34', '首页.jpg', 'image/jpeg', 'TargetFileFolder/11c004f86e1779be9156e8697d087b8d', '11c004f86e1779be9156e8697d087b8d', '1', '89987');
INSERT INTO `t_sys_file` VALUES ('213', '2016-12-27 19:04:22', '2016-12-29 15:31:34', '搜索用户.jpg', 'image/jpeg', 'TargetFileFolder/a5a813953cfd4bbda33d326fd059612d', 'a5a813953cfd4bbda33d326fd059612d', '1', '19801');
INSERT INTO `t_sys_file` VALUES ('214', '2016-12-27 19:04:22', '2016-12-29 15:31:34', '搜索用户区分.jpg', 'image/jpeg', 'TargetFileFolder/b24c4908e40efb24fc113ba94b0f9d5e', 'b24c4908e40efb24fc113ba94b0f9d5e', '1', '13800');
INSERT INTO `t_sys_file` VALUES ('215', '2016-12-27 19:04:22', '2016-12-29 15:31:34', '添加好友.jpg', 'image/jpeg', 'TargetFileFolder/349551c02d10e815b7abaec4a1ceb9ed', '349551c02d10e815b7abaec4a1ceb9ed', '1', '25642');
INSERT INTO `t_sys_file` VALUES ('216', '2016-12-27 19:04:22', '2016-12-29 15:31:34', '图文心情展示.jpg', 'image/jpeg', 'TargetFileFolder/86e9d544fa184ead9782ea12875dd2b1', '86e9d544fa184ead9782ea12875dd2b1', '1', '61982');
INSERT INTO `t_sys_file` VALUES ('217', '2016-12-27 19:04:22', '2016-12-29 15:31:34', '退出.jpg', 'image/jpeg', 'TargetFileFolder/4f4a8413dbbd8764a1dba844434bd074', '4f4a8413dbbd8764a1dba844434bd074', '1', '80531');
INSERT INTO `t_sys_file` VALUES ('218', '2016-12-27 19:04:22', '2016-12-29 15:31:34', '文本心情展示.jpg', 'image/jpeg', 'TargetFileFolder/7561219d0012e5ccf0c9cd1527b2f819', '7561219d0012e5ccf0c9cd1527b2f819', '1', '14594');
INSERT INTO `t_sys_file` VALUES ('219', '2016-12-27 19:04:23', '2016-12-29 15:31:34', '我的好友.jpg', 'image/jpeg', 'TargetFileFolder/4fbc0c56745649d97169476b3f950e48', '4fbc0c56745649d97169476b3f950e48', '1', '55749');
INSERT INTO `t_sys_file` VALUES ('220', '2016-12-27 19:04:23', '2016-12-29 15:31:34', '相册列表.jpg', 'image/jpeg', 'TargetFileFolder/f4693995400d03ea11bebe7c9bb7a1f2', 'f4693995400d03ea11bebe7c9bb7a1f2', '1', '51387');
INSERT INTO `t_sys_file` VALUES ('221', '2016-12-27 19:04:23', '2016-12-29 15:31:34', '新建好友分组.jpg', 'image/jpeg', 'TargetFileFolder/a7c27467812665da7ab6388bc1ea51f7', 'a7c27467812665da7ab6388bc1ea51f7', '1', '17887');
INSERT INTO `t_sys_file` VALUES ('222', '2016-12-27 19:04:23', '2016-12-29 15:31:34', '新建相册.jpg', 'image/jpeg', 'TargetFileFolder/0041110a0e501ac9a5d621dd1a98b9ec', '0041110a0e501ac9a5d621dd1a98b9ec', '1', '24042');
INSERT INTO `t_sys_file` VALUES ('223', '2016-12-27 19:04:23', '2016-12-29 15:31:34', '修改个人信息.jpg', 'image/jpeg', 'TargetFileFolder/7028e6a061f6322d44821b3b27312779', '7028e6a061f6322d44821b3b27312779', '1', '104305');
INSERT INTO `t_sys_file` VALUES ('224', '2016-12-27 19:04:23', '2016-12-29 15:31:34', '修改密码.jpg', 'image/jpeg', 'TargetFileFolder/0fc772fbad60151be94ad8b17932ab5f', '0fc772fbad60151be94ad8b17932ab5f', '1', '17427');
INSERT INTO `t_sys_file` VALUES ('225', '2016-12-27 19:04:23', '2016-12-29 15:31:34', '修改相册.jpg', 'image/jpeg', 'TargetFileFolder/b89ee89ce6bedecc68bc755cab635963', 'b89ee89ce6bedecc68bc755cab635963', '1', '23788');
INSERT INTO `t_sys_file` VALUES ('226', '2016-12-27 19:04:24', '2016-12-29 15:31:34', '与我相关.jpg', 'image/jpeg', 'TargetFileFolder/e08c6ca68b5e489d0544cf959f4a4b82', 'e08c6ca68b5e489d0544cf959f4a4b82', '1', '75871');
INSERT INTO `t_sys_file` VALUES ('227', '2016-12-27 19:04:24', '2016-12-29 15:31:34', '照片列表.jpg', 'image/jpeg', 'TargetFileFolder/73f79005002120966e7886b6bec74015', '73f79005002120966e7886b6bec74015', '1', '112664');
INSERT INTO `t_sys_file` VALUES ('228', '2016-12-27 19:04:24', '2016-12-29 15:31:34', '注册.JPG', 'image/jpeg', 'TargetFileFolder/7f963ebe8a7b1b8c616976c07788a525', '7f963ebe8a7b1b8c616976c07788a525', '1', '112109');
INSERT INTO `t_sys_file` VALUES ('229', '2016-12-27 19:04:24', '2016-12-29 15:31:34', '最近照片.jpg', 'image/jpeg', 'TargetFileFolder/3daf61366e74639fc77e31010356ec6d', '3daf61366e74639fc77e31010356ec6d', '1', '119276');
INSERT INTO `t_sys_file` VALUES ('230', '2016-12-29 15:02:43', '2016-12-29 15:02:43', '002k80dlgy6E77hD2OG06&690.jpg', 'image/jpeg', 'TargetFileFolder/c86b45c3508d650c1dbe639f82c586fa', 'c86b45c3508d650c1dbe639f82c586fa', '1', '35218');
INSERT INTO `t_sys_file` VALUES ('231', '2016-12-29 15:26:03', '2016-12-29 15:26:03', '捕获.JPG', 'image/jpeg', 'TargetFileFolder/027aa47663d671827dd86336ef9fb738', '027aa47663d671827dd86336ef9fb738', '1', '75439');

-- ----------------------------
-- View structure for v_core_album
-- ----------------------------
DROP VIEW IF EXISTS `v_core_album`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER  VIEW `v_core_album` AS select a.*,f.uri from t_core_album a left join t_core_photo p on a.cover_photo_id = p.id left join t_sys_file f on p.file_id = f.id ;

-- ----------------------------
-- View structure for v_core_photo
-- ----------------------------
DROP VIEW IF EXISTS `v_core_photo`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `v_core_photo` AS select p.*,f.uri,f.filename,f.md5,f.mime,f.ref_count,f.size from t_core_photo p join t_sys_file f on p.file_id = f.id ;

-- ----------------------------
-- View structure for v_core_recently_photo
-- ----------------------------
DROP VIEW IF EXISTS `v_core_recently_photo`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `v_core_recently_photo` AS select a.account_id,p.* from t_core_album a join t_core_photo p on a.id=p.album_id ;
DROP TRIGGER IF EXISTS `CoreAccountCreatedTimeTrigger`;
DELIMITER ;;
CREATE TRIGGER `CoreAccountCreatedTimeTrigger` BEFORE INSERT ON `t_core_account` FOR EACH ROW begin
    declare nowTime datetime;
    set nowTime = now();
    set new.created_time = nowTime;
    set new.updated_time = nowTime;
    
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `CoreAccountUpdatedTimeTrigger`;
DELIMITER ;;
CREATE TRIGGER `CoreAccountUpdatedTimeTrigger` BEFORE UPDATE ON `t_core_account` FOR EACH ROW begin
    DECLARE msg varchar(200);
    
    if old.created_time != new.created_time
    then 
        set msg = '禁止修改创建时间';
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
    end if;
       
    set new.updated_time=now();
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `CoreAlbumCreatedTimeTrigger`;
DELIMITER ;;
CREATE TRIGGER `CoreAlbumCreatedTimeTrigger` BEFORE INSERT ON `t_core_album` FOR EACH ROW begin
    declare nowTime datetime;
    set nowTime = now();
    set new.created_time = nowTime;
    set new.updated_time = nowTime;
    
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `CoreAlbumUpdatedTimeTrigger`;
DELIMITER ;;
CREATE TRIGGER `CoreAlbumUpdatedTimeTrigger` BEFORE UPDATE ON `t_core_album` FOR EACH ROW begin
    DECLARE msg varchar(200);
    
    if old.created_time != new.created_time
    then 
        set msg = '禁止修改创建时间';
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
    end if;
       
    set new.updated_time=now();
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `CoreCommentCreatedTimeTrigger`;
DELIMITER ;;
CREATE TRIGGER `CoreCommentCreatedTimeTrigger` BEFORE INSERT ON `t_core_comment` FOR EACH ROW begin
    declare nowTime datetime;
    set nowTime = now();
    set new.created_time = nowTime;
    set new.updated_time = nowTime;
    
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `CoreCommentUpdatedTimeTrigger`;
DELIMITER ;;
CREATE TRIGGER `CoreCommentUpdatedTimeTrigger` BEFORE UPDATE ON `t_core_comment` FOR EACH ROW begin
    DECLARE msg varchar(200);
    
    if old.created_time != new.created_time
    then 
        set msg = '禁止修改创建时间';
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
    end if;
       
    set new.updated_time=now();
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `CoreFriendCreatedTimeTrigger`;
DELIMITER ;;
CREATE TRIGGER `CoreFriendCreatedTimeTrigger` BEFORE INSERT ON `t_core_friend` FOR EACH ROW begin
    declare nowTime datetime;
    set nowTime = now();
    set new.created_time = nowTime;
    set new.updated_time = nowTime;
    
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `CoreFriendUpdatedTimeTrigger`;
DELIMITER ;;
CREATE TRIGGER `CoreFriendUpdatedTimeTrigger` BEFORE UPDATE ON `t_core_friend` FOR EACH ROW begin
    DECLARE msg varchar(200);
    
    if old.created_time != new.created_time
    then 
        set msg = '禁止修改创建时间';
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
    end if;
       
    set new.updated_time=now();
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `CoreFriendGroupCreatedTimeTrigger`;
DELIMITER ;;
CREATE TRIGGER `CoreFriendGroupCreatedTimeTrigger` BEFORE INSERT ON `t_core_friend_group` FOR EACH ROW begin
    declare nowTime datetime;
    set nowTime = now();
    set new.created_time = nowTime;
    set new.updated_time = nowTime;
    
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `CoreFriendGroupUpdatedTimeTrigger`;
DELIMITER ;;
CREATE TRIGGER `CoreFriendGroupUpdatedTimeTrigger` BEFORE UPDATE ON `t_core_friend_group` FOR EACH ROW begin
    DECLARE msg varchar(200);
    
    if old.created_time != new.created_time
    then 
        set msg = '禁止修改创建时间';
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
    end if;
       
    set new.updated_time=now();
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `CoreMoodCreatedTimeTrigger`;
DELIMITER ;;
CREATE TRIGGER `CoreMoodCreatedTimeTrigger` BEFORE INSERT ON `t_core_mood` FOR EACH ROW begin
    declare nowTime datetime;
    set nowTime = now();
    set new.created_time = nowTime;
    set new.updated_time = nowTime;
    
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `CoreMoodUpdatedTimeTrigger`;
DELIMITER ;;
CREATE TRIGGER `CoreMoodUpdatedTimeTrigger` BEFORE UPDATE ON `t_core_mood` FOR EACH ROW begin
    DECLARE msg varchar(200);
    
    if old.created_time != new.created_time
    then 
        set msg = '禁止修改创建时间';
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
    end if;
       
    set new.updated_time=now();
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `MoodFileCreatedTimeTrigger`;
DELIMITER ;;
CREATE TRIGGER `MoodFileCreatedTimeTrigger` BEFORE INSERT ON `t_core_mood_file` FOR EACH ROW begin
    declare nowTime datetime;
    set nowTime = now();
    set new.created_time = nowTime;
    set new.updated_time = nowTime;
    
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `MoodFileUpdatedTimeTrigger`;
DELIMITER ;;
CREATE TRIGGER `MoodFileUpdatedTimeTrigger` BEFORE UPDATE ON `t_core_mood_file` FOR EACH ROW begin
    DECLARE msg varchar(200);
    
    if old.created_time != new.created_time
    then 
        set msg = '禁止修改创建时间';
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
    end if;
       
    set new.updated_time=now();
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `CorePhotoCreatedTimeTrigger`;
DELIMITER ;;
CREATE TRIGGER `CorePhotoCreatedTimeTrigger` BEFORE INSERT ON `t_core_photo` FOR EACH ROW begin
    declare nowTime datetime;
    set nowTime = now();
    set new.created_time = nowTime;
    set new.updated_time = nowTime;
    
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `CorePhotoUpdatedTimeTrigger`;
DELIMITER ;;
CREATE TRIGGER `CorePhotoUpdatedTimeTrigger` BEFORE UPDATE ON `t_core_photo` FOR EACH ROW begin
    DECLARE msg varchar(200);
    
    if old.created_time != new.created_time
    then 
        set msg = '禁止修改创建时间';
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
    end if;
       
    set new.updated_time=now();
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `SysFileCreatedTimeTrigger`;
DELIMITER ;;
CREATE TRIGGER `SysFileCreatedTimeTrigger` BEFORE INSERT ON `t_sys_file` FOR EACH ROW begin
    declare nowTime datetime;
    set nowTime = now();
    set new.created_time = nowTime;
    set new.updated_time = nowTime;
    
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `SysFileUpdatedTimeTrigger`;
DELIMITER ;;
CREATE TRIGGER `SysFileUpdatedTimeTrigger` BEFORE UPDATE ON `t_sys_file` FOR EACH ROW begin
    DECLARE msg varchar(200);
    
    if old.created_time != new.created_time
    then 
        set msg = '禁止修改创建时间';
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
    end if;
       
    set new.updated_time=now();
end
;;
DELIMITER ;
