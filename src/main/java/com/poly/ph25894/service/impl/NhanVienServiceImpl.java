package com.poly.ph25894.service.impl;

import com.poly.ph25894.entity.NhanVien;
import com.poly.ph25894.entity.base.CodeAuto;
import com.poly.ph25894.repository.NhanVienRepository;
import com.poly.ph25894.service.NhanVienService;
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
public class NhanVienServiceImpl implements NhanVienService {

    @Autowired
    private NhanVienRepository repository;

    @Override
    public Page<NhanVien> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return repository.getAll(pageable);
    }

    @Override
    public List<NhanVien> findAll() {
        return repository.listNhanVien();
    }

    @Override
    public NhanVien getOne(UUID id) {
        return repository.findById(id).get();
    }

    @Override
    public NhanVien getLogin(String ma, String matKhau) {
        return repository.findByMaAndMatKhau(ma, matKhau);
    }

    @Override
    public String add(@Valid NhanVien doiTuong, BindingResult result) {
        doiTuong.setMatKhau("123456");
        doiTuong.setTrangThai(0);
        if (result.hasErrors()) {
            return "nhanvien/home";
        }
        if (doiTuong.getNgaySinh() == null) {
            result.rejectValue("ngaySinh", "null", "Ngày sinh trống");
            return "nhanvien/home";
        }
        doiTuong.setMa(new CodeAuto().generateRandomCode("NV"));
        doiTuong.setMatKhau("123456");
        doiTuong.setTrangThai(0);
        repository.save(doiTuong);
        return "redirect:/admin/nhan-vien/hien-thi";
    }

    @Override
    public String update(@Valid NhanVien doiTuong, BindingResult result, UUID id) {
        if (result.hasErrors()) {
            return "nhanvien/update";
        }
        BeanUtils.copyProperties(doiTuong, getOne(id));
        repository.save(doiTuong);
        return "redirect:/admin/nhan-vien/hien-thi";
    }

    @Override
    public void remove(UUID id) {
        NhanVien nhanVien = getOne(id);
        repository.delete(nhanVien);
    }

}
