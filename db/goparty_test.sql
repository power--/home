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


-- Dumping structure for table openfire.gp_category
DROP TABLE IF EXISTS `gp_category`;
CREATE TABLE IF NOT EXISTS `gp_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.gp_category: ~4 rows (approximately)
DELETE FROM `gp_category`;
/*!40000 ALTER TABLE `gp_category` DISABLE KEYS */;
INSERT INTO `gp_category` (`id`, `name`, `logo`) VALUES
	(1, 'KTV ', 'http://image.baidu.com/adfafafafaa.jpg'),
	(2, '中文', NULL),
	(3, '回成 ', NULL),
	(4, '吃饭', NULL);
/*!40000 ALTER TABLE `gp_category` ENABLE KEYS */;


-- Dumping structure for table openfire.gp_event
DROP TABLE IF EXISTS `gp_event`;
CREATE TABLE IF NOT EXISTS `gp_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `endTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `startTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `location` varchar(255) DEFAULT NULL,
  `status` tinyint(4) NOT NULL,
  `visibility` tinyint(4) NOT NULL,
  `locationShareable` tinyint(1) NOT NULL,
  `ownerId` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.gp_event: ~73 rows (approximately)
DELETE FROM `gp_event`;
/*!40000 ALTER TABLE `gp_event` DISABLE KEYS */;
INSERT INTO `gp_event` (`id`, `title`, `description`, `endTime`, `startTime`, `location`, `status`, `visibility`, `locationShareable`, `ownerId`) VALUES
	(1, 'hello PUT', 'test', '2014-03-13 09:53:17', '2014-03-13 09:53:17', 'Shenzhen', 0, 0, 0, 33),
	(2, 'hello PUT', 'test', '2014-03-21 16:22:33', '2014-03-21 16:22:33', 'Shenzhen', 0, 0, 0, 33),
	(3, 'hello PUT', 'test', '2014-03-21 17:01:04', '2014-03-21 17:01:04', 'Shenzhen', 0, 0, 0, 33),
	(4, 'hello', 'test', '2014-04-05 13:54:40', '2014-04-05 13:54:40', 'Shenzhen', 0, 0, 0, 21),
	(5, 'hello', 'test', '2014-04-05 14:06:18', '2014-04-05 14:06:18', 'Shenzhen', 0, 0, 0, 21),
	(6, 'hello', 'test', '2014-04-06 03:21:40', '2014-04-06 03:21:40', 'Shenzhen', 0, 0, 0, 33),
	(7, 'hello', 'test', '2014-04-06 03:22:37', '2014-04-06 03:22:37', 'Shenzhen', 0, 0, 0, 33),
	(8, 'hello', 'test', '2014-04-06 03:37:46', '2014-04-06 03:37:46', 'Shenzhen', 0, 0, 0, 33),
	(9, 'hello', 'test', '2014-04-06 03:43:06', '2014-04-06 03:43:06', 'Shenzhen', 0, 0, 0, 33),
	(10, 'hello', 'test', '2014-04-06 03:45:50', '2014-04-06 03:45:50', 'Shenzhen', 0, 0, 0, 33),
	(11, 'hello', 'test', '2014-04-06 03:46:15', '2014-04-06 03:46:15', 'Shenzhen', 0, 0, 0, 33),
	(14, 'hello', 'test', '2014-04-07 01:41:01', '2014-04-07 01:41:01', 'Shenzhen', 0, 0, 0, 33),
	(15, 'hello', 'test', '2014-04-09 22:26:24', '2014-04-09 22:26:24', 'Shenzhen', 0, 0, 0, 21),
	(16, 'hello', 'test', '2014-04-18 09:18:49', '2014-04-18 09:18:49', 'Shenzhen', 0, 0, 0, 33),
	(17, 'hello', 'test', '2014-04-18 09:57:39', '2014-04-18 09:57:39', 'Shenzhen', 0, 0, 0, 33),
	(18, 'hello', 'test', '2014-04-19 02:06:07', '2014-04-19 02:06:07', 'Shenzhen', 0, 0, 0, 33),
	(20, 'hello', 'test', '2014-04-19 02:10:21', '2014-04-19 02:10:21', 'Shenzhen', 0, 0, 0, 33),
	(23, 'hello', 'test', '2014-04-19 02:19:45', '2014-04-19 02:19:45', 'Shenzhen', 0, 0, 0, 33),
	(24, 'hello', 'test', '2014-04-19 02:27:04', '2014-04-19 02:27:04', 'Shenzhen', 0, 0, 0, 21),
	(28, 'hello', 'test', '2014-04-19 03:48:18', '2014-04-19 03:48:18', 'Shenzhen', 0, 0, 0, 21),
	(29, 'hello', 'test', '2014-04-19 04:04:13', '2014-04-19 04:04:13', 'Shenzhen', 0, 0, 0, 21),
	(30, 'hello', 'test', '2014-04-19 04:05:01', '2014-04-19 04:05:01', 'Shenzhen', 0, 0, 0, 21),
	(31, 'hello', 'test', '2014-04-19 04:06:12', '2014-04-19 04:06:12', 'Shenzhen', 0, 0, 0, 21),
	(32, 'hello', 'test', '2014-04-19 04:07:41', '2014-04-19 04:07:41', 'Shenzhen', 0, 0, 0, 21),
	(33, 'hello', 'test', '2014-04-19 04:10:07', '2014-04-19 04:10:07', 'Shenzhen', 0, 0, 0, 21),
	(34, 'hello', 'test', '2014-04-19 04:11:58', '2014-04-19 04:11:58', 'Shenzhen', 0, 0, 0, 18),
	(35, 'hello', 'test', '2014-04-19 04:18:27', '2014-04-19 04:18:27', 'Shenzhen', 0, 0, 0, 18),
	(37, 'hello', 'test', '2014-04-19 09:10:56', '2014-04-19 09:10:56', 'Shenzhen', 0, 0, 0, NULL),
	(38, 'hello', 'test', '2014-04-19 09:14:39', '2014-04-19 09:14:39', 'Shenzhen', 0, 0, 0, NULL),
	(40, 'hello', 'test', '2014-04-19 09:23:27', '2014-04-19 09:23:27', 'Shenzhen', 0, 0, 0, 18),
	(42, 'hello', 'test', '2014-04-19 14:50:41', '2014-04-19 14:50:41', 'Shenzhen', 0, 2, 0, 18),
	(43, 'hello', 'test', '2014-04-19 14:56:04', '2014-04-19 14:56:04', 'Shenzhen', 0, 2, 0, 18),
	(44, 'A Title', 'Hello World', '2014-04-20 12:39:23', '2014-04-20 12:39:23', NULL, 0, 1, 0, 21),
	(45, 'A Title', 'Hello World', '2014-04-20 12:41:06', '2014-04-20 12:41:06', NULL, 0, 1, 0, 21),
	(46, 'A Title', 'Hello World', '2014-04-20 12:42:21', '2014-04-20 12:42:21', NULL, 0, 1, 0, 21),
	(47, 'A Title', 'Hello World', '2014-04-20 12:43:51', '2014-04-20 12:43:51', NULL, 0, 1, 0, 21),
	(52, 'hello', 'test', '2014-04-20 13:37:24', '2014-04-20 13:37:24', 'Shenzhen', 0, 2, 0, 18),
	(59, 'A Title', 'Hello World', '2014-04-21 14:14:23', '2014-04-21 14:14:23', NULL, 0, 1, 0, NULL),
	(60, 'A Title', 'Hello World', '2014-04-21 14:15:29', '2014-04-21 14:15:29', NULL, 0, 1, 0, NULL),
	(61, 'A Title', 'Hello World', '2014-04-21 14:21:00', '2014-04-21 14:21:00', NULL, 0, 1, 0, NULL),
	(62, 'A Title', 'Hello World', '2014-04-21 14:22:08', '2014-04-21 14:22:08', NULL, 0, 1, 0, 18),
	(76, 'A Title', 'Hello World', '2014-04-22 02:43:09', '2014-04-22 02:43:09', NULL, 0, 1, 0, 18),
	(77, 'A Title', 'Hello World', '2014-04-22 02:44:32', '2014-04-22 02:44:32', NULL, 0, 1, 0, 18),
	(78, 'A Title', 'Hello World', '2014-04-22 02:45:58', '2014-04-22 02:45:58', NULL, 0, 1, 0, 18),
	(79, 'A Title', 'Hello World', '2014-04-22 02:52:27', '2014-04-22 02:52:27', NULL, 0, 1, 0, 18),
	(80, 'A Title', 'Hello World', '2014-04-22 03:05:39', '2014-04-22 03:05:39', NULL, 0, 1, 0, 18),
	(93, 'A Title', 'Hello World', '2014-04-22 06:18:45', '2014-04-22 06:18:45', NULL, 0, 1, 0, 21),
	(94, 'A Title', 'Hello World', '2014-04-22 06:21:01', '2014-04-22 06:21:01', NULL, 0, 1, 0, 21),
	(96, 'A Title', 'Hello World', '2014-04-22 06:25:50', '2014-04-22 06:25:50', NULL, 0, 1, 0, 21),
	(98, 'hello PUT', 'test', '2014-04-22 06:51:45', '2014-04-22 06:51:45', 'Shenzhen', 0, 0, 0, 33),
	(99, 'hello', 'test', '2014-04-22 06:52:35', '2014-04-22 06:52:35', 'Shenzhen', 0, 0, 0, 18),
	(100, 'hello', 'test', '2014-04-22 06:54:45', '2014-04-22 06:54:45', 'Shenzhen', 0, 0, 0, 18),
	(101, 'hello PUT', 'test', '2014-04-22 06:56:13', '2014-04-22 06:56:13', 'Shenzhen', 0, 0, 0, 18),
	(102, 'hello PUT', 'test', '2014-04-22 06:59:09', '2014-04-22 06:59:09', 'Shenzhen', 0, 0, 0, 18),
	(103, 'hello PUT', 'test', '2014-04-22 08:12:13', '2014-04-22 08:12:13', 'Shenzhen', 0, 0, 0, 18),
	(104, 'hello PUT', 'test', '2014-04-22 08:13:20', '2014-04-22 08:13:20', 'Shenzhen', 0, 0, 0, 18),
	(105, 'hello PUT', 'test', '2014-04-22 08:17:41', '2014-04-22 08:17:41', 'Shenzhen', 0, 0, 0, 18),
	(106, 'hello PUT', 'test', '2014-04-22 08:51:10', '2014-04-22 08:51:10', 'Shenzhen', 0, 0, 0, 18),
	(107, 'hello PUT', 'test', '2014-04-22 08:54:35', '2014-04-22 08:54:35', 'Shenzhen', 0, 0, 0, 18),
	(108, 'hello PUT', 'test', '2014-04-22 08:56:42', '2014-04-22 08:56:42', 'Shenzhen', 0, 0, 0, 18),
	(109, 'hello PUT', 'test', '2014-04-22 09:07:16', '2014-04-22 09:07:16', 'Shenzhen', 0, 0, 0, 18),
	(110, 'hello PUT', 'test', '2014-04-22 09:09:32', '2014-04-22 09:09:32', 'Shenzhen', 0, 0, 0, 18),
	(113, 'hello PUT', 'test', '2014-04-22 09:46:22', '2014-04-22 09:46:22', 'Shenzhen', 0, 0, 0, 18),
	(114, 'hello', 'test', '2014-04-22 09:48:27', '2014-04-22 09:48:27', 'Shenzhen', 0, 0, 0, 18),
	(115, 'hello', 'test', '2014-04-22 09:58:06', '2014-04-22 09:58:06', 'Shenzhen', 0, 0, 0, 18),
	(116, 'hello', 'test', '2014-04-22 10:08:53', '2014-04-22 10:08:53', 'Shenzhen', 0, 0, 0, 18),
	(117, 'A Title', 'Hello World', '2014-04-22 14:19:26', '2014-04-22 14:19:26', NULL, 0, 1, 0, 21),
	(118, 'A Title', 'Hello World', '2014-04-22 14:23:17', '2014-04-22 14:23:17', NULL, 0, 1, 0, 21),
	(119, 'A Title', 'Hello World', '2014-04-22 14:24:02', '2014-04-22 14:24:02', NULL, 0, 1, 0, 21),
	(120, 'A Title', 'Hello World', '2014-04-22 14:28:27', '2014-04-22 14:28:27', NULL, 0, 1, 0, 21),
	(121, 'A Title', 'Hello World', '2014-04-22 14:31:31', '2014-04-22 14:31:31', NULL, 0, 1, 0, 21),
	(123, 'hello PUT', 'test', '2014-04-22 15:04:28', '2014-04-22 15:04:28', 'Shenzhen', 0, 0, 0, 33),
	(127, 'A Title', 'Hello World', '2014-04-23 02:25:33', '2014-04-23 02:25:33', NULL, 0, 1, 0, 21);
