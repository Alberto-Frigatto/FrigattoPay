<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.entity.despesa.Despesa, com.model.dao.DAOTipo, com.model.dao.DAODespesa, java.sql.Connection" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="template/header.jsp">
    <jsp:param name="title" value="Cadastrar Despesa" />
    <jsp:param name="navbar" value='true' />
</jsp:include>

<%
	Connection conn = (Connection) request.getAttribute("conn");
	
	try
	{
		DAOTipo daoTipo = new DAOTipo(conn);
		
		request.setAttribute("tiposDespesa", daoTipo.getAll("t_fp_tipo_despesa"));
		
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
	
	if (request.getParameter("id") != null)
	{
		try
		{
			DAODespesa daoDespesa = new DAODespesa(conn);
			
			int idDespesa = Integer.parseInt(request.getParameter("id"));
			
			request.setAttribute("despesa", daoDespesa.getById(idDespesa));
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
		action='<%=request.getContextPath() %>/user/despesa${ despesa != null ? "?method=put&idDespesa=".concat(despesa.getId()) : "" }'
		method='post'
		class='p-4 bg-white d-flex flex-column rounded-4 w-50 gap-4'>
		
		<h1 class='fs-2'>${ despesa != null ? "Alterar despesa" : "Cadastrar despesa" }</h1>
		
		<div class='row mb-2'>
			<div class='col'>
				<div class="form-floating">
					<select class='form-control' name="idTipo" id="idTipo">
						<c:forEach var="tipo" items="${ tiposDespesa }">
							<c:choose>
							    <c:when test='${ despesa != null and tipo.getId() == despesa.getIdTipoDespesa() }'>
							    	<option selected value="${ tipo.getId() }">${ tipo.getNome() }</option>
							    </c:when>
							    <c:otherwise>
							    	<option value="${ tipo.getId() }">${ tipo.getNome() }</option>
							    </c:otherwise>
							</c:choose>
							
						</c:forEach>
					</select>
					
  					<label for="idTipo">Tipo de Despesa</label>
				</div>
			</div>
			<div class='col'>
				<div class="form-floating">
				    <input
				    	type="date"
				    	class="form-control"
				    	name='dataVencimento'
				    	id="dataVencimento"
				    	placeholder="27/02/2005"
				    	value='<c:if test="${ despesa != null }"><fmt:formatDate pattern="yyyy-MM-dd" value="${ despesa.getDataVencimento() }" /></c:if>'>
				    <label for="dataVencimento">Data de Vencimento</label>
				</div>
			</div>
		</div>
		
		<div class='row mb-2'>
			<div class='col'>
				<div class="form-floating">
					<input type="text" value='${ despesa != null ? despesa.getNome() : "" }' class="form-control" name='nome' placeholder="Conta">
					<label for="nome">Nome da Despesa</label>
				</div>
			</div>
			<div class='col'>
				<div class="form-floating">
					<textarea type="text" class="form-control" name='descricao' placeholder="Descricao"> ${ despesa != null ? despesa.getDescricao() : "" }  </textarea>
					<label for="descricao">Descrição</label>
				</div>
			</div>
		</div>
		
		<div class='row mb-2'>
			<div class='col-5'>
				<div class="form-floating">
				    <input type="text" value='<c:if test='${ despesa != null }'><fmt:formatNumber value="${ despesa.getValor() }" type="currency" currencyCode="BRL" pattern="#,##0.00" /></c:if>' class="form-control valor-input" name='valor' id="valor" placeholder="1400">
				    <label for="valor">Valor</label>
				</div>
			</div>
		</div>
		
		<div class='d-grid'>
			<button type='submit' class='btn btn-primary d-flex align-items-center justify-content-center gap-2'>
				${ despesa != null ? "<i class='bi bi-pencil-fill fs-6'></i> Alterar" : "<i class='bi bi-person-fill-add fs-5'></i> Cadastrar" }
			</button>
		</div>
	</form>
</main>

<%@ include file="template/footer.jsp" %>