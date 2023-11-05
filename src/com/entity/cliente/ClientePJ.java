package com.entity.cliente;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import com.entity.cliente.exceptions.ClienteExceptions.ClienteException;
import com.entity.cliente.exceptions.ClientePJExceptions.*;
import com.entity.endereco.Endereco;
import com.entity.telefone.Telefone;

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
    ) throws ClienteException
    {
        super(id, nome, email, senha);
        this.cnpj = cnpj.strip();
        this.inscricaoEstadual = inscricaoEstadual.strip();
        this.setor = setor.strip();

        this.validarCnpj();
        this.validarInscricaoEstadual();
        this.validarSetor();
        this.definirDataAberturaSeValida(dataAbertura);
    }

    public ClientePJ(
        String nome,
        String email,
        String senha,
        String cnpj,
        String inscricaoEstadual,
        String dataAbertura,
        String setor
    ) throws ClienteException
    {
        super(nome, email, senha);
        this.cnpj = cnpj.strip();
        this.inscricaoEstadual = inscricaoEstadual.strip();
        this.setor = setor.strip();

        this.validarCnpj();
        this.validarInscricaoEstadual();
        this.validarSetor();
        this.definirDataAberturaSeValida(dataAbertura);
    }

    @Override
    protected boolean nomeEValido()
    {
        return this.getNome() instanceof String &&
               !this.getNome().isEmpty() &&
               this.getNome().length() <= 50;
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

    private void validarInscricaoEstadual() throws InscricaoEstadualInvalidaException
    {
        if (!this.inscricaoEstadualEValida())
            throw new InscricaoEstadualInvalidaException();
    }

    private boolean inscricaoEstadualEValida()
    {
        int inscEstadualMinSize = 15;
        int inscEstadualMaxSize = 20;

        if (this.inscricaoEstadual.isEmpty() ||
            this.inscricaoEstadual.length() < inscEstadualMinSize ||
            this.inscricaoEstadual.length() > inscEstadualMaxSize)
            return false;

        for (char c : this.inscricaoEstadual.toCharArray())
            if (!Character.isDigit(c))
                return false;

        return true;
    }

    private void validarSetor() throws SetorInvalidoException
    {
        if (!this.setorEValido())
            throw new SetorInvalidoException();
    }

    private boolean setorEValido()
    {
        return this.setor instanceof String &&
               !this.setor.isEmpty() &&
               this.setor.length() <= 30;
    }

    private void definirDataAberturaSeValida(String dataAbertura) throws DataAberturaInvalidaException
    {
        try
        {
            this.dataAbertura = this.dateFormat.parse(dataAbertura);
        }
        catch (ParseException e)
        {
            throw new DataAberturaInvalidaException();
        }

        if (!this.dataAberturaEValida(this.dataAbertura))
            throw new DataAberturaInvalidaException();
    }

    private boolean dataAberturaEValida(Date dataAbertura)
    {

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DAY_OF_YEAR, -1);

        Date ontem = calendar.getTime();

        return dataAbertura.before(ontem);
    }

    public void data()
    {
        System.out.println("ClientePJ(");
        System.out.println("\tid=" + this.getId() + ",");
        System.out.println("\tnome=" + this.getNome() + ",");
        System.out.println("\temail=" + this.getEmail() + ",");
        System.out.println("\tsenha=" + this.getSenha() + ",");
        System.out.println("\tcnpj=" + this.cnpj + ",");
        System.out.println("\tinscricaoEstadual=" + this.inscricaoEstadual + ",");
        System.out.println("\tdataAbertura=" + this.dateFormat.format(this.dataAbertura) + ",");
        System.out.println("\tsetor=" + this.setor + ",");
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

    public void updateCnpj(String value) throws CnpjInvalidoException
    {
        this.cnpj = value;

        if (!this.cnpjEValido())
            throw new CnpjInvalidoException();
    }

    public String getInscricaoEstadual()
    {
        return this.inscricaoEstadual;
    }

    public void updateInscricaoEstadual(String value) throws InscricaoEstadualInvalidaException
    {
        this.inscricaoEstadual = value;

        if (!this.inscricaoEstadualEValida())
            throw new InscricaoEstadualInvalidaException();
    }

    public Date getDataAbertura()
    {
        return this.dataAbertura;
    }

    public void updateDataAbertura(String value) throws ParseException, DataAberturaInvalidaException
    {
        this.definirDataAberturaSeValida(value);
    }

    public String getSetor()
    {
        return this.setor;
    }

    public void updateSetor(String value) throws SetorInvalidoException
    {
        this.setor = value;

        if (!this.setorEValido())
            throw new SetorInvalidoException();
    }
}


