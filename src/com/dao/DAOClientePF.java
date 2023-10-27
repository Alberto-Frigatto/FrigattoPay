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
import com.entity.cliente.ClientePF;

public class DAOClientePF extends DAOCliente
{
    public DAOClientePF(Connection conn)
    {
        super(conn);
    }

    public List<ClientePF> getAll() throws SQLException, ParseException
    {
        List<ClientePF> clientesList = new ArrayList<ClientePF>();

        ResultSet result = this.getClientes();

        while (result.next())
        {
            String formattedDate = this.formatDate(result.getDate("dt_nascimento"));

            ClientePF clientePF = new ClientePF(
                result.getInt("cd_cliente"),
                result.getString("nm_cliente"),
                result.getString("ds_email"),
                result.getString("ds_senha"),
                result.getString("nr_cpf"),
                result.getString("nr_rg"),
                formattedDate
            );

            int idCliente = clientePF.getId();

            List<Telefone> telefones = this.instantiateTelefones(idCliente);
            List<Endereco> enderecos = this.instantiateEnderecos(idCliente);

            clientePF.addTelefone(telefones);
            clientePF.addEndereco(enderecos);

            clientesList.add(clientePF);
        }

        return clientesList;
    }

    protected ResultSet getClientes() throws SQLException
    {
        Statement stmt = this.conn.createStatement();

        String query = """
            SELECT C.cd_cliente, C.nm_cliente, C.ds_email, C.ds_senha, PF.nr_cpf, PF.nr_rg, PF.dt_nascimento
                FROM T_FP_CLIENTE C, T_FP_CLIENTE_PF PF
                    WHERE C.cd_cliente = PF.t_fp_cliente_cd_cliente
        """;

        return stmt.executeQuery(query);
    }

    public ClientePF getById(int id) throws SQLException, ParseException, Exception
    {
        ResultSet result = this.getCliente(id);

        if (!result.next())
            throw new Exception("O cliente  " + id + " não existe");

        String formattedDate = this.formatDate(result.getDate("dt_nascimento"));

        ClientePF clientePF = new ClientePF(
            result.getInt("cd_cliente"),
            result.getString("nm_cliente"),
            result.getString("ds_email"),
            result.getString("ds_senha"),
            result.getString("nr_cpf"),
            result.getString("nr_rg"),
            formattedDate
        );

        int idCliente = clientePF.getId();

        List<Telefone> telefones = this.instantiateTelefones(idCliente);
        List<Endereco> enderecos = this.instantiateEnderecos(idCliente);

        clientePF.addTelefone(telefones);
        clientePF.addEndereco(enderecos);

        return clientePF;
    }

    protected ResultSet getCliente(int id) throws SQLException
    {
        String query = """
            SELECT C.cd_cliente, C.nm_cliente, C.ds_email, C.ds_senha, PF.nr_cpf, PF.nr_rg, PF.dt_nascimento
                FROM T_FP_CLIENTE C, T_FP_CLIENTE_PF PF
                    WHERE C.cd_cliente = PF.t_fp_cliente_cd_cliente AND
                          C.cd_cliente = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(query);

        pstmt.setInt(1, id);

        return pstmt.executeQuery();
    }

    public void insert(ClientePF pf) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ ? = call CadastrarClientePF(?, ?, ?, ?, ?, ?) }");

        cs.registerOutParameter(1, Types.NUMERIC);
        cs.setString(2, pf.getNome());
        cs.setString(3, pf.getEmail());
        cs.setString(4, pf.getSenha());
        cs.setString(5, pf.getCpf());
        cs.setString(6, pf.getRg());
        cs.setDate(7, new java.sql.Date(pf.getDataNascimento().getTime()));
        cs.execute();

        int idCliente = cs.getInt(1);

        this.insertTelefones(pf.getTelefones(), idCliente);
        this.insertEnderecos(pf.getEnderecos(), idCliente);
    }

    public void update(ClientePF pf) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ call AtualizarClientePf(?, ?, ?, ?, ?, ?, ?) }");

        cs.setInt(1, pf.getId());
        cs.setString(2, pf.getNome());
        cs.setString(3, pf.getEmail());
        cs.setString(4, pf.getSenha());
        cs.setString(5, pf.getCpf());
        cs.setString(6, pf.getRg());
        cs.setDate(7, new java.sql.Date(pf.getDataNascimento().getTime()));
        cs.execute();
    }
}
