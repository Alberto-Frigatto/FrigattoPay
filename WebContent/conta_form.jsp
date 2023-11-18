<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.entity.conta.Conta, com.model.dao.DAOTipo, com.model.dao.DAOConta, com.model.dao.DAOBanco, com.model.entity.banco.Banco, java.sql.Connection" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="template/header.jsp">
    <jsp:param name="title" value="Cadastrar Conta" />
    <jsp:param name="navbar" value='true' />
</jsp:include>

<%
	Connection conn = (Connection) request.getAttribute("conn");
	
	try
	{
		DAOTipo daoTipo = new DAOTipo(conn);
		
		request.setAttribute("tiposConta", daoTipo.getAll("t_fp_tipo_conta"));
		
		DAOBanco daoBanco = new DAOBanco(conn);
		
		request.setAttribute("bancos", daoBanco.getAll());
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
	
	if (request.getParameter("id") != null)
	{
		try
		{
			DAOConta daoConta = new DAOConta(conn);
			
			int idConta = Integer.parseInt(request.getParameter("id"));
			
			request.setAttribute("conta", daoConta.getById(idConta));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
%>

<main class='d-flex align-items-center justify-content-center flex-column gap-3'>
	<c:if test="${error != null}">
		<div class="alert alert-danger alert-dismissible fade show" role="alert">
		    ${ error }
		    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
		</div>
	</c:if>
	
	<form 
		action='<%=request.getContextPath() %>/user/conta${ conta != null ? "?method=put&idConta=".concat(conta.getId()) : "" }'
		method='post'
		class='p-4 bg-white d-flex flex-column rounded-4 w-50 gap-4'>
		
		<h1 class='fs-2'>${ conta != null ? "Alterar conta: ".concat(conta.getNumero()) : "Cadastrar conta bancária" }</h1>
		
		<div class='row mb-2'>
			<div class='col'>
				<div class="form-floating">
					<select class='form-control' name="idTipo" id="idTipo">
						<c:forEach var="tipo" items="${ tiposConta }">
							<c:choose>
							    <c:when test='${ conta != null and tipo.getId() == conta.getIdTipoConta() }'>
							    	<option selected value="${ tipo.getId() }">${ tipo.getNome() }</option>
							    </c:when>
							    <c:otherwise>
							    	<option value="${ tipo.getId() }">${ tipo.getNome() }</option>
							    </c:otherwise>
							</c:choose>
							
						</c:forEach>
					</select>
					
  					<label for="idTipo">Tipo de conta bancária </label>
				</div>
			</div>
			<div class='col-5'>
			<div class="form-floating">
					<select class='form-control' name="idBanco" id="idBanco">
						<c:forEach var="banco" items="${ bancos }">
							<c:choose>
							    <c:when test='${ conta != null and banco.getId() == conta.getIdBanco() }'>
							    	<option selected value="${ banco.getId() }">${ banco.getNome() }</option>
							    </c:when>
							    <c:otherwise>
							    	<option value="${ banco.getId() }">${ banco.getNome() }</option>
							    </c:otherwise>
							</c:choose>
							
						</c:forEach>
					</select>
					
  					<label for="idBanco">Banco</label>
				</div>
			</div>
		</div>
		
		<div class='row mb-2'>
			<div class='col'>
				<div class="form-floating">
					<input type="text" value='${ conta != null ? conta.getNumero() : "" }' class="form-control" name="numero" placeholder="123456">
					<label for="numero">Número da Conta</label>
				</div>
			</div>
			<div class='col'>
				<div class="form-floating">
					<input type="text" value='${ conta != null ? conta.getAgencia() : "" }' class="form-control" name="agencia" placeholder="423015">
					<label for="agencia">Agência</label>
				</div>
			</div>
		</div>
		<div class='row mb-2'>
			<div class='col'>
				<div class="form-floating">
				    <input type="text" value='<c:if test='${ conta != null }'><fmt:formatNumber value="${ conta.getSaldo() }" type="currency" currencyCode="BRL" pattern="#,##0.00" /></c:if>' class="form-control valor-input" name='saldo' id="saldo" placeholder="1000">
				    <label for="saldo">Saldo</label>
				</div>
			</div>
		</div>
		
		
		<div class='d-grid'>
			<button type='submit' class='btn btn-primary d-flex align-items-center justify-content-center gap-2'>
				${ conta != null ? "<i class='bi bi-pencil-fill fs-6'></i> Alterar" : "<i class='bi bi-plus-circle'></i> Cadastrar" }
			</button>
		</div>
	</form>
</main>

<%@ include file="template/footer.jsp" %>