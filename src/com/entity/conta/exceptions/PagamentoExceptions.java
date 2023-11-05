package com.entity.conta.exceptions;

public class PagamentoExceptions
{
    public static abstract class PagamentoException extends Exception
    {
        public PagamentoException(String message)
        {
            super(message);
        }
    }

    public static class NomeInvalidoException extends PagamentoException
    {
        static final String MESSAGE = "Nome inválido";

        public NomeInvalidoException()
        {
            super(MESSAGE);
        }
    }

    public static class CodigoBarrasInvalidoException extends PagamentoException
    {
        static final String MESSAGE = "Código de barras inválido";

        public CodigoBarrasInvalidoException()
        {
            super(MESSAGE);
        }
    }

    public static class ValorInvalidoException extends PagamentoException
    {
        static final String MESSAGE = "Valor inválido";

        public ValorInvalidoException()
        {
            super(MESSAGE);
        }
    }

    public static class DataPagamentoInvalidaException extends PagamentoException
    {
        static final String MESSAGE = "Data inválida inválido";

        public DataPagamentoInvalidaException()
        {
            super(MESSAGE);
        }
    }
}
