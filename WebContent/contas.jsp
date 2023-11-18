<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.entity.conta.Conta, com.model.dao.DAOTipo, com.model.dao.DAOBanco, java.sql.Connection" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="template/header.jsp">
    <jsp:param name="title" value="Contas" />
    <jsp:param name="navbar" value="true" />
</jsp:include>

<%
	Connection conn = (Connection) request.getAttribute("conn");
	
	try
	{
		DAOTipo daoTipo = new DAOTipo(conn);
		
		request.setAttribute("daoTipo", daoTipo);
		
		DAOBanco daoBanco = new DAOBanco(conn);
		
		request.setAttribute("daoBanco", daoBanco);
		
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
%>

<main class='d-flex align-items-center justify-content-center flex-column gap-3'>
	<section class='p-4 bg-white d-flex flex-column rounded-4 w-50 gap-3'>
		<header class='d-flex align-items-center justify-content-between'>
			<h1 class='fs-2'>Suas contas bancárias</h1>
			<a href='<%=request.getContextPath() + "/user/contas/criar"%>' class='btn btn-primary d-flex align-items-center gap-2'>
				<i class="bi bi-plus-circle"></i>
				Criar conta bancária
			</a>
		</header>
		<section class='d-flex flex-column gap-3'>
			<c:choose>
			    <c:when test="${contas.size() > 0}">
			    	<c:forEach var="conta" items="${ contas }">
			    		<div class='card'>
			    			<div class='card-body d-flex align-items-center justify-content-between'>
			    				<div class='d-flex flex-column align-items-start'>
			    					<span class='fs-4'>Conta ${ conta.getNumero() }</span>
			    					<small>${ daoBanco.getById(conta.getIdBanco()).getNome()  } - ${ daoTipo.getById("t_fp_tipo_conta", conta.getIdTipoConta()).getNome() }</small>
			    				</div>
			    				<span class='fs-5'><fmt:formatNumber value="${ conta.getSaldo() }" type="currency" currencyCode="BRL" /></span>
				    			<ul class='nav gap-3'>
				    				<a href='<%=request.getContextPath() + "/user/conta/alterar?id="%>${ conta.getId() }' class='btn btn-outline-primary'>
				    					<i class="bi bi-pencil-square"></i>
				    				</a>
				    				<button type='button' data-bs-toggle="modal"
											data-bs-target="#excluirConta${ conta.getId() }Modal"
											class='btn btn-outline-danger'>
											<i class="bi bi-trash-fill"></i>
									</button>
				    			</ul>
			    			</div>
			    		</div>
			    	</c:forEach>
			    </c:when>
			    <c:otherwise>
			    	<p class='text-center'>Não há contas</p>
			    </c:otherwise>
			</c:choose>
		</section>
	</section>
	
   	<c:forEach var="conta" items="${ contas }">
		<div class="modal fade" id="excluirConta${ conta.getId() }Modal">
		    <div class="modal-dialog modal-dialog-centered">
		    	<div class="modal-content">
		      		<div class="modal-header">
		        		<h1 class="modal-title fs-5" id="exampleModalLabel">Excluir Conta</h1>
		        		<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
		      		</div>
		      		<div class="modal-body">
		        		<span class='text-center d-block'>Deseja excluir a conta ${ conta.getNumero() } ?</span>
		      		</div>
		      		<div class="modal-footer">
		        		<button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
						<a href='<%=request.getContextPath() + "/user/conta?method=delete&idConta="%>${ conta.getId() }' class="btn btn-primary">Excluir Conta</a>
		      		</div>
		    	</div>
		    </div>
		</div>
   	</c:forEach>
</main>

<%@ include file="template/footer.jsp" %>