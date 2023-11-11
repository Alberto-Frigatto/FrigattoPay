<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.model.entity.cliente.Cliente" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><%=request.getParameter("title") %></title>
	<link rel="icon" type="image/x-icon" href="https://cdn-icons-png.flaticon.com/512/4021/4021642.png" />
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/static/css/styles.css?6" />
</head>
<body class='bg-dark'>

	<%
	    boolean withNavbar = Boolean.parseBoolean(request.getParameter("navbar"));
		
		if (withNavbar) {
	%>
		<nav class="navbar navbar-expand-lg bg-body-tertiary">
		  	<div class="container-fluid d-flex align-items-center justify-content-between px-5">
		  		<a class="navbar-brand" href="<%=request.getContextPath() + "/user/home"%>">FrigattoPay</a>
		    	<span>Olá, ${ clienteLogado.getNome() }</span>
		    	<ul class='nav d-flex gap-4'>
		    		<li class='nav-item'>
		    			<a href='<%=request.getContextPath() + "/user/perfil"%>' class='btn btn-primary'>Minha conta</a>
		    		</li>
		    		<li class='nav-item'>
		    			<a href='<%=request.getContextPath() + "/user/logout"%>' class='btn btn-outline-danger'>Sair</a>
		    		</li>
		    	</ul>
		  	</div>
		</nav>
	<% } %>