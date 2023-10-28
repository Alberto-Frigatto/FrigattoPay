create or replace PROCEDURE AtualizarDespesa(
    p_cd_despesa IN t_fp_despesa.cd_despesa%type,
    p_nm_despesa IN t_fp_despesa.nm_despesa%type,
    p_vl_despesa IN t_fp_despesa.vl_despesa%type,
    p_ds_despesa IN t_fp_despesa.ds_despesa%type,
    p_dt_vencimento IN t_fp_despesa.dt_vencimento%type,
    p_cd_tipo IN t_fp_tipo_despesa.cd_tipo%type
)
IS BEGIN

    UPDATE t_fp_despesa
        SET nm_despesa = p_nm_despesa,
            vl_despesa = p_vl_despesa,
            ds_despesa = p_ds_despesa,
            dt_vencimento = p_dt_vencimento,
            t_fp_tipo_despesa_cd_tipo = p_cd_tipo
        WHERE cd_despesa = p_cd_despesa;

    COMMIT;
END;