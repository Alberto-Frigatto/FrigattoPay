package com.entity;

public class Endereco
{
    private Integer id;
    private Integer idCliente;
    private String cep;
    private String logradouro;
    private int numero;
    private String complemento;
    private int idTipoLogradouro;
    private int idBairro;

    public Endereco(
        Integer id,
        Integer idCliente,
        String cep,
        String logradouro,
        int numero,
        String complemento,
        int idTipoLogradouro,
        int idBairro
    )
    {
        this.id = id;
        this.idCliente = idCliente;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.idTipoLogradouro = idTipoLogradouro;
        this.idBairro = idBairro;
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
        System.out.println(tab_char + "\tidTipoLogradouro=" + this.idTipoLogradouro + ",");
        System.out.println(tab_char + "\tidBairro=" + this.idBairro + ",");
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

    public int getIdTipoLogradouro()
    {
      return this.idTipoLogradouro;
    }

    public int getIdBairro()
    {
      return this.idBairro;
    }
}
