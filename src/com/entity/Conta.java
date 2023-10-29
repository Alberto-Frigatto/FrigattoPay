package com.entity;

public class Conta
{
    private Integer id;
    private int idCliente;
    private int idBanco;
    private int idTipoConta;
    private String numero;
    private double saldo;
    private String agencia;

    public Conta(
        Integer id,
        int idCliente,
        int idBanco,
        int idTipoConta,
        String numero,
        double saldo,
        String agencia
    )
    {
        this.id = id;
        this.idCliente = idCliente;
        this.idBanco = idBanco;
        this.idTipoConta = idTipoConta;
        this.numero = numero;
        this.saldo = saldo;
        this.agencia = agencia;
    }

    public Conta(
        int idCliente,
        int idBanco,
        int idTipoConta,
        String numero,
        double saldo,
        String agencia
    )
    {
        this.idCliente = idCliente;
        this.idBanco = idBanco;
        this.idTipoConta = idTipoConta;
        this.numero = numero;
        this.saldo = saldo;
        this.agencia = agencia;
    }

    public void data()
    {
        System.out.println("Conta(");
        System.out.println("\tid=" + this.id + ",");
        System.out.println("\tidCliente=" + this.idCliente + ",");
        System.out.println("\tidTipoConta=" + this.idTipoConta + ",");
        System.out.println("\tnumero=" + this.numero + ",");
        System.out.println("\tsaldo=" + this.saldo + ",");
        System.out.println("\tagencia=" + this.agencia + ",");
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

    public int getIdBanco()
    {
        return this.idBanco;
    }

    public int getIdTipoConta()
    {
        return this.idTipoConta;
    }

    public String getNumero()
    {
        return this.numero;
    }

    public double getSaldo()
    {
        return this.saldo;
    }

    public void updateSaldo(double saldo)
    {
        this.saldo = saldo;
    }

    public String getAgencia()
    {
        return this.agencia;
    }
}
