package com.poly.ph25894.controller;

import com.poly.ph25894.entity.CuaHang;
import com.poly.ph25894.service.CuaHangService;
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
@RequestMapping("/admin/cua-hang")
public class CuaHangController {

    @Autowired
    private CuaHangService cuaHangService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        model.addAttribute("cuaHang", new CuaHang());
        goHome(model, pageNo);
        return "cuahang/home";
    }

    public void goHome(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<CuaHang> getPage = cuaHangService.getAll(pageNo);
        model.addAttribute("listCuaHang", getPage.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", getPage.getTotalPages());
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable UUID id,
                         @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        model.addAttribute("cuaHang", cuaHangService.getOne(id));
        goHome(model, pageNo);
        return "cuahang/home";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("cuaHang") CuaHang cuaHang, BindingResult result, Model model) {
        goHome(model, 1);
        return cuaHangService.add(cuaHang, result);
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable UUID id) {
        model.addAttribute("cuaHang", cuaHangService.getOne(id));
        return "cuahang/update";
    }

    @PostMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("cuaHang") CuaHang cuaHang,
                         BindingResult result) {
        goHome(model, 1);
        return cuaHangService.update(cuaHang, id, result);
    }

    @GetMapping("/remove/{id}")
    public String remove(Model model, @PathVariable("id") UUID id) {
        model.addAttribute("message", "Remove thành công");
        model.addAttribute("cuaHang", new CuaHang());
        cuaHangService.remove(id);
        goHome(model, 1);
        return "cuahang/home";
    }

}
