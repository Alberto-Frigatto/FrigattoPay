create or replace PROCEDURE InserirTransacao(
    p_cd_conta_origem IN t_fp_conta.cd_conta%type,
    p_cd_conta_destino IN t_fp_conta.cd_conta%type,
    p_cd_tipo_transc IN t_fp_tipo_transc.cd_tipo%type,
    p_vl_transc IN t_fp_transc.vl_transc%type,
    p_dt_transc IN t_fp_transc.dt_transc%type
)
IS BEGIN
    INSERT INTO t_fp_transc
        (
            cd_transc,
            t_fp_conta_cd_conta,
            t_fp_conta_cd_conta2,
            t_fp_tipo_transc_cd_tipo,
            vl_transc,
            dt_transc
        )
        VALUES (
            seq_transc.nextval,
            p_cd_conta_origem,
            p_cd_conta_destino,
            p_cd_tipo_transc,
            p_vl_transc,
            p_dt_transc
        );

    COMMIT;
END;