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
  `MaNhanVien` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
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
  `MaChuyenDi` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `MaXe` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `ThoiGianKhoiHanh` datetime DEFAULT NULL,
  `DiemKhoiHanh` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `DiemKetThuc` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
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
INSERT INTO `chuyendi` VALUES ('1','GHE_01','2021-05-18 00:00:00','Bến Tre ','TpHCM',29,0,200000),('355727','GHE_02','2022-04-12 00:00:00','TpHCM','Đà Lạt',22,2,200000),('363366','GHE_02','2023-01-10 00:00:00','TpHCM','Hà Nội',24,0,200000),('635170','GHE_01','2022-02-12 00:00:00','Bến Tre ','TpHCM',29,0,1000002),('678116','GHE_01','2021-10-07 00:00:00','Bến Tre ','TpHCM',29,0,200000),('835810','GHE_01','2022-03-12 00:00:00','Đà Lạt ','TpHCM',29,0,200000);
/*!40000 ALTER TABLE `chuyendi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doanhthuchuyendi`
--

DROP TABLE IF EXISTS `doanhthuchuyendi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doanhthuchuyendi` (
  `MaChuyenDi` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
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
  `MaNhanVien` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `TenNhanVien` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `MaLoaiNhanVien` int DEFAULT NULL,
  `NgaySinh` datetime DEFAULT NULL,
  `DiaChi` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
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
  `SoDienThoai` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NgayDat` datetime DEFAULT NULL,
  `MaChuyenDi` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `ViTriGhe` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `TrangThai` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`MaVe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vexe`
--

LOCK TABLES `vexe` WRITE;
/*!40000 ALTER TABLE `vexe` DISABLE KEYS */;
INSERT INTO `vexe` VALUES ('050100','aaaaaaaaaaaaaa','123','2021-12-27 00:00:00','248049','A4','Đặt'),('059414','Lamdeptrai','159','2021-12-21 00:00:00','355727','A7','Đặt'),('061833','213','2131312355','2021-12-30 00:00:00','635170','A8','Đặt'),('1','Vy','123','2021-12-06 00:00:00','1','A1','Đặt'),('105554','eeeee','123','2021-12-23 00:00:00','835810','A16','Đặt'),('153398','demo2','12345','2021-12-20 00:00:00','355727','A1','Đặt'),('166679','demo2','12345','2021-12-20 00:00:00','355727','A2','Đặt'),('170935','LamOke','456','2021-12-23 00:00:00','835810','A7','Đặt'),('2','KhaiVy','123','2021-12-06 00:00:00','1','A2','Đặt'),('207085','Lammm','785','2021-12-23 00:00:00','835810','A6','Đặt'),('243851','asdasdas','834871829','2021-12-30 00:00:00','635170','A11','Đặt'),('274216','demo','123456','2021-12-23 00:00:00','835810','A1','Đặt'),('275707','231','0834871829','2021-12-30 00:00:00','635170','A9','Bán'),('3','VyHeo','123','2021-12-07 00:00:00','355727','A3','Bán'),('305691','2321','834871829','2021-12-30 00:00:00','363366','A7','Bán'),('308303','2313','7894561231','2021-12-30 00:00:00','635170','A3','Bán'),('354087','đạt khôn','456','2021-12-21 00:00:00','355727','A5','Bán'),('362466','dsfasdfsdaf','12314','2021-12-23 00:00:00','','','Bán'),('367076','qaaaaaaa','159','2021-12-23 00:00:00','835810','A5','Bán'),('393925','LamOkayy','789','2021-12-23 00:00:00','835810','A3','Bán'),('4','VyKhung','123','2021-12-07 00:00:00','355727','A4','Bán'),('475650','www','123','2021-12-23 00:00:00','835810','A18','Bán'),('5','demo','123','2021-12-12 00:00:00','635170','A5','Bán'),('500490','bbbbb','123','2021-12-28 00:00:00','363366','A1','Bán'),('512125','LamOkayy','789','2021-12-23 00:00:00','835810','A8','Bán'),('526216','demo123','456789','2021-12-21 00:00:00','635170','A1','Đặt'),('572242','demo3','465','2021-12-20 00:00:00','635170','A2','Đặt'),('6','demo','834871829','2021-12-14 00:00:00','635170','A10','Đặt'),('633412','ccccc','213','2021-12-28 00:00:00','363366','A2','Đặt'),('711935','demo','123456','2021-12-23 00:00:00','835810','A2','Đặt'),('721947','dasd','123','2021-12-27 00:00:00','835810','A11','Đặt'),('732320','demooo123','834871829','2021-12-30 00:00:00','835810','A12','Đặt'),('754312','devv','2123','2021-12-21 00:00:00','355727','A8','Đặt'),('776559','213','834871829','2021-12-30 00:00:00','363366','A8','Đặt'),('821876','312','834871829','2021-12-30 00:00:00','835810','A20','Đặt'),('867723','dadad','834871829','2021-12-28 00:00:00','835810','A10','Đặt'),('891447','Lamm','357','2021-12-21 00:00:00','355727','A15','Đặt'),('898037','dsfasdfsdaf','12314','2021-12-23 00:00:00','835810','A14','Đặt'),('908848','0834871829a','834871829','2021-12-28 00:00:00','835810','A9','Đặt'),('921857','23123','1234567891','2021-12-30 00:00:00','635170','A20','Đặt'),('953614','asd','0834871821','2021-12-30 00:00:00','635170','A7','Đặt'),('957534','213213','1234567891','2021-12-30 00:00:00','363366','A12','Đặt'),('958207','Đạt ngu','0834871829','2021-12-20 00:00:00','635170','A4','Đặt'),('982427','aaaaaaaaaaaaaaaaaa','21312242','2021-12-27 00:00:00','248049','A3','Đặt');
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
  `MaNhanVien` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `BienSoXe` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `SoGhe` int DEFAULT NULL,
  PRIMARY KEY (`MaXe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xekhach`
--

LOCK TABLES `xekhach` WRITE;
/*!40000 ALTER TABLE `xekhach` DISABLE KEYS */;
INSERT INTO `xekhach` VALUES ('GHE_01','3','XE1',29),('GHE_02','3','XE2',24),('GHE_03','3','XE3',24),('GHE_04','3','XE4',29);
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

-- Dump completed on 2021-12-30  1:26:28
