package com.model.entity.conta.exceptions;

public class ContaInvestimentoExceptions
{
    public static abstract class ContaInvestimentoException extends Exception
    {
        public ContaInvestimentoException(String message)
        {
            super(message);
        }
    }

    public static class ValorInvalidoException extends ContaInvestimentoException
    {
        private static final String MESSAGE = "Valor inválido";

        public ValorInvalidoException()
        {
            super(MESSAGE);
        }
    }

    public static class DataInvestimentoInvalidaException extends ContaInvestimentoException
    {
        private static final String MESSAGE = "Data de investimento inválida";

        public DataInvestimentoInvalidaException()
        {
            super(MESSAGE);
        }
    }
}
