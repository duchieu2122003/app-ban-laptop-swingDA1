package com.poly.ph25894.repository;

import com.poly.ph25894.entity.HoaDonChiTiet;
import com.poly.ph25894.entity.HoaDonChiTietId;
import com.poly.ph25894.model.respone.HoaDonChiTietRespone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, HoaDonChiTietId> {

    HoaDonChiTiet findByIdHoaDonAndIdChiTietSanPham(UUID idHoaDon, UUID idChiTietSanPham);

    @Query("select new com.poly.ph25894.model.respone.HoaDonChiTietRespone(hoaDon,chiTietSanPham," +
            " hoaDonChiTiet.soLuong,hoaDonChiTiet.donGia) from  HoaDonChiTiet hoaDonChiTiet " +
            " JOIN HoaDon hoaDon ON hoaDon.id = hoaDonChiTiet.idHoaDon" +
            " JOIN ChiTietSanPham  chiTietSanPham on chiTietSanPham.id = hoaDonChiTiet.idChiTietSanPham")
    Page<HoaDonChiTietRespone> getAllCustom(Pageable pageable);

    @Query("SELECT SUM(chitiet.donGia) FROM HoaDonChiTiet chitiet WHERE chitiet.idHoaDon = ?1")
    BigDecimal tongTienHoaDonChiTiet(UUID idHoaDon);

}
