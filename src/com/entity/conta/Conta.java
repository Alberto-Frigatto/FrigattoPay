package com.entity.conta;

import com.entity.conta.exceptions.CartaoExceptions.CartaoException;
import com.entity.conta.exceptions.ContaExceptions.AgenciaInvalidaException;
import com.entity.conta.exceptions.ContaExceptions.ContaException;
import com.entity.conta.exceptions.ContaExceptions.NumeroInvalidoException;
import com.entity.conta.exceptions.ContaExceptions.TransacaoInvalidaException;
import com.entity.conta.exceptions.EmprestimoExceptions.EmprestimoException;
import com.entity.conta.exceptions.PagamentoExceptions.PagamentoException;

public class Conta
{
    private Integer id;
    private int idCliente;
    private int idBanco;
    private int idTipoConta;
    private String numero;
    private double saldo;
    private String agencia;

    public Conta(
        Integer id,
        int idCliente,
        int idBanco,
        int idTipoConta,
        String numero,
        double saldo,
        String agencia
    ) throws ContaException
    {
        this.id = id;
        this.idCliente = idCliente;
        this.idBanco = idBanco;
        this.idTipoConta = idTipoConta;
        this.numero = numero.strip();
        this.saldo = saldo;
        this.agencia = agencia.strip();

        this.validarNumero();
        this.validarAgencia();
    }

    public Conta(
        int idCliente,
        int idBanco,
        int idTipoConta,
        String numero,
        double saldo,
        String agencia
    ) throws ContaException
    {
        this.idCliente = idCliente;
        this.idBanco = idBanco;
        this.idTipoConta = idTipoConta;
        this.numero = numero.strip();
        this.saldo = saldo;
        this.agencia = agencia.strip();

        this.validarNumero();
        this.validarAgencia();
    }

    private void validarNumero() throws NumeroInvalidoException
    {
        if (!this.numeroEValido())
            throw new NumeroInvalidoException();
    }

    private boolean numeroEValido()
    {
        int maxLength = 9;

        if (this.numero.isEmpty() || this.numero.length() > maxLength)
            return false;

        for (char c : this.numero.toCharArray())
            if (!Character.isDigit(c))
                return false;

        return true;
    }

    private void validarAgencia() throws AgenciaInvalidaException
    {
        if (!this.agenciaEValida())
            throw new AgenciaInvalidaException();
    }

    private boolean agenciaEValida()
    {
        int maxLength = 10;

        if (this.agencia.isEmpty() || this.agencia.length() > maxLength)
            return false;

        for (char c : this.agencia.toCharArray())
            if (!Character.isDigit(c))
                return false;

        return true;
    }

    public Transacao transferir(double value, Conta conta) throws TransacaoInvalidaException
    {
        if (!this.transacaoEPossivel(value) || !this.valorTransacaoEValido(value))
            throw new TransacaoInvalidaException();

        this.saldo -= value;

        conta.depositar(value);

        return new Transacao(this.id, conta.getId(), 2, value);
    }

    private boolean valorTransacaoEValido(double value)
    {
        return value > 0;
    }

    private boolean transacaoEPossivel(double value)
    {
        return this.saldo >= value;
    }

    private void depositar(double value) throws TransacaoInvalidaException
    {
        if (!valorTransacaoEValido(value))
            throw new TransacaoInvalidaException();

        this.saldo += value;
    }

    public Cartao criarCartao(
        int idBandeira,
        int idTipoCartao,
        String numero,
        String dataValidade,
        String cvv,
        boolean desbloqueado
    ) throws CartaoException
    {
        return new Cartao(this.id, idBandeira, idTipoCartao, numero, dataValidade, cvv, desbloqueado);
    }

    public Emprestimo criarEmprestimo(
        double valorEmprestimo,
        double valorJuros,
        double valorParcela,
        String dataPrazo,
        String dataSolicitacao,
        int diaVencimentoParcela
    ) throws EmprestimoException
    {
        Emprestimo emprestimo = new Emprestimo(
            this.id,
            valorEmprestimo,
            valorJuros,
            valorParcela,
            dataPrazo,
            dataSolicitacao,
            diaVencimentoParcela
        );

        this.saldo += valorEmprestimo;

        return emprestimo;
    }

    public Pagamento criarPagamento(
        int idTipoPagamento,
        String codigoBarras,
        String nome,
        String dataPagamento,
        double valor
    ) throws PagamentoException
    {
        Pagamento pagamento = new Pagamento(this.id, idTipoPagamento, codigoBarras, nome, dataPagamento, valor);

        this.saldo -= valor;

        return pagamento;
    }

    public void data()
    {
        System.out.println("Conta(");
        System.out.println("\tid=" + this.id + ",");
        System.out.println("\tidCliente=" + this.idCliente + ",");
        System.out.println("\tidTipoConta=" + this.idTipoConta + ",");
        System.out.println("\tnumero=" + this.numero + ",");
        System.out.println("\tsaldo=" + this.saldo + ",");
        System.out.println("\tagencia=" + this.agencia + ",");
        System.out.println(")\n");
    }

    public Integer getId()
    {
        return this.id;
    }

    public int getIdCliente()
    {
        return this.idCliente;
    }

    public int getIdBanco()
    {
        return this.idBanco;
    }

    public int getIdTipoConta()
    {
        return this.idTipoConta;
    }

    public String getNumero()
    {
        return this.numero;
    }

    public double getSaldo()
    {
        return this.saldo;
    }

    public void updateSaldo(double saldo)
    {
        this.saldo = saldo;
    }

    public String getAgencia()
    {
        return this.agencia;
    }
}
