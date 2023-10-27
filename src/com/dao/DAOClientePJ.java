package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Endereco;
import com.entity.Telefone;
import com.entity.cliente.ClientePJ;

public class DAOClientePJ extends DAO
{
    public DAOClientePJ(Connection conn)
    {
        super(conn);
    }

    public List<ClientePJ> getAll() throws SQLException, ParseException
    {
        List<ClientePJ> clientesList = new ArrayList<ClientePJ>();

        ResultSet result = this.getClientes();

        while (result.next())
        {
            String formattedDate = this.formatDate(result.getDate("dt_abertura"));

            ClientePJ clientePJ = new ClientePJ(
                result.getInt("cd_cliente"),
                result.getString("nm_cliente"),
                result.getString("ds_email"),
                result.getString("ds_senha"),
                result.getString("nr_cnpj"),
                result.getString("nr_inscricao_estadual"),
                result.getString("dt_abertura"),
                formattedDate
            );

            int idCliente = clientePJ.getId();

            List<Telefone> telefones = this.instantiateTelefones(idCliente);
            List<Endereco> enderecos = this.instantiateEnderecos(idCliente);

            clientePJ.addTelefone(telefones);
            clientePJ.addEndereco(enderecos);

            clientesList.add(clientePJ);
        }

        return clientesList;
    }

    private ResultSet getClientes() throws SQLException
    {
        Statement stmt = this.conn.createStatement();

        String query = """
            SELECT C.cd_cliente, C.nm_cliente, C.ds_email, C.ds_senha, PJ.nr_cnpj, PJ.nr_inscricao_estadual, PJ.dt_abertura, PJ.ds_setor 
                FROM T_FP_CLIENTE C, T_FP_CLIENTE_PJ PJ
                    WHERE C.cd_cliente = PJ.t_fp_cliente_cd_cliente
        """;

        return stmt.executeQuery(query);
    }

    public ClientePJ getById(int id) throws SQLException, ParseException
    {
        ResultSet result = this.getCliente(id);

        result.next();

        String formattedDate = this.formatDate(result.getDate("dt_abertura"));

        ClientePJ clientePJ = new ClientePJ(
            result.getInt("cd_cliente"),
            result.getString("nm_cliente"),
            result.getString("ds_email"),
            result.getString("ds_senha"),
            result.getString("nr_cnpj"), 
            result.getString("nr_inscricao_estadual"), 
            formattedDate,
            result.getString("ds_setor")
        );

        int idCliente = clientePJ.getId();

        List<Telefone> telefones = this.instantiateTelefones(idCliente);
        List<Endereco> enderecos = this.instantiateEnderecos(idCliente);

        clientePJ.addTelefone(telefones);
        clientePJ.addEndereco(enderecos);

        return clientePJ;
    }

    private ResultSet getCliente(int id) throws SQLException
    {
        String query = """
            SELECT C.cd_cliente, C.nm_cliente, C.ds_email, C.ds_senha, PJ.nr_cnpj, PJ.nr_inscricao_estadual, PJ.dt_abertura, PJ.ds_setor 
                FROM T_FP_CLIENTE C, T_FP_CLIENTE_PJ PJ
                    WHERE C.cd_cliente = PJ.t_fp_cliente_cd_cliente AND
                            C.cd_cliente = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(query);

        pstmt.setInt(1, id);

        return pstmt.executeQuery();
    }

    private List<Telefone> instantiateTelefones(int idCliente) throws SQLException
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
                result.getInt("t_fp_ddd_nr_ddd"),
                result.getInt("t_fp_tipo_tel_cd_tipo")
            );

            telefonesList.add(telefone);
        }

        return telefonesList;
    }

    private ResultSet getTelefones(int idCliente) throws SQLException
    {
        String query = """
            SELECT T.cd_telefone, T.nr_telefone, T.nr_ramal, T.t_fp_ddd_nr_ddd, T.t_fp_tipo_tel_cd_tipo
                FROM T_FP_TELEFONE T, T_FP_REL_TEL R, T_FP_CLIENTE C
                    WHERE C.cd_cliente = R.t_fp_cliente_cd_cliente AND
                          T.cd_telefone = R.t_fp_telefone_cd_telefone AND C.cd_cliente = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(query);

        pstmt.setInt(1, idCliente);

        return pstmt.executeQuery();
    }