/*!40000 ALTER TABLE `gp_event` ENABLE KEYS */;


-- Dumping structure for table openfire.gp_event_application
DROP TABLE IF EXISTS `gp_event_application`;
CREATE TABLE IF NOT EXISTS `gp_event_application` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eventId` int(11) DEFAULT NULL,
  `applicantId` int(11) DEFAULT NULL,
  `applicantMessage` varchar(255) DEFAULT NULL,
  `adminIds` varchar(255) DEFAULT NULL,
  `approverId` int(11) DEFAULT NULL,
  `approverMessage` varchar(255) DEFAULT NULL,
  `approval` varchar(10) DEFAULT NULL,
  `status` char(4) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.gp_event_application: ~0 rows (approximately)
DELETE FROM `gp_event_application`;
/*!40000 ALTER TABLE `gp_event_application` DISABLE KEYS */;
/*!40000 ALTER TABLE `gp_event_application` ENABLE KEYS */;


-- Dumping structure for table openfire.gp_event_category
DROP TABLE IF EXISTS `gp_event_category`;
CREATE TABLE IF NOT EXISTS `gp_event_category` (
  `eventId` int(10) NOT NULL,
  `cateId` int(10) NOT NULL,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.gp_event_category: ~42 rows (approximately)
DELETE FROM `gp_event_category`;
/*!40000 ALTER TABLE `gp_event_category` DISABLE KEYS */;
INSERT INTO `gp_event_category` (`eventId`, `cateId`, `updateTime`) VALUES
	(33, 1, '2014-04-19 04:10:08'),
	(34, 1, '2014-04-19 04:11:58'),
	(35, 1, '2014-04-19 04:18:28'),
	(37, 1, '2014-04-19 09:13:38'),
	(38, 1, '2014-04-19 09:17:31'),
	(40, 1, '2014-04-19 09:23:32'),
	(42, 1, '2014-04-19 14:50:59'),
	(43, 1, '2014-04-19 14:56:06'),
	(44, 1, '2014-04-20 12:39:23'),
	(46, 1, '2014-04-20 12:42:22'),
	(52, 1, '2014-04-20 13:37:26'),
	(59, 1, '2014-04-21 14:14:23'),
	(60, 1, '2014-04-21 14:15:29'),
	(61, 1, '2014-04-21 14:21:00'),
	(62, 1, '2014-04-21 14:22:08'),
	(76, 1, '2014-04-22 02:43:10'),
	(77, 1, '2014-04-22 02:44:32'),
	(78, 1, '2014-04-22 02:45:58'),
	(79, 1, '2014-04-22 02:52:27'),
	(80, 1, '2014-04-22 03:05:40'),
	(94, 1, '2014-04-22 06:21:01'),
	(96, 1, '2014-04-22 06:25:50'),
	(98, 1, '2014-04-22 06:51:47'),
	(99, 1, '2014-04-22 06:53:46'),
	(100, 1, '2014-04-22 06:54:46'),
	(101, 1, '2014-04-22 06:56:15'),
	(102, 1, '2014-04-22 06:59:13'),
	(103, 1, '2014-04-22 08:12:18'),
	(104, 1, '2014-04-22 08:14:23'),
	(105, 1, '2014-04-22 08:17:45'),
	(106, 1, '2014-04-22 08:51:14'),
	(107, 1, '2014-04-22 08:56:36'),
	(108, 1, '2014-04-22 09:04:02'),
	(109, 1, '2014-04-22 09:08:30'),
	(114, 1, '2014-04-22 09:48:29'),
	(115, 1, '2014-04-22 09:58:08'),
	(116, 1, '2014-04-22 10:08:55'),
	(117, 1, '2014-04-22 14:19:26'),
	(118, 1, '2014-04-22 14:23:18'),
	(119, 1, '2014-04-22 14:24:02'),
	(123, 1, '2014-04-22 15:04:29'),
	(127, 1, '2014-04-23 02:25:33');
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- Dumping data for table openfire.gp_event_comment: ~6 rows (approximately)
DELETE FROM `gp_event_comment`;
/*!40000 ALTER TABLE `gp_event_comment` DISABLE KEYS */;
INSERT INTO `gp_event_comment` (`id`, `content`, `userId`, `publishTime`, `eventId`, `parentId`) VALUES
	(9, 'funk you ', 33, '2014-03-18 09:26:52', 12, NULL),
	(10, 'funk you ', 33, '2014-03-18 09:27:23', 12, NULL),
	(11, 'love you too!', 33, '2014-03-18 09:37:07', 12, NULL),
	(12, 'funk you ', 33, '2014-04-05 13:54:36', 12, NULL),
	(13, 'funk you ', 33, '2014-04-05 14:06:15', 12, NULL),
	(14, 'funk you ', 33, '2014-04-09 22:24:10', 12, NULL);
/*!40000 ALTER TABLE `gp_event_comment` ENABLE KEYS */;


-- Dumping structure for table openfire.gp_event_invitation
DROP TABLE IF EXISTS `gp_event_invitation`;
CREATE TABLE IF NOT EXISTS `gp_event_invitation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eventId` int(11) DEFAULT NULL,
  `inviterId` int(11) DEFAULT NULL,
  `inviterMessage` varchar(255) DEFAULT NULL,
  `inviteeId` int(11) DEFAULT NULL,
  `inviteeMessage` varchar(255) DEFAULT NULL,
  `participance` varchar(10) DEFAULT NULL,
  `status` char(4) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.gp_event_invitation: ~0 rows (approximately)
DELETE FROM `gp_event_invitation`;
/*!40000 ALTER TABLE `gp_event_invitation` DISABLE KEYS */;
/*!40000 ALTER TABLE `gp_event_invitation` ENABLE KEYS */;


-- Dumping structure for table openfire.gp_event_member
DROP TABLE IF EXISTS `gp_event_member`;
CREATE TABLE IF NOT EXISTS `gp_event_member` (
  `eventId` int(10) NOT NULL,
  `userId` int(10) NOT NULL,
  `status` char(5) DEFAULT 'INIT' COMMENT 'INIT  AGREE REJECT',
  `admin` char(1) DEFAULT 'N',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`eventId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='attendees';

