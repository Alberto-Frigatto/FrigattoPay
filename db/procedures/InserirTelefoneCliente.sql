create or replace PROCEDURE InserirTelefoneCliente(
    p_cd_cliente IN t_fp_cliente.cd_cliente%type,
    p_nr_telefone IN t_fp_telefone.nr_telefone%type,
    p_nr_ramal IN t_fp_telefone.nr_ramal%type,
    p_nr_ddd IN t_fp_ddd.nr_ddd%type
)
IS BEGIN
    INSERT INTO t_fp_telefone
        (
            cd_telefone,
            nr_telefone,
            nr_ramal,
            t_fp_ddd_nr_ddd,
            t_fp_cliente_cd_cliente
        )
        VALUES (
            seq_telefone.nextval,
            p_nr_telefone,
            p_nr_ramal,
            p_nr_ddd,
            p_cd_cliente
        );

    COMMIT;
END;