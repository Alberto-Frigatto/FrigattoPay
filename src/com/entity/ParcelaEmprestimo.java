package com.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ParcelaEmprestimo
{
    private Integer id;
    private int idEmprestimo;
    private double valor;
    private Date dataPagamento = Calendar.getInstance().getTime();
    private double juros;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public ParcelaEmprestimo(
        Integer id,
        int idEmprestimo,
        double valor,
        String dataPagamento,
        double juros
    ) throws ParseException
    {
        this.id = id;
        this.idEmprestimo = idEmprestimo;
        this.valor = valor;
        this.dataPagamento = this.dateFormat.parse(dataPagamento);
        this.juros = juros;
    }

    public ParcelaEmprestimo(
        int idEmprestimo,
        double valor,
        double juros
    )
    {
        this.idEmprestimo = idEmprestimo;
        this.valor = valor;
        this.juros = juros;
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
