package com.entity.cliente;

import com.entity.Endereco;
import com.entity.Telefone;

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

    public void data()
    {
        System.out.println("ClientePF(");
        System.out.println("\tid=" + this.getId() + ",");
        System.out.println("\tnome=" + this.getNome() + ",");
        System.out.println("\temail=" + this.getEmail() + ",");
        System.out.println("\tsenha=" + this.getSenha() + ",");
        System.out.println("\tcnpj=" + this.cnpj + ",");
        System.out.println("\tinscricaoEstadual=" + this.inscricaoEstadual + ",");
        System.out.println("\tdataAbertura=" + this.dataAbertura + ",");
        System.out.println("\ttelefones=[");

        for (Telefone telefone : this.getTelefones())
        {
            telefone.data(true);
        }

        System.out.println("\t]");

        System.out.println("\tenderecos=[");

        for (Endereco endereco : this.getEnderecos())
        {
            endereco.data(true);
        }

        System.out.println("\t]");
        System.out.println(")\n");
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


