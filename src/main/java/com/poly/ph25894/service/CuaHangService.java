package com.poly.ph25894.service;

import com.poly.ph25894.entity.CuaHang;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import java.util.List;
import java.util.UUID;

public interface CuaHangService {

    Page<CuaHang> getAll(Integer pageNo);

    List<CuaHang> findAll();

    CuaHang getOne(UUID id);

    String add(@Valid CuaHang doiTuong, BindingResult result);

    String update(@Valid CuaHang doiTuong, UUID id, BindingResult result);

    void remove(UUID id);

}
