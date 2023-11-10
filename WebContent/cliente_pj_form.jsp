<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template/header.jsp">
	<jsp:param name="title" value="Realizar Cadastro" />
</jsp:include>

<main class='d-flex align-items-center justify-content-center flex-column gap-3'>
	<c:if test="${error != null }">
		<div class="alert alert-danger" role="alert">
			${ error }
		</div>
	</c:if>
	
	<form action='clientePJ' method='post' class='p-4 bg-white d-flex flex-column rounded-4 w-50 gap-2'>
		<h1  class='fs-2'>Pessoa Jurídica</h1>
		
		<p>Para criar uma conta como <b>Pessoa Física</b> <a href='<%=request.getContextPath() + "/criar/pf"%>'> Clique aqui</a></p>
		
		<div class='row mb-2'>
			<div class='col'>
				<div class="form-floating">
					<input type="text" class="form-control" name='nome' placeholder="Empresa">
					<label for="nome">Nome</label>
				</div>
			</div>
			<div class='col'>
				<div class="form-floating">
					<input type="text" class="form-control" name='email' placeholder="email">
					<label for="email">Email</label>
				</div>
			</div>
		</div>
		<div class='row mb-2'>
			<div class='col'>
				<div class="form-floating">
					<input type="text" class="form-control" name='cnpj' placeholder="11111111111111">
					<label for="cnpj">CNPJ</label>
				</div>
			</div>
			<div class='col'>
				<div class="form-floating">
					<input type="text" class="form-control" name="setor" placeholder="setor">
					<label for="setor">Setor</label>
				</div>
			</div>
		</div>
		<div class='row mb-2'>
			<div class='col'>
				<div class="form-floating">
					<input type="text" class="form-control" name="inscricaoEstadual" placeholder="11111111111111111111">
					<label for="inscricaoEstadual">Inscrição estadual</label>
				</div>
			</div>
			<div class='col'>
				<div class="form-floating">
					<input type="date" class="form-control" name="dataAbertura" placeholder="25/10/2015">
					<label for="dataAbertura">Data de abertura</label>
				</div>
			</div>
		</div>	

		
		<div class='row mb-2 align-items-center'>
			<div class='col'>
				<div class="form-floating">
				    <input type="password" class="form-control" name='senha' id="senha" placeholder="adawdawdawdaw">
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
		
		<div class='d-grid'>
			<button type='submit' class='btn btn-primary'>Cadastrar</button>
		</div>
		
		<p>Ou então, efetue seu login <a href='<%=request.getContextPath() + "/"%>'>clicando aqui</a></p>
	</form>
</main>

<%@ include file="template/footer.jsp" %>