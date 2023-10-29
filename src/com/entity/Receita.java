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

    public Receita(
        int idCliente,
        int idTipoReceita,
        double valor,
        String dataReceita
    ) throws ParseException
    {
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

    public void updateId(Integer value)
    {
        this.id = value;
    }

    public int getIdCliente()
    {
        return this.idCliente;
    }

    public void updateIdCliente(int value)
    {
        this.idCliente = value;
    }

    public int getIdTipoReceita()
    {
        return this.idTipoReceita;
    }

    public void updateIdTipoReceita(int value)
    {
        this.idTipoReceita = value;
    }

    public double getValor()
    {
        return this.valor;
    }

    public void updateValor(double value)
    {
        this.valor = value;
    }

    public Date getDataReceita()
    {
        return this.dataReceita;
    }

    public void updateDataReceita(String value) throws ParseException
    {
        this.dataReceita = this.dateFormat.parse(value);
    }
}