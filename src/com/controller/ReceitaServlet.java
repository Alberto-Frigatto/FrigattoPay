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

@WebServlet("/user/receita")
public class ReceitaServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
		if ("delete".equals(request.getParameter("method")))
			this.doDelete(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
		if ("put".equals(request.getParameter("method")))
		{
			this.doPut(request, response);
			return;
		}
		
		Connection conn = (Connection) request.getAttribute("conn");
		
		SimpleDateFormat dateFormatInter = new SimpleDateFormat("yyyy-MM-dd");
		
		try
		{
			DAOReceita daoReceita = new DAOReceita(conn);
			
			Cliente clienteLogado = (Cliente) request.getSession().getAttribute("clienteLogado");
			int idCliente = clienteLogado.getId();
			
			int idTipoReceita = Integer.parseInt(request.getParameter("idTipo"));
			String rawValor = request.getParameter("valor");
			Double valor = rawValor.isEmpty() ? 0 : Double.parseDouble(rawValor);
			
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
		SimpleDateFormat dateFormatInter = new SimpleDateFormat("yyyy-MM-dd");
		
		Connection conn = (Connection) request.getAttribute("conn");
		
		int idReceita = Integer.parseInt((String) request.getParameter("idReceita"));
		
		try
		{
			DAOReceita daoReceita = new DAOReceita(conn);
			
			int idTipoReceita = Integer.parseInt(request.getParameter("idTipo"));
			String rawValor = request.getParameter("valor");
			Double valor = rawValor.isEmpty() ? 0 : Double.parseDouble(rawValor);
			
			String rawDataReceita = request.getParameter("dataReceita");
			Date dataReceita = rawDataReceita != "" ? dateFormatInter.parse(rawDataReceita) : null;
			
			
			Receita receita = daoReceita.getById(idReceita);
			
			receita.updateDataReceita(dataReceita);
			receita.updateIdTipoReceita(idTipoReceita);
			receita.updateValor(valor);
			
			daoReceita.update(receita);

			response.sendRedirect(request.getContextPath() + "/user/receitas");
		}
		catch (Exception e)
		{
			request.setAttribute("error", e.getMessage());
			e.printStackTrace();
			request.getRequestDispatcher("/user/receita/alterar?id=" + idReceita).forward(request, response);
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Connection conn = (Connection) request.getAttribute("conn");
		
		try
		{
			DAOReceita daoReceita = new DAOReceita(conn);
			
			int idReceita = Integer.parseInt((String) request.getParameter("idReceita"));
			
			daoReceita.delete(idReceita);
			
			response.sendRedirect(request.getContextPath() + "/user/receitas");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
