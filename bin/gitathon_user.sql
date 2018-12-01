-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 10, 2018 at 08:52 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `gitathon_user`
--

-- --------------------------------------------------------

--
-- Table structure for table `role_master`
--

CREATE TABLE IF NOT EXISTS `role_master` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `role_master`
--

INSERT INTO `role_master` (`role_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `role_name`) VALUES
(1, 1, '2018-08-10 23:28:42', NULL, NULL, 1, 'ADMIN'),
(2, 1, '2018-08-10 23:28:42', NULL, NULL, 1, 'REVIEWER'),
(3, 1, '2018-08-10 23:28:42', NULL, NULL, 1, 'DEVELOPER');

-- --------------------------------------------------------

--
-- Table structure for table `user_master`
--

CREATE TABLE IF NOT EXISTS `user_master` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `email_id` varchar(255) DEFAULT NULL,
  `employee_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `user_master`
--

INSERT INTO `user_master` (`user_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `email_id`, `employee_id`, `name`) VALUES
(1, 0, '2018-08-10 23:28:42', NULL, NULL, 1, 'Avirup.pal@cognizant.com', '702534', 'Avirup Pal');

-- --------------------------------------------------------

--
-- Table structure for table `user_vs_role`
--

CREATE TABLE IF NOT EXISTS `user_vs_role` (
  `user_vs_role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `role_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_vs_role_id`),
  KEY `FK9s1g0kn4opwky2f32vxt9lrai` (`role_id`),
  KEY `FK8b87cnjkydh9emno84u8lc2cr` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `user_vs_role`
--

INSERT INTO `user_vs_role` (`user_vs_role_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `role_id`, `user_id`) VALUES
(1, 1, '2018-08-10 23:43:38', NULL, NULL, 1, 1, 1),
(2, 1, '2018-08-10 23:43:38', NULL, NULL, 1, 2, 1),
(3, 1, '2018-08-10 23:43:38', NULL, NULL, 1, 3, 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `user_vs_role`
--
ALTER TABLE `user_vs_role`
  ADD CONSTRAINT `FK8b87cnjkydh9emno84u8lc2cr` FOREIGN KEY (`user_id`) REFERENCES `user_master` (`user_id`),
  ADD CONSTRAINT `FK9s1g0kn4opwky2f32vxt9lrai` FOREIGN KEY (`role_id`) REFERENCES `role_master` (`role_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
