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
