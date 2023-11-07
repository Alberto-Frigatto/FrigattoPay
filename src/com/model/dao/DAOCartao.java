package com.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.model.entity.conta.Cartao;
import com.model.entity.conta.exceptions.CartaoExceptions.CartaoException;

public class DAOCartao extends DAO
{
    public DAOCartao(Connection conn)
    {
        super(conn);
    }

    public List<Cartao> getAll() throws SQLException, ParseException, CartaoException
    {
        List<Cartao> cartoes = new ArrayList<Cartao>();
        
        ResultSet result = this.getCartoes();

        while (result.next())
        {
            String formattedDate = this.formatMonthYearDate(result.getDate("dt_validade"));

            boolean desbloqueadoBoolean = result.getInt("vl_cartao_desbloqueado") == 1;

            Cartao cartao = new Cartao(
                result.getInt("cd_cartao"),
                result.getInt("t_fp_conta_cd_conta"),
                result.getInt("t_fp_bandeira_cd_bandeira"),
                result.getInt("t_fp_tipo_cartao_cd_tipo"),
                result.getString("nr_cartao"),
                formattedDate,
                result.getString("nr_cvv"),
                desbloqueadoBoolean
            );

            cartoes.add(cartao);
        }

        return cartoes;
    }

    private ResultSet getCartoes() throws SQLException
    {
        Statement stmt = this.conn.createStatement();

        String query = """
            SELECT
                cd_cartao,
                nr_cartao, 
                t_fp_conta_cd_conta, 
                t_fp_bandeira_cd_bandeira, 
                t_fp_tipo_cartao_cd_tipo, 
                dt_validade, 
                nr_cvv, 
                vl_cartao_desbloqueado

                FROM T_FP_CARTAO
                    ORDER BY cd_cartao
        """;

        return stmt.executeQuery(query);
    }

    public Cartao getById(int id) throws SQLException, ParseException, CartaoException
    {
        ResultSet result = this.getCartao(id);

        if (!result.next())
            throw new SQLException("O cartão " + id + " não existe");

        String formattedDate = this.formatMonthYearDate(result.getDate("dt_validade"));

        boolean desbloqueadoBoolean = result.getInt("vl_cartao_desbloqueado") != 0;

        Cartao cartao = new Cartao(
            result.getInt("cd_cartao"),
            result.getInt("t_fp_conta_cd_conta"),
            result.getInt("t_fp_bandeira_cd_bandeira"),
            result.getInt("t_fp_tipo_cartao_cd_tipo"),
            result.getString("nr_cartao"),
            formattedDate,
            result.getString("nr_cvv"),
            desbloqueadoBoolean
        );

        return cartao;
    }

    private ResultSet getCartao(int id) throws SQLException
    {
        String query = """
            SELECT
                cd_cartao,
                nr_cartao,
                t_fp_conta_cd_conta, 
                t_fp_bandeira_cd_bandeira, 
                t_fp_tipo_cartao_cd_tipo, 
                dt_validade, 
                nr_cvv, 
                vl_cartao_desbloqueado

                FROM T_FP_CARTAO
                    WHERE cd_cartao = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(query);

        pstmt.setInt(1, id);

        return pstmt.executeQuery();
    }

    public void insert(Cartao cartao) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ call InserirCartao(?, ?, ?, ?, ?, ?, ?) }");

        int desbloqueadoNumeric = cartao.getDesbloqueado() ? 1 : 0;

        cs.setString(1, cartao.getNumero());
        cs.setInt(2, cartao.getIdConta());
        cs.setInt(3, cartao.getIdBandeira());
        cs.setInt(4, cartao.getIdTipoCartao());
        cs.setDate(5, new java.sql.Date(cartao.getDataValidade().getTime()));
        cs.setString(6, cartao.getCvv());
        cs.setInt(7, desbloqueadoNumeric);
        cs.execute();
    }

    public void update(Cartao cartao) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ call AtualizarCartao(?, ?) }");

        int desbloqueadoNumeric = cartao.getDesbloqueado() ? 1 : 0;

        cs.setInt(1, cartao.getId());
        cs.setInt(2, desbloqueadoNumeric);
        cs.execute();
    }

    public void delete(int id) throws SQLException, ParseException, CartaoException
    {
        Cartao cartao = this.getById(id);

        String deleteQuery = """
            DELETE FROM T_FP_CARTAO
                WHERE cd_cartao = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(deleteQuery);

        pstmt.setInt(1, cartao.getId());

        pstmt.executeUpdate();
    }
}
