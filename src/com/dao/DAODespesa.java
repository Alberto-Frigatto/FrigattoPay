package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entity.Despesa;

public class DAODespesa
{
  
    private Connection conn;

    public DAODespesa(Connection conn) 
    {
        this.conn = conn;
    }

    public List<Despesa> getAll() throws SQLException 
    {
        List<Despesa> despesas = new ArrayList<Despesa>();
        
        ResultSet result = this.getDespesas();

        while (result.next())
        {
            Despesa despesa = new Despesa(
                result.getInt("cd_despesa"),
                result.getInt("t_fp_cliente_cd_cliente"),
                result.getInt("t_fp_tipo_despesa_cd_tipo"),
                result.getString("nm_despesa"),
                result.getDouble("vl_despesa"),
                result.getString("ds_despesa"),
                result.getString("dt_vencimento")
            );

            despesas.add(despesa);
        }

        return despesas;
    }

    private ResultSet getDespesas() throws SQLException
    {
        Statement stmt = this.conn.createStatement();

        String query = """
            SELECT
                cd_despesa, 
                t_fp_cliente_cd_cliente, 
                t_fp_tipo_despesa_cd_tipo, 
                nm_despesa, 
                vl_despesa, 
                ds_despesa, 
                dt_vencimento

                FROM T_FP_DESPESA
        """;

        return stmt.executeQuery(query);        
    }

    public void insert(Despesa dp) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ call InserirDespesa(?, ?, ?, ?, ?, ?) }");

        cs.setString(1, dp.getNome());
        cs.setDouble(2, dp.getValor());
        cs.setString(3, dp.getDescricao());
        cs.setString(4, dp.getDataVencimento());
        cs.setInt(5, dp.getIdTipoDespesa());
        cs.setInt(6, dp.getIdCliente());
        cs.execute();
    }
}