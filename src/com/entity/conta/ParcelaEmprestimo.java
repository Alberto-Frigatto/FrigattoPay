package com.entity.conta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.entity.conta.exceptions.ParcelaEmprestimoExceptions.*;

public class ParcelaEmprestimo
{
    private Integer id;
    private int idEmprestimo;
    private double valor;
    private Date dataPagamento;
    private double juros;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public ParcelaEmprestimo(
        Integer id,
        int idEmprestimo,
        double valor,
        String dataPagamento,
        double juros
    ) throws ParcelaEmprestimoException
    {
        this.id = id;
        this.idEmprestimo = idEmprestimo;
        this.valor = valor;
        this.juros = juros;

        this.validarValor();
        this.definirDataPagamentoSeValida(dataPagamento);
    }

    public ParcelaEmprestimo(
        int idEmprestimo,
        double valor,
        String dataPagamento,
        double juros
    ) throws ParcelaEmprestimoException
    {
        this.idEmprestimo = idEmprestimo;
        this.valor = valor;
        this.juros = juros;

        this.validarValor();
        this.definirDataPagamentoSeValida(dataPagamento);
    }

    private void validarValor() throws ValorInvalidoException
    {
        if (!this.valorEValido())
            throw new ValorInvalidoException();
    }

    private boolean valorEValido()
    {
        return this.valor > 0;
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
        System.out.println("ParcelaEmprestimo(");
        System.out.println("\tid=" + this.id + ",");
        System.out.println("\tidEmprestimo=" + this.idEmprestimo + ",");
        System.out.println("\tvalor=" + this.valor + ",");
        System.out.println("\tdataPagamento=" + this.dateFormat.format(this.dataPagamento) + ",");
        System.out.println("\tjuros=" + this.juros + ",");
        System.out.println(")\n");
    }

    public Integer getId()
    {
        return this.id;
    }

    public int getIdEmprestimo()
    {
        return this.idEmprestimo;
    }

    public double getValor()
    {
        return this.valor;
    }

    public Date getDataPagamento()
    {
        return this.dataPagamento;
    }

    public double getJuros()
    {
        return this.juros;
    }

    public SimpleDateFormat getDateFormat()
    {
        return this.dateFormat;
    }
}
