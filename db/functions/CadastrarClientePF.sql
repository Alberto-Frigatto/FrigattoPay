create or replace FUNCTION CadastrarClientePF(
    p_nm_cliente IN t_fp_cliente.nm_cliente%type,
    p_ds_email IN t_fp_cliente.ds_email%type,
    p_ds_senha IN t_fp_cliente.ds_senha%type,
    p_nr_cpf IN t_fp_cliente_pf.nr_cpf%type,
    p_nr_rg IN t_fp_cliente_pf.nr_rg%type,
    p_dt_nascimento IN t_fp_cliente_pf.dt_nascimento%type
) RETURN NUMBER
IS
    v_cd_cliente NUMBER;
BEGIN
    v_cd_cliente := CadastrarCliente(p_nm_cliente, p_ds_email, p_ds_senha);

    INSERT INTO t_fp_cliente_pf (t_fp_cliente_cd_cliente, nr_cpf, nr_rg, dt_nascimento)
        VALUES (v_cd_cliente, p_nr_cpf, p_nr_rg, p_dt_nascimento);

    COMMIT;

    RETURN v_cd_cliente;
END;