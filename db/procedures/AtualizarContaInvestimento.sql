create or replace PROCEDURE AtualizarContaInvestimento(
    p_cd_conta_invs IN t_fp_conta_invs.cd_conta_invs%type,
    p_vl_invs IN t_fp_conta_invs.vl_invs%type
)
IS BEGIN
    UPDATE t_fp_conta_invs
        SET vl_invs = p_vl_invs
        WHERE cd_conta_invs = p_cd_conta_invs;

    COMMIT;
END;


