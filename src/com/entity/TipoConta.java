package com.entity;

public class TipoConta {
    private Integer id;
    private String nome;

    public TipoConta(
        Integer id,
        String nome
    )
    {
        this.id = id;
        this.nome = nome;
    }

    public TipoConta(
        String nome
    )
    {
        this.nome = nome;
    }

    public void data()
    {
        System.out.println("Tipo Conta(");
        System.out.println("\tid=" + this.id + ",");
        System.out.println("\tNome=" + this.nome + ",");
        System.out.println(")\n");
    }

    public Integer getId()
    {
        return this.id;
    }

    public String getNome()
    {
        return this.nome;
    }

    public String updateNome()
    {
        return this.nome;
    }
}
