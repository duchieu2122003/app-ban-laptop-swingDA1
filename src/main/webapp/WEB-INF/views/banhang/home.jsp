<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bán hàng tại quầy</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <style>
        a {
            text-decoration: none;
            color: white;
        }
        h1 {
            text-align: center;
        }
        .thanhCuon {
            height: 250px;
            overflow-y: auto;
        }
        body {
            font-size: 16px;
            padding: 20px;
            font-family: "Times New Roman";
            background-color: #C6E2FF;
        }
        table {
            width: 100%;
        }
        table th {
            background-color: #f5f5f5;
            font-weight: bold;
        }
        table tr:hover {
            background-color: #eaeaea;
        }
        h5 {
            margin-top: 10px;
            color: darkblue;
            font-weight: bolder;
            font-size: 16px;
        }
        table button {
            width: 100%;
        }
        .thanhToan {
            border: 0px;
            background-color: steelblue;
            font-size: 17px;
            width: 100%;
            color: whitesmoke;
            font-weight: bolder;
            border: none;
            border-bottom: 2px solid black;
            border-radius: 4px;
            padding: 5px;
        }
        .thanhToan:focus {
            outline: none;
            border-color: wheat;
        }
        label {
            color: black;
        }
        button a {
            font-weight: bolder;
            color: whitesmoke;
        }
    </style>
    <c:if test="${trangThaiLogin == false}">
        <style>
            button {
                opacity: 0.5;
                pointer-events: none;
            }
        </style>
    </c:if>
</head>
<body>
<div class="container-fluid">
    <a href="/admin/san-pham/hien-thi" type="submit" class="btn btn-outline-primary">Sản phẩm</a>
    <a href="/admin/dong-san-pham/hien-thi" type="submit" class="btn btn-outline-success">Dòng sản phẩm</a>
    <a href="/admin/mau-sac/hien-thi" type="submit" class="btn btn-outline-danger">Màu sắc</a>
    <a href="/admin/chuc-vu/hien-thi" type="submit" class="btn btn-outline-danger">Chức vụ</a>
    <a href="/admin/chi-tiet-san-pham/hien-thi" type="submit" class="btn btn-outline-danger">Chi tiết sản phẩm</a>
    <a href="/admin/cua-hang/hien-thi" type="submit" class="btn btn-outline-info">Cửa hàng</a>
    <a href="/admin/khach-hang/hien-thi" type="submit" class="btn btn-outline-success">Khách hàng</a>
    <a href="/admin/nhan-vien/hien-thi" type="submit" class="btn btn-outline-primary">Nhân viên</a>
    <a href="/admin/hoa-don/hien-thi" type="submit" class="btn btn-outline-primary">Hóa đơn</a>
    <a href="/admin/hoa-don-chi-tiet/hien-thi" type="submit" class="btn btn-outline-success">Hóa đơn chi tiết</a>
    <a href="/admin/gio-hang/hien-thi" type="submit" class="btn btn-outline-danger">Giỏ hàng</a>
    <a href="/admin/gio-hang-chi-tiet/hien-thi" type="submit" class="btn btn-outline-danger">Giỏ hàng chi tiết</a>
    <c:if test="${trangThaiLogin==true}">
        <a href="/ban-hang/dang-xuat" type="submit" class="btn btn-outline-danger">Đăng xuất</a>
    </c:if>
    <c:if test="${trangThaiLogin==false}">
        <a href="/ban-hang/login-form" type="submit" class="btn btn-outline-primary">Đăng nhập</a>
    </c:if>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
            aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
</div>
<div class="row">
    <h1 class="col-md-10" style="color: black;text-align: center; font-weight: bolder; font-family: 'Courier New'">
        👟Cửa hàng bán giày Hiiu✿(Offline)☀</h1>
    <div style="width: 15%" class="col-md-2">
        <form action="/ban-hang/hoa-don/add" method="post">
            <br>
            <button class="btn btn-danger" type="submit"
                    style="float:right;height: 55px; color: whitesmoke; font-weight: bolder; font-size: 18px">Tạo hóa
                đơn
            </button>
        </form>
    </div>
