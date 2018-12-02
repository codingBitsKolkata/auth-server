-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 02, 2018 at 09:55 AM
-- Server version: 5.7.9
-- PHP Version: 5.6.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ora_auth`
--

-- --------------------------------------------------------

--
-- Table structure for table `host_vs_account`
--

DROP TABLE IF EXISTS `host_vs_account`;
CREATE TABLE IF NOT EXISTS `host_vs_account` (
  `host_vs_account_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `account_holder_name` varchar(255) DEFAULT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `account_type` varchar(255) DEFAULT NULL,
  `bank_name` varchar(255) DEFAULT NULL,
  `branch_name` varchar(255) DEFAULT NULL,
  `ifsc_code` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`host_vs_account_id`),
  KEY `FK2o57av88b996lc7tnhyqoukv6` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `login_details`
--

DROP TABLE IF EXISTS `login_details`;
CREATE TABLE IF NOT EXISTS `login_details` (
  `login_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `device_id` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`login_id`),
  KEY `FKqd5giregrap11ay6cckx328pd` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `master_country`
--

DROP TABLE IF EXISTS `master_country`;
CREATE TABLE IF NOT EXISTS `master_country` (
  `country_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `country_code` varchar(255) DEFAULT NULL,
  `country_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`country_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `master_identity`
--

DROP TABLE IF EXISTS `master_identity`;
CREATE TABLE IF NOT EXISTS `master_identity` (
  `identity_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`identity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `master_language`
--

DROP TABLE IF EXISTS `master_language`;
CREATE TABLE IF NOT EXISTS `master_language` (
  `language_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`language_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `master_user`
--

DROP TABLE IF EXISTS `master_user`;
CREATE TABLE IF NOT EXISTS `master_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `email_id` varchar(255) DEFAULT NULL,
  `is_email_verified` varchar(255) DEFAULT NULL,
  `is_mobile_verified` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `otp` varchar(255) DEFAULT NULL,
  `country_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK4d8m97w9ng2808inw7l46sn7d` (`country_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `master_user_type`
--

DROP TABLE IF EXISTS `master_user_type`;
CREATE TABLE IF NOT EXISTS `master_user_type` (
  `user_type_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `user_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_activity`
--

DROP TABLE IF EXISTS `user_activity`;
CREATE TABLE IF NOT EXISTS `user_activity` (
  `activity_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `page_visit` varchar(255) DEFAULT NULL,
  `property_visit` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`activity_id`),
  KEY `FK171cx8gsppqvvek5wmo5ptj8p` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_vs_identity`
--

DROP TABLE IF EXISTS `user_vs_identity`;
CREATE TABLE IF NOT EXISTS `user_vs_identity` (
  `user_vs_identity_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `file_url` varchar(255) DEFAULT NULL,
  `identity_number` varchar(255) DEFAULT NULL,
  `identity_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_vs_identity_id`),
  KEY `FKgn2s2vtytbivavyqt0gq84dw` (`identity_id`),
  KEY `FK3o7y8u4q8nngu0fr2qu04eaq3` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_vs_info`
--

DROP TABLE IF EXISTS `user_vs_info`;
CREATE TABLE IF NOT EXISTS `user_vs_info` (
  `user_vs_info_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `alt_phno` varchar(255) DEFAULT NULL,
  `bio_info` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `dob` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `no_show_count` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_vs_info_id`),
  KEY `FKlacsctiii11ywrult2cjkn6jj` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_vs_language`
--

DROP TABLE IF EXISTS `user_vs_language`;
CREATE TABLE IF NOT EXISTS `user_vs_language` (
  `user_vs_language_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `language_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_vs_language_id`),
  KEY `FK401kpkgbcgyrk4u38xwtb30tn` (`language_id`),
  KEY `FKqau9oea4uq3lp7ey0k1n3svk5` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_vs_type`
--

DROP TABLE IF EXISTS `user_vs_type`;
CREATE TABLE IF NOT EXISTS `user_vs_type` (
  `user_vs_type_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  `user_type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_vs_type_id`),
  KEY `FK1h58kqthty04ghr9pu33kmxcn` (`user_id`),
  KEY `FKtp07okoa2i3ahq5ywwgt4aygk` (`user_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `host_vs_account`
--
ALTER TABLE `host_vs_account`
  ADD CONSTRAINT `FK2o57av88b996lc7tnhyqoukv6` FOREIGN KEY (`user_id`) REFERENCES `master_user` (`user_id`);

--
-- Constraints for table `login_details`
--
ALTER TABLE `login_details`
  ADD CONSTRAINT `FKqd5giregrap11ay6cckx328pd` FOREIGN KEY (`user_id`) REFERENCES `master_user` (`user_id`);

--
-- Constraints for table `master_user`
--
ALTER TABLE `master_user`
  ADD CONSTRAINT `FK4d8m97w9ng2808inw7l46sn7d` FOREIGN KEY (`country_id`) REFERENCES `master_country` (`country_id`);

--
-- Constraints for table `user_activity`
--
ALTER TABLE `user_activity`
  ADD CONSTRAINT `FK171cx8gsppqvvek5wmo5ptj8p` FOREIGN KEY (`user_id`) REFERENCES `master_user` (`user_id`);

--
-- Constraints for table `user_vs_identity`
--
ALTER TABLE `user_vs_identity`
  ADD CONSTRAINT `FK3o7y8u4q8nngu0fr2qu04eaq3` FOREIGN KEY (`user_id`) REFERENCES `master_user` (`user_id`),
  ADD CONSTRAINT `FKgn2s2vtytbivavyqt0gq84dw` FOREIGN KEY (`identity_id`) REFERENCES `master_identity` (`identity_id`);

--
-- Constraints for table `user_vs_info`
--
ALTER TABLE `user_vs_info`
  ADD CONSTRAINT `FKlacsctiii11ywrult2cjkn6jj` FOREIGN KEY (`user_id`) REFERENCES `master_user` (`user_id`);

--
-- Constraints for table `user_vs_language`
--
ALTER TABLE `user_vs_language`
  ADD CONSTRAINT `FK401kpkgbcgyrk4u38xwtb30tn` FOREIGN KEY (`language_id`) REFERENCES `master_language` (`language_id`),
  ADD CONSTRAINT `FKqau9oea4uq3lp7ey0k1n3svk5` FOREIGN KEY (`user_id`) REFERENCES `master_user` (`user_id`);

--
-- Constraints for table `user_vs_type`
--
ALTER TABLE `user_vs_type`
  ADD CONSTRAINT `FK1h58kqthty04ghr9pu33kmxcn` FOREIGN KEY (`user_id`) REFERENCES `master_user` (`user_id`),
  ADD CONSTRAINT `FKtp07okoa2i3ahq5ywwgt4aygk` FOREIGN KEY (`user_type_id`) REFERENCES `master_user_type` (`user_type_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
