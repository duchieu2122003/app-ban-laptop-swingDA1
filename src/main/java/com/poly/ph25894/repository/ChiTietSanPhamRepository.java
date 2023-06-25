package com.poly.ph25894.repository;

import com.poly.ph25894.entity.ChiTietSanPham;
import com.poly.ph25894.model.respone.HoaDonChiTietBanHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, UUID> {

    @Query(value = " select  chiTiet.id,  chiTiet.idSP,  chiTiet.idDongSp,  chiTiet.idMauSac," +
            "  chiTiet.moTa, chiTiet.namBH, chiTiet.giaBan, chiTiet.giaNhap, chiTiet.soLuongTon, chiTiet.trangThai " +
            "  from ChiTietSP chiTiet ", nativeQuery = true)
    Page<ChiTietSanPham> getAll(Pageable pageable);

    ChiTietSanPham findChiTietSanPhamById(UUID id);

    @Query(value = "select chiTiet.id,  chiTiet.idSP,  chiTiet.idDongSp,  chiTiet.idMauSac," +
            "  chiTiet.moTa, chiTiet.namBH, chiTiet.giaBan, chiTiet.giaNhap, chiTiet.soLuongTon, chiTiet.trangThai " +
            "  from ChiTietSP chiTiet WHERE chiTiet.soLuongTon > 0", nativeQuery = true)
    List<ChiTietSanPham> findAllBanHang();

    @Query(value = "select new com.poly.ph25894.model.respone.HoaDonChiTietBanHang" +
            "(chiTiet , hoaDon.idHoaDon, hoaDon.soLuong , hoaDon.donGia) " +
            " from ChiTietSanPham chiTiet" +
            " JOIN HoaDonChiTiet hoaDon on hoaDon.idChiTietSanPham = chiTiet.id" +
            " JOIN SanPham sp on sp.id = chiTiet.sanPham.id" +
            " where hoaDon.idHoaDon = ?1")
    List<HoaDonChiTietBanHang> listChiTietHoaDon(UUID idHoaDon);

}
