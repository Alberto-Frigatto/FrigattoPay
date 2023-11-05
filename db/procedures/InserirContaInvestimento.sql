create or replace PROCEDURE InserirContaInvestimento(
    p_dt_invs IN t_fp_conta_invs.dt_invs%type,
    p_vl_invs IN t_fp_conta_invs.vl_invs%type,
    p_cd_invs IN t_fp_invs.cd_invs%type,
    p_cd_conta IN t_fp_conta.cd_conta%type
)
IS BEGIN
    INSERT INTO t_fp_conta_invs
        (
            cd_conta_invs,
            dt_invs,
            vl_invs,
            t_fp_invs_cd_invs,
            t_fp_conta_cd_conta
        )
        VALUES (
            seq_conta_invs.nextval,
            p_dt_invs,
            p_vl_invs,
            p_cd_invs,
            p_cd_conta
        );

    COMMIT;
END;



