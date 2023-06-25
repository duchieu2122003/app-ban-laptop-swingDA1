<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Chi tiết sản phẩm</title>
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
        h1 {
            text-align: center;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
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
        <a href="/ban-hang/login-form" type="submit" class="btn btn-outline-primary">Đăng nhập</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    </div>
</nav>
<% if (request.getAttribute("message") != null) {%>
<script>
    alert("<%=request.getAttribute("message")%>")
</script>
<%} %>
<section class="container">
    <h1>Quản lý chi tiết sản phẩm</h1>
    <br><br>
    <form:form action="/admin/chi-tiet-san-pham/add" method="post" modelAttribute="chiTiet">
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
        <form:button type="submit" class="btn btn-success"
                     onclick=" return thongBao('thêm')">Thêm</form:button>
    </form:form>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">STT</th>
            <th scope="col">Sản phẩm</th>
            <th scope="col">Dòng sản phẩm</th>
            <th scope="col">Màu sắc</th>
            <th scope="col">Năm bảo hành</th>
            <th scope="col">Mô tả</th>
            <th scope="col">Số lượng</th>
            <th scope="col">Giá nhập</th>
            <th scope="col">Giá bán</th>
            <th scope="col">Trạng thái</th>
            <th colspan="3">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listChiTiet}" var="chiTietSanPham" varStatus="i">
            <tr>
                <th scope="row">${i.index+1}</th>
                <td>${chiTietSanPham.sanPham.ten}</td>
                <td>${chiTietSanPham.dongSanPham.ten}</td>
                <td>${chiTietSanPham.mauSac.ten}</td>
                <td>${chiTietSanPham.namBh}</td>
                <td>${chiTietSanPham.moTa}</td>
                <td>${chiTietSanPham.soLuongTon}</td>
                <td>${chiTietSanPham.giaNhap}</td>
                <td>${chiTietSanPham.giaBan}</td>
                <td>${chiTietSanPham.trangThai==0?"Đang kinh doanh":"Ngừng kinh doanh"}</td>
                <td>
                    <button class="btn btn-primary"><a
                            href="/admin/chi-tiet-san-pham/detail/${chiTietSanPham.id}">Xem</a>
                    </button>
                </td>
                <td>
                    <button class="btn btn-warning"><a
                            href="/admin/chi-tiet-san-pham/view-update/${chiTietSanPham.id}">Sửa</a>
                    </button>
                </td>
                <td>
                    <button class="btn btn-danger"><a href="/admin/chi-tiet-san-pham/remove/${chiTietSanPham.id}"
                                                      onclick="return thongBao('xóa')">Xóa</a>
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <c:if test="${currentPage >1}">
                <li class="page-item" onclick="return thongBao('previous')">
                    <a class="page-link" href="/admin/chi-tiet-san-pham/hien-thi?pageNo=${currentPage - 1}">Previous</a>
                </li>
            </c:if>
            <c:forEach begin="1" end="${totalPages}" varStatus="i">
                <li class="page-item" onclick="return thongBao('tới trang ${i.index}')">
                    <a class="page-link" href="/admin/chi-tiet-san-pham/hien-thi?pageNo=${i.index}">${i.index}</a></li>
            </c:forEach>
            <c:if test="${currentPage < totalPages}">
                <li class="page-item" onclick="return thongBao('next')">
                    <a class="page-link" href="/admin/chi-tiet-san-pham/hien-thi?pageNo=${currentPage + 1}">Next</a>
                </li>
            </c:if>
        </ul>
    </nav>
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