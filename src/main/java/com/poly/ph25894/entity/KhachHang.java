package com.poly.ph25894.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "KhachHang")
@Entity
@Builder
public class KhachHang {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "Ma")
    private String ma;

    @NotBlank(message = "Tên trống")
    @Column(name = "Ten")
    private String ten;

    @Temporal(TemporalType.DATE)
    @Past(message = "Ngày sinh phải là 1 ngày trong quá khứ và không được trống")
    @Column(name = "NgaySinh")
    private Date ngaySinh;

    @NotBlank(message = "Sdt trống")
    @Pattern(regexp = "^(0[0-9]{9,10})$", message = "Sdt sai định dạng")
    @Column(name = "Sdt")
    private String soDienThoai;

    @NotBlank(message = "Địa chỉ trống")
    @Column(name = "DiaChi")
    private String diaChi;

    @NotBlank(message = "Thành phố trống")
    @Column(name = "ThanhPho")
    private String thanhPho;

    @NotBlank(message = "Quốc gia trống")
    @Column(name = "QuocGia")
    private String quocGia;

    @NotBlank(message = "Mật khẩu trống")
    @Column(name = "MatKhau")
    private String matKhau;

    @Column(name = "TrangThai")
    private Integer trangThai;

}
