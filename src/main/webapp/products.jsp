<jsp:include page="header.jsp" />

<%@page import="com.caltech.sportyshoes.pojo.*"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@page import="java.util.*"%>

<div class="container">
	<h1>
		<i>List of Products</i>
	</h1>
	<sec:authorize access="hasAuthority('ADMIN')">
		<form action="addProduct">
			<input type="submit" value="Add product">
		</form>
	</sec:authorize>

	<table border=1 cellspacing=2 cellpadding=4>
		<tr>
			<th>Shoe id</th>
			<th>Shoe name</th>
			<th>Brand</th>
			<th>Cost</th>
			<sec:authorize access="hasAuthority('ADMIN')">
				<th>Edit Action</th>
				<th>Delete Action</th>
			</sec:authorize>
		</tr>
		<%
		List<Product> list = (List<Product>) request.getAttribute("list");
		for (Product product : list) {
		%>
		<tr>
			<td><%=product.getId()%></td>
			<td><%=product.getName()%></td>
			<td><%=product.getBrand().getName()%></td>
			<td><%=product.getCost()%></td>
			<sec:authorize access="hasAuthority('ADMIN')">
				<td><a href="/product/edit?id=<%=product.getId()%>">Edit</a></td>
				<td><a href="/product/delete?id=<%=product.getId()%>">Delete</a></td>
			</sec:authorize>
			<sec:authorize access="hasAuthority('CUSTOMER')">
				<td><a href="/order/buy?id=<%=product.getId()%>">Buy</a></td>
			</sec:authorize>
		</tr>
		<%
		}
		%>
	</table>
</div>

</body>
</html>