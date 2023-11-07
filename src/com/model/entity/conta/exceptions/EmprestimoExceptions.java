package com.model.entity.conta.exceptions;

public class EmprestimoExceptions
{
    public static abstract class EmprestimoException extends Exception
    {
        public EmprestimoException(String message)
        {
            super(message);
        }
    }

    public static class ValorInvalidoException extends EmprestimoException
    {
        static final String MESSAGE = "Valor inválido";

        public ValorInvalidoException()
        {
            super(MESSAGE);
        }
    }

    public static class ValorJurosInvalidoException extends EmprestimoException
    {
        static final String MESSAGE = "Valor de juros inválido";

        public ValorJurosInvalidoException()
        {
            super(MESSAGE);
        }
    }
    
    public static class ValorParcelaInvalidoException extends EmprestimoException
    {
        static final String MESSAGE = "Valor de parcela inválido";
        
        public ValorParcelaInvalidoException()
        {
            super(MESSAGE);
        }
    }

    public static class DataPrazoInvalidaException extends EmprestimoException
    {
        static final String MESSAGE = "Data prazo inválida";

        public DataPrazoInvalidaException()
        {
            super(MESSAGE);
        }
    }

    public static class DataSolicitacaoInvalidaException extends EmprestimoException
    {
        static final String MESSAGE = "Data de solicitação inválida";

        public DataSolicitacaoInvalidaException()
        {
            super(MESSAGE);
        }
    }

    public static class DiaVencimentoParcelaInvalidoException extends EmprestimoException
    {
        static final String MESSAGE = "Dia de vencimento da parcela inválido";

        public DiaVencimentoParcelaInvalidoException()
        {
            super(MESSAGE);
        }
    }
}
