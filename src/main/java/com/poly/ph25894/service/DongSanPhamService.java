package com.poly.ph25894.service;

import com.poly.ph25894.entity.DongSanPham;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import java.util.List;
import java.util.UUID;

public interface DongSanPhamService {

    Page<DongSanPham> getAll(Integer pageNo);

    List<DongSanPham> findAll();

    DongSanPham getOne(UUID id);

    String add(@Valid DongSanPham doiTuong, BindingResult result);

    String update(@Valid DongSanPham doiTuong, UUID id, BindingResult result);

    void remove(UUID id);

}
