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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

@Table(name = "GioHang")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GioHang implements Serializable {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdKH", referencedColumnName = "Id")
    private KhachHang khachHang;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "NgayTao")
    private Date ngayTao;

    @Temporal(TemporalType.DATE)
    @Column(name = "NgayThanhToan")
    private Date ngayThanhToan;

    @NotBlank(message = "Tên người nhận trống")
    @Column(name = "TenNguoiNhan")
    private String tenNguoiNhan;

    @NotBlank(message = "Địa chỉ nhận trống")
    @Column(name = "DiaChi")
    private String diaChi;

    @NotBlank(message = "Sdt trống")
    @Pattern(regexp = "^(0[0-9]{9,10})$", message = "Sdt sai định dạng")
    @Column(name = "Sdt")
    private String sdt;

    @Column(name = "TrangThai")
    private Integer trangThai;

}
