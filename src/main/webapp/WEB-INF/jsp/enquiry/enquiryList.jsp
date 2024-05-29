<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div>
	<c:if test="${not empty message}">
	    <script>
	        alert("${message}");
	        location.href = "/enquiry/enquiry-list-view";
	    </script>
	</c:if>
	<table class="table text-center">
		<thead>
			<tr>
				<th>번호</th>
				<th>
					<select id="division" class="form-control-sm">
						<option value="분류">분류</option>
						<option value="배송문의" <c:if test="${division == '배송문의'}">selected</c:if>>배송문의</option>
						<option value="구매문의" <c:if test="${division == '구매문의'}">selected</c:if>>구매문의</option>
						<option value="신청문의" <c:if test="${division == '신청문의'}">selected</c:if>>신청문의</option>
						<option value="기타문의" <c:if test="${division == '기타문의'}">selected</c:if>>기타문의</option>
					</select>
				</th>
				<th>제목</th>
				<th>작성자</th>
				<th>날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${postList}" var="post">
				<tr class="clickable-row" data-href="/enquiry/enquiry-detail-view?id=${post.enquiry.id}&userId=${post.enquiry.userId}">
					<td>${post.num}</td>
					<td>${post.enquiry.division}</td>
					<td>${post.enquiry.subject}</td>
					<td>${post.userId}</td>
					<td><fmt:formatDate value="${post.enquiry.createdAt}" pattern="yyyy-MM-dd" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<script>
	$(document).ready(function() {
		$('#division').on('change', function() {
			let division = $('#division').val();
			
			if (division === '분류') {
				location.href = "/enquiry/enquiry-list-view";
				return;
			}
			location.href = "/enquiry/enquiry-list-view?division=" + division;
		});
		
		$('.clickable-row').on('click', function() {
			location.href = this.dataset.href;
		});
	});
</script>