</div>
<section class="row">
    <section class="col-md-8" style="height: 750px;width: 1000px">
        <div class="row">
            <h5>✿ Hóa đơn</h5>
            <div class="thanhCuon" style="width: 890px;width: 85%">
                <table>
                    <thead>
                    <tr>
                        <th scope="col">STT</th>
                        <th scope="col">Mã</th>
                        <th scope="col">Khách hàng</th>
                        <th scope="col">Nhân viên</th>
                        <th scope="col">Ngày tạo</th>
                        <th scope="col">Ngày thanh toán</th>
                        <th scope="col">Trạng thái</th>
                        <th colspan="3">Hành động</th>
                    </tr>
                    </thead>
                    <c:forEach items="${listHoaDon}" var="hoaDon" varStatus="i">
                        <tr>
                            <th>${i.index+1}</th>
                            <td>${hoaDon.ma}</td>
                            <td>${hoaDon.khachHang.ten}</td>
                            <td>${hoaDon.nhanVien.ten}</td>
                            <td>${hoaDon.ngayTao}</td>
                            <td>${hoaDon.ngayThanhToan}</td>
                            <td>${hoaDon.trangThai==0?"Đã thanh toán":"Chờ thanh toán"}</td>
                            <td>
                                <a class="btn btn-primary" href="/ban-hang/hoa-don-chi-tiet/detail/${hoaDon.id}"
                                   style="font-weight: bolder">Chi
                                    tiết</a>
                            </td>
                            <td>
                                <a class="btn btn-success" href="/ban-hang/hoa-don/view-thanh-toan/${hoaDon.id}"
                                   style="font-weight: bolder">Thanh
                                    toán</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <div class="row">
            <section style="width: 85%;">
                <h5>✿ Giỏ hàng</h5>
                <div class="thanhCuon">
                    <table>
                        <thead>
                        <tr>
                            <th scope="col">STT</th>
                            <th scope="col">Chi tiết sản phẩm</th>
                            <th colspan="3" style="text-align: center">Số lượng</th>
                            <th scope="col">Giá</th>
                            <th colspan="2">Hành động</th>
                        </tr>
                        </thead>
                        <c:forEach items="${listChiTietSanPhamHoaDon}" var="listChiTietSanPhamHoaDon" varStatus="i">
                            <tr>
                                <th scope="row">${i.index+1}</th>
                                <td>${listChiTietSanPhamHoaDon.chiTietSanPham.sanPham.ten} ${listChiTietSanPhamHoaDon.chiTietSanPham.dongSanPham.ten} ${listChiTietSanPhamHoaDon.chiTietSanPham.mauSac.ten}</td>
                                <td>
                                    <button class="btn btn-primary" ${listChiTietSanPhamHoaDon.soLuong<=1?"disabled":""} ${detaiDaThanhToan==true?"disabled":""}>
                                        <a href="/ban-hang/hoa-don-chi-tiet/update-so-luong/${listChiTietSanPhamHoaDon.idHoaDon}/${listChiTietSanPhamHoaDon.chiTietSanPham.id}/${listChiTietSanPhamHoaDon.soLuong-1}">➖</a>
                                    </button>
                                </td>
                                <td>${listChiTietSanPhamHoaDon.soLuong}</td>
                                <td>
                                    <button class="btn btn-success" ${listChiTietSanPhamHoaDon.chiTietSanPham.soLuongTon<=0?"disabled":""} ${detaiDaThanhToan==true?"disabled":""}>
                                        <a href="/ban-hang/hoa-don-chi-tiet/update-so-luong/${listChiTietSanPhamHoaDon.idHoaDon}/${listChiTietSanPhamHoaDon.chiTietSanPham.id}/${listChiTietSanPhamHoaDon.soLuong+1}">➕</a>
                                    </button>
                                </td>
                                <td>${listChiTietSanPhamHoaDon.donGia} VNĐ</td>
                                <td>
                                    <button class="btn btn-warning" ${detaiDaThanhToan==true?"disabled":""}>
                                        <a href="/ban-hang/hoa-don-chi-tiet/view-update-so-luong-new/${listChiTietSanPhamHoaDon.idHoaDon}/${listChiTietSanPhamHoaDon.chiTietSanPham.id}">
                                            ✎Sửa</a>
                                    </button>
                                </td>
                                <td>
                                    <button class="btn btn-danger" ${detaiDaThanhToan==true?"disabled":""}>
                                        <a href="/ban-hang/hoa-don-chi-tiet/remove/${listChiTietSanPhamHoaDon.idHoaDon}/${listChiTietSanPhamHoaDon.chiTietSanPham.id}">
                                            Bỏ</a>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <c:if test="${tongTien!= null}">
                        <label style="margin-left: 70%; color: darkred; font-weight: bolder; font-size: 18px;">Tổng
                            tiền: ${tongTien} VNĐ</label>
                    </c:if>
                </div>
            </section>
            <section style="width: 15%">
                <c:if test="${suaSoLuong==true}"><br>
                    <form action="/ban-hang/hoa-don-chi-tiet/update-so-luong-new-custom/${idHoaDon}/${idChiTietSanPham}"
                          method="post">
                        <label>Nhập số lượng:</label>
                        <input type="number" name="soLuong" min="1" style="width: 50px" required>
                        <button type="submit" class="btn btn-success">Sửa</button>
                        <a class="btn btn-danger" href="/ban-hang/home">Hủy</a>
                    </form>
                </c:if></section>
        </div>
        <div class="row">
            <section style="width: 85%">
                <h5>✿ Sản phẩm</h5>
                <div class="thanhCuon">
                    <table>
                        <thead>
                        <tr>
                            <th scope="col">STT</th>
                            <th scope="col">Chi tiết sản phẩm</th>
                            <th scope="col">Bảo hành</th>
                            <th scope="col">Mô tả</th>
                            <th scope="col">Số lượng tồn</th>
                            <th scope="col">Giá bán</th>
                            <th scope="col">Hành động</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${listChiTietSanPham}" var="chiTietSanPham" varStatus="i">
                            <tr>
                                <th scope="row">${i.index+1}</th>
                                <td>${chiTietSanPham.sanPham.ten} ${chiTietSanPham.dongSanPham.ten} ${chiTietSanPham.mauSac.ten}</td>
                                <td>${chiTietSanPham.namBh} (năm)</td>
                                <td>${chiTietSanPham.moTa}</td>
                                <td>${chiTietSanPham.soLuongTon}</td>
                                <td>${chiTietSanPham.giaBan} VNĐ</td>
                                <td>
                                    <button class="btn btn-primary" ${detaiDaThanhToan==true?"disabled":""}><a
                                            href="/ban-hang/hoa-don-chi-tiet/view-update-so-luong/${chiTietSanPham.id}">Thêm</a>
                                    </button>

                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </section>
            <section style="width: 15%">
                <c:if test="${chonSoLuong==true}"><br>
                    <form action="/ban-hang/hoa-don-chi-tiet/update-so-luong/${idChiTietSanPham}" method="post">
                        <label>Nhập số lượng:</label>
                        <input type="number" name="soLuong" min="1" style="width: 50px" required>
                        <button type="submit" class="btn btn-success">Sửa</button>
                        <a class="btn btn-danger" href="/ban-hang/home">Hủy</a>
                    </form>
                </c:if></section>
        </div>
    </section>
    <br>
    <section class="col-md-4">
        <c:if test="${thanhToan}">
            <div style="border: 3px cornflowerblue solid; padding: 30px; height: 700px; background-color: steelblue; border-radius: 10px; padding-top: 8px; color: whitesmoke">
                <h1 style="font-family: 'Courier New'; font-weight: bolder">Thanh toán</h1>
                <form:form action="/ban-hang/hoa-don/thanh-toan/${idHoaDon}" method="post" modelAttribute="hoaDon">
                    <form:input path="id" readonly="true" cssStyle="display: none"/>
                    <label>Mã hóa đơn:</label>
                    <form:input path="ma" readonly="true" cssClass="thanhToan"/>
                    <br><br>
                    <label>Mã khách hàng:</label>
                    <form:select path="khachHang" id="selectKhachHang" onchange="getThanhToan()"
                                 cssClass="thanhToanSelect">
                        <c:forEach items="${listKhachHang}" var="khachHang">
                            <form:option value="${khachHang}" data-ten="${khachHang.ten}" label="${khachHang.ma}"/>
                        </c:forEach>
                    </form:select>
                    <label id="tenKhachHang" style="font-weight: bolder"></label>
                    <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#exampleModal"
                            style="font-weight: bolder; color: whitesmoke">
                        Thêm
                    </button>
                    <br><br>
                    <label>Mã nhân viên:</label>
                    <form:select path="nhanVien" id="selectNhanVien" onchange="getThanhToan()"
                                 cssClass="thanhToanSelect">
                        <c:forEach items="${listNhanVien}" var="nhanVien">
                            <form:option value="${nhanVien}" data-ten="${nhanVien.ten}" label="${nhanVien.ma}"/>
                        </c:forEach>
                    </form:select>
                    <label id="tenNhanVien" style="font-weight: bolder"></label>
                    <br><br>
                    <label>Địa chỉ:</label>
                    <form:input path="diaChi" cssClass="thanhToan"/>
                    <form:errors path="diaChi" cssStyle="color: red;"/>
                    <br><br>
                    <label>Số điện thoại:</label>
                    <form:input type="number" path="soDienThoai" cssClass="thanhToan"/>
                    <form:errors path="soDienThoai" cssStyle="color:red"/>
                    <br><br> <label>Ngày tạo:</label>
                    <form:input type="date" path="ngayTao" readonly="true" cssClass="thanhToan"/>
                    <br><br> <label>Tổng tiền phải trả:</label>
                    <input type="number" value="${tongTien}" readonly class="thanhToan"/>
                    <br><br>
                    <form:input value="Khách hàng" path="tenNguoiNhan" cssStyle="display: none"/>
                    <c:if test="${detailDaThanhToan==false}">
                        <form:button type="submit" class="btn btn-danger"
                                     style="height:47px;font-weight: bolder; font-size: 18px"
                                     onclick=" return thongBao('thanh toán')">Thanh toán</form:button>
                    </c:if>
                </form:form>
            </div>
        </c:if>
    </section>
