package com.poly.ph25894.service;

import com.poly.ph25894.entity.GioHangChiTiet;
import com.poly.ph25894.entity.GioHangChiTietId;
import com.poly.ph25894.model.respone.GioHangChiTietRespone;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;

public interface GioHangChiTietService {

    Page<GioHangChiTietRespone> getAll(Integer pageNo);

    GioHangChiTiet getOne(GioHangChiTietId gioHangChiTietId);

    String add(@Valid GioHangChiTiet doiTuong, BindingResult result);

    String update(@Valid GioHangChiTiet doiTuong, BindingResult result, GioHangChiTietId gioHangChiTietId);

    void remove(GioHangChiTietId gioHangChiTietId);

}
