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

import com.model.dao.DAODespesa;
import com.model.entity.cliente.Cliente;
import com.model.entity.despesa.Despesa;

@WebServlet("/user/despesa")
public class DespesaServlet extends HttpServlet
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
			DAODespesa daoDespesa = new DAODespesa(conn);
			
			Cliente clienteLogado = (Cliente) request.getSession().getAttribute("clienteLogado");
			int idCliente = clienteLogado.getId();
			
			int idTipoDespesa = Integer.parseInt(request.getParameter("idTipo"));
			
			String nome = request.getParameter("nome");
			
			String rawValor = request.getParameter("valor");
			Double valor = rawValor.isEmpty() ? 0 : Double.parseDouble(rawValor);
			
			String descricao = request.getParameter("descricao");
			
			String rawDataVencimento = request.getParameter("dataVencimento");
			Date dataVencimento = rawDataVencimento != "" ? dateFormatInter.parse(rawDataVencimento) : null;
			
			Despesa despesa = new Despesa(
				idCliente,
				idTipoDespesa,
				nome,
				valor,
				descricao,
				dataVencimento
			);
			
			daoDespesa.insert(despesa);
			
			response.sendRedirect(request.getContextPath() + "/user/despesas");
		}
		catch (Exception e)
		{
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/despesa_form.jsp").forward(request, response);
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		SimpleDateFormat dateFormatInter = new SimpleDateFormat("yyyy-MM-dd");
		
		Connection conn = (Connection) request.getAttribute("conn");
		
		int idDespesa = Integer.parseInt((String) request.getParameter("idDespesa"));
		
		try
		{
			DAODespesa daoDespesa = new DAODespesa(conn);
						
			int idTipoDespesa = Integer.parseInt(request.getParameter("idTipo"));
			
			String nome = request.getParameter("nome");
			
			String rawValor = request.getParameter("valor");
			Double valor = rawValor.isEmpty() ? 0 : Double.parseDouble(rawValor);
			
			String descricao = request.getParameter("descricao");
			
			String rawDataVencimento = request.getParameter("dataVencimento");
			Date dataVencimento = rawDataVencimento != "" ? dateFormatInter.parse(rawDataVencimento) : null;
			
			
			Despesa despesa = daoDespesa.getById(idDespesa);
			
			
			despesa.updateNome(nome);
			despesa.updateDataVencimento(dataVencimento);
			despesa.updateIdTipoDespesa(idTipoDespesa);
			despesa.updateValor(valor);
			despesa.updateDescricao(descricao);
			
			daoDespesa.update(despesa);

			response.sendRedirect(request.getContextPath() + "/user/despesas");
		}
		catch (Exception e)
		{
			request.setAttribute("error", e.getMessage());
			e.printStackTrace();
			request.getRequestDispatcher("/user/despesa/alterar?id=" + idDespesa).forward(request, response);
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Connection conn = (Connection) request.getAttribute("conn");
		
		try
		{
			DAODespesa daoDespesa = new DAODespesa(conn);
			
			int idDespesa = Integer.parseInt((String) request.getParameter("idDespesa"));
			
			daoDespesa.delete(idDespesa);
			
			response.sendRedirect(request.getContextPath() + "/user/despesas");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
