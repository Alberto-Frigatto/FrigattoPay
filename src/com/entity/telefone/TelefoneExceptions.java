package com.entity.telefone;

public class TelefoneExceptions 
{
    public static class TelefoneException extends Exception
    {
        public TelefoneException(String message)
        {
            super(message);
        }
    }

    public static class NumeroInvalidoException extends TelefoneException
    {
        static final String MESSAGE = "Número inválido";

        public NumeroInvalidoException()
        {
            super(MESSAGE);
        }
    }

    public static class RamalInvalidoException extends TelefoneException
    {
        static final String MESSAGE = "Ramal inválido";

        public RamalInvalidoException()
        {
            super(MESSAGE);
        }
    }

    public static class DddInvalidoException extends TelefoneException
    {
        static final String MESSAGE = "DDD inválido";

        public DddInvalidoException()
        {
            super(MESSAGE);
        }
    }
}
