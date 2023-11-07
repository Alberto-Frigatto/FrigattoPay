package com.singleton;

import java.sql.DriverManager;
import java.sql.Connection;

public class ConnectionManager
{
	private static ConnectionManager connectionManager;
	
	public static ConnectionManager getInstance()
	{
		if (connectionManager != null)
			connectionManager = new ConnectionManager();
		
		return connectionManager;
	}
	
	public static Connection getConnection()
	{
		Connection conn = null;
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                "RM97807",
                "270205"
            );
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return conn;
	}
}
