package com.poly.ph25894.service;

import com.poly.ph25894.entity.GioHang;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import java.util.List;
import java.util.UUID;

public interface GioHangService {

    Page<GioHang> getAll(Integer pageNo);

    List<GioHang> findAll();

    GioHang getOne(UUID id);

    String add(@Valid GioHang doiTuong, BindingResult result);

    String update(@Valid GioHang doiTuong, BindingResult result, UUID id);

    void remove(UUID id);

}
