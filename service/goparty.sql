-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               5.5.34-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for openfire
DROP DATABASE IF EXISTS `openfire`;
CREATE DATABASE IF NOT EXISTS `openfire` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `openfire`;


-- Dumping structure for table openfire.gp_event
DROP TABLE IF EXISTS `gp_event`;
CREATE TABLE IF NOT EXISTS `gp_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `endTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `startTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `location` varchar(255) DEFAULT NULL,
  `eventStatus` tinyint(4) NOT NULL,
  `visibility` tinyint(4) NOT NULL,
  `locationShareable` tinyint(1) NOT NULL,
  `cateId` tinyint(4) NOT NULL,
  `ownerId` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.gp_event: ~3 rows (approximately)
DELETE FROM `gp_event`;
/*!40000 ALTER TABLE `gp_event` DISABLE KEYS */;
INSERT INTO `gp_event` (`id`, `title`, `description`, `endTime`, `startTime`, `location`, `eventStatus`, `visibility`, `locationShareable`, `cateId`, `ownerId`) VALUES
	(1, 'hello PUT', 'test', '2014-03-13 09:53:17', '2014-03-13 09:53:17', 'Shenzhen', 0, 0, 0, 1, 33),
	(2, 'hello PUT', 'test', '2014-03-21 16:22:33', '2014-03-21 16:22:33', 'Shenzhen', 0, 0, 0, 1, 33),
	(3, 'hello PUT', 'test', '2014-03-21 17:01:04', '2014-03-21 17:01:04', 'Shenzhen', 0, 0, 0, 1, 33);
/*!40000 ALTER TABLE `gp_event` ENABLE KEYS */;


-- Dumping structure for table openfire.gp_event_attendee
DROP TABLE IF EXISTS `gp_event_attendee`;
CREATE TABLE IF NOT EXISTS `gp_event_attendee` (
  `eventId` int(10) NOT NULL,
  `userId` int(10) NOT NULL,
  `status` char(5) DEFAULT 'INIT' COMMENT 'INIT  AGREE REJECT',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`eventId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='attendees';

-- Dumping data for table openfire.gp_event_attendee: ~32 rows (approximately)
DELETE FROM `gp_event_attendee`;
/*!40000 ALTER TABLE `gp_event_attendee` DISABLE KEYS */;
INSERT INTO `gp_event_attendee` (`eventId`, `userId`, `status`, `updateTime`) VALUES
	(1, 18, 'INIT', '2014-03-13 09:53:55'),
	(1, 19, 'INIT', '2014-03-13 09:53:56'),
	(2, 18, 'INIT', '2014-03-21 16:22:40'),
	(2, 19, 'INIT', '2014-03-21 16:22:40'),
	(3, 18, 'INIT', '2014-03-21 17:01:11'),
	(3, 19, 'INIT', '2014-03-21 17:01:11'),
	(27, 21, 'INIT', NULL),
	(27, 22, 'INIT', NULL),
	(27, 244, 'INIT', NULL),
	(30, 21, 'INIT', NULL),
	(30, 22, 'INIT', NULL),
	(30, 244, 'INIT', NULL),
	(31, 21, 'INIT', NULL),
	(31, 22, 'INIT', NULL),
	(31, 244, 'INIT', NULL),
	(32, 21, 'INIT', NULL),
	(32, 22, 'INIT', NULL),
	(32, 244, 'INIT', NULL),
	(33, 21, 'INIT', NULL),
	(33, 22, 'INIT', NULL),
	(33, 244, 'INIT', NULL),
	(34, 21, 'INIT', NULL),
	(34, 22, 'INIT', NULL),
	(34, 244, 'INIT', NULL),
	(41, 21, 'INIT', NULL),
	(49, 21, 'INIT', NULL),
	(50, 21, 'INIT', NULL),
	(50, 88, 'INIT', NULL),
	(51, 21, 'INIT', NULL),
	(51, 88, 'INIT', NULL),
	(52, 21, 'INIT', '2014-02-27 07:50:21'),
	(52, 88, 'INIT', '2014-02-27 07:50:21');
/*!40000 ALTER TABLE `gp_event_attendee` ENABLE KEYS */;


