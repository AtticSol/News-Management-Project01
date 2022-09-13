<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.menu.href.newslist" var="newsListHref"/>
<fmt:message bundle="${loc}" key="local.news.viewnews" var="viewNews"/>
<fmt:message bundle="${loc}" key="local.news.newstitle" var="newsTitle"/>
<fmt:message bundle="${loc}" key="local.news.newsdate" var="newsDate"/>
<fmt:message bundle="${loc}" key="local.news.newsbrief" var="newsBrief"/>
<fmt:message bundle="${loc}" key="local.news.newscontent" var="content"/>
<fmt:message bundle="${loc}" key="local.news.button.edit" var="edit"/>
<fmt:message bundle="${loc}" key="local.news.button.delete" var="delete"/>



<div class="body-title">
	<a href="">${newsListHref} >> </a> ${viewNews}
</div>

<div class="add-table-margin">
	<table class="news_text_format">
		<tr>
			<td class="space_around_title_text">${newsTitle}</td>

			<td class="space_around_view_text">
				<div class="word-breaker">
					<c:out value="${requestScope.news.title}" />
				</div>
			</td>
		</tr>
		<tr>
			<td class="space_around_title_text">${newsDate}</td>

			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${requestScope.news.newsDate}" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text">${newsBrief}</td>
			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${requestScope.news.briefNews}" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text">${content}</td>
			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${requestScope.news.content}" />
				</div></td>
		</tr>
	</table>
</div>


<c:if test="${not (sessionScope.role eq 'user')}">
	<div class="first-view-button">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="go_to_edit_news" />
			<input type="hidden" name="newsId" value="${requestScope.news.newsID}" />
			<input type="hidden" name="previousPresentation" value="${requestScope.presentation}" />
			<input type="submit" value="${edit}" />
		</form>
	</div>

	<div class="second-view-button">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="do_delete_news" />
			<input type="hidden" name="newsId" value="${requestScope.news.newsID}" />
			<input type="submit" value="${delete}" />
		</form>
	</div>
</c:if>