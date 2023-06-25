package com.poly.ph25894.service.impl;

import com.poly.ph25894.entity.HoaDonChiTiet;
import com.poly.ph25894.entity.HoaDonChiTietId;
import com.poly.ph25894.model.respone.HoaDonChiTietRespone;
import com.poly.ph25894.repository.ChiTietSanPhamRepository;
import com.poly.ph25894.repository.HoaDonChiTietRepository;
import com.poly.ph25894.service.HoaDonChiTietService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.math.BigDecimal;

@Service
public class HoaDonChiTietServiceImpl implements HoaDonChiTietService {

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Override
    public Page<HoaDonChiTietRespone> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return hoaDonChiTietRepository.getAllCustom(pageable);
    }

    @Override
    public HoaDonChiTiet getOne(HoaDonChiTietId hoaDonChiTietId) {
        return hoaDonChiTietRepository.findById(hoaDonChiTietId).get();
    }

    @Override
    public String add(@Valid HoaDonChiTiet doiTuong, BindingResult result) {
        if (result.hasErrors()) {
            return "hoadonchitiet/home";
        }
        if (hoaDonChiTietRepository.findByIdHoaDonAndIdChiTietSanPham(doiTuong.getIdHoaDon(),
                doiTuong.getIdChiTietSanPham()) != null) {
            HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepository.findByIdHoaDonAndIdChiTietSanPham(
                    doiTuong.getIdHoaDon(), doiTuong.getIdChiTietSanPham());
            Integer soLuong = doiTuong.getSoLuong() + hoaDonChiTiet.getSoLuong();
            Integer donGia = soLuong * chiTietSanPhamRepository
                    .findChiTietSanPhamById(doiTuong.getIdChiTietSanPham()).getGiaBan().intValue();
            doiTuong.setSoLuong(soLuong);
            doiTuong.setDonGia(BigDecimal.valueOf(donGia));
            hoaDonChiTietRepository.save(doiTuong);
            return "redirect:/admin/hoa-don-chi-tiet/hien-thi";
        }
        Integer donGia = doiTuong.getSoLuong() * chiTietSanPhamRepository
                .findChiTietSanPhamById(doiTuong.getIdChiTietSanPham()).getGiaBan().intValue();
        doiTuong.setDonGia(BigDecimal.valueOf(donGia));
        hoaDonChiTietRepository.save(doiTuong);
        return "redirect:/admin/hoa-don-chi-tiet/hien-thi";
    }

    @Override
    public String update(@Valid HoaDonChiTiet doiTuong, BindingResult result, HoaDonChiTietId hoaDonChiTietId) {
        if (result.hasErrors()) {
            return "hoadonchitiet/update";
        }
        Integer donGia = doiTuong.getSoLuong() * chiTietSanPhamRepository
                .findChiTietSanPhamById(doiTuong.getIdChiTietSanPham()).getGiaBan().intValue();
        doiTuong.setDonGia(BigDecimal.valueOf(donGia));
        hoaDonChiTietRepository.save(doiTuong);
        return "redirect:/admin/hoa-don-chi-tiet/hien-thi";
    }

    @Override
    public void remove(HoaDonChiTietId hoaDonChiTietId) {
        hoaDonChiTietRepository.delete(getOne(hoaDonChiTietId));
    }

}
