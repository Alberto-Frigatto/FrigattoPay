package com.model.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.dao.DAOClientePF;
import com.model.dao.DAOClientePJ;

import com.model.entity.cliente.Cliente;
import com.model.entity.cliente.ClientePF;
import com.model.entity.cliente.ClientePJ;
import com.model.entity.cliente.exceptions.ClienteExceptions.ClienteException;
import com.model.entity.endereco.Endereco;
import com.model.entity.endereco.EnderecoExceptions.EnderecoException;
import com.model.entity.telefone.Telefone;
import com.model.entity.telefone.TelefoneExceptions.TelefoneException;
import com.singleton.ConnectionManager;

public class DAOCliente extends DAO
{
    public DAOCliente(Connection conn)
    {
        super(conn);
    }
    
    public Cliente login(String email, String senha) throws SQLException, ClienteException, TelefoneException, EnderecoException
    {
    	ResultSet result = this.searchCliente(email, senha);
    	
    	if (!result.next())
    		throw new SQLException("Email ou senha inválido");
    	
    	int idCliente = result.getInt("cd_cliente");
    	
    	ConnectionManager.getInstance();
		
    	try
    	{
    		DAOClientePF daoClientePF = new DAOClientePF(ConnectionManager.getConnection());
    		ClientePF cliente = daoClientePF.getById(idCliente);
    		
    		return cliente;
    	}
    	catch (Exception e)
    	{
    		DAOClientePJ daoClientePJ = new DAOClientePJ(ConnectionManager.getConnection());
    		ClientePJ cliente = daoClientePJ.getById(idCliente);
    		
    		return cliente;
    	}
    }
    
    protected ResultSet searchCliente(String email, String senha) throws SQLException
    {
    	String senhaCriptografada = this.criptografarSenha(senha);

        String query = """
            SELECT cd_cliente
        		FROM T_FP_CLIENTE
        			WHERE ds_email = ? AND
        				  ds_senha = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(query);

        pstmt.setString(1, email);
        pstmt.setString(2, senhaCriptografada);

        return pstmt.executeQuery();
    }
    
    private String criptografarSenha(String senha)
    {
    	try
    	{
    		MessageDigest md = MessageDigest.getInstance("SHA-256");
    		byte[] hashedBytes = md.digest(senha.getBytes());
    		
    		StringBuilder stringBuilder = new StringBuilder();
    		for (byte b : hashedBytes)
    			stringBuilder.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
    		
    		return stringBuilder.toString();
    	}
    	catch (NoSuchAlgorithmException e)
    	{
    		throw new RuntimeException("Error hashing password.");
    	}
    }

    protected List<Telefone> instantiateTelefones(int idCliente) throws SQLException, TelefoneException
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
                    ORDER BY T.cd_telefone
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(query);

        pstmt.setInt(1, idCliente);

        return pstmt.executeQuery();
    }

    protected List<Endereco> instantiateEnderecos(int idCliente) throws SQLException, EnderecoException
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
                    ORDER BY E.cd_endereco
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
