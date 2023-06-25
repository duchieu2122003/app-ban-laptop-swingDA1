package com.poly.ph25894.service;

import com.poly.ph25894.entity.SanPham;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import java.util.List;
import java.util.UUID;

public interface SanPhamService {

    Page<SanPham> getAll(Integer pageNo);

    List<SanPham> findAll();

    SanPham getOne(UUID id);

    String addSanPham(@Valid SanPham sanPham, BindingResult result);

    String updateSanPham(@Valid SanPham sanPham, UUID id, BindingResult result);

    void removeSanPham(UUID id);

}
