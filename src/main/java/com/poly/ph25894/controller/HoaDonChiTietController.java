package com.poly.ph25894.controller;

import com.poly.ph25894.entity.HoaDonChiTiet;
import com.poly.ph25894.entity.HoaDonChiTietId;
import com.poly.ph25894.model.respone.HoaDonChiTietRespone;
import com.poly.ph25894.service.ChiTietSanPhamService;
import com.poly.ph25894.service.HoaDonChiTietService;
import com.poly.ph25894.service.HoaDonService;
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
@RequestMapping("/admin/hoa-don-chi-tiet")
public class HoaDonChiTietController {

    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping("/hien-thi")
    private String hienThi(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        model.addAttribute("hoaDonChiTiet", hoaDonChiTiet);
        goHome(model, pageNo);
        return "hoadonchitiet/home";
    }

    private void goHome(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<HoaDonChiTietRespone> getPage = hoaDonChiTietService.getAll(pageNo);
        model.addAttribute("ListHoaDonChiTiet", getPage.getContent());
        model.addAttribute("listHoaDon", hoaDonService.findAll());
        model.addAttribute("listChiTietSanPham", chiTietSanPhamService.findAll());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", getPage.getTotalPages());
    }

    @GetMapping("/detail/{idHoaDon}/{idChiTiet}")
    private String detail(Model model, @PathVariable UUID idHoaDon, @PathVariable UUID idChiTiet,
                          @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        model.addAttribute("hoaDonChiTiet",
                hoaDonChiTietService.getOne(new HoaDonChiTietId(idHoaDon, idChiTiet)));
        goHome(model, pageNo);
        return "hoadonchitiet/home";
    }

    @PostMapping("/add")
    private String add(@Valid @ModelAttribute("hoaDonChiTiet") HoaDonChiTiet hoaDonChiTiet
            , BindingResult result, Model model) {
        model.addAttribute("hoaDonChiTiet", hoaDonChiTiet);
        goHome(model, 1);
        return hoaDonChiTietService.add(hoaDonChiTiet, result);
    }

    @GetMapping("/view-update/{idHoaDon}/{idChiTiet}")
    private String viewUpdate(Model model, @PathVariable UUID idHoaDon,
                              @PathVariable UUID idChiTiet) {
        model.addAttribute("hoaDonChiTiet",
                hoaDonChiTietService.getOne(new HoaDonChiTietId(idHoaDon, idChiTiet)));
        goHome(model, 1);
        return "hoadonchitiet/update";
    }

    @PostMapping("/update/{idHoaDon}/{idChiTiet}")
    private String update(Model model, @Valid @ModelAttribute("hoaDonChiTiet") HoaDonChiTiet hoaDonChiTiet,
                          BindingResult result, @PathVariable UUID idHoaDon, @PathVariable UUID idChiTiet) {
        model.addAttribute("hoaDonChiTiet", hoaDonChiTiet);
        goHome(model, 1);
        return hoaDonChiTietService.update(hoaDonChiTiet, result, new HoaDonChiTietId(idHoaDon, idChiTiet));
    }

    @GetMapping("/remove/{idHoaDon}/{idChiTiet}")
    private String remove(Model model, @PathVariable UUID idHoaDon, @PathVariable UUID idChiTiet) {
        goHome(model, 1);
        hoaDonChiTietService.remove(new HoaDonChiTietId(idHoaDon, idChiTiet));
        model.addAttribute("message", "Xóa thành công");
        return "redirect:/admin/hoa-don-chi-tiet/hien-thi";
    }

}
