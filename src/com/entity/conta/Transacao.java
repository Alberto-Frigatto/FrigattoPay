package com.entity.conta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Transacao
{
    private Integer id;
    private int idContaOrigem;
    private Integer idContaDestino;
    private int idTipoTransacao;
    private double valor;
    private Date dataTransacao = Calendar.getInstance().getTime();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public Transacao(
        Integer id,
        int idContaOrigem,
        Integer idContaDestino,
        int idTipoTransacao,
        double valor,
        String dataTransacao
    ) throws ParseException
    {
        this.id = id;
        this.idContaOrigem = idContaOrigem;
        this.idContaDestino = idContaDestino;
        this.idTipoTransacao = idTipoTransacao;
        this.valor = valor;
        this.dataTransacao = this.dateFormat.parse(dataTransacao);
    }

    public Transacao(
        int idContaOrigem,
        Integer idContaDestino,
        int idTipoTransacao,
        double valor
    ) 
    {
        this.idContaOrigem = idContaOrigem;
        this.idContaDestino = idContaDestino;
        this.idTipoTransacao = idTipoTransacao;
        this.valor = valor;
    }

    public Transacao(
        int idContaOrigem,
        int idTipoTransacao,
        double valor
    )
    {
        this.idContaOrigem = idContaOrigem;
        this.idTipoTransacao = idTipoTransacao;
        this.valor = valor;
    }

    public void data()
    {
        System.out.println("Transacao(");
        System.out.println("\tid=" + this.id + ",");
        System.out.println("\tidContaOrigem=" + this.idContaOrigem + ",");
        System.out.println("\tidContaDestino=" + this.idContaDestino + ",");
        System.out.println("\tidTipoTransacao=" + this.idTipoTransacao + ",");
        System.out.println("\tvalor=" + this.valor + ",");
        System.out.println("\tdataTransacao=" + this.dateFormat.format(this.dataTransacao) + ",");
        System.out.println(")\n");
    }

    public Integer getId()
    {
        return this.id;
    }

    public int getIdContaOrigem()
    {
        return this.idContaOrigem;
    }

    public Integer getIdContaDestino()
    {
        return this.idContaDestino;
    }

    public int getIdTipoTransacao()
    {
        return this.idTipoTransacao;
    }

    public double getValor()
    {
        return this.valor;
    }

    public Date getDataTransacao()
    {
        return this.dataTransacao;
    }
}
