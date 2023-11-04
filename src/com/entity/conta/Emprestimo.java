package com.entity.conta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.entity.conta.exceptions.EmprestimoExceptions.*;

public class Emprestimo
{
    private Integer id;
    private int idConta;
    private double valorEmprestimo;
    private double valorJuros;
    private Date dataPrazo;
    private Date dataSolicitacao;
    private double valorParcela;
    private int diaVencimentoParcela;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public Emprestimo(
        Integer id,
        int idConta,
        double valorEmprestimo,
        double valorJuros,
        double valorParcela,
        String dataPrazo,
        String dataSolicitacao,
        int diaVencimentoParcela
    ) throws EmprestimoException
    {
        this.id = id;
        this.idConta = idConta;
        this.valorEmprestimo = valorEmprestimo;
        this.valorJuros = valorJuros;
        this.valorParcela = valorParcela;
        this.diaVencimentoParcela = diaVencimentoParcela;

        this.validarValorEmprestimo();
        this.validarValorJuros();
        this.validarValorParcela();
        this.validarDiaVencimentoParcela();
        this.definirDataPrazoSeValida(dataPrazo);
        this.definirDataSolicitacaoSeValida(dataSolicitacao);
    }

    public Emprestimo(
        int idConta,
        double valorEmprestimo,
        double valorJuros,
        double valorParcela,
        String dataPrazo,
        String dataSolicitacao,
        int diaVencimentoParcela
    ) throws EmprestimoException
    {
        this.idConta = idConta;
        this.valorEmprestimo = valorEmprestimo;
        this.valorJuros = valorJuros;
        this.valorParcela = valorParcela;
        this.diaVencimentoParcela = diaVencimentoParcela;

        this.validarValorEmprestimo();
        this.validarValorJuros();
        this.validarValorParcela();
        this.validarDiaVencimentoParcela();
        this.definirDataPrazoSeValida(dataPrazo);
        this.definirDataSolicitacaoSeValida(dataSolicitacao);
    }

    private void validarValorEmprestimo() throws ValorInvalidoException
    {
        if (!this.valorEValido(this.valorEmprestimo))
            throw new ValorInvalidoException();
    }

    private boolean valorEValido(double value)
    {
        return value > 1;
    }

    private void validarValorJuros() throws ValorJurosInvalidoException
    {
        if (!this.valorEValido(this.valorEmprestimo))
            throw new ValorJurosInvalidoException();
    }

    private void validarValorParcela() throws ValorParcelaInvalidoException
    {
        if (!this.valorEValido(this.valorEmprestimo))
            throw new ValorParcelaInvalidoException();
    }

    private void definirDataPrazoSeValida(String dataPrazo) throws DataPrazoInvalidaException
    {
        try
        {
            this.dataPrazo = this.dateFormat.parse(dataPrazo);
        }
        catch (ParseException e)
        {
            throw new DataPrazoInvalidaException();
        }
    }

    private void definirDataSolicitacaoSeValida(String dataSolicitacao) throws DataPrazoInvalidaException
    {
        try
        {
            this.dataSolicitacao = this.dateFormat.parse(dataSolicitacao);
        }
        catch (ParseException e)
        {
            throw new DataPrazoInvalidaException();
        }
    }

    private void validarDiaVencimentoParcela() throws ValorInvalidoException
    {
        if (!this.diaVencimentoParcelaEValido())
            throw new ValorInvalidoException();
    }

    private boolean diaVencimentoParcelaEValido()
    {
        return this.diaVencimentoParcela >= 1 && this.diaVencimentoParcela <= 31;
    }

    public void data()
    {
        System.out.println("Emprestimo(");
        System.out.println("\tid=" + this.id + ",");
        System.out.println("\tidConta=" + this.idConta + ",");
        System.out.println("\tvalorEmprestimo=" + this.valorEmprestimo + ",");
        System.out.println("\tvalorJuros=" + this.valorJuros + ",");
        System.out.println("\tdataPrazo=" + this.dateFormat.format(this.dataPrazo) + ",");
        System.out.println("\tdataSolicitacao=" + this.dateFormat.format(this.dataSolicitacao) + ",");
        System.out.println("\tvalorParcela=" + this.valorParcela + ",");
        System.out.println("\tdiaVencimentoParcela=" + this.diaVencimentoParcela + ",");
        System.out.println(")\n");
    }

    public Integer getId() 
    {
        return this.id;
    }

    public int getIdConta() 
    {
        return this.idConta;
    }

    public double getValorEmprestimo() 
    {
        return this.valorEmprestimo;
    }

    public double getValorJuros() 
    {
        return this.valorJuros;
    }

    public Date getDataPrazo() 
    {
        return this.dataPrazo;
    }

    public Date getDataSolicitacao() 
    {
        return this.dataSolicitacao;
    }

    public double getValorParcela() 
    {
        return this.valorParcela;
    }

    public int getDiaVencimentoParcela()
    {
        return this.diaVencimentoParcela;
    }
}