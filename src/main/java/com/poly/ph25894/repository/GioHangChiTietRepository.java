package com.poly.ph25894.repository;

import com.poly.ph25894.entity.GioHangChiTiet;
import com.poly.ph25894.entity.GioHangChiTietId;
import com.poly.ph25894.model.respone.GioHangChiTietRespone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, GioHangChiTietId> {

    GioHangChiTiet findByIdGioHangAndIdChiTietSanPham(UUID idGioHang, UUID idChiTietSanPham);

    @Query("select new com.poly.ph25894.model.respone.GioHangChiTietRespone(gioHang,chiTietSanPham," +
            "gioHangChiTiet.soLuong,gioHangChiTiet.donGia) from  GioHangChiTiet gioHangChiTiet " +
            " JOIN GioHang gioHang ON gioHang.id = gioHangChiTiet.idGioHang " +
            " JOIN ChiTietSanPham  chiTietSanPham on chiTietSanPham.id = gioHangChiTiet.idChiTietSanPham")
    Page<GioHangChiTietRespone> getAllCustom(Pageable pageable);

}
