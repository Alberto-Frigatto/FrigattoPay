package com.filter;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

import com.singleton.ConnectionManager;

@WebFilter("/*")
public class ConnectionFilter extends HttpFilter implements Filter
{
	private static final long serialVersionUID = 1L;

	public void init(FilterConfig fConfig) throws ServletException {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		ConnectionManager.getInstance();
		
		Connection conn = ConnectionManager.getConnection();
		
		request.setAttribute("conn", conn);
		
		chain.doFilter(request, response);
		
		try
		{
			conn.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void destroy() {}
}
