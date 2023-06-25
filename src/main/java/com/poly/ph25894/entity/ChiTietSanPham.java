package com.poly.ph25894.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "ChiTietSP")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChiTietSanPham {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdSP", referencedColumnName = "Id")
    private SanPham sanPham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdDongSp", referencedColumnName = "Id")
    private DongSanPham dongSanPham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdMauSac", referencedColumnName = "Id")
    private MauSac mauSac;

    @NotNull(message = "Năm bảo hành trống")
    @Column(name = "NamBH")
    private Integer namBh;

    @NotEmpty(message = "Mô tả trống")
    @Column(name = "MoTa")
    private String moTa;

    @NotNull(message = "Số lượng trống")
    @Column(name = "SoLuongTon")
    private Integer soLuongTon;

    @NotNull(message = "Giá nhập trống")
    @Column(name = "GiaNhap")
    private BigDecimal giaNhap;

    @NotNull(message = "Giá bán trống")
    @Column(name = "GiaBan")
    private BigDecimal giaBan;

    @Column(name = "TrangThai")
    private Integer trangThai;

}
