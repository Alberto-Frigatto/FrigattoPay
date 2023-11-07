package com.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.entity.telefone.Telefone;
import com.model.entity.telefone.TelefoneExceptions.TelefoneException;

public class DAOTelefone extends DAO
{

    public DAOTelefone(Connection conn)
    {
        super(conn);
    }

    public List<Telefone> getAll() throws SQLException, TelefoneException
    {
        List<Telefone> telefones = new ArrayList<Telefone>();
        
        ResultSet result = this.getTelefones();

        while (result.next())
        {
            Telefone telefone = new Telefone(
                result.getInt("cd_telefone"),
                result.getInt("t_fp_cliente_cd_cliente"),
                result.getString("nr_telefone"),
                result.getString("nr_ramal"),
                result.getInt("t_fp_ddd_nr_ddd")
            );

            telefones.add(telefone);
        }

        return telefones;
    }

    private ResultSet getTelefones() throws SQLException
    {
        Statement stmt = this.conn.createStatement();

        String query = """
                SELECT
                    cd_telefone,
                    t_fp_cliente_cd_cliente,
                    nr_telefone,
                    nr_ramal,
                    t_fp_ddd_nr_ddd

                    FROM T_FP_TELEFONE
                        ORDER BY cd_telefone
                """;

        return stmt.executeQuery(query);
    }

    public Telefone getById(int id) throws SQLException, TelefoneException
    {
        ResultSet result = this.getTelefone(id);

        if (!result.next())
            throw new SQLException("O telefone " + id + " não existe");

        Telefone telefone = new Telefone(
                result.getInt("cd_telefone"),
                result.getInt("t_fp_cliente_cd_cliente"),
                result.getString("nr_telefone"),
                result.getString("nr_ramal"),
                result.getInt("t_fp_ddd_nr_ddd")
            );

        return telefone;
    }

    private ResultSet getTelefone(int id) throws SQLException
    {
        String query = """
            SELECT
                cd_telefone,
                nr_telefone,
                nr_ramal,
                t_fp_ddd_nr_ddd,
                t_fp_cliente_cd_cliente

                FROM T_FP_TELEFONE
                    WHERE cd_telefone = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(query);

        pstmt.setInt(1, id);

        return pstmt.executeQuery();
    }

    public void insert(Telefone telefone) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ call InserirTelefoneCliente(?, ?, ?, ?) }");

        cs.setInt(1, telefone.getIdCliente());
        cs.setString(2, telefone.getNumero());
        cs.setString(3, telefone.getRamal());
        cs.setInt(4, telefone.getDdd());
        cs.execute();
    }

    public void update(Telefone telefone) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ call AtualizarTelefoneCliente(?, ?, ?, ?, ?) }");

        cs.setInt(1, telefone.getId());
        cs.setInt(2, telefone.getIdCliente());
        cs.setString(3, telefone.getNumero());
        cs.setString(4, telefone.getRamal());
        cs.setInt(5, telefone.getDdd());
        cs.execute();
    }

    public void delete(int id) throws SQLException, TelefoneException
    {
        Telefone telefone = this.getById(id);

        String deleteQuery = """
            DELETE FROM T_FP_TELEFONE
                WHERE cd_telefone = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(deleteQuery);

        pstmt.setInt(1, telefone.getId());

        pstmt.executeUpdate();
    }
}
