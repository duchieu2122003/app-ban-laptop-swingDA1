package com.poly.ph25894.controller;

import com.poly.ph25894.entity.ChiTietSanPham;
import com.poly.ph25894.entity.HoaDon;
import com.poly.ph25894.entity.KhachHang;
import com.poly.ph25894.entity.NhanVien;
import com.poly.ph25894.model.respone.HoaDonChiTietBanHang;
import com.poly.ph25894.service.BanHangService;
import com.poly.ph25894.service.ChiTietSanPhamService;
import com.poly.ph25894.service.HoaDonChiTietService;
import com.poly.ph25894.service.HoaDonService;
import com.poly.ph25894.service.KhachHangService;
import com.poly.ph25894.service.NhanVienService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/ban-hang")
public class BanHangController {

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private BanHangService banHangService;

    private String idNhanVien;

    private UUID idHoaDonChon;

    @GetMapping("/login-form")
    public String loginForm(@ModelAttribute("nhanVien") NhanVien nhanVien, Model model) {
        model.addAttribute("nhanVien", new NhanVien());
        return "banhang/login";
    }

    @PostMapping("/dang-nhap")
    public String login(@RequestParam("maNhanVien") String maNhanVien, @RequestParam("matKhau") String matKhau,
                        Model model, HttpSession session) {
        HashMap<String, String> checkLogin = banHangService.getLogin(maNhanVien, matKhau);
        model.addAttribute("message", checkLogin.get("message"));
        model.addAttribute("errorMa", checkLogin.get("errorMa"));
        model.addAttribute("errorMatKhau", checkLogin.get("errorMatKhau"));
        session.setAttribute("idNhanVien", checkLogin.get("idNhanVien"));
        idNhanVien = checkLogin.get("idNhanVien");
        goHome(model);
        return checkLogin.get("url");
    }

    @GetMapping("/dang-xuat")
    public String logOut(HttpSession session) {
        session.removeAttribute("idNhanVien");
        idNhanVien = null;
        idHoaDonChon = null;
        return "redirect:/ban-hang/home";
    }

    @GetMapping("/home")
    public String homeLogin(Model model) {
        model.addAttribute("khachHang", new KhachHang());
        model.addAttribute("trangThaiLogin", banHangService.trangThaiLogin(idNhanVien));
        goHome(model);
        return "banhang/home";
    }

    public void goHome(Model model) {
        model.addAttribute("trangThaiLogin", banHangService.trangThaiLogin(idNhanVien));
        List<HoaDon> listHoaDon = hoaDonService.findAll();
        model.addAttribute("listKhachHang", khachHangService.findAll());
        model.addAttribute("listNhanVien", nhanVienService.findAll());
        List<ChiTietSanPham> listChiTietSanPham = chiTietSanPhamService.findAllBanHang();
        model.addAttribute("listHoaDon", listHoaDon);
        model.addAttribute("listChiTietSanPham", listChiTietSanPham);
    }

    @GetMapping("/hoa-don-chi-tiet/remove/{idHoaDon}/{idChiTietSanPham}")
    public String xoaSanPhamGioHang(@PathVariable UUID idHoaDon, @PathVariable UUID idChiTietSanPham, Model model) {
        banHangService.removeHoaDonChiTiet(idHoaDon, idChiTietSanPham);
        List<HoaDonChiTietBanHang> listChiTietSanPhamHoaDon = banHangService.listChiTietSanPhamHoaDon(idHoaDon);
        model.addAttribute("listChiTietSanPhamHoaDon", listChiTietSanPhamHoaDon);
        model.addAttribute("message", "Xóa khỏi giỏ hàng thành công");
        model.addAttribute("khachHang", new KhachHang());
        goHome(model);
        return "banhang/home";
    }

    @GetMapping("/hoa-don-chi-tiet/update-so-luong/{idHoaDon}/{idChiTietSanPham}/{soLuong}")
    public String suaSoLuongHoaDon(@PathVariable UUID idHoaDon, @PathVariable UUID idChiTietSanPham, Model model,
                                   @PathVariable Integer soLuong) {
        banHangService.suaChiTietSanPhamHoaDonChiTiet(idHoaDon, idChiTietSanPham, soLuong);
        List<HoaDonChiTietBanHang> listChiTietSanPhamHoaDon = banHangService.listChiTietSanPhamHoaDon(idHoaDon);
        model.addAttribute("listChiTietSanPhamHoaDon", listChiTietSanPhamHoaDon);
        model.addAttribute("tongTien", banHangService.tongTienHoaDonChiTiet(idHoaDon));
        model.addAttribute("khachHang", new KhachHang());
        goHome(model);
        return "banhang/home";
    }

