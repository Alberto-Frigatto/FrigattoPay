create or replace FUNCTION CadastrarClientePJ(
    p_nm_cliente IN t_fp_cliente.nm_cliente%type,
    p_ds_email IN t_fp_cliente.ds_email%type,
    p_ds_senha IN t_fp_cliente.ds_senha%type,
    p_nr_cnpj IN t_fp_cliente_pj.nr_cnpj%type,
    p_nr_inscricao_estadual IN t_fp_cliente_pj.nr_inscricao_estadual%type,
    p_dt_abertura IN t_fp_cliente_pj.dt_abertura%type,
    p_ds_setor IN t_fp_cliente_pj.ds_setor%type
) RETURN NUMBER
IS
    v_cd_cliente NUMBER;
BEGIN
    v_cd_cliente := CadastrarCliente(p_nm_cliente, p_ds_email, p_ds_senha);

    INSERT INTO t_fp_cliente_pj (t_fp_cliente_cd_cliente, nr_cnpj, nr_inscricao_estadual, dt_abertura, ds_setor)
        VALUES (v_cd_cliente, p_nr_cnpj, p_nr_inscricao_estadual, p_dt_abertura, p_ds_setor);

    COMMIT;

    RETURN v_cd_cliente;
END;