create or replace PROCEDURE InserirReceita(
    p_cd_tipo_receita IN t_fp_receita.t_fp_tipo_receita_cd_tipo%type,
    p_cd_cliente IN t_fp_cliente.cd_cliente%type,
    p_vl_receita IN t_fp_receita.vl_receita%type,
    p_dt_receita IN t_fp_receita.dt_receita%type
)
IS BEGIN
    INSERT INTO t_fp_receita
        (
            cd_receita,
            t_fp_tipo_receita_cd_tipo,
            t_fp_cliente_cd_cliente,
            vl_receita,
            dt_receita
        )
        VALUES (
            seq_receita.nextval,
            p_cd_tipo_receita,
            p_cd_cliente,
            p_vl_receita,
            p_dt_receita
        );
    COMMIT;
END;