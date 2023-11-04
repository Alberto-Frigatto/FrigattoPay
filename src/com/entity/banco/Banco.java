package com.entity.banco;

import com.entity.banco.BancoExceptions.*;

public class Banco
{
    private Integer id;
    private String cnpj;
    private String nome;
    private String email;

    public Banco(
        Integer id,
        String cnpj,
        String nome,
        String email
    ) throws BancoException
    {
        this.id = id;
        this.cnpj = cnpj.strip();
        this.nome = nome.strip();
        this.email = email.strip();

        this.validarCnpj();
        this.validarNome();
        this.validarEmail();
    }

    public Banco(
        String cnpj,
        String nome,
        String email
    ) throws BancoException
    {
        this.cnpj = cnpj.strip();
        this.nome = nome.strip();
        this.email = email.strip();

        this.validarCnpj();
        this.validarNome();
        this.validarEmail();
    }

    public void data()
    {
        System.out.println("Banco(");
        System.out.println("\tid=" + this.id + ",");
        System.out.println("\tcnpj=" + this.cnpj + ",");
        System.out.println("\tnome=" + this.nome + ",");
        System.out.println("\temail=" + this.email + ",");
        System.out.println(")\n");
    }
    
    private void validarCnpj() throws CnpjInvalidoException
    {
        if (!this.cnpjEValido())
            throw new CnpjInvalidoException();
    }

    private boolean cnpjEValido()
    {
        int cnpjSize = 14;

        if (this.cnpj.matches("(\\d)\\1{13}") || this.cnpj.length() != cnpjSize)
            return false;

        int[] cnpjDigits = new int[14];
        for (int i = 0; i < 14; i++)
            cnpjDigits[i] = Character.getNumericValue(this.cnpj.charAt(i));

        int[] weights = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    
        int sum = 0;
        for (int i = 0; i < 12; i++)
        sum += cnpjDigits[i] * weights[i];
        
        int rest = sum % 11;
        int expectedDigit = (rest < 2) ? 0 : 11 - rest;
        if (cnpjDigits[12] != expectedDigit)
        return false;
        
        weights = new int[]{6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        
        sum = 0;
        for (int i = 0; i < 13; i++)
        sum += cnpjDigits[i] * weights[i];
        
        rest = sum % 11;
        expectedDigit = (rest < 2) ? 0 : 11 - rest;
        return cnpjDigits[13] == expectedDigit;
    }

    private void validarNome() throws NomeInvalidoException
    {
        if (!this.nomeEValido())
            throw new NomeInvalidoException();
    }

    private boolean nomeEValido()
    {
        return this.nome instanceof String &&
                !this.nome.isEmpty() &&
                this.nome.length() <= 50;
    }

    private void validarEmail() throws EmailInvalidoException
    {
        if (!this.emailEValido())
            throw new EmailInvalidoException();
    }

    private boolean emailEValido()
    {
        String emailRegex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9]+\\.)+[A-Za-z]{2,}$";

        return this.email.matches(emailRegex);
    }
    
    public Integer getId()
    {
        return this.id;
    }

    public String getCnpj()
    {
        return this.cnpj;
    }

    public void updateCnpj(String value) throws CnpjInvalidoException
    {
        this.cnpj = value;

        if (!this.cnpjEValido())
            throw new CnpjInvalidoException();
    }

    public String getNome()
    {
        return this.nome;
    }

    public void updateNome(String value) throws NomeInvalidoException
    {
        this.nome = value;

        if (!this.nomeEValido())
            throw new NomeInvalidoException();
    }

    public String getEmail()
    {
        return this.email;
    }

    public void updateEmail(String value) throws EmailInvalidoException
    {
        this.email = value;

        if (!this.emailEValido())
            throw new EmailInvalidoException();
    }
}