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
	<div class="d-flex justify-content-center mt-3">
		<h3>
			<span class="status ${status == '주문 확인중' ? 'text-primary' : ''}">주문 확인중</span> ->
            <span class="status ${status == '배송 준비중' ? 'text-primary' : ''}">배송 준비중</span> ->
            <span class="status ${status == '배송 시작' ? 'text-primary' : ''}">배송 시작</span> ->
            <span class="status ${status == '배송 완료' ? 'text-primary' : ''}">배송 완료</span>
		</h3>
	</div>
	<c:if test="${loginId eq 'admin'}">
		<div class="mt-2 ml-4 d-flex align-items-center">
			<h5 class="mt-2">진행 상황 변경</h5>
			<div class=" ml-2">
				<select id="status" class="form-control">
					<option value="주문 확인중" ${status == '주문 확인중' ? 'selected' : ''}>주문 확인중</option>
		            <option value="배송 준비중" ${status == '배송 준비중' ? 'selected' : ''}>배송 준비중</option>
		            <option value="배송 시작" ${status == '배송 시작' ? 'selected' : ''}>배송 시작</option>
		            <option value="배송 완료" ${status == '배송 완료' ? 'selected' : ''}>배송 완료</option>
				</select>
			</div>
		</div>
	</c:if>
</div>
<script>
    $(document).ready(function() {
        $('#status').on('change', function() {
            let status = $(this).val();
            let orderId = ${orderId};
            
            $.ajax({
				type : 'POST',
				url : "/order/update-status",
				data : {
					"status" : status,
					"orderId" : orderId
				},
				success : function(data) {
					if (data.code == 200) {
						alert('진행 상황이 성공적으로 업데이트되었습니다.');
                        location.reload();
					} else {
						alert(data.error_message);
					}
				},
				error : function(e) {
					alert('진행 상황 업데이트에 실패했습니다.');
				}
			});
        });
    });
</script>