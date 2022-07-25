<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div id="form">
	<h1>Register here</h1><br />
		
	<form action="controller" method="post">

		<input type="hidden" name="command" value="do_registration"/>
		<div class="inline_blocks">
			<div class="block1"><div class="title">Name:</div></div>
			<div class="block2"><input type="text" class="text" name="firstName" placeholder="Input your first name" value="" /></div>
		</div>
		<div class="inline_blocks">
			<div class="block1"><div class="title">Username:</div></div>
			<div class="block2"><input type="text" class="text" name="login" placeholder="Input your username" value="" /></div>
		</div>
		<div class="inline_blocks">
			<div class="block1"><div class="title">Password:</div></div>
			<div class="block2"><input type="password" class="text" name="password" placeholder="Input your password" value="" /></div>
		</div>
		<div class="inline_blocks">
			<div class="block1"><div class="title">Confirm password:</div></div>
			<div class="block2"><input type="password" class="text" name="confirm_password" placeholder="Confirm your password" value="" /></div>
		</div>
		<div class="inline_blocks">
			<div class="block1"><div class="title">Email:</div></div>
			<div class="block2"><input type="text" class="text" name="email" placeholder="Input your email" value="" /></div>
		</div>
		
			<c:if test="${not (requestScope.RegistrationError eq null)}">
				<font color="red">
					<c:out value="${requestScope.RegistrationError}" />
				</font>
			</c:if>

		<div class="button">
    		<input type="submit"  value="SIGN UP" />
    	</div>
	</form>
</div>