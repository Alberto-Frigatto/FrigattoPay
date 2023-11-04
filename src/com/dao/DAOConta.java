package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entity.conta.Conta;
import com.entity.conta.ContaExceptions.ContaException;

public class DAOConta extends DAO
{
    public DAOConta(Connection conn)
    {
        super(conn);
    }

    public List<Conta> getAll() throws SQLException, ContaException
    {
        List<Conta> contas = new ArrayList<Conta>();
        
        ResultSet result = this.getContas();

        while (result.next())
        {
            Conta conta = new Conta(
                result.getInt("cd_conta"),
                result.getInt("t_fp_cliente_cd_cliente"),
                result.getInt("t_fp_banco_cd_banco"),
                result.getInt("t_fp_tipo_conta_cd_tipo"),
                result.getString("nr_conta"),
                result.getDouble("vl_saldo"),
                result.getString("nr_agencia")
            );

            contas.add(conta);
        }

        return contas;
    }

    private ResultSet getContas() throws SQLException
    {
        Statement stmt = this.conn.createStatement();

        String query = """
            SELECT
                cd_conta, 
                t_fp_cliente_cd_cliente, 
                t_fp_banco_cd_banco, 
                t_fp_tipo_conta_cd_tipo, 
                nr_conta, 
                vl_saldo, 
                nr_agencia

                FROM T_FP_CONTA
                    ORDER BY cd_conta
        """;

        return stmt.executeQuery(query);        
    }

    public Conta getById(int id) throws SQLException, ContaException
    {
        ResultSet result = this.getConta(id);

        if (!result.next())
            throw new SQLException("A conta " + id + " não existe");

        Conta conta = new Conta(
            result.getInt("cd_conta"),
            result.getInt("t_fp_cliente_cd_cliente"),
            result.getInt("t_fp_banco_cd_banco"),
            result.getInt("t_fp_tipo_conta_cd_tipo"),
            result.getString("nr_conta"),
            result.getDouble("vl_saldo"),
            result.getString("nr_agencia")
        );

        return conta;
    }

    private ResultSet getConta(int id) throws SQLException
    {
        String query = """
            SELECT
                cd_conta, 
                t_fp_cliente_cd_cliente, 
                t_fp_banco_cd_banco, 
                t_fp_tipo_conta_cd_tipo, 
                nr_conta, 
                vl_saldo, 
                nr_agencia

                FROM T_FP_CONTA
                    WHERE cd_conta = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(query);

        pstmt.setInt(1, id);

        return pstmt.executeQuery();
    }

    
    public void insert(Conta conta) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ call InserirConta(?, ?, ?, ?, ?, ?) }");

        cs.setInt(1, conta.getIdCliente());
        cs.setInt(2, conta.getIdBanco());
        cs.setInt(3, conta.getIdTipoConta());
        cs.setString(4, conta.getNumero());
        cs.setDouble(5, conta.getSaldo());
        cs.setString(6, conta.getAgencia());
        cs.execute();
    }

    public void update(Conta conta) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{ call AtualizarConta(?, ?) }");

        cs.setInt(1, conta.getId());
        cs.setDouble(2, conta.getSaldo());
        cs.execute();
    }

    public void delete(int id) throws SQLException, ContaException
    {
        Conta conta = this.getById(id);

        String deleteQuery = """
            DELETE FROM T_FP_CONTA
                WHERE cd_conta = ?
        """;

        PreparedStatement pstmt = this.conn.prepareStatement(deleteQuery);

        pstmt.setInt(1, conta.getId());

        pstmt.executeUpdate();
    }
}