-- Dumping data for table openfire.gp_event_member: ~81 rows (approximately)
DELETE FROM `gp_event_member`;
/*!40000 ALTER TABLE `gp_event_member` DISABLE KEYS */;
INSERT INTO `gp_event_member` (`eventId`, `userId`, `status`, `admin`, `updateTime`) VALUES
	(28, 18, 'INIT', 'N', '2014-04-19 03:48:34'),
	(28, 19, 'INIT', 'N', '2014-04-19 03:48:34'),
	(29, 18, 'INIT', 'N', '2014-04-19 04:04:13'),
	(29, 19, 'INIT', 'N', '2014-04-19 04:04:13'),
	(30, 18, 'INIT', 'N', '2014-04-19 04:05:10'),
	(30, 19, 'INIT', 'N', '2014-04-19 04:05:10'),
	(30, 21, 'INIT', 'N', NULL),
	(30, 22, 'INIT', 'N', NULL),
	(30, 244, 'INIT', 'N', NULL),
	(31, 18, 'INIT', 'N', '2014-04-19 04:06:12'),
	(31, 19, 'INIT', 'N', '2014-04-19 04:06:12'),
	(31, 21, 'INIT', 'N', NULL),
	(31, 22, 'INIT', 'N', NULL),
	(31, 244, 'INIT', 'N', NULL),
	(32, 18, 'INIT', 'N', '2014-04-19 04:07:42'),
	(32, 19, 'INIT', 'N', '2014-04-19 04:07:42'),
	(32, 21, 'INIT', 'N', NULL),
	(32, 22, 'INIT', 'N', NULL),
	(32, 244, 'INIT', 'N', NULL),
	(33, 18, 'INIT', 'N', '2014-04-19 04:10:08'),
	(33, 19, 'INIT', 'N', '2014-04-19 04:10:08'),
	(33, 21, 'INIT', 'N', NULL),
	(33, 22, 'INIT', 'N', NULL),
	(33, 244, 'INIT', 'N', NULL),
	(34, 18, 'INIT', 'N', '2014-04-19 04:11:58'),
	(34, 19, 'INIT', 'N', '2014-04-19 04:11:58'),
	(35, 18, 'INIT', 'N', '2014-04-19 04:18:28'),
	(35, 19, 'INIT', 'N', '2014-04-19 04:18:28'),
	(37, 18, 'INIT', 'N', '2014-04-19 09:13:38'),
	(37, 19, 'INIT', 'N', '2014-04-19 09:13:38'),
	(38, 18, 'INIT', 'N', '2014-04-19 09:17:31'),
	(38, 19, 'INIT', 'N', '2014-04-19 09:17:31'),
	(40, 18, 'INIT', 'N', '2014-04-19 09:23:32'),
	(40, 19, 'INIT', 'N', '2014-04-19 09:23:32'),
	(42, 18, 'INIT', 'N', '2014-04-19 14:50:59'),
	(42, 19, 'INIT', 'N', '2014-04-19 14:50:59'),
	(43, 18, 'INIT', 'N', '2014-04-19 14:56:06'),
	(43, 19, 'INIT', 'N', '2014-04-19 14:56:06'),
	(44, 18, 'INIT', 'N', '2014-04-20 12:39:23'),
	(44, 19, 'INIT', 'N', '2014-04-20 12:39:23'),
	(44, 21, 'INIT', 'N', '2014-04-20 12:39:23'),
	(46, 18, 'INIT', 'N', '2014-04-20 12:42:22'),
	(46, 19, 'INIT', 'N', '2014-04-20 12:42:22'),
	(46, 21, 'INIT', 'N', '2014-04-20 12:42:22'),
	(49, 21, 'INIT', 'N', NULL),
	(50, 21, 'INIT', 'N', NULL),
	(50, 88, 'INIT', 'N', NULL),
	(51, 21, 'INIT', 'N', NULL),
	(51, 88, 'INIT', 'N', NULL),
	(52, 18, 'INIT', 'N', '2014-04-20 13:37:26'),
	(52, 19, 'INIT', 'N', '2014-04-20 13:37:26'),
	(52, 21, 'INIT', 'N', '2014-02-27 07:50:21'),
	(52, 88, 'INIT', 'N', '2014-02-27 07:50:21'),
	(59, 100, 'INIT', 'Y', '2014-04-21 14:14:17'),
	(60, 101, 'INIT', 'Y', '2014-04-21 14:15:22'),
	(61, 102, 'INIT', 'Y', '2014-04-21 14:21:00'),
	(62, 103, 'INIT', 'Y', '2014-04-21 14:22:08'),
	(76, 105, 'INIT', 'Y', '2014-04-22 02:43:09'),
	(77, 19, 'INIT', 'Y', '2014-04-22 02:44:31'),
	(78, 19, 'INIT', 'Y', '2014-04-22 02:45:58'),
	(79, 19, 'INIT', 'Y', '2014-04-22 02:52:20'),
	(80, 19, 'INIT', 'Y', '2014-04-22 03:04:56'),
	(94, 19, 'INIT', 'Y', '2014-04-22 06:21:00'),
	(96, 19, 'INIT', 'Y', '2014-04-22 06:25:50'),
	(99, 19, 'INIT', 'Y', '2014-04-22 06:52:35'),
	(100, 19, 'INIT', 'Y', '2014-04-22 06:54:45'),
	(114, 19, 'INIT', 'Y', '2014-04-22 09:48:27'),
	(117, 18, 'INIT', 'N', '2014-04-22 14:19:26'),
	(117, 19, 'INIT', 'N', '2014-04-22 14:19:26'),
	(117, 21, 'INIT', 'N', '2014-04-22 14:19:26'),
	(118, 18, 'INIT', 'N', '2014-04-22 14:23:18'),
	(118, 19, 'INIT', 'N', '2014-04-22 14:23:18'),
	(118, 21, 'INIT', 'N', '2014-04-22 14:23:18'),
	(119, 18, 'INIT', 'Y', '2014-04-22 14:24:02'),
	(119, 19, 'INIT', 'N', '2014-04-22 14:24:02'),
	(119, 21, 'INIT', 'N', '2014-04-22 14:24:02'),
	(123, 18, 'INIT', 'N', '2014-04-22 15:04:29'),
	(123, 19, 'INIT', 'N', '2014-04-22 15:04:29'),
	(127, 18, 'INIT', 'Y', '2014-04-23 02:25:33'),
	(127, 19, 'INIT', 'N', '2014-04-23 02:25:33'),
	(127, 21, 'INIT', 'N', '2014-04-23 02:25:33');
