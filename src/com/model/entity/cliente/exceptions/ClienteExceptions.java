package com.model.entity.cliente.exceptions;

public class ClienteExceptions
{
    public static abstract class ClienteException extends Exception
    {
        public ClienteException(String message)
        {
            super(message);
        }
    }

    public static class NomeInvalidoException extends ClienteException
    {
        static final String MESSAGE = "Nome inválido";

        public NomeInvalidoException()
        {
            super(MESSAGE);
        }
    }

    public static class EmailInvalidoException extends ClienteException
    {
        static final String MESSAGE = "Email inválido";

        public EmailInvalidoException()
        {
            super(MESSAGE);
        }
    }

    public static class SenhaInvalidaException extends ClienteException
    {
        static final String MESSAGE = "Senha inválida";

        public SenhaInvalidaException()
        {
            super(MESSAGE);
        }
    }
}
