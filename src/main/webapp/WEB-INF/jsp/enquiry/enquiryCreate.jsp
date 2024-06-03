<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="w-100">
     <div class="form-row">
         <div class="form-group col-md-3">
             <label for="division">분류</label>
             <select id="division" class="form-control">
                 <option>배송문의</option>
                 <option>구매문의</option>
                 <option>신청문의</option>
                 <option>기타문의</option>
             </select>
         </div>
         <div class="form-group col-md-9">
             <label for="subject">제목</label>
             <input type="text" id="subject" class="form-control">
         </div>
     </div>
     <div class="form-group">
         <label for="description">내용</label>
         <textarea id="description" rows="5" class="form-control"></textarea>
     </div>
     <div class="form-row d-flex justify-content-end">
         <div class="form-group col-md-2 d-flex align-items-end">
             <button id="applyBtn" class="btn btn-primary w-100">문의하기</button>
         </div>
     </div>
</div>
<script>
	$(document).ready(function() {
		$('#applyBtn').on('click', function() {
			let division = $('#division').val();
			let subject = $('#subject').val().trim();
			let description = $('#description').val().trim();
			
			if (!subject) {
				alert("제목을 입력해주세요");
				return;
			}
			
			if (!description) {
				alert("내용을 입력해주세요");
				return;
			}
			
			let formData = new FormData();
			formData.append("division", division);
			formData.append("subject", subject);
			formData.append("description", description);
			
			$.ajax({
				type : 'POST',
				url : "/enquiry/create",
				data : formData,
				processData: false,
	            contentType: false,
				success : function(data) {
					if (data.code == 200) {
						alert("작성이 완료되었습니다");
						location.href = "/enquiry/enquiry-list-view";
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