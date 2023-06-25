package com.poly.ph25894.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.UUID;

@Table(name = "CuaHang")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CuaHang implements Serializable {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "Ma")
    private String ma;

    @NotBlank(message = "Tên trống")
    @Column(name = "Ten")
    private String ten;

    @NotBlank(message = "Địa chỉ trống")
    @Column(name = "DiaChi")
    private String diaChi;

    @NotBlank(message = "Thành phố trống")
    @Column(name = "ThanhPho")
    private String thanhPho;

    @NotBlank(message = "Quốc gia trống")
    @Column(name = "QuocGia")
    private String quocGia;

    @Column(name = "TrangThai")
    private Integer trangThai;

}
