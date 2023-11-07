package com.model.entity.conta.exceptions;

public class CartaoExceptions
{
    public static abstract class CartaoException extends Exception
    {
        public CartaoException(String message)
        {
            super(message);
        }
    }

    public static class NumeroInvalidoException extends CartaoException
    {
        static final String MESSAGE = "Número inválido";

        public NumeroInvalidoException()
        {
            super(MESSAGE);
        }
    }

    public static class DataValidadeInvalidaException extends CartaoException
    {
        static final String MESSAGE = "Validade inválida";

        public DataValidadeInvalidaException()
        {
            super(MESSAGE);
        }
    }

    public static class CvvInvalidoException extends CartaoException
    {
        static final String MESSAGE = "Cvv inválido";

        public CvvInvalidoException()
        {
            super(MESSAGE);
        }
    }
}
