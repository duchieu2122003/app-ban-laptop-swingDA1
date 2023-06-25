package com.poly.ph25894.controller;

import com.poly.ph25894.entity.NhanVien;
import com.poly.ph25894.service.ChucVuService;
import com.poly.ph25894.service.CuaHangService;
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
@RequestMapping("/admin/nhan-vien")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private CuaHangService cuaHangService;

    @Autowired
    private ChucVuService chucVuService;


    @GetMapping("/hien-thi")
    private String hienThi(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setNgaySinh(Date.valueOf(LocalDate.now()));
        model.addAttribute("nhanVien", nhanVien);
        goHome(model, pageNo);
        return "nhanvien/home";
    }

    private void goHome(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<NhanVien> getPage = nhanVienService.getAll(pageNo);
        model.addAttribute("ngaySinh", LocalDate.now());
        model.addAttribute("listNhanVien", getPage.getContent());
        model.addAttribute("listCuaHang", cuaHangService.findAll());
        model.addAttribute("listChucVu", chucVuService.findAll());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", getPage.getTotalPages());
    }

    @GetMapping("/detail/{id}")
    private String detail(Model model, @PathVariable UUID id,
                          @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        model.addAttribute("nhanVien", nhanVienService.getOne(id));
        goHome(model, pageNo);
        return "nhanvien/home";
    }

    @PostMapping("/add")
    private String add(@Valid @ModelAttribute("nhanVien") NhanVien nhanVien
            , BindingResult result, Model model) {
        model.addAttribute("nhanVien", nhanVien);
        goHome(model, 1);
        return nhanVienService.add(nhanVien, result);
    }

    @GetMapping("/view-update/{id}")
    private String viewUpdate(Model model, @PathVariable UUID id) {
        model.addAttribute("nhanVien", nhanVienService.getOne(id));
        goHome(model, 1);
        return "nhanvien/update";
    }

    @PostMapping("/update/{id}")
    private String update(Model model, @Valid @ModelAttribute("nhanVien") NhanVien nhanVien,
                          BindingResult result, @PathVariable("id") UUID id) {
        model.addAttribute("nhanVien", nhanVien);
        goHome(model, 1);
        return nhanVienService.update(nhanVien, result, id);
    }

    @GetMapping("/remove/{id}")
    private String remove(Model model, @PathVariable("id") UUID id) {
        model.addAttribute("message", "Remove thành công");
        NhanVien nhanVien = new NhanVien();
        nhanVien.setNgaySinh(Date.valueOf(LocalDate.now()));
        model.addAttribute("nhanVien", nhanVien);
        nhanVienService.remove(id);
        goHome(model, 1);
        return "nhanvien/home";
    }

}
