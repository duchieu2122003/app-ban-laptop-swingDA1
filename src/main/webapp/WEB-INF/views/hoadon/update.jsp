<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sửa thông tin hóa đơn</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <style>
        a {
            text-decoration: none;
            color: white;
        }
        body {
            font-size: 16px;
            padding: 20px;
            font-family: "Times New Roman";
            background-color: lightsteelblue;
        }
    </style>
</head>
<body>
<section class="container">
    <h1>Sửa thông tin hóa đơn</h1>
    <br><br>
    <form:form action="/admin/hoa-don/update/${hoaDon.id}" method="post" modelAttribute="hoaDon">
        <label>Mã hóa đơn</label>
        <form:input path="ma" readonly="true"/>
        <br><br>
        <label>Mã khách hàng:</label>
        <form:select path="khachHang" id="selectKhachHang" onchange="getData()">
            <c:forEach items="${listKhachHang}" var="khachHang">
                <form:option value="${khachHang}" data-ten="${khachHang.ten}" label="${khachHang.ma}"/>
            </c:forEach>
        </form:select>
        <label id="tenKhachHang"></label>
        <br><br>
        <label>Mã nhân viên:</label>
        <form:select path="nhanVien" id="selectNhanVien" onchange="getData()">
            <c:forEach items="${listNhanVien}" var="nhanVien">
                <form:option value="${nhanVien}" data-ten="${nhanVien.ten}" label="${nhanVien.ma}"/>
            </c:forEach>
        </form:select>
        <label id="tenNhanVien"></label>
        <br><br>
        <label>Tên người nhận:</label>
        <form:input path="tenNguoiNhan"/>
        <form:errors path="tenNguoiNhan" cssStyle="color: red"/>
        <br><br>
        <label>Địa chỉ:</label>
        <form:input path="diaChi"/>
        <form:errors path="diaChi" cssStyle="color: red"/>
        <br><br>
        <label>Số điện thoại:</label>
        <form:input type="number" path="soDienThoai"/>
        <form:errors path="soDienThoai" cssStyle="color:red"/>
        <br><br>
        <form:input type="date" path="ngayTao" cssStyle="display: none"/>
        <label>Ngày thanh toán:</label>
        <form:input type="date" path="ngayThanhToan"/>
        <form:errors path="ngayThanhToan" cssStyle="color:red" cssClass="error"/>
        <br><br>
        <label>Ngày nhận:</label>
        <form:input type="date" path="ngayNhan"/>
        <form:errors path="ngayNhan" cssStyle="color:red" cssClass="error"/>
        <br><br>
        <label>Trạng thái:</label>
        <form:radiobutton path="trangThai" checked="true" value="0" label="Đã thanh toán"/>
        <form:radiobutton path="trangThai" value="1" label="Chưa thanh toán"/>
        <br><br>
        <form:button type="submit" class="btn btn-success"
                     onclick=" return thongBao('sửa')">Sửa</form:button>
    </form:form>
</section>
<script>
    function thongBao(a) {
        if (confirm("Bạn có muốn " + a + " không ?")) {
            return true;
        }
        return false;
    }

    function getData() {
        var selectKhachHang = document.getElementById("selectKhachHang");
        var indexKhachHang = selectKhachHang.selectedIndex;
        var optionsKhachHang = selectKhachHang.options[indexKhachHang];
        document.getElementById("tenKhachHang").innerHTML = optionsKhachHang.dataset.ten;
        var selectNhanVien = document.getElementById("selectNhanVien");
        var indexNhanVien = selectNhanVien.selectedIndex;
        var optionsNhanVien = selectNhanVien.options[indexNhanVien];
        document.getElementById("tenNhanVien").innerHTML = optionsNhanVien.dataset.ten;
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