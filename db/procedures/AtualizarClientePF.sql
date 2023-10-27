create or replace PROCEDURE AtualizarClientePF(
    p_cd_cliente IN t_fp_cliente.cd_cliente%type,
    p_nm_cliente IN t_fp_cliente.nm_cliente%type,
    p_ds_email IN t_fp_cliente.ds_email%type,
    p_ds_senha IN t_fp_cliente.ds_senha%type,
    p_nr_cpf IN t_fp_cliente_pf.nr_cpf%type,
    p_nr_rg IN t_fp_cliente_pf.nr_rg%type,
    p_dt_nascimento IN t_fp_cliente_pf.dt_nascimento%type
)
IS BEGIN

    UPDATE t_fp_cliente
        SET nm_cliente = p_nm_cliente,
            ds_email = p_ds_email,
            ds_senha = p_ds_senha
        WHERE cd_cliente = p_cd_cliente;

    UPDATE t_fp_cliente_pf
        SET nr_cpf = p_nr_cpf,
            nr_rg = p_nr_rg,
            dt_nascimento = p_dt_nascimento
        WHERE t_fp_cliente_cd_cliente = p_cd_cliente;

    COMMIT;
END;