package com.entity;

public class Endereco
{
    private Integer id;
    private Integer idCliente;
    private int idUF;
    private String cep;
    private String logradouro;
    private int numero;
    private String complemento;
    private String municipio;

    public Endereco(
        Integer id,
        Integer idCliente,
        int idUF,
        String cep,
        String logradouro,
        int numero,
        String complemento,
        String municipio
    )
    {
        this.id = id;
        this.idCliente = idCliente;
        this.idUF = idUF;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.municipio = municipio;
    }

    public Endereco(
        int idUF,
        String cep,
        String logradouro,
        int numero,
        String complemento,
        String municipio
    )
    {
        this.idUF = idUF;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.municipio = municipio;
    }

    public void data(boolean tab)
    {
        String tab_char = tab ? "\t\t" : "";

        System.out.println(tab_char + "Endereco(");
        System.out.println(tab_char + "\tid=" + this.id + ",");
        System.out.println(tab_char + "\tidCliente=" + this.idCliente + ",");
        System.out.println(tab_char + "\tcep=" + this.cep + ",");
        System.out.println(tab_char + "\tlogradouro=" + this.logradouro + ",");
        System.out.println(tab_char + "\tnumero=" + this.numero + ",");
        System.out.println(tab_char + "\tcomplemento=" + this.complemento + ",");
        System.out.println(tab_char + "\tmunicipio=" + this.municipio + ",");
        System.out.println(tab_char + ")\n");
    }

    public Integer getId()
    {
        return this.id;
    }

    public int getIdCliente()
    {
        return this.idCliente;
    }

    public String getCep()
    {
        return this.cep;
    }

    public String getLogradouro()
    {
        return this.logradouro;
    }

    public int getNumero()
    {
        return this.numero;
    }

    public String getComplemento()
    {
        return this.complemento;
    }

    public String getMunicipio()
    {
        return this.municipio;
    }

    public int getIdUF()
    {
        return this.idUF;
    }
}
