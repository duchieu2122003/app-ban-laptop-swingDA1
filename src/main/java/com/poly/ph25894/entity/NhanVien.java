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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;
import java.util.UUID;

@Table(name = "NhanVien")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NhanVien {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "Ma")
    private String ma;

    @NotBlank(message = "Tên trống")
    @Column(name = "Ten")
    private String ten;

    @NotBlank(message = "Giới tính trống")
    @Column(name = "GioiTinh")
    private String gioiTinh;


    @NotNull(message = "Ngày sinh không được để trống")
    @Temporal(TemporalType.DATE)
    @Past(message = "Ngày sinh phải là 1 ngày trong quá khứ và không được trống")
    @Column(name = "NgaySinh")
    private Date ngaySinh;

    @NotBlank(message = "Địa chỉ trống")
    @Column(name = "DiaChi")
    private String diaChi;

    @NotBlank(message = "Sdt trống")
    @Pattern(regexp = "^(0[0-9]{9,10})$", message = "Sdt sai định dạng")
    @Column(name = "Sdt")
    private String soDienThoai;

    @NotEmpty(message = "Mật khẩu trống")
    @Column(name = "MatKhau")
    private String matKhau;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdCH", referencedColumnName = "Id")
    private CuaHang cuaHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdCV", referencedColumnName = "Id")
    private ChucVu chucVu;

    @Column(name = "TrangThai")
    private Integer trangThai;

}
