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
				<td>
					<c:if test="${loginId eq 'admin'}">
						<div class="d-flex justify-content-end">
							<button id="delBtn" class="btn btn-danger" value="${post.enquiry.id}">삭제</button>
						</div>
					</c:if>		
				</td>
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
	<c:if test="${loginId eq 'admin' and empty post.enquiry.answer}">
		<div id="adminReplyBox" class="mt-3 mx-2">
			<textarea id="content" rows="2" class="w-100 form-control"></textarea>
			<div class="d-flex justify-content-end">
				<div>
					<button id="replyBtn" class="btn btn-info" value="${post.enquiry.id}">작성</button>
				</div>
			</div>
		</div>
	</c:if>
</div>
<script>
	$(document).ready(function() {
		$('#replyBtn').on('click', function() {
			let answer = $('#content').val().trim();
			let id = $('#replyBtn').val();
			
			$.ajax({
				type : 'POST',
				url : "/enquiry/add-reply",
				data : {'answer':answer, 'id':id},
				success : function(data) {
					if (data.code == 200) {
						alert("작성이 완료되었습니다");
						location.reload();
					} else {
						alert(data.error_message);
					}
				},
				error : function(e) {
					alert("답글을 작성하는데 실패하였습니다.")
				}
			});
		});
		
		$('#delBtn').on('click', function() {
			let id = $('#replyBtn').val();
			
			$.ajax({
				type : 'POST',
				url : "/enquiry/delete",
				data : {'id':id},
				success : function(data) {
					if (data.code == 200) {
						alert("삭제가 완료되었습니다");
						location.href = "/enquiry/enquiry-list-view";
					} else {
						alert(data.error_message);
					}
				},
				error : function(e) {
					alert("삭제하는데 실패하였습니다.")
				}
			});
		});
	});
</script>