/*!40000 ALTER TABLE `gp_event_member` ENABLE KEYS */;


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


-- Dumping structure for table openfire.gp_friend_invitation
DROP TABLE IF EXISTS `gp_friend_invitation`;
CREATE TABLE IF NOT EXISTS `gp_friend_invitation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `inviterId` int(11) DEFAULT NULL,
  `inviterMessage` varchar(255) DEFAULT NULL,
  `inviteeId` int(11) DEFAULT NULL,
  `inviteeMessage` varchar(255) DEFAULT NULL,
  `acceptance` varchar(10) DEFAULT NULL,
  `status` char(4) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.gp_friend_invitation: ~5 rows (approximately)
DELETE FROM `gp_friend_invitation`;
/*!40000 ALTER TABLE `gp_friend_invitation` DISABLE KEYS */;
INSERT INTO `gp_friend_invitation` (`id`, `inviterId`, `inviterMessage`, `inviteeId`, `inviteeMessage`, `acceptance`, `status`, `updateTime`) VALUES
	(1, 98, '我是蔡虎', 97, 'fuck you!', 'Y', 'RESP', '2014-03-24 08:22:44'),
	(2, 33, '我是蔡虎', 98, 'fuck you!!', 'Y', 'INIT', '2014-03-25 09:03:27'),
	(3, 98, '我是蔡虎', 97, 'fuck you!!', 'Y', 'RESP', '2014-03-25 06:49:44'),
	(4, 98, '我是time', 97, NULL, 'N', 'RESP', '2014-03-24 09:58:07'),
	(5, 98, '我是time', 97, NULL, 'N', 'INIT', '2014-03-25 01:31:47');
/*!40000 ALTER TABLE `gp_friend_invitation` ENABLE KEYS */;


-- Dumping structure for table openfire.gp_group
DROP TABLE IF EXISTS `gp_group`;
CREATE TABLE IF NOT EXISTS `gp_group` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `ownerId` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.gp_group: ~15 rows (approximately)
DELETE FROM `gp_group`;
/*!40000 ALTER TABLE `gp_group` DISABLE KEYS */;
INSERT INTO `gp_group` (`id`, `name`, `ownerId`) VALUES
	(2, 'Boys', 33),
	(3, 'Girls', 33),
	(5, '3', 98),
	(9, 'private friend ', 98),
	(10, 'private friend ', 33),
	(11, 'private friend ', 33),
	(12, '', 33),
	(13, '', 33),
	(14, '', 33),
	(15, 'private girl friend ', 33),
	(16, 'private girl friend ', 98),
	(17, 'private friend ', 98),
	(18, ' ', 33),
	(19, ' girl friend 22', 98),
	(24, 'bb', 33);
/*!40000 ALTER TABLE `gp_group` ENABLE KEYS */;


-- Dumping structure for table openfire.gp_group_user
DROP TABLE IF EXISTS `gp_group_user`;
CREATE TABLE IF NOT EXISTS `gp_group_user` (
  `groupId` int(10) NOT NULL,
  `userId` int(10) NOT NULL,
  PRIMARY KEY (`groupId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.gp_group_user: ~11 rows (approximately)
