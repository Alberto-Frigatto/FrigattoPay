package com.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.dao.DAOReceita;
import com.model.entity.cliente.Cliente;

@WebServlet("/user/receitas")
public class LoadPageReceitasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Connection conn = (Connection) request.getAttribute("conn");
		
		try
		{
			DAOReceita daoReceita = new DAOReceita(conn);
			
			Cliente clienteLogado = (Cliente) request.getSession().getAttribute("clienteLogado");
			int idCliente = clienteLogado.getId();
			
			request.setAttribute("receitas", daoReceita.getAll(idCliente));
			
			request.getRequestDispatcher("/receitas.jsp").forward(request, response);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
