package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Despesa;

public class DAODespesa extends DAO
{
    public DAODespesa(Connection conn) 
    {
        super(conn);
    }

    public List<Despesa> getAll() throws SQLException, ParseException
    {
        List<Despesa> despesas = new ArrayList<Despesa>();
        
        ResultSet result = this.getDespesas();

        while (result.next())
        {
            String formattedDate = this.formatDate(result.getDate("dt_vencimento"));

            Despesa despesa = new Despesa(
                result.getInt("cd_despesa"),
                result.getInt("t_fp_cliente_cd_cliente"),
                result.getInt("t_fp_tipo_despesa_cd_tipo"),
                result.getString("nm_despesa"),
                result.getDouble("vl_despesa"),
                result.getString("ds_despesa"),
                formattedDate
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
        cs.setDate(4, new java.sql.Date(dp.getDataVencimento().getTime()));
        cs.setInt(5, dp.getIdTipoDespesa());
        cs.setInt(6, dp.getIdCliente());
        cs.execute();
    }
}