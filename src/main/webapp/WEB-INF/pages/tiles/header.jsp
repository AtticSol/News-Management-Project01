<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<style>
.redWarning{
	font-size: 80%;
	color: red;
}
</style>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.header.newstitle" var="newstitle"/>
<fmt:message bundle="${loc}" key="local.header.login" var="login"/>
<fmt:message bundle="${loc}" key="local.header.password" var="password"/>
<fmt:message bundle="${loc}" key="local.header.href.registration" var="registration"/>
<fmt:message bundle="${loc}" key="local.header.locbutton.href.en" var="en_button" />
<fmt:message bundle="${loc}" key="local.header.locbutton.href.ru" var="ru_button" />
<fmt:message bundle="${loc}" key="local.header.button.sign.in" var="in"/>
<fmt:message bundle="${loc}" key="local.header.button.sign.out" var="out"/>
<fmt:message bundle="${loc}" key="local.message.error.session" var="sessionError"/>
<fmt:message bundle="${loc}" key="local.message.error.authentication" var="authenticationError"/>
<fmt:message bundle="${loc}" key="local.message.error.access" var="accessError"/>


<div class="wrapper">
	<div class="newstitle">
	
	${newstitle}
		
	</div>
	
	<div class="local-link"> 
	
		<div align="right"><br />				
			<a href="controller?command=do_change_local&local=en">${en_button}</a>&nbsp;&nbsp;
			<a href="controller?command=do_change_local&local=ru">${ru_button}</a> <br />
		</div>

		<c:if test="${not (sessionScope.userStatus eq 'active')}">
			<c:if test="${not (sessionScope.userStatus eq 'registration')}">
				<div align="right">
					<form action="controller" method="post">
						<input type="hidden" name="command" value="do_sign_in" />
						
							<c:if test="${not (requestScope.sessionError eq null)}">
								<font class="redWarning">
									${sessionError} <br />
								</font>
							</c:if>
						
							<c:if test="${not (param.authenticationError eq null)}">
								<font class="redWarning">
									${authenticationError} <br />
								</font>
							</c:if>
							
							<c:if test="${not (requestScope.accessError eq null)}">
								<font class="redWarning">
									${accessError} <br />
								</font>
							</c:if>
														
							${login}: <input type="text" name="login" value="" /><br />
							${password}: <input type="password" name="password" value="" /><br />
							
							<a href="controller?command=go_to_registration_page">${registration}</a>
							
						<input type="submit" value="${in}" /><br />
						
					</form>
				</div>
			</c:if>
		</c:if>
		
		<c:if test="${sessionScope.userStatus eq 'active'}">
			<div align="right">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="do_sign_out" />
					<input type="submit" value="${out}" /><br />
				</form>
			</div>
		</c:if>


	</div>
</div>