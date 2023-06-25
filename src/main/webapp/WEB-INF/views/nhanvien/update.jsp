<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sửa thông tin nhân viên</title>
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
    <h1>Sửa thông tin nhân viên</h1>
    <br><br>
    <form:form action="/admin/nhan-vien/update/${nhanVien.id}" method="post" modelAttribute="nhanVien">
        <label>Mã nhân viên:</label>
        <form:input path="ma" readonly="true"/>
        <br><br>
        <label>Tên nhân viên:</label>
        <form:input path="ten"/>
        <form:errors path="ten" cssStyle="color: red"/>
        <br><br>
        <label>Giới tính:</label>
        <form:radiobutton path="gioiTinh" value="Nam" label="Nam"/>
        <form:radiobutton path="gioiTinh" value="Nữ" label="Nữ"/>
        <br><br>
        <label>Ngày sinh:</label>
        <form:input type="date" path="ngaySinh"/>
        <form:errors path="ngaySinh" cssStyle="color:red"/>
        <br><br>
        <label>Số điện thoại:</label>
        <form:input type="number" path="soDienThoai"/>
        <form:errors path="soDienThoai" cssStyle="color:red"/>
        <br><br>
        <label>Địa chỉ:</label>
        <form:input path="diaChi"/>
        <form:errors path="diaChi" cssStyle="color:red"/>
        <br><br>
        <label>Mã cửa hàng:</label>
        <form:select path="cuaHang" id="selectCuaHang">
            <c:forEach items="${listCuaHang}" var="cuaHang">
                <form:option value="${cuaHang}" data-ten="${cuaHang.ten}" label="${cuaHang.ma}"/>
            </c:forEach>
        </form:select>
        <label id="tenCuaHang"></label>
        <br><br>
        <label>Mã chức vụ:</label>
        <form:select path="chucVu" id="selectChucVu">
            <c:forEach items="${listChucVu}" var="chucVu">
                <form:option value="${chucVu}" data-ten="${chucVu.ten}" label="${chucVu.ma}"/>
            </c:forEach>
        </form:select>
        <label id="tenChucVu"></label>
        <br><br>
        <label>Mật khẩu: </label>
        <form:input path="matKhau"/>
        <form:errors path="matKhau" cssStyle="color: red"/>
        <br> <br>
        <label>Trạng thái</label>
        <form:radiobutton path="trangThai" value="0" label="Đang làm việc"/>
        <form:radiobutton path="trangThai" value="1" label="Nghỉ việc"/>
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