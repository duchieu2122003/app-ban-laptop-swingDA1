package com.poly.ph25894.service;

import com.poly.ph25894.entity.KhachHang;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import java.util.List;
import java.util.UUID;

public interface KhachHangService {

    Page<KhachHang> getAll(Integer pageNo);

    List<KhachHang> findAll();

    KhachHang getOne(UUID id);

    String add(@Valid KhachHang doiTuong, BindingResult result);

    String update(@Valid KhachHang doiTuong, BindingResult result, UUID id);

    void remove(UUID id);

}
