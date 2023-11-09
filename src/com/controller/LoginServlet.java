package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.dao.DAOCliente;

@WebServlet("/login")
public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Connection conn = (Connection) request.getAttribute("conn");
		
		try
		{
			DAOCliente daoCliente = new DAOCliente(conn);
			
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			
			HttpSession session = request.getSession();
			
			session.setAttribute("clienteLogado", daoCliente.login(email, senha));

			response.sendRedirect("user/home");
		}
		catch(Exception e)
		{
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}
}
