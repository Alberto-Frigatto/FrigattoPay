create or replace PROCEDURE AtualizarClientePJ(
    p_cd_cliente IN t_fp_cliente.cd_cliente%type,
    p_nm_cliente IN t_fp_cliente.nm_cliente%type,
    p_ds_email IN t_fp_cliente.ds_email%type,
    p_ds_senha IN t_fp_cliente.ds_senha%type,
    p_nr_cnpj IN t_fp_cliente_pj.nr_cnpj%type,
    p_nr_inscricao_estadual IN t_fp_cliente_pj.nr_inscricao_estadual%type,
    p_dt_abertura IN t_fp_cliente_pj.dt_abertura%type,
    p_ds_setor IN t_fp_cliente_pj.ds_setor%type
)
IS BEGIN

    UPDATE t_fp_cliente
        SET nm_cliente = p_nm_cliente,
            ds_email = p_ds_email,
            ds_senha = p_ds_senha
        WHERE cd_cliente = p_cd_cliente;

    UPDATE t_fp_cliente_pj
        SET nr_cnpj = p_nr_cnpj,
            nr_inscricao_estadual = p_nr_inscricao_estadual,
            dt_abertura = p_dt_abertura,
            ds_setor = p_ds_setor
        WHERE t_fp_cliente_cd_cliente = p_cd_cliente;

    COMMIT;
END;