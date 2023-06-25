package com.poly.ph25894.controller;

import com.poly.ph25894.entity.MauSac;
import com.poly.ph25894.service.MauSacService;
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
@RequestMapping("/admin/mau-sac")
public class MauSacController {

    @Autowired
    private MauSacService mauSacService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        model.addAttribute("mauSac", new MauSac());
        goHome(model, pageNo);
        return "mausac/home";
    }

    public void goHome(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<MauSac> getPage = mauSacService.getAll(pageNo);
        model.addAttribute("listMauSac", getPage.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", getPage.getTotalPages());
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable UUID id, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        model.addAttribute("mauSac", mauSacService.getOne(id));
        goHome(model, pageNo);
        return "mausac/home";
    }

    @PostMapping("/add")
    public String add(Model model, @Valid @ModelAttribute("mauSac") MauSac mauSac, BindingResult result) {
        goHome(model, 1);
        return mauSacService.add(mauSac, result);
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable UUID id, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        model.addAttribute("mauSac", mauSacService.getOne(id));
        return "mausac/update";
    }

    @PostMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("mauSac") MauSac mauSac,
                          BindingResult result) {
        goHome(model, 1);
        return mauSacService.update(mauSac, id, result);
    }

    @GetMapping("/remove/{id}")
    public String remove(Model model, @PathVariable("id") UUID id) {
        model.addAttribute("message", "Remove thành công");
        model.addAttribute("mauSac", new MauSac());
        mauSacService.remove(id);
        goHome(model, 1);
        return "mausac/home";
    }

}
