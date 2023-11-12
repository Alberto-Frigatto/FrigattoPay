<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.entity.cliente.ClientePF" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="template/header.jsp">
    <jsp:param name="title" value="Realizar Cadastro" />
    <jsp:param name="navbar" value='${ clienteLogado != null }' />
</jsp:include>

<main class='d-flex align-items-center justify-content-center flex-column gap-3'>
	<c:if test="${error != null}">
		<div class="alert alert-danger alert-dismissible fade show" role="alert">
		    ${ error }
		    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
		</div>
	</c:if>

	<form 
		action='<%=request.getContextPath() %>${ clienteLogado != null ? "/user/clientePF?method=put" : "/clientePF" }' 
		method='post'
		class='p-4 bg-white d-flex flex-column rounded-4 w-50 ${ clienteLogado != null ? "gap-4" : "gap-2" }'>

		<h1 class='fs-2'>${ clienteLogado != null ? "Alterar cadastro" : "Pessoa Física" }</h1>
		
		<c:if test='${ clienteLogado == null }'>
			<p>Para criar uma conta como <b>Pessoa Jurídica</b> <a href='<%=request.getContextPath() + "/criar/pj"%>'>Clique Aqui</a></p>
		</c:if>
		
		<div class='row mb-2'>
			<div class='col'>
				<div class="form-floating">
				    <input type="text" value='${ clienteLogado != null ? clienteLogado.getNome() : "" }' class="form-control" name='nome' id="nome" placeholder="João">
				    <label for="nome">Nome</label>
				</div>
			</div>
			<div class='col'>
				<div class="form-floating">
				    <input type="email" value='${ clienteLogado != null ? clienteLogado.getEmail() : "" }' class="form-control" name='email' id="email" placeholder="email">
				    <label for="email">Email</label>
				</div>
			</div>
		</div>
		
		<div class='row mb-2'>
			<div class='col'>
				<div class="form-floating">
				    <input type="text" value='${ clienteLogado != null ? clienteLogado.getCpf() : "" }' class="form-control cpf-input" name='cpf' id="cpf" placeholder="11111111111">
				    <label for="cpf">CPF</label>
				</div>
			</div>
			<div class='col'>
				<div class="form-floating">
				    <input type="text" value='${ clienteLogado != null ? clienteLogado.getRg() : "" }' class="form-control rg-input" name='rg' id="rg" placeholder="111111111">
				    <label for="rg">RG</label>
				</div>
			</div>
			<div class='col'>
				<div class="form-floating">
				    <input
				    	type="date"
				    	class="form-control"
				    	name='dataNascimento'
				    	id="dataNascimento"
				    	placeholder="27/02/2005"
				    	value='<c:if test="${ clienteLogado != null }"><fmt:formatDate pattern="yyyy-MM-dd" value="${ clienteLogado.getDataNascimento() }" /></c:if>'>
				    <label for="dataNascimento">Data de nascimento</label>
				</div>
			</div>
		</div>
		
		<c:if test='${ clienteLogado == null }'>
			<div class='row mb-2 align-items-center'>
				<div class='col'>
					<div class="form-floating">
					    <input
					    	type="password"
					    	class="form-control"
					    	name='senha'
					    	id="senha"
					    	placeholder="adawdawdawdaw">
					    	
					    <label for="senha">Senha</label>
					</div>
				</div>
				<div class='col'>
					<p>A senha deve possuir no mínimo:</p>
					<ul>
						<li>8 caracteres</li>
						<li>1 letra maiúscula</li>
						<li>1 letra minúscula</li>
						<li>1 número</li>
						<li>1 caractere especial</li>
					</ul>
				</div>
			</div>
		</c:if>
		
		<div class='d-grid'>
			<button type='submit' class='btn btn-primary d-flex align-items-center justify-content-center gap-2'>
				${ clienteLogado != null ? "<i class='bi bi-pencil-fill fs-6'></i> Alterar" : "<i class='bi bi-person-fill-add fs-5'></i> Cadastrar" }
			</button>
		</div>
		
		<c:if test='${ clienteLogado == null }'>
			<p>Ou então, efetue seu login <a href='<%=request.getContextPath() + "/"%>'>clicando aqui</a></p>
		</c:if>
	</form>
</main>

<%@ include file="template/footer.jsp" %>