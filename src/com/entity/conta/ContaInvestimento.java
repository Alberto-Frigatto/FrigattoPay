package com.entity.conta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.entity.conta.exceptions.ContaInvestimentoExceptions.*;

public class ContaInvestimento
{
    private Integer id;
    private int idInvestimento;
    private int idConta;
    private Date dataInvestimento;
    private double valor;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public ContaInvestimento(
        Integer id,
        int idInvestimento,
        int idConta,
        String dataInvestimento,
        double valor
    ) throws ContaInvestimentoException
    {
        this.id = id;
        this.idInvestimento = idInvestimento;
        this.idConta = idConta;
        this.valor = valor;

        this.validarValor();
        this.definirDataInvestimentoSeValida(dataInvestimento);
    }

    public ContaInvestimento(
        int idInvestimento,
        int idConta,
        String dataInvestimento,
        double valor
    ) throws ContaInvestimentoException
    {
        this.idInvestimento = idInvestimento;
        this.idConta = idConta;
        this.valor = valor;

        this.validarValor();
        this.definirDataInvestimentoSeValida(dataInvestimento);
    }

    private void definirDataInvestimentoSeValida(String dataPagamento) throws DataInvestimentoInvalidaException
    {
        try
        {
            this.dataInvestimento = this.dateFormat.parse(dataPagamento);
        }
        catch (ParseException e)
        {
            throw new DataInvestimentoInvalidaException();
        }
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

    public void data()
    {
        System.out.println("ContaInvestimento(");
        System.out.println("\tid=" + this.id + ",");
        System.out.println("\tdataInvestimento=" + this.dateFormat.format(this.dataInvestimento) + ",");
        System.out.println("\tvalor=" + this.valor + ",");
        System.out.println("\tidInvestimento=" + this.idInvestimento + ",");
        System.out.println("\tidConta=" + this.idConta + ",");
        System.out.println(")\n");
    }

    public Integer getId()
    {
        return this.id;
    }

    public int getIdInvestimento()
    {
        return this.idInvestimento;
    }

    public int getIdConta()
    {
        return this.idConta;
    }

    public double getValor()
    {
        return this.valor;
    }

    public void updateValor(double value)
    {
        this.valor = value;
    }

    public Date getDataInvestimento()
    {
        return this.dataInvestimento;
    }
}