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

import com.model.dao.DAOClientePF;
import com.model.entity.cliente.ClientePF;

@WebServlet({"/clientePF", "/user/clientePF"})
public class ClientePFServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		SimpleDateFormat dateFormatInter = new SimpleDateFormat("yyyy-MM-dd");
		
		Connection conn = (Connection) request.getAttribute("conn");
		
		try
		{
			DAOClientePF daoClientePF = new DAOClientePF(conn);
			
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String cpf = request.getParameter("cpf").replaceAll("\\D", "");
			String rg = request.getParameter("rg").replaceAll("\\D", "");
			
			String rawDataNascimento = request.getParameter("dataNascimento");
			Date dataNascimento = rawDataNascimento != "" ? dateFormatInter.parse(rawDataNascimento) : null;
			String senha = request.getParameter("senha");	
			
			ClientePF cliente = new ClientePF(
				nome,
				email,
				senha,
				cpf,
				rg,
				dataNascimento
			);
			
			daoClientePF.insert(cliente);
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		catch (Exception e)
		{
			request.setAttribute("error", e.getMessage());
			e.printStackTrace();
			request.getRequestDispatcher("register_cliente_pf.jsp").forward(request, response);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if ("delete".equals(request.getParameter("method")))
			this.doDelete(request, response);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Connection conn = (Connection) request.getAttribute("conn");
		
		try
		{
			DAOClientePF daoClientePF = new DAOClientePF(conn);
			
			ClientePF cliente = (ClientePF) request.getSession().getAttribute("clienteLogado");
			
			daoClientePF.delete(cliente.getId());
			
			response.sendRedirect(request.getContextPath() + "/user/logout");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
