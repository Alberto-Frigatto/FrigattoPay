package com.entity.cliente;

import java.text.ParseException;
import java.util.Date;

import com.entity.Endereco;
import com.entity.Telefone;

public class ClientePF extends Cliente
{
    private String cpf;
    private String rg;
    private Date dataNascimento;

    public ClientePF(
        Integer id,
        String nome,
        String email,
        String senha,
        String cpf,
        String rg,
        String dataNascimento
    ) throws ParseException
    {
        super(id, nome, email, senha);
        this.cpf = cpf;
        this.rg = rg;

        this.dataNascimento = this.dateFormat.parse(dataNascimento);
    }

    public ClientePF(
        String nome,
        String email,
        String senha,
        String cpf,
        String rg,
        String dataNascimento
    ) throws ParseException
    {
        super(nome, email, senha);
        this.cpf = cpf;
        this.rg = rg;

        this.dataNascimento = this.dateFormat.parse(dataNascimento);
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
        System.out.println("\tdataNascimento=" + this.dateFormat.format(this.dataNascimento) + ",");
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

    public void updateCpf(String value)
    {
        this.cpf = value;
    }

    public String getRg()
    {
        return this.rg;
    }

    public void updateRg(String value)
    {
        this.rg = value;
    }

    public Date getDataNascimento()
    {
        return this.dataNascimento;
    }

    public void updateDataNascimento(String value) throws ParseException
    {
        this.dataNascimento = this.dateFormat.parse(value);
    }
}
