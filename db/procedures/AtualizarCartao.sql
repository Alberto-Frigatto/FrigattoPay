create or replace PROCEDURE AtualizarCartao(
    p_cd_cartao IN t_fp_cartao.cd_cartao%type,
    p_vl_cartao_desbloqueado IN t_fp_cartao.vl_cartao_desbloqueado%type
)
IS BEGIN
    UPDATE t_fp_cartao
        SET vl_cartao_desbloqueado = p_vl_cartao_desbloqueado
            WHERE cd_cartao = p_cd_cartao;

    COMMIT;
END;