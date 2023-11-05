package com.entity.conta.exceptions;

public class ParcelaEmprestimoExceptions
{
    public static abstract class ParcelaEmprestimoException extends Exception
    {
        public ParcelaEmprestimoException(String message)
        {
            super(message);
        }
    }

    public static class ValorInvalidoException extends ParcelaEmprestimoException
    {
        private static final String MESSAGE = "Valor inválido";

        public ValorInvalidoException()
        {
            super(MESSAGE);
        }
    }

    public static class DataPagamentoInvalidaException extends ParcelaEmprestimoException
    {
        private static final String MESSAGE = "Data de pagamento inválida";

        public DataPagamentoInvalidaException()
        {
            super(MESSAGE);
        }
    }
}
