<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="d-flex justify-content-center">
	<div class="w-50">
		<img alt="" src="${uniform.image}" width="390px" height="496px">
	</div>
	<div class="w-50 mx-2">
		<div class="detailSubjectText">${uniform.subject}</div>
		<div class="detailPriceText mt-1">
			<fmt:formatNumber value="${uniform.price}" type="currency" />
		</div>
		<div class="descriptionText mt-3">${uniform.description}</div>
		<div class="statusText mt-4">상태: ${uniform.status}</div>
		<div class="mt-5 d-flex">
			<button id="basketBtn" class="w-50 btn btn-secondary" value="${uniform.id}">장바구니</button>
			<button id="purchaseBtn" class="w-50 btn btn-primary" value="${uniform.id}">구매하기</button>
		</div>
		<c:if test="${loginId eq 'admin'}">
			<div>
				<button id="deleteBtn" class="w-100 btn btn-danger" value="${uniform.id}">삭제하기</button>
			</div>
		</c:if>
	</div>
</div>
<script>
	$(document).ready(function() {
		
	})
</script>
