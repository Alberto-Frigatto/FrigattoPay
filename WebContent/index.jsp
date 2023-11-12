<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template/header.jsp">
    <jsp:param name="title" value="Login" />
</jsp:include>

<main class='d-flex align-items-center justify-content-center flex-column gap-3'>
	<c:if test="${error != null}">
		<div class="alert alert-danger" role="alert">
		    ${ error }
		</div>
	</c:if>
	<form action='login' method='post' class='p-4 bg-white d-flex flex-column rounded-4 w-25 gap-3'>
		<h1 class='fs-2'>Entre</h1>
		
		<div class="form-floating mb-3">
		    <input type="email" class="form-control" name='email' id="email" placeholder="name@example.com">
		    <label for="email">Email</label>
		</div>
		
		<div class="form-floating mb-3">
		    <input type="password" class="form-control" name='senha' id="senha" placeholder="senha">
		    <label for="senha">Senha</label>
		</div>
		
		<div class='d-grid'>
			<button type='submit' class='btn btn-primary d-flex align-items-center justify-content-center gap-2'>
				Entrar
				<i class="bi bi-chevron-right"></i>
			</button>
		</div>
		
		<a href='criar/pf'>Criar conta</a>
	</form>
</main>

<%@ include file="template/footer.jsp" %>