<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.menu.href.newslist" var="newsListHref"/>
<fmt:message bundle="${loc}" key="local.menu.newslist" var="newslist"/>
<fmt:message bundle="${loc}" key="local.news.href.view" var="view"/>
<fmt:message bundle="${loc}" key="local.news.href.edit" var="edit"/>
<fmt:message bundle="${loc}" key="local.news.button.delete" var="delete"/>
<fmt:message bundle="${loc}" key="local.message.nonews" var="noNews"/>
<fmt:message bundle="${loc}" key="local.news.add.error.delete" var="noNewsToDelete"/>

<style>
.redWarning{
	font-size: 70%;
	color: red;
}
</style>

<div class="body-title">
	<a href="controller?command=go_to_news_list">${newsListHref} >> </a> ${newslist}
</div>

<form action="controller" method="post">
	&nbsp;&nbsp;Page:
	<c:forEach var="page" items="${requestScope.page}">
		<a href="controller?command=go_to_news_list&pageNumber=${page}">${page}</a>
	</c:forEach>

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
				<div class="news-link-to-wrapper">

					<div class="link-position">
						<a href="controller?command=go_to_view_news&newsId=${news.newsID}">${view}</a>&nbsp;

						<c:if test="${not (sessionScope.role eq  'user')}">
							<a href="controller?command=go_to_edit_news&newsId=${news.newsID}&previousPresentation=${requestScope.presentation}&pageNumber=${pageNumber}">${edit}</a>
						</c:if>
						
						&nbsp;
						<c:if test="${sessionScope.role eq 'admin'}">
							<input type="checkbox" name="newsId" value="${news.newsID}" />
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<br />
	</c:forEach>

	<c:if test="${sessionScope.role eq 'admin'}">
		<div class="delete-button-position">
			<c:if test="${not (param.noNewsToDelete eq null)}">
				<font class="redWarning">
					${noNewsToDelete}
				</font>
			</c:if>
	
			<input type="hidden" name="command" value="do_delete_news" />
			<input type="submit" value="${delete}" />
		</div>
	</c:if>



	<div class="no-news">
		<c:if test="${requestScope.news eq null}">
        ${noNews}
	</c:if>
	</div>
</form>


