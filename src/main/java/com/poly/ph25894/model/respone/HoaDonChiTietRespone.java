package com.poly.ph25894.model.respone;

import com.poly.ph25894.entity.ChiTietSanPham;
import com.poly.ph25894.entity.HoaDon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HoaDonChiTietRespone {

    private HoaDon hoaDon;

    private ChiTietSanPham chiTietSanPham;

    private Integer soLuong;

    private BigDecimal donGia;

}