-- Dumping structure for table openfire.gp_event_category
DROP TABLE IF EXISTS `gp_event_category`;
CREATE TABLE IF NOT EXISTS `gp_event_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.gp_event_category: ~4 rows (approximately)
DELETE FROM `gp_event_category`;
/*!40000 ALTER TABLE `gp_event_category` DISABLE KEYS */;
INSERT INTO `gp_event_category` (`id`, `name`) VALUES
	(1, 'KTV '),
	(2, '中文'),
	(3, '回成 '),
	(4, '吃饭');
/*!40000 ALTER TABLE `gp_event_category` ENABLE KEYS */;


-- Dumping structure for table openfire.gp_event_comment
DROP TABLE IF EXISTS `gp_event_comment`;
CREATE TABLE IF NOT EXISTS `gp_event_comment` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `publishTime` datetime DEFAULT NULL,
  `eventId` int(11) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- Dumping data for table openfire.gp_event_comment: ~3 rows (approximately)
DELETE FROM `gp_event_comment`;
/*!40000 ALTER TABLE `gp_event_comment` DISABLE KEYS */;
INSERT INTO `gp_event_comment` (`id`, `content`, `userId`, `publishTime`, `eventId`, `parentId`) VALUES
	(9, 'funk you ', 33, '2014-03-18 09:26:52', 12, NULL),
	(10, 'funk you ', 33, '2014-03-18 09:27:23', 12, NULL),
	(11, 'love you too!', 33, '2014-03-18 09:37:07', 12, NULL);
/*!40000 ALTER TABLE `gp_event_comment` ENABLE KEYS */;


-- Dumping structure for table openfire.gp_event_message
DROP TABLE IF EXISTS `gp_event_message`;
CREATE TABLE IF NOT EXISTS `gp_event_message` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL COMMENT '1-system message 2 - user message',
  `userId` int(11) DEFAULT NULL,
  `publishTime` datetime DEFAULT NULL,
  `eventId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.gp_event_message: ~7 rows (approximately)
DELETE FROM `gp_event_message`;
/*!40000 ALTER TABLE `gp_event_message` DISABLE KEYS */;
INSERT INTO `gp_event_message` (`id`, `content`, `type`, `userId`, `publishTime`, `eventId`) VALUES
	(1, 'update message', 0, 33, '2014-03-10 06:58:32', 12),
	(3, 'funk you ', 1, 33, '2014-03-10 07:01:21', 12),
	(4, 'funk you ', 0, 33, '2014-03-10 09:00:17', 12),
	(5, 'funk you ', 0, 33, '2014-03-10 09:02:23', 12),
	(6, 'love you!', 0, 33, '2014-03-11 02:19:05', 12),
	(7, 'love you!', 0, 33, '2014-03-11 03:08:16', 12),
	(8, 'love you too!', 0, 33, '2014-03-14 02:02:39', 12);
/*!40000 ALTER TABLE `gp_event_message` ENABLE KEYS */;


-- Dumping structure for table openfire.gp_group
DROP TABLE IF EXISTS `gp_group`;
CREATE TABLE IF NOT EXISTS `gp_group` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `ownerId` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.gp_group: ~15 rows (approximately)
DELETE FROM `gp_group`;
/*!40000 ALTER TABLE `gp_group` DISABLE KEYS */;
INSERT INTO `gp_group` (`id`, `name`, `ownerId`) VALUES
	(2, 'Boys', 33),
	(3, 'Girls', 33),
	(5, '3', 33),
	(9, 'private friend ', 33),
	(10, 'private friend ', 33),
	(11, 'private friend ', 33),
	(12, '', 33),
	(13, '', 33),
	(14, '', 33),
	(15, 'private girl friend ', 33),
	(16, 'private girl friend ', 33),
	(17, 'private friend ', 33),
	(18, ' ', 33),
	(19, ' girl friend 22', 33),
	(24, 'bb', 33);
/*!40000 ALTER TABLE `gp_group` ENABLE KEYS */;


