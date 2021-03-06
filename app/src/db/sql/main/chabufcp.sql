-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 18, 2016 at 09:18 PM
-- Server version: 10.1.10-MariaDB
-- PHP Version: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `chabufcp`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `addTable` (IN `table_num` INT, IN `cus_num` INT, OUT `table_id` INT, IN `respons` INT)  BEGIN
DECLARE found_check int;
SELECT HISHDRTBLNO INTO found_check
FROM histrnshdr WHERE HISHDRTBLNO = table_num AND HISTRNSTAT = 'OPEN';

IF found_check IS NULL THEN
INSERT INTO  histrnshdr (HISHDRTBLNO,HISTRNCUS,HISHDRTOTALPRICE,HISHDRRESPON,HISHDRTIMEEAT) VALUES(table_num,cus_num,cus_num*239,respons,CONCAT(DATE_FORMAT(CURRENT_TIME(),'%H:%i'),' - ',DATE_FORMAT(DATE_ADD(CURRENT_TIME(),INTERVAL 2 HOUR),'%H:%i') ));
SET table_id = LAST_INSERT_ID();

ELSE SET table_id = 0; 
END IF;

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `ADMID` int(11) NOT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `PWD` varchar(255) DEFAULT NULL,
  `FNAME` varchar(255) DEFAULT NULL,
  `LNAME` varchar(255) DEFAULT NULL,
  `POSITION` varchar(255) DEFAULT NULL,
  `SEX` int(1) NOT NULL COMMENT '0=male,1=female'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`ADMID`, `USERNAME`, `PWD`, `FNAME`, `LNAME`, `POSITION`, `SEX`) VALUES
(1, 'admin', 'admin', 'admin', 'admin', 'Admin', 1),
(2, 'arm', '123456', 'Nattachai', 'Summart', 'Manager', 0),
(3, 'big', '123456', 'KuyBig', 'ChengSus', 'Customer', 0);

-- --------------------------------------------------------

--
-- Table structure for table `histrnsdtl`
--

