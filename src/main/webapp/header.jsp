<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page import="org.apache.commons.lang3.StringUtils.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

	<div class="nav">

		<div class="title-container">
			<h2>SportyShoes</h2>

		</div>

		<div class="nav-items">
			<sec:authorize access="hasAnyAuthority('ADMIN','CUSTOMER')">
				<a href="/product/index">PRODUCTS</a>
			</sec:authorize>

			<sec:authorize access="hasAuthority('ADMIN')">
				<a href="/brand/index">BRANDS</a>
				<a href="/user/index">REGISTERED USERS</a>
				<a href="/order/index">ORDERS</a>
			</sec:authorize>

			<sec:authorize access="!isAuthenticated()">
				<a href="login">LOGIN</a>
				<a href="register">REGISTER</a>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<a style="text-transform: uppercase;" href="/user/profile"><security:authentication 
				property="principal.username" /></a>
				<a href="/logout">LOGOUT</a>
			</sec:authorize>
		</div>
	</div>
	<p>${message}</p>
	