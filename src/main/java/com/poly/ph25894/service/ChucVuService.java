package com.poly.ph25894.service;

import com.poly.ph25894.entity.ChucVu;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import java.util.List;
import java.util.UUID;

public interface ChucVuService {

    Page<ChucVu> getAll(Integer pageNo);

    List<ChucVu> findAll();

    ChucVu getOne(UUID id);

    String add(@Valid ChucVu doiTuong, BindingResult result);

    String update(@Valid ChucVu doiTuong, UUID id, BindingResult result);

    void remove(UUID id);

}
