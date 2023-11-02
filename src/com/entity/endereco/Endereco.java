package com.entity.endereco;

import com.entity.endereco.EnderecoExceptions.*;

public class Endereco
{
    private Integer id;
    private Integer idCliente;
    private int idUF;
    private String cep;
    private String logradouro;
    private int numero;
    private String complemento;
    private String municipio;

    public Endereco(
        Integer id,
        Integer idCliente,
        int idUF,
        String cep,
        String logradouro,
        int numero,
        String complemento,
        String municipio
    ) throws EnderecoException
    {
        this.id = id;
        this.idCliente = idCliente;
        this.idUF = idUF;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.municipio = municipio;

        this.validarCep();
        this.validarLogradouro();
        this.validarNumero();
        this.validarComplemento();
        this.validarMunicipio();
    }

    public Endereco(
        Integer idCliente,
        int idUF,
        String cep,
        String logradouro,
        int numero,
        String complemento,
        String municipio
    ) throws EnderecoException
    {
        this.idCliente = idCliente;
        this.idUF = idUF;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.municipio = municipio;

        this.validarCep();
        this.validarLogradouro();
        this.validarNumero();
        this.validarComplemento();
        this.validarMunicipio();
    }

    public Endereco(
        int idUF,
        String cep,
        String logradouro,
        int numero,
        String complemento,
        String municipio
    ) throws EnderecoException
    {
        this.idUF = idUF;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.municipio = municipio;

        this.validarCep();
        this.validarLogradouro();
        this.validarNumero();
        this.validarComplemento();
        this.validarMunicipio();
    }

    private void validarCep() throws CepInvalidoException
    {
        if (!this.cepEValido())
            throw new CepInvalidoException();
    }

    private boolean cepEValido()
    {
        int cepSize = 8;

        if (this.cep.isEmpty() ||
            this.cep.length() != cepSize)
            return false;

        for (char c : this.cep.toCharArray())
            if (!Character.isDigit(c))
                return false;

        return true;
    }

    private void validarLogradouro() throws LogradouroInvalidoException
    {
        if (!this.logradouroEValido())
            throw new LogradouroInvalidoException();
    }

    private boolean logradouroEValido()
    {
        int maxSize = 40;

        if (this.logradouro.isEmpty() ||
            this.logradouro.length() > maxSize ||
            this.logradouro.matches("\\d"))
            return false;

        return true;
    }

    private void validarNumero() throws NumeroInvalidoException
    {
        if (!this.numeroEValido())
            throw new NumeroInvalidoException();
    }

    private boolean numeroEValido()
    {
        int minValue = 1;
        int maxValue = 9999;

        if (this.numero < minValue ||
            this.numero > maxValue)
            return false;

        return true;
    }

    private void validarComplemento() throws ComplementoInvalidoException
    {
        if (!this.complementoEValido())
            throw new ComplementoInvalidoException();
    }
    
    private boolean complementoEValido()
    {
        if (this.complemento != null)
        {
            int complementoMaxSize = 30;
            
            if (this.complemento.isEmpty() ||
                this.complemento.length() > complementoMaxSize)
                return false;
        }
        
        return true;
    }

    private void validarMunicipio() throws MunicipioInvalidoException
    {
        if (!this.municipioEValido())
            throw new MunicipioInvalidoException();
    }

    private boolean municipioEValido()
    {
        int maxSize = 70;

        if (this.municipio.isEmpty() ||
            this.municipio.length() > maxSize ||
            this.municipio.matches("\\d"))
            return false;

        return true;
    }

    public void data(boolean tab)
    {
        String tab_char = tab ? "\t\t" : "";

        System.out.println(tab_char + "Endereco(");
        System.out.println(tab_char + "\tid=" + this.id + ",");
        System.out.println(tab_char + "\tidCliente=" + this.idCliente + ",");
        System.out.println(tab_char + "\tidUF=" + this.idUF + ",");
        System.out.println(tab_char + "\tcep=" + this.cep + ",");
        System.out.println(tab_char + "\tlogradouro=" + this.logradouro + ",");
        System.out.println(tab_char + "\tnumero=" + this.numero + ",");
        System.out.println(tab_char + "\tcomplemento=" + this.complemento + ",");
        System.out.println(tab_char + "\tmunicipio=" + this.municipio + ",");
        System.out.println(tab_char + ")\n");
    }

    public Integer getId()
    {
        return this.id;
    }

    public Integer getIdCliente()
    {
        return this.idCliente;
    }

    public int getIdUF()
    {
        return this.idUF;
    }

    public void updateIdUF(int value)
    {
        this.idUF = value;
    }

    public String getCep()
    {
        return this.cep;
    }

    public void updateCep(String value) throws CepInvalidoException
    {
        this.cep = value;

        if (!this.cepEValido())
            throw new CepInvalidoException();
    }

    public String getLogradouro()
    {
        return this.logradouro;
    }

    public void updateLogradouro(String value) throws LogradouroInvalidoException
    {
        this.logradouro = value;

        if (!this.logradouroEValido())
            throw new LogradouroInvalidoException();
    }

    public int getNumero()
    {
        return this.numero;
    }

    public void updateNumero(int value) throws NumeroInvalidoException
    {
        this.numero = value;

        if (!this.numeroEValido())
            throw new NumeroInvalidoException();
    }

    public String getComplemento()
    {
        return this.complemento;
    }

    public void updateComplemento(String value) throws ComplementoInvalidoException
    {
        this.complemento = value;

        if (!this.complementoEValido())
            throw new ComplementoInvalidoException();
    }

    public String getMunicipio()
    {
        return this.municipio;
    }

    public void updateMunicipio(String value) throws MunicipioInvalidoException
    {
        this.municipio = value;

        if (!this.municipioEValido())
            throw new MunicipioInvalidoException();
    }
}
