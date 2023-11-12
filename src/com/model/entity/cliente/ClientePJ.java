package com.model.entity.cliente;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import com.model.entity.cliente.exceptions.ClienteExceptions.ClienteException;
import com.model.entity.cliente.exceptions.ClientePJExceptions.*;
import com.model.entity.endereco.Endereco;
import com.model.entity.telefone.Telefone;

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
        Date dataAbertura,
        String setor
    ) throws ClienteException
    {
        super(id, nome, email, senha);
        this.cnpj = cnpj.strip();
        this.inscricaoEstadual = inscricaoEstadual.strip();
        this.setor = setor.strip();
        this.dataAbertura = dataAbertura;

        this.validarCnpj();
        this.validarInscricaoEstadual();
        this.validarSetor();
        this.validarDataAbertura();
    }

    public ClientePJ(
        String nome,
        String email,
        String senha,
        String cnpj,
        String inscricaoEstadual,
        Date dataAbertura,
        String setor
    ) throws ClienteException
    {
        super(nome, email, senha);
        this.cnpj = cnpj.strip();
        this.inscricaoEstadual = inscricaoEstadual.strip();
        this.setor = setor.strip();
        this.dataAbertura = dataAbertura;

        this.validarCnpj();
        this.validarInscricaoEstadual();
        this.validarSetor();
        this.validarDataAbertura();
    }

    @Override
    protected boolean nomeEValido(String nome)
    {
        return nome instanceof String &&
        	   !nome.isEmpty() &&
        	   nome.length() <= 50;
    }

    private void validarCnpj() throws CnpjInvalidoException
    {
        if (!this.cnpjEValido(this.cnpj))
            throw new CnpjInvalidoException();
    }

    private boolean cnpjEValido(String cnpj)
    {
        int cnpjSize = 14;

        if (cnpj.matches("(\\d)\\1{13}") || cnpj.length() != cnpjSize)
            return false;

        int[] cnpjDigits = new int[14];
        for (int i = 0; i < 14; i++)
            cnpjDigits[i] = Character.getNumericValue(cnpj.charAt(i));

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
        if (!this.inscricaoEstadualEValida(this.inscricaoEstadual))
            throw new InscricaoEstadualInvalidaException();
    }

    private boolean inscricaoEstadualEValida(String inscricaoEstadual)
    {
        int inscEstadualMinSize = 7;
        int inscEstadualMaxSize = 20;

        if (inscricaoEstadual.isEmpty() ||
            inscricaoEstadual.length() < inscEstadualMinSize ||
            inscricaoEstadual.length() > inscEstadualMaxSize)
            return false;

        for (char c : inscricaoEstadual.toCharArray())
            if (!Character.isDigit(c))
                return false;

        return true;
    }

    private void validarSetor() throws SetorInvalidoException
    {
        if (!this.setorEValido(this.setor))
            throw new SetorInvalidoException();
    }

    private boolean setorEValido(String setor)
    {
        return setor instanceof String &&
               !setor.isEmpty() &&
               setor.length() <= 30;
    }

    private void validarDataAbertura() throws DataAberturaInvalidaException
    {
        if (!this.dataAberturaEValida(this.dataAbertura))
            throw new DataAberturaInvalidaException();
    }

    private boolean dataAberturaEValida(Date dataAbertura)
    {
    	if (dataAbertura != null)
    	{
    		Calendar calendar = Calendar.getInstance();
    		
    		calendar.add(Calendar.DAY_OF_YEAR, -1);
    		
    		Date ontem = calendar.getTime();
    		
    		return dataAbertura.before(ontem);    		
    	}
    	
    	return false;
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
        if (!this.cnpjEValido(value))
            throw new CnpjInvalidoException();

        this.cnpj = value;
    }

    public String getInscricaoEstadual()
    {
        return this.inscricaoEstadual;
    }

    public void updateInscricaoEstadual(String value) throws InscricaoEstadualInvalidaException
    {
        if (!this.inscricaoEstadualEValida(value))
            throw new InscricaoEstadualInvalidaException();

        this.inscricaoEstadual = value;
    }

    public Date getDataAbertura()
    {
        return this.dataAbertura;
    }

    public void updateDataAbertura(Date value) throws ParseException, DataAberturaInvalidaException
    {
        if (!this.dataAberturaEValida(value))
            throw new DataAberturaInvalidaException();

        this.dataAbertura = value;
    }

    public String getSetor()
    {
        return this.setor;
    }

    public void updateSetor(String value) throws SetorInvalidoException
    {
        if (!this.setorEValido(value))
            throw new SetorInvalidoException();

        this.setor = value;
    }
}


