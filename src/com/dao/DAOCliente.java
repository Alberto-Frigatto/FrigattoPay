package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Endereco;
import com.entity.Telefone;

public abstract class DAOCliente extends DAO
{
    public DAOCliente(Connection conn)
    {
        super(conn);
    }

    protected abstract ResultSet getClientes() throws SQLException;
    protected abstract ResultSet getCliente(int id) throws SQLException;

    protected List<Telefone> instantiateTelefones(int idCliente) throws SQLException
    {
        List<Telefone> telefonesList = new ArrayList<Telefone>();

        ResultSet result = this.getTelefones(idCliente);

        while (result.next())
        {
            Telefone telefone = new Telefone(
                result.getInt("cd_telefone"),
                idCliente,
                result.getString("nr_telefone"),
                result.getString("nr_ramal"),
                result.getInt("t_fp_ddd_nr_ddd")
            );

            telefonesList.add(telefone);
        }

        return telefonesList;
    }

    protected ResultSet getTelefones(int idCliente) throws SQLException
    {
        String query = """
            SELECT T.cd_telefone, T.nr_telefone, T.nr_ramal, T.t_fp_ddd_nr_ddd
                FROM T_FP_TELEFONE T, T_FP_CLIENTE C
                    WHERE T.t_fp_cliente_cd_cliente = C.cd_cliente AND
                          C.cd_cliente = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(query);

        pstmt.setInt(1, idCliente);

        return pstmt.executeQuery();
    }

    protected List<Endereco> instantiateEnderecos(int idCliente) throws SQLException
    {
        List<Endereco> enderecosList = new ArrayList<Endereco>();

        ResultSet result = this.getEnderecos(idCliente);

        while (result.next())
        {
            Endereco endereco = new Endereco(
                result.getInt("cd_endereco"),
                idCliente,
                result.getInt("t_fp_uf_cd_uf"),
                result.getString("nr_cep"),
                result.getString("nm_logradouro"),
                result.getInt("nr_logradouro"),
                result.getString("ds_complemento"),
                result.getString("nm_municipio")
            );

            enderecosList.add(endereco);
        }

        return enderecosList;
    }

    protected ResultSet getEnderecos(int idCliente) throws SQLException
    {
        String query = """
            SELECT
                E.cd_endereco,
                E.nr_cep,
                E.nm_logradouro,
                E.nr_logradouro,
                E.ds_complemento,
                E.nm_municipio,
                E.t_fp_uf_cd_uf

                FROM T_FP_ENDERECO E, T_FP_CLIENTE C
                    WHERE E.t_fp_cliente_cd_cliente = C.cd_cliente AND
                          C.cd_cliente = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(query);

        pstmt.setInt(1, idCliente);

        return pstmt.executeQuery();
    }

    protected void insertTelefones(List<Telefone> list, int idCliente) throws SQLException
    {
        for (Telefone telefone : list)
        {
            CallableStatement cs = conn.prepareCall("{ call InserirTelefoneCliente(?, ?, ?, ?) }");

            cs.setInt(1, idCliente);
            cs.setString(2, telefone.getNumero());
            cs.setString(3, telefone.getRamal());
            cs.setInt(4, telefone.getDdd());
            cs.execute();
        }
    }

    protected void insertEnderecos(List<Endereco> list, int idCliente) throws SQLException
    {
        for (Endereco endereco : list)
        {
            CallableStatement cs = conn.prepareCall("{ call InserirEnderecoCliente(?, ?, ?, ?, ?, ?, ?) }");

            cs.setInt(1, idCliente);
            cs.setString(2, endereco.getCep());
            cs.setString(3, endereco.getLogradouro());
            cs.setInt(4, endereco.getNumero());
            cs.setString(5, endereco.getComplemento());
            cs.setString(6, endereco.getMunicipio());
            cs.setInt(7, endereco.getIdUF());
            cs.execute();
        }
    }
}
