<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.model.entity.cliente.ClientePF, com.model.entity.cliente.ClientePJ, com.util.FormatUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template/header.jsp">
    <jsp:param name="title" value="Minha conta" />
    <jsp:param name="navbar" value="true" />
</jsp:include>

<main class='d-flex align-items-center justify-content-center flex-column gap-3'>
	<section class='p-4 bg-white d-flex flex-column rounded-4 w-50 gap-3'>
		<h1 class='fs-2 text-center'>Minha conta</h1>
		<div class='row'>
			<div class='col'>
				<b>Nome:</b>
				${ clienteLogado.getNome() }
			</div>
			<div class='col'>
				<%
					boolean isClientePF = session.getAttribute("clienteLogado") instanceof ClientePF;
				
					if (isClientePF) {
				%>
				        <b>CPF:</b>
						${ FormatUtil.formatCpf(clienteLogado.getCpf()) }
				<% } else { %>
				        <b>CNPJ:</b>
						${ FormatUtil.formatCnpj(clienteLogado.getCnpj()) }
				<% } %>
			</div>
			<div class='col'>
				<%
					if (isClientePF) {
				%>
				        <b>RG:</b>
						${ FormatUtil.formatRg(clienteLogado.getRg()) }
				<% } else { %>
				        <b>Insc.:</b>
						${ clienteLogado.getInscricaoEstadual() }
				<% } %>
			</div>
			
		</div>
	</section>
</main>

<%@ include file="template/footer.jsp" %>