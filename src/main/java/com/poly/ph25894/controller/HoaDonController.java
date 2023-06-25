package com.poly.ph25894.controller;

import com.poly.ph25894.entity.HoaDon;
import com.poly.ph25894.service.HoaDonService;
import com.poly.ph25894.service.KhachHangService;
import com.poly.ph25894.service.NhanVienService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Controller
@RequestMapping("/admin/hoa-don")
public class HoaDonController {

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping("/hien-thi")
    private String hienThi(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setNgayThanhToan(Date.valueOf(LocalDate.now()));
        hoaDon.setNgayNhan(Date.valueOf(LocalDate.now()));
        model.addAttribute("hoaDon", hoaDon);
        goHome(model, pageNo);
        return "hoadon/home";
    }

    private void goHome(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<HoaDon> getPage = hoaDonService.getAll(pageNo);
        model.addAttribute("listHoaDon", getPage.getContent());
        model.addAttribute("listKhachHang", khachHangService.findAll());
        model.addAttribute("listNhanVien", nhanVienService.findAll());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", getPage.getTotalPages());
    }

    @GetMapping("/detail/{id}")
    private String detail(Model model, @PathVariable UUID id,
                          @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        model.addAttribute("hoaDon", hoaDonService.getOne(id));
        goHome(model, pageNo);
        return "hoadon/home";
    }

    @GetMapping("/view-update/{id}")
    private String viewUpdate(Model model, @PathVariable UUID id) {
        model.addAttribute("hoaDon", hoaDonService.getOne(id));
        goHome(model, 1);
        return "hoadon/update";
    }

    @PostMapping("/add")
    private String add(@Valid @ModelAttribute("hoaDon") HoaDon hoaDon, BindingResult result, Model model) {
        model.addAttribute("hoaDon", hoaDon);
        goHome(model, 1);
        return hoaDonService.add(hoaDon, result);
    }

    @PostMapping("/update/{id}")
    private String update(Model model, @Valid @ModelAttribute("hoaDon") HoaDon hoaDon,
                          BindingResult result, @PathVariable("id") UUID id) {
        model.addAttribute("hoaDon", hoaDon);
        goHome(model, 1);
        return hoaDonService.update(hoaDon, result, id);
    }

    @GetMapping("/remove/{id}")
    private String remove(@PathVariable UUID id) {
        hoaDonService.remove(id);
        return "redirect:/admin/hoa-don/hien-thi";
    }

}