DELETE FROM `gp_group_user`;
/*!40000 ALTER TABLE `gp_group_user` DISABLE KEYS */;
INSERT INTO `gp_group_user` (`groupId`, `userId`) VALUES
	(2, 92),
	(3, 92),
	(3, 96),
	(5, 93),
	(16, 33),
	(16, 97),
	(16, 98),
	(17, 33),
	(17, 97),
	(17, 98),
	(19, 98);
/*!40000 ALTER TABLE `gp_group_user` ENABLE KEYS */;


-- Dumping structure for table openfire.gp_moment
DROP TABLE IF EXISTS `gp_moment`;
CREATE TABLE IF NOT EXISTS `gp_moment` (
  `id` varchar(255) NOT NULL,
  `moment` varchar(255) DEFAULT NULL,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `visibility` int(11) DEFAULT NULL,
  `eventId` varchar(255) NOT NULL,
  `userId` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.gp_moment: ~4 rows (approximately)
DELETE FROM `gp_moment`;
/*!40000 ALTER TABLE `gp_moment` DISABLE KEYS */;
INSERT INTO `gp_moment` (`id`, `moment`, `updateTime`, `visibility`, `eventId`, `userId`) VALUES
	('3e0bc050-c80a-4eae-89d2-96c0e3a385ba', NULL, '2014-04-18 13:03:42', NULL, '10', '96'),
	('8c3d1270-f648-4723-b946-f0ccdc102dc4', '111', '2014-04-16 10:17:35', NULL, '1', '12'),
	('af1d0370-cbed-4652-82af-037abbaf5349', NULL, '2014-04-18 13:00:58', NULL, '10', '96'),
	('d1f054a8-1e94-4885-830c-a3abc3fece19', '111', '2014-04-16 09:58:08', NULL, '1', '12');
/*!40000 ALTER TABLE `gp_moment` ENABLE KEYS */;


-- Dumping structure for table openfire.gp_photo
DROP TABLE IF EXISTS `gp_photo`;
CREATE TABLE IF NOT EXISTS `gp_photo` (
  `id` varchar(255) NOT NULL,
  `format` varchar(255) DEFAULT NULL,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `momentId` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.gp_photo: ~3 rows (approximately)
DELETE FROM `gp_photo`;
/*!40000 ALTER TABLE `gp_photo` DISABLE KEYS */;
INSERT INTO `gp_photo` (`id`, `format`, `updateTime`, `momentId`) VALUES
	('52aac37d-7255-4828-840d-3bfa9b8c73f7', NULL, '2014-04-16 10:17:35', '8c3d1270-f648-4723-b946-f0ccdc102dc4'),
	('75b4c493-3967-428b-971f-eb03230a550c', 'JPEG', '2014-04-18 13:03:43', '3e0bc050-c80a-4eae-89d2-96c0e3a385ba'),
	('b2ea6c28-31fe-4071-921b-5d32be6ea94f', 'JPEG', '2014-04-18 13:00:58', 'af1d0370-cbed-4652-82af-037abbaf5349');
/*!40000 ALTER TABLE `gp_photo` ENABLE KEYS */;


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
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.gp_user: ~27 rows (approximately)
DELETE FROM `gp_user`;
/*!40000 ALTER TABLE `gp_user` DISABLE KEYS */;
INSERT INTO `gp_user` (`id`, `loginId`, `nickName`, `password`, `qq`, `birthdate`, `gender`, `location`, `phone`, `photo`, `signature`, `weChat`, `weibo`) VALUES
	(18, 'att212', 'att2', 'password', NULL, '2014-04-19 02:28:22', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(19, 'rer', 'ff', NULL, NULL, '2014-04-23 02:10:29', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(21, 'user5422', 'f11', 'f1password', NULL, '2014-04-23 02:10:26', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(33, 'chenb33', 'Chen, Bo', 'password', '1343243', '2014-03-22 08:54:47', 'M', '深圳', '13798594856', 'http://www.baidu.com/img/bdlogo.gif', 'life is good', 'wechat', '@weibo'),
	(34, 'user3534', 'f2', 'f2password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(35, 'chenb35', 'Bo', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(53, 'user5353', 'ahuuu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(60, 'user6060', 'f11', 'f1password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(61, 'user6161', 'f22', 'f2password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(62, 'user6262', 'ahuuu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(63, 'user6363', 'f1122', 'f1password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(64, 'user6464', 'ahuuu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(65, 'chenb65', 'Bo', 'password', '1343243', '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(89, 'chengc01789', 'chengc017', 'chengc017@', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(90, 'ahu90', 'ahuuu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(91, 'ahu91', 'ahuuu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(92, 'ahu92', 'ahuuu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(93, 'ahu93', 'ahuuu', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(94, 'yrdy4494', 'ahuuu', NULL, NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(95, 'ahub95', 'ahu-new', 'password', NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(96, 'openId--96', 'tokenId--', NULL, NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(97, 'openId--97', 'tokenId--', NULL, NULL, '2014-03-03 06:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(98, 'openId--', 'tokenId--', NULL, '329985729', '2014-03-03 06:42:01', NULL, '冈到了', '137985945', NULL, NULL, NULL, NULL),
	(100, NULL, 'ff', NULL, NULL, '2014-04-21 14:14:23', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(101, NULL, 'ff', NULL, NULL, '2014-04-21 14:15:29', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(102, NULL, 'ff', NULL, NULL, '2014-04-21 14:21:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(105, NULL, 'ffvv', NULL, NULL, '2014-04-22 02:43:10', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `gp_user` ENABLE KEYS */;


-- Dumping structure for table openfire.gp_user_friend
DROP TABLE IF EXISTS `gp_user_friend`;
CREATE TABLE IF NOT EXISTS `gp_user_friend` (
  `userId` int(10) NOT NULL,
  `friendId` int(10) NOT NULL,
  `status` char(6) DEFAULT 'INIT' COMMENT 'init  agree reject',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `remarkName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userId`,`friendId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table openfire.gp_user_friend: ~20 rows (approximately)
DELETE FROM `gp_user_friend`;
/*!40000 ALTER TABLE `gp_user_friend` DISABLE KEYS */;
INSERT INTO `gp_user_friend` (`userId`, `friendId`, `status`, `updateTime`, `remarkName`) VALUES
	(22, 99, 'NORMAL', NULL, NULL),
	(33, 34, 'NORMAL', '2014-03-03 07:19:01', 'yrtytry'),
	(33, 35, 'NORMAL', '2014-03-05 09:44:43', 'ss'),
	(33, 90, 'NORMAL', NULL, NULL),
	(33, 92, 'AGREE', '2014-04-05 13:54:49', 'Jim'),
	(49, 50, 'NORMAL', NULL, NULL),
	(53, 54, 'NORMAL', NULL, NULL),
	(53, 55, 'NORMAL', NULL, NULL),
	(72, 12, 'NORMAL', NULL, NULL),
	(76, 33, 'NORMAL', NULL, NULL),
	(83, 33, 'NORMAL', NULL, NULL),
	(84, 33, 'NORMAL', NULL, NULL),
	(88, 33, 'NORMAL', NULL, NULL),
	(90, 33, 'NORMAL', '2014-02-27 07:42:38', NULL),
	(91, 33, 'NORMAL', '2014-02-27 09:59:16', NULL),
	(92, 33, 'NORMAL', '2014-02-28 08:41:05', NULL),
	(98, 33, 'NORMAL', '2014-03-25 09:03:27', NULL),
	(98, 97, 'NORMAL', '2014-03-25 08:45:26', 'Tim'),
	(98, 98, 'NORMAL', '2014-03-25 08:49:18', NULL),
	(981, 33, 'NORMAL', '2014-03-03 06:33:04', 'NULL');
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
	(33, '12498a17-7960-4944-8a8c-4492c3dc6733', '2014-04-20 12:36:57', '2014-05-20 12:36:57'),
	(96, '98326219-4388-4692-a93f-f18b2c7a275e', '2014-03-03 06:35:50', '2014-04-02 06:35:50'),
	(97, '477bfabb-edfb-4d97-9048-3a550d0c845d', '2014-03-03 06:37:09', '2014-04-02 06:37:09'),
	(98, '4e8bb1e4-4fab-4c4e-9a9f-cf5ece4cc2aa', '2014-03-03 06:48:40', '2014-05-01 06:48:40');
/*!40000 ALTER TABLE `gp_user_token` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
