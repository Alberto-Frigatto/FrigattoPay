package com.model.entity.despesa;

public class DespesaExceptions
{
    public static abstract class DespesaException extends Exception
    {
        public DespesaException(String message)
        {
            super(message);
        }
    }

    public static class NomeInvalidoException extends DespesaException
    {
        static final String MESSAGE = "Nome inválido";

        public NomeInvalidoException()
        {
            super(MESSAGE);
        }
    }

    public static class ValorInvalidoException extends DespesaException
    {
        static final String MESSAGE = "Valor inválido";

        public ValorInvalidoException()
        {
            super(MESSAGE);
        }
    }

    public static class DescricaoInvalidaException extends DespesaException
    {
        static final String MESSAGE = "Descrição inválida";

        public DescricaoInvalidaException()
        {
            super(MESSAGE);
        }
    }

    public static class DataVencimentoInvalidaException extends DespesaException
    {
        static final String MESSAGE = "Data de vencimento inválida";

        public DataVencimentoInvalidaException()
        {
            super(MESSAGE);
        }
    }
}
