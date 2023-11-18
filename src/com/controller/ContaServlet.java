package com.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.dao.DAOConta;
import com.model.entity.cliente.Cliente;
import com.model.entity.conta.Conta;

@WebServlet("/user/conta")
public class ContaServlet extends HttpServlet
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
		
		
		try
		{
			DAOConta daoConta = new DAOConta(conn);
			
			Cliente clienteLogado = (Cliente) request.getSession().getAttribute("clienteLogado");
			int idCliente = clienteLogado.getId();
			
			int idBanco = Integer.parseInt(request.getParameter("idBanco"));
			
			int idTipoConta = Integer.parseInt(request.getParameter("idTipo"));
			
			String numero = request.getParameter("numero");
			
			String rawSaldo = request.getParameter("saldo");
			Double saldo = rawSaldo.isEmpty() ? 0 : Double.parseDouble(rawSaldo);
			
			String agencia = request.getParameter("agencia");
			
			Conta conta = new Conta(
				idCliente,
				idBanco,
				idTipoConta,
				numero,
				saldo,
				agencia
			);
			
			daoConta.insert(conta);
			
			response.sendRedirect(request.getContextPath() + "/user/contas");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/conta_form.jsp").forward(request, response);
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		Connection conn = (Connection) request.getAttribute("conn");
		
		int idConta = Integer.parseInt((String) request.getParameter("idConta"));
		
		try
		{
			DAOConta daoConta = new DAOConta(conn);
			
						
			int idBanco = Integer.parseInt(request.getParameter("idBanco"));
			
			int idTipoConta = Integer.parseInt(request.getParameter("idTipo"));
			
			String numero = request.getParameter("numero");
			
			String rawSaldo = request.getParameter("saldo");
			Double saldo = rawSaldo.isEmpty() ? 0 : Double.parseDouble(rawSaldo);
			
			String agencia = request.getParameter("agencia");
			
			
			Conta conta = daoConta.getById(idConta);
			
			conta.updateIdBanco(idBanco);
			conta.updateIdTipoConta(idTipoConta);
			conta.updateNumero(numero);
			conta.updateSaldo(saldo);
			conta.updateAgencia(agencia);
			
			daoConta.update(conta);

			response.sendRedirect(request.getContextPath() + "/user/contas");
		}
		catch (Exception e)
		{
			request.setAttribute("error", e.getMessage());
			e.printStackTrace();
			request.getRequestDispatcher("/user/conta/alterar?id=" + idConta).forward(request, response);
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Connection conn = (Connection) request.getAttribute("conn");
		
		try
		{
			DAOConta daoConta = new DAOConta(conn);
			
			int idConta = Integer.parseInt((String) request.getParameter("idConta"));
			
			daoConta.delete(idConta);
			
			response.sendRedirect(request.getContextPath() + "/user/contas");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
