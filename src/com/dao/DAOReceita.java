package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Receita;

public class DAOReceita extends DAO
{ 
    public DAOReceita(Connection conn)
    {
        super(conn);
    }

    public List<Receita> getAll() throws SQLException, ParseException
    {
        List<Receita> receitas = new ArrayList<Receita>();
        
        ResultSet result = this.getReceitas();

        while (result.next())
        {
            String formattedDate = this.formatDate(result.getDate("dt_receita"));

            Receita receita = new Receita(
                result.getInt("cd_receita"),
                result.getInt("t_fp_cliente_cd_cliente"),
                result.getInt("t_fp_tipo_receita_cd_tipo"),
                result.getDouble("vl_receita"),
                formattedDate
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

    public Receita getById(int id) throws SQLException, ParseException
    {
        ResultSet result = this.getReceita(id);

        if (!result.next())
            throw new SQLException("A receita " + id + " não existe");

        String formattedDate = this.formatDate(result.getDate("dt_receita"));

        Receita receita = new Receita(
            result.getInt("cd_receita"),
            result.getInt("t_fp_cliente_cd_cliente"),
            result.getInt("t_fp_tipo_receita_cd_tipo"),
            result.getDouble("vl_receita"),
            formattedDate
        );

        return receita;
    }

    protected ResultSet getReceita(int id) throws SQLException
    {
        String query = """
            SELECT
                cd_receita,
                vl_receita,
                dt_receita,
                t_fp_cliente_cd_cliente,
                t_fp_tipo_receita_cd_tipo

                FROM T_FP_RECEITA
                    WHERE cd_receita = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(query);

        pstmt.setInt(1, id);

        return pstmt.executeQuery();
    }

    public void insert(Receita receita) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ call InserirReceita(?, ?, ?, ?) }");

        cs.setInt(1, receita.getIdTipoReceita());
        cs.setInt(2, receita.getIdCliente());
        cs.setDouble(3, receita.getValor());
        cs.setDate(4, new java.sql.Date(receita.getDataReceita().getTime()));
        cs.execute();
    }

    public void update(Receita receita) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ call AtualizarReceita(?, ?, ?, ?) }");

        cs.setInt(1, receita.getId());
        cs.setInt(2, receita.getIdTipoReceita());
        cs.setDouble(3, receita.getValor());
        cs.setDate(4, new java.sql.Date(receita.getDataReceita().getTime()));
        cs.execute();
    }

    public void delete(int id) throws SQLException, ParseException
    {
        Receita receita = this.getById(id);

        String deleteQuery = """
            DELETE FROM T_FP_RECEITA
                WHERE cd_receita = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(deleteQuery);

        pstmt.setInt(1, receita.getId());

        pstmt.executeUpdate();
    }
}