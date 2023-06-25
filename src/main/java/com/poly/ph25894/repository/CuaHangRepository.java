package com.poly.ph25894.repository;

import com.poly.ph25894.entity.CuaHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface CuaHangRepository extends JpaRepository<CuaHang, UUID> {

    @Query(value = "SELECT id, ma, ten, diaChi, thanhPho, quocGia, trangThai FROM CuaHang ",
            nativeQuery = true)
    Page<CuaHang> getAll(Pageable pageable);

    @Query(value = "SELECT * FROM CuaHang WHERE trangThai = 0 ",
            nativeQuery = true)
    List<CuaHang> listCuaHang();

}
