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
			<div class="col-2 d-flex align-items-center justify-content-end">
				<button class="delBtn btn btn-danger" value="${uniform.id}">삭제</button>
			</div>
		</div>
	</c:forEach>
	<div class="mt-2">
		<button id="purchaseBtn" class="btn btn-primary form-control">구매하기</button>
	</div>
</div>
<script>
	$(document).ready(function() {
		$('.delBtn').on('click', function() {
			let uniformId = $(this).val();
			
			$.ajax({
				type : 'POST',
				url : "/basket/delete",
				data : {
					"uniformId" : uniformId
				},
				success : function(data) {
					if (data.code == 200) {
						alert("삭제를 하는데 성공했습니다.");
						location.reload();
					} else {
						alert(data.error_message);
					}
				},
				error : function(e) {
					alert("삭제를 하는데 실패했습니다.");
				}
			});
		});
		
		$('#purchaseBtn').on('click', function() {
			
			$.ajax({
				type : 'POST',
				url : "/basket/is_empty",
				success : function(data) {
					if (data.result) {
						alert("장바구니가 비어있습니다.");
					} else {
						location.href="/order/purchase-view";
					}
				},
				error : function(e) {
					alert("장바구니 확인을 하는데 실패했습니다.");
				}
			});
		});
	});
</script>