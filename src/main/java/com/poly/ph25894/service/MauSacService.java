package com.poly.ph25894.service;

import com.poly.ph25894.entity.MauSac;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import java.util.List;
import java.util.UUID;

public interface MauSacService {

    Page<MauSac> getAll(Integer pageNo);

    List<MauSac> findAll();

    MauSac getOne(UUID id);

    String add(@Valid MauSac mauSac, BindingResult result);

    String update(@Valid MauSac mauSac, UUID id, BindingResult result);

    void remove(UUID id);

}
