<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.registration.title" var="registrationTitle"/>
<fmt:message bundle="${loc}" key="local.registration.name" var="name"/>
<fmt:message bundle="${loc}" key="local.registration.name.placeholder" var="namePlaceholder"/>
<fmt:message bundle="${loc}" key="local.registration.username" var="username"/>
<fmt:message bundle="${loc}" key="local.registration.username.placeholder" var="usernamePlaceholder"/>
<fmt:message bundle="${loc}" key="local.registration.password" var="password"/>
<fmt:message bundle="${loc}" key="local.registration.password.placeholder" var="passwordPlaceholder"/>
<fmt:message bundle="${loc}" key="local.registration.confirmpassword" var="confirmPassword"/>
<fmt:message bundle="${loc}" key="local.registration.confirmpassword.placeholder" var="confirmPasswordPlaceholder"/>
<fmt:message bundle="${loc}" key="local.registration.email" var="email"/>
<fmt:message bundle="${loc}" key="local.registration.email.placeholder" var="emailPlaceholder"/>
<fmt:message bundle="${loc}" key="local.registration.sign.up" var="signup"/>
<fmt:message bundle="${loc}" key="local.message.error.registration" var="registrationError"/>
<fmt:message bundle="${loc}" key="local.registration.error.login.exists" var="loginExists"/>
<fmt:message bundle="${loc}" key="local.registration.error.login.length" var="loginLength"/>
<fmt:message bundle="${loc}" key="local.registration.error.password.creation" var="passwordCreateError"/>
<fmt:message bundle="${loc}" key="local.registration.error.password.confirmation" var="passwordConfirmError"/>
<fmt:message bundle="${loc}" key="local.registration.error.email.exists" var="emailExists"/>
<fmt:message bundle="${loc}" key="local.registration.error.email.incorrect" var="emailIncorrect"/>
<fmt:message bundle="${loc}" key="local.registration.button.main" var="backToMain"/>



<style>
.redWarning{
	font-size: 70%;
	color: red;
}
</style>

<div id="form">
	<h1>${registrationTitle}</h1><br />
		
	<form action="controller" method="get">
		<input type="hidden" name="command" value="do_registration"/>

		<c:if test="${not (param.registrationError eq null)}">
			<font class="redWarning">
				${registrationError}
			</font>
		</c:if>
		
		<div class="inline_blocks">
			<div class="block1"><div class="title">${name}:</div></div>
			<div class="block2"><input type="text" class="text" name="name" placeholder="${namePlaceholder}" value="" /></div>
		</div>
		<div class="inline_blocks">
			<div class="block1"><div class="title">${username}:</div></div>
			<div class="block2"><input type="text" class="text" name="login" placeholder="${usernamePlaceholder}" value="" /></div>
		</div>
		
			<c:if test="${not (param.loginExists eq null)}">
				<font class="redWarning">
					${loginExists}
				</font>
			</c:if>
			<c:if test="${not (param.loginLength eq null)}">
				<font class="redWarning">
					${loginLength}
				</font>
			</c:if>

		<div class="inline_blocks">
			<div class="block1"><div class="title">${password}:</div></div>
			<div class="block2"><input type="password" class="text" name="password" placeholder="${passwordPlaceholder}" value="" /></div>
		</div>
		
		<c:if test="${not (param.passwordCreateError eq null)}">
			<font class="redWarning">
				${passwordCreateError}
			</font>
		</c:if>
		
		<div class="inline_blocks">
			<div class="block1"><div class="title">${confirmPassword}:</div></div>
			<div class="block2"><input type="password" class="text" name="confirmPassword" placeholder="${confirmPasswordPlaceholder}" value="" /></div>
		</div>
		<br />
		

		<c:if test="${not (param.passwordConfirmError eq null)}">
			<font class="redWarning">
				${passwordConfirmError}
			</font>
		</c:if>
		
		<div class="inline_blocks">
			<div class="block1"><div class="title">${email}:</div></div>
			<div class="block2"><input type="text" class="text" name="email" placeholder="${emailPlaceholder}" value="" /></div>
		</div>
		
		<c:if test="${not (param.emailExists eq null)}">
			<font class="redWarning">
				${emailExists}
			</font>
		</c:if>
		<c:if test="${not (param.emailIncorrect eq null)}">
			<font class="redWarning">
				${emailIncorrect}
			</font>
		</c:if>
		


		<div class="button">
    		<input type="submit"  value="${signup}" />
    	</div>
	</form>
	
	<form action="controller" method="post">
		<input type="hidden" name="command" value="go_to_base_page" />
		<input type="hidden" name="userStatus" value="not_active" />
		<div class="button">
			<input type="submit" value="${backToMain}" />
		</div>
	</form>
</div>