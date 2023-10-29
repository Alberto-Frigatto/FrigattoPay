package com.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Despesa
{
    private Integer id;
    private Integer idCliente;
    private int idTipoDespesa;
    private String nome;
    private double valor;
    private String descricao;
    private Date dataVencimento;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public Despesa(
        Integer id,
        Integer idCliente,
        int idTipoDespesa,
        String nome,
        double valor,
        String descricao,
        String dataVencimento
    ) throws ParseException
    {
        this.id = id;
        this.idCliente = idCliente;
        this.idTipoDespesa = idTipoDespesa;
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.dataVencimento = this.dateFormat.parse(dataVencimento);
    }

    public Despesa(
        Integer idCliente,
        int idTipoDespesa,
        String nome,
        double valor,
        String descricao,
        String dataVencimento
    ) throws ParseException
    {
        this.idCliente = idCliente;
        this.idTipoDespesa = idTipoDespesa;
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.dataVencimento = this.dateFormat.parse(dataVencimento);
    }

    public void data()
    {
        System.out.println("Despesa(");
        System.out.println("\tid=" + this.id + ",");
        System.out.println("\tidCliente=" + this.idCliente + ",");
        System.out.println("\tidTipoDespesa=" + this.idTipoDespesa + ",");
        System.out.println("\tnome=" + this.nome + ",");
        System.out.println("\tvalor=" + this.valor + ",");
        System.out.println("\tdescricao=" + this.descricao + ",");
        System.out.println("\tdataVencimento=" + this.dateFormat.format(this.dataVencimento) + ",");
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

    public Integer getIdCliente()
    {
        return this.idCliente;
    }

    public void updateIdCliente(Integer value)
    {
        this.idCliente = value;
    }

    public int getIdTipoDespesa()
    {
        return this.idTipoDespesa;
    }

    public void updateIdTipoDespesa(int value)
    {
        this.idTipoDespesa = value;
    }

    public String getNome()
    {
        return this.nome;
    }

    public void updateNome(String value)
    {
        this.nome = value;
    }

    public double getValor()
    {
        return this.valor;
    }

    public void updateValor(double value)
    {
        this.valor = value;
    }

    public String getDescricao()
    {
        return this.descricao;
    }

    public void updateDescricao(String value)
    {
        this.descricao = value;
    }

    public Date getDataVencimento()
    {
        return this.dataVencimento;
    }

    public void updateDataVencimento(String value) throws ParseException
    {
        this.dataVencimento = this.dateFormat.parse(value);
    }
}