<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="w-50 d-flex justify-content-center">
	<div class="col-7">
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