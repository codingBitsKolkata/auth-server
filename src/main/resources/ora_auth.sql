-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 22, 2018 at 04:51 PM
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
-- Table structure for table `host_vs_domain`
--

DROP TABLE IF EXISTS `host_vs_domain`;
CREATE TABLE IF NOT EXISTS `host_vs_domain` (
  `host_dom_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `domain_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`host_dom_id`),
  KEY `FK1lrjojvnmwqcriscfw846j7ob` (`domain_id`),
  KEY `FKl0lojlgub38279xp5nvrx08d6` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `host_vs_interest`
--

DROP TABLE IF EXISTS `host_vs_interest`;
CREATE TABLE IF NOT EXISTS `host_vs_interest` (
  `host_int_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `interest_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`host_int_id`),
  KEY `FKtrdc6uapmgktoc7oc941ii720` (`interest_id`),
  KEY `FKtn74a3lr5oeq23rwf3jh17280` (`user_id`)
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
  `session_token` varchar(255) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=240 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_country`
--

INSERT INTO `master_country` (`country_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `country_code`, `country_name`) VALUES
(1, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+93', 'AFGHANISTAN'),
(2, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+355', 'ALBANIA'),
(3, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+213', 'ALGERIA'),
(4, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+1684', 'AMERICAN SAMOA'),
(5, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+376', 'ANDORRA'),
(6, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+244', 'ANGOLA'),
(7, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+1264', 'ANGUILLA'),
(8, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+0', 'ANTARCTICA'),
(9, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+1268', 'ANTIGUA AND BARBUDA'),
(10, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+54', 'ARGENTINA'),
(11, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+374', 'ARMENIA'),
(12, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+297', 'ARUBA'),
(13, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+61', 'AUSTRALIA'),
(14, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+43', 'AUSTRIA'),
(15, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+994', 'AZERBAIJAN'),
(16, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+1242', 'BAHAMAS'),
(17, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+973', 'BAHRAIN'),
(18, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+880', 'BANGLADESH'),
(19, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+1246', 'BARBADOS'),
(20, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+375', 'BELARUS'),
(21, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+32', 'BELGIUM'),
(22, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+501', 'BELIZE'),
(23, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+229', 'BENIN'),
(24, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+1441', 'BERMUDA'),
(25, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+975', 'BHUTAN'),
(26, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+591', 'BOLIVIA'),
(27, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+387', 'BOSNIA AND HERZEGOVINA'),
(28, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+267', 'BOTSWANA'),
(29, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+0', 'BOUVET ISLAND'),
(30, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+55', 'BRAZIL'),
(31, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+246', 'BRITISH INDIAN OCEAN TERRITORY'),
(32, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+673', 'BRUNEI DARUSSALAM'),
(33, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+359', 'BULGARIA'),
(34, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+226', 'BURKINA FASO'),
(35, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+257', 'BURUNDI'),
(36, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+855', 'CAMBODIA'),
(37, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+237', 'CAMEROON'),
(38, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+1', 'CANADA'),
(39, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+238', 'CAPE VERDE'),
(40, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+1345', 'CAYMAN ISLANDS'),
(41, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+236', 'CENTRAL AFRICAN REPUBLIC'),
(42, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+235', 'CHAD'),
(43, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+56', 'CHILE'),
(44, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+86', 'CHINA'),
(45, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+61', 'CHRISTMAS ISLAND'),
(46, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+672', 'COCOS (KEELING) ISLANDS'),
(47, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+57', 'COLOMBIA'),
(48, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+269', 'COMOROS'),
(49, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+242', 'CONGO'),
(50, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+242', 'CONGO, THE DEMOCRATIC REPUBLIC OF THE'),
(51, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+682', 'COOK ISLANDS'),
(52, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+506', 'COSTA RICA'),
(53, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+225', 'COTE D''IVOIRE'),
(54, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+385', 'CROATIA'),
(55, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+53', 'CUBA'),
(56, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+357', 'CYPRUS'),
(57, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+420', 'CZECH REPUBLIC'),
(58, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+45', 'DENMARK'),
(59, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+253', 'DJIBOUTI'),
(60, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+1767', 'DOMINICA'),
(61, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+1809', 'DOMINICAN REPUBLIC'),
(62, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+593', 'ECUADOR'),
(63, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+20', 'EGYPT'),
(64, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+503', 'EL SALVADOR'),
(65, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+240', 'EQUATORIAL GUINEA'),
(66, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+291', 'ERITREA'),
(67, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+372', 'ESTONIA'),
(68, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+251', 'ETHIOPIA'),
(69, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+500', 'FALKLAND ISLANDS (MALVINAS)'),
(70, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+298', 'FAROE ISLANDS'),
(71, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+679', 'FIJI'),
(72, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+358', 'FINLAND'),
(73, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+33', 'FRANCE'),
(74, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+594', 'FRENCH GUIANA'),
(75, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+689', 'FRENCH POLYNESIA'),
(76, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+0', 'FRENCH SOUTHERN TERRITORIES'),
(77, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+241', 'GABON'),
(78, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+220', 'GAMBIA'),
(79, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+995', 'GEORGIA'),
(80, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+49', 'GERMANY'),
(81, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+233', 'GHANA'),
(82, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+350', 'GIBRALTAR'),
(83, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+30', 'GREECE'),
(84, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+299', 'GREENLAND'),
(85, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+1473', 'GRENADA'),
(86, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+590', 'GUADELOUPE'),
(87, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+1671', 'GUAM'),
(88, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+502', 'GUATEMALA'),
(89, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+224', 'GUINEA'),
(90, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+245', 'GUINEA-BISSAU'),
(91, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+592', 'GUYANA'),
(92, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+509', 'HAITI'),
(93, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+0', 'HEARD ISLAND AND MCDONALD ISLANDS'),
(94, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+39', 'HOLY SEE (VATICAN CITY STATE)'),
(95, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+504', 'HONDURAS'),
(96, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+852', 'HONG KONG'),
(97, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+36', 'HUNGARY'),
(98, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+354', 'ICELAND'),
(99, 1, '2018-12-15 01:27:34', NULL, NULL, 1, '+91', 'INDIA'),
(100, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+62', 'INDONESIA'),
(101, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+98', 'IRAN, ISLAMIC REPUBLIC OF'),
(102, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+964', 'IRAQ'),
(103, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+353', 'IRELAND'),
(104, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+972', 'ISRAEL'),
(105, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+39', 'ITALY'),
(106, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+1876', 'JAMAICA'),
(107, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+81', 'JAPAN'),
(108, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+962', 'JORDAN'),
(109, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+7', 'KAZAKHSTAN'),
(110, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+254', 'KENYA'),
(111, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+686', 'KIRIBATI'),
(112, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+850', 'KOREA, DEMOCRATIC PEOPLE''S REPUBLIC OF'),
(113, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+82', 'KOREA, REPUBLIC OF'),
(114, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+965', 'KUWAIT'),
(115, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+996', 'KYRGYZSTAN'),
(116, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+856', 'LAO PEOPLE''S DEMOCRATIC REPUBLIC'),
(117, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+371', 'LATVIA'),
(118, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+961', 'LEBANON'),
(119, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+266', 'LESOTHO'),
(120, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+231', 'LIBERIA'),
(121, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+218', 'LIBYAN ARAB JAMAHIRIYA'),
(122, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+423', 'LIECHTENSTEIN'),
(123, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+370', 'LITHUANIA'),
(124, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+352', 'LUXEMBOURG'),
(125, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+853', 'MACAO'),
(126, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+389', 'MACEDONIA, THE FORMER YUGOSLAV REPUBLIC OF'),
(127, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+261', 'MADAGASCAR'),
(128, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+265', 'MALAWI'),
(129, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+60', 'MALAYSIA'),
(130, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+960', 'MALDIVES'),
(131, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+223', 'MALI'),
(132, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+356', 'MALTA'),
(133, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+692', 'MARSHALL ISLANDS'),
(134, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+596', 'MARTINIQUE'),
(135, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+222', 'MAURITANIA'),
(136, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+230', 'MAURITIUS'),
(137, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+269', 'MAYOTTE'),
(138, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+52', 'MEXICO'),
(139, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+691', 'MICRONESIA, FEDERATED STATES OF'),
(140, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+373', 'MOLDOVA, REPUBLIC OF'),
(141, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+377', 'MONACO'),
(142, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+976', 'MONGOLIA'),
(143, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+1664', 'MONTSERRAT'),
(144, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+212', 'MOROCCO'),
(145, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+258', 'MOZAMBIQUE'),
(146, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+95', 'MYANMAR'),
(147, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+264', 'NAMIBIA'),
(148, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+674', 'NAURU'),
(149, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+977', 'NEPAL'),
(150, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+31', 'NETHERLANDS'),
(151, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+599', 'NETHERLANDS ANTILLES'),
(152, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+687', 'NEW CALEDONIA'),
(153, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+64', 'NEW ZEALAND'),
(154, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+505', 'NICARAGUA'),
(155, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+227', 'NIGER'),
(156, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+234', 'NIGERIA'),
(157, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+683', 'NIUE'),
(158, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+672', 'NORFOLK ISLAND'),
(159, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+1670', 'NORTHERN MARIANA ISLANDS'),
(160, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+47', 'NORWAY'),
(161, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+968', 'OMAN'),
(162, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+92', 'PAKISTAN'),
(163, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+680', 'PALAU'),
(164, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+970', 'PALESTINIAN TERRITORY, OCCUPIED'),
(165, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+507', 'PANAMA'),
(166, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+675', 'PAPUA NEW GUINEA'),
(167, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+595', 'PARAGUAY'),
(168, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+51', 'PERU'),
(169, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+63', 'PHILIPPINES'),
(170, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+0', 'PITCAIRN'),
(171, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+48', 'POLAND'),
(172, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+351', 'PORTUGAL'),
(173, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+1787', 'PUERTO RICO'),
(174, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+974', 'QATAR'),
(175, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+262', 'REUNION'),
(176, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+40', 'ROMANIA'),
(177, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+70', 'RUSSIAN FEDERATION'),
(178, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+250', 'RWANDA'),
(179, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+290', 'SAINT HELENA'),
(180, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+1869', 'SAINT KITTS AND NEVIS'),
(181, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+1758', 'SAINT LUCIA'),
(182, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+508', 'SAINT PIERRE AND MIQUELON'),
(183, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+1784', 'SAINT VINCENT AND THE GRENADINES'),
(184, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+684', 'SAMOA'),
(185, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+378', 'SAN MARINO'),
(186, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+239', 'SAO TOME AND PRINCIPE'),
(187, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+966', 'SAUDI ARABIA'),
(188, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+221', 'SENEGAL'),
(189, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+381', 'SERBIA AND MONTENEGRO'),
(190, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+248', 'SEYCHELLES'),
(191, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+232', 'SIERRA LEONE'),
(192, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+65', 'SINGAPORE'),
(193, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+421', 'SLOVAKIA'),
(194, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+386', 'SLOVENIA'),
(195, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+677', 'SOLOMON ISLANDS'),
(196, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+252', 'SOMALIA'),
(197, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+27', 'SOUTH AFRICA'),
(198, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+0', 'SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS'),
(199, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+34', 'SPAIN'),
(200, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+94', 'SRI LANKA'),
(201, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+249', 'SUDAN'),
(202, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+597', 'SURINAME'),
(203, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+47', 'SVALBARD AND JAN MAYEN'),
(204, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+268', 'SWAZILAND'),
(205, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+46', 'SWEDEN'),
(206, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+41', 'SWITZERLAND'),
(207, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+963', 'SYRIAN ARAB REPUBLIC'),
(208, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+886', 'TAIWAN, PROVINCE OF CHINA'),
(209, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+992', 'TAJIKISTAN'),
(210, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+255', 'TANZANIA, UNITED REPUBLIC OF'),
(211, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+66', 'THAILAND'),
(212, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+670', 'TIMOR-LESTE'),
(213, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+228', 'TOGO'),
(214, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+690', 'TOKELAU'),
(215, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+676', 'TONGA'),
(216, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+1868', 'TRINIDAD AND TOBAGO'),
(217, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+216', 'TUNISIA'),
(218, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+90', 'TURKEY'),
(219, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+7370', 'TURKMENISTAN'),
(220, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+1649', 'TURKS AND CAICOS ISLANDS'),
(221, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+688', 'TUVALU'),
(222, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+256', 'UGANDA'),
(223, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+380', 'UKRAINE'),
(224, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+971', 'UNITED ARAB EMIRATES'),
(225, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+44', 'UNITED KINGDOM'),
(226, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+1', 'UNITED STATES'),
(227, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+1', 'UNITED STATES MINOR OUTLYING ISLANDS'),
(228, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+598', 'URUGUAY'),
(229, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+998', 'UZBEKISTAN'),
(230, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+678', 'VANUATU'),
(231, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+58', 'VENEZUELA'),
(232, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+84', 'VIET NAM'),
(233, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+1284', 'VIRGIN ISLANDS, BRITISH'),
(234, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+1340', 'VIRGIN ISLANDS, U.S.'),
(235, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+681', 'WALLIS AND FUTUNA'),
(236, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+212', 'WESTERN SAHARA'),
(237, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+967', 'YEMEN'),
(238, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+260', 'ZAMBIA'),
(239, 1, '2018-12-15 01:27:34', NULL, NULL, 2, '+263', 'ZIMBABWE');

-- --------------------------------------------------------

--
-- Table structure for table `master_domain`
--

DROP TABLE IF EXISTS `master_domain`;
CREATE TABLE IF NOT EXISTS `master_domain` (
  `domain_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `domain_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`domain_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_domain`
--

INSERT INTO `master_domain` (`domain_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `domain_name`) VALUES
(1, 1, '2018-12-15 01:27:34', NULL, NULL, 1, 'Doctor'),
(2, 1, '2018-12-15 01:27:34', NULL, NULL, 1, 'Painter');

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
-- Table structure for table `master_interest`
--

DROP TABLE IF EXISTS `master_interest`;
CREATE TABLE IF NOT EXISTS `master_interest` (
  `interest_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `interest_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`interest_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_interest`
--

INSERT INTO `master_interest` (`interest_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `interest_name`) VALUES
(1, 1, '2018-12-15 01:27:34', NULL, NULL, 1, 'Singing'),
(2, 1, '2018-12-15 01:27:34', NULL, NULL, 1, 'Acting');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_language`
--

INSERT INTO `master_language` (`language_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `description`) VALUES
(1, 1, '2018-12-15 01:27:34', NULL, NULL, 1, 'English');

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
  `email_otp` varchar(255) DEFAULT NULL,
  `mobile_otp` varchar(255) DEFAULT NULL,
  `email_otp_validity` varchar(255) DEFAULT NULL,
  `mobile_otp_validity` varchar(255) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_user_type`
--

INSERT INTO `master_user_type` (`user_type_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `user_type`) VALUES
(1, 1, '2018-12-15 01:27:34', NULL, NULL, 1, 'Admin'),
(2, 1, '2018-12-15 01:27:34', NULL, NULL, 1, 'Customer'),
(3, 1, '2018-12-15 01:27:34', NULL, NULL, 1, 'Host');

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
-- Constraints for table `host_vs_domain`
--
ALTER TABLE `host_vs_domain`
  ADD CONSTRAINT `FK1lrjojvnmwqcriscfw846j7ob` FOREIGN KEY (`domain_id`) REFERENCES `master_domain` (`domain_id`),
  ADD CONSTRAINT `FKl0lojlgub38279xp5nvrx08d6` FOREIGN KEY (`user_id`) REFERENCES `master_user` (`user_id`);

--
-- Constraints for table `host_vs_interest`
--
ALTER TABLE `host_vs_interest`
  ADD CONSTRAINT `FKtn74a3lr5oeq23rwf3jh17280` FOREIGN KEY (`user_id`) REFERENCES `master_user` (`user_id`),
  ADD CONSTRAINT `FKtrdc6uapmgktoc7oc941ii720` FOREIGN KEY (`interest_id`) REFERENCES `master_interest` (`interest_id`);

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
