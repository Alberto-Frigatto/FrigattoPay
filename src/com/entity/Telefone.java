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

    public void data(boolean tab)
    {
        String tab_char = tab ? "\t\t" : "";

        System.out.println(tab_char + "Telefone(");
        System.out.println(tab_char + "\tid=" + this.id + ",");
        System.out.println(tab_char + "\tidCliente=" + this.idCliente + ",");
        System.out.println(tab_char + "\tnumero=" + this.numero + ",");
        System.out.println(tab_char + "\tramal=" + this.ramal + ",");
        System.out.println(tab_char + "\tddd=" + this.ddd + ",");
        System.out.println(tab_char + "\tidTipoTelefone=" + this.idTipoTelefone + ",");
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
