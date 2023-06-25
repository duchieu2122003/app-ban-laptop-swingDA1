<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sửa thông tin giỏ hàng</title>
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
    <h1>Sửa thông tin giỏ hàng</h1>
    <br><br>
    <form:form action="/admin/gio-hang/update/${gioHang.id}" method="post" modelAttribute="gioHang">
        <label>Mã giỏ hàng:</label>
        <form:input path="ma" readonly="true"/>
        <br><br>
        <label>Mã khách hàng:</label>
        <form:select path="khachHang" id="selectKhachHang" onchange="getData()">
            <c:forEach items="${listKhachHang}" var="khachHang">
                <form:option value="${khachHang.id}" data-ten="${khachHang.ten}" label="${khachHang.ma}"/>
            </c:forEach>
        </form:select>
        <label id="tenKhachHang"></label>
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
        <form:input type="number" path="sdt"/>
        <form:errors path="sdt" cssStyle="color:red"/>
        <br><br>
        <form:input type="date" path="ngayTao" cssStyle="display: none"/>
        <label>Ngày thanh toán:</label>
        <form:input type="date" path="ngayThanhToan"/>
        <form:errors path="ngayThanhToan" cssStyle="color:red" cssClass="error"/>
        <br><br>
        <label>Trạng thái:</label>
        <form:radiobutton path="trangThai" value="0" label="Chưa thanh toán"/>
        <form:radiobutton path="trangThai" value="1" label="Đã thanh toán"/>
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
        var select = document.getElementById("selectKhachHang");
        var index = select.selectedIndex;
        var options = select.options[index];
        document.getElementById("tenKhachHang").innerHTML = options.dataset.ten;
        getData();
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