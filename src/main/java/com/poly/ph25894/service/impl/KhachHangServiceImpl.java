package com.poly.ph25894.service.impl;

import com.poly.ph25894.entity.KhachHang;
import com.poly.ph25894.entity.base.CodeAuto;
import com.poly.ph25894.repository.KhachHangRepository;
import com.poly.ph25894.service.KhachHangService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.util.List;
import java.util.UUID;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    private KhachHangRepository repository;

    @Override
    public Page<KhachHang> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return repository.findAll(pageable);
    }

    @Override
    public List<KhachHang> findAll() {
        return repository.listKhachHang();
    }

    @Override
    public KhachHang getOne(UUID id) {
        return repository.findById(id).get();
    }

    @Override
    public String add(@Valid KhachHang doiTuong, BindingResult result) {
        doiTuong.setMatKhau("123456");
        doiTuong.setTrangThai(0);
        if (result.hasErrors()) {
            return "khachhang/home";
        }
        if (doiTuong.getNgaySinh() == null) {
            result.rejectValue("ngaySinh", "null", "Ngày sinh trống");
            return "khachhang/home";
        }
        doiTuong.setMa(new CodeAuto().generateRandomCode("KH"));
        doiTuong.setMatKhau("123456");
        doiTuong.setTrangThai(0);
        repository.save(doiTuong);
        return "redirect:/admin/khach-hang/hien-thi";
    }

    @Override
    public String update(@Valid KhachHang doiTuong, BindingResult result, UUID id) {
        if (result.hasErrors()) {
            return "khachhang/update";
        }
        BeanUtils.copyProperties(doiTuong, getOne(id));
        repository.save(doiTuong);
        return "redirect:/admin/khach-hang/hien-thi";
    }

    @Override
    public void remove(UUID id) {
        KhachHang khachHang = getOne(id);
        repository.delete(khachHang);
    }

}
