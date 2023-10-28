create or replace PROCEDURE AtualizarReceita(
    p_cd_receita IN t_fp_receita.cd_receita%type,
    p_cd_tipo_receita IN t_fp_tipo_receita.cd_tipo%type,
    p_cd_cliente IN t_fp_cliente.cd_cliente%type,
    p_vl_receita IN t_fp_receita.vl_receita%type,
    p_dt_receita IN t_fp_receita.dt_receita%type
)
IS BEGIN

    UPDATE t_fp_receita
        SET t_fp_tipo_receita_cd_tipo = p_cd_tipo_receita,
            t_fp_cliente_cd_cliente = p_cd_cliente,
            vl_receita = p_vl_receita,
            dt_receita = p_dt_receita
        WHERE cd_receita = p_cd_receita;

    COMMIT;
END;