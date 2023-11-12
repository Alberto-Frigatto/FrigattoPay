package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.dao.DAOReceita;
import com.model.entity.cliente.Cliente;
import com.model.entity.receita.Receita;

@WebServlet("/user/receita/*")
public class ReceitaServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String pathInfo = request.getPathInfo();
		
		if (pathInfo != null)
			this.doPut(request, response);
		
		Connection conn = (Connection) request.getAttribute("conn");
		
		SimpleDateFormat dateFormatInter = new SimpleDateFormat("yyyy-MM-dd");
		
		try
		{
			DAOReceita daoReceita = new DAOReceita(conn);
			
			Cliente clienteLogado = (Cliente) request.getSession().getAttribute("clienteLogado");
			int idCliente = clienteLogado.getId();
			
			int idTipoReceita = Integer.parseInt(request.getParameter("idTipo"));
			Double valor = Double.parseDouble(request.getParameter("valor"));
			
			String rawDataReceita = request.getParameter("dataReceita");
			Date dataReceita = rawDataReceita != "" ? dateFormatInter.parse(rawDataReceita) : null;
			
			Receita receita = new Receita(
				idCliente,
				idTipoReceita,
				valor,
				dataReceita
			);
			
			daoReceita.insert(receita);
			
			response.sendRedirect(request.getContextPath() + "/user/receitas");
		}
		catch (Exception e)
		{
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/receita_form.jsp").forward(request, response);
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	}
}
