create or replace PROCEDURE AtualizarConta(
    p_cd_conta IN t_fp_conta.cd_conta%type,
    p_vl_saldo IN t_fp_conta.vl_saldo%type,
    p_nr_conta IN t_fp_conta.nr_conta%type,
    p_nr_agencia IN t_fp_conta.nr_agencia%type,
    p_cd_tipo IN t_fp_conta.t_fp_tipo_conta_cd_tipo%type,
    p_cd_banco IN t_fp_conta.t_fp_banco_cd_banco%type
)
IS BEGIN
    UPDATE t_fp_conta
        SET vl_saldo = p_vl_saldo,
            nr_conta = p_nr_conta,
            nr_agencia = p_nr_agencia,
            t_fp_tipo_conta_cd_tipo = p_cd_tipo,
            t_fp_banco_cd_banco = p_cd_banco

            WHERE cd_conta = p_cd_conta;

    COMMIT;
END;