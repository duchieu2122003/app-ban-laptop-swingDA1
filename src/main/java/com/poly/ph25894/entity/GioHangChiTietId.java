package com.poly.ph25894.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class GioHangChiTietId implements Serializable {

    private UUID idGioHang;

    private UUID idChiTietSanPham;

}
