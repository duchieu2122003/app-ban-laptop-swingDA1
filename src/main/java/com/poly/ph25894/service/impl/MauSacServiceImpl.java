package com.poly.ph25894.service.impl;

import com.poly.ph25894.entity.MauSac;
import com.poly.ph25894.entity.base.CodeAuto;
import com.poly.ph25894.repository.MauSacRepository;
import com.poly.ph25894.service.MauSacService;
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
public class MauSacServiceImpl implements MauSacService {

    @Autowired
    private MauSacRepository mauSacRepository;

    @Override
    public Page<MauSac> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return mauSacRepository.getAll(pageable);
    }

    @Override
    public List<MauSac> findAll() {
        return mauSacRepository.findAllAddChiTietSanPham();
    }

    @Override
    public MauSac getOne(UUID id) {
        return mauSacRepository.findById(id).get();
    }

    @Override
    public String add(@Valid MauSac mauSac, BindingResult result) {
        if (result.hasErrors()) {
            return "mausac/home";
        }
        mauSac = MauSac.builder()
                .ma(new CodeAuto().generateRandomCode("MS"))
                .ten(mauSac.getTen())
                .trangThai(0)
                .build();
        mauSacRepository.save(mauSac);
        return "redirect:/admin/mau-sac/hien-thi";
    }

    @Override
    public String update(@Valid MauSac mauSac, UUID id, BindingResult result) {
        if (result.hasErrors()) {
            return "mausac/update";
        }
        BeanUtils.copyProperties(mauSac, getOne(id));
        mauSacRepository.save(mauSac);
        return "redirect:/admin/mau-sac/hien-thi";
    }

    @Override
    public void remove(UUID id) {
        MauSac mauSac = getOne(id);
        mauSacRepository.delete(mauSac);
    }

}
