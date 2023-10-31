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

import com.entity.ParcelaEmprestimo;

public class DAOParcelaEmprestimo extends DAO
{
    public DAOParcelaEmprestimo(Connection conn)
    {
        super(conn);
    }

    public List<ParcelaEmprestimo> getAll() throws SQLException, ParseException
    {
        List<ParcelaEmprestimo> parcelas = new ArrayList<ParcelaEmprestimo>();
        
        ResultSet result = this.getParcelas();

        while (result.next())
        {
            String formattedDate = this.formatCompleteDate(result.getDate("dt_pagamento"));

            ParcelaEmprestimo parcela = new ParcelaEmprestimo(
                result.getInt("cd_parcela"),
                result.getInt("t_fp_empr_cd_empr"),
                result.getDouble("vl_pago"),
                formattedDate,
                result.getDouble("vl_juros")
            );

            parcelas.add(parcela);
        }

        return parcelas;
    }

    private ResultSet getParcelas() throws SQLException
    {
        Statement stmt = this.conn.createStatement();

        String query = """
            SELECT
                cd_parcela, 
                t_fp_empr_cd_empr, 
                vl_pago, 
                dt_pagamento, 
                vl_juros

                FROM T_FP_PARCELA_EMPR
                    ORDER BY cd_parcela
        """;

        return stmt.executeQuery(query);
    }

    public ParcelaEmprestimo getById(int id) throws SQLException, ParseException
    {
        ResultSet result = this.getTransacao(id);

        if (!result.next())
            throw new SQLException("A Parcela " + id + " não existe");

        String formattedDate = this.formatCompleteDate(result.getDate("dt_pagamento"));

        ParcelaEmprestimo parcela = new ParcelaEmprestimo(
            result.getInt("cd_parcela"),
            result.getInt("t_fp_empr_cd_empr"),
            result.getDouble("vl_pago"),
            formattedDate,
            result.getDouble("vl_juros")
        );

        return parcela;
    }

    private ResultSet getTransacao(int id) throws SQLException
    {
        String query = """
            SELECT
                cd_parcela, 
                t_fp_empr_cd_empr, 
                vl_pago, 
                dt_pagamento, 
                vl_juros

                FROM T_FP_PARCELA_EMPR
                    WHERE cd_parcela = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(query);

        pstmt.setInt(1, id);

        return pstmt.executeQuery();
    }

    public void insert(ParcelaEmprestimo parcela) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ call InserirParcelaEmprestimo(?, ?, ?, ?) }");

        cs.setInt(1, parcela.getIdEmprestimo());
        cs.setDouble(2, parcela.getValor());
        cs.setDate(3, new java.sql.Date(parcela.getDataPagamento().getTime()));
        cs.setDouble(4, parcela.getJuros());
        cs.execute();
    }

    public void delete(int id) throws SQLException, ParseException
    {
        ParcelaEmprestimo parcela = this.getById(id);

        String deleteQuery = """
            DELETE FROM T_FP_PARCELA_EMPR
                WHERE cd_parcela = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(deleteQuery);

        pstmt.setInt(1, parcela.getId());

        pstmt.executeUpdate();
    }
}
