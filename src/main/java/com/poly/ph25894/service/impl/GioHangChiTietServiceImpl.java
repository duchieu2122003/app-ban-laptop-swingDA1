package com.poly.ph25894.service.impl;

import com.poly.ph25894.entity.GioHangChiTiet;
import com.poly.ph25894.entity.GioHangChiTietId;
import com.poly.ph25894.model.respone.GioHangChiTietRespone;
import com.poly.ph25894.repository.ChiTietSanPhamRepository;
import com.poly.ph25894.repository.GioHangChiTietRepository;
import com.poly.ph25894.service.GioHangChiTietService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.math.BigDecimal;

@Service
public class GioHangChiTietServiceImpl implements GioHangChiTietService {

    @Autowired
    private GioHangChiTietRepository repository;

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Override
    public Page<GioHangChiTietRespone> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return repository.getAllCustom(pageable);
    }

    @Override
    public GioHangChiTiet getOne(GioHangChiTietId gioHangChiTietId) {
        return repository.findById(gioHangChiTietId).get();
    }

    @Override
    public String add(@Valid GioHangChiTiet doiTuong, BindingResult result) {
        if (result.hasErrors()) {
            return "giohangchitiet/home";
        }
        if (repository.findByIdGioHangAndIdChiTietSanPham(doiTuong.getIdGioHang(), doiTuong.getIdChiTietSanPham())
                != null) {
            GioHangChiTiet gioHangChiTiet = repository.findByIdGioHangAndIdChiTietSanPham(doiTuong.getIdGioHang(),
                    doiTuong.getIdChiTietSanPham());
            Integer soLuong = doiTuong.getSoLuong() + gioHangChiTiet.getSoLuong();
            Integer donGia = soLuong * chiTietSanPhamRepository
                    .findChiTietSanPhamById(doiTuong.getIdChiTietSanPham()).getGiaBan().intValue();
            doiTuong.setSoLuong(soLuong);
            doiTuong.setDonGia(BigDecimal.valueOf(donGia));
            repository.save(doiTuong);
            return "redirect:/admin/gio-hang-chi-tiet/hien-thi";
        }
        Integer donGia = doiTuong.getSoLuong() * chiTietSanPhamRepository
                .findChiTietSanPhamById(doiTuong.getIdChiTietSanPham()).getGiaBan().intValue();
        doiTuong.setDonGia(BigDecimal.valueOf(donGia));
        repository.save(doiTuong);
        return "redirect:/admin/gio-hang-chi-tiet/hien-thi";
    }

    @Override
    public String update(@Valid GioHangChiTiet doiTuong, BindingResult result, GioHangChiTietId gioHangChiTietId) {
        if (result.hasErrors()) {
            return "giohangchitiet/update";
        }
        Integer donGia = doiTuong.getSoLuong() * chiTietSanPhamRepository
                .findChiTietSanPhamById(doiTuong.getIdChiTietSanPham()).getGiaBan().intValue();
        doiTuong.setDonGia(BigDecimal.valueOf(donGia));
        repository.save(doiTuong);
        return "redirect:/admin/gio-hang-chi-tiet/hien-thi";
    }

    @Override
    public void remove(GioHangChiTietId gioHangChiTietId) {
        repository.delete(getOne(gioHangChiTietId));
    }

}
