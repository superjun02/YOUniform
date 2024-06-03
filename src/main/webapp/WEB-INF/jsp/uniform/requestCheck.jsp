<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class=" w-100">
	<div class="form-row">
		<div class="form-group col-md-3">
			<label for="league">카테고리</label> <select id="league"
				class="form-control">
				<option value="Kleague" ${uniform.league == 'Kleague' ? 'selected' : ''}>K-league</option>
	            <option value="SerieA" ${uniform.league == 'SerieA' ? 'selected' : ''}>Serie A</option>
	            <option value="laliga" ${uniform.league == 'LaLiga' ? 'selected' : ''}>LaLiga</option>
	            <option value="EPL" ${uniform.league == 'EPL' ? 'selected' : ''}>EPL</option>
	            <option value="Ligue1" ${uniform.league == 'Ligue1' ? 'selected' : ''}>Ligue 1</option>
	            <option value="Bundesliga" ${uniform.league == 'Bundesliga' ? 'selected' : ''}>Bundesliga</option>
			</select>
		</div>
		<div class="form-group col-md-6">
			<label for="subject">제목</label> <input type="text" id="subject"
				class="form-control" value="${uniform.subject}">
		</div>
		<div class="form-group col-md-3">
			<label for="price">가격</label> <input type="text" id="price"
				class="form-control" value="${uniform.price}">
		</div>
	</div>
	<div class="form-group">
		<label for="description">내용</label>
		<textarea id="description" rows="5" class="form-control">${uniform.description}</textarea>
	</div>
	<div class="form-row d-flex justify-content-between">
		<div class="form-group col-md-6 d-flex">
			<div>
				<img alt="" src="${uniform.image}" width="195px" height="248px">
			</div>
		</div>
		<div class="col-md-3 d-flex">
			<select id="status" class="form-control">
				<option>--상태입력--</option>
				<option ${uniform.status == 'Excellent' ? 'selected' : ''}>Excellent</option>
				<option ${uniform.status == 'Very Good' ? 'selected' : ''}>Very Good</option>
				<option ${uniform.status == 'Good' ? 'selected' : ''}>Good</option>
				<option ${uniform.status == 'Not Bad' ? 'selected' : ''}>Not Bad</option>
				<option ${uniform.status == 'Bad' ? 'selected' : ''}>Bad</option>
			</select>
		</div>
		<div class="form-group col-md-2 d-flex align-items-start">
			<button id="acceptBtn" class="btn btn-primary w-100" value="${uniform.id}">승인하기</button>
		</div>
	</div>
</div>
<script>
	$(document).ready(function() {
		$('#acceptBtn').on('click', function() {
			let league = $('#league').val().trim();
			let subject = $('#subject').val().trim();
			let price = $('#price').val().trim();
			let description = $('#description').val().trim();
			let status = $('#status').val().trim();
			let id = $('#acceptBtn').val();

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
			formData.append("league", league);
			formData.append("subject", subject);
			formData.append("price", price);
			formData.append("description", description);
			formData.append("status", status);
			formData.append("id", id);

			$.ajax({
				type : 'POST',
				url : "/uniform/accept",
				data : formData,
				processData: false,
	            contentType: false,
				success : function(data) {
					if (data.code == 200) {
						alert("승인되었습니다.");
						location.href = "/home/home-view";
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