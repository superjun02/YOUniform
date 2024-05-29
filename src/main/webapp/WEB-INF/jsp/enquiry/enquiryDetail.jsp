<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div>
	<table class="table">
		<tbody>
			<tr>
				<th>제목</th>
				<td>${post.enquiry.subject}</td>
				<td><fmt:formatDate	value="${post.enquiry.createdAt}" pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${post.userId}</td>
			</tr>
		</tbody>
	</table>
	<div class="mx-2">
		${post.enquiry.description}
	</div>
	
	<c:if test="${not empty post.enquiry.answer}">
		<div class="mx-2 mt-3">
			<h5>관리자: ${post.enquiry.answer}</h5>
		</div>
	</c:if>
</div>