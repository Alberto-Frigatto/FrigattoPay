package com.model.entity.cliente;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import com.model.entity.cliente.exceptions.ClienteExceptions.ClienteException;
import com.model.entity.cliente.exceptions.ClientePFExceptions.*;
import com.model.entity.endereco.Endereco;
import com.model.entity.telefone.Telefone;

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
        Date dataNascimento
    ) throws ClienteException
    {
        super(id, nome, email, senha);
        this.cpf = cpf.strip();
        this.rg = rg.strip();
        this.dataNascimento = dataNascimento;

        this.validarCpf();
        this.validarRg();
		this.validarDataNascimento();
    }

    public ClientePF(
        String nome,
        String email,
        String senha,
        String cpf,
        String rg,
        Date dataNascimento
    ) throws ClienteException
    {
        super(nome, email, senha);
        this.cpf = cpf.strip();
        this.rg = rg.strip();
        this.dataNascimento = dataNascimento;

        this.validarCpf();
        this.validarRg();
		this.validarDataNascimento();
    }

    @Override
    protected boolean nomeEValido(String nome)
    {
        return nome instanceof String &&
        	   !nome.isEmpty() &&
        	   nome.length() <= 50 &&
        	   nome.matches("^[^0-9]*$");
    }

    private void validarCpf() throws CpfInvalidoException
    {
        if (!this.cpfEValido(this.cpf))
            throw new CpfInvalidoException();
    }

    private boolean cpfEValido(String cpf)
    {
        if (cpf.matches("(\\d)\\1{10}") || cpf.strip().length() != 11)
            return false;

        int[] cpfDigits = new int[11];

        for (int i = 0; i < 11; i++)
            cpfDigits[i] = Character.getNumericValue(cpf.charAt(i));

        for (int t = 9; t < 11; t++)
        {
            int d = 0;
            for (int c = 0; c < t; c++)
                d += cpfDigits[c] * ((t + 1) - c);

            d = ((10 * d) % 11) % 10;

            if (cpfDigits[t] != d)
                return false;
        }

        return true;
    }

    private void validarRg() throws RgInvalidoException
    {
        if (!this.rgEValido(this.rg))
            throw new RgInvalidoException();
    }

    private boolean rgEValido(String rg)
    {
        int minSize = 8;
        int maxSize = 9;

        if (rg.isEmpty() ||
        	rg.length() < minSize ||
        	rg.length() > maxSize)
            return false;

        for (char c : rg.toCharArray())
            if (!Character.isDigit(c))
                return false;

        return true;
    }

    private void validarDataNascimento() throws DataNascimentoInvalidaException
    {
        if (!this.dataNascimentoEValida(this.dataNascimento))
            throw new DataNascimentoInvalidaException();
    }

    private boolean dataNascimentoEValida(Date dataNascimento)
    {
    	if (dataNascimento != null)
    	{    		
    		Calendar calendar = Calendar.getInstance();
    		
    		int anosMaioridade = 18;
    		
    		calendar.add(Calendar.YEAR, -anosMaioridade);
    		
    		Date dataMaioridade = calendar.getTime();
    		
    		return dataNascimento.before(dataMaioridade);
    	}
    	
    	return false;
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

    public void updateCpf(String value) throws CpfInvalidoException
    {
        if (!this.cpfEValido(value))
            throw new CpfInvalidoException();

        this.cpf = value;
    }

    public String getRg()
    {
        return this.rg;
    }

    public void updateRg(String value) throws RgInvalidoException
    {
    	if (!this.rgEValido(value))
    		throw new RgInvalidoException();

        this.rg = value;
    }

    public Date getDataNascimento()
    {
        return this.dataNascimento;
    }

    public void updateDataNascimento(Date value) throws ParseException, DataNascimentoInvalidaException
    {
        if (!this.dataNascimentoEValida(value))
            throw new DataNascimentoInvalidaException();

        this.dataNascimento = value;
    }
}
