<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1" />
	<meta http-equiv="cache-control" content="no-cache" />
	<title>Insert title here</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<style>
		body
		{
			min-height: 100vh;
		}
	</style>
</head>
<body class='d-flex align-items-center justify-content-center bg-black flex-column gap-3'>
	<c:if test="${error != null}">
		<div class="alert alert-danger" role="alert">
		    ${ error }
		</div>
	</c:if>
	<form action='login' method='post' class='p-4 bg-white d-flex flex-column rounded-4 w-25 gap-3'>
		<h2>Entre</h2>
		
		<div class="form-floating mb-3">
		    <input type="email" class="form-control" name='email' id="email" placeholder="name@example.com">
		    <label for="email">Email</label>
		</div>
		
		<div class="form-floating mb-3">
		    <input type="password" class="form-control" name='senha' id="senha" placeholder="senha">
		    <label for="senha">Senha</label>
		</div>
		
		<div class='d-grid'>
			<button type='submit' class='btn btn-primary'>Entrar</button>
		</div>
	</form>
</body>
</html>