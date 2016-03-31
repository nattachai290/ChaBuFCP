-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 22, 2016 at 06:20 PM
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
  `POSITION` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `histrnsdtl`
--

CREATE TABLE `histrnsdtl` (
  `HISDTLID` int(11) NOT NULL,
  `HISHDRID` int(11) NOT NULL,
  `HISDTLITMID` int(11) NOT NULL,
  `HISDTLQTY` int(4) DEFAULT NULL,
  `HISDTLPRICE` int(4) DEFAULT NULL,
  `HISTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `histrnshdr`
--

CREATE TABLE `histrnshdr` (
  `HISHDRID` int(11) NOT NULL,
  `HISHDRTBLNO` int(3) NOT NULL,
  `HISHDRTOTALPRICE` int(7) DEFAULT NULL,
  `HISHDRTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `itmgnl`
--

CREATE TABLE `itmgnl` (
  `ITMID` int(11) NOT NULL,
  `ITMNAME` varchar(255) NOT NULL,
  `ITMQTY` int(7) DEFAULT NULL,
  `ITMTYPE` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `type`
--

CREATE TABLE `type` (
  `TYPEID` varchar(255) NOT NULL,
  `TYPEDESC` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  ADD KEY `HISHDRID` (`HISHDRID`);

--
-- Indexes for table `histrnshdr`
--
ALTER TABLE `histrnshdr`
  ADD PRIMARY KEY (`HISHDRID`);

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
  MODIFY `ADMID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `histrnsdtl`
--
ALTER TABLE `histrnsdtl`
  MODIFY `HISDTLID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `histrnshdr`
--
ALTER TABLE `histrnshdr`
  MODIFY `HISHDRID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `itmgnl`
--
ALTER TABLE `itmgnl`
  MODIFY `ITMID` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `histrnsdtl`
--
ALTER TABLE `histrnsdtl`
  ADD CONSTRAINT `histrnsdtl_ibfk_1` FOREIGN KEY (`HISDTLITMID`) REFERENCES `itmgnl` (`ITMID`),
  ADD CONSTRAINT `histrnsdtl_ibfk_2` FOREIGN KEY (`HISHDRID`) REFERENCES `histrnshdr` (`HISHDRID`);

--
-- Constraints for table `itmgnl`
--
ALTER TABLE `itmgnl`
  ADD CONSTRAINT `itmgnl_ibfk_1` FOREIGN KEY (`ITMTYPE`) REFERENCES `type` (`TYPEID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
