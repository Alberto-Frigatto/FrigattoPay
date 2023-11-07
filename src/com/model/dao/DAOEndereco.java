package com.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.entity.endereco.Endereco;
import com.model.entity.endereco.EnderecoExceptions.EnderecoException;

public class DAOEndereco extends DAO
{
    public DAOEndereco(Connection conn)
    {
        super(conn);
    }

    public List<Endereco> getAll() throws SQLException, EnderecoException
    {
        List<Endereco> enderecos = new ArrayList<Endereco>();
        
        ResultSet result = this.getEnderecos();

        while (result.next())
        {
            Endereco endereco = new Endereco(
                result.getInt("cd_endereco"),
                result.getInt("t_fp_cliente_cd_cliente"),
                result.getInt("t_fp_uf_cd_uf"),
                result.getString("nr_cep"),
                result.getString("nm_logradouro"),
                result.getInt("nr_logradouro"),
                result.getString("ds_complemento"),
                result.getString("nm_municipio")
            );

            enderecos.add(endereco);
        }

        return enderecos;
    }

    protected ResultSet getEnderecos() throws SQLException
    {
        Statement stmt = this.conn.createStatement();

        String query = """
            SELECT
                cd_endereco,
                nr_cep,
                nm_logradouro,
                nr_logradouro,
                ds_complemento,
                nm_municipio,
                t_fp_cliente_cd_cliente,
                t_fp_uf_cd_uf

                FROM T_FP_ENDERECO
                    ORDER BY cd_endereco
        """;

        return stmt.executeQuery(query);
    }

    public Endereco getById(int id) throws SQLException, EnderecoException
    {
        ResultSet result = this.getEndereco(id);

        if (!result.next())
            throw new SQLException("O Endereço " + id + " não existe");

        Endereco endereco = new Endereco(
            result.getInt("cd_endereco"),
            result.getInt("t_fp_cliente_cd_cliente"),
            result.getInt("t_fp_uf_cd_uf"),
            result.getString("nr_cep"),
            result.getString("nm_logradouro"),
            result.getInt("nr_logradouro"),
            result.getString("ds_complemento"),
            result.getString("nm_municipio")
        );

        return endereco;
    }

    private ResultSet getEndereco(int id) throws SQLException
    {
        String query = """
            SELECT
                cd_endereco,
                nr_cep,
                nm_logradouro,
                nr_logradouro,
                ds_complemento,
                nm_municipio,
                t_fp_cliente_cd_cliente,
                t_fp_uf_cd_uf

                FROM T_FP_ENDERECO
                    WHERE cd_endereco = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(query);

        pstmt.setInt(1, id);

        return pstmt.executeQuery();
    }

    public void insert(Endereco endereco) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ call InserirEnderecoCliente(?, ?, ?, ?, ?, ?, ?) }");

        cs.setInt(1, endereco.getIdCliente());
        cs.setString(2, endereco.getCep());
        cs.setString(3, endereco.getLogradouro());
        cs.setInt(4, endereco.getNumero());
        cs.setString(5, endereco.getComplemento());
        cs.setString(6, endereco.getMunicipio());
        cs.setInt(7, endereco.getIdUF());
        cs.execute();
    }

    public void update(Endereco endereco) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ call AtualizarEnderecoCliente(?, ?, ?, ?, ?, ?, ?) }");

        cs.setInt(1, endereco.getId());
        cs.setString(2, endereco.getCep());
        cs.setString(3, endereco.getLogradouro());
        cs.setInt(4, endereco.getNumero());
        cs.setString(5, endereco.getComplemento());
        cs.setString(6, endereco.getMunicipio());
        cs.setInt(7, endereco.getIdUF());
        cs.execute();
    }

    public void delete(int id) throws SQLException, EnderecoException
    {
        Endereco endereco = this.getById(id);

        String deleteQuery = """
            DELETE FROM T_FP_ENDERECO
                WHERE cd_endereco = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(deleteQuery);

        pstmt.setInt(1, endereco.getId());

        pstmt.executeUpdate();
    }
}
