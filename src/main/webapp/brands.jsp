<jsp:include page="header.jsp" />

<%@page import="com.caltech.sportyshoes.pojo.*"%>
<%@page import="java.util.*"%>

<div class="container">
	<h1>
		<i>List of Brands</i>
	</h1>
	<form action="addBrand">
		<input type="submit" value="Add Brand">
	</form>

	<table border=1>
		<tr>
			<th>Brand id</th>
			<th>Brand name</th>

			<th>Edit Action</th>
			<th>Delete Action</th>
		</tr>
		<%
		List<Brand> list = (List<Brand>) request.getAttribute("list");
		for (Brand brand : list) {
		%>
		<tr>
			<td><%=brand.getId()%></td>
			<td><%=brand.getName()%></td>

			<td><a href="/brand/edit?id=<%=brand.getId()%>">Edit</a></td>
			<td><a href="/brand/delete?id=<%=brand.getId()%>">Delete</a></td>
		</tr>
		<%
		}
		%>
	</table>
</div>

</body>
</html>