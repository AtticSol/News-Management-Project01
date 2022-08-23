<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="body-title">
	<a href="">News >> </a> Add List
</div>



<form action="controller" method="post">

	<input type="hidden" name="command" value="add_news" />
	<div class="add-table-margin">
		<table class="news_text_format">
			<tr>
				<td class="space_around_title_tex">News Title</td>
				<td class="space_around_view_text">
					<input type="text" class="text" name="title" placeholder="News title" value="" />
				</td>
			</tr>
			<tr>
				<td class="space_around_title_tex">News Date</td>
				<td class="space_around_view_text">
					<input type="text" class="text" name="news_date" placeholder="11/25/2005" value="" />
				</td>
			</tr>
			<tr>
				<td class="space_around_title_tex">Brief</td>
				<td class="space_around_view_text">
					<textarea name="brief" cols="58" rows="3" placeholder="Text text text..."></textarea>
				</td>
			</tr>

			<tr>
				<td class="space_around_title_tex">Content</td>
				<td class="space_around_view_text">
					<textarea name="content" cols="58" rows="5" placeholder="Text text text..."></textarea>
				</td>
			</tr>
		</table>

		<div class="first-view-button">
			<form action="controller" method="post">
				<input type="hidden" name="command" value="add_news" /> <input
					type="hidden" name="id" value="${news.idNews}" /> <input
					type="submit" value="SAVE" />
			</form>
		</div>

		<div class="second-view-button">
			<form action="controller" method="post">
				<input type="hidden" name="command" value="cancel" /> <input
					type="hidden" name="id" value="${news.idNews}" /> <input
					type="submit" value="CANCEL" />
			</form>
		</div>

	</div>
</form>