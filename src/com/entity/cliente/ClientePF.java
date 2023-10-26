package com.entity.cliente;

import com.entity.Endereco;
import com.entity.Telefone;

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

    public void data()
    {
        System.out.println("ClientePF(");
        System.out.println("\tid=" + this.getId() + ",");
        System.out.println("\tnome=" + this.getNome() + ",");
        System.out.println("\temail=" + this.getEmail() + ",");
        System.out.println("\tsenha=" + this.getSenha() + ",");
        System.out.println("\tcpf=" + this.cpf + ",");
        System.out.println("\trg=" + this.rg + ",");
        System.out.println("\tdataNascimento=" + this.dataNascimento + ",");
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
