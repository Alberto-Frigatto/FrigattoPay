package com.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.dao.DAODespesa;
import com.model.entity.cliente.Cliente;

@WebServlet("/user/despesas")
public class LoadPageDespesasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Connection conn = (Connection) request.getAttribute("conn");
		
		try
		{
			DAODespesa daoDespesa = new DAODespesa(conn);
			
			Cliente clienteLogado = (Cliente) request.getSession().getAttribute("clienteLogado");
			int idCliente = clienteLogado.getId();
			
			request.setAttribute("despesas", daoDespesa.getAll(idCliente));
			
			request.getRequestDispatcher("/despesas.jsp").forward(request, response);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
