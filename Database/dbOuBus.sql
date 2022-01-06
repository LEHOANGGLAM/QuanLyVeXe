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
  `TaiKhoan` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `MatKhau` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `MaQuyen` int DEFAULT NULL,
  `MaNhanVien` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`TaiKhoan`),
  KEY `fk_MaQuyen_idx` (`MaQuyen`),
  KEY `fk_account_nhanvien1_idx` (`MaNhanVien`),
  CONSTRAINT `fk_account_nhanvien1` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`),
  CONSTRAINT `fk_MaQuyen` FOREIGN KEY (`MaQuyen`) REFERENCES `phanquyen` (`MaQuyen`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('hoanglam','á\nÜ9IºY«¾VàWòˆ>',1,'1'),('thuantam','á\nÜ9IºY«¾VàWòˆ>',2,'2');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chuyendi`
--

DROP TABLE IF EXISTS `chuyendi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chuyendi` (
  `MaChuyenDi` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `NgayKhoiHanh` datetime DEFAULT NULL,
  `DiemKhoiHanh` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `DiemKetThuc` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `SoGheTrong` int DEFAULT NULL,
  `SoGheDat` int DEFAULT NULL,
  `GiaVe` int DEFAULT NULL,
  `GioKhoiHanh` time DEFAULT NULL,
  `MaXe` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`MaChuyenDi`),
  KEY `fk_chuyendi_xekhach1_idx` (`MaXe`),
  CONSTRAINT `fk_chuyendi_xekhach1` FOREIGN KEY (`MaXe`) REFERENCES `xekhach` (`MaXe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chuyendi`
--

LOCK TABLES `chuyendi` WRITE;
/*!40000 ALTER TABLE `chuyendi` DISABLE KEYS */;
INSERT INTO `chuyendi` VALUES ('1','2021-04-16 00:00:00','TpHCM','Bến Tre ',29,0,200000,'17:12:00','GHE_01'),('143618','2022-01-06 00:00:00','demo','demo2',24,0,100000,'08:00:05','GHE_01'),('313220','2022-04-15 00:00:00','TpHCM','Bến Tre ',23,6,200000,'17:12:00','GHE_01'),('355727','2022-04-12 00:00:00','Bến Tre','TpHCM',20,4,200000,'09:30:00','GHE_02'),('363366','2022-01-06 00:00:00','Bến Tre ','TpHCM',24,0,200000,'18:00:00','GHE_02'),('577258','2022-01-06 00:00:00','demo','demo2',24,0,100000,'08:00:05','GHE_01'),('612320','2022-04-12 00:00:00','Bến Tre','TpHCM',24,0,200000,'09:30:00','GHE_02');
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
  `DoanhThu` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SoVeDat` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Ngay` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`MaChuyenDi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doanhthuchuyendi`
--

LOCK TABLES `doanhthuchuyendi` WRITE;
/*!40000 ALTER TABLE `doanhthuchuyendi` DISABLE KEYS */;
INSERT INTO `doanhthuchuyendi` VALUES ('313220','1200000','6','2022-01-06'),('355727','800000','4','2022-01-06');
/*!40000 ALTER TABLE `doanhthuchuyendi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `MaNhanVien` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `TenNhanVien` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `MaLoaiNhanVien` int DEFAULT NULL,
  `NgaySinh` datetime DEFAULT NULL,
  `SoDienThoai` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `CMND` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `QueQuan` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
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
INSERT INTO `nhanvien` VALUES ('1','Hoàng Lâm',1,'2001-08-24 00:00:00','033113','39113','Bến Tre'),('2','Thuận Tâm',2,'2001-01-01 00:00:00','033114','39114','TpHCM'),('3','Hưng',3,'2001-01-02 00:00:00','035115','39115','TpHCM');
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
  `TenLoaiNhanVien` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
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
  `TenQuyen` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
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
  `MaVe` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `TenKhachHang` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `SoDienThoai` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `NgayDat` datetime DEFAULT NULL,
  `ViTriGhe` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `TrangThai` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `MaChuyenDi` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `MaNhanVien` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`MaVe`),
  KEY `fk_vexe_chuyendi1_idx` (`MaChuyenDi`),
  KEY `fk_vexe_nhanvien1_idx` (`MaNhanVien`),
  CONSTRAINT `fk_vexe_chuyendi1` FOREIGN KEY (`MaChuyenDi`) REFERENCES `chuyendi` (`MaChuyenDi`),
  CONSTRAINT `fk_vexe_nhanvien1` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vexe`
--

LOCK TABLES `vexe` WRITE;
/*!40000 ALTER TABLE `vexe` DISABLE KEYS */;
INSERT INTO `vexe` VALUES ('229022','test2','0123456789','2022-01-06 00:00:00','A3','Đặt','313220','1'),('263270','0123456789','0123456789','2022-01-06 00:00:00','A7','Bán','355727','1'),('437604','0123456789','0123456789','2022-01-06 00:00:00','A7','Bán','313220','2'),('562190','0123456789','0123456789','2022-01-06 00:00:00','A6','Đặt','313220','2'),('583628','0123456789','0123456789','2022-01-06 00:00:00','A3','Bán','355727','1'),('671631','0123456789','0123456789','2022-01-06 00:00:00','A8','Bán','355727','1'),('815685','0123456789','0123456789','2022-01-06 00:00:00','A1','Bán','313220','1'),('851519','0123456789','0123456789','2022-01-06 00:00:00','A2','Đặt','313220','1'),('867355','0123456789','0123456789','2022-01-06 00:00:00','A4','Đặt','313220','1'),('884191','0123456789','0123456789','2022-01-06 00:00:00','A4','Đặt','355727','1');
/*!40000 ALTER TABLE `vexe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xekhach`
--

DROP TABLE IF EXISTS `xekhach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xekhach` (
  `MaXe` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `BienSoXe` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `SoGhe` int DEFAULT NULL,
  `MaNhanVien` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`MaXe`),
  KEY `fk_xekhach_nhanvien1_idx` (`MaNhanVien`),
  CONSTRAINT `fk_xekhach_nhanvien1` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xekhach`
--

LOCK TABLES `xekhach` WRITE;
/*!40000 ALTER TABLE `xekhach` DISABLE KEYS */;
INSERT INTO `xekhach` VALUES ('GHE_01','XE1',29,'3'),('GHE_02','XE2',24,'3');
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

-- Dump completed on 2022-01-06 20:58:22
