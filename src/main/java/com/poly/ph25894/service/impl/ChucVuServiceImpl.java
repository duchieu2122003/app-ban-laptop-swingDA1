package com.poly.ph25894.service.impl;

import com.poly.ph25894.entity.ChucVu;
import com.poly.ph25894.entity.base.CodeAuto;
import com.poly.ph25894.repository.ChucVuRepository;
import com.poly.ph25894.service.ChucVuService;
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
public class ChucVuServiceImpl implements ChucVuService {

    @Autowired
    private ChucVuRepository repository;

    @Override
    public Page<ChucVu> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return repository.getAll(pageable);
    }

    @Override
    public List<ChucVu> findAll() {
        return repository.listChucVu();
    }

    @Override
    public ChucVu getOne(UUID id) {
        return repository.findById(id).get();
    }

    @Override
    public String add(@Valid ChucVu doiTuong, BindingResult result) {
        if (result.hasErrors()) {
            return "chucvu/home";
        }
        doiTuong = ChucVu.builder()
                .ma(new CodeAuto().generateRandomCode("SP"))
                .ten(doiTuong.getTen())
                .trangThai(0)
                .build();
        repository.save(doiTuong);
        return "redirect:/admin/chuc-vu/hien-thi";
    }

    @Override
    public String update(@Valid ChucVu doiTuong, UUID id, BindingResult result) {
        if (result.hasErrors()) {
            return "chucvu/update";
        }
        BeanUtils.copyProperties(doiTuong, getOne(id));
        repository.save(doiTuong);
        return "redirect:/admin/chuc-vu/hien-thi";
    }

    @Override
    public void remove(UUID id) {
        ChucVu doiTuong = getOne(id);
        repository.delete(doiTuong);
    }

}
