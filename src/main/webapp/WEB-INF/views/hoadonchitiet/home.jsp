<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Hóa đơn chi tiết</title>
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
    <h1>Quản lý hóa đơn chi tiết</h1>
    <br>
    <form:form action="/admin/hoa-don-chi-tiet/add" method="post" modelAttribute="hoaDonChiTiet">
        <label>Mã hóa đơn:</label>
        <form:select path="idHoaDon">
            <c:forEach items="${listHoaDon}" var="hoaDon">
                <form:option value="${hoaDon.id}" label="${hoaDon.ma}"/>
            </c:forEach>
        </form:select>
        <br><br>
        <label>Chi tiết sản phẩm:</label>
        <form:select path="idChiTietSanPham">
            <c:forEach items="${listChiTietSanPham}" var="chiTietSanPham">
                <form:option value="${chiTietSanPham.id}"
                             label="${chiTietSanPham.sanPham.ten} ${chiTietSanPham.dongSanPham.ten} ${chiTietSanPham.mauSac.ten} "/>
            </c:forEach>
        </form:select>
        <br><br>
        <label>Số lượng:</label>
        <form:input type="number" path="soLuong"/>
        <form:errors path="soLuong" cssStyle="color: red"/>
        <br><br>
        <form:button type="submit" class="btn btn-success"
                     onclick=" return thongBao('thêm')">Thêm</form:button>
    </form:form>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">STT</th>
            <th scope="col">Hóa đơn</th>
            <th scope="col">Chi tiết sản phẩm</th>
            <th scope="col">Số lượng</th>
            <th scope="col">Đơn giá</th>
            <th colspan="3">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ListHoaDonChiTiet}" var="hoaDonChiTiet" varStatus="i">
            <tr>
                <th scope="row">${i.index+1}</th>
                <td>${hoaDonChiTiet.hoaDon.ma}</td>
                <td>${hoaDonChiTiet.chiTietSanPham.sanPham.ten} ${hoaDonChiTiet.chiTietSanPham.dongSanPham.ten} ${hoaDonChiTiet.chiTietSanPham.mauSac.ten}</td>
                <td>${hoaDonChiTiet.soLuong}</td>
                <td>${hoaDonChiTiet.donGia}</td>
                <td>
                    <button class="btn btn-primary"><a
                            href="/admin/hoa-don-chi-tiet/detail/${hoaDonChiTiet.hoaDon.id}/${hoaDonChiTiet.chiTietSanPham.id}">Xem</a>
                    </button>
                </td>
                <td>
                    <button class="btn btn-warning"><a
                            href="/admin/hoa-don-chi-tiet/view-update/${hoaDonChiTiet.hoaDon.id}/${hoaDonChiTiet.chiTietSanPham.id}">Sửa</a>
                    </button>
                </td>
                <td>
                    <button class="btn btn-danger"><a
                            href="/admin/hoa-don-chi-tiet/remove/${hoaDonChiTiet.hoaDon.id}/${hoaDonChiTiet.chiTietSanPham.id}">Xóa</a>
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
                    <a class="page-link" href="/admin/hoa-don-chi-tiet/hien-thi?pageNo=${currentPage - 1}">Previous</a>
                </li>
            </c:if>
            <c:forEach begin="1" end="${totalPages}" varStatus="i">
                <li class="page-item" onclick="return thongBao('tới trang ${i.index}')">
                    <a class="page-link" href="/admin/hoa-don-chi-tiet/hien-thi?pageNo=${i.index}">${i.index}</a></li>
            </c:forEach>
            <c:if test="${currentPage < totalPages}">
                <li class="page-item" onclick="return thongBao('next')">
                    <a class="page-link" href="/admin/hoa-don-chi-tiet/hien-thi?pageNo=${currentPage + 1}">Next</a>
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