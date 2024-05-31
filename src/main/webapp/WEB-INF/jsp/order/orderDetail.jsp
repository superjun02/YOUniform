<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div>
	<c:forEach items="${uniformList}" var="uniform">
		<div class="d-flex">
			<div class="col-3">
				<img alt="장바구니 이미지" src="${uniform.image}" width="110px" height="140px"> 
			</div>
			<div class="col-7">
				<div>${uniform.subject}</div>
				<div class="mt-2">
					<fmt:formatNumber value="${uniform.price}" type="currency" />
				</div>
				<div class="mt-2">${uniform.description}</div>
			</div>
		</div>
	</c:forEach>
</div>