    @GetMapping("/hoa-don-chi-tiet/update-so-luong-new/{idHoaDon}/{idChiTietSanPham}")
    public String suaCustomSoLuongHoaDon(@PathVariable UUID idHoaDon, @PathVariable UUID idChiTietSanPham, Model model,
                                         @RequestParam("suaSoLuong") Integer soLuong) {
        banHangService.suaChiTietSanPhamHoaDonChiTiet(idHoaDon, idChiTietSanPham, soLuong);
        List<HoaDonChiTietBanHang> listChiTietSanPhamHoaDon = banHangService.listChiTietSanPhamHoaDon(idHoaDon);
        model.addAttribute("listChiTietSanPhamHoaDon", listChiTietSanPhamHoaDon);
        model.addAttribute("tongTien", banHangService.tongTienHoaDonChiTiet(idHoaDon));
        goHome(model);
        return "banhang/home";
    }

    @GetMapping("/hoa-don-chi-tiet/view-update-so-luong-new/{idHoaDon}/{idChiTietSanPham}")
    public String suaCustomSoLuongHoaDonNew(@PathVariable UUID idHoaDon, @PathVariable UUID idChiTietSanPham, Model model) {
        model.addAttribute("suaSoLuong", true);
        List<HoaDonChiTietBanHang> listChiTietSanPhamHoaDon = banHangService.listChiTietSanPhamHoaDon(idHoaDon);
        model.addAttribute("listChiTietSanPhamHoaDon", listChiTietSanPhamHoaDon);
        model.addAttribute("tongTien", banHangService.tongTienHoaDonChiTiet(idHoaDon));
        model.addAttribute("khachHang", new KhachHang());
        goHome(model);
        return "banhang/home";
    }

    @PostMapping("/hoa-don-chi-tiet/update-so-luong-new-custom/{idHoaDon}/{idChiTietSanPham}")
    public String suaSoLuongHoaDonNew(@PathVariable UUID idHoaDon, @PathVariable UUID idChiTietSanPham, Model model,
                                      @RequestParam("soLuong") Integer soLuong) {
        HashMap<String, String> hashMap = banHangService.addSoLuongHoaDonChiTiet(idHoaDon, idChiTietSanPham, soLuong);
        model.addAttribute("message", hashMap.get("message"));
        List<HoaDonChiTietBanHang> listChiTietSanPhamHoaDon = banHangService.listChiTietSanPhamHoaDon(idHoaDon);
        model.addAttribute("tongTien", banHangService.tongTienHoaDonChiTiet(idHoaDon));
        model.addAttribute("listChiTietSanPhamHoaDon", listChiTietSanPhamHoaDon);
        model.addAttribute("khachHang", new KhachHang());
        goHome(model);
        return "banhang/home";
    }

    @PostMapping("/hoa-don/add")
    public String addHoaDon(Model model) {
        HashMap<String, String> hashMap = banHangService.addHoaDon(idNhanVien);
        model.addAttribute("message", hashMap.get("message"));
        model.addAttribute("khachHang", new KhachHang());
        goHome(model);
        return hashMap.get("url");
    }

    @GetMapping("/hoa-don-chi-tiet/detail/{idHoaDon}")
    public String detail(Model model, @PathVariable UUID idHoaDon) {
        idHoaDonChon = idHoaDon;
        List<HoaDonChiTietBanHang> listChiTietSanPhamHoaDon = banHangService.listChiTietSanPhamHoaDon(idHoaDon);
        model.addAttribute("listChiTietSanPhamHoaDon", listChiTietSanPhamHoaDon);
        model.addAttribute("tongTien", banHangService.tongTienHoaDonChiTiet(idHoaDon));
        model.addAttribute("khachHang", new KhachHang());
        model.addAttribute("detaiDaThanhToan", banHangService.detailDaThanhToan(idHoaDon));
        goHome(model);
        return "banhang/home";
    }

    @GetMapping("/hoa-don-chi-tiet/view-update-so-luong/{idChiTietSanPham}")
    public String chonSoLuong(@PathVariable UUID idChiTietSanPham, Model model) {
        HashMap<String, String> hashMapCheckChonHoaDon = banHangService.chonHoaDonKhiThemSanPham(idHoaDonChon);
        List<HoaDonChiTietBanHang> listChiTietSanPhamHoaDon = banHangService.listChiTietSanPhamHoaDon(idHoaDonChon);
        model.addAttribute("listChiTietSanPhamHoaDon", listChiTietSanPhamHoaDon);
        model.addAttribute("message", hashMapCheckChonHoaDon.get("message"));
        model.addAttribute("idChiTietSanPham", idChiTietSanPham);
        model.addAttribute("chonSoLuong", true);
        model.addAttribute("khachHang", new KhachHang());
        goHome(model);
        return "banhang/home";
    }

