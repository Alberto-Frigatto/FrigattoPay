<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.model.entity.cliente.ClientePF, com.model.entity.cliente.ClientePJ, com.util.FormatUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template/header.jsp">
    <jsp:param name="title" value="Minha conta" />
    <jsp:param name="navbar" value="true" />
</jsp:include>

<main class='d-flex align-items-center justify-content-center flex-column gap-3'>
	<section class='p-4 bg-white d-flex flex-column rounded-4 w-50 gap-5'>
		<h1 class='fs-2 text-center'>Minha conta</h1>
		<div class='row'>
			<div class='col'>
				<b>Nome:</b>
				${ clienteLogado.getNome() }
			</div>
			<c:choose>
				<c:when test="${isClientePF}">
					<div class='col'>
						<b>CPF:</b>
						${ FormatUtil.formatCpf(clienteLogado.getCpf()) }
					</div>
					<div class='col'>
						<b>RG:</b>
						${ FormatUtil.formatRg(clienteLogado.getRg()) }
					</div>
				</c:when>
				<c:otherwise>
					<div class='col'>
						<b>CNPJ:</b>
						${ FormatUtil.formatCnpj(clienteLogado.getCnpj()) }
					</div>
					<div class='col'>
						<b>Insc:</b>
						${ clienteLogado.getInscricaoEstadual() }
					</div>
				</c:otherwise>
			</c:choose>
			
		</div>
		<div class='row gap-3'>
			<div class='d-grid'>
			<c:choose>
				<c:when test="${isClientePF}">
					<a href='<%=request.getContextPath() + "/user/alterar/pf"%>' class='text-decoration-none btn btn-outline-primary p-4'>Alterar cadastro</a>
				</c:when>
				<c:otherwise>
					<a href='<%=request.getContextPath() + "/user/alterar/pj"%>' class='text-decoration-none btn btn-outline-primary p-4'>Alterar cadastro</a>
				</c:otherwise>
			</c:choose>
			</div>
			<div class='d-grid'>
				<button type='button' data-bs-toggle="modal"
						data-bs-target="#excluirContaModal"
						class='p-4 btn btn-outline-danger'>Excluir cadastro</button>
			</div>
		</div>
			
		<div class="modal fade" id="excluirContaModal">
		    <div class="modal-dialog modal-dialog-centered">
		    	<div class="modal-content">
		      		<div class="modal-header">
		        		<h1 class="modal-title fs-5" id="exampleModalLabel">Excluir Cadastro</h1>
		        		<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
		      		</div>
		      		<div class="modal-body">
		        		<span class='text-center d-block'>Deseja excluir seu cadastro?</span>
		      		</div>
		      		<div class="modal-footer">
		        		<button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
		        		<c:choose>
							<c:when test="${isClientePF}">
								<a href='<%=request.getContextPath() + "/user/clientePF?method=delete"%>' class="btn btn-primary">Excluir cadastro</a>
							</c:when>
							<c:otherwise>
								<a href='<%=request.getContextPath() + "/user/clientePJ?method=delete"%>' class="btn btn-primary">Excluir cadastro</a>
							</c:otherwise>
						</c:choose>
		      		</div>
		    	</div>
		    </div>
		</div>
	</section>
</main>

<%@ include file="template/footer.jsp" %>