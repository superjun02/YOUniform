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
		<div>${uniform.league}</div>
		<div class="detailSubjectText">${uniform.subject}</div>
		<div class="detailPriceText mt-1">
			<fmt:formatNumber value="${uniform.price}" type="currency" />
		</div>
		<div class="descriptionText mt-3">${uniform.description}</div>
		<div class="statusText mt-4">상태: ${uniform.status}</div>
		<c:if test="${loginId ne 'admin'}">
			<div class="mt-5 d-flex">
				<button id="basketBtn" class="w-50 btn btn-secondary" value="${userId}">장바구니</button>
				<button id="purchaseBtn" class="w-50 btn btn-primary" value="${userId}">구매하기</button>
			</div>
		</c:if>
		<c:if test="${loginId eq 'admin'}">
			<div class="mt-5">
				<button id="deleteBtn" class="w-100 btn btn-danger" value="${uniform.id}">삭제하기</button>
			</div>
		</c:if>
	</div>
</div>
<script>
	$(document).ready(function() {
		$('#purchaseBtn').on('click', function() {
			let userId = $('#purchaseBtn').val();
			if (!userId) {
				alert("구매를 할려면 회원가입을 진행해주세요");
				return;
			}
			location.href = "/order/purchase-view?uniformId=${uniform.id}";
		});
		
		$('#basketBtn').on('click', function() {
			let userId = $('#basketBtn').val();
			let uniformId = ${uniform.id};
			if (!userId) {
				alert("구매를 할려면 회원가입을 진행해주세요");
				return;
			}
			
			$.ajax({
				type : 'POST',
				url : "/basket/add_basket",
				data : {
					"uniformId" : uniformId
				},
				success : function(data) {
					if (data.code == 200) {
						location.reload(true);
						alert("장바구니에 추가되었습니다");
					} else {
						alert(data.error_message);
					}
				},
				error : function(e) {
					alert("추가 하는데 실패했습니다.")
				}
			});
		});
		
		$('#deleteBtn').on('click', function() {
			let uniformId = $(this).val();
			
			$.ajax({
				type : 'POST',
				url : "/uniform/delete",
				data : {
					"uniformId" : uniformId
				},
				success : function(data) {
					if (data.code == 200) {
						alert("삭제를 하는데 성공했습니다.");
						location.href="/home/home-view";
					} else {
						alert(data.error_message);
					}
				},
				error : function(e) {
					alert("삭제를 하는데 실패했습니다.");
				}
			});
		});
	})
</script>
