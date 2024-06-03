<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div>
	<table class="table text-center">
		<thead>
			<tr>
				<th>유저아이디</th>
				<th>제목</th>
				<th>날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestList}" var="request"> 
				<tr class="clickable-row" data-href="/uniform/request-check-view?id=${request.uniform.id}">
					<td>${request.userId}</td>
					<td>${request.uniform.subject}</td>
					<td>
						<fmt:formatDate value="${request.uniform.createdAt}" pattern="yyyy년 MM월 dd일 HH시 mm분" /><br>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<script>
	$(document).ready(function() {
		$('.clickable-row').on('click', function() {
			location.href = this.dataset.href;
		});
	})
</script>