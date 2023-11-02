package com.entity.telefone;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.entity.telefone.TelefoneExceptions.*;

public class Telefone
{
    private Integer id;
    private Integer idCliente;
    private String numero;
    private String ramal;
    private int ddd;

    public Telefone(
        Integer id,
        Integer idCliente,
        String numero,
        String ramal,
        int ddd
    ) throws TelefoneException
    {
        this.id = id;
        this.idCliente = idCliente;
        this.numero = numero;
        this.ramal = ramal;
        this.ddd = ddd;

        this.validarNumero();
        this.validarDdd();
        this.validarRamal();
    }

    public Telefone(
        Integer idCliente,
        String numero,
        String ramal,
        int ddd
    ) throws TelefoneException
    {
        this.idCliente = idCliente;
        this.numero = numero;
        this.ramal = ramal;
        this.ddd = ddd;

        this.validarNumero();
        this.validarDdd();
        this.validarRamal();
    }

    public Telefone(
        String numero,
        String ramal,
        int ddd
    ) throws TelefoneException
    {
        this.numero = numero;
        this.ramal = ramal;
        this.ddd = ddd;

        this.validarNumero();
        this.validarDdd();
        this.validarRamal();
    }

    private void validarNumero() throws NumeroInvalidoException
    {
        if (!this.numeroEValido(this.numero))
            throw new NumeroInvalidoException();
    }

    private boolean numeroEValido(String telefone)
    {
        return eTelefoneFixoValido(telefone) || eCelularValido(telefone);
    }

    private boolean eTelefoneFixoValido(String telefone)
    {
        Pattern fixedPattern = Pattern.compile("^\\d{8}$");
        Matcher fixedMatcher = fixedPattern.matcher(telefone);

        return fixedMatcher.matches() && !eSequencia(telefone);
    }

    private boolean eCelularValido(String telefone)
    {
        Pattern mobilePattern = Pattern.compile("^9\\d{8}$");
        Matcher mobileMatcher = mobilePattern.matcher(telefone);

        return mobileMatcher.matches() && !eSequencia(telefone);
    }

    private boolean eSequencia(String telefone) {
        String sequentialPattern = "01234567890123456789";

        return sequentialPattern.contains(telefone);
    }

    private void validarDdd() throws DddInvalidoException
    {
        if (!this.dddEValido())
            throw new DddInvalidoException();
    }

    private boolean dddEValido()
    {
        int[] ddds = {11, 12, 13, 14, 15, 16, 17, 18, 19, 21, 22, 24, 27, 28, 31, 32, 33, 34, 35,
                      37, 38, 41, 42, 43, 44, 45, 46, 47, 48, 49, 51, 53, 54, 55, 61, 62, 63, 64,
                      65, 66, 67, 68, 69, 71, 73, 74, 75, 77, 79, 81, 82, 83, 84, 85, 86, 87, 88,
                      89, 91, 92, 93, 94, 95, 96, 97, 98, 99};

        return Arrays.stream(ddds).anyMatch(ddd -> ddd == this.ddd);
    }

    private void validarRamal() throws RamalInvalidoException
    {
        if (!this.ramalEValido())
            throw new RamalInvalidoException();
    }

    private boolean ramalEValido()
    {
        if (this.ramal != null)
        {
            int ramalMinSize = 1;
            int ramalMaxSize = 4;

            if (this.ramal.isEmpty() ||
                this.ramal.length() < ramalMinSize ||
                this.ramal.length() > ramalMaxSize)
                return false;

            for (char c : this.ramal.toCharArray())
                if (!Character.isDigit(c))
                    return false;
        }

        return true;
    }

    public void data(boolean tab)
    {
        String tab_char = tab ? "\t\t" : "";

        System.out.println(tab_char + "Telefone(");
        System.out.println(tab_char + "\tid=" + this.id + ",");
        System.out.println(tab_char + "\tidCliente=" + this.idCliente + ",");
        System.out.println(tab_char + "\tnumero=" + this.numero + ",");
        System.out.println(tab_char + "\tramal=" + this.ramal + ",");
        System.out.println(tab_char + "\tddd=" + this.ddd + ",");
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

    public String getNumero()
    {
        return this.numero;
    }

    public void updateNumero(String value) throws NumeroInvalidoException
    {
        if (!this.numeroEValido(value))
            throw new NumeroInvalidoException();

        this.numero = value;
    }

    public String getRamal()
    {
        return this.ramal;
    }

    public void updateRamal(String value) throws RamalInvalidoException
    {
        this.ramal = value;

        if (!this.ramalEValido())
            throw new RamalInvalidoException();
    }

    public int getDdd()
    {
        return this.ddd;
    }

    public void updateDdd(int value) throws DddInvalidoException
    {
        this.ddd = value;

        if (!this.dddEValido())
            throw new DddInvalidoException();
    }
}
