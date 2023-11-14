package com.model.entity.despesa;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.model.entity.despesa.DespesaExceptions.*;

public class Despesa
{
    private Integer id;
    private int idCliente;
    private int idTipoDespesa;
    private String nome;
    private double valor;
    private String descricao;
    private Date dataVencimento;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public Despesa(
        Integer id,
        int idCliente,
        int idTipoDespesa,
        String nome,
        double valor,
        String descricao,
        Date dataVencimento
    ) throws DespesaException
    {
        this.id = id;
        this.idCliente = idCliente;
        this.idTipoDespesa = idTipoDespesa;
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.dataVencimento = dataVencimento;

        this.validarNome();
        this.validarValor();
        this.validarDescricao();
        this.validarDataVencimento();
    }

    public Despesa(
        int idCliente,
        int idTipoDespesa,
        String nome,
        double valor,
        String descricao,
        Date dataVencimento
    ) throws DespesaException
    {
        this.idCliente = idCliente;
        this.idTipoDespesa = idTipoDespesa;
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.dataVencimento = dataVencimento;

        this.validarNome();
        this.validarValor();
        this.validarDescricao();
        this.validarDataVencimento();
    }

    private void validarNome() throws NomeInvalidoException
    {
        if (!this.nomeEValido())
            throw new NomeInvalidoException();
    }

    protected boolean nomeEValido()
    {
        return this.nome instanceof String &&
               !this.nome.isEmpty() &&
               this.nome.length() <= 30;
    }

    private void validarValor() throws ValorInvalidoException
    {
        if (!this.valorEValido())
            throw new ValorInvalidoException();
    }

    private boolean valorEValido()
    {
        return this.valor > 0;
    }

    private void validarDataVencimento() throws DataVencimentoInvalidaException
    {
        if (!this.dataVencimentoEvalida(this.dataVencimento))
        	throw new DataVencimentoInvalidaException();
    }
    
    private boolean dataVencimentoEvalida(Date dataVencimento)
    {
    	return this.dataVencimento != null;
    }

    private void validarDescricao() throws DescricaoInvalidaException
    {
        if (!this.descricaoEValida())
            throw new DescricaoInvalidaException();
    }

    protected boolean descricaoEValida()
    {
        return this.nome instanceof String &&
                !this.nome.isEmpty() &&
                this.nome.length() <= 300;
    }

    public void data()
    {
        System.out.println("Despesa(");
        System.out.println("\tid=" + this.id + ",");
        System.out.println("\tidCliente=" + this.idCliente + ",");
        System.out.println("\tidTipoDespesa=" + this.idTipoDespesa + ",");
        System.out.println("\tnome=" + this.nome + ",");
        System.out.println("\tvalor=" + this.valor + ",");
        System.out.println("\tdescricao=" + this.descricao + ",");
        System.out.println("\tdataVencimento=" + this.dateFormat.format(this.dataVencimento) + ",");
        System.out.println(")\n");
    }

    public Integer getId()
    {
        return this.id;
    }

    public int getIdCliente()
    {
        return this.idCliente;
    }

    public int getIdTipoDespesa()
    {
        return this.idTipoDespesa;
    }

    public void updateIdTipoDespesa(int value)
    {
        this.idTipoDespesa = value;
    }

    public String getNome()
    {
        return this.nome;
    }

    public void updateNome(String value)
    {
        this.nome = value;
    }

    public double getValor()
    {
        return this.valor;
    }

    public void updateValor(double value)
    {
        this.valor = value;
    }

    public String getDescricao()
    {
        return this.descricao;
    }

    public void updateDescricao(String value)
    {
        this.descricao = value;
    }

    public Date getDataVencimento()
    {
        return this.dataVencimento;
    }

    public void updateDataVencimento(Date value) throws DataVencimentoInvalidaException
    {
    	if (!this.dataVencimentoEvalida(this.dataVencimento))
    		throw new DataVencimentoInvalidaException();
    	
        this.dataVencimento = value;
    }
}