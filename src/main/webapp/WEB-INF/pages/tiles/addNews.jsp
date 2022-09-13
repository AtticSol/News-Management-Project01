<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.menu.href.newslist" var="newsListHref"/>
<fmt:message bundle="${loc}" key="local.news.add" var="add"/>
<fmt:message bundle="${loc}" key="local.news.newstitle" var="newsTitle"/>
<fmt:message bundle="${loc}" key="local.news.newstitle.placeholder" var="titlePlaceholder"/>
<fmt:message bundle="${loc}" key="local.news.newsdate" var="newsDate"/>
<fmt:message bundle="${loc}" key="local.news.newsbrief" var="newsBrief"/>
<fmt:message bundle="${loc}" key="local.news.newsbrief.placeholder" var="briefPlaceholder"/>
<fmt:message bundle="${loc}" key="local.news.newscontent" var="content"/>
<fmt:message bundle="${loc}" key="local.news.newscontent.placeholder" var="contentPlaceholder"/>
<fmt:message bundle="${loc}" key="local.news.button.save" var="save"/>
<fmt:message bundle="${loc}" key="local.news.button.cancel" var="cancel"/>
<fmt:message bundle="${loc}" key="local.news.add.error.title" var="titleError"/>
<fmt:message bundle="${loc}" key="local.news.add.error.date" var="dateError"/>
<fmt:message bundle="${loc}" key="local.news.add.error.brief" var="briefError"/>
<fmt:message bundle="${loc}" key="local.news.add.error.content" var="contentError"/>

<style>
.redWarning{
	font-size: 70%;
	color: red;
}
</style>

<div class="body-title">
	<a href="">${newsListHref} >> </a> ${add}
</div>

 

<form action="controller" method="post">

	<c:if test="${requestScope.presentation eq 'addNews' }">
		<input type="hidden" name="command" value="do_add_news" />
	</c:if>

	<c:if test="${requestScope.presentation eq 'editNews' }">
		<input type="hidden" name="command" value="do_edit_news" />
	</c:if>


	<div class="add-table-margin">
		<table class="news_text_format">

			<c:if test="${requestScope.presentation eq 'addNews' }">
				<tr>
					<td class="space_around_title_tex">${newsTitle}</td>
					<td class="space_around_view_text">
						<input type="text" class="text" name="title" placeholder="${titlePlaceholder}" value="" />
					</td>
				</tr>
			</c:if>


			<c:if test="${requestScope.presentation eq 'editNews' }">
				<tr>
					<td class="space_around_title_tex">${newsTitle}</td>
					<td class="space_around_view_text">
						<input type="text" class="text" name="title" value="${news.title}" />
					</td>
				</tr>
			</c:if>
			
			<c:if test="${not (param.titleError eq null)}">
				<font class="redWarning">
					${titleError} <br />
				</font>
			</c:if>


			<c:if test="${requestScope.presentation eq 'editNews' }">
				<tr>
					<td class="space_around_title_tex">${newsDate}</td>
					<td class="space_around_view_text">
						<input type="date" name="date" value="" />
					</td>
				</tr>
			</c:if>
			
			<c:if test="${not (param.dateError eq null)}">
				<font class="redWarning">
					${dateError} <br />
				</font>
			</c:if>

			<c:if test="${requestScope.presentation eq 'addNews' }">
				<tr>
					<td class="space_around_title_tex">${newsBrief}</td>
					<td class="space_around_view_text">
						<textarea name="brief" cols="58" rows="3" placeholder="${briefPlaceholder}"></textarea>
					</td>
				</tr>
			</c:if>
			
			<c:if test="${requestScope.presentation eq 'editNews' }">
				<tr>
					<td class="space_around_title_tex">${newsBrief}</td>
					<td class="space_around_view_text">
						<textarea name="brief" cols="58" rows="3">${news.briefNews}</textarea>
					</td>
				</tr>
			</c:if>
			
			<c:if test="${not (param.briefError eq null)}">
				<font class="redWarning">
					${briefError} <br />
				</font>
			</c:if>

			<c:if test="${requestScope.presentation eq 'addNews' }">
				<tr>
					<td class="space_around_title_tex">${content}</td>
					<td class="space_around_view_text">
						<textarea name="content" cols="58" rows="5" placeholder="${contentPlaceholder}"></textarea>
					</td>
				</tr>
			</c:if>
			<c:if test="${requestScope.presentation eq 'editNews' }">
				<tr>
					<td class="space_around_title_tex">${content}</td>
					<td class="space_around_view_text">
						<textarea name="content" cols="58" rows="5">${news.content}</textarea>
					</td>
				</tr>
			</c:if>
			
			<c:if test="${not (param.contentError eq null)}">
				<font class="redWarning">
					${contentError} <br />
				</font>
			</c:if>

		</table>

		<div class="first-view-button">
			<c:if test="${requestScope.presentation eq 'addNews' }">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="do_add_news" />
					<input type="submit" value="${save}" />
				</form>
			</c:if>
			<c:if test="${requestScope.presentation eq 'editNews'}">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="do_edit_news" />
					<input type="hidden" name="newsId" value="${requestScope.news.newsID}" />
					<input type="submit" value="${save}" />
				</form>
			</c:if>
		</div>

		<div class="second-view-button">
			<form action="controller" method="post">
				<input type="hidden" name="command" value="do_cancel" />
				<input type="hidden" name="newsId" value="${requestScope.news.newsID}" />
				<input type="hidden" name="previousPresentation" value="${requestScope.previousPresentation}" />
				<input type="hidden" name="pageNumber" value="${requestScope.pageNumber}" />
				<input type="submit" value="${cancel}" />
			</form>
		</div>

	</div>
</form>