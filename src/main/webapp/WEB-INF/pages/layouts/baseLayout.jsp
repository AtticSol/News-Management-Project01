<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="script/validation.js"></script>
<title>News Management 02</title>
<link rel="stylesheet" type="text/css" href="styles/newsStyle.css">

</head>
<body>
	<div class="page">
	
		<div class="header">
			<c:import url="/WEB-INF/pages/titles/header.jsp" />
		</div>
		
		<div class="base-layout-wrapper">
			<div class="menu">
				<c:if test="${not (sessionScope.user eq  'active')}">
					Welcome!!!
					<%-- <c:import url=""></c:import> --%>
				</c:if>
				<c:if test="${sessionScope.user eq 'active'}">
					<c:import url="/WEB-INF/pages/titles/menu.jsp" />
				</c:if>
			</div>
	
			<div class="content">
				<c:if test="${not (sessionScope.user eq 'active')}">
					<c:import url="/WEB-INF/pages/titles/guestInfo.jsp" />
				</c:if>
				<c:if test="${sessionScope.user eq 'active'}">
					<c:import url="/WEB-INF/pages/titles/body.jsp" />
				</c:if>
			
			</div>
		
		</div>		
		
		<div class="footer">
			<c:import url="/WEB-INF/pages/titles/footer.jsp" />
		</div>
		
	</div>
</body>
</html>