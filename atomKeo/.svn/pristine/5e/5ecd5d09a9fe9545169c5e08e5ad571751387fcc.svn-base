-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               10.0.19-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for atomcoredemo
CREATE DATABASE IF NOT EXISTS `atomcoredemo` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `atomcoredemo`;


-- Dumping structure for table atomcoredemo.notifications
CREATE TABLE IF NOT EXISTS `notifications` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `subject` varchar(100) DEFAULT NULL,
  `template_path` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table atomcoredemo.notifications: ~0 rows (approximately)
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` (`id`, `name`, `type`, `subject`, `template_path`) VALUES
	(1, 'SUBSCRIPTION_REQUEST_SUCCESS_EMAIL', 'email', 'Subscription Notification', 'templates/SUBSCRIPTION_REQUEST_SUCCESS_EMAIL.vm');
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
