package com.entity;

import java.text.ParseException;


public class Banco
{
    private Integer id;
    private String cnpj;
    private String nome;
    private String email;

    public Banco(
        Integer id,
        String cnpj,
        String nome,
        String email
    )
    {
        this.id = id;
        this.cnpj = cnpj;
        this.nome = nome;
        this.email = email;
    }

    public Banco(
        String cnpj,
        String nome,
        String email
    )
    {
        this.cnpj = cnpj;
        this.nome = nome;
        this.email = email;
    }

    public void data()
    {
        System.out.println("Banco(");
        System.out.println("\tid=" + this.id + ",");
        System.out.println("\tcnpj=" + this.cnpj + ",");
        System.out.println("\tnome=" + this.nome + ",");
        System.out.println("\temail=" + this.email + ",");
        System.out.println(")\n");
    }

    public Integer getId()
    {
        return this.id;
    }

    public String getCnpj()
    {
        return this.cnpj;
    }

    public void updateCnpj(String value)
    {
        this.cnpj = value;
    }

    public String getNome()
    {
        return this.nome;
    }

    public void updateNome(String value)
    {
        this.nome = value;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void updateEmail(String value)
    {
        this.email = value;
    }
}