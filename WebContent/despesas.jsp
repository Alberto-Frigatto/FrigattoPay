<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.entity.despesa.Despesa, com.model.dao.DAOTipo, java.sql.Connection" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="template/header.jsp">
    <jsp:param name="title" value="Despesas" />
    <jsp:param name="navbar" value="true" />
</jsp:include>

<%
	Connection conn = (Connection) request.getAttribute("conn");
	
	try
	{
		DAOTipo daoTipo = new DAOTipo(conn);
		
		request.setAttribute("daoTipo", daoTipo);
		
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
%>

<main class='d-flex align-items-center justify-content-center flex-column gap-3'>
	<section class='p-4 bg-white d-flex flex-column rounded-4 w-50 gap-3'>
		<header class='d-flex align-items-center justify-content-between'>
			<h1 class='fs-2'>Suas despesas</h1>
			<a href='<%=request.getContextPath() + "/user/despesas/criar"%>' class='btn btn-primary d-flex align-items-center gap-2'>
				<i class="bi bi-plus-circle"></i>
				Criar Despesa
			</a>
		</header>
		<section class='d-flex flex-column gap-3'>
			<c:choose>
			    <c:when test="${despesas.size() > 0}">
			    	<c:forEach var="despesa" items="${ despesas }">
			    		<div class='card'>
			    			<div class='card-body d-flex align-items-center justify-content-between'>
			    				<div class='d-flex flex-column align-items-start'>
			    					<span class='fs-4'>${ despesa.getNome() }</span>
			    					<small>${ daoTipo.getById("t_fp_tipo_despesa", despesa.getIdTipoDespesa()).getNome() } - <fmt:formatDate pattern="dd/MM/yyyy" value="${ despesa.getDataVencimento() }" /></small>			    							    	
			    				</div>
			    				<span class='fs-5'><fmt:formatNumber value="${ despesa.getValor() }" type="currency" currencyCode="BRL" /></span>
				    			<ul class='nav gap-3'>
				    				<a href='<%=request.getContextPath() + "/user/despesa/alterar?id="%>${ despesa.getId() }' class='btn btn-outline-primary'>
				    					<i class="bi bi-pencil-square"></i>
				    				</a>
				    				<button type='button' data-bs-toggle="modal"
											data-bs-target="#excluirDespesa${ despesa.getId() }Modal"
											class='btn btn-outline-danger'>
											<i class="bi bi-trash-fill"></i>
									</button>
				    			</ul>
			    			</div>
			    		</div>
			    	</c:forEach>
			    </c:when>
			    <c:otherwise>
			    	<p class='text-center'>Não há despesas</p>
			    </c:otherwise>
			</c:choose>
		</section>
	</section>
	
   	<c:forEach var="despesa" items="${ despesas }">
		<div class="modal fade" id="excluirDespesa${ despesa.getId() }Modal">
		    <div class="modal-dialog modal-dialog-centered">
		    	<div class="modal-content">
		      		<div class="modal-header">
		        		<h1 class="modal-title fs-5" id="exampleModalLabel">Excluir Despesa</h1>
		        		<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
		      		</div>
		      		<div class="modal-body">
		        		<span class='text-center d-block'>Deseja excluir a despesa de ${ despesa.getNome() } no valor de <fmt:formatNumber value="${ despesa.getValor() }" type="currency" currencyCode="BRL" />?</span>
		      		</div>
		      		<div class="modal-footer">
		        		<button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
						<a href='<%=request.getContextPath() + "/user/despesa?method=delete&idDespesa="%>${ despesa.getId() }' class="btn btn-primary">Excluir Despesa</a>
		      		</div>
		    	</div>
		    </div>
		</div>
   	</c:forEach>
</main>

<%@ include file="template/footer.jsp" %>