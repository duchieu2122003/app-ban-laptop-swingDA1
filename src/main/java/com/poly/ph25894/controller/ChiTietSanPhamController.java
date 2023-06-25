package com.poly.ph25894.controller;

import com.poly.ph25894.entity.ChiTietSanPham;
import com.poly.ph25894.service.ChiTietSanPhamService;
import com.poly.ph25894.service.DongSanPhamService;
import com.poly.ph25894.service.MauSacService;
import com.poly.ph25894.service.SanPhamService;
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
import java.util.UUID;

@Controller
@RequestMapping("/admin/chi-tiet-san-pham")
public class ChiTietSanPhamController {

    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private DongSanPhamService dongSanPhamService;

    @Autowired
    private MauSacService mauSacService;

    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @GetMapping("/hien-thi")
    private String hienThi(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        model.addAttribute("chiTiet", new ChiTietSanPham());
        goHome(model, pageNo);
        return "chitietsanpham/home";
    }

    private void goHome(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<ChiTietSanPham> getPage = chiTietSanPhamService.getAll(pageNo);
        model.addAttribute("listChiTiet", getPage.getContent());
        model.addAttribute("listSanPham", sanPhamService.findAll());
        model.addAttribute("listDongSp", dongSanPhamService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", getPage.getTotalPages());
    }

    @GetMapping("/detail/{id}")
    private String detail(Model model, @PathVariable UUID id,
                          @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        model.addAttribute("chiTiet", chiTietSanPhamService.getOne(id));
        goHome(model, pageNo);
        return "chitietsanpham/home";
    }

    @PostMapping("/add")
    private String add(Model model, @Valid @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham
            , BindingResult result) {
        goHome(model, 1);
        return chiTietSanPhamService.add(chiTietSanPham, result);
    }

    @GetMapping("/view-update/{id}")
    private String viewUpdate(Model model, @PathVariable UUID id) {
        model.addAttribute("chiTiet", chiTietSanPhamService.getOne(id));
        goHome(model, 1);
        return "chitietsanpham/update";
    }

    @PostMapping("/update/{id}")
    private String update(Model model, @Valid @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham,
                          BindingResult result, @PathVariable("id") UUID id) {
        goHome(model, 1);
        return chiTietSanPhamService.update(chiTietSanPham, id, result);
    }

    @GetMapping("/remove/{id}")
    private String remove(Model model, @PathVariable("id") UUID id) {
        model.addAttribute("message", "Remove thành công");
        model.addAttribute("chiTiet", new ChiTietSanPham());
        chiTietSanPhamService.remove(id);
        goHome(model, 1);
        return "chitietsanpham/home";
    }

}
