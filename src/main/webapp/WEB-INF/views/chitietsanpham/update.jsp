<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sửa cửa hàng</title>
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
    <h1>Sửa thông tin chi tiết sản phẩm</h1>
    <br><br>
    <form:form action="/admin/chi-tiet-san-pham/update/${chiTiet.id}" method="post" modelAttribute="chiTiet">
        Mã sản phẩm:
        <form:select path="sanPham" id="selectSanPham" onchange="getData()">
            <c:forEach items="${listSanPham}" var="sanPham">
                <form:option value="${sanPham.id}" data-ten="${sanPham.ten}">${sanPham.ma}</form:option>
            </c:forEach>
        </form:select>
        <label id="tenSanPham"></label>
        <br><br>
        Mã dòng sản phẩm:
        <form:select path="dongSanPham" id="selectDongSp" onchange="getData()">
            <c:forEach items="${listDongSp}" var="dongSanPham">
                <form:option value="${dongSanPham.id}" data-ten="${dongSanPham.ten}">${dongSanPham.ma}</form:option>
            </c:forEach>
        </form:select>
        <label id="tenDongSp"></label>
        <br><br>
        Mã màu sắc:
        <form:select path="mauSac" id="selectMauSac" onchange="getData()">
            <c:forEach items="${listMauSac}" var="mauSac">
                <form:option value="${mauSac}" data-ten="${mauSac.ten}">${mauSac.ma}</form:option>
            </c:forEach>
        </form:select>
        <label id="tenMauSac"></label>
        <br><br>
        <label>Năm BH: </label>
        <form:input type="number" path="namBh"/>
        <form:errors path="namBh" cssStyle="color: red"/>
        <br> <br>
        <label>Mô tả: </label>
        <form:input path="moTa"/>
        <form:errors path="moTa" cssStyle="color: red"/>
        <br> <br>
        <label>Số lượng tồn: </label>
        <form:input type="number" path="soLuongTon"/>
        <form:errors path="soLuongTon" cssStyle="color: red"/>
        <br> <br>
        <label>Giá nhập: </label>
        <form:input type="number" path="giaNhap"/>
        <form:errors path="giaNhap" cssStyle="color: red"/>
        <br> <br>
        <label>Giá bán: </label>
        <form:input type="number" path="giaBan"/>
        <form:errors path="giaBan" cssStyle="color: red"/>
        <br> <br>
        <label>Trạng thái</label>
        <form:radiobutton path="trangThai" value="0" label="Đang kinh doanh"/>
        <form:radiobutton path="trangThai" value="1" label="Ngừng kinh doanh"/>
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
//label sp
        var selectSanPham = document.getElementById("selectSanPham");
        var indexSanPham = selectSanPham.selectedIndex;
        var optionsSanPham = selectSanPham.options[indexSanPham];
        document.getElementById("tenSanPham").innerHTML = optionsSanPham.dataset.ten;

// label dongsp
        var selectDongSp = document.getElementById("selectDongSp");
        var indexDongSp = selectDongSp.selectedIndex;
        var optionsDongSp = selectDongSp.options[indexDongSp];
        document.getElementById("tenDongSp").innerHTML = optionsDongSp.dataset.ten;

// label mausac
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