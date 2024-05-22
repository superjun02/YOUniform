<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${loginId eq 'admin'}">
	<ul class="nav flex-column fixed_box">
		<li class="nav-item side-nav"><a class="nav-link text-white" href="#">주문내역</a></li>
		<li class="nav-item side-nav"><a class="nav-link text-white" href="#">문의내역</a></li>
		<li class="nav-item side-nav"><a class="nav-link text-white" href="#">신청내역</a></li>
		<li class="nav-item side-nav"><a class="nav-link text-white" href="#">판매하기</a></li>
	</ul>
</c:if>
<c:if test="${loginId ne 'admin'}">
	<ul class="nav flex-column fixed_box">
		<li class="nav-item side-nav"><a class="nav-link text-white" href="/basket/basket-view">장바구니</a></li>
		<li class="nav-item side-nav"><a class="nav-link text-white" href="/order/order-list-view">주문내역</a></li>
		<li class="nav-item side-nav"><a class="nav-link text-white" href="#">문의하기</a></li>
		<li class="nav-item side-nav"><a class="nav-link text-white" href="#">문의내역</a></li>
		<li class="nav-item side-nav"><a class="nav-link text-white" href="#">판매신청</a></li>
	</ul>
</c:if>