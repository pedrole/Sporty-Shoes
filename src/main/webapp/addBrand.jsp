<jsp:include page="header.jsp" />
<%@page import="com.caltech.sportyshoes.pojo.Brand"%>
<%@page import="com.caltech.sportyshoes.pojo.Product"%>

<%@page import="com.caltech.sportyshoes.dao.*"%>
<%@page import="com.caltech.sportyshoes.pojo.*"%>
<%@page import="java.util.*"%>
<div class="container">


	<h1>
		<i>Record of Brand</i>
	</h1>
	<form action="/brand/addBrand" method="post">
		<label>Name</label><br>
		<input type="text" name="name"><br> <input type="submit"
			value="add">
	</form>
</div>
</body>
</html>
