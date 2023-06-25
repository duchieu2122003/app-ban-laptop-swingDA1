package com.poly.ph25894.service.impl;

import com.poly.ph25894.entity.GioHang;
import com.poly.ph25894.entity.base.CodeAuto;
import com.poly.ph25894.repository.GioHangRepository;
import com.poly.ph25894.service.GioHangService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class GioHangServiceImpl implements GioHangService {

    @Autowired
    private GioHangRepository repository;

    @Override
    public Page<GioHang> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return repository.findAll(pageable);
    }

    @Override
    public List<GioHang> findAll() {
        return repository.findAll();
    }

    @Override
    public GioHang getOne(UUID id) {
        return repository.findById(id).get();
    }

    @Override
    public String add(@Valid GioHang doiTuong, BindingResult result) {
        System.out.println(result);
        if (result.hasErrors()) {
            return "giohang/home";
        }
        if (doiTuong.getTrangThai() == 1) {
            doiTuong.setNgayThanhToan(null);
        }
        doiTuong.setMa(new CodeAuto().generateRandomCode("GH"));
        doiTuong.setNgayTao(Date.valueOf(LocalDate.now()));
        doiTuong.setTrangThai(0);
        repository.save(doiTuong);
        return "redirect:/admin/gio-hang/hien-thi";
    }

    @Override
    public String update(@Valid GioHang doiTuong, BindingResult result, UUID id) {
        if (result.hasErrors()) {
            return "giohang/update";
        }
        BeanUtils.copyProperties(doiTuong, getOne(id));
        repository.save(doiTuong);
        return "redirect:/admin/gio-hang/hien-thi";
    }

    @Override
    public void remove(UUID id) {
        repository.delete(getOne(id));
    }

}
