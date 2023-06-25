package com.poly.ph25894.repository;

import com.poly.ph25894.entity.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, UUID> {

    @Query(value = "SELECT id, ma, ten, gioiTinh, ngaySinh, diaChi,sdt, matKhau, idCH, idCV, trangThai FROM " +
            " NhanVien nhanVien", nativeQuery = true)
    Page<NhanVien> getAll(Pageable pageable);

    @Query(value = "SELECT id, ma, ten, gioiTinh, ngaySinh, diaChi,sdt, matKhau, idCH, idCV, trangThai FROM " +
            " NhanVien nhanVien WHERE trangThai =0", nativeQuery = true)
    List<NhanVien> listNhanVien();

    NhanVien findByMa(String ma);

    NhanVien findByMaAndMatKhau(String ma, String matKhau);

}
