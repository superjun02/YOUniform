<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center mt-1">
	<div class="col-5">
		<div class="d-flex justify-content-center text-success">
			<h1>YOUniform</h1>
		</div>
		<form id="signInForm" method="post" action="/user/sign-in">
			<div class="pt-4">
				<label for="loginId">아이디</label> 
				<input type="text" id="loginId" name="loginId" class="form-control">
			</div>
			<div class="pt-2">
				<label for="password">비밀번호</label> 
				<input type="password" id="password" name="password" class="form-control">
			</div>
			<div class="pt-3">
				<input type="submit" value="로그인" class="btn btn-success col-12">
			</div>
			<div class="pt-2">
				<a href="/user/sign-up-view" class="btn btn-secondary col-12">회원가입</a>
			</div>
		</form>
	</div>
</div>
<script>
	$(document).ready(function() {
		$('form').on('submit', function(e) {
			e.preventDefault();
			
			let loginId = $('#loginId').val().trim();
			let password = $('#password').val().trim();
			
			if (!loginId) {
				alert("아이디를 입력해주세요");
				return false;
			}
			
			if (!password) {
				alert("패스워드를 입력해주세요")
				return false;
			}
			
			let url = $(this).attr('action');
			let params = $(this).serialize();
			
			$.post(url, params)
			.done(function(data) {
				if (data.result == "성공") {
					location.href = "/home/home-view";
				} else {
					alert(data.error_message);
				}
			});
		});
	});
</script>