package com.model.entity.receita;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.model.entity.receita.ReceitaExceptions.*;

public class Receita
{
    private Integer id;
    private int idCliente;
    private int idTipoReceita;
    private double valor;
    private Date dataReceita;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public Receita(
        Integer id,
        int idCliente,
        int idTipoReceita,
        double valor,
        Date dataReceita
    ) throws ReceitaException
    {
        this.id = id;
        this.idCliente = idCliente;
        this.idTipoReceita = idTipoReceita;
        this.valor = valor;
        this.dataReceita = dataReceita;

        this.validarValor();
    	this.validarDataReceita();
    }

    public Receita(
        int idCliente,
        int idTipoReceita,
        double valor,
        Date dataReceita
    ) throws ReceitaException
    {
        this.idCliente = idCliente;
        this.idTipoReceita = idTipoReceita;
        this.valor = valor;
        this.dataReceita = dataReceita;

        this.validarValor();
    	this.validarDataReceita();
    }

    private void validarValor() throws ValorInvalidoException
    {
        if (!this.valorEValido(this.valor))
            throw new ValorInvalidoException();
    }

    private boolean valorEValido(Double value)
    {
        return value > 0;
    }
    
    private void validarDataReceita() throws DataReceitaInvalidaException
    {
    	if (!this.dataReceitaEvalida(this.dataReceita))
    		throw new DataReceitaInvalidaException();
    }

    private boolean dataReceitaEvalida(Date dataReceita)
    {
		return this.dataReceita != null;
	}

	public void data()
    {
        System.out.println("Receita(");
        System.out.println("\tid=" + this.id + ",");
        System.out.println("\tidCliente=" + this.idCliente + ",");
        System.out.println("\tidTipoReceita=" + this.idTipoReceita + ",");
        System.out.println("\tvalor=" + this.valor + ",");
        System.out.println("\tdataReceita=" + this.dateFormat.format(this.dataReceita) + ",");
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

    public int getIdTipoReceita()
    {
        return this.idTipoReceita;
    }

    public void updateIdTipoReceita(int value)
    {
        this.idTipoReceita = value;
    }

    public double getValor()
    {
        return this.valor;
    }

    public void updateValor(double value) throws ValorInvalidoException
    {
    	if (!this.valorEValido(value))
            throw new ValorInvalidoException();
    	
        this.valor = value;
    }

    public Date getDataReceita()
    {
        return this.dataReceita;
    }

    public void updateDataReceita(Date value) throws DataReceitaInvalidaException
    {
    	if (!this.dataReceitaEvalida(this.dataReceita))
    		throw new DataReceitaInvalidaException();
    	
        this.dataReceita = value;
    }
}