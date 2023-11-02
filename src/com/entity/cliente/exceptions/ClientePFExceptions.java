package com.entity.cliente.exceptions;

import com.entity.cliente.exceptions.ClienteExceptions.ClienteException;

public class ClientePFExceptions
{
    public abstract static class ClientePFException extends ClienteException
    {
        public ClientePFException(String message)
        {
            super(message);
        }
    }

    public static class CpfInvalidoException extends ClientePFException
    {
        static final String MESSAGE = "CPF inválido";

        public CpfInvalidoException()
        {
            super(MESSAGE);
        }
    }

    public static class RgInvalidoException extends ClientePFException
    {
        static final String MESSAGE = "RG inválido";

        public RgInvalidoException()
        {
            super(MESSAGE);
        }
    }

    public static class DataNascimentoInvalidaException extends ClientePFException
    {
        static final String MESSAGE = "Data de nascimento inválida";

        public DataNascimentoInvalidaException()
        {
            super(MESSAGE);
        }
    }
}
