create or replace PROCEDURE InserirTelefoneCliente(
    p_cd_cliente IN t_fp_cliente.cd_cliente%type,
    p_nr_telefone IN t_fp_telefone.nr_telefone%type,
    p_nr_ramal IN t_fp_telefone.nr_ramal%type := NULL,
    p_nr_ddd IN t_fp_telefone.t_fp_ddd_nr_ddd%type,
    p_cd_tipo_telefone IN t_fp_telefone.t_fp_tipo_tel_cd_tipo%type
)
IS
    v_cd_telefone NUMBER;
BEGIN
    INSERT INTO t_fp_telefone
        (
            cd_telefone,
            nr_telefone,
            nr_ramal,
            t_fp_ddd_nr_ddd,
            t_fp_tipo_tel_cd_tipo,
            t_fp_cliente_cd_cliente
        )
        VALUES (
            seq_telefone.nextval,
            p_nr_telefone,
            p_nr_ramal,
            p_nr_ddd,
            p_cd_tipo_telefone,
            p_cd_cliente
        );

    COMMIT;
END;