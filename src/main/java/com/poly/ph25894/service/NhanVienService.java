package com.poly.ph25894.service;

import com.poly.ph25894.entity.NhanVien;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import java.util.List;
import java.util.UUID;

public interface NhanVienService {

    Page<NhanVien> getAll(Integer pageNo);

    List<NhanVien> findAll();

    NhanVien getOne(UUID id);

    NhanVien getLogin(String ma, String matKhau);

    String add(@Valid NhanVien doiTuong, BindingResult result);

    String update(@Valid NhanVien doiTuong, BindingResult result, UUID id);

    void remove(UUID id);

}
