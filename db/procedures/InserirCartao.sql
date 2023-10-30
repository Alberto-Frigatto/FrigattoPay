create or replace PROCEDURE InserirCartao(
    p_nr_cartao IN t_fp_cartao.nr_cartao%type,
    p_cd_conta IN t_fp_conta.cd_conta%type,
    p_cd_bandeira IN t_fp_bandeira.cd_bandeira%type,
    p_cd_tipo_cartao IN t_fp_tipo_cartao.cd_tipo%type,
    p_dt_validade IN t_fp_cartao.dt_validade%type,
    p_nr_cvv IN t_fp_cartao.nr_cvv%type,
    p_vl_cartao_desbloqueado IN t_fp_cartao.vl_cartao_desbloqueado%type
)
IS BEGIN
    INSERT INTO t_fp_cartao
        (
            cd_cartao,
            nr_cartao,
            t_fp_conta_cd_conta,
            t_fp_bandeira_cd_bandeira,
            t_fp_tipo_cartao_cd_tipo,
            dt_validade,
            nr_cvv,
            vl_cartao_desbloqueado
        )
        VALUES (
            seq_cartao.nextval,
            p_nr_cartao,
            p_cd_conta,
            p_cd_bandeira,
            p_cd_tipo_cartao,
            p_dt_validade,
            p_nr_cvv,
            p_vl_cartao_desbloqueado
        );

    COMMIT;
END;