package com.poly.ph25894.service;

import com.poly.ph25894.entity.ChiTietSanPham;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import java.util.List;
import java.util.UUID;

public interface ChiTietSanPhamService {

    Page<ChiTietSanPham> getAll(Integer pageNo);

    List<ChiTietSanPham> findAll();

    List<ChiTietSanPham> findAllBanHang();

    ChiTietSanPham getOne(UUID id);

    String add(@Valid ChiTietSanPham doiTuong, BindingResult result);

    String update(@Valid ChiTietSanPham doiTuong, UUID id, BindingResult result);

    void remove(UUID id);

}
