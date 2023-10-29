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
                    ORDER BY cd_despesa
        """;

        return stmt.executeQuery(query);        
    }

    public Despesa getById(int id) throws SQLException, ParseException
    {
        ResultSet result = this.getDespesa(id);

        if (!result.next())
            throw new SQLException("A despesa " + id + " não existe");

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

        return despesa;
    }

    private ResultSet getDespesa(int id) throws SQLException
    {
        String query = """
            SELECT
                cd_despesa,
                nm_despesa,
                vl_despesa,
                ds_despesa,
                dt_vencimento,
                t_fp_cliente_cd_cliente,
                t_fp_tipo_despesa_cd_tipo

                FROM T_FP_DESPESA
                    WHERE cd_despesa = ?
                    ORDER BY cd_despesa
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(query);

        pstmt.setInt(1, id);

        return pstmt.executeQuery();
    }

    public void insert(Despesa despesa) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ call InserirDespesa(?, ?, ?, ?, ?, ?) }");

        cs.setString(1, despesa.getNome());
        cs.setDouble(2, despesa.getValor());
        cs.setString(3, despesa.getDescricao());
        cs.setDate(4, new java.sql.Date(despesa.getDataVencimento().getTime()));
        cs.setInt(5, despesa.getIdTipoDespesa());
        cs.setInt(6, despesa.getIdCliente());
        cs.execute();
    }

    public void update(Despesa despesa) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ call AtualizarDespesa(?, ?, ?, ?, ?, ?) }");

        cs.setInt(1, despesa.getId());
        cs.setString(2, despesa.getNome());
        cs.setDouble(3, despesa.getValor());
        cs.setString(4, despesa.getDescricao());
        cs.setDate(5, new java.sql.Date(despesa.getDataVencimento().getTime()));
        cs.setInt(6, despesa.getIdTipoDespesa());
        cs.execute();
    }

    public void delete(int id) throws SQLException, ParseException
    {
        Despesa despesa = this.getById(id);

        String deleteQuery = """
            DELETE FROM T_FP_DESPESA
                WHERE cd_despesa = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(deleteQuery);

        pstmt.setInt(1, despesa.getId());

        pstmt.executeUpdate();
    }
}