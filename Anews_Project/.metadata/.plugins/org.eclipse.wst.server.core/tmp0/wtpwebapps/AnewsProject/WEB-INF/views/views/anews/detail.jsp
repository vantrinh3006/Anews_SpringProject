<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:if test="${not empty tinTuc}">
	<h3>${tinTuc.tenTinTuc }</h3>
	<div class="main-content">
		<p>${tinTuc.chiTiet}</p>
	</div>
</c:if>

