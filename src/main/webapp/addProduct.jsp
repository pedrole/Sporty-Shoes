<jsp:include page="header.jsp" />
<%@page import="com.caltech.sportyshoes.pojo.Brand"%>
<%@page import="com.caltech.sportyshoes.pojo.Product"%>

<%@page import="com.caltech.sportyshoes.dao.*"%>
<%@page import="com.caltech.sportyshoes.pojo.*"%>
<%@page import="java.util.*"%>
<div class = "container">

	<%
	List<Brand> brands = (List<Brand>) request.getAttribute("brands");
	%>
	<h1>
		<i>Record of Product</i>
	</h1>
	<form action="/product/addProduct" method="post">
		<label>Name</label><br><input type="text" name="name"><br> <label>Select
			Brand:</label><br> <select name="brand">
			<%
			for (Brand b : brands) {
			%><option value="<%=b.getId()%>">
				<%
				out.println(b.getName());
				%>
			</option>
			<%
			}
			%>
		</select><br> <label>Cost</label><br><input type="number" step=".01" name="cost"><br>
		<input type="submit" value="add">
	</form>
	</div>
</body>
</html>
