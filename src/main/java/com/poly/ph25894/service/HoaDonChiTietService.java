package com.poly.ph25894.service;

import com.poly.ph25894.entity.HoaDonChiTiet;
import com.poly.ph25894.entity.HoaDonChiTietId;
import com.poly.ph25894.model.respone.HoaDonChiTietRespone;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;

public interface HoaDonChiTietService {

    Page<HoaDonChiTietRespone> getAll(Integer pageNo);

    HoaDonChiTiet getOne(HoaDonChiTietId gioHangChiTietId);

    String add(@Valid HoaDonChiTiet doiTuong, BindingResult result);

    String update(@Valid HoaDonChiTiet doiTuong, BindingResult result, HoaDonChiTietId hoaDonChiTietId);

    void remove(HoaDonChiTietId gioHangChiTietId);

}
