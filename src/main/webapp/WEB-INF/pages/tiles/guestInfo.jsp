<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.menu.href.newslist" var="newsListHref"/>
<fmt:message bundle="${loc}" key="local.news.latest" var="latest"/>
<fmt:message bundle="${loc}" key="local.message.nonews" var="noNews"/>


<c:if test="${not (sessionScope.userStatus eq 'registration')}">
	<div class="body-title">
		<a href="">${newsListHref} >> </a> ${latest}
	</div>

<!-- 	<form action="command.do?method=delete" method="post"> -->
		<c:forEach var="news" items="${requestScope.news}">
			<div class="single-news-wrapper">
				<div class="single-news-header-wrapper">
					<div class="news-title">
						<c:out value="${news.title}" />
					</div>
					<div class="news-date">
						<c:out value="${news.newsDate}" />
					</div>
					<div class="news-content">
						<c:out value="${news.briefNews}" />
					</div>
				</div>
			</div>
		</c:forEach>
		
		<div class="no-news">
			<c:if test="${requestScope.news eq null}">
        		${noNews}
			</c:if>
		</div>
<!-- 	</form> -->
</c:if>

<c:if test="${sessionScope.userStatus eq 'registration'}">
	<c:import url="/WEB-INF/pages/tiles/registration.jsp" />
</c:if>