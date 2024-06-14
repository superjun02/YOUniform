<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="d-flex flex-wrap">
	<c:forEach items="${uniformList}" var="uniform">
		<a href="/uniform/detail-view?id=${uniform.id}" class="itemLink">
			<div class="uniformBox">
				<div>
					<div class="imageBox">
						<img alt="" src="${uniform.image}" width="110px" height="140px">
					</div>
					<div class="subjectText">${uniform.subject}</div>
					<div class="priceText">
						<fmt:formatNumber value="${uniform.price}" type="currency" />
					</div>
				</div>
			</div>
		</a>
	</c:forEach>
</div>

<div class="pt-4">
	<c:if test="${empty league}">
		<nav aria-label="Page navigation" class>
			<ul class="pagination justify-content-center">
				<c:if test="${pageInfo.hasPreviousPage}">
					<li class="page-item"><a class="page-link"
						href="/home/home-view?page=${currentPage - 1}"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
				</c:if>
				<c:forEach begin="1" end="${totalPages}" var="i">
					<li class="page-item <c:if test='${currentPage == i}'>active</c:if>">
						<a class="page-link"
						href="/home/home-view?page=${i}">${i}</a>
					</li>
				</c:forEach>
				<c:if test="${pageInfo.hasNextPage}">
					<li class="page-item"><a class="page-link"
						href="/home/home-view?page=${currentPage + 1}"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
			</ul>
		</nav>
	</c:if>
	<c:if test="${not empty league}">
		<nav aria-label="Page navigation" class>
			<ul class="pagination justify-content-center">
				<c:if test="${pageInfo.hasPreviousPage}">
					<li class="page-item"><a class="page-link"
						href="/uniform/sort-list-view?league=${league}&page=${currentPage - 1}"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
				</c:if>
				<c:forEach begin="1" end="${totalPages}" var="i">
					<li class="page-item <c:if test='${currentPage == i}'>active</c:if>">
						<a class="page-link"
						href="/uniform/sort-list-view?league=${league}&page=${i}">${i}</a>
					</li>
				</c:forEach>
				<c:if test="${pageInfo.hasNextPage}">
					<li class="page-item"><a class="page-link"
						href="/uniform/sort-list-view?league=${league}&page=${currentPage + 1}"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
			</ul>
		</nav>
	</c:if>
</div>