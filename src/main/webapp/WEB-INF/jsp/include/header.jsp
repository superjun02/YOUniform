<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="d-flex justify-content-between align-items-center h-100">
	<%-- logo --%>
	<div class="pl-3 text-success col-3">
		<h1>YOUniform</h1>
	</div>
	<div>
		<div class="d-flex">
			<input class="form-control" type="search" placeholder="제품 입력">
   	 		<button id="searchBtn" class="btn btn-outline-success" type="button">search</button>
		</div>
	</div>
	
	<%-- login 정보 --%>
	<div class="pr-3 col-3 d-flex justify-content-end">
		<c:if test="${not empty userId}">
			<span>${name}님 안녕하세요. &nbsp;</span> 
			<a href="/user/sign-out">로그아웃</a>
		</c:if>
		<c:if test="${empty userId}">
			<a href="/user/sign-in-view">로그인</a>
		</c:if>
	</div>
</div>