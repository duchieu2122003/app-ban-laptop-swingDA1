package com.poly.ph25894.controller;

import com.poly.ph25894.entity.SanPham;
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
@RequestMapping("/admin/san-pham")
public class SanPhamController {

    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        model.addAttribute("sanPham", new SanPham());
        goHome(model, pageNo);
        return "/sanpham/home";
    }

    public void goHome(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<SanPham> getPage = sanPhamService.getAll(pageNo);
        model.addAttribute("listSanPham", getPage.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", getPage.getTotalPages());
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable UUID id, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        model.addAttribute("sanPham", sanPhamService.getOne(id));
        goHome(model, pageNo);
        return "/sanpham/home";
    }

    @PostMapping("/add")
    public String add(Model model, @Valid @ModelAttribute("sanPham") SanPham sanPham, BindingResult result) {
        goHome(model, 1);
        return sanPhamService.addSanPham(sanPham, result);
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable UUID id, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        model.addAttribute("sanPham", sanPhamService.getOne(id));
        return "sanpham/update";
    }

    @PostMapping("/update/{id}")
    public String update(Model model, @PathVariable UUID id, @Valid @ModelAttribute("sanPham") SanPham sanPham,
                         BindingResult result) {
        goHome(model, 1);
        return sanPhamService.updateSanPham(sanPham, id, result);
    }

    @GetMapping("/remove/{id}")
    public String remove(Model model, @PathVariable("id") UUID id) {
        model.addAttribute("message", "Remove thành công");
        model.addAttribute("sanPham", new SanPham());
        sanPhamService.removeSanPham(id);
        goHome(model, 1);
        return "sanpham/home";
    }

}
