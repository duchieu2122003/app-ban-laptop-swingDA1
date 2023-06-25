package com.poly.ph25894.service.impl;

import com.poly.ph25894.entity.SanPham;
import com.poly.ph25894.entity.base.CodeAuto;
import com.poly.ph25894.repository.SanPhamRepository;
import com.poly.ph25894.service.SanPhamService;
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
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Override
    public Page<SanPham> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return sanPhamRepository.getAll(pageable);
    }

    @Override
    public List<SanPham> findAll() {
        return sanPhamRepository.findAllAddChiTietSanPham();
    }

    @Override
    public SanPham getOne(UUID id) {
        return sanPhamRepository.findById(id).get();
    }

    @Override
    public String addSanPham(@Valid SanPham sanPham, BindingResult result) {
        if (result.hasErrors()) {
            return "sanpham/home";
        }
        sanPham = SanPham.builder()
                .ma(new CodeAuto().generateRandomCode("SP"))
                .ten(sanPham.getTen())
                .trangThai(0)
                .build();
        sanPhamRepository.save(sanPham);
        return "redirect:/admin/san-pham/hien-thi";
    }

    @Override
    public String updateSanPham(@Valid SanPham sanPham, UUID id, BindingResult result) {
        if (result.hasErrors()) {
            return "sanpham/update";
        }
        BeanUtils.copyProperties(sanPham, getOne(id));
        sanPhamRepository.save(sanPham);
        return "redirect:/admin/san-pham/hien-thi";
    }

    @Override
    public void removeSanPham(UUID id) {
        SanPham sanPham = getOne(id);
        sanPhamRepository.delete(sanPham);
    }

}
