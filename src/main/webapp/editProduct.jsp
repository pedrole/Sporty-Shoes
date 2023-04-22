<jsp:include page="header.jsp" />
<%@page import="com.caltech.sportyshoes.pojo.*"%>
<%@page import="java.util.*"%>
<div class="container">

	<%
	List<Brand> brands = (List<Brand>) request.getAttribute("brands");
	Product product = (Product) request.getAttribute("product");
	%>

	<h1>
		<i>Edit Product with id <%=product.getId() %></i>
	</h1>
	<form action="/product/edit" method="post">
	<input name="id" type="hidden" value="<%= product.getId() %>" >
		<label>Name</label><br> <input type="text"
			value="<%=product.getName()%>" name="name"><br> <label>Select
			Brand:</label><br> <select name="brand">
			<%
			for (Brand b : brands) {
			%><option value="<%=b.getId()%>"
				<%if (b.getId() == product.getBrand().getId()) {%> selected <%}%>>
				<%
				out.println(b.getName());
				%>
			</option>
			<%
			}
			%>
		</select><br> <label>Cost</label><br> <input type="number" step=".01"
			value="<%=product.getCost()%>" name="cost"><br> <input
			type="submit" value="Edit">
	</form>

</div>

</body>
</html>