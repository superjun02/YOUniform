<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div>
	<table class="table text-center">
		<thead>
			<tr>
				<th>주문번호</th>
				<th>진행상황</th>
				<th>가격</th>
				<th>구매날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${orderList}" var="order" varStatus="status"> 
				<tr class="clickable-row" data-href="/order/order-detail-view?id=${order.id}&userId=${order.userId}">
					<td>${order.orderNumber}</td>
					<td>${order.status}</td>
					<td>${order.totalPrice}원</td>
					<td>
						<fmt:formatDate value="${order.createdAt}" pattern="yyyy년 MM월 dd일 HH시 mm분" /><br>
					</td>
					<td>
						<c:if test="${order.status eq '주문 확인중' or loginId eq 'admin'}">
							<button class="delBtn btn btn-danger" value="${order.id}">주문취소</button>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<script>
	$(document).ready(function() {
		$('.delBtn').on('click', function() {
			let orderId = $(this).val();
			
			$.ajax({
				type : 'POST',
				url : "/order/delete",
				data : {
					"orderId" : orderId
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
		
		$('.clickable-row').on('click', function() {
			location.href = this.dataset.href;
		});
	});
</script>