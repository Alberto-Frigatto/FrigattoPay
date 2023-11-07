package com.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.entity.conta.ContaInvestimento;
import com.model.entity.conta.exceptions.ContaInvestimentoExceptions.ContaInvestimentoException;

public class DAOContaInvestimento extends DAO
{
    public DAOContaInvestimento(Connection conn)
    {
        super(conn);
    }

    public List<ContaInvestimento> getAll() throws SQLException, ContaInvestimentoException
    {
        List<ContaInvestimento> contaInvestimentos = new ArrayList<ContaInvestimento>();

        ResultSet result = this.getContaInvestimento();

        while (result.next())
        {
            String formattedDate = this.formatCompleteDate(result.getDate("dt_invs"));

            ContaInvestimento contaInvestimento = new ContaInvestimento(
                result.getInt("cd_conta_invs"),
                result.getInt("t_fp_invs_cd_invs"),
                result.getInt("t_fp_conta_cd_conta"),
                formattedDate,
                result.getDouble("vl_invs")
            );

            contaInvestimentos.add(contaInvestimento);
        }

        return contaInvestimentos;
    }

    private ResultSet getContaInvestimento() throws SQLException
    {
        Statement stmt = this.conn.createStatement();

        String query = """
            SELECT
                cd_conta_invs,
                dt_invs,
                vl_invs,
                t_fp_invs_cd_invs,
                t_fp_conta_cd_conta

                FROM t_fp_conta_invs
                    ORDER BY cd_conta_invs
        """;

        return stmt.executeQuery(query);
    }

    public ContaInvestimento getById(int id) throws SQLException, ContaInvestimentoException
    {
        ResultSet result = this.getContaInvestimento(id);

        if (!result.next())
            throw new SQLException("A conta Investimento " + id + " não existe");

        String formattedDate = this.formatCompleteDate(result.getDate("dt_invs"));

        ContaInvestimento contaInvestimento = new ContaInvestimento(
                result.getInt("cd_conta_invs"),
                result.getInt("t_fp_invs_cd_invs"),
                result.getInt("t_fp_conta_cd_conta"),
                formattedDate,
                result.getDouble("vl_invs")
            );

        return contaInvestimento;
    }

    private ResultSet getContaInvestimento(int id) throws SQLException
    {
        String query = """
            SELECT
                cd_conta_invs,
                dt_invs,
                vl_invs,
                t_fp_invs_cd_invs,
                t_fp_conta_cd_conta

                FROM t_fp_conta_invs
                    WHERE cd_conta_invs = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(query);

        pstmt.setInt(1, id);

        return pstmt.executeQuery();
    }

    public void insert(ContaInvestimento contaInvestimento) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ call InserirContaInvestimento(?, ?, ?, ?) }");

        cs.setDate(1, new java.sql.Date(contaInvestimento.getDataInvestimento().getTime()));
        cs.setDouble(2, contaInvestimento.getValor());
        cs.setInt(3, contaInvestimento.getIdInvestimento());
        cs.setInt(4, contaInvestimento.getIdConta());
        cs.execute();
    }

    public void update(ContaInvestimento contaInvestimento) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ call AtualizarContaInvestimento(?, ?) }");

        cs.setInt(1, contaInvestimento.getId());
        cs.setDouble(2, contaInvestimento.getValor());
        cs.execute();
    }

    public void delete(int id) throws SQLException, ContaInvestimentoException
    {
        ContaInvestimento contaInvestimento = this.getById(id);

        String deleteQuery = """
            DELETE FROM t_fp_conta_invs
                WHERE cd_conta_invs = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(deleteQuery);

        pstmt.setInt(1, contaInvestimento.getId());

        pstmt.executeUpdate();
    }
}