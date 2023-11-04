package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.entity.cliente.ClientePJ;
import com.entity.cliente.exceptions.ClienteExceptions.ClienteException;
import com.entity.endereco.Endereco;
import com.entity.endereco.EnderecoExceptions.EnderecoException;
import com.entity.telefone.Telefone;
import com.entity.telefone.TelefoneExceptions.TelefoneException;

public class DAOClientePJ extends DAOCliente
{
    public DAOClientePJ(Connection conn)
    {
        super(conn);
    }

    public List<ClientePJ> getAll() throws SQLException, ClienteException, TelefoneException, EnderecoException
    {
        List<ClientePJ> clientesList = new ArrayList<ClientePJ>();

        ResultSet result = this.getClientes();

        while (result.next())
        {
            String formattedDate = this.formatCompleteDate(result.getDate("dt_abertura"));

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

    @Override
    protected ResultSet getClientes() throws SQLException
    {
        Statement stmt = this.conn.createStatement();

        String query = """
            SELECT C.cd_cliente, C.nm_cliente, C.ds_email, C.ds_senha, PJ.nr_cnpj, PJ.nr_inscricao_estadual, PJ.dt_abertura, PJ.ds_setor 
                FROM T_FP_CLIENTE C, T_FP_CLIENTE_PJ PJ
                    WHERE C.cd_cliente = PJ.t_fp_cliente_cd_cliente
                    ORDER BY C.cd_cliente
        """;

        return stmt.executeQuery(query);
    }

    public ClientePJ getById(int id) throws SQLException, ClienteException, TelefoneException, EnderecoException
    {
        ResultSet result = this.getCliente(id);

        if (!result.next())
            throw new SQLException("O cliente PJ " + id + " não existe");

        String formattedDate = this.formatCompleteDate(result.getDate("dt_abertura"));

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

    @Override
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

    public void insert(ClientePJ cliente) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ ? = call CadastrarClientePJ(?, ?, ?, ?, ?, ?, ?) }");

        cs.registerOutParameter(1, Types.NUMERIC);
        cs.setString(2, cliente.getNome());
        cs.setString(3, cliente.getEmail());
        cs.setString(4, cliente.getSenha());
        cs.setString(5, cliente.getCnpj());
        cs.setString(6, cliente.getInscricaoEstadual());
        cs.setDate(7, new java.sql.Date(cliente.getDataAbertura().getTime()));
        cs.setString(8, cliente.getSetor());
        cs.execute();

        int idCliente = cs.getInt(1);

        this.insertTelefones(cliente.getTelefones(), idCliente);
        this.insertEnderecos(cliente.getEnderecos(), idCliente);
    }

    public void update(ClientePJ cliente) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ call AtualizarClientePJ(?, ?, ?, ?, ?, ?, ?, ?) }");

        cs.setInt(1, cliente.getId());
        cs.setString(2, cliente.getNome());
        cs.setString(3, cliente.getEmail());
        cs.setString(4, cliente.getSenha());
        cs.setString(5, cliente.getCnpj());
        cs.setString(6, cliente.getInscricaoEstadual());
        cs.setDate(7, new java.sql.Date(cliente.getDataAbertura().getTime()));
        cs.setString(8, cliente.getSetor());
        cs.execute();
    }

    public void delete(int id) throws SQLException, ClienteException, TelefoneException, EnderecoException
    {
        ClientePJ cliente = this.getById(id);

        String deleteQuery = """
            DELETE FROM T_FP_CLIENTE
                WHERE cd_cliente = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(deleteQuery);

        pstmt.setInt(1, cliente.getId());

        pstmt.executeUpdate();
    }
}