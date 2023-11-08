package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.dao.DAOClientePF;
import com.model.entity.cliente.ClientePF;
import com.singleton.ConnectionManager;

@WebServlet("/clientePF")
public class ClientePFServlet extends HttpServlet {
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
}
