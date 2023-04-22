<%@page import="java.text.SimpleDateFormat"%>
<jsp:include page="header.jsp" />



<%@page import="com.caltech.sportyshoes.pojo.*"%>
<%@page import="java.util.*"%>
<div class = "container">
  	
	
	
	
	<h1>
		<i>List Users</i>
	</h1>
	<form action="/user/index"  autocomplete="off">
		 <label>Search Usernamer</label><br><input type="text"
	autocomplete="off" value="${username}"  name="usernameSearch"/><br>
		<input type="submit" value=Search>
	</form>
	
	
		<table border=1 cellspacing=2 cellpadding=4>
		<tr>
			<th>User ID</th>
			<th>Username</th>
			<th>Role</th>
		</tr>
		<%
		List<User> list = (List<User>) request.getAttribute("users");
		for (User user : list) {
		%>
		
		<tr>
			<td><%=user.getId()%></td>
			<td><%=user.getUsername()%></td>
			<td><%=user.getRole()%></td>
		</tr>
		<%
		}
		%>
	</table>
	</div>
	
		
</body>
</html>