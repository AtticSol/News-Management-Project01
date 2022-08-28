<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="body-title">
	<a href="">News >> </a> News List
</div>

<form action="controller" method="post">
	&nbsp;&nbsp;Page:
	<c:forEach var="page" items="${requestScope.page}">
		<a href="controller?command=go_to_news_list&page_number=${page}">${page}</a>
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
						<a href="controller?command=go_to_view_news&idNews=${news.idNews}">view</a>&nbsp;

						<a href="controller?command=go_to_edit_news&idNews=${news.idNews}&previousPresentation=${requestScope.presentation}&page_number=${page_number}">edit</a>

						&nbsp;
						<c:if test="${sessionScope.role eq 'admin'}">
							<input type="checkbox" name="idNews" value="${news.idNews }" />
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>

	<c:if test="${sessionScope.role eq 'admin'}">
		<div class="delete-button-position">
			<input type="hidden" name="command" value="do_delete_news" />
			<input type="submit" value="DELETE" />
		</div>
	</c:if>



	<div class="no-news">
		<c:if test="${requestScope.news eq null}">
        No news.
	</c:if>
	</div>
</form>


