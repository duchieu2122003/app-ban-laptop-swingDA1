package com.poly.ph25894.controller;

import com.poly.ph25894.entity.KhachHang;
import com.poly.ph25894.service.KhachHangService;
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
@RequestMapping("/admin/khach-hang")
public class KhachHangController {

    @Autowired
    private KhachHangService khachHangService;

    @GetMapping("/hien-thi")
    private String hienThi(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        KhachHang khachHang = new KhachHang();
        khachHang.setNgaySinh(Date.valueOf(LocalDate.now()));
        model.addAttribute("khachHang", khachHang);
        goHome(model, pageNo);
        return "khachhang/home";
    }

    private void goHome(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<KhachHang> getPage = khachHangService.getAll(pageNo);
        model.addAttribute("listKhachHang", getPage.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", getPage.getTotalPages());
    }

    @GetMapping("/detail/{id}")
    private String detail(Model model, @PathVariable UUID id,
                          @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        model.addAttribute("khachHang", khachHangService.getOne(id));
        goHome(model, pageNo);
        return "khachhang/home";
    }

    @PostMapping("/add")
    private String add(@Valid @ModelAttribute("khachHang") KhachHang khachHang
            , BindingResult result, Model model) {
        model.addAttribute("khachHang", khachHang);
        goHome(model, 1);
        return khachHangService.add(khachHang, result);
    }

    @GetMapping("/view-update/{id}")
    private String viewUpdate(Model model, @PathVariable UUID id) {
        model.addAttribute("khachHang", khachHangService.getOne(id));
        goHome(model, 1);
        return "khachhang/update";
    }

    @PostMapping("/update/{id}")
    private String update(Model model, @Valid @ModelAttribute("khachHang") KhachHang khachHang,
                          BindingResult result, @PathVariable("id") UUID id) {
        khachHang.setNgaySinh(Date.valueOf(LocalDate.now()));
        model.addAttribute("khachHang", khachHang);
        goHome(model, 1);
        return khachHangService.update(khachHang, result, id);
    }

    @GetMapping("/remove/{id}")
    private String remove(Model model, @PathVariable("id") UUID id) {
        model.addAttribute("message", "Remove thành công");
        KhachHang khachHang = new KhachHang();
        khachHang.setNgaySinh(Date.valueOf(LocalDate.now()));
        model.addAttribute("khachHang", khachHang);
        khachHangService.remove(id);
        goHome(model, 1);
        return "khachhang/home";
    }

}
