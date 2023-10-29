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

import com.entity.Banco;

public class DAOBanco extends DAO
{
    public DAOBanco(Connection conn)
    {
        super(conn);
    }

    public List<Banco> getAll() throws SQLException, ParseException
    {
        List<Banco> bancos = new ArrayList<Banco>();
        
        ResultSet result = this.getBancos();

        while (result.next())
        {
            Banco banco = new Banco(
                result.getInt("cd_banco"),
                result.getString("nr_cnpj"),
                result.getString("nm_banco_parceiro"),
                result.getString("ds_email")
            );

            bancos.add(banco);
        }

        return bancos;
    }

    private ResultSet getBancos() throws SQLException
    {
        Statement stmt = this.conn.createStatement();

        String query = """
            SELECT
                cd_banco,
                nr_cnpj,
                nm_banco_parceiro,
                ds_email

                FROM T_fp_banco
                    ORDER BY cd_banco
        """;

        return stmt.executeQuery(query);
    }

    public Banco getById(int id) throws SQLException, ParseException
    {
        ResultSet result = this.getBanco(id);

        if (!result.next())
            throw new SQLException("O banco " + id + " não existe");

       
        Banco banco = new Banco(
                result.getInt("cd_banco"),
                result.getString("nr_cnpj"),
                result.getString("nm_banco_parceiro"),
                result.getString("ds_email")
            );

        return banco;
    }

    protected ResultSet getBanco(int id) throws SQLException
    {
        String query = """
            SELECT
                cd_banco,
                nr_cnpj,
                nm_banco_parceiro,
                ds_email

                FROM T_fp_banco
                    WHERE cd_banco = ?
                    ORDER BY cd_banco
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(query);

        pstmt.setInt(1, id);

        return pstmt.executeQuery();
    }

    public void insert(Banco banco) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ call InserirBanco(?, ?, ?) }");

        cs.setString(1, banco.getCnpj());
        cs.setString(2, banco.getNome());
        cs.setString(3, banco.getEmail());
        cs.execute();
    }

    public void update(Banco banco) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ call AtualizarBanco(?, ?, ?, ?) }");

        cs.setInt(1, banco.getId());
        cs.setString(2, banco.getCnpj());
        cs.setString(3, banco.getNome());
        cs.setString(4, banco.getEmail());
        cs.execute();
    }

    public void delete(int id) throws SQLException, ParseException
    {
        Banco banco = this.getById(id);

        String deleteQuery = """
            DELETE FROM T_fp_banco
                WHERE cd_banco = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(deleteQuery);

        pstmt.setInt(1, banco.getId());

        pstmt.executeUpdate();
    }
}