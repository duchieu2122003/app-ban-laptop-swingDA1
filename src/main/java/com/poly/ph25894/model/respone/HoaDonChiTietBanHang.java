package com.poly.ph25894.model.respone;

import com.poly.ph25894.entity.ChiTietSanPham;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonChiTietBanHang {

    private ChiTietSanPham chiTietSanPham;

    private UUID idHoaDon;

    private Integer soLuong;

    private BigDecimal donGia;
}
