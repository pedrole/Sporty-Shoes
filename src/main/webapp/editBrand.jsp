<jsp:include page="header.jsp" />
<%@page import="com.caltech.sportyshoes.pojo.Brand"%>
<%@page import="com.caltech.sportyshoes.pojo.Product"%>

<%@page import="com.caltech.sportyshoes.dao.*"%>
<%@page import="com.caltech.sportyshoes.pojo.*"%>
<%@page import="java.util.*"%>
<div class="container">


	<h1>
		<i>Edit Brand with id ${brand.getId()}</i>
	</h1>
	<form action="/brand/edit" method="post">
		<input name="id" type="hidden" value="${brand.getId()}"> <label>Name</label><br>
		<input type="text" name="name" value="${brand.getName()}"> <br>
		<input type="submit" value="Edit">
	</form>
</div>
</body>
</html>
