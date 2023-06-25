package com.poly.ph25894.service;

import com.poly.ph25894.entity.HoaDon;
import com.poly.ph25894.entity.KhachHang;
import com.poly.ph25894.model.respone.HoaDonChiTietBanHang;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public interface BanHangService {

    HashMap<String, String> getLogin(String maNhanVien, String matKhau);

    Boolean trangThaiLogin(String idNhanVien);

    List<HoaDonChiTietBanHang> listChiTietSanPhamHoaDon(UUID idHoaDon);

    Boolean detailDaThanhToan(UUID idHoaDon);

    BigDecimal tongTienHoaDonChiTiet(UUID idHoaDon);

    void removeHoaDonChiTiet(UUID idHoaDon, UUID idChiTietSanPham);

    HashMap<String, String> chonHoaDonKhiThemSanPham(UUID idHoaDonChon);

    HashMap<String, String> addHoaDon(String idNhanVien);

    HashMap<String, String> thanhToanHoaDon(@Valid HoaDon hoaDon, BindingResult result, UUID idHoaDon);

    HashMap<String, String> addChiTietSanPhamHoaDonChiTiet(UUID idHoaDon, UUID idChiTietSanPham, Integer soLuong);

    HashMap<String, String> addSoLuongHoaDonChiTiet(UUID idHoaDon, UUID idChiTietSanPham, Integer soLuong);

    void suaChiTietSanPhamHoaDonChiTiet(UUID idHoaDon, UUID idChiTietSanPham, Integer soLuong);

    HashMap<String, String> addKhachHang(@Valid KhachHang khachHang, BindingResult result, UUID idHoaDon);

}
