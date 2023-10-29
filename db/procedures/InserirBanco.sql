create or replace PROCEDURE InserirBanco(
    p_nr_cnpj IN t_fp_banco.nr_cnpj%type,
    p_nm_banco_parceiro IN t_fp_banco.nm_banco_parceiro%type,
    p_ds_email IN t_fp_banco.ds_email%type
)
IS BEGIN

    INSERT INTO t_fp_banco (cd_banco, nr_cnpj, nm_banco_parceiro, ds_email)
        VALUES (
            seq_banco.nextval,
            p_nr_cnpj,
            p_nm_banco_parceiro,
            p_ds_email
        );

    COMMIT;
END;
