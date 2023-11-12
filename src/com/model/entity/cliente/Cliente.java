package com.model.entity.cliente;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.model.entity.cliente.exceptions.ClienteExceptions.*;
import com.model.entity.endereco.Endereco;
import com.model.entity.telefone.Telefone;

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
    ) throws ClienteException
    {
        this.id = id;
        this.nome = nome.strip();
        this.email = email.strip();
        this.senha = senha.strip();

        this.validarNome();
        this.validarEmail();
    }

    public Cliente(
        String nome,
        String email,
        String senha
    ) throws ClienteException
    {
        this.nome = nome.strip();
        this.email = email.strip();
        this.senha = senha.strip();

        this.validarNome();
        this.validarEmail();
        this.criptografarSenhaSeValida();
    }

    private void validarNome() throws NomeInvalidoException
    {
        if (!this.nomeEValido(this.nome))
            throw new NomeInvalidoException();
    }

    protected abstract boolean nomeEValido(String nome);

    private void validarEmail() throws EmailInvalidoException
    {
        if (!this.emailEValido(this.email))
            throw new EmailInvalidoException();
    }

    private boolean emailEValido(String email)
    {
        String emailRegex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9]+\\.)+[A-Za-z]{2,}$";

        return email.matches(emailRegex);
    }

    private void criptografarSenhaSeValida() throws SenhaInvalidaException
    {
        if (!this.senhaEValida())
            throw new SenhaInvalidaException();

        this.senha = criptografarSenha(this.senha);
    }

    private boolean senhaEValida()
    {
        String senhaRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!_\\-()&¨%'\"<>:;/?~^{}´`]).{8,}$";

        return this.senha.matches(senhaRegex);
    }
    private boolean senhaEValida(String senha)
    {
        String senhaRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!_\\-()&¨%'\"<>:;/?~^{}´`]).{8,}$";

        return senha.matches(senhaRegex);
    }

    private String criptografarSenha(String senha)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(senha.getBytes());

            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashedBytes)
                stringBuilder.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));

            return stringBuilder.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException("Error hashing password.");
        }
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

    public String getNome()
    {
        return this.nome;
    }

    public void updateNome(String value) throws NomeInvalidoException
    {
        if (!this.nomeEValido(value))
            throw new NomeInvalidoException();

        this.nome = value;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void updateEmail(String value) throws EmailInvalidoException
    {
        if (!this.emailEValido(value))
            throw new EmailInvalidoException();

        this.email = value;
    }

    public String getSenha()
    {
        return this.senha;
    }

    public void updateSenha(String value) throws SenhaInvalidaException
    {
        if (!this.senhaEValida(value))
            throw new SenhaInvalidaException();

        this.senha = criptografarSenha(this.senha);
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
