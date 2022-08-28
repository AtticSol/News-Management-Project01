<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="body-title">
	<a href="">News >> </a> Add List
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
					<td class="space_around_title_tex">News Title</td>
					<td class="space_around_view_text"><input type="text"
						class="text" name="title" placeholder="News title" value="" /></td>
				</tr>
			</c:if>


			<c:if test="${requestScope.presentation eq 'editNews' }">
				<tr>
					<td class="space_around_title_tex">News Title</td>
					<td class="space_around_view_text"><input type="text"
						class="text" name="title" value="${news.title}" /></td>
				</tr>
			</c:if>


			<c:if test="${requestScope.presentation eq 'editNews' }">
				<tr>
					<td class="space_around_title_tex">News Date</td>
					<td class="space_around_view_text"><input type="date"
						name="date" value="" /></td>
				</tr>
			</c:if>


			<c:if test="${requestScope.presentation eq 'addNews' }">
				<tr>
					<td class="space_around_title_tex">Brief</td>
					<td class="space_around_view_text"><textarea name="brief"
							cols="58" rows="3" placeholder="Text text text..."></textarea></td>
				</tr>
			</c:if>


			<c:if test="${requestScope.presentation eq 'editNews' }">
				<tr>
					<td class="space_around_title_tex">Brief</td>
					<td class="space_around_view_text"><textarea name="brief"
							cols="58" rows="3">${news.briefNews}</textarea></td>
				</tr>
			</c:if>

			<c:if test="${requestScope.presentation eq 'addNews' }">
				<tr>
					<td class="space_around_title_tex">Content</td>
					<td class="space_around_view_text"><textarea name="content"
							cols="58" rows="5" placeholder="Text text text..."></textarea></td>
				</tr>
			</c:if>
			<c:if test="${requestScope.presentation eq 'editNews' }">
				<tr>
					<td class="space_around_title_tex">Content</td>
					<td class="space_around_view_text"><textarea name="content"
							cols="58" rows="5">${news.content}</textarea></td>
				</tr>
			</c:if>

		</table>

		<div class="first-view-button">
			<c:if test="${requestScope.presentation eq 'addNews' }">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="do_add_news" /> <input
						type="submit" value="SAVE" />
				</form>
			</c:if>
			<c:if test="${requestScope.presentation eq 'editNews' }">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="do_edit_news" /> <input
						type="hidden" name="idNews" value="${requestScope.news.idNews}" /> <input
						type="submit" value="SAVE" />
				</form>
			</c:if>
		</div>

		<div class="second-view-button">
			<form action="controller" method="post">
				<input type="hidden" name="command" value="do_cancel" /> 
				<input type="hidden" name="idNews" value="${requestScope.news.idNews}" /> 
				<input type="hidden" name="previousPresentation" value="${requestScope.previousPresentation}" />
				<input type="hidden" name="page_number" value="${requestScope.page_number}" />  
				<input type="submit" value="CANCEL" />
			</form>
		</div>

	</div>
</form>