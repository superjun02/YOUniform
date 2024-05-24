<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class=" w-100">
     <div class="form-row">
         <div class="form-group col-md-3">
             <label for="league">카테고리</label>
             <select id="league" class="form-control">
                 <option value="Kleague">K-league</option>
                 <option value="serieA">Serie A</option>
                 <option value="laliga">Laliga</option>
                 <option value="epl">EPL</option>
                 <option value="ligue1">Ligue 1</option>
                 <option value="bundesliga">Bundesliga</option>
             </select>
         </div>
         <div class="form-group col-md-6">
             <label for="subject">제목</label>
             <input type="text" id="subject" class="form-control" placeholder="예) 바르셀로나 13-14 #10 MESSI M">
         </div>
         <div class="form-group col-md-3">
             <label for="price">가격</label>
             <input type="text" id="price" class="form-control">
         </div>
     </div>
     <div class="form-group">
         <label for="description">내용</label>
         <textarea id="description" rows="5" class="form-control" placeholder="예) 13년도에 바르셀로나 공항에서 구매한 상품입니다. 사용감이 조금 있습니다."></textarea>
     </div>
     <div class="form-row d-flex justify-content-between">
         <div class="form-group col-md-6 d-flex">
			<div>
				<input id="file" type="file" class="d-none" accept=".jpg, .png, .gif"/>
				<button id="imgBtn"><img alt="이미지 삽입" src="/static/img/image_icon.png"></button>
			</div>
			<div>
				<div id="fileName" class="mt-2"></div>
			</div>
         </div>
         <div class="form-group col-md-2 d-flex align-items-end">
             <button id="applyBtn" class="btn btn-primary w-100">신청하기</button>
         </div>
     </div>
</div>
<script>
	$(document).ready(function() {
		$('#imgBtn').on('click', function() {
			let file = document.getElementById("file");
			file.click();
		});
		
		$('#applyBtn').on('click', function() {
			let league = $('#league').val();
			let subject = $('#subject').val();
			let price = $('#price').val();
			let description = $('#description').val();
			let fileName = $('#file').val();

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
			
			if (fileName) {
				let extension = fileName.split(".").pop().toLowerCase();
				if ($.inArray(extension, [ 'jpg', 'png', 'gif' ]) == -1) {
					alert("이미지 파일만 업로드 할 수 있습니다.");
					$('#file').val("");
					$('#fileName').text("");
					return;
				}
			}

			let formData = new FormData();
			formData.append("league", league);
			formData.append("subject", subject);
			formData.append("price", price);
			formData.append("description", description);
			formData.append("file", $('#file')[0].files[0]);

			$.ajax({
				type : 'POST',
				url : "/uniform/create",
				data : formData,
				enctype : "multipart/form-data",
				processData : false,
				contentType : false,
				success : function(data) {
					if (data.code == 200) {
						alert("신청이 완료되었습니다 관리자의 검토 후 승인하도록 하겠습니다");
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
		
		let target = document.getElementById('file');
		target.addEventListener('change', function() {

			if (target.value.length) {
				$('#fileName').text(target.files[0].name);
			} else {
				$('#fileName').text("");
			}
		});
	});
</script>