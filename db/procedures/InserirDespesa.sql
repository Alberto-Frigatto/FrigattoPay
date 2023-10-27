create or replace PROCEDURE InserirDespesa(
    p_nm_despesa IN t_fp_despesa.nm_despesa%type,
    p_vl_despesa IN t_fp_despesa.vl_despesa%type,
    p_ds_despesa IN t_fp_despesa.ds_despesa%type,
    p_dt_vencimento IN t_fp_despesa.dt_vencimento%type,
    p_cd_tipo IN t_fp_despesa.T_FP_TIPO_DESPESA_CD_TIPO%type,
    p_cd_cliente IN t_fp_despesa.t_fp_cliente_cd_cliente%type
)
IS BEGIN

    INSERT INTO t_fp_despesa (cd_despesa, nm_despesa, vl_despesa, ds_despesa, dt_vencimento, T_FP_TIPO_DESPESA_CD_TIPO, t_fp_cliente_cd_cliente)
        VALUES (
            seq_despesa.NEXTVAL,
            p_nm_despesa,
            p_vl_despesa,
            p_ds_despesa,
            p_dt_vencimento,
            p_cd_tipo,
            p_cd_cliente
        );

    COMMIT;
END;