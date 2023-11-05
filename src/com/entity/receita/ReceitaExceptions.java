package com.entity.receita;

public class ReceitaExceptions
{
    public static abstract class ReceitaException extends Exception
    {
        public ReceitaException(String message)
        {
            super(message);
        }
    }

    public static class ValorInvalidoException extends ReceitaException
    {
        static final String MESSAGE = "Valor inválido";

        public ValorInvalidoException()
        {
            super(MESSAGE);
        }
    }

    public static class DataReceitaInvalidaException extends ReceitaException
    {
        static final String MESSAGE = "Data inválida";

        public DataReceitaInvalidaException()
        {
            super(MESSAGE);
        }
    }
}
