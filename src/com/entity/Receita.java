package com.entity;

public class Receita {
    private Integer id;
    private int idCliente;
    private int idTipoReceita;
    private double valor;
    private String dataReceita;


    public Receita(
        Integer id,
        int idCliente,
        int idTipoReceita,
        double valor,
        String dataReceita
    )
    {
        this.id = id;
        this.idCliente = idCliente;
        this.idTipoReceita = idTipoReceita;
        this.valor = valor;
        this.dataReceita = dataReceita;
    }

    public void data()
    {
        System.out.println("Receita(");
        System.out.println("\tid=" + this.id + ",");
        System.out.println("\tidCliente=" + this.idCliente + ",");
        System.out.println("\tidTipoReceita=" + this.idTipoReceita + ",");
        System.out.println("\tvalor=" + this.valor + ",");
        System.out.println("\tdataReceita=" + this.dataReceita + ",");
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

    public String getDataReceita()
    {
        return this.dataReceita;
    }

    public int getIdTipoReceita()
    {
        return this.idTipoReceita;
    }

}