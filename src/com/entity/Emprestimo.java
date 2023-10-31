package com.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Emprestimo
{
    private Integer id;
    private int idConta;
    private double valorEmprestimo;
    private double valorJuros;
    private Date dataPrazo;
    private Date dataSolicitacao = Calendar.getInstance().getTime();
    private double valorParcela;
    private int diaVencimentoParcela;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public Emprestimo(
        Integer id,
        int idConta,
        double valorEmprestimo,
        double valorJuros,
        String dataPrazo,
        String dataSolicitacao,
        double valorParcela,
        int diaVencimentoParcela
    ) throws ParseException
    {
        this.id = id;
        this.idConta = idConta;
        this.valorEmprestimo = valorEmprestimo;
        this.valorJuros = valorJuros;
        this.dataPrazo = this.dateFormat.parse(dataPrazo);
        this.dataSolicitacao = this.dateFormat.parse(dataSolicitacao);
        this.valorParcela = valorParcela;
        this.diaVencimentoParcela = diaVencimentoParcela;
    }

    public Emprestimo(
        int idConta,
        double valorEmprestimo,
        double valorJuros,
        String dataPrazo,
        double valorParcela,
        int diaVencimentoParcela
    ) throws ParseException
    {
        this.idConta = idConta;
        this.valorEmprestimo = valorEmprestimo;
        this.valorJuros = valorJuros;
        this.dataPrazo = this.dateFormat.parse(dataPrazo);
        this.valorParcela = valorParcela;
        this.diaVencimentoParcela = diaVencimentoParcela;
    }

    public void data()
    {
        System.out.println("Emprestimo(");
        System.out.println("\tid=" + this.id + ",");
        System.out.println("\tidConta=" + this.idConta + ",");
        System.out.println("\tvalorEmprestimo=" + this.valorEmprestimo + ",");
        System.out.println("\tvalorJuros=" + this.valorJuros + ",");
        System.out.println("\tdataPrazo=" + this.dateFormat.format(this.dataPrazo) + ",");
        System.out.println("\tdataSolicitacao=" + this.dateFormat.format(this.dataSolicitacao) + ",");
        System.out.println("\tvalorParcela=" + this.valorParcela + ",");
        System.out.println("\tdiaVencimentoParcela=" + this.diaVencimentoParcela + ",");
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

    public double getValorEmprestimo() 
    {
        return this.valorEmprestimo;
    }

    public double getValorJuros() 
    {
        return this.valorJuros;
    }

    public Date getDataPrazo() 
    {
        return this.dataPrazo;
    }

    public Date getDataSolicitacao() 
    {
        return this.dataSolicitacao;
    }

    public double getValorParcela() 
    {
        return this.valorParcela;
    }

    public int getDiaVencimentoParcela()
    {
        return this.diaVencimentoParcela;
    }
}