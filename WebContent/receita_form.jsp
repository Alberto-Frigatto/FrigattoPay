<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.entity.receita.Receita, com.model.dao.DAOTipo, com.model.dao.DAOReceita, java.sql.Connection" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="template/header.jsp">
    <jsp:param name="title" value="Cadastrar Receita" />
    <jsp:param name="navbar" value='true' />
</jsp:include>

<%
	Connection conn = (Connection) request.getAttribute("conn");
	
	try
	{
		DAOTipo daoTipo = new DAOTipo(conn);
		
		request.setAttribute("tiposReceita", daoTipo.getAll("t_fp_tipo_receita"));
		
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
	
	if (request.getParameter("id") != null)
	{
		try
		{
			DAOReceita daoReceita = new DAOReceita(conn);
			
			int idReceita = Integer.parseInt(request.getParameter("id"));
			
			request.setAttribute("receita", daoReceita.getById(idReceita));
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
		action='<%=request.getContextPath() %>/user/receita${ receita != null ? "?method=put&idReceita=".concat(receita.getId()) : "" }'
		method='post'
		class='p-4 bg-white d-flex flex-column rounded-4 w-50 gap-4'>
		
		<h1 class='fs-2'>${ receita != null ? "Alterar receita" : "Cadastrar receita" }</h1>
		
		<div class='row mb-2'>
			<div class='col'>
				<div class="form-floating">
					<select class='form-control' name="idTipo" id="idTipo">
						<c:forEach var="tipo" items="${ tiposReceita }">
							<c:choose>
							    <c:when test='${ receita != null and tipo.getId() == receita.getIdTipoReceita() }'>
							    	<option selected value="${ tipo.getId() }">${ tipo.getNome() }</option>
							    </c:when>
							    <c:otherwise>
							    	<option value="${ tipo.getId() }">${ tipo.getNome() }</option>
							    </c:otherwise>
							</c:choose>
							
						</c:forEach>
					</select>
					
  					<label for="idTipo">Tipo de Receita</label>
				</div>
			</div>
			<div class='col'>
				<div class="form-floating">
				    <input
				    	type="date"
				    	class="form-control"
				    	name='dataReceita'
				    	id="dataReceita"
				    	placeholder="27/02/2005"
				    	value='<c:if test="${ receita != null }"><fmt:formatDate pattern="yyyy-MM-dd" value="${ receita.getDataReceita() }" /></c:if>'>
				    <label for="dataReceita">Data da Receita</label>
				</div>
			</div>
		</div>
		
		<div class='row mb-2'>
			<div class='col-5'>
				<div class="form-floating">
				    <input type="text" value='<c:if test='${ receita != null }'><fmt:formatNumber value="${ receita.getValor() }" type="currency" currencyCode="BRL" pattern="#,##0.00" /></c:if>' class="form-control valor-input" name='valor' id="valor" placeholder="1301">
				    <label for="valor">Valor</label>
				</div>
			</div>
		</div>
		
		<div class='d-grid'>
			<button type='submit' class='btn btn-primary d-flex align-items-center justify-content-center gap-2'>
				${ receita != null ? "<i class='bi bi-pencil-fill fs-6'></i> Alterar" : "<i class='bi bi-plus-circle'></i> Cadastrar" }
			</button>
		</div>
	</form>
</main>

<%@ include file="template/footer.jsp" %>