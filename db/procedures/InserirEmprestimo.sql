create or replace PROCEDURE InserirEmprestimo(
    p_cd_conta IN t_fp_empr.t_fp_conta_cd_conta%type,
    p_vl_empr IN t_fp_empr.vl_empr%type,
    p_vl_juros IN t_fp_empr.vl_juros%type,
    p_dt_prazo IN t_fp_empr.dt_prazo%type,
    p_dt_solicitacao IN t_fp_empr.dt_solicitacao%type,
    p_vl_parcela IN t_fp_empr.vl_parcela%type,
    p_dia_vencimento_parcela IN t_fp_empr.dia_vencimento_parcela%type
)
IS BEGIN
    INSERT INTO t_fp_empr
        (
            cd_empr,
            t_fp_conta_cd_conta,
            vl_empr,
            vl_juros,
            dt_prazo,
            dt_solicitacao,
            vl_parcela,
            dia_vencimento_parcela
        )
        VALUES (
            seq_empr.nextval,
            p_cd_conta,
            p_vl_empr,
            p_vl_juros,
            p_dt_prazo,
            p_dt_solicitacao,
            p_vl_parcela,
            p_dia_vencimento_parcela
        );
    COMMIT;
END;