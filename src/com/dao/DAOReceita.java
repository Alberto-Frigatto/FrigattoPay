package com.dao;

import com.entity.Receita;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOReceita {

    private Connection conn;

    public DAOReceita(Connection conn)
    {
        this.conn = conn;
    }

    public List<Receita> getAll() throws SQLException 
    {
        List<Receita> receitas = new ArrayList<Receita>();
        
        ResultSet result = this.getReceitas();

        while (result.next())
        {
            Receita receita = new Receita(
                result.getInt("cd_receita"),
                result.getInt("t_fp_cliente_cd_cliente"),
                result.getInt("t_fp_tipo_receita_cd_tipo"),
                result.getDouble("vl_receita"),
                result.getString("dt_receita")
            );

            receitas.add(receita);
        }

        return receitas;
    }

    private ResultSet getReceitas() throws SQLException
    {
        Statement stmt = this.conn.createStatement();

        String query = """
                SELECT
                    cd_receita, 
                    t_fp_cliente_cd_cliente, 
                    t_fp_tipo_receita_cd_tipo, 
                    vl_receita, 
                    dt_receita

                    FROM T_FP_RECEITA
                """;

        return stmt.executeQuery(query);        
    }

    public void insert(Receita rc) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ call InserirReceita(?, ?, ?, ?) }");

        cs.setInt(1, rc.getIdTipoReceita());
        cs.setInt(2, rc.getIdCliente());
        cs.setDouble(3, rc.getValor());
        cs.setString(4, rc.getDataReceita());
        cs.execute();
    }
}