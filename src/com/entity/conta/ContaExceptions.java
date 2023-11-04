package com.entity.conta;

public class ContaExceptions
{
    public abstract static class ContaException extends Exception
    {
        public ContaException(String message)
        {
            super(message);
        }
    }

    public static class NumeroInvalidoException extends ContaException
    {
        static final String MESSAGE = "Número da conta inválido";

        public NumeroInvalidoException()
        {
            super(MESSAGE);
        }
    }

    public static class AgenciaInvalidaException extends ContaException
    {
        static final String MESSAGE = "Agência inválida";

        public AgenciaInvalidaException()
        {
            super(MESSAGE);
        }
    }

    public static class TransacaoInvalidaException extends ContaException
    {
        static final String MESSAGE = "Transação não permitida";

        public TransacaoInvalidaException()
        {
            super(MESSAGE);
        }
    }
}
