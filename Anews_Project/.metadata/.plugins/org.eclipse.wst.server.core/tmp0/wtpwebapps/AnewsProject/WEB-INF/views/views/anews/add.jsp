<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>		<!-- chú ý phải có taglib do hibernate cung cấp này -->

<h3>Thêm tin</h3>
<div class="msg">${msg}</div>
		
<div class="main-content">
	<form action="${pageContext.request.contextPath }/anews/add" method="post" enctype="multipart/form-data">
	
		<form:errors path="tinTucError.tenTinTuc" cssStyle="color:red;font-style:italic" ></form:errors><br />
		Tên tin: <input type="text" name="tenTinTuc" value="${tinTuc.tenTinTuc }" /><br /><br />
		
		<form:errors path="tinTucError.moTa" cssStyle="color:red;font-style:italic" ></form:errors><br />
		Mô tả: <textarea rows="3" cols="50" name="moTa">${tinTuc.moTa }</textarea><br /><br />
		
		<form:errors path="tinTucError.chiTiet" cssStyle="color:red;font-style:italic" ></form:errors><br />
		Chi tiết: <textarea rows="10" cols="50" name="chiTiet">${tinTuc.chiTiet }</textarea><br /><br />
		
		<form:errors path="tinTucError.hinhAnh" cssStyle="color:red;font-style:italic" ></form:errors><br />
		Hình ảnh: <input type="file" name="pic" value="" />${multipartFile }<br /><br />
		
		Chọn danh mục tin:
		<select id="idDanhMucTin" name="idDanhMucTin" >
			<c:if test="${not empty listDanhMucTin}">
				<c:forEach items="${listDanhMucTin }" var="danhMucTin">
					<option value="${danhMucTin.idDanhMucTin }" >${danhMucTin.tenDanhMucTin}</option>
				</c:forEach>
			</c:if>
			<c:if test="${empty listDanhMucTin}">
				<div style="color:'red'">Không có dữ liếu</div>
			</c:if>
		</select> <br /><br />
		
		<input type="submit" name="submit" value="Thêm" /><br /><br />
	</form>
</div>