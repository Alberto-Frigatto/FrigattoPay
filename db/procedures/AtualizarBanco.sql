create or replace PROCEDURE AtualizarBanco(
    p_cd_banco IN t_fp_banco.cd_banco%type,
    p_nr_cnpj IN t_fp_banco.nr_cnpj%type,
    p_nm_banco_parceiro IN t_fp_banco.nm_banco_parceiro%type,
    p_ds_email IN t_fp_banco.ds_email%type
)
IS BEGIN

    UPDATE t_fp_banco
        SET nr_cnpj = p_nr_cnpj,
            nm_banco_parceiro = p_nm_banco_parceiro,
            ds_email = p_ds_email
        WHERE cd_banco = p_cd_banco;

    COMMIT;
END;