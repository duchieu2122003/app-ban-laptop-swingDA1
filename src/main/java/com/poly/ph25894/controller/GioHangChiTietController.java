package com.poly.ph25894.controller;

import com.poly.ph25894.entity.GioHangChiTiet;
import com.poly.ph25894.entity.GioHangChiTietId;
import com.poly.ph25894.model.respone.GioHangChiTietRespone;
import com.poly.ph25894.service.ChiTietSanPhamService;
import com.poly.ph25894.service.GioHangChiTietService;
import com.poly.ph25894.service.GioHangService;
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
@RequestMapping("/admin/gio-hang-chi-tiet")
public class GioHangChiTietController {

    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    private GioHangService gioHangService;

    @Autowired
    private GioHangChiTietService gioHangChiTietService;

    @GetMapping("/hien-thi")
    private String hienThi(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
        model.addAttribute("gioHangChiTiet", gioHangChiTiet);
        goHome(model, pageNo);
        return "giohangchitiet/home";
    }

    private void goHome(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<GioHangChiTietRespone> getPage = gioHangChiTietService.getAll(pageNo);
        model.addAttribute("listGioHangChiTiet", getPage.getContent());
        model.addAttribute("listGioHang", gioHangService.findAll());
        model.addAttribute("listChiTietSanPham", chiTietSanPhamService.findAll());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", getPage.getTotalPages());
    }

    @GetMapping("/detail/{idGioHang}/{idChiTiet}")
    private String detail(Model model, @PathVariable UUID idGioHang, @PathVariable UUID idChiTiet,
                          @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        model.addAttribute("gioHangChiTiet",
                gioHangChiTietService.getOne(new GioHangChiTietId(idGioHang, idChiTiet)));
        goHome(model, pageNo);
        return "giohangchitiet/home";
    }

    @PostMapping("/add")
    private String add(@Valid @ModelAttribute("gioHangChiTiet") GioHangChiTiet gioHangChiTiet, BindingResult result,
                       Model model) {
        model.addAttribute("gioHangChiTiet", gioHangChiTiet);
        goHome(model, 1);
        return gioHangChiTietService.add(gioHangChiTiet, result);
    }

    @GetMapping("/view-update/{idGioHang}/{idChiTiet}")
    private String viewUpdate(Model model, @PathVariable UUID idGioHang, @PathVariable UUID idChiTiet) {
        model.addAttribute("gioHangChiTiet",
                gioHangChiTietService.getOne(new GioHangChiTietId(idGioHang, idChiTiet)));
        goHome(model, 1);
        return "giohangchitiet/update";
    }

    @PostMapping("/update/{idGioHang}/{idChiTiet}")
    private String update(@Valid @ModelAttribute("gioHangChiTiet") GioHangChiTiet gioHangChiTiet,
                          BindingResult result, @PathVariable UUID idGioHang, @PathVariable UUID idChiTiet, Model model) {
        model.addAttribute("gioHangChiTiet", gioHangChiTiet);
        goHome(model, 1);
        return gioHangChiTietService.update(gioHangChiTiet, result, new GioHangChiTietId(idGioHang, idChiTiet));
    }

    @GetMapping("/remove/{idGioHang}/{idChiTiet}")
    private String remove(Model model, @PathVariable UUID idGioHang, @PathVariable UUID idChiTiet) {
        goHome(model, 1);
        gioHangChiTietService.remove(new GioHangChiTietId(idGioHang, idChiTiet));
        return "redirect:/admin/gio-hang-chi-tiet/hien-thi";
    }

}
