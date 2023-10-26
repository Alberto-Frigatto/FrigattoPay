package com.entity;

public class Despesa
{
    private Integer id;
    private Integer idCliente;
    private int idTipoDespesa;
    private String nome;
    private double valor;
    private String descricao;
    private String dataVencimento;


    public Despesa(
        Integer id,
        Integer idCliente,
        int idTipoDespesa,
        String nome,
        double valor,
        String descricao,
        String dataVencimento
    )
    {
        this.id = id;
        this.idCliente = idCliente;
        this.idTipoDespesa = idTipoDespesa;
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.dataVencimento = dataVencimento;
    }

    public void data()
    {
        System.out.println("Receita(");
        System.out.println("\tid=" + this.id + ",");
        System.out.println("\tidCliente=" + this.idCliente + ",");
        System.out.println("\tidTipoDespesa=" + this.idTipoDespesa + ",");
        System.out.println("\tnome=" + this.nome + ",");
        System.out.println("\tvalor=" + this.valor + ",");
        System.out.println("\tdescricao=" + this.descricao + ",");
        System.out.println("\tdataVencimento=" + this.dataVencimento + ",");
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

    public String getNome()
    {
        return this.nome;
    }

    public Double getValor()
    {
        return this.valor;
    }

    public String getDescricao()
    {
        return this.descricao;
    }

    public String getDataVencimento()
    {
        return this.dataVencimento;
    }

    public int getIdTipoDespesa()
    {
        return this.idTipoDespesa;
    }

}