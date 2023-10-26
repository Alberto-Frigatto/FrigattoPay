package com.entity.cliente;

public class ClientePF extends Cliente
{
    private String cpf;
    private String rg;
    private String dataNascimento;

    public ClientePF(
        Integer id,
        String nome,
        String email,
        String senha,
        String cpf,
        String rg,
        String dataNascimento
    )
    {
        super(id, nome, email, senha);
        this.cpf = cpf;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
    }

    public String getCpf()
    {
      return this.cpf;
    }

    public String getRg()
    {
      return this.rg;
    }

    public String getDataNascimento()
    {
      return this.dataNascimento;
    }
}
