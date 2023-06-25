package com.poly.ph25894.service;

import com.poly.ph25894.entity.HoaDon;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import java.util.List;
import java.util.UUID;

public interface HoaDonService {

    Page<HoaDon> getAll(Integer pageNo);

    List<HoaDon> findAll();

    HoaDon getOne(UUID id);

    String add(@Valid HoaDon doiTuong, BindingResult result);

    String update(@Valid HoaDon doiTuong, BindingResult result, UUID id);

    void remove(UUID id);

}
