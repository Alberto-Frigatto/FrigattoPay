package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entity.TipoConta;

public class DAOTipoConta extends DAO
{ 
    public DAOTipoConta(Connection conn)
    {
        super(conn);
    }

    public List<TipoConta> getAll() throws SQLException
    {
        List<TipoConta> tiposContaList = new ArrayList<TipoConta>();
        
        ResultSet result = this.getTiposConta();

        while (result.next())
        {
            TipoConta tipoConta = new TipoConta(
                result.getInt("cd_tipo"),
                result.getString("nm_tipo")
            );

            tiposContaList.add(tipoConta);
        }

        return tiposContaList;
    }

    private ResultSet getTiposConta() throws SQLException
    {
        Statement stmt = this.conn.createStatement();

        String query = """
            SELECT
                cd_tipo,
                nm_tipo

                FROM T_FP_TIPO_CONTA
                    ORDER BY cd_tipo
        """;

        return stmt.executeQuery(query);        
    }

    public TipoConta getById(int id) throws SQLException
    {
        ResultSet result = this.getTipoConta(id);

        if (!result.next())
            throw new SQLException("O tipo " + id + " não existe");

        TipoConta tipoConta = new TipoConta(
            result.getInt("cd_tipo"),
            result.getString("nm_tipo")
        );

        return tipoConta;
    }

    private ResultSet getTipoConta(int id) throws SQLException
    {
        String query = """
            SELECT
                cd_tipo,
                nm_tipo

                FROM T_FP_TIPO_CONTA
                    WHERE cd_tipo = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(query);

        pstmt.setInt(1, id);

        return pstmt.executeQuery();
    }

    public void insert(TipoConta tipoConta) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ call InserirTipoConta(?) }");

        cs.setString(1, tipoConta.getNome());
        cs.execute();
    }

    public void update(TipoConta tipoConta) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ call AtualizarTipoConta(?, ?) }");

        cs.setInt(1, tipoConta.getId());
        cs.setString(2, tipoConta.getNome());
        cs.execute();
    }

    public void delete(int id) throws SQLException
    {
        TipoConta tipoConta = this.getById(id);

        String deleteQuery = """
            DELETE FROM T_FP_TIPO_CONTA
                WHERE cd_tipo = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(deleteQuery);

        pstmt.setInt(1, tipoConta.getId());

        pstmt.executeUpdate();
    }
}