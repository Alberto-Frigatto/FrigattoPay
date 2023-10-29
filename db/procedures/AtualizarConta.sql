create or replace PROCEDURE AtualizarConta(
    p_cd_conta IN t_fp_conta.cd_conta%type,
    p_vl_saldo IN t_fp_conta.vl_saldo%type
)
IS BEGIN
    UPDATE t_fp_conta
        SET vl_saldo = p_vl_saldo
            WHERE cd_conta = p_cd_conta;

    COMMIT;
END;