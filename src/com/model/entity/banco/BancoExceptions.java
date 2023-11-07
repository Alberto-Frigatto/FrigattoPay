package com.model.entity.banco;

public class BancoExceptions
{
    public static abstract class BancoException extends Exception
    {
        public BancoException(String message)
        {
            super(message);
        }
    }

    public static class NomeInvalidoException extends BancoException
    {
        static final String MESSAGE = "Nome inválido";

        public NomeInvalidoException()
        {
            super(MESSAGE);
        }
    }

    public static class EmailInvalidoException extends BancoException
    {
        static final String MESSAGE = "Email inválido";

        public EmailInvalidoException()
        {
            super(MESSAGE);
        }
    }

    public static class CnpjInvalidoException extends BancoException
    {
        static final String MESSAGE = "CNPJ inválido";

        public CnpjInvalidoException()
        {
            super(MESSAGE);
        }
    }
}
