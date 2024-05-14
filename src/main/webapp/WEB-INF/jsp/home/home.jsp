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