    private List<Endereco> instantiateEnderecos(int idCliente) throws SQLException
    {
        List<Endereco> enderecosList = new ArrayList<Endereco>();

        ResultSet result = this.getEnderecos(idCliente);

        while (result.next())
        {
            Endereco endereco = new Endereco(
                result.getInt("cd_endereco"),
                idCliente,
                result.getString("nr_cep"),
                result.getString("nm_logradouro"),
                result.getInt("nr_logradouro"),
                result.getString("ds_complemento"),
                result.getInt("t_fp_tipo_lograd_cd_tipo"),
                result.getInt("t_fp_bairro_cd_bairro")
            );

            enderecosList.add(endereco);
        }

        return enderecosList;
    }

    private ResultSet getEnderecos(int idCliente) throws SQLException
    {
        String query = """
            SELECT
                E.cd_endereco,
                E.nr_cep,
                E.nm_logradouro,
                E.nr_logradouro,
                E.ds_complemento,
                E.t_fp_tipo_lograd_cd_tipo,
                E.t_fp_bairro_cd_bairro

                FROM T_FP_ENDERECO E, T_FP_REL_END R, T_FP_CLIENTE C
                    WHERE C.cd_cliente = R.t_fp_cliente_cd_cliente AND
                          E.cd_endereco = R.t_fp_endereco_cd_endereco AND C.cd_cliente = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(query);

        pstmt.setInt(1, idCliente);

        return pstmt.executeQuery();
    }

    public void insert(ClientePJ pj) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ ? = call CadastrarClientePJ(?, ?, ?, ?, ?, ?, ?) }");

        cs.registerOutParameter(1, Types.NUMERIC);
        cs.setString(2, pj.getNome());
        cs.setString(3, pj.getEmail());
        cs.setString(4, pj.getSenha());
        cs.setString(5, pj.getCnpj());
        cs.setString(6, pj.getInscricaoEstadual());
        cs.setDate(7, new java.sql.Date(pj.getDataAbertura().getTime()));
        cs.setString(8, pj.getSetor());
        cs.execute();

        int idCliente = cs.getInt(1);

        this.insertTelefones(pj.getTelefones(), idCliente);
        this.insertEnderecos(pj.getEnderecos(), idCliente);
    }

    private void insertTelefones(List<Telefone> list, int idCliente) throws SQLException
    {
        for (Telefone telefone : list)
        {
            CallableStatement cs = conn.prepareCall("{ call InserirTelefoneCliente(?, ?, ?, ?, ?) }");

            cs.setInt(1, idCliente);
            cs.setString(2, telefone.getNumero());
            cs.setString(3, telefone.getRamal());
            cs.setInt(4, telefone.getDdd());
            cs.setInt(5, telefone.getIdTipoTelefone());
            cs.execute();
        }
    }

    private void insertEnderecos(List<Endereco> list, int idCliente) throws SQLException
    {
        for (Endereco endereco : list)
        {
            CallableStatement cs = conn.prepareCall("{ call InserirEnderecoCliente(?, ?, ?, ?, ?, ?, ?) }");

            cs.setInt(1, idCliente);
            cs.setString(2, endereco.getCep());
            cs.setString(3, endereco.getLogradouro());
            cs.setInt(4, endereco.getNumero());
            cs.setString(5, endereco.getComplemento());
            cs.setInt(6, endereco.getIdTipoLogradouro());
            cs.setInt(7, endereco.getIdBairro());
            cs.execute();
        }
    }

    public void update(ClientePJ pj) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ call AtualizarClientePJ(?, ?, ?, ?, ?, ?, ?, ?) }");

        cs.setInt(1, pj.getId());
        cs.setString(2, pj.getNome());
        cs.setString(3, pj.getEmail());
        cs.setString(4, pj.getSenha());
        cs.setString(5, pj.getCnpj());
        cs.setString(6, pj.getInscricaoEstadual());
        cs.setDate(7, new java.sql.Date(pj.getDataAbertura().getTime()));
        cs.setString(8, pj.getSetor());
        cs.execute();
    }
}