<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template/header.jsp">
    <jsp:param name="title" value="Realizar Cadastro" />
</jsp:include>

<body class='d-flex align-items-center justify-content-center bg-black flex-column gap-3'>
	<c:if test="${error != null}">
		<div class="alert alert-danger" role="alert">
		    ${ error }
		</div>
	</c:if>

	<form action='clientePF' method='post' class='p-4 bg-white d-flex flex-column rounded-4 w-50 gap-2'>
		<h2>Pessoa Física</h2>
		
		<p>Para criar uma conta como Pessoa Jurídica <a href='register_cliente_pj.jsp'>Clique Aqui</a></p>
		
		<div class='row mb-2'>
			<div class='col'>
				<div class="form-floating">
				    <input type="text" class="form-control" name='nome' id="nome" placeholder="João">
				    <label for="nome">Nome</label>
				</div>
			</div>
			<div class='col'>
				<div class="form-floating">
				    <input type="email" class="form-control" name='email' id="email" placeholder="email">
				    <label for="email">Email</label>
				</div>
			</div>
		</div>
		
		<div class='row mb-2'>
			<div class='col'>
				<div class="form-floating">
				    <input type="text" class="form-control" name='cpf' id="cpf" placeholder="11111111111">
				    <label for="cpf">CPF</label>
				</div>
			</div>
			<div class='col'>
				<div class="form-floating">
				    <input type="text" class="form-control" name='rg' id="rg" placeholder="111111111">
				    <label for="rg">RG</label>
				</div>
			</div>
			<div class='col'>
				<div class="form-floating">
				    <input type="date" class="form-control" name='dataNascimento' id="dataNascimento" placeholder="27/02/2005">
				    <label for="dataNascimento">Data de nascimento</label>
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
	</form>
</body>

<%@ include file="template/footer.jsp" %>