package com.poly.ph25894.service.impl;

import com.poly.ph25894.entity.CuaHang;
import com.poly.ph25894.entity.base.CodeAuto;
import com.poly.ph25894.repository.CuaHangRepository;
import com.poly.ph25894.service.CuaHangService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.util.List;
import java.util.UUID;

@Service
public class CuaHangServiceImpl implements CuaHangService {

    @Autowired
    private CuaHangRepository repository;

    @Override
    public Page<CuaHang> getAll(Integer pageNo) {
        return repository.getAll(PageRequest.of(pageNo - 1, 5));
    }

    @Override
    public List<CuaHang> findAll() {
        return repository.listCuaHang();
    }

    @Override
    public CuaHang getOne(UUID id) {
        return repository.findById(id).get();
    }

    @Override
    public String add(@Valid CuaHang cuaHang, BindingResult result) {
        if (result.hasErrors()) {
            return "cuahang/home";
        }
        cuaHang = CuaHang.builder()
                .ma(new CodeAuto().generateRandomCode("CH"))
                .ten(cuaHang.getTen())
                .diaChi(cuaHang.getDiaChi())
                .thanhPho(cuaHang.getThanhPho())
                .quocGia(cuaHang.getQuocGia())
                .trangThai(0)
                .build();
        repository.save(cuaHang);
        return "redirect:/admin/cua-hang/hien-thi";
    }

    @Override
    public String update(@Valid CuaHang doiTuong, UUID id, BindingResult result) {
        if (result.hasErrors()) {
            return "cuahang/update";
        }
        BeanUtils.copyProperties(doiTuong, getOne(id));
        repository.save(doiTuong);
        return "redirect:/admin/cua-hang/hien-thi";
    }

    @Override
    public void remove(UUID id) {
        CuaHang cuaHang = getOne(id);
        repository.delete(cuaHang);
    }

}
