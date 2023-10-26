package com.entity.cliente;

public class ClientePJ extends Cliente
{
    private String cnpj;
    private String inscricaoEstadual;
    private String dataAbertura;
    private String setor;

    public ClientePJ(
        Integer id,
        String nome,
        String email,
        String senha,
        String cnpj,
        String inscricaoEstadual,
        String dataAbertura,
        String setor
    )
    {
        super(id, nome, email, senha);
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.dataAbertura = dataAbertura;
        this.setor = setor;
    }

    public String getcnpj()
    {
      return this.cnpj;
    }

    public String getinscricaoEstadual()
    {
      return this.inscricaoEstadual;
    }

    public String getDataAbertura()
    {
      return this.dataAbertura;
    }

    public String getsetor()
    {
      return this.setor;
    }
}


