create or replace PROCEDURE AtualizarTelefoneCliente(
    p_cd_telefone IN t_fp_telefone.cd_telefone%type,
    p_cd_cliente IN t_fp_cliente.cd_cliente%type,
    p_nr_telefone IN t_fp_telefone.nr_telefone%type,
    p_nr_ramal IN t_fp_telefone.nr_ramal%type,
    p_nr_ddd IN t_fp_ddd.nr_ddd%type
)
IS BEGIN
    UPDATE t_fp_telefone
        SET t_fp_cliente_cd_cliente = p_cd_cliente,
            nr_telefone = p_nr_telefone,
            nr_ramal = p_nr_ramal,
            t_fp_ddd_nr_ddd = p_nr_ddd
        WHERE cd_telefone = p_cd_telefone;

    COMMIT;
END;