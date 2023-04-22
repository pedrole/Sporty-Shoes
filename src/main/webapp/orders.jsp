<%@page import="java.text.SimpleDateFormat"%>
<jsp:include page="header.jsp" />



<%@page import="com.caltech.sportyshoes.pojo.*"%>
<%@page import="java.util.*"%>
<div class = "container">
  	
	<%
			int selectedBrand = (Integer) request.getAttribute("selectedBrand");
	List<Brand> brands = (List<Brand>) request.getAttribute("brands");
	%>
	
	
	<h1>
		<i>List Orders</i>
	</h1>
	<form action="/order/search" method="post">
		 <label>Select
			Brand:</label><br> <select name="brand">
			<%
			for (Brand b : brands) {
			%><option value="<%=b.getId()%>"  <%if(b.getId()==selectedBrand){%> selected <%}%>>
				<%
				out.println(b.getName());
				%>
			</option>
			<%
			}
			%>
		</select><br> <label>Ordered Date</label><br><input type="date"
		name="orderedDate" value="${orderedDate}" /><br>
		<input type="submit" value=Search>
	</form>
	
	
		<table border=1 cellspacing=2 cellpadding=4>
		<tr>
			<th>Order ID</th>
			<th>Date</th>
			<th>Total</th>
			<th>Product Name</th>
			<th>Category</th>
			<th>User</th>
		</tr>
		<%
		List<Order> list = (List<Order>) request.getAttribute("orders");
		for (Order order : list) {
		%>
		
		<tr>
			<td><%=order.getId()%></td>
			<td><%=order.getOrderedDate()%></td>
			<td><%=order.getTotal()%></td>
			<td><%=order.getProduct().getName()%></td>
			<td><%=order.getProduct().getBrand().getName()%></td>
			<td><%=order.getCustomer().getUsername()%></td>
		
		</tr>
		<%
		}
		%>
	</table>
	</div>
	
		
</body>
</html>