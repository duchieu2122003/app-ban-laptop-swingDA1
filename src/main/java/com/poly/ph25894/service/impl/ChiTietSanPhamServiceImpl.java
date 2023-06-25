package com.poly.ph25894.service.impl;

import com.poly.ph25894.entity.ChiTietSanPham;
import com.poly.ph25894.repository.ChiTietSanPhamRepository;
import com.poly.ph25894.service.ChiTietSanPhamService;
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
public class ChiTietSanPhamServiceImpl implements ChiTietSanPhamService {

    @Autowired
    private ChiTietSanPhamRepository repository;

    @Override
    public Page<com.poly.ph25894.entity.ChiTietSanPham> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return repository.getAll(pageable);
    }

    @Override
    public List<ChiTietSanPham> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ChiTietSanPham> findAllBanHang() {
        return repository.findAllBanHang();
    }

    @Override
    public com.poly.ph25894.entity.ChiTietSanPham getOne(UUID id) {
        return repository.findById(id).get();
    }

    @Override
    public String add(@Valid ChiTietSanPham doiTuong, BindingResult result) {
        Boolean check = true;
        if (result.hasErrors()) {
            check = false;
        }
        if (doiTuong.getGiaNhap() != null && doiTuong.getGiaBan() != null) {
            if (doiTuong.getGiaBan().compareTo(doiTuong.getGiaNhap()) < 0) {
                result.rejectValue("giaBan", "NotNull", "Giá bán phải lớn hơn giá nhập");
                check = false;
            }
        }
        if (!check) {
            return "chitietsanpham/home";
        }
        doiTuong.setTrangThai(0);
        repository.save(doiTuong);
        return "redirect:/admin/chi-tiet-san-pham/hien-thi";
    }

    @Override
    public String update(@Valid ChiTietSanPham doiTuong, UUID id, BindingResult result) {
        Boolean check = true;
        if (result.hasErrors()) {
            check = false;
        }
        if (doiTuong.getGiaNhap() != null && doiTuong.getGiaBan() != null) {
            if (doiTuong.getGiaBan().compareTo(doiTuong.getGiaNhap()) < 0) {
                result.rejectValue("giaBan", "NotNull", "Giá bán phải lớn hơn giá nhập");
                check = false;
            }
        }
        if (!check) {
            return "chitietsanpham/update";
        }
        BeanUtils.copyProperties(doiTuong, getOne(id));
        repository.save(doiTuong);
        return "redirect:/admin/chi-tiet-san-pham/hien-thi";
    }

    @Override
    public void remove(UUID id) {
        ChiTietSanPham chiTietSanPham = getOne(id);
        repository.delete(chiTietSanPham);
    }

}
