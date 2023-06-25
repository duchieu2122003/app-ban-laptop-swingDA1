package com.poly.ph25894.controller;

import com.poly.ph25894.entity.DongSanPham;
import com.poly.ph25894.service.DongSanPhamService;
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
@RequestMapping("/admin/dong-san-pham")
public class DongSanPhamController {

    @Autowired
    private DongSanPhamService dongSanPhamService;

    @GetMapping("/hien-thi")
    private String hienThi(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        model.addAttribute("dongSanPham", new DongSanPham());
        goHome(model, pageNo);
        return "dongsanpham/home";
    }

    private void goHome(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<DongSanPham> getPage = dongSanPhamService.getAll(pageNo);
        model.addAttribute("listDongSp", getPage.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", getPage.getTotalPages());
    }

    @GetMapping("/detail/{id}")
    private String detail(Model model, @PathVariable UUID id, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        model.addAttribute("dongSanPham", dongSanPhamService.getOne(id));
        goHome(model, pageNo);
        return "dongsanpham/home";
    }

    @PostMapping("/add")
    private String add(Model model, @Valid @ModelAttribute("dongSanPham") DongSanPham dongSanPham, BindingResult result) {
        goHome(model, 1);
        return dongSanPhamService.add(dongSanPham, result);
    }

    @GetMapping("/view-update/{id}")
    private String viewUpdate(Model model, @PathVariable UUID id) {
        model.addAttribute("dongSanPham", dongSanPhamService.getOne(id));
        return "dongsanpham/update";
    }

    @PostMapping("/update/{id}")
    private String update(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("dongSanPham") DongSanPham dongSanPham,
                          BindingResult result) {
        goHome(model, 1);
        return dongSanPhamService.update(dongSanPham, id, result);
    }

    @GetMapping("/remove/{id}")
    private String remove(Model model, @PathVariable("id") UUID id) {
        model.addAttribute("message", "Remove thành công");
        model.addAttribute("dongSanPham", new DongSanPham());
        dongSanPhamService.remove(id);
        goHome(model, 1);
        return "dongsanpham/home";
    }

}
