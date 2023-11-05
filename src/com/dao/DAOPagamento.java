package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entity.conta.Pagamento;
import com.entity.conta.exceptions.PagamentoExceptions.PagamentoException;

public class DAOPagamento extends DAO
{
    public DAOPagamento(Connection conn)
    {
        super(conn);
    }

    public List<Pagamento> getAll() throws SQLException, PagamentoException
    {
        List<Pagamento> pagamentos = new ArrayList<Pagamento>();

        ResultSet result = this.getPagamentos();

        while (result.next())
        {
            String formattedDate = this.formatCompleteDate(result.getDate("dt_pagmt"));

            Pagamento pagamento = new Pagamento(
                result.getInt("cd_pagmt"),
                result.getInt("t_fp_conta_cd_conta"),
                result.getInt("t_fp_tipo_pagmt_cd_tipo"),
                result.getString("cd_barras"),
                result.getString("nm_pagmt"),
                formattedDate,
                result.getDouble("vl_pagmt")
            );

            pagamentos.add(pagamento);
        }

        return pagamentos;
    }

    private ResultSet getPagamentos() throws SQLException
    {
        Statement stmt = this.conn.createStatement();

        String query = """
            SELECT
                cd_pagmt,
                t_fp_conta_cd_conta,
                t_fp_tipo_pagmt_cd_tipo,
                cd_barras,
                nm_pagmt,
                dt_pagmt,
                vl_pagmt

                FROM T_FP_PAGMT
                    ORDER BY cd_pagmt
        """;

        return stmt.executeQuery(query);
    }

    public Pagamento getById(int id) throws SQLException, PagamentoException
    {
        ResultSet result = this.getPagamento(id);

        if (!result.next())
            throw new SQLException("O pagamento " + id + " não existe");

        String formattedDate = this.formatCompleteDate(result.getDate("dt_pagmt"));

        Pagamento pagamento = new Pagamento(
            result.getInt("cd_pagmt"),
            result.getInt("t_fp_conta_cd_conta"),
            result.getInt("t_fp_tipo_pagmt_cd_tipo"),
            result.getString("cd_barras"),
            result.getString("nm_pagmt"),
            formattedDate,
            result.getDouble("vl_pagmt")
        );

        return pagamento;
    }

    private ResultSet getPagamento(int id) throws SQLException
    {
        String query = """
            SELECT
                cd_pagmt,
                t_fp_conta_cd_conta,
                t_fp_tipo_pagmt_cd_tipo,
                cd_barras,
                nm_pagmt,
                dt_pagmt,
                vl_pagmt

                FROM T_FP_PAGMT
                    WHERE cd_pagmt = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(query);

        pstmt.setInt(1, id);

        return pstmt.executeQuery();
    }

    public void insert(Pagamento pagamento) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ call InserirPagamento(?, ?, ?, ?, ?, ?) }");

        cs.setInt(1, pagamento.getIdConta());
        cs.setInt(2, pagamento.getIdTipoPagamento());
        cs.setString(3, pagamento.getCodigoBarras());
        cs.setString(4, pagamento.getNome());
        cs.setDate(5, new java.sql.Date(pagamento.getDataPagamento().getTime()));
        cs.setDouble(6, pagamento.getValor());
        cs.execute();
    }

    public void update(Pagamento pagamento) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ call AtualizarPagamento(?, ?, ?) }");

        cs.setInt(1, pagamento.getId());
        cs.setInt(2, pagamento.getIdTipoPagamento());
        cs.setString(3, pagamento.getNome());
        cs.execute();
    }

    public void delete(int id) throws SQLException, PagamentoException
    {
        Pagamento pagamento = this.getById(id);

        String deleteQuery = """
            DELETE FROM T_FP_PAGMT
                WHERE cd_pagmt = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(deleteQuery);

        pstmt.setInt(1, pagamento.getId());

        pstmt.executeUpdate();
    }
}
