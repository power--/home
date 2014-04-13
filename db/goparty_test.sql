-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.34-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table openfire.gp_event
DROP TABLE IF EXISTS `gp_event`;
CREATE TABLE IF NOT EXISTS `gp_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `endTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `startTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `status` varchar(255) DEFAULT NULL,
  `cateId` tinyint(4) NOT NULL,
  `ownerId` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.gp_event: ~36 rows (approximately)
DELETE FROM `gp_event`;
/*!40000 ALTER TABLE `gp_event` DISABLE KEYS */;
INSERT INTO `gp_event` (`id`, `title`, `description`, `endTime`, `startTime`, `status`, `cateId`, `ownerId`) VALUES
	(8, 'A Title', 'Hello World', '2014-01-23 14:21:31', '2014-01-23 14:20:42', 'INIT', 1, 21),
	(9, 'A Title', 'Hello World', '2014-01-23 14:22:04', '2014-01-23 14:22:04', 'INIT', 1, 21),
	(10, 'A Title', 'Hello World', '2014-01-23 14:26:58', '2014-01-23 14:22:43', 'INIT', 1, 2),
	(11, 'A Title', 'Hello World', '2014-01-23 14:27:04', '2014-01-23 14:23:20', 'INIT', 1, 2),
	(12, 'A Title', 'Hello World', '2014-01-23 14:26:34', '2014-01-23 14:26:34', 'INIT', 1, 21),
	(13, 'A Title', 'Hello World', '2014-01-24 03:24:49', '2014-01-24 03:24:49', 'INIT', 1, 21),
	(14, 'A Title', 'Hello World', '2014-01-24 03:26:29', '2014-01-24 03:26:29', 'INIT', 1, 21),
	(15, 'A Title', 'Hello World', '2014-01-24 03:27:20', '2014-01-24 03:27:20', 'INIT', 1, 21),
	(16, 'A Title', 'Hello World', '2014-01-24 03:28:18', '2014-01-24 03:28:18', 'INIT', 1, 21),
	(17, 'A Title', 'Hello World', '2014-01-24 03:28:43', '2014-01-24 03:28:43', 'INIT', 1, 21),
	(18, 'A Title', 'Hello World', '2014-01-24 03:29:24', '2014-01-24 03:29:24', 'INIT', 1, 21),
	(19, 'A Title', 'Hello World3', '2014-01-24 09:43:22', '2014-01-24 03:31:19', 'INIT', 2, 21),
	(20, 'A Title', 'Hello World33', '2014-01-24 09:43:25', '2014-01-24 06:49:21', 'INIT', 2, 21),
	(21, 'A Title', 'Hello World2', '2014-01-24 09:43:28', '2014-01-24 07:57:00', 'INIT', 2, 21),
	(22, 'A Title', 'Hello World', '2014-01-24 07:58:28', '2014-01-24 07:58:28', 'INIT', 1, 21),
	(23, 'A Title', 'Hello World', '2014-01-24 09:39:41', '2014-01-24 09:39:41', 'INIT', 1, 21),
	(24, 'A Title', 'Hello World', '2014-01-25 07:54:07', '2014-01-25 07:54:07', 'INIT', 1, 10086),
	(25, 'A Title', 'Hello World', '2014-01-25 07:56:51', '2014-01-25 07:56:51', 'INIT', 1, 21),
	(26, 'A Title', 'Hello World', '2014-01-25 07:57:59', '2014-01-25 07:57:59', 'INIT', 1, 21),
	(27, 'A Title', 'Hello World', '2014-02-16 02:09:06', '2014-02-16 02:09:06', 'INIT', 1, 21),
	(28, NULL, 'test', '2014-02-16 03:44:55', '2014-02-16 03:44:55', NULL, 1, NULL),
	(29, 'A Title', 'Hello World', '2014-02-16 08:45:30', '2014-02-16 08:45:30', 'INIT', 1, 21),
	(30, 'A Title', 'Hello World', '2014-02-16 08:46:49', '2014-02-16 08:46:49', 'INIT', 1, 21),
	(31, 'A Title', 'Hello World', '2014-02-16 09:01:53', '2014-02-16 09:01:53', 'INIT', 1, 21),
	(32, 'A Title', 'Hello World', '2014-02-16 09:02:33', '2014-02-16 09:02:33', 'INIT', 1, 21),
	(33, 'A Title', 'Hello World', '2014-02-16 09:04:33', '2014-02-16 09:04:33', 'INIT', 1, 21),
	(34, 'A Title', 'Hello World', '2014-02-16 09:06:57', '2014-02-16 09:06:57', 'INIT', 1, 21),
	(41, 'A Title', 'Hello World', '2014-02-16 09:48:08', '2014-02-16 09:48:08', 'INIT', 1, 21),
	(42, 'A Title', 'Hello World', '2014-02-16 09:48:40', '2014-02-16 09:48:40', 'INIT', 1, 21),
	(45, 'A Title', 'Hello World', '2014-02-16 09:51:54', '2014-02-16 09:51:54', 'INIT', 1, 21),
	(46, 'A Title', 'Hello World', '2014-02-16 10:23:10', '2014-02-16 10:23:10', 'INIT', 1, 21),
	(48, 'A Title', 'Hello World', '2014-02-16 10:23:10', '2014-02-16 10:23:10', 'INIT', 1, 21),
	(49, 'A Title', 'Hello World', '2014-02-16 10:23:10', '2014-02-16 10:23:10', 'INIT', 1, 21),
	(50, 'A Title', 'Hello World', '2014-02-16 10:23:10', '2014-02-16 10:23:10', 'INIT', 1, 21),
	(51, 'A Title', 'Hello World', '2014-02-19 00:00:00', '2014-02-16 10:23:10', 'INIT', 1, 21),
	(52, 'A Title', 'Hello World', '2014-02-19 00:00:00', '2014-02-16 10:23:10', 'INIT', 1, 21);
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

-- Dumping data for table openfire.gp_event_attendee: ~26 rows (approximately)
DELETE FROM `gp_event_attendee`;
/*!40000 ALTER TABLE `gp_event_attendee` DISABLE KEYS */;
INSERT INTO `gp_event_attendee` (`eventId`, `userId`, `status`, `updateTime`) VALUES
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
	(2, '鐢靛奖闄�'),
	(3, '杩愬姩鍋ヨ韩 '),
	(4, '鍚冮キ');
/*!40000 ALTER TABLE `gp_event_category` ENABLE KEYS */;


-- Dumping structure for table openfire.gp_event_message
DROP TABLE IF EXISTS `gp_event_message`;
CREATE TABLE IF NOT EXISTS `gp_event_message` (
  `id` int(10) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL COMMENT '1-system message 2 - user message',
  `userId` int(11) DEFAULT NULL,
  `publishTime` datetime DEFAULT NULL,
  `eventId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.gp_event_message: ~0 rows (approximately)
