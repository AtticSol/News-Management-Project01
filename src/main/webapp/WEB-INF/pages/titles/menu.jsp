<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="menu-wrapper">
	<div class="menu-title-wrapper">
		<div class="menu-title">News</div>
	</div>

	<div class="list-menu-invisible-wrapper">
		<div class="list-menu-wrapper" style="float: right;">
			<c:if test="${sessionScope.user eq 'active'}">
				<ul style="list-style-image: url(images/img.jpg); text-align: left;">
					<li style="padding-left: 15px;"><a
						href="controller?command=go_to_news_list">News List</a><br /></li>

					<li style="padding-left: 15px;"><a href="">Add News</a><br />
					</li>
				</ul>
			</c:if>
		</div>
		<div class="clear"></div>
	</div>
	<!--  grey free space at the bottom of menu -->
	<div style="height: 25px;"></div>
</div>
