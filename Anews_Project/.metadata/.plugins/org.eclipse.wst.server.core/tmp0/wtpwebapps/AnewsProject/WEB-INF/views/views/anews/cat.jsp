<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="SlugUtil" class="spring.util.SlugUtil"></jsp:useBean>

<h3>Tin tức</h3>
<div class="main-content items-new">
	<ul>
		<c:if test="${not empty listTinTuc }">
			<c:forEach items="${listTinTuc}" var="tinTuc">
				<li>
					<h2>
						<a href="${pageContext.request.contextPath}/anews/detail/${SlugUtil.makeSlug(tinTuc.tenTinTuc)}/${tinTuc.idTinTuc}" title="">${tinTuc.tenTinTuc }</a>
					</h2>
					<div class="item">
						<p>${tinTuc.moTa}</p>
						<div class="clr"></div>
					</div>
				</li>
			</c:forEach>
		</c:if>
		
	</ul>
</div>