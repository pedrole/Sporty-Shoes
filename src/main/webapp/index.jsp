
<%@page import="org.apache.commons.lang3.StringUtils"%>
<jsp:include page="header.jsp" />


<span><%= StringUtils.defaultString( (String) request.getAttribute("message")) %></span>


</body>
</html>