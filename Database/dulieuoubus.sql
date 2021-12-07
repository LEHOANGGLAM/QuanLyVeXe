use `oubus`;

INSERT INTO `oubus`.`phanloainhanvien` (`MaLoaiNhanVien`, `TenLoaiNhanVien`) VALUES ('1', 'Chủ');
INSERT INTO `oubus`.`phanloainhanvien` (`MaLoaiNhanVien`, `TenLoaiNhanVien`) VALUES ('2', 'Nhân Viên');
INSERT INTO `oubus`.`phanloainhanvien` (`MaLoaiNhanVien`, `TenLoaiNhanVien`) VALUES ('3', 'Tài Xế');

INSERT INTO `oubus`.`phanquyen` (`MaQuyen`, `TenQuyen`) VALUES ('1', 'ADMIN');
INSERT INTO `oubus`.`phanquyen` (`MaQuyen`, `TenQuyen`) VALUES ('2', 'Nhân Viên');

INSERT INTO `oubus`.`account` (`TaiKhoan`, `MatKhau`, `MaNhanVien`, `MaQuyen`) VALUES ('hoanglam', '123456', '1', '1');
INSERT INTO `oubus`.`account` (`TaiKhoan`, `MatKhau`, `MaNhanVien`, `MaQuyen`) VALUES ('thuantam', '123456', '2', '2');

INSERT INTO `oubus`.`nhanvien` (`MaNhanVien`, `TenNhanVien`, `MaLoaiNhanVien`, `NgaySinh`, `DiaChi`) VALUES ('1', 'Hoàng Lâm', '1', '2001-8-24', 'Bến Tre');
INSERT INTO `oubus`.`nhanvien` (`MaNhanVien`, `TenNhanVien`, `MaLoaiNhanVien`, `NgaySinh`, `DiaChi`) VALUES ('2', 'Thuận Tâm', '2', '2001-1-1', 'TpHCM');
INSERT INTO `oubus`.`nhanvien` (`MaNhanVien`, `TenNhanVien`, `MaLoaiNhanVien`, `NgaySinh`, `DiaChi`) VALUES ('3', 'Hưng', '3', '2001-1-2', 'TpHCM');

INSERT INTO `oubus`.`xekhach` (`MaXe`, `MaNhanVien`, `SoGhe`) VALUES ('GHE_01', '3', '25');
INSERT INTO `oubus`.`xekhach` (`MaXe`, `MaNhanVien`, `SoGhe`) VALUES ('GHE_02', '3', '20');
INSERT INTO `oubus`.`xekhach` (`MaXe`, `MaNhanVien`, `SoGhe`) VALUES ('GIUONG_01', '3', '20');
INSERT INTO `oubus`.`xekhach` (`MaXe`, `MaNhanVien`, `SoGhe`) VALUES ('GIUONG_02', '3', '25');

INSERT INTO `oubus`.`chuyendi` (`MaChuyenDi`, `MaXe`, `ThoiGianKhoiHanh`, `DiemKhoiHanh`, `DiemKetThuc`, `GiaVe`) VALUES ('1', 'GHE_01', '2021-12-7', 'Bến Tre ', 'TpHCM', '200000');
INSERT INTO `oubus`.`chuyendi` (`MaChuyenDi`, `MaXe`, `ThoiGianKhoiHanh`, `DiemKhoiHanh`, `DiemKetThuc`, `GiaVe`) VALUES ('2', 'GHE_02', '2021-12-8', 'TpHCM', 'Bến Tre', '200000');

INSERT INTO `oubus`.`doanhthuchuyendi` (`MaChuyenDi`) VALUES ('1');
INSERT INTO `oubus`.`doanhthuchuyendi` (`MaChuyenDi`) VALUES ('2');

INSERT INTO `oubus`.`vexe` (`MaVe`, `TenKhachHang`, `SoDienThoai`, `NgayDat`, `MaChuyenDi`, `ViTriGhe`) VALUES ('1', 'Vy', '123', '2021-12-6', '1', 'A1');
INSERT INTO `oubus`.`vexe` (`MaVe`, `TenKhachHang`, `SoDienThoai`, `NgayDat`, `MaChuyenDi`, `ViTriGhe`) VALUES ('2', 'KhaiVy', '123', '2021-12-6', '1', 'A2');
INSERT INTO `oubus`.`vexe` (`MaVe`, `TenKhachHang`, `SoDienThoai`, `NgayDat`, `MaChuyenDi`, `ViTriGhe`) VALUES ('3', 'VyHeo', '123', '2021-12-7', '2', 'A1');
INSERT INTO `oubus`.`vexe` (`MaVe`, `TenKhachHang`, `SoDienThoai`, `NgayDat`, `MaChuyenDi`, `ViTriGhe`) VALUES ('4', 'VyKhung', '123', '2021-12-7', '2', 'A2');

UPDATE `oubus`.`chuyendi` SET `SoGheTrong` = '23', `SoGheDat` = '2' WHERE (`MaChuyenDi` = '1');
UPDATE `oubus`.`chuyendi` SET `SoGheTrong` = '18', `SoGheDat` = '2' WHERE (`MaChuyenDi` = '2');





