package com.entity.cliente;

import java.text.ParseException;
import java.util.Date;

import com.entity.Endereco;
import com.entity.Telefone;

public class ClientePJ extends Cliente
{
    private String cnpj;
    private String inscricaoEstadual;
    private Date dataAbertura;
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
    ) throws ParseException
    {
        super(id, nome, email, senha);
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.setor = setor;

        this.dataAbertura = this.dateFormat.parse(dataAbertura);
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
        System.out.println("\tdataAbertura=" + this.dateFormat.format(this.dataAbertura) + ",");
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

    public String getCnpj()
    {
        return this.cnpj;
    }

    public void updateCnpj(String value)
    {
        this.cnpj = value;
    }

    public String getInscricaoEstadual()
    {
        return this.inscricaoEstadual;
    }

    public void updateInscricaoEstadual(String value)
    {
        this.inscricaoEstadual = value;
    }

    public Date getDataAbertura()
    {
        return this.dataAbertura;
    }

    public void updateDataAbertura(String value) throws ParseException
    {
        this.dataAbertura = this.dateFormat.parse(value);
    }

    public String getSetor()
    {
        return this.setor;
    }

    public void updateSetor(String value)
    {
        this.setor = value;
    }
}


