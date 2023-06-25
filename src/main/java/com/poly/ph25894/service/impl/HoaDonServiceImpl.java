package com.poly.ph25894.service.impl;

import com.poly.ph25894.entity.HoaDon;
import com.poly.ph25894.entity.base.CodeAuto;
import com.poly.ph25894.repository.HoaDonRepository;
import com.poly.ph25894.service.HoaDonService;
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
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    private HoaDonRepository repository;

    @Override
    public Page<HoaDon> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return repository.findAll(pageable);
    }

    @Override
    public List<HoaDon> findAll() {
        return repository.findAll();
    }

    @Override
    public HoaDon getOne(UUID id) {
        return repository.findById(id).get();
    }

    @Override
    public String add(@Valid HoaDon doiTuong, BindingResult result) {
        System.out.println(result);
        if (result.hasErrors()) {
            return "hoadon/home";
        }
        if (doiTuong.getNgayThanhToan() != null && doiTuong.getNgayNhan() != null) {
            if (doiTuong.getNgayNhan().compareTo(doiTuong.getNgayThanhToan()) < 0) {
                result.rejectValue("ngayNhan", "invalid",
                        "Ngày nhận phải lớn hơn ngày thanh toán");
                return "hoadon/home";
            }
        }
        if (doiTuong.getTrangThai() == 1) {
            doiTuong.setNgayNhan(null);
            doiTuong.setNgayThanhToan(null);
        }
        doiTuong.setMa(new CodeAuto().generateRandomCode("HD"));
        doiTuong.setNgayTao(Date.valueOf(LocalDate.now()));
        repository.save(doiTuong);
        return "redirect:/admin/hoa-don/hien-thi";
    }

    @Override
    public String update(@Valid HoaDon doiTuong, BindingResult result, UUID id) {
        if (doiTuong.getTrangThai() == 1) {
            doiTuong.setNgayThanhToan(null);
            doiTuong.setNgayNhan(null);
        }
        if (result.hasErrors()) {
            return "hoadon/update";
        }
        if (doiTuong.getNgayThanhToan() != null && doiTuong.getNgayNhan() != null) {
            if (doiTuong.getNgayNhan().compareTo(doiTuong.getNgayThanhToan()) < 0) {
                result.rejectValue("ngayNhan", "invalid",
                        "Ngày nhận phải lớn hơn ngày thanh toán");
                return "hoadon/update";
            }
        }
        if (doiTuong.getTrangThai() == 0 && result.hasErrors()) {
            return "hoadon/update";
        }
        BeanUtils.copyProperties(doiTuong, getOne(id));
        repository.save(doiTuong);
        return "redirect:/admin/hoa-don/hien-thi";
    }

    @Override
    public void remove(UUID id) {
        repository.delete(getOne(id));
    }

}
