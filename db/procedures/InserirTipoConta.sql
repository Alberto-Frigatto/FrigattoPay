create or replace PROCEDURE InserirTipoConta(
    p_nm_tipo IN t_fp_tipo_conta.nm_tipo%type
)
IS BEGIN

    INSERT INTO t_fp_tipo_conta (cd_tipo, nm_tipo)
        VALUES (
            seq_tipo_conta.NEXTVAL,
            p_nm_tipo
        );

    COMMIT;
END;