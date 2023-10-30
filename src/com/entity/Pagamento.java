package com.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Pagamento
{
    private Integer id;
    private int idConta;
    private int idTipoPagamento;
    private String codigoBarras;
    private String nome;
    private Date dataPagamento = Calendar.getInstance().getTime();
    private double valor;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    
    public Pagamento(
        Integer id,
        int idConta,
        int idTipoPagamento,
        String codigoBarras,
        String nome,
        String dataPagamento,
        double valor
    ) throws ParseException
    {
        this.id = id;
        this.idConta = idConta;
        this.idTipoPagamento = idTipoPagamento;
        this.codigoBarras = codigoBarras;
        this.nome = nome;
        this.dataPagamento = this.dateFormat.parse(dataPagamento);
        this.valor = valor;
    }

    public Pagamento(
        int idConta,
        int idTipoPagamento,
        String codigoBarras,
        String nome,
        double valor
    )
    {
        this.idConta = idConta;
        this.idTipoPagamento = idTipoPagamento;
        this.codigoBarras = codigoBarras;
        this.nome = nome;
        this.valor = valor;
    }

    public void data()
    {
        System.out.println("Pagamento(");
        System.out.println("\tid=" + this.id + ",");
        System.out.println("\tidConta=" + this.idConta + ",");
        System.out.println("\tidTipoPagamento=" + this.idTipoPagamento + ",");
        System.out.println("\tcodigoBarras=" + this.codigoBarras + ",");
        System.out.println("\tdataReceita=" + this.dateFormat.format(this.dataPagamento) + ",");
        System.out.println("\tvalor=" + this.valor + ",");
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

    public int getIdTipoPagamento()
    {
        return this.idTipoPagamento;
    }

    public void updateIdTipoPagamento(int idTipoPagamento)
    {
        this.idTipoPagamento = idTipoPagamento;
    }

    public String getCodigoBarras()
    {
        return this.codigoBarras;
    }

    public String getNome()
    {
        return this.nome;
    }

    public void updateNome(String nome)
    {
        this.nome = nome;
    }

    public Date getDataPagamento()
    {
        return this.dataPagamento;
    }

    public double getValor()
    {
        return this.valor;
    }
}
