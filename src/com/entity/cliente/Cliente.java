package com.entity.cliente;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.entity.Endereco;
import com.entity.Telefone;

public abstract class Cliente
{
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private List<Telefone> telefones = new ArrayList<Telefone>();
    private List<Endereco> enderecos = new ArrayList<Endereco>();
    protected SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public Cliente(
        Integer id,
        String nome,
        String email,
        String senha
    )
    {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Cliente(
        String nome,
        String email,
        String senha
    )
    {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public void addTelefone(Telefone telefone)
    {
        this.telefones.add(telefone);
    }

    public void addTelefone(List<Telefone> list)
    {
        for (Telefone telefone : list)
        {
            this.telefones.add(telefone);
        }
    }
    
    public void addEndereco(Endereco endereco)
    {
        this.enderecos.add(endereco);
    }

    public void addEndereco(List<Endereco> list)
    {
        for (Endereco endereco : list)
        {
            this.enderecos.add(endereco);
        }
    }

    public Integer getId()
    {
        return this.id;
    }

    public void updateId(Integer value)
    {
        this.id = value;
    }

    public String getNome()
    {
        return this.nome;
    }

    public void updateNome(String value)
    {
        this.nome = value;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void updateEmail(String value)
    {
        this.email = value;
    }

    public String getSenha()
    {
        return this.senha;
    }

    public void updateSenha(String value)
    {
        this.senha = value;
    }

    public List<Telefone> getTelefones()
    {
        return this.telefones;
    }

    public List<Endereco> getEnderecos()
    {
        return this.enderecos;
    }
}
