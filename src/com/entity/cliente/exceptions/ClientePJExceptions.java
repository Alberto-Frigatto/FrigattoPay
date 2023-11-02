package com.entity.cliente.exceptions;

import com.entity.cliente.exceptions.ClienteExceptions.ClienteException;

public class ClientePJExceptions
{
    public abstract static class ClientePJException extends ClienteException
    {
        public ClientePJException(String message)
        {
            super(message);
        }
    }

    public static class CnpjInvalidoException extends ClientePJException
    {
        static final String MESSAGE = "CNPJ inválido";

        public CnpjInvalidoException()
        {
            super(MESSAGE);
        }
    }

    public static class InscricaoEstadualInvalidaException extends ClientePJException
    {
        static final String MESSAGE = "Inscrição estadual inválida";

        public InscricaoEstadualInvalidaException()
        {
            super(MESSAGE);
        }
    }

    public static class SetorInvalidoException extends ClientePJException
    {
        static final String MESSAGE = "Setor inválido";

        public SetorInvalidoException()
        {
            super(MESSAGE);
        }
    }

    public static class DataAberturaInvalidaException extends ClientePJException
    {
        static final String MESSAGE = "Data de abertura inválida";

        public DataAberturaInvalidaException()
        {
            super(MESSAGE);
        }
    }
}
