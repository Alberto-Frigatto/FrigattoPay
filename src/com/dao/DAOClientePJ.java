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

public class DAOClientePJ extends DAOCliente
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

    protected ResultSet getClientes() throws SQLException
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

    protected ResultSet getCliente(int id) throws SQLException
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