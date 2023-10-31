create or replace PROCEDURE InserirParcelaEmprestimo(
    p_cd_empr IN t_fp_empr.cd_empr%type,
    p_vl_pago IN t_fp_parcela_empr.vl_pago%type,
    p_dt_pagamento IN t_fp_parcela_empr.dt_pagamento%type,
    p_vl_juros IN t_fp_parcela_empr.vl_juros%type
)
IS BEGIN
    INSERT INTO t_fp_parcela_empr
        (
            cd_parcela,
            t_fp_empr_cd_empr,
            vl_pago,
            dt_pagamento,
            vl_juros
        )
        VALUES (
            seq_parcela_empr.nextval,
            p_cd_empr,
            p_vl_pago,
            p_dt_pagamento,
            p_vl_juros
        );

    COMMIT;
END;