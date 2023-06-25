package com.poly.ph25894.controller;

import com.poly.ph25894.entity.GioHang;
import com.poly.ph25894.service.GioHangService;
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
@RequestMapping("/admin/gio-hang")
public class GioHangController {

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private GioHangService gioHangService;

    @GetMapping("/hien-thi")
    private String hienThi(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        GioHang gioHang = new GioHang();
        gioHang.setNgayThanhToan(Date.valueOf(LocalDate.now()));
        model.addAttribute("gioHang", gioHang);
        goHome(model, pageNo);
        return "giohang/home";
    }

    private void goHome(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<GioHang> getPage = gioHangService.getAll(pageNo);
        model.addAttribute("listGioHang", getPage.getContent());
        model.addAttribute("listKhachHang", khachHangService.findAll());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", getPage.getTotalPages());
    }

    @GetMapping("/detail/{id}")
    private String detail(Model model, @PathVariable UUID id,
                          @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        model.addAttribute("gioHang", gioHangService.getOne(id));
        goHome(model, pageNo);
        return "giohang/home";
    }

    @PostMapping("/add")
    private String add(@Valid @ModelAttribute("gioHang") GioHang gioHang
            , BindingResult result, Model model) {
        model.addAttribute("gioHang", gioHang);
        goHome(model, 1);
        return gioHangService.add(gioHang, result);
    }

    @GetMapping("/view-update/{id}")
    private String viewUpdate(Model model, @PathVariable UUID id) {
        model.addAttribute("gioHang", gioHangService.getOne(id));
        goHome(model, 1);
        return "giohang/update";
    }

    @PostMapping("/update/{id}")
    private String update(Model model, @Valid @ModelAttribute("gioHang") GioHang gioHang,
                          BindingResult result, @PathVariable("id") UUID id) {
        model.addAttribute("gioHang", gioHang);
        goHome(model, 1);
        return gioHangService.update(gioHang, result, id);
    }

    @GetMapping("/remove/{id}")
    private String remove(Model model, @PathVariable UUID id) {
        gioHangService.remove(id);
        goHome(model, 1);
        return "redirect:/admin/gio-hang/hien-thi";
    }

}
