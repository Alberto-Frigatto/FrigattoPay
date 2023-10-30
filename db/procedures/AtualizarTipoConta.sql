create or replace PROCEDURE AtualizarTipoConta(
    p_cd_tipo IN t_fp_tipo_conta.cd_tipo%type,
    p_nm_tipo_conta IN t_fp_tipo_conta.nm_tipo%type,
)
IS BEGIN

    UPDATE t_fp_tipo_conta
        SET nm_tipo = p_nm_tipo
        WHERE cd_tipo = p_cd_tipo;

    COMMIT;
END;