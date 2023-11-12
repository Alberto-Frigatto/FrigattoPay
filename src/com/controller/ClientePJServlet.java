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

import com.model.dao.DAOClientePJ;
import com.model.entity.cliente.ClientePJ;

@WebServlet({"/clientePJ", "/user/clientePJ"})
public class ClientePJServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if ("put".equals(request.getParameter("method")))
		{
			this.doPut(request, response);
			return;
		}

		SimpleDateFormat dateFormatInter = new SimpleDateFormat("yyyy-MM-dd");
		
		Connection conn = (Connection) request.getAttribute("conn");
		
		try
		{
			DAOClientePJ daoClientePJ = new DAOClientePJ(conn);
			
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String cnpj = request.getParameter("cnpj");
			String inscricaoEstadual = request.getParameter("inscricaoEstadual");
			String setor = request.getParameter("setor");
			
			String rawDataAbertura = request.getParameter("dataAbertura");
			Date dataAbertura = rawDataAbertura != "" ? dateFormatInter.parse(rawDataAbertura) : null;
			String senha = request.getParameter("senha");
			
			ClientePJ cliente = new ClientePJ(
					nome,
					email,
					senha,
					cnpj,
					inscricaoEstadual,
					dataAbertura,
					setor
			);
			
			daoClientePJ.insert(cliente);
			
			request.getRequestDispatcher("index.jsp").forward(request, response);		
		}
		catch (Exception e)
		{
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("cliente_pj_form.jsp").forward(request, response);
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		SimpleDateFormat dateFormatInter = new SimpleDateFormat("yyyy-MM-dd");
		
		Connection conn = (Connection) request.getAttribute("conn");
		
		try
		{
			DAOClientePJ daoClientePJ = new DAOClientePJ(conn);
			
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String cnpj = request.getParameter("cnpj").replaceAll("\\D", "");
			String inscricaoEstadual = request.getParameter("inscricaoEstadual").replaceAll("\\D", "");
			String setor = request.getParameter("setor");
			
			String rawDataAbertura = request.getParameter("dataAbertura");
			Date dataAbertura = rawDataAbertura != "" ? dateFormatInter.parse(rawDataAbertura) : null;
			
			ClientePJ cliente = (ClientePJ) request.getSession().getAttribute("clienteLogado");
			
			cliente.updateNome(nome);
			cliente.updateEmail(email);
			cliente.updateCnpj(cnpj);
			cliente.updateInscricaoEstadual(inscricaoEstadual);
			cliente.updateSetor(setor);
			cliente.updateDataAbertura(dataAbertura);
			
			daoClientePJ.update(cliente);

			response.sendRedirect(request.getContextPath() + "/user/perfil");
		}
		catch (Exception e)
		{
			request.setAttribute("error", e.getMessage());
			e.printStackTrace();
			request.getRequestDispatcher("/cliente_pj_form.jsp").forward(request, response);
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
			DAOClientePJ daoClientePJ = new DAOClientePJ(conn);
			
			ClientePJ cliente = (ClientePJ) request.getSession().getAttribute("clienteLogado");
			
			daoClientePJ.delete(cliente.getId());
			
			response.sendRedirect(request.getContextPath() + "/user/logout");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
}