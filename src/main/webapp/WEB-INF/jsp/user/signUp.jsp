<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center mt-4">
	<div class="col-5">
		<div class="d-flex justify-content-center text-success">
			<h1>YOUniform</h1>
		</div>
		<div class="pt-4">
			<span>이름</span> 
			<input type="text" id="name" name="name" class="form-control">
		</div>
		<div class="pt-2">
			<div class="d-flex justify-content-between">
				<span>아이디</span> 
				<button id="loginIdChkBtn" class="btn btn-sm btn-success">중복확인</button>
			</div>
			<input type="text" id="loginId" name="loginId" class="form-control">
			<div id="idCheckLength" class="small text-danger d-none">ID를 4자 이상 입력해주세요.</div>
			<div id="idCheckDuplicated" class="small text-danger d-none">이미 사용중인 ID입니다.</div>
			<div id="idCheckOk" class="small text-success d-none">사용 가능한 ID 입니다.</div>
		</div>
		<div class="pt-2">
			<span>비밀번호</span> 
			<input type="password" id="password" name="password" class="form-control">
		</div>
		<div class="pt-2">
			<span>비밀번호 확인</span> 
			<input type="password" id="passwordChk" name="passwordChk" class="form-control">
		</div>
		<div class="pt-2">
			<span>이메일</span> 
			<input type="text" id="email" name="email" class="form-control">
		</div>
		<div class="pt-3">
			<input type="button" id="signUpBtn" value="회원가입" class="btn btn-success col-12">
		</div>
	</div>
</div>
<script>
	$(document).ready(function() {
		var usableId = null;
		
		$('#loginIdChkBtn').on('click', function() {
			$('#idCheckLength').addClass("d-none");
			$('#idCheckDuplicated').addClass("d-none");
			$('#idCheckOk').addClass("d-none");
			
			let loginId = $("input[name=loginId]").val().trim();
			if (loginId.length < 4) {
				$('#idCheckLength').removeClass("d-none");
				return;
			}
			
			$.get("/user/is-duplicated-id", {"loginId" : loginId})
			.done(function(data) {
				if (data.code == 200) {
					if (data.is_duplicated_id) {
						$('#idCheckDuplicated').removeClass("d-none");
					} else {
						$('#idCheckOk').removeClass("d-none");
						usableId = loginId;
					}
				} else {
					alert(data.error_message);
				}
				
			});
		});
		
		$('#signUpBtn').on('click', function() {
			let loginId = $('#loginId').val().trim();
			let password = $('#password').val().trim();
			let passwordChk = $('#passwordChk').val().trim();
			let name = $('#name').val().trim();
			let email = $('#email').val().trim();
			
			if (!name) {
				alert("이름을 입력하세요");
				return;
			}

			if (!loginId) {
				alert("아이디를 입력하세요");
				return;
			}
			
			if (!password || !passwordChk) {
				alert("비밀번호를 입력하세요");
				return;
			}
			
			if (password != passwordChk) {
				alert("비밀번호가 일치하지 않습니다.")
				return;
			}
			
			if (!email) {
				alert("이메일을 입력하세요");
				return;
			}
			
			if ($('#idCheckOk').hasClass('d-none') || usableId != loginId) {
				alert("중복확인을 진행해주세요");
				return;
			}
			
			$.post("/user/sign-up", {"loginId":loginId, "name":name, "password":password, "email":email})
			.done(function(data) {
				if (data.code == 200) {
					alert("가입을 환영합니다. 로그인 해주세요.")
					location.href = "/user/sign-in-view";
				} else {
					alert(data.error_message);
				}
			});
		});
	});
</script>