</section>
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <form:form action="/ban-hang/khach-hang/add-thanh-toan" method="post" modelAttribute="khachHang">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Thêm khách hàng</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <label>Tên khách hàng:</label>
                <form:input path="ten"/>
                <form:errors path="ten" cssStyle="color: red"/>
                <br><br>
                <label>Số điện thoại:</label>
                <form:input type="number" path="soDienThoai"/>
                <form:errors path="soDienThoai" cssStyle="color:red"/>
                <br><br>
                <label>Địa chỉ:</label>
                <form:input path="diaChi"/>
                <form:errors path="diaChi" cssStyle="color:red"/>
                <br><br>
                <label>Thành phố:</label>
                <form:input path="thanhPho"/>
                <form:errors path="thanhPho" cssStyle="color:red"/>
                <br><br>
                <label>Quốc gia:</label>
                <form:input path="quocGia"/>
                <form:errors path="quocGia" cssStyle="color:red"/>
                <br><br>
                <form:input path="matKhau" value="123456" cssStyle="display: none"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                <button type="submit" class="btn btn-primary">Thêm</button>
                </form:form>
            </div>
        </div>
    </div>
</div>
<c:if test="${message!=null}">
    <script>
        alert("${message}")
    </script>
</c:if>
<script>
    function thongBao(a) {
        if (confirm(" Bạn có muốn " + a + " không ?")) {
            return true;
        }
        return false;
    }

    function getThanhToan() {
        var selectKhachHang = document.getElementById("selectKhachHang");
        var indexKhachHang = selectKhachHang.selectedIndex;
        var optionsKhachHang = selectKhachHang.options[indexKhachHang];
        document.getElementById("tenKhachHang").innerHTML = optionsKhachHang.dataset.ten;
        var selectNhanVien = document.getElementById("selectNhanVien");
        var indexNhanVien = selectNhanVien.selectedIndex;
        var optionsNhanVien = selectNhanVien.options[indexNhanVien];
        document.getElementById("tenNhanVien").innerHTML = optionsNhanVien.dataset.ten;
    }

    getThanhToan();

    function getData() {
        var selectSanPham = document.getElementById("selectSanPham");
        var indexSanPham = selectSanPham.selectedIndex;
        var optionsSanPham = selectSanPham.options[indexSanPham];
        document.getElementById("tenSanPham").innerHTML = optionsSanPham.dataset.ten;
        var selectDongSp = document.getElementById("selectDongSp");
        var indexDongSp = selectDongSp.selectedIndex;
        var optionsDongSp = selectDongSp.options[indexDongSp];
        document.getElementById("tenDongSp").innerHTML = optionsDongSp.dataset.ten;
        var selectMauSac = document.getElementById("selectMauSac");
        var indexMauSac = selectMauSac.selectedIndex;
        var optionsMauSac = selectMauSac.options[indexMauSac];
        document.getElementById("tenMauSac").innerHTML = optionsMauSac.dataset.ten;
    }

    getData();
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"
        integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS"
        crossorigin="anonymous"></script>
</body>
</html>