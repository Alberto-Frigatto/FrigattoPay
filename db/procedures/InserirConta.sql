create or replace PROCEDURE InserirConta(
    p_cd_cliente IN t_fp_cliente.cd_cliente%type,
    p_cd_banco IN t_fp_banco.cd_banco%type,
    p_cd_tipo_conta IN t_fp_tipo_conta.cd_tipo%type,
    p_nr_conta IN t_fp_conta.nr_conta%type,
    p_vl_saldo IN t_fp_conta.vl_saldo%type,
    p_nr_agencia IN t_fp_conta.nr_agencia%type
)
IS BEGIN
    INSERT INTO t_fp_conta
        (
            cd_conta,
            t_fp_cliente_cd_cliente,
            t_fp_banco_cd_banco,
            t_fp_tipo_conta_cd_tipo,
            nr_conta,
            vl_saldo,
            nr_agencia
        )
        VALUES (
            seq_conta.nextval,
            p_cd_cliente,
            p_cd_banco,
            p_cd_tipo_conta,
            p_nr_conta,
            p_vl_saldo,
            p_nr_agencia
        );

    COMMIT;
END;