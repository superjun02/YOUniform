<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${loginId eq 'admin'}">
	<ul class="nav flex-column fixed_box">
		<li class="nav-item side-nav"><a class="nav-link text-white" href="/order/order-list-view">주문내역</a></li>
		<li class="nav-item side-nav"><a class="nav-link text-white" href="/enquiry/enquiry-list-view">문의내역</a></li>
		<li class="nav-item side-nav"><a class="nav-link text-white" href="/uniform/request-list-view">신청내역</a></li>
		<li class="nav-item side-nav"><a class="nav-link text-white" href="/uniform/request-view">판매하기</a></li>
	</ul>
</c:if>
<c:if test="${loginId ne 'admin'}">
	<ul class="nav flex-column fixed_box">
		<li class="nav-item side-nav"><a class="nav-link text-white" href="/basket/basket-view">장바구니</a></li>
		<li class="nav-item side-nav"><a class="nav-link text-white" href="/order/order-list-view">주문내역</a></li>
		<li class="nav-item side-nav"><a class="nav-link text-white" href="/enquiry/enquiry-create-view">문의하기</a></li>
		<li class="nav-item side-nav"><a class="nav-link text-white" href="/enquiry/enquiry-list-view">문의내역</a></li>
		<li class="nav-item side-nav"><a class="nav-link text-white" href="/uniform/request-view">판매신청</a></li>
	</ul>
</c:if>