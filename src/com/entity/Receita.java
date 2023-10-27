package com.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Receita {
    private Integer id;
    private int idCliente;
    private int idTipoReceita;
    private double valor;
    private Date dataReceita;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public Receita(
        Integer id,
        int idCliente,
        int idTipoReceita,
        double valor,
        String dataReceita
    ) throws ParseException
    {
        this.id = id;
        this.idCliente = idCliente;
        this.idTipoReceita = idTipoReceita;
        this.valor = valor;
        this.dataReceita = this.dateFormat.parse(dataReceita);
    }

    public void data()
    {
        System.out.println("Receita(");
        System.out.println("\tid=" + this.id + ",");
        System.out.println("\tidCliente=" + this.idCliente + ",");
        System.out.println("\tidTipoReceita=" + this.idTipoReceita + ",");
        System.out.println("\tvalor=" + this.valor + ",");
        System.out.println("\tdataReceita=" + this.dateFormat.format(this.dataReceita) + ",");
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

    public Double getValor()
    {
        return this.valor;
    }

    public Date getDataReceita()
    {
        return this.dataReceita;
    }

    public int getIdTipoReceita()
    {
        return this.idTipoReceita;
    }

}