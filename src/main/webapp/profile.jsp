<jsp:include page="header.jsp" />
<%@page import="com.caltech.sportyshoes.pojo.User"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>



<%@page import="java.util.*"%>
<div class="container">



	<h1>
		<i>Profile</i>
	</h1>
	<form action="/user/changePassword" method="post">
		<input name="id" type="hidden" value="1"> <label>Name</label><br>
		<input type="text" name="name" disabled="disabled"
		value="<security:authentication property="principal.username" />"> <br>
		<label>Role</label><br>
			<input type="text" name="role" disabled="disabled"
		value="<security:authentication property="principal.user.role" />"> <br>
		<label>Password</label><br>
			<input type="password"" required name="password" > <br>
			<label>New Password</label><br>
			<input type="password"" required name="newPassword" > <br>
			<label>Confirm Password</label><br>
			<input type="password" required name="confirmPassword" > <br>
		<input type="submit" value="Change Password">
	</form>
</div>
</body>
</html>
