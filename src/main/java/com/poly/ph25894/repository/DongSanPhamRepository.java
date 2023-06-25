package com.poly.ph25894.repository;

import com.poly.ph25894.entity.DongSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DongSanPhamRepository extends JpaRepository<DongSanPham, UUID> {

    @Query(value = "SELECT id, ma, ten , trangThai FROM DongSP ", nativeQuery = true)
    Page<DongSanPham> getAll(Pageable pageable);

    @Query(value = " SELECT id, ma, ten, trangThai FROM DongSP  WHERE trangThai = 0 "
            , nativeQuery = true)
    List<DongSanPham> findAllAddChiTietSanPham();

}
