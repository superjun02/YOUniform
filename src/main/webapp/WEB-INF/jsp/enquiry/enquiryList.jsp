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
				<th class="col-1">번호</th>
				<th class="col-1">
					<select id="division" class="form-control-sm">
						<option value="">분류</option>
						<option value="배송문의" <c:if test="${division == '배송문의'}">selected</c:if>>배송문의</option>
						<option value="구매문의" <c:if test="${division == '구매문의'}">selected</c:if>>구매문의</option>
						<option value="신청문의" <c:if test="${division == '신청문의'}">selected</c:if>>신청문의</option>
						<option value="기타문의" <c:if test="${division == '기타문의'}">selected</c:if>>기타문의</option>
					</select>
				</th>
				<th class="col-6">제목</th>
				<th class="col-2">작성자</th>
				<th class="col-2">날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${postList}" var="post">
				<tr class="clickable-row" data-href="/enquiry/enquiry-detail-view?id=${post.enquiry.id}&userId=${post.enquiry.userId}">
					<td>${post.enquiry.id}</td>
					<td>${post.enquiry.division}</td>
					<td>
						${post.enquiry.subject}
						<c:if test="${not empty post.enquiry.answer}">
							<small class="text-secondary">답변완료</small>
						</c:if>
					</td>
					<td>${post.userId}</td>
					<td><fmt:formatDate value="${post.enquiry.createdAt}" pattern="yyyy-MM-dd" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<nav aria-label="Page navigation">
	    <ul class="pagination justify-content-center">
	        <c:if test="${pageInfo.hasPreviousPage}">
	            <li class="page-item">
	                <a class="page-link" href="/enquiry/enquiry-list-view?division=${division}&page=${currentPage - 1}" aria-label="Previous">
	                    <span aria-hidden="true">&laquo;</span>
	                </a>
	            </li>
	        </c:if>
	        <c:forEach begin="1" end="${totalPages}" var="i">
	            <li class="page-item <c:if test='${currentPage == i}'>active</c:if>">
	                <a class="page-link" href="/enquiry/enquiry-list-view?division=${division}&page=${i}">${i}</a>
	            </li>
	        </c:forEach>
	        <c:if test="${pageInfo.hasNextPage}">
	            <li class="page-item">
	                <a class="page-link" href="/enquiry/enquiry-list-view?division=${division}&page=${currentPage + 1}" aria-label="Next">
	                    <span aria-hidden="true">&raquo;</span>
	                </a>
	            </li>
	        </c:if>
	    </ul>
	</nav>
</div>
<script>
	$(document).ready(function() {
		$('#division').on('change', function() {
			let division = $('#division').val();
			location.href = "/enquiry/enquiry-list-view?division=" + division;
		});
		
		$('.clickable-row').on('click', function() {
			location.href = this.dataset.href;
		});
	});
</script>