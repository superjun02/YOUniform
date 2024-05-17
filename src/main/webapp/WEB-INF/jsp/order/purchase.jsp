<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div>
	<table class="table text-center">
		<thead>
			<tr>
				<th>No.</th>
				<th>제품</th>
				<th>가격</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>1</td>
				<td>${uniform.subject}</td>
				<td>${uniform.price}원</td>
			</tr>
		</tbody>
	</table>
	<div class="d-flex justify-content-between">
		<span>무통장입금 계좌번호: 하나은행 378-321321-3222</span>
		<span>총액: ${uniform.price}원</span>
	</div>
	<div class="mt-2">
		<span>주소</span>
		<div>
			<input type="text" class="form-control" id="address">
		</div>
		<div class="d-flex justify-content-end mt-2">
			<button class="btn btn-primary purchaseBtn">구매하기</button>
		</div>
	</div>
</div>
<script>
	$(document).ready(function() {
		$('.purchaseBtn').on('click', function() {
			let address = $('#address').val().trim();
			let uniformId = ${uniform.id};
			let totalPrice = ${uniform.price};
			
			if (!address) {
				alert("주소를 입력해주세요");
				return;
			}
			
			$.ajax({
				type : 'POST',
				url : "/order/purchase",
				data : {
					"address" : address,
					"uniformId" : uniformId,
					"totalPrice" : totalPrice
				},
				success : function(data) {
					if (data.code == 200) {
						alert("구매가 완료되었습니다.");
						location.href="/home/home-view";
					} else {
						alert(data.error_message);
					}
				},
				error : function(e) {
					alert("구매하는데 실패하였습니다.")
				}
			});
		});
	});
			
</script>