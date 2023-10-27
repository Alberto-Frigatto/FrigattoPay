create or replace FUNCTION CadastrarCliente(
    p_nm_cliente IN t_fp_cliente.nm_cliente%type,
    p_ds_email IN t_fp_cliente.ds_email%type,
    p_ds_senha IN t_fp_cliente.ds_senha%type
) RETURN NUMBER
IS
    v_cd_cliente NUMBER;
BEGIN
    INSERT INTO t_fp_cliente (cd_cliente, nm_cliente, ds_email, ds_senha)
        VALUES (seq_cliente.nextval, p_nm_cliente, p_ds_email, p_ds_senha)
        RETURNING cd_cliente INTO v_cd_cliente;

    COMMIT;

    RETURN v_cd_cliente;
END;