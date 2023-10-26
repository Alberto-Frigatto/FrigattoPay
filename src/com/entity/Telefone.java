package com.entity;

public class Telefone
{
    private Integer id;
    private Integer idCliente;
    private String numero;
    private String ramal;
    private int ddd;
    private int idTipoTelefone;

    public Telefone(
        Integer id,
        Integer idCliente,
        String numero,
        String ramal,
        int ddd,
        int idTipoTelefone
    )
    {
        this.id = id;
        this.idCliente = idCliente;
        this.numero = numero;
        this.ramal = ramal;
        this.ddd = ddd;
        this.idTipoTelefone = idTipoTelefone;
    }

    public Integer getId()
    {
      return this.id;
    }

    public int getIdCliente()
    {
      return this.idCliente;
    }

    public String getNumero()
    {
      return this.numero;
    }

    public String getRamal()
    {
      return this.ramal;
    }

    public int getDdd()
    {
      return this.ddd;
    }

    public int getIdTipoTelefone()
    {
      return this.idTipoTelefone;
    }
}
