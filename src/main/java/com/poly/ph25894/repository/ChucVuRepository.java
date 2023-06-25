package com.poly.ph25894.repository;

import com.poly.ph25894.entity.ChucVu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface ChucVuRepository extends JpaRepository<ChucVu, UUID> {

    @Query(value = " SELECT id, ma, ten, trangThai FROM ChucVu  ", nativeQuery = true)
    Page<ChucVu> getAll(Pageable pageable);

    @Query(value = "SELECT * FROM ChucVu WHERE trangThai = 0 ", nativeQuery = true)
    List<ChucVu> listChucVu();

}
