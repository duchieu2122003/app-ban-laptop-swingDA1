package com.poly.ph25894.controller;

import com.poly.ph25894.entity.ChucVu;
import com.poly.ph25894.service.ChucVuService;
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
@RequestMapping("/admin/chuc-vu")
public class ChucVuController {

    @Autowired
    private ChucVuService chucVuService;

    @GetMapping("/hien-thi")
    private String hienThi(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        model.addAttribute("chucVu", new ChucVu());
        goHome(model, pageNo);
        return "chucvu/home";
    }

    private void goHome(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<ChucVu> getPage = chucVuService.getAll(pageNo);
        model.addAttribute("listChucVu", getPage.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", getPage.getTotalPages());

    }

    @GetMapping("/detail/{id}")
    private String detail(Model model, @PathVariable UUID id,
                          @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        model.addAttribute("chucVu", chucVuService.getOne(id));
        goHome(model, pageNo);
        return "chucvu/home";
    }

    @PostMapping("/add")
    private String add(Model model, @Valid @ModelAttribute("chucVu") ChucVu chucVu, BindingResult result) {
        goHome(model, 1);
        return chucVuService.add(chucVu, result);
    }

    @GetMapping("/view-update/{id}")
    private String viewUpdate(Model model, @PathVariable UUID id) {
        model.addAttribute("chucVu", chucVuService.getOne(id));
        return "chucvu/update";
    }

    @PostMapping("/update/{id}")
    private String update(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("chucVu") ChucVu chucVu,
                          BindingResult result) {
        goHome(model, 1);
        return chucVuService.update(chucVu, id, result);
    }

    @GetMapping("/remove/{id}")
    private String remove(Model model, @PathVariable("id") UUID id) {
        model.addAttribute("message", "Remove thành công");
        model.addAttribute("chucVu", new ChucVu());
        chucVuService.remove(id);
        goHome(model, 1);
        return "chucvu/home";
    }

}
