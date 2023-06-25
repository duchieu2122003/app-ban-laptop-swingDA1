package com.poly.ph25894.service.impl;

import com.poly.ph25894.entity.ChiTietSanPham;
import com.poly.ph25894.entity.HoaDon;
import com.poly.ph25894.entity.HoaDonChiTiet;
import com.poly.ph25894.entity.KhachHang;
import com.poly.ph25894.entity.NhanVien;
import com.poly.ph25894.entity.base.CodeAuto;
import com.poly.ph25894.model.respone.HoaDonChiTietBanHang;
import com.poly.ph25894.repository.ChiTietSanPhamRepository;
import com.poly.ph25894.repository.HoaDonChiTietRepository;
import com.poly.ph25894.repository.HoaDonRepository;
import com.poly.ph25894.repository.KhachHangRepository;
import com.poly.ph25894.repository.NhanVienRepository;
import com.poly.ph25894.service.BanHangService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class BanHangServiceImpl implements BanHangService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Override
    public HashMap<String, String> getLogin(String maNhanVien, String matKhau) {
        HashMap<String, String> map = new HashMap<>();
        NhanVien doiTuong = nhanVienRepository.findByMaAndMatKhau(maNhanVien, matKhau);
        map.put("idNhanVien", null);
        if (maNhanVien == null || maNhanVien.equals("")) {
            map.put("message", "Đăng nhập thất bại");
            map.put("errorMa", "Mã nhân viên trống !");
            map.put("url", "banhang/login");
        }
        if (matKhau == null || matKhau.equals("")) {
            map.put("message", "Đăng nhập thất bại");
            map.put("errorMatKhau", "Mật khẩu trống !");
            map.put("url", "banhang/login");
        }
        if (doiTuong == null) {
            map.put("message", "Đăng nhập thất bại");
            map.put("errorMatKhau", "Tài khoản hoặc mật khẩu sai !");
            map.put("url", "banhang/login");
            map.put("idNhanVien", null);
        } else {
            map.put("message", "Đăng nhập thành công");
            map.put("idNhanVien", doiTuong.getId().toString());
            map.put("url", "redirect:/ban-hang/home");
        }
        return map;
    }

    @Override
    public Boolean trangThaiLogin(String idNhanVien) {
        if (idNhanVien == null || idNhanVien.equals("")) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public List<HoaDonChiTietBanHang> listChiTietSanPhamHoaDon(UUID idHoaDon) {
        return chiTietSanPhamRepository.listChiTietHoaDon(idHoaDon);
    }

    @Override
    public Boolean detailDaThanhToan(UUID idHoaDon) {
        HoaDon hoaDon = hoaDonRepository.findById(idHoaDon).get();
        if (hoaDon.getTrangThai() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public BigDecimal tongTienHoaDonChiTiet(UUID idHoaDon) {
        return hoaDonChiTietRepository.tongTienHoaDonChiTiet(idHoaDon);
    }

    @Override
    public void removeHoaDonChiTiet(UUID idHoaDon, UUID idChiTietSanPham) {
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepository.findByIdHoaDonAndIdChiTietSanPham(idHoaDon,
                idChiTietSanPham);
        Integer soLuong = hoaDonChiTiet.getSoLuong();
        ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findChiTietSanPhamById(idChiTietSanPham);
        chiTietSanPham.setSoLuongTon(chiTietSanPham.getSoLuongTon() + soLuong);
        chiTietSanPhamRepository.save(chiTietSanPham);
        hoaDonChiTietRepository.delete(hoaDonChiTiet);
    }

    @Override
    public HashMap<String, String> chonHoaDonKhiThemSanPham(UUID idHoaDonChon) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (idHoaDonChon == null) {
            hashMap.put("message", "Phải chọn hóa đơn để thêm sản phẩm");
            hashMap.put("url", "banhang/home");
            return hashMap;
        }
        hashMap.put("url", "banhang/home");
        return hashMap;
    }

    @Override
    public HashMap<String, String> addHoaDon(String idNhanVien) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (idNhanVien == null) {
            hashMap.put("message", "Nhân viên vui lòng đăng nhập để sử dụng chức năng !");
            hashMap.put("url", "banhang/login");
            return hashMap;
        }
        HoaDon hoaDon = HoaDon.builder()
                .ma(new CodeAuto().generateRandomCode("HD"))
                .nhanVien(nhanVienRepository.findById(UUID.fromString(idNhanVien)).get())
                .ngayTao(Date.valueOf(LocalDate.now()))
                .diaChi("Tại cửa hàng")
                .khachHang(khachHangRepository.findById(UUID.fromString("84d46181-f472-4c03-b03a-71c98aebf7e4")).get())
                .tenNguoiNhan("Khách lẻ")
                .soDienThoai("0334850260")
                .trangThai(1)
                .build();
        hoaDonRepository.save(hoaDon);
        hashMap.put("message", "Tạo hóa đơn mới " + hoaDon.getMa() + " thành công !");
        hashMap.put("url", "banhang/home");
        return hashMap;
    }

    @Override
    public HashMap<String, String> thanhToanHoaDon(@Valid HoaDon hoaDon, BindingResult result, UUID idHoaDon) {
        hoaDon.setTenNguoiNhan("Khách");
        HashMap<String, String> hashMapThanhToan = new HashMap<>();
        if (result.hasErrors()) {
            System.out.println("--------------------------------------------------------------------------------------------------------" +
                    " " + result);
            hashMapThanhToan.put("message", "Thanh toán thất bại");
            return hashMapThanhToan;
        }
        HoaDon hoaDonEdit = hoaDonRepository.findById(idHoaDon).get();
        BeanUtils.copyProperties(hoaDon, hoaDonEdit);
        hoaDon.setTrangThai(0);
        hoaDon.setNgayThanhToan(Date.valueOf(LocalDate.now()));
        hoaDon.setNgayNhan(Date.valueOf(LocalDate.now()));
        hoaDonRepository.save(hoaDon);
        hashMapThanhToan.put("message", "Thanh toán thành công");
        hashMapThanhToan.put("detailDaThanhToan", "true");
        return hashMapThanhToan;
    }

    @Override
    public HashMap<String, String> addChiTietSanPhamHoaDonChiTiet(UUID idHoaDon, UUID idChiTietSanPham, Integer soLuong) {
        HashMap<String, String> hashMap = new HashMap<>();
        HoaDonChiTiet hoaDonChiTietTrung = hoaDonChiTietRepository.findByIdHoaDonAndIdChiTietSanPham(idHoaDon,
                idChiTietSanPham);
        if (idHoaDon == null) {
            hashMap.put("message", "Vui lòng chọn hóa đơn để thêm");
            return hashMap;
        }
        if (hoaDonChiTietTrung != null) {
            ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findChiTietSanPhamById(idChiTietSanPham);
            if ((soLuong - chiTietSanPham.getSoLuongTon()) > 0) {
                hashMap.put("message", "Vui lòng nhập số lượng sản phẩm nhỏ hơn " + chiTietSanPham.getSoLuongTon());
                return hashMap;
            }
            Integer soLuongNew = soLuong + hoaDonChiTietTrung.getSoLuong();
            Integer donGia = soLuongNew * chiTietSanPhamRepository
                    .findChiTietSanPhamById(idChiTietSanPham).getGiaBan().intValue();
            hoaDonChiTietTrung.setSoLuong(soLuongNew);
            hoaDonChiTietTrung.setDonGia(BigDecimal.valueOf(donGia));
            hoaDonChiTietRepository.save(hoaDonChiTietTrung);
            hashMap.put("message", "Update số lượng sản phẩm giỏ hàng thành công");
            chiTietSanPham.setSoLuongTon(chiTietSanPham.getSoLuongTon() - soLuong);
            chiTietSanPhamRepository.save(chiTietSanPham);
            return hashMap;
        } else {
            ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findChiTietSanPhamById(idChiTietSanPham);
            if ((soLuong - chiTietSanPham.getSoLuongTon()) > 0) {
                hashMap.put("message", "Vui lòng nhập số lượng sản phẩm nhỏ hơn " + chiTietSanPham.getSoLuongTon());
                return hashMap;
            }
            Integer donGia = soLuong * chiTietSanPhamRepository
                    .findChiTietSanPhamById(idChiTietSanPham).getGiaBan().intValue();
            HoaDonChiTiet hoaDonChiTiet = HoaDonChiTiet.builder()
                    .idChiTietSanPham(idChiTietSanPham)
                    .idHoaDon(idHoaDon)
                    .soLuong(soLuong)
                    .donGia(BigDecimal.valueOf(donGia))
                    .build();
            chiTietSanPham.setSoLuongTon(chiTietSanPham.getSoLuongTon() - soLuong);
            hoaDonChiTietRepository.save(hoaDonChiTiet);
            chiTietSanPhamRepository.save(chiTietSanPham);
            hashMap.put("message", "Thêm sản phẩm vào giỏ hàng thành công");
            return hashMap;
        }
    }

    @Override
    public HashMap<String, String> addSoLuongHoaDonChiTiet(UUID idHoaDon, UUID idChiTietSanPham, Integer soLuong) {
        HashMap<String, String> hashMap = new HashMap<>();
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepository.findByIdHoaDonAndIdChiTietSanPham(idHoaDon,
                idChiTietSanPham);
        ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findChiTietSanPhamById(idChiTietSanPham);
        if (soLuong > (chiTietSanPham.getSoLuongTon() + hoaDonChiTiet.getSoLuong())) {
            hashMap.put("message", "Số lượng sản phẩm không được lớn hơn " +
                    (chiTietSanPham.getSoLuongTon() + hoaDonChiTiet.getSoLuong()));
            return hashMap;
        } else if (soLuong <= hoaDonChiTiet.getSoLuong()) {
            chiTietSanPham.setSoLuongTon(chiTietSanPham.getSoLuongTon() + (hoaDonChiTiet.getSoLuong() - soLuong));
            hoaDonChiTiet.setSoLuong(soLuong);
            hoaDonChiTiet.setDonGia(BigDecimal.valueOf(chiTietSanPham.getGiaNhap().intValue() * soLuong));
            hashMap.put("message", "Sửa số lượng thành công");
            chiTietSanPhamRepository.save(chiTietSanPham);
            hoaDonChiTietRepository.save(hoaDonChiTiet);
            return hashMap;
        }
        if (soLuong > hoaDonChiTiet.getSoLuong() && soLuong <= (hoaDonChiTiet.getSoLuong() + chiTietSanPham.getSoLuongTon())) {
            chiTietSanPham.setSoLuongTon((chiTietSanPham.getSoLuongTon() + hoaDonChiTiet.getSoLuong()) - soLuong);
            hoaDonChiTiet.setSoLuong(soLuong);
            hoaDonChiTiet.setDonGia(BigDecimal.valueOf(chiTietSanPham.getGiaBan().intValue() * soLuong));
            hashMap.put("message", "Sửa số lượng thành công");
            hoaDonChiTietRepository.save(hoaDonChiTiet);
            chiTietSanPhamRepository.save(chiTietSanPham);
            return hashMap;
        }
        return hashMap;
    }

    @Override
    public void suaChiTietSanPhamHoaDonChiTiet(UUID idHoaDon, UUID idChiTietSanPham, Integer soLuong) {
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepository.findByIdHoaDonAndIdChiTietSanPham(idHoaDon,
                idChiTietSanPham);
        ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findChiTietSanPhamById(idChiTietSanPham);
        Integer soLuongSua = 0;
        Integer soLuongSanPham = hoaDonChiTiet.getSoLuong();
        if ((soLuongSanPham - soLuong) > 0) {
            soLuongSua = 1;
        } else {
            soLuongSua = -1;
        }
        hoaDonChiTiet.setSoLuong(soLuong);
        hoaDonChiTiet.setDonGia(BigDecimal.valueOf(soLuong * chiTietSanPham.getGiaBan().intValue()));
        chiTietSanPham.setSoLuongTon(chiTietSanPham.getSoLuongTon() + soLuongSua);
        hoaDonChiTietRepository.save(hoaDonChiTiet);
        chiTietSanPhamRepository.save(chiTietSanPham);
    }

    @Override
    public HashMap<String, String> addKhachHang(@Valid KhachHang doiTuong, BindingResult result, UUID idHoaDon) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (result.hasErrors()) {
            hashMap.put("message", "Thêm khách hàng thất bại");
            hashMap.put("idKhachHang", hoaDonRepository.findById(idHoaDon).get().getKhachHang().getId().toString());
            return hashMap;
        }
        doiTuong.setMa(new CodeAuto().generateRandomCode("KH"));
        doiTuong.setMatKhau("123456");
        doiTuong.setNgaySinh(null);
        doiTuong.setTrangThai(0);
        khachHangRepository.save(doiTuong);
        hashMap.put("message", "Thêm khách hàng thành công mã " + doiTuong.getMa());
        hashMap.put("idKhachHang", doiTuong.getId().toString());
        return hashMap;
    }

}