CREATE TABLE `histrnsdtl` (
  `HISDTLID` int(11) NOT NULL,
  `HISDTLHDRID` int(11) NOT NULL,
  `HISDTLITMID` int(11) NOT NULL,
  `HISDTLQTY` int(4) NOT NULL,
  `HISDTLPRICE` int(4) DEFAULT NULL,
  `HISDTLRESPON` int(11) NOT NULL,
  `HISTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `HISDTLSTAT` int(1) NOT NULL DEFAULT '1' COMMENT '1=doing,2=did'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `histrnshdr`
--

CREATE TABLE `histrnshdr` (
  `HISHDRID` int(11) NOT NULL,
  `HISHDRTBLNO` int(3) NOT NULL,
  `HISTRNCUS` int(7) NOT NULL,
  `HISHDRTOTALPRICE` int(7) DEFAULT NULL,
  `HISTRNSTAT` varchar(6) NOT NULL DEFAULT 'OPEN',
  `HISHDRRESPON` int(11) NOT NULL,
  `HISHDRTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `HISHDRTIMEEAT` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `histrnshdr`
--

INSERT INTO `histrnshdr` (`HISHDRID`, `HISHDRTBLNO`, `HISTRNCUS`, `HISHDRTOTALPRICE`, `HISTRNSTAT`, `HISHDRRESPON`, `HISHDRTIME`, `HISHDRTIMEEAT`) VALUES
(1, 2, 5, 1195, 'OPEN', 1, '2016-05-16 16:42:16', '23:42 - 01:42'),
(2, 6, 19, 4541, 'OPEN', 1, '2016-05-17 09:28:41', '16:28 - 18:28');

-- --------------------------------------------------------

--
-- Table structure for table `itmgnl`
--

CREATE TABLE `itmgnl` (
  `ITMID` int(11) NOT NULL,
  `ITMNAME` varchar(255) NOT NULL,
  `ITMQTY` int(7) DEFAULT NULL,
  `ITMTYPE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `itmgnl`
--

INSERT INTO `itmgnl` (`ITMID`, `ITMNAME`, `ITMQTY`, `ITMTYPE`) VALUES
(1, 'เนื้อวัว', 99, 1),
(2, 'หมูเด้ง', 99, 2),
(3, 'สันคอหมู', 99, 2),
(4, 'หมูพริกไทยดำ', 99, 2),
(5, 'หมูสามชั้น', 99, 2),
(6, 'เบคอน', 99, 2),
(7, 'สันนอกหมู', 99, 2),
(8, 'ตับหมู', 99, 2),
(9, 'หัวใจหมู', 99, 2),
(10, 'อกไก่', 99, 3),
(11, 'น่องไก่', 99, 3),
(12, 'ปีกไก่', 99, 3),
(13, 'หัวใจไก่', 99, 3),
(14, 'กุ้งแม่น้ำ', 99, 4),
(15, 'หมึกกล้วย', 99, 4),
(16, 'ปลาดอลี่', 99, 4),
(17, 'ปลาแซลม่อน', 99, 4),
(18, 'แครอท', 99, 5),
(19, 'ผักกาดขาว', 99, 5),
(20, 'ข้าวโพด', 99, 5),
(21, 'ผักบุ้ง', 99, 5),
(22, 'เห็ดออรินจิ', 99, 5),
(23, 'เห็ดเข็มทอง', 99, 5),
(24, 'เห็ดนางฟ้า', 99, 5),
(25, 'วนิลา', 99, 6),
(26, 'มะนาว', 99, 6),
(27, 'ช็อกโกแลต', 99, 6),
(28, 'ชาเขียว', 99, 6),
(29, 'สตอเบอรี่', 99, 6),
(30, 'ไข่กุ้ง', 99, 7),
(31, 'ไข่หวาน', 99, 7),
(32, 'ปลาไหล', 99, 7),
(33, 'แซลม่อน', 99, 7),
(34, 'ช้างคลาสสิก', 99, 8),
(35, 'ช้างเอกซ์พอร์ต', 99, 8),
(36, 'ลีโอ', 99, 8),
(37, 'สิงห์', 99, 8),
(38, 'อาชา', 99, 8),
(39, 'ไฮเนเก้น', 99, 8),
(40, 'แตงโม', 99, 9),
(41, 'สัปปะรด', 99, 9),
(42, 'มะม่วง', 99, 9),
(43, 'ส้ม', 99, 9),
(44, 'แอปเปิ้ล', 99, 9),
(45, 'เฟรนฟราย', 99, 10),
(46, 'ไส้กรอก', 99, 10),
(47, 'นักเกต', 99, 10),
(48, 'ข้าวกระเทียม', 99, 10),
(49, 'ข้าวผัด', 99, 10),
(50, 'ข้าวสวย', 99, 10),
(51, 'หมี่หยก', 99, 11),
(52, 'วุ้นเส้น', 99, 11),
(53, 'มาม่า', 99, 11),
(54, 'ไข่ไก่', 99, 12),
(55, 'ไข่นกกระทา', 99, 12),
(56, 'ไข่เป็ด', 99, 12);

-- --------------------------------------------------------

--
-- Table structure for table `type`
--

CREATE TABLE `type` (
  `TYPEID` int(11) NOT NULL,
  `TYPEDESC` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `type`
--

INSERT INTO `type` (`TYPEID`, `TYPEDESC`) VALUES
(1, 'Beef'),
(2, 'Pig'),
(3, 'Chicken'),
(4, 'Aquatic Animal'),
(5, 'Vegetable'),
(6, 'Dessert'),
(7, 'Shushi'),
(8, 'Alcohol'),
(9, 'Fruit'),
(10, 'Snack'),
(11, 'Noodles '),
(12, 'Egg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`ADMID`);

--
-- Indexes for table `histrnsdtl`
--
ALTER TABLE `histrnsdtl`
  ADD PRIMARY KEY (`HISDTLID`),
  ADD KEY `HISDTLITMID` (`HISDTLITMID`),
  ADD KEY `HISHDRID` (`HISDTLHDRID`),
  ADD KEY `HISDTLRESPON` (`HISDTLRESPON`);

--
-- Indexes for table `histrnshdr`
--
ALTER TABLE `histrnshdr`
  ADD PRIMARY KEY (`HISHDRID`),
  ADD KEY `HISHDRRESPON` (`HISHDRRESPON`);

--
-- Indexes for table `itmgnl`
--
ALTER TABLE `itmgnl`
  ADD PRIMARY KEY (`ITMID`),
  ADD KEY `ITMTYPE` (`ITMTYPE`);

--
-- Indexes for table `type`
--
ALTER TABLE `type`
  ADD PRIMARY KEY (`TYPEID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `ADMID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `histrnsdtl`
--
ALTER TABLE `histrnsdtl`
  MODIFY `HISDTLID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `histrnshdr`
--
ALTER TABLE `histrnshdr`
  MODIFY `HISHDRID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `itmgnl`
--
ALTER TABLE `itmgnl`
  MODIFY `ITMID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;
--
-- AUTO_INCREMENT for table `type`
--
ALTER TABLE `type`
  MODIFY `TYPEID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `histrnsdtl`
--
ALTER TABLE `histrnsdtl`
  ADD CONSTRAINT `histrnsdtl_ibfk_1` FOREIGN KEY (`HISDTLITMID`) REFERENCES `itmgnl` (`ITMID`),
  ADD CONSTRAINT `histrnsdtl_ibfk_2` FOREIGN KEY (`HISDTLHDRID`) REFERENCES `histrnshdr` (`HISHDRID`),
  ADD CONSTRAINT `histrnsdtl_ibfk_3` FOREIGN KEY (`HISDTLRESPON`) REFERENCES `admin` (`ADMID`);

--
-- Constraints for table `histrnshdr`
--
ALTER TABLE `histrnshdr`
  ADD CONSTRAINT `histrnshdr_ibfk_1` FOREIGN KEY (`HISHDRRESPON`) REFERENCES `admin` (`ADMID`);

--
-- Constraints for table `itmgnl`
--
ALTER TABLE `itmgnl`
  ADD CONSTRAINT `itmgnl_ibfk_1` FOREIGN KEY (`ITMTYPE`) REFERENCES `type` (`TYPEID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
