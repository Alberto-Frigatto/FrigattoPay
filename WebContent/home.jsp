<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.entity.cliente.Cliente" %>

<jsp:include page="template/header.jsp">
    <jsp:param name="title" value="Início" />
</jsp:include>

<body>
	<h1>${ clienteLogado.getNome() }</h1>
	
	<a href='/Fintech'>Clique aqui</a>
</body>

<%@ include file="template/footer.jsp" %>