-- Dumping structure for table openfire.gp_group_user
DROP TABLE IF EXISTS `gp_group_user`;
CREATE TABLE IF NOT EXISTS `gp_group_user` (
  `groupId` int(10) NOT NULL,
  `userId` int(10) NOT NULL,
  PRIMARY KEY (`groupId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.gp_group_user: ~6 rows (approximately)
DELETE FROM `gp_group_user`;
/*!40000 ALTER TABLE `gp_group_user` DISABLE KEYS */;
INSERT INTO `gp_group_user` (`groupId`, `userId`) VALUES
	(2, 92),
	(2, 93),
	(2, 95),
	(3, 92),
	(3, 96),
	(3, 97);
/*!40000 ALTER TABLE `gp_group_user` ENABLE KEYS */;


-- Dumping structure for table openfire.gp_user
DROP TABLE IF EXISTS `gp_user`;
CREATE TABLE IF NOT EXISTS `gp_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginId` varchar(255) DEFAULT NULL,
  `nickName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `birthdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `gender` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `weChat` varchar(255) DEFAULT NULL,
  `weibo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `loginId` (`loginId`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.gp_user: ~40 rows (approximately)
DELETE FROM `gp_user`;
/*!40000 ALTER TABLE `gp_user` DISABLE KEYS */;
INSERT INTO `gp_user` (`id`, `loginId`, `nickName`, `password`, `qq`, `birthdate`, `gender`, `location`, `phone`, `photo`, `signature`, `weChat`, `weibo`) VALUES
	(12, 'att212', 'att2', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(17, 'user1717', 'ahu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(18, 'user1818', 'ahu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(19, 'user1919', 'ahu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(20, 'user2020', 'ahu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(21, 'user2121', 'ahu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(22, 'user5422', 'f11', 'f1password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(33, 'chenb33', 'Chen, Bo', 'password', '1343243', '2014-03-22 08:54:47', 'M', '深圳', '13798594856', 'http://www.baidu.com/img/bdlogo.gif', 'life is good', 'wechat', '@weibo'),
	(34, 'user3534', 'f2', 'f2password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(35, 'chenb35', 'Bo', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(53, 'user5353', 'ahuuu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(55, 'user5555', 'f22', 'f2password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(56, 'user5656', 'ahuuu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(57, 'user5757', 'f11', 'f1password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(58, 'user5858', 'f22', 'f2password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(59, 'user5959', 'ahuuu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(60, 'user6060', 'f11', 'f1password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(61, 'user6161', 'f22', 'f2password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(62, 'user6262', 'ahuuu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(63, 'user6363', 'f1122', 'f1password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(64, 'user6464', 'ahuuu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(65, 'chenb65', 'Bo', 'password', '1343243', '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(66, 'chenb66', 'Bo', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(68, 'chenb68', 'Bo', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(72, 'ahu72', 'ahuuu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(73, 'ahu73', 'ahuuu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(76, 'ahu76', 'ahuuu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(83, 'ahu83', 'ahuuu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(84, 'ahu84', 'ahuuu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(88, 'ahu88', 'ahuuu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(89, 'chengc01789', 'chengc017', 'chengc017@', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(90, 'ahu90', 'ahuuu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(91, 'ahu91', 'ahuuu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(92, 'ahu92', 'ahuuu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(93, 'ahu93', 'ahuuu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(94, 'yrdy4494', 'ahuuu', NULL, NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(95, 'ahub95', 'ahu-new', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(96, 'openId--96', 'tokenId--', NULL, NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(97, 'openId--97', 'tokenId--', NULL, NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(98, 'openId--', 'tokenId--', NULL, '329985729', '2014-03-03 06:42:01', NULL, '冈到了', '137985945', NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `gp_user` ENABLE KEYS */;


-- Dumping structure for table openfire.gp_user_friend
DROP TABLE IF EXISTS `gp_user_friend`;
CREATE TABLE IF NOT EXISTS `gp_user_friend` (
  `userId` int(10) NOT NULL,
  `friendId` int(10) NOT NULL,
  `status` char(5) DEFAULT 'INIT' COMMENT 'init  agree reject',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `remarkName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userId`,`friendId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.gp_user_friend: ~29 rows (approximately)
DELETE FROM `gp_user_friend`;
/*!40000 ALTER TABLE `gp_user_friend` DISABLE KEYS */;
INSERT INTO `gp_user_friend` (`userId`, `friendId`, `status`, `updateTime`, `remarkName`) VALUES
	(22, 99, NULL, NULL, NULL),
	(33, 34, 'INIT', '2014-03-03 07:19:01', 'yrtytry'),
	(33, 35, 'AGREE', '2014-03-05 09:44:43', 'ss'),
	(33, 90, NULL, NULL, NULL),
	(33, 92, 'AGREE', '2014-03-04 08:55:40', 'Jim'),
	(33, 93, 'init', '2014-03-04 09:27:11', 'new Remark'),
	(33, 95, 'INIT', '2014-03-10 09:23:22', 'new Remark'),
	(33, 96, 'INIT', '2014-03-04 09:39:22', NULL),
	(33, 97, 'INIT', '2014-03-04 09:51:53', NULL),
	(47, 48, NULL, NULL, NULL),
	(49, 50, NULL, NULL, NULL),
	(53, 54, NULL, NULL, NULL),
	(53, 55, NULL, NULL, NULL),
	(56, 57, NULL, NULL, NULL),
	(56, 58, NULL, NULL, NULL),
	(59, 60, NULL, NULL, NULL),
	(59, 61, NULL, NULL, NULL),
	(62, 63, NULL, NULL, NULL),
	(62, 64, NULL, NULL, NULL),
	(72, 12, NULL, NULL, NULL),
	(76, 33, NULL, NULL, NULL),
	(83, 33, NULL, NULL, NULL),
	(84, 33, NULL, NULL, NULL),
	(88, 33, NULL, NULL, NULL),
	(90, 33, 'INIT', '2014-02-27 07:42:38', NULL),
	(91, 33, 'INIT', '2014-02-27 09:59:16', NULL),
	(92, 33, 'INIT', '2014-02-28 08:41:05', NULL),
	(93, 33, 'INIT', '2014-02-28 08:56:51', NULL),
	(95, 33, 'INIT', '2014-03-03 06:33:04', NULL);
/*!40000 ALTER TABLE `gp_user_friend` ENABLE KEYS */;


-- Dumping structure for table openfire.gp_user_token
DROP TABLE IF EXISTS `gp_user_token`;
CREATE TABLE IF NOT EXISTS `gp_user_token` (
  `userId` int(10) NOT NULL,
  `token` varchar(50) DEFAULT NULL,
  `applyTime` datetime DEFAULT NULL,
  `expireTime` datetime DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.gp_user_token: ~4 rows (approximately)
DELETE FROM `gp_user_token`;
/*!40000 ALTER TABLE `gp_user_token` DISABLE KEYS */;
INSERT INTO `gp_user_token` (`userId`, `token`, `applyTime`, `expireTime`) VALUES
	(33, '5397efef-01ef-4d4b-aef8-260508af81bf', '2014-03-03 07:20:23', '2014-03-02 07:20:23'),
	(96, '98326219-4388-4692-a93f-f18b2c7a275e', '2014-03-03 06:35:50', '2014-04-02 06:35:50'),
	(97, '477bfabb-edfb-4d97-9048-3a550d0c845d', '2014-03-03 06:37:09', '2014-04-02 06:37:09'),
	(98, '4e8bb1e4-4fab-4c4e-9a9f-cf5ece4cc2aa', '2014-03-03 06:48:40', '2014-04-02 06:48:40');
/*!40000 ALTER TABLE `gp_user_token` ENABLE KEYS */;


-- Dumping structure for table openfire.oauth_access_token
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE IF NOT EXISTS `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(256) DEFAULT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.oauth_access_token: ~1 rows (approximately)
DELETE FROM `oauth_access_token`;
/*!40000 ALTER TABLE `oauth_access_token` DISABLE KEYS */;
INSERT INTO `oauth_access_token` (`token_id`, `token`, `authentication_id`, `user_name`, `client_id`, `authentication`, `refresh_token`) VALUES
	('b21acb72553cb15c2376fd8027498f0a', _binary 0xACED0005737200436F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E636F6D6D6F6E2E44656661756C744F4175746832416363657373546F6B656E0CB29E361B24FACE0200064C00156164646974696F6E616C496E666F726D6174696F6E74000F4C6A6176612F7574696C2F4D61703B4C000A65787069726174696F6E7400104C6A6176612F7574696C2F446174653B4C000C72656672657368546F6B656E74003F4C6F72672F737072696E676672616D65776F726B2F73656375726974792F6F61757468322F636F6D6D6F6E2F4F417574683252656672657368546F6B656E3B4C000573636F706574000F4C6A6176612F7574696C2F5365743B4C0009746F6B656E547970657400124C6A6176612F6C616E672F537472696E673B4C000576616C756571007E000578707372001E6A6176612E7574696C2E436F6C6C656374696F6E7324456D7074794D6170593614855ADCE7D002000078707372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000144EA872D90787372004C6F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E636F6D6D6F6E2E44656661756C744578706972696E674F417574683252656672657368546F6B656E2FDF47639DD0C9B70200014C000A65787069726174696F6E71007E0002787200446F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E636F6D6D6F6E2E44656661756C744F417574683252656672657368546F6B656E73E10E0A6354D45E0200014C000576616C756571007E0005787074002435316230343739662D366661652D346333612D613937622D6663346364356238303539657371007E00097708000001458272C79078737200256A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C65536574801D92D18F9B80550200007872002C6A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C65436F6C6C656374696F6E19420080CB5EF71E0200014C0001637400164C6A6176612F7574696C2F436F6C6C656374696F6E3B7870737200176A6176612E7574696C2E4C696E6B656448617368536574D86CD75A95DD2A1E020000787200116A6176612E7574696C2E48617368536574BA44859596B8B7340300007870770C000000103F400000000000027400047265616474000577726974657874000662656172657274002432383962326364312D383932382D346138322D393835622D623233376565333464616336, '7f5db75d92833a30f10720aa5ef1a2e7', 'admin', 'unity-client', _binary 0xACED0005737200416F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E70726F76696465722E4F417574683241757468656E7469636174696F6EBD400B02166252130200024C0014636C69656E7441757468656E7469636174696F6E7400434C6F72672F737072696E676672616D65776F726B2F73656375726974792F6F61757468322F70726F76696465722F417574686F72697A6174696F6E526571756573743B4C00127573657241757468656E7469636174696F6E7400324C6F72672F737072696E676672616D65776F726B2F73656375726974792F636F72652F41757468656E7469636174696F6E3B787200476F72672E737072696E676672616D65776F726B2E73656375726974792E61757468656E7469636174696F6E2E416273747261637441757468656E7469636174696F6E546F6B656ED3AA287E6E47640E0200035A000D61757468656E746963617465644C000B617574686F7269746965737400164C6A6176612F7574696C2F436F6C6C656374696F6E3B4C000764657461696C737400124C6A6176612F6C616E672F4F626A6563743B787000737200266A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C654C697374FC0F2531B5EC8E100200014C00046C6973747400104C6A6176612F7574696C2F4C6973743B7872002C6A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C65436F6C6C656374696F6E19420080CB5EF71E0200014C00016371007E00047870737200136A6176612E7574696C2E41727261794C6973747881D21D99C7619D03000149000473697A65787000000003770400000003737200426F72672E737072696E676672616D65776F726B2E73656375726974792E636F72652E617574686F726974792E53696D706C654772616E746564417574686F7269747900000000000001360200014C0004726F6C657400124C6A6176612F6C616E672F537472696E673B7870740009524F4C455F555345527371007E000D74000A524F4C455F554E4954597371007E000D74000B524F4C455F4D4F42494C457871007E000C70737200486F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E70726F76696465722E44656661756C74417574686F72697A6174696F6E52657175657374DD2F6C1608186D180200075A0008617070726F7665644C0012617070726F76616C506172616D657465727374000F4C6A6176612F7574696C2F4D61703B4C000B617574686F72697469657371007E00044C0017617574686F72697A6174696F6E506172616D657465727371007E00164C00137265736F6C766564526564697265637455726971007E000E4C000B7265736F7572636549647374000F4C6A6176612F7574696C2F5365743B4C000573636F706571007E0017787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F400000000000107708000000100000000078737200116A6176612E7574696C2E48617368536574BA44859596B8B7340300007870770C000000103F400000000000017371007E000D74000A524F4C455F554E49545978737200266A6176612E7574696C2E636F6E63757272656E742E436F6E63757272656E74486173684D61706499DE129D87293D03000349000B7365676D656E744D61736B49000C7365676D656E7453686966745B00087365676D656E74737400315B4C6A6176612F7574696C2F636F6E63757272656E742F436F6E63757272656E74486173684D6170245365676D656E743B78700000000F0000001C757200315B4C6A6176612E7574696C2E636F6E63757272656E742E436F6E63757272656E74486173684D6170245365676D656E743B52773F41329B39740200007870000000107372002E6A6176612E7574696C2E636F6E63757272656E742E436F6E63757272656E74486173684D6170245365676D656E741F364C905893293D02000146000A6C6F6164466163746F72787200286A6176612E7574696C2E636F6E63757272656E742E6C6F636B732E5265656E7472616E744C6F636B6655A82C2CC86AEB0200014C000473796E6374002F4C6A6176612F7574696C2F636F6E63757272656E742F6C6F636B732F5265656E7472616E744C6F636B2453796E633B7870737200346A6176612E7574696C2E636F6E63757272656E742E6C6F636B732E5265656E7472616E744C6F636B244E6F6E6661697253796E63658832E7537BBF0B0200007872002D6A6176612E7574696C2E636F6E63757272656E742E6C6F636B732E5265656E7472616E744C6F636B2453796E63B81EA294AA445A7C020000787200356A6176612E7574696C2E636F6E63757272656E742E6C6F636B732E416273747261637451756575656453796E6368726F6E697A65726655A843753F52E30200014900057374617465787200366A6176612E7574696C2E636F6E63757272656E742E6C6F636B732E41627374726163744F776E61626C6553796E6368726F6E697A657233DFAFB9AD6D6FA90200007870000000003F4000007371007E00247371007E0028000000003F4000007371007E00247371007E0028000000003F4000007371007E00247371007E0028000000003F4000007371007E00247371007E0028000000003F4000007371007E00247371007E0028000000003F4000007371007E00247371007E0028000000003F4000007371007E00247371007E0028000000003F4000007371007E00247371007E0028000000003F4000007371007E00247371007E0028000000003F4000007371007E00247371007E0028000000003F4000007371007E00247371007E0028000000003F4000007371007E00247371007E0028000000003F4000007371007E00247371007E0028000000003F4000007371007E00247371007E0028000000003F4000007371007E00247371007E0028000000003F40000074000D636C69656E745F736563726574740005756E69747974000A6772616E745F7479706574000870617373776F7264740009636C69656E745F696474000C756E6974792D636C69656E74740008757365726E616D6574000561646D696E74000573636F706574000A72656164207772697465707078707371007E001B770C000000103F4000000000000174000E756E6974792D7265736F7572636578737200176A6176612E7574696C2E4C696E6B656448617368536574D86CD75A95DD2A1E0200007871007E001B770C000000103F40000000000002740004726561647400057772697465787372004F6F72672E737072696E676672616D65776F726B2E73656375726974792E61757468656E7469636174696F6E2E557365726E616D6550617373776F726441757468656E7469636174696F6E546F6B656E00000000000001360200024C000B63726564656E7469616C7371007E00054C00097072696E636970616C71007E00057871007E0003017371007E00077371007E000B0000000377040000000371007E000F71007E001171007E00137871007E005E707073720022636F6D2E676F70617274792E6F61757468322E5573657244657461696C73496D706CE82451B58FDF1E880200014C0004757365727400234C636F6D2F676F70617274792F646174612F6D6F64656C2F4F4175746832557365723B787073720021636F6D2E676F70617274792E646174612E6D6F64656C2E4F41757468325573657222F660275F0C75920200075A000B64656661756C74557365724C0005656D61696C71007E000E4C000269647400134C6A6176612F6C616E672F496E74656765723B4C000D6C6173744C6F67696E54696D657400104C6A6176612F7574696C2F446174653B4C000870617373776F726471007E000E4C000570686F6E6571007E000E4C0008757365726E616D6571007E000E78700174000D61646D696E40776463792E6363737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000001570740020323132333266323937613537613561373433383934613065346138303166633374000B3032382D3132333435363774000561646D696E, 'dcff4b487116148bc5f0b155c0096bed');
/*!40000 ALTER TABLE `oauth_access_token` ENABLE KEYS */;


-- Dumping structure for table openfire.oauth_client_details
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE IF NOT EXISTS `oauth_client_details` (
  `client_id` varchar(255) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `archived` tinyint(1) DEFAULT '0',
  `trusted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.oauth_client_details: ~2 rows (approximately)
DELETE FROM `oauth_client_details`;
/*!40000 ALTER TABLE `oauth_client_details` DISABLE KEYS */;
INSERT INTO `oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `create_time`, `archived`, `trusted`) VALUES
	('mobile-client', 'mobile-resource', 'mobile', 'read,write', 'password,authorization_code,refresh_token,implicit', NULL, 'ROLE_MOBILE', NULL, NULL, NULL, '2014-03-22 04:01:16', 0, 0),
	('unity-client', 'unity-resource', 'unity', 'read,write', 'password,authorization_code,refresh_token,implicit', NULL, 'ROLE_UNITY', NULL, NULL, NULL, '2014-03-22 04:01:16', 0, 0);
/*!40000 ALTER TABLE `oauth_client_details` ENABLE KEYS */;


-- Dumping structure for table openfire.oauth_client_token
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE IF NOT EXISTS `oauth_client_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(256) DEFAULT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.oauth_client_token: ~0 rows (approximately)
DELETE FROM `oauth_client_token`;
/*!40000 ALTER TABLE `oauth_client_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_client_token` ENABLE KEYS */;


-- Dumping structure for table openfire.oauth_code
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE IF NOT EXISTS `oauth_code` (
  `code` varchar(256) DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.oauth_code: ~0 rows (approximately)
DELETE FROM `oauth_code`;
/*!40000 ALTER TABLE `oauth_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_code` ENABLE KEYS */;


-- Dumping structure for table openfire.oauth_refresh_token
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE IF NOT EXISTS `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.oauth_refresh_token: ~1 rows (approximately)
DELETE FROM `oauth_refresh_token`;
/*!40000 ALTER TABLE `oauth_refresh_token` DISABLE KEYS */;
INSERT INTO `oauth_refresh_token` (`token_id`, `token`, `authentication`) VALUES
	('dcff4b487116148bc5f0b155c0096bed', _binary 0xACED00057372004C6F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E636F6D6D6F6E2E44656661756C744578706972696E674F417574683252656672657368546F6B656E2FDF47639DD0C9B70200014C000A65787069726174696F6E7400104C6A6176612F7574696C2F446174653B787200446F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E636F6D6D6F6E2E44656661756C744F417574683252656672657368546F6B656E73E10E0A6354D45E0200014C000576616C75657400124C6A6176612F6C616E672F537472696E673B787074002435316230343739662D366661652D346333612D613937622D6663346364356238303539657372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001458272C79078, _binary 0xACED0005737200416F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E70726F76696465722E4F417574683241757468656E7469636174696F6EBD400B02166252130200024C0014636C69656E7441757468656E7469636174696F6E7400434C6F72672F737072696E676672616D65776F726B2F73656375726974792F6F61757468322F70726F76696465722F417574686F72697A6174696F6E526571756573743B4C00127573657241757468656E7469636174696F6E7400324C6F72672F737072696E676672616D65776F726B2F73656375726974792F636F72652F41757468656E7469636174696F6E3B787200476F72672E737072696E676672616D65776F726B2E73656375726974792E61757468656E7469636174696F6E2E416273747261637441757468656E7469636174696F6E546F6B656ED3AA287E6E47640E0200035A000D61757468656E746963617465644C000B617574686F7269746965737400164C6A6176612F7574696C2F436F6C6C656374696F6E3B4C000764657461696C737400124C6A6176612F6C616E672F4F626A6563743B787000737200266A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C654C697374FC0F2531B5EC8E100200014C00046C6973747400104C6A6176612F7574696C2F4C6973743B7872002C6A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C65436F6C6C656374696F6E19420080CB5EF71E0200014C00016371007E00047870737200136A6176612E7574696C2E41727261794C6973747881D21D99C7619D03000149000473697A65787000000003770400000003737200426F72672E737072696E676672616D65776F726B2E73656375726974792E636F72652E617574686F726974792E53696D706C654772616E746564417574686F7269747900000000000001360200014C0004726F6C657400124C6A6176612F6C616E672F537472696E673B7870740009524F4C455F555345527371007E000D74000A524F4C455F554E4954597371007E000D74000B524F4C455F4D4F42494C457871007E000C70737200486F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E70726F76696465722E44656661756C74417574686F72697A6174696F6E52657175657374DD2F6C1608186D180200075A0008617070726F7665644C0012617070726F76616C506172616D657465727374000F4C6A6176612F7574696C2F4D61703B4C000B617574686F72697469657371007E00044C0017617574686F72697A6174696F6E506172616D657465727371007E00164C00137265736F6C766564526564697265637455726971007E000E4C000B7265736F7572636549647374000F4C6A6176612F7574696C2F5365743B4C000573636F706571007E0017787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F400000000000107708000000100000000078737200116A6176612E7574696C2E48617368536574BA44859596B8B7340300007870770C000000103F400000000000017371007E000D74000A524F4C455F554E49545978737200266A6176612E7574696C2E636F6E63757272656E742E436F6E63757272656E74486173684D61706499DE129D87293D03000349000B7365676D656E744D61736B49000C7365676D656E7453686966745B00087365676D656E74737400315B4C6A6176612F7574696C2F636F6E63757272656E742F436F6E63757272656E74486173684D6170245365676D656E743B78700000000F0000001C757200315B4C6A6176612E7574696C2E636F6E63757272656E742E436F6E63757272656E74486173684D6170245365676D656E743B52773F41329B39740200007870000000107372002E6A6176612E7574696C2E636F6E63757272656E742E436F6E63757272656E74486173684D6170245365676D656E741F364C905893293D02000146000A6C6F6164466163746F72787200286A6176612E7574696C2E636F6E63757272656E742E6C6F636B732E5265656E7472616E744C6F636B6655A82C2CC86AEB0200014C000473796E6374002F4C6A6176612F7574696C2F636F6E63757272656E742F6C6F636B732F5265656E7472616E744C6F636B2453796E633B7870737200346A6176612E7574696C2E636F6E63757272656E742E6C6F636B732E5265656E7472616E744C6F636B244E6F6E6661697253796E63658832E7537BBF0B0200007872002D6A6176612E7574696C2E636F6E63757272656E742E6C6F636B732E5265656E7472616E744C6F636B2453796E63B81EA294AA445A7C020000787200356A6176612E7574696C2E636F6E63757272656E742E6C6F636B732E416273747261637451756575656453796E6368726F6E697A65726655A843753F52E30200014900057374617465787200366A6176612E7574696C2E636F6E63757272656E742E6C6F636B732E41627374726163744F776E61626C6553796E6368726F6E697A657233DFAFB9AD6D6FA90200007870000000003F4000007371007E00247371007E0028000000003F4000007371007E00247371007E0028000000003F4000007371007E00247371007E0028000000003F4000007371007E00247371007E0028000000003F4000007371007E00247371007E0028000000003F4000007371007E00247371007E0028000000003F4000007371007E00247371007E0028000000003F4000007371007E00247371007E0028000000003F4000007371007E00247371007E0028000000003F4000007371007E00247371007E0028000000003F4000007371007E00247371007E0028000000003F4000007371007E00247371007E0028000000003F4000007371007E00247371007E0028000000003F4000007371007E00247371007E0028000000003F4000007371007E00247371007E0028000000003F40000074000D636C69656E745F736563726574740005756E69747974000A6772616E745F7479706574000870617373776F7264740009636C69656E745F696474000C756E6974792D636C69656E74740008757365726E616D6574000561646D696E74000573636F706574000A72656164207772697465707078707371007E001B770C000000103F4000000000000174000E756E6974792D7265736F7572636578737200176A6176612E7574696C2E4C696E6B656448617368536574D86CD75A95DD2A1E0200007871007E001B770C000000103F40000000000002740004726561647400057772697465787372004F6F72672E737072696E676672616D65776F726B2E73656375726974792E61757468656E7469636174696F6E2E557365726E616D6550617373776F726441757468656E7469636174696F6E546F6B656E00000000000001360200024C000B63726564656E7469616C7371007E00054C00097072696E636970616C71007E00057871007E0003017371007E00077371007E000B0000000377040000000371007E000F71007E001171007E00137871007E005E707073720022636F6D2E676F70617274792E6F61757468322E5573657244657461696C73496D706CE82451B58FDF1E880200014C0004757365727400234C636F6D2F676F70617274792F646174612F6D6F64656C2F4F4175746832557365723B787073720021636F6D2E676F70617274792E646174612E6D6F64656C2E4F41757468325573657222F660275F0C75920200075A000B64656661756C74557365724C0005656D61696C71007E000E4C000269647400134C6A6176612F6C616E672F496E74656765723B4C000D6C6173744C6F67696E54696D657400104C6A6176612F7574696C2F446174653B4C000870617373776F726471007E000E4C000570686F6E6571007E000E4C0008757365726E616D6571007E000E78700174000D61646D696E40776463792E6363737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000001570740020323132333266323937613537613561373433383934613065346138303166633374000B3032382D3132333435363774000561646D696E);
/*!40000 ALTER TABLE `oauth_refresh_token` ENABLE KEYS */;


-- Dumping structure for table openfire.user_
DROP TABLE IF EXISTS `user_`;
CREATE TABLE IF NOT EXISTS `user_` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `archived` tinyint(1) DEFAULT '0',
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `default_user` tinyint(1) DEFAULT '0',
  `last_login_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `guid` (`guid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.user_: ~1 rows (approximately)
DELETE FROM `user_`;
/*!40000 ALTER TABLE `user_` DISABLE KEYS */;
INSERT INTO `user_` (`id`, `guid`, `create_time`, `archived`, `email`, `password`, `phone`, `username`, `default_user`, `last_login_time`) VALUES
	(21, '29f6004fb1b0466f9572b02bf2ac1be8', '2014-03-22 04:01:16', 0, 'admin@wdcy.cc', '21232f297a57a5a743894a0e4a801fc3', '028-1234567', 'admin', 1, NULL);
/*!40000 ALTER TABLE `user_` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;