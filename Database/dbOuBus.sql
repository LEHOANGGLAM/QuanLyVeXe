CREATE DATABASE  IF NOT EXISTS `oubus` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `oubus`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: oubus
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `TaiKhoan` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `MatKhau` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `MaNhanVien` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MaQuyen` int DEFAULT NULL,
  PRIMARY KEY (`TaiKhoan`),
  KEY `fk_MaQuyen_idx` (`MaQuyen`),
  CONSTRAINT `fk_MaQuyen` FOREIGN KEY (`MaQuyen`) REFERENCES `phanquyen` (`MaQuyen`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('hoanglam','123456','1',1),('thuantam','123456','2',2);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chuyendi`
--

DROP TABLE IF EXISTS `chuyendi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chuyendi` (
  `MaChuyenDi` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `MaXe` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ThoiGianKhoiHanh` datetime DEFAULT NULL,
  `DiemKhoiHanh` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DiemKetThuc` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SoGheTrong` int DEFAULT NULL,
  `SoGheDat` int DEFAULT NULL,
  `GiaVe` int DEFAULT NULL,
  PRIMARY KEY (`MaChuyenDi`),
  KEY `fk_MaChuyenDi_Xe_idx` (`MaXe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chuyendi`
--

LOCK TABLES `chuyendi` WRITE;
/*!40000 ALTER TABLE `chuyendi` DISABLE KEYS */;
INSERT INTO `chuyendi` VALUES ('1','GHE_01','2021-12-07 00:00:00','Bến Tre ','TpHCM',23,2,200000),('5f133354-2eff-44f4-bb4a-4bd504938ad2','GHE_01',NULL,'Bến Tre ','TpHCM',25,0,200000);
/*!40000 ALTER TABLE `chuyendi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doanhthuchuyendi`
--

DROP TABLE IF EXISTS `doanhthuchuyendi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doanhthuchuyendi` (
  `MaChuyenDi` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `DoanhThu` int DEFAULT NULL,
  `SoVeDat` int DEFAULT NULL,
  `Ngay` datetime DEFAULT NULL,
  PRIMARY KEY (`MaChuyenDi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doanhthuchuyendi`
--

LOCK TABLES `doanhthuchuyendi` WRITE;
/*!40000 ALTER TABLE `doanhthuchuyendi` DISABLE KEYS */;
INSERT INTO `doanhthuchuyendi` VALUES ('1',NULL,NULL,NULL),('2',NULL,NULL,NULL);
/*!40000 ALTER TABLE `doanhthuchuyendi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `MaNhanVien` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `TenNhanVien` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MaLoaiNhanVien` int DEFAULT NULL,
  `NgaySinh` datetime DEFAULT NULL,
  `DiaChi` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`MaNhanVien`),
  KEY `fk_MaLoaiNhanVien_idx` (`MaLoaiNhanVien`),
  CONSTRAINT `fk_MaLoaiNhanVien` FOREIGN KEY (`MaLoaiNhanVien`) REFERENCES `phanloainhanvien` (`MaLoaiNhanVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES ('1','Hoàng Lâm',1,'2001-08-24 00:00:00','Bến Tre'),('2','Thuận Tâm',2,'2001-01-01 00:00:00','TpHCM'),('3','Hưng',3,'2001-01-02 00:00:00','TpHCM');
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phanloainhanvien`
--

DROP TABLE IF EXISTS `phanloainhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phanloainhanvien` (
  `MaLoaiNhanVien` int NOT NULL,
  `TenLoaiNhanVien` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`MaLoaiNhanVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phanloainhanvien`
--

LOCK TABLES `phanloainhanvien` WRITE;
/*!40000 ALTER TABLE `phanloainhanvien` DISABLE KEYS */;
INSERT INTO `phanloainhanvien` VALUES (1,'Chủ'),(2,'Nhân Viên'),(3,'Tài Xế');
/*!40000 ALTER TABLE `phanloainhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phanquyen`
--

DROP TABLE IF EXISTS `phanquyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phanquyen` (
  `MaQuyen` int NOT NULL,
  `TenQuyen` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`MaQuyen`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phanquyen`
--

LOCK TABLES `phanquyen` WRITE;
/*!40000 ALTER TABLE `phanquyen` DISABLE KEYS */;
INSERT INTO `phanquyen` VALUES (1,'ADMIN'),(2,'Nhân Viên');
/*!40000 ALTER TABLE `phanquyen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vexe`
--

DROP TABLE IF EXISTS `vexe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vexe` (
  `MaVe` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `TenKhachHang` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SoDienThoai` int DEFAULT NULL,
  `NgayDat` datetime DEFAULT NULL,
  `MaChuyenDi` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ViTriGhe` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TrangThai` int DEFAULT NULL,
  PRIMARY KEY (`MaVe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vexe`
--

LOCK TABLES `vexe` WRITE;
/*!40000 ALTER TABLE `vexe` DISABLE KEYS */;
INSERT INTO `vexe` VALUES ('1','Vy',123,'2021-12-06 00:00:00','1','A1',NULL),('2','KhaiVy',123,'2021-12-06 00:00:00','1','A2',NULL),('3','VyHeo',123,'2021-12-07 00:00:00','2','A1',NULL),('4','VyKhung',123,'2021-12-07 00:00:00','2','A2',NULL);
/*!40000 ALTER TABLE `vexe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xekhach`
--

DROP TABLE IF EXISTS `xekhach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xekhach` (
  `MaXe` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `MaNhanVien` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BienSoXe` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SoGhe` int DEFAULT NULL,
  PRIMARY KEY (`MaXe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xekhach`
--

LOCK TABLES `xekhach` WRITE;
/*!40000 ALTER TABLE `xekhach` DISABLE KEYS */;
INSERT INTO `xekhach` VALUES ('GHE_01','3','XE1',25),('GHE_02','3','XE2',20),('GIUONG_01','3','XE3',20),('GIUONG_02','3','XE4',25);
/*!40000 ALTER TABLE `xekhach` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-10  0:15:35
