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

import com.entity.Emprestimo;

public class DAOEmprestimo extends DAO
{ 
    public DAOEmprestimo(Connection conn)
    {
        super(conn);
    }

    public List<Emprestimo> getAll() throws SQLException, ParseException
    {
        List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
        
        ResultSet result = this.getEmprestimos();

        while (result.next())
        {
            String formattedDatePrazo = this.formatCompleteDate(result.getDate("dt_prazo"));
            String formattedDateSolicitacao = this.formatCompleteDate(result.getDate("dt_solicitacao"));

            Emprestimo emprestimo = new Emprestimo(
                result.getInt("cd_empr"),
                result.getInt("t_fp_conta_cd_conta"),
                result.getDouble("vl_empr"),
                result.getDouble("vl_juros"),
                formattedDatePrazo,
                formattedDateSolicitacao,
                result.getDouble("vl_parcela"),
                result.getInt("dia_vencimento_parcela")
            );

            emprestimos.add(emprestimo);
        }

        return emprestimos;
    }

    private ResultSet getEmprestimos() throws SQLException
    {
        Statement stmt = this.conn.createStatement();

        String query = """
            SELECT
                cd_empr,
                t_fp_conta_cd_conta,
                vl_empr,
                vl_juros,
                dt_prazo,
                dt_solicitacao,
                vl_parcela,
                dia_vencimento_parcela

                FROM T_FP_EMPR
                    ORDER BY cd_empr
        """;

        return stmt.executeQuery(query);        
    }

    public Emprestimo getById(int id) throws SQLException, ParseException
    {
        ResultSet result = this.getEmprestimo(id);

        if (!result.next())
            throw new SQLException("O emprestimo " + id + " não existe");

        String formattedDatePrazo = this.formatCompleteDate(result.getDate("dt_prazo"));
        String formattedDateSolicitacao = this.formatCompleteDate(result.getDate("dt_solicitacao"));

        Emprestimo emprestimo = new Emprestimo(
            result.getInt("cd_empr"),
            result.getInt("t_fp_conta_cd_conta"),
            result.getDouble("vl_empr"),
            result.getDouble("vl_juros"),
            formattedDatePrazo,
            formattedDateSolicitacao,
            result.getDouble("vl_parcela"),
            result.getInt("dia_vencimento_parcela")
        );

        return emprestimo;
    }

    private ResultSet getEmprestimo(int id) throws SQLException
    {
        String query = """
            SELECT
                cd_empr,
                t_fp_conta_cd_conta,
                vl_empr,
                vl_juros,
                dt_prazo,
                dt_solicitacao,
                vl_parcela,
                dia_vencimento_parcela

                FROM T_FP_EMPR
                    WHERE cd_empr = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(query);

        pstmt.setInt(1, id);

        return pstmt.executeQuery();
    }

    public void insert(Emprestimo emprestimo) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ call InserirEmprestimo(?, ?, ?, ?, ?, ?, ?) }");

        cs.setInt(1, emprestimo.getIdConta());
        cs.setDouble(2, emprestimo.getValorEmprestimo());
        cs.setDouble(3, emprestimo.getValorJuros());
        cs.setDate(4, new java.sql.Date(emprestimo.getDataPrazo().getTime()));
        cs.setDate(5, new java.sql.Date(emprestimo.getDataSolicitacao().getTime()));
        cs.setDouble(6, emprestimo.getValorParcela());
        cs.setInt(7, emprestimo.getDiaVencimentoParcela());
        cs.execute();
    }

    public void delete(int id) throws SQLException, ParseException
    {
        Emprestimo emprestimo = this.getById(id);

        String deleteQuery = """
            DELETE FROM T_FP_EMPR
                WHERE cd_empr = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(deleteQuery);

        pstmt.setInt(1, emprestimo.getId());

        pstmt.executeUpdate();
    }
}