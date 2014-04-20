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

DROP TABLE IF EXISTS `gp_friend_invitation`;
CREATE TABLE `gp_friend_invitation` (
  `id` int(11) NOT NULL auto_increment,
  `inviterId` int(11) default NULL,
  `inviterMessage` varchar(255) default NULL,
  `inviteeId` int(11) default NULL,
  `inviteeMessage` varchar(255) default NULL,
  `acceptance` varchar(10) default NULL,
  `status` char(4) default NULL,
  `updateTime` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `gp_event_invitation`;
CREATE TABLE `gp_event_invitation` (
  `id` int(11) NOT NULL auto_increment,
  `eventId` int(11) default NULL,
  `inviterId` int(11) default NULL,
  `inviterMessage` varchar(255) default NULL,
  `inviteeId` int(11) default NULL,
  `inviteeMessage` varchar(255) default NULL,
  `participance` varchar(10) default NULL,
  `status` char(4) default NULL,
  `updateTime` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `gp_event_application`;
CREATE TABLE `gp_event_application` (
  `id` int(11) NOT NULL auto_increment,
  `eventId` int(11) default NULL,
  `applicantId` int(11) default NULL,
  `applicantMessage` varchar(255) default NULL,
  `adminIds` varchar(255) default NULL,
  `approverId` int(11) default NULL,
  `approverMessage` varchar(255) default NULL,
  `approval` varchar(10) default NULL,
  `status` char(4) default NULL,
  `updateTime` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table openfire.gp_event_attendee
DROP TABLE IF EXISTS `gp_event_attendee`;
CREATE TABLE IF NOT EXISTS `gp_event_attendee` (
  `eventId` int(10) NOT NULL,
  `userId` int(10) NOT NULL,
  `status` char(5) DEFAULT 'INIT' COMMENT 'INIT  AGREE REJECT',
  `admin` char(1) DEFAULT 'N',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`eventId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='attendees';

-- Data exporting was unselected.


-- Dumping structure for table openfire.gp_category
DROP TABLE IF EXISTS `gp_category`;
CREATE TABLE IF NOT EXISTS `gp_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
DROP TABLE IF EXISTS `gp_event_category`;
CREATE TABLE IF NOT EXISTS `gp_event_category` (
  `eventId` int(10) NOT NULL,
  `cateId` int(10) NOT NULL,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping structure for table openfire.gp_event_message
DROP TABLE IF EXISTS `gp_event_message`;
CREATE TABLE IF NOT EXISTS `gp_event_message` (
  `id` int(10) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL COMMENT '1-system message 2 - user message',
  `userId` int(11) DEFAULT NULL,
  `publishTime` datetime DEFAULT NULL,
  `eventId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table openfire.gp_group
DROP TABLE IF EXISTS `gp_group`;
CREATE TABLE IF NOT EXISTS `gp_group` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `ownerId` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table openfire.gp_group_user
DROP TABLE IF EXISTS `gp_group_user`;
CREATE TABLE IF NOT EXISTS `gp_group_user` (
  `groupId` int(10) NOT NULL,
  `userId` int(10) NOT NULL,
  PRIMARY KEY (`groupId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table openfire.gp_user_friend
DROP TABLE IF EXISTS `gp_user_friend`;
CREATE TABLE IF NOT EXISTS `gp_user_friend` (
  `userId` int(10) NOT NULL,
  `friendId` int(10) NOT NULL,
  `status` char(6) DEFAULT 'NORMAL' COMMENT 'NORMAL, BLACK',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `remarkName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userId`,`friendId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table openfire.gp_user_token
DROP TABLE IF EXISTS `gp_user_token`;
CREATE TABLE IF NOT EXISTS `gp_user_token` (
  `userId` int(10) NOT NULL,
  `token` varchar(50) DEFAULT NULL,
  `applyTime` datetime DEFAULT NULL,
  `expireTime` datetime DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;


DROP TABLE IF EXISTS `gp_moment`;
CREATE TABLE IF NOT EXISTS `gp_moment` (
  `id` char(36) NOT NULL,
  `eventId` int(11) NOT NULL,
  `userId` int(10) NOT NULL,
  `moment` varchar(512) DEFAULT NULL,
  `visibility` tinyint(4) DEFAULT NULL,
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `gp_photo`;
CREATE TABLE IF NOT EXISTS `gp_photo` (
  `id` char(36) NOT NULL,
  `momentId` char(36) NOT NULL,
  `format` varchar(32) NOT NULL,
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
