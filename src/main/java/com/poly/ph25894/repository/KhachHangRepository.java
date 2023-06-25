package com.poly.ph25894.repository;

import com.poly.ph25894.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, UUID> {

    @Query(value = "SELECT * FROM KhachHang WHERE trangThai =0", nativeQuery = true)
    List<KhachHang> listKhachHang();

    KhachHang findByMa(String ma);

}