DELETE FROM `gp_event_message`;
/*!40000 ALTER TABLE `gp_event_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `gp_event_message` ENABLE KEYS */;


-- Dumping structure for table openfire.gp_group
DROP TABLE IF EXISTS `gp_group`;
CREATE TABLE IF NOT EXISTS `gp_group` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `ownerId` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.gp_group: ~0 rows (approximately)
DELETE FROM `gp_group`;
/*!40000 ALTER TABLE `gp_group` DISABLE KEYS */;
INSERT INTO `gp_group` (`id`, `name`, `ownerId`) VALUES
	(2, 'Boys', 33),
	(3, 'Girls', 33),
	(5, '3', 33),
	(9, 'private friend ', 33);
/*!40000 ALTER TABLE `gp_group` ENABLE KEYS */;


-- Dumping structure for table openfire.gp_group_user
DROP TABLE IF EXISTS `gp_group_user`;
CREATE TABLE IF NOT EXISTS `gp_group_user` (
  `groupId` int(10) NOT NULL,
  `userId` int(10) NOT NULL,
  PRIMARY KEY (`groupId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.gp_group_user: ~0 rows (approximately)
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


-- Dumping structure for table openfire.gp_user_profile
DROP TABLE IF EXISTS `gp_user_profile_profile`;
CREATE TABLE IF NOT EXISTS `gp_user_profile` (
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
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.gp_user_profile: ~40 rows (approximately)
DELETE FROM `gp_user_profile`;
/*!40000 ALTER TABLE `gp_user_profile` DISABLE KEYS */;
INSERT INTO `gp_user_profile` (`id`, `loginId`, `nickName`, `password`, `qq`, `birthdate`, `gender`, `location`, `phone`, `photo`, `signature`, `weChat`, `weibo`) VALUES
	(12, 'att212', 'att2', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(17, 'user1717', 'ahu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(18, 'user1818', 'ahu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(19, 'user1919', 'ahu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(20, 'user2020', 'ahu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(21, 'user2121', 'ahu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(22, 'user5422', 'f11', 'f1password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(33, 'chenb33', 'Chen, Bo', 'password', '1343243', '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
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
	(98, 'openId--', 'tokenId--', NULL, NULL, '2014-03-03 06:42:01', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `gp_user_profile` ENABLE KEYS */;


-- Dumping structure for table openfire.gp_user_profile_friend
DROP TABLE IF EXISTS `gp_user_profile_friend`;
CREATE TABLE IF NOT EXISTS `gp_user_profile_friend` (
  `userId` int(10) NOT NULL,
  `friendId` int(10) NOT NULL,
  `status` char(5) DEFAULT 'INIT' COMMENT 'init  agree reject',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `remarkName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userId`,`friendId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.gp_user_profile_friend: ~24 rows (approximately)
DELETE FROM `gp_user_profile_friend`;
/*!40000 ALTER TABLE `gp_user_profile_friend` DISABLE KEYS */;
INSERT INTO `gp_user_profile_friend` (`userId`, `friendId`, `status`, `updateTime`, `remarkName`) VALUES
	(22, 92, 'AGREE', '2014-03-04 08:37:17', 'Jim'),
	(22, 99, NULL, NULL, NULL),
	(33, 34, 'INIT', '2014-03-03 07:19:01', 'yrtytry'),
	(33, 35, 'AGREE', '2014-03-05 09:44:43', 'ss'),
	(33, 90, NULL, NULL, NULL),
	(33, 92, 'AGREE', '2014-03-04 08:55:40', 'Jim'),
	(33, 93, 'init', '2014-03-04 09:27:11', 'new Remark'),
	(33, 95, 'INIT', '2014-03-05 08:41:20', 'new Remark'),
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
/*!40000 ALTER TABLE `gp_user_profile_friend` ENABLE KEYS */;


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
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