    @PostMapping("/hoa-don-chi-tiet/update-so-luong/{idChiTietSanPham}")
    public String themChiTietSanPhamVaoHoaDon(Model model, @PathVariable UUID idChiTietSanPham,
                                              @RequestParam("soLuong") Integer soLuong) {
        HashMap<String, String> hashMap = banHangService.addChiTietSanPhamHoaDonChiTiet(idHoaDonChon, idChiTietSanPham, soLuong);
        List<HoaDonChiTietBanHang> listChiTietSanPhamHoaDon = banHangService.listChiTietSanPhamHoaDon(idHoaDonChon);
        model.addAttribute("listChiTietSanPhamHoaDon", listChiTietSanPhamHoaDon);
        model.addAttribute("tongTien", banHangService.tongTienHoaDonChiTiet(idHoaDonChon));
        model.addAttribute("message", hashMap.get("message"));
        model.addAttribute("khachHang", new KhachHang());
        goHome(model);
        return "banhang/home";
    }

    @GetMapping("/hoa-don/view-thanh-toan/{idHoaDon}")
    public String viewThanhToan(@ModelAttribute("hoaDon") HoaDon hoaDon, Model model, @PathVariable UUID idHoaDon) {
        idHoaDonChon = idHoaDon;
        model.addAttribute("thanhToan", true);
        model.addAttribute("khachHang", new KhachHang());
        model.addAttribute("hoaDon", hoaDonService.getOne(idHoaDon));
        List<HoaDonChiTietBanHang> listChiTietSanPhamHoaDon = banHangService.listChiTietSanPhamHoaDon(idHoaDon);
        model.addAttribute("detailDaThanhToan", banHangService.detailDaThanhToan(idHoaDon));
        model.addAttribute("listChiTietSanPhamHoaDon", listChiTietSanPhamHoaDon);
        model.addAttribute("tongTien", banHangService.tongTienHoaDonChiTiet(idHoaDon));
        model.addAttribute("listKhachHang", khachHangService.findAll());
        model.addAttribute("listNhanVien", nhanVienService.findAll());
        goHome(model);
        return "banhang/home";
    }

    @PostMapping("/khach-hang/add-thanh-toan")
    private String add(@Valid @ModelAttribute("khachHang") KhachHang khachHang, BindingResult result, Model model) {
        HashMap<String, String> hashMap = banHangService.addKhachHang(khachHang, result, idHoaDonChon);
        List<HoaDonChiTietBanHang> listChiTietSanPhamHoaDon = banHangService.listChiTietSanPhamHoaDon(idHoaDonChon);
        model.addAttribute("listChiTietSanPhamHoaDon", listChiTietSanPhamHoaDon);
        model.addAttribute("detailDaThanhToan", banHangService.detailDaThanhToan(idHoaDonChon));
        model.addAttribute("message", hashMap.get("message"));
        model.addAttribute("thanhToan", true);
        model.addAttribute("tongTien", banHangService.tongTienHoaDonChiTiet(idHoaDonChon));
        HoaDon hoaDon = hoaDonService.getOne(idHoaDonChon);
        KhachHang khachHangThem = khachHangService.getOne(UUID.fromString(hashMap.get("idKhachHang")));
        hoaDon.setKhachHang(khachHangThem);
        model.addAttribute("hoaDon", hoaDon);
        goHome(model);
        return "banhang/home";
    }

    @PostMapping("/hoa-don/thanh-toan/{idHoaDon}")
    private String thanhToanHoaDon(Model model, @PathVariable UUID idHoaDon,
                                   @Valid @ModelAttribute("hoaDon") HoaDon hoaDon, BindingResult result) {
        HashMap<String, String> hashMapThanhToan = banHangService.thanhToanHoaDon(hoaDon, result, idHoaDon);
        goHome(model);
        model.addAttribute("message", hashMapThanhToan.get("message"));
        model.addAttribute("thanhToan", true);
        model.addAttribute("detailDaThanhToan", banHangService.detailDaThanhToan(idHoaDon));
        model.addAttribute("khachHang", new KhachHang());
        model.addAttribute("tongTien", banHangService.tongTienHoaDonChiTiet(idHoaDon));
        idHoaDonChon = null;
        return "banhang/home";
    }

}
