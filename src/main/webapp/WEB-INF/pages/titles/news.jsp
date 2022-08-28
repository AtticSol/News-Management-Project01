<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${requestScope.presentation eq 'newsList' }">
	<c:import url="/WEB-INF/pages/titles/newsList.jsp" />
</c:if>

<c:if test="${requestScope.presentation eq 'addNews' }">
	<c:import url="/WEB-INF/pages/titles/addNews.jsp" />
</c:if>

<c:if test="${requestScope.presentation eq 'viewNews' }">
	<c:import url="/WEB-INF/pages/titles/viewNews.jsp" />
</c:if>

<c:if test="${requestScope.presentation eq 'editNews' }">
	<c:import url="/WEB-INF/pages/titles/addNews.jsp" />
</c:if>