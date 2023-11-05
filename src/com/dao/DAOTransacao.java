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

import com.entity.conta.Transacao;

public class DAOTransacao extends DAO
{
    public DAOTransacao(Connection conn)
    {
        super(conn);
    }

    public List<Transacao> getAll() throws SQLException, ParseException
    {
        List<Transacao> transacoes = new ArrayList<Transacao>();

        ResultSet result = this.getTransacoes();

        while (result.next())
        {
            String formattedDate = this.formatCompleteDate(result.getDate("dt_transc"));

            Transacao transacao = new Transacao(
                result.getInt("cd_transc"),
                result.getInt("t_fp_conta_cd_conta"),
                result.getInt("t_fp_conta_cd_conta2") != 0 ? result.getInt("t_fp_conta_cd_conta2") : null,
                result.getInt("t_fp_tipo_transc_cd_tipo"),
                result.getDouble("vl_transc"),
                formattedDate
            );

            transacoes.add(transacao);
        }

        return transacoes;
    }

    private ResultSet getTransacoes() throws SQLException
    {
        Statement stmt = this.conn.createStatement();

        String query = """
            SELECT
                cd_transc,
                t_fp_conta_cd_conta,
                t_fp_conta_cd_conta2,
                t_fp_tipo_transc_cd_tipo,
                vl_transc,
                dt_transc

                FROM T_FP_TRANSC
                    ORDER BY cd_transc
        """;

        return stmt.executeQuery(query);
    }

    public Transacao getById(int id) throws SQLException, ParseException
    {
        ResultSet result = this.getTransacao(id);

        if (!result.next())
            throw new SQLException("A Transação " + id + " não existe");

        String formattedDate = this.formatCompleteDate(result.getDate("dt_transc"));

        Transacao transacao = new Transacao(
            result.getInt("cd_transc"),
            result.getInt("t_fp_conta_cd_conta"),
            result.getInt("t_fp_conta_cd_conta2") != 0 ? result.getInt("t_fp_conta_cd_conta2") : null,
            result.getInt("t_fp_tipo_transc_cd_tipo"),
            result.getDouble("vl_transc"),
            formattedDate
        );

        return transacao;
    }

    private ResultSet getTransacao(int id) throws SQLException
    {
        String query = """
            SELECT
                cd_transc,
                t_fp_conta_cd_conta,
                t_fp_conta_cd_conta2,
                t_fp_tipo_transc_cd_tipo,
                vl_transc,
                dt_transc

                FROM T_FP_TRANSC
                    WHERE cd_transc = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(query);

        pstmt.setInt(1, id);

        return pstmt.executeQuery();
    }

    public void insert(Transacao transacao) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ call InserirTransacao(?, ?, ?, ?, ?) }");

        cs.setInt(1, transacao.getIdContaOrigem());

        if (transacao.getIdContaDestino() != null)
            cs.setInt(2, transacao.getIdContaDestino());
        else
            cs.setNull(2, java.sql.Types.NULL);

        cs.setInt(3, transacao.getIdTipoTransacao());
        cs.setDouble(4, transacao.getValor());
        cs.setDate(5, new java.sql.Date(transacao.getDataTransacao().getTime()));
        cs.execute();
    }

    public void delete(int id) throws SQLException, ParseException
    {
        Transacao transacao = this.getById(id);

        String deleteQuery = """
            DELETE FROM T_FP_TRANSC
                WHERE cd_transc = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(deleteQuery);

        pstmt.setInt(1, transacao.getId());

        pstmt.executeUpdate();
    }
}
