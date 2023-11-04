package com.entity.conta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.entity.conta.exceptions.CartaoExceptions.*;

public class Cartao
{
    private Integer id;
    private String numero;
    private int idConta;
    private int idBandeira;
    private int idTipoCartao;
    private Date dataValidade;
    private String cvv;
    private boolean desbloqueado;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM-yy");

    public Cartao(
        Integer id,
        int idConta,
        int idBandeira,
        int idTipoCartao,
        String numero,
        String dataValidade,
        String cvv,
        boolean desbloqueado
    ) throws CartaoException
    {
        this.id = id;
        this.idConta = idConta;
        this.idBandeira = idBandeira;
        this.idTipoCartao = idTipoCartao;
        this.numero = numero.strip();
        this.cvv = cvv.strip();
        this.desbloqueado = desbloqueado;

        this.validarNumero();
        this.validarCvv();
        this.definirDataValidadeSeValida(dataValidade);
    }

    public Cartao(
        int idConta,
        int idBandeira,
        int idTipoCartao,
        String numero,
        String dataValidade,
        String cvv,
        boolean desbloqueado
    ) throws CartaoException
    {
        this.idConta = idConta;
        this.idBandeira = idBandeira;
        this.idTipoCartao = idTipoCartao;
        this.numero = numero.strip();
        this.cvv = cvv.strip();
        this.desbloqueado = desbloqueado;

        this.validarNumero();
        this.validarCvv();
        this.definirDataValidadeSeValida(dataValidade);
    }

    private void validarNumero() throws NumeroInvalidoException
    {
        if (!this.numeroEValido())
            throw new NumeroInvalidoException();
    }

    private boolean numeroEValido()
    {
        if (this.numero.isEmpty() ||
            this.numero.length() < 15 || 
            this.numero.length() > 16)
            return false;

        for (char c : this.numero.toCharArray())
            if (!Character.isDigit(c))
                return false;

        return true;
    }

    private void definirDataValidadeSeValida(String dataValidade) throws DataValidadeInvalidaException
    {
        try
        {
            this.dataValidade = this.dateFormat.parse(dataValidade);
        }
        catch (ParseException e)
        {
            throw new DataValidadeInvalidaException();
        }
    }

    private void validarCvv() throws CvvInvalidoException
    {
        if (!this.cvvEValido())
            throw new CvvInvalidoException();
    }

    private boolean cvvEValido()
    {
        if (this.cvv.isEmpty() ||
            this.cvv.length() < 3 ||
            this.cvv.length() > 4)
            return false;

        for (char c : this.cvv.toCharArray())
            if (!Character.isDigit(c))
                return false;

        return true;
    }

    public void data()
    {
        System.out.println("Cartao(");
        System.out.println("\tid=" + this.id + ",");
        System.out.println("\tnumero=" + this.numero + ",");
        System.out.println("\tidConta=" + this.idConta + ",");
        System.out.println("\tidBandeira=" + this.idBandeira + ",");
        System.out.println("\tidTipoCartao=" + this.idTipoCartao + ",");
        System.out.println("\tdataValidade=" + this.dateFormat.format(this.dataValidade) + ",");
        System.out.println("\tcvv=" + this.cvv + ",");
        System.out.println("\tdesbloqueado=" + this.desbloqueado + ",");
        System.out.println(")\n");
    }

    public Integer getId()
    {
        return this.id;
    }

    public String getNumero()
    {
        return this.numero;
    }

    public int getIdConta()
    {
        return this.idConta;
    }

    public int getIdBandeira()
    {
        return this.idBandeira;
    }

    public int getIdTipoCartao()
    {
        return this.idTipoCartao;
    }

    public Date getDataValidade()
    {
        return this.dataValidade;
    }

    public String getCvv()
    {
        return this.cvv;
    }

    public boolean getDesbloqueado()
    {
        return this.desbloqueado;
    }

    public void updateDesbloqueado(boolean desbloqueado)
    {
        this.desbloqueado = desbloqueado;
    }
}
