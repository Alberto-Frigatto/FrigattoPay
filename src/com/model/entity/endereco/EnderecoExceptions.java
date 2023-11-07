package com.model.entity.endereco;

public class EnderecoExceptions
{
    public static abstract class EnderecoException extends Exception
    {
        public EnderecoException(String message)
        {
            super(message);
        }
    }

    public static class CepInvalidoException extends EnderecoException
    {
        static final String MESSAGE = "CEP inválido";

        public CepInvalidoException()
        {
            super(MESSAGE);
        }
    }

    public static class LogradouroInvalidoException extends EnderecoException
    {
        static final String MESSAGE = "Logradouro inválido";

        public LogradouroInvalidoException()
        {
            super(MESSAGE);
        }
    }

    public static class NumeroInvalidoException extends EnderecoException
    {
        static final String MESSAGE = "Número inválido";

        public NumeroInvalidoException()
        {
            super(MESSAGE);
        }
    }

    public static class ComplementoInvalidoException extends EnderecoException
    {
        static final String MESSAGE = "Complemento inválido";

        public ComplementoInvalidoException()
        {
            super(MESSAGE);
        }
    }

    public static class MunicipioInvalidoException extends EnderecoException
    {
        static final String MESSAGE = "Município inválido";

        public MunicipioInvalidoException()
        {
            super(MESSAGE);
        }
    }
}
