package com.poly.ph25894.service.impl;

import com.poly.ph25894.entity.DongSanPham;
import com.poly.ph25894.entity.base.CodeAuto;
import com.poly.ph25894.repository.DongSanPhamRepository;
import com.poly.ph25894.service.DongSanPhamService;
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
public class DongSanPhamServiceImpl implements DongSanPhamService {

    @Autowired
    private DongSanPhamRepository dongSanPhamRepository;

    @Override
    public Page<DongSanPham> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return dongSanPhamRepository.getAll(pageable);
    }

    @Override
    public List<DongSanPham> findAll() {
        return dongSanPhamRepository.findAllAddChiTietSanPham();
    }

    @Override
    public DongSanPham getOne(UUID id) {
        return dongSanPhamRepository.findById(id).get();
    }

    @Override
    public String add(@Valid DongSanPham doiTuong, BindingResult result) {
        if (result.hasErrors()) {
            return "dongsanpham/home";
        }
        doiTuong = DongSanPham.builder()
                .ma(new CodeAuto().generateRandomCode("SP"))
                .ten(doiTuong.getTen())
                .trangThai(0)
                .build();
        dongSanPhamRepository.save(doiTuong);
        return "redirect:/admin/dong-san-pham/hien-thi";
    }

    @Override
    public String update(@Valid DongSanPham doiTuong, UUID id, BindingResult result) {
        if (result.hasErrors()) {
            return "dongsanpham/update";
        }
        BeanUtils.copyProperties(doiTuong, getOne(id));
        dongSanPhamRepository.save(doiTuong);
        return "redirect:/admin/dong-san-pham/hien-thi";
    }

    @Override
    public void remove(UUID id) {
        DongSanPham doiTuong = getOne(id);
        dongSanPhamRepository.delete(doiTuong);
    }

}
