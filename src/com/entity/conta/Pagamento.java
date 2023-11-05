package com.entity.conta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.entity.conta.exceptions.PagamentoExceptions.*;

public class Pagamento
{
    private Integer id;
    private int idConta;
    private int idTipoPagamento;
    private String codigoBarras;
    private String nome;
    private Date dataPagamento = Calendar.getInstance().getTime();
    private double valor;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    
    public Pagamento(
        Integer id,
        int idConta,
        int idTipoPagamento,
        String codigoBarras,
        String nome,
        String dataPagamento,
        double valor
    ) throws PagamentoException
    {
        this.id = id;
        this.idConta = idConta;
        this.idTipoPagamento = idTipoPagamento;
        this.codigoBarras = codigoBarras;
        this.nome = nome;
        this.valor = valor;

        this.validarCodigoBarras();
        this.validarNome();
        this.validarValor();
        this.definirDataPagamentoSeValida(dataPagamento);
    }

    public Pagamento(
        int idConta,
        int idTipoPagamento,
        String codigoBarras,
        String nome,
        String dataPagamento,
        double valor
    ) throws PagamentoException
    {
        this.idConta = idConta;
        this.idTipoPagamento = idTipoPagamento;
        this.codigoBarras = codigoBarras;
        this.nome = nome;
        this.valor = valor;

        this.validarCodigoBarras();
        this.validarNome();
        this.validarValor();
        this.definirDataPagamentoSeValida(dataPagamento);
    }

    private void validarNome() throws NomeInvalidoException
    {
        if (!this.nomeEValido())
            throw new NomeInvalidoException();
    }

    private boolean nomeEValido()
    {
        return this.nome instanceof String &&
               !this.nome.isEmpty() &&
               this.nome.length() <= 30;
    }

    private void validarCodigoBarras() throws CodigoBarrasInvalidoException
    {
        if (!this.codigoBarrasEValido())
            throw new CodigoBarrasInvalidoException();
    }

    private boolean codigoBarrasEValido()
    {
        int minSize = 35;
        int maxSize = 44;

        if (this.codigoBarras.isEmpty() ||
            this.codigoBarras.length() < minSize ||
            this.codigoBarras.length() > maxSize)
            return false;

        for (char c : this.codigoBarras.toCharArray())
            if (!Character.isDigit(c))
                return false;

        return true;
    }

    private void validarValor() throws ValorInvalidoException
    {
        if (!this.valorEValido(this.valor))
            throw new ValorInvalidoException();
    }

    private boolean valorEValido(double value)
    {
        return value > 1;
    }

    private void definirDataPagamentoSeValida(String dataPagamento) throws DataPagamentoInvalidaException
    {
        try
        {
            this.dataPagamento = this.dateFormat.parse(dataPagamento);
        }
        catch (ParseException e)
        {
            throw new DataPagamentoInvalidaException();
        }
    }

    public void data()
    {
        System.out.println("Pagamento(");
        System.out.println("\tid=" + this.id + ",");
        System.out.println("\tidConta=" + this.idConta + ",");
        System.out.println("\tidTipoPagamento=" + this.idTipoPagamento + ",");
        System.out.println("\tcodigoBarras=" + this.codigoBarras + ",");
        System.out.println("\tdataReceita=" + this.dateFormat.format(this.dataPagamento) + ",");
        System.out.println("\tvalor=" + this.valor + ",");
        System.out.println(")\n");
    }

    public Integer getId()
    {
        return this.id;
    }

    public int getIdConta()
    {
        return this.idConta;
    }

    public int getIdTipoPagamento()
    {
        return this.idTipoPagamento;
    }

    public void updateIdTipoPagamento(int idTipoPagamento)
    {
        this.idTipoPagamento = idTipoPagamento;
    }

    public String getCodigoBarras()
    {
        return this.codigoBarras;
    }

    public String getNome()
    {
        return this.nome;
    }

    public void updateNome(String nome)
    {
        this.nome = nome;
    }

    public Date getDataPagamento()
    {
        return this.dataPagamento;
    }

    public double getValor()
    {
        return this.valor;
    }
}
