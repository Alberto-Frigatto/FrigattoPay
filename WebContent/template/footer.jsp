<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate" %>

		<footer class='d-flex align-items-center justify-content-center py-3 border-top text-white'>
			<span class='d-flex align-items-center gap-2'>
				Copyright © FrigattoPay <%=LocalDate.now().getYear() %> - 
				<a href='https://github.com/Alberto-Frigatto/Fintech' target='_blank' class="text-white">
           			 <i class="bi bi-github fs-4"></i>
        		</a>
			</span>
		</footer>
		
	
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/jquery-mask-plugin@1.14.16/dist/jquery.mask.min.js"></script>
		<script src="<%=request.getContextPath() %>/static/js/app.js?5"></script>
	</body>
</html>