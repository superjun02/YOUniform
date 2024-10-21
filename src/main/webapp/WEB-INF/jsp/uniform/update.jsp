<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class=" w-100">
	<div class="form-row">
		<div class="form-group col-md-3">
			<label for="league">카테고리</label>
			<select id="league" class="form-control">
			    <option value="Kleague" ${uniform.league == 'Kleague' ? 'selected' : ''}>K-league</option>
			    <option value="SerieA" ${uniform.league == 'SerieA' ? 'selected' : ''}>Serie A</option>
			    <option value="LaLiga" ${uniform.league == 'LaLiga' ? 'selected' : ''}>LaLiga</option>
			    <option value="EPL" ${uniform.league == 'EPL' ? 'selected' : ''}>EPL</option>
			    <option value="Ligue1" ${uniform.league == 'Ligue1' ? 'selected' : ''}>Ligue 1</option>
			    <option value="Bundesliga" ${uniform.league == 'Bundesliga' ? 'selected' : ''}>Bundesliga</option>
			</select> 
		</div>
		<div class="form-group col-md-6">
			<label for="subject">제목</label> <input type="text" id="subject"
				class="form-control" placeholder="예) 바르셀로나 13-14 #10 MESSI M" value="${uniform.subject}">
		</div>
		<div class="form-group col-md-3">
			<label for="price">가격</label> <input type="text" id="price"
				class="form-control" value="${uniform.price}">
		</div>
	</div>
	<div class="form-group">
		<label for="description">내용</label>
		<textarea id="description" rows="5" class="form-control"
			placeholder="예) 13년도에 바르셀로나 공항에서 구매한 상품입니다. 사용감이 조금 있습니다.">${uniform.description}</textarea>
	</div>
	<div class="form-row d-flex justify-content-between">
		<div class="col-md-3 d-flex">
			<select id="status" class="form-control">
				<option value="--상태입력--" ${uniform.status == '--상태입력--' ? 'selected' : ''}>--상태입력--</option>
				<option value="Excellent" ${uniform.status == 'Excellent' ? 'selected' : ''}>Excellent</option>
				<option value="Very Good" ${uniform.status == 'Very Good' ? 'selected' : ''}>Very Good</option>
				<option value="Good" ${uniform.status == 'Good' ? 'selected' : ''}>Good</option>
				<option value="Not Bad" ${uniform.status == 'Not Bad' ? 'selected' : ''}>Not Bad</option>
				<option value="Bad" ${uniform.status == 'Bad' ? 'selected' : ''}>Bad</option>
			</select>
		</div>
		<div class="form-group col-md-2 d-flex align-items-end">
			<button id="updateBtn" class="btn btn-primary w-100" value="${uniform.id}">수정하기</button>
		</div>
	</div>
</div>
<script>
	$(document).ready(function() {
		$('#updateBtn').on('click', function() {
			let uniformId = $('#updateBtn').val();
			let league = $('#league').val().trim();
			let subject = $('#subject').val().trim();
			let price = $('#price').val().trim();
			let description = $('#description').val().trim();
			let fileName = $('#file').val();
			let status = $('#status').val().trim();
			
			if (!subject) {
				alert("제목을 입력하세요");
				return;
			}

			if (!price) {
				alert("가격을 입력하세요");
				return;
			}

			if (!description) {
				alert("유니폼에 대한 설명을 입력하세요");
				return;
			}

			if (status === '--상태입력--') {
				alert("상태를 입력해주세요");
				return;
			}
			
			let formData = new FormData();
			formData.append("id", uniformId);
			formData.append("league", league);
			formData.append("subject", subject);
			formData.append("price", price);
			formData.append("description", description);
			formData.append("status", status);

			$.ajax({
				type : 'POST',
				url : "/uniform/update",
				data : formData,
				processData: false,
		    	contentType: false,
				success : function(data) {
					if (data.code == 200) {
						alert("수정이 완료되었습니다.");
						location.href = "/uniform/detail-view?id=" + data.id;
					} else {
						alert(data.error_message);
					}
				},
				error : function(e) {
					alert("글을 작성하는데 실패하였습니다.")
				}
			});
		});
	